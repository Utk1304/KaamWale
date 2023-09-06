package help.service.kaamwale.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import help.service.kaamwale.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextFullName,editTextNumber,editTextEmail,editTextPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        TextView banner = findViewById(R.id.banner3);
        banner.setOnClickListener(this);

        TextView registerUser = findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextFullName = findViewById(R.id.fullName1);
        editTextNumber = findViewById(R.id.number2);
        editTextEmail = findViewById(R.id.email1);
        editTextPassword = findViewById(R.id.password1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.banner3:
                startActivity(new Intent(this , MainActivity.class));
                break;

            case R.id.registerUser:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullName = editTextFullName.getText().toString().trim();
        String number = editTextNumber.getText().toString().trim();

        if(fullName.isEmpty()){
            editTextFullName.setError("Full name is required!");
            editTextFullName.requestFocus();
            return;
        }
        if (number.isEmpty()){
            editTextNumber.setError("Phone Number is required!");
            editTextNumber.requestFocus();
            return;
        }
        if (number.length() < 10){
            editTextNumber.setError("Phone Number Must Be 10 Digits!");
            editTextNumber.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide valid email!");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6){
            editTextPassword.setError("Min password length should be 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        User user = new User(fullName , number , email);
                        FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();



                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()){
                                        assert user1 != null;
                                        user1.sendEmailVerification();
                                        Toast.makeText(RegisterUser.this, "Check your email to verify your account!", Toast.LENGTH_LONG).show();

                                        Intent intent = new Intent(RegisterUser.this , MainActivity.class);
                                        startActivity(intent);
                                        //redirect to login layout!
                                    }
                                    else {
                                        Toast.makeText(RegisterUser.this, "Failed to register ! Try again!", Toast.LENGTH_LONG).show();
                                    }
                                });
                    }else {
                        Toast.makeText(RegisterUser.this, "Failed to register!", Toast.LENGTH_LONG).show();
                    }
                });


    }
}