package help.service.kaamwale.Login;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import help.service.kaamwale.HomeActivity;
import help.service.kaamwale.R;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int TIME_INTERVAL = 3000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    private TextView register, forgotPassword;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;
    ProgressBar progressBar;
    SharedPreferences sp;
    private SignInButton googlebtn;
    private FirebaseAuth mAuth;
    int autosave;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        register = findViewById(R.id.register);
        register.setOnClickListener(this);

        signIn = findViewById(R.id.registersignIn);
        signIn.setOnClickListener(this);

        editTextEmail = findViewById(R.id.registeremail);
        editTextPassword = findViewById(R.id.registerpassword);

        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar1);

        forgotPassword = findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);

        initGoogleAds();

        sp = getSharedPreferences("autoLogin", Context.MODE_PRIVATE);
        int j = sp.getInt("key", 0);
        if (j > 0){
            Intent activity = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(activity);
            finish();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                startActivity(new Intent(this, RegisterUser.class));
                break;
            case R.id.registersignIn:
                userLogin();
                autosave = 1;
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("key",autosave);
                editor.apply();
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(this, ForgotPassword.class));

        }
    }
    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide valid email!");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Min password length should be 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (user.isEmailVerified()) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
            } else {
                Toast.makeText(MainActivity.this, "Failed to login ! Try again!", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "Press Back Again To Exit", Toast.LENGTH_LONG).show(); }
        mBackPressed = System.currentTimeMillis();
    }


    private void initGoogleAds(){

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Toast.makeText(MainActivity.this, "Ad Init", Toast.LENGTH_LONG).show();

                AdView mAdView = findViewById(R.id.adView);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);

                mAdView.setAdListener(new AdListener() {
                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                    }

                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        mAdView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdOpened() {
                        super.onAdOpened();
                    }

                    @Override
                    public void onAdSwipeGestureClicked() {
                        super.onAdSwipeGestureClicked();
                    }
                });
            }
        });
    }




}

