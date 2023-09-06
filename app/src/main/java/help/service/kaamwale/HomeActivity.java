package help.service.kaamwale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import help.service.kaamwale.Fragment.ChatFragment;
import help.service.kaamwale.Fragment.HomeFragment;
import help.service.kaamwale.Fragment.SettingsFragment;


public class HomeActivity extends AppCompatActivity {
    private MeowBottomNavigation bnv_Main;
    private static final int TIME_INTERVAL = 3000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    //=======================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//=======================================
        bnv_Main = (MeowBottomNavigation) findViewById(R.id.bottomvav);
        bnv_Main.add(new MeowBottomNavigation.Model(2,R.drawable.profile));
        bnv_Main.add(new MeowBottomNavigation.Model(1,R.drawable.home));
        bnv_Main.add(new MeowBottomNavigation.Model(3,R.drawable.settings));
//=======================================
        bnv_Main.show(1,true);
        replace(new HomeFragment());
        bnv_Main.setOnClickMenuListener(model -> {
            switch (model.getId()){
                case 1:
                    replace(new HomeFragment());
                    break;

                case 2:
                    replace(new ChatFragment());
                    break;

                case 3:
                    replace(new SettingsFragment());
                    break;

            }
            return null;
        });


    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();

    }


    public void onBackPressed()
    {
        Log.d("back","Backpressed");
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




}
