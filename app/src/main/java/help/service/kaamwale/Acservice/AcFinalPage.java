package help.service.kaamwale.Acservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import help.service.kaamwale.HomeActivity;
import help.service.kaamwale.Login.User;
import help.service.kaamwale.Plumbingservice.PlumbingFinalPage;
import help.service.kaamwale.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AcFinalPage extends AppCompatActivity {
    TextView addreess,duration;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private final static String default_notification_channel_id = "default";
    DatabaseReference databaseReference;
    FirebaseUser user;
    DatabaseReference reference;
    Button btnCreateNotification;
    String userID;
    TextView fullNameTextView,emailTextView,numberTextView,type;
    RadioButton rd1, rd2;
    RadioGroup rdg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_final_page);


      /*  rd1 = findViewById(R.id.rd1);*/
        rd2 = findViewById(R.id.acrd2);
        rdg = findViewById(R.id.acRadiogroup);

        addreess = findViewById(R.id.acadr1);
        duration = findViewById(R.id.acduration);

        duration.setText(getIntent().getStringExtra("Text"));
        addreess.setText(getIntent().getStringExtra("society"));


        btnCreateNotification = findViewById(R.id.acbs);
        btnCreateNotification.setOnClickListener(v -> {

            int selectedId = rdg.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(AcFinalPage.this, "Kindly select Your Payment Method",Toast.LENGTH_SHORT).show();
            } else {
                RadioButton radioButton = (RadioButton) rdg.findViewById(selectedId);
                Intent snoozeIntent = new Intent(AcFinalPage.this, HomeActivity.class) ;
                snoozeIntent.setAction("ACTION_SNOOZE") ;
                PendingIntent snoozePendingIntent = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                    snoozePendingIntent = PendingIntent.getActivity
                            (this, 0, snoozeIntent, PendingIntent.FLAG_MUTABLE);
                }
                else
                {
                    snoozePendingIntent = PendingIntent.getActivity
                            (this, 0, snoozeIntent, PendingIntent.FLAG_ONE_SHOT);
                }
              /*  PendingIntent snoozePendingIntent = PendingIntent.getActivity (AcFinalPage.this,0,snoozeIntent,PendingIntent.FLAG_UPDATE_CURRENT);*/
                NotificationManager mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE) ;
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(AcFinalPage.this,default_notification_channel_id) ;
                mBuilder.setContentTitle("KaamWale") ;
                mBuilder.setContentIntent(snoozePendingIntent);
                mBuilder.setContentText("Congratulations! Your Order Is Confirmed!") ;
                mBuilder.setTicker("Congratulations! Your Order Is Confirmed!") ;
                mBuilder.setSmallIcon(R.drawable.carimg123) ;
                mBuilder.setAutoCancel(true);
                int importance = NotificationManager. IMPORTANCE_HIGH ;
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"NOTIFICATION_CHANNEL_NAME",importance) ;
                mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID) ;
                assert mNotificationManager != null;
                mNotificationManager.createNotificationChannel(notificationChannel) ;
                mNotificationManager.notify((int) System.currentTimeMillis(),mBuilder.build()) ;

                addDatatoFirebase();
            }
        });

        duration.setText(getIntent().getStringExtra("Text"));
        addreess.setText(getIntent().getStringExtra("society"));

        databaseReference = FirebaseDatabase.getInstance().getReference("AC_Details");

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        fullNameTextView = findViewById(R.id.acfullName);
        emailTextView = findViewById(R.id.acemailAddress);
        numberTextView = findViewById(R.id.acPhoneNumber);
        type = findViewById(R.id.acType);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String fullName = userProfile.fullName;
                    String email = userProfile.email;
                    String number = userProfile.number;

                    fullNameTextView.setText(fullName);
                    emailTextView.setText(email);
                    numberTextView.setText(number);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AcFinalPage.this, "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(AcFinalPage.this , HomeActivity.class);
        startActivity(intent);
    }
    private void addDatatoFirebase() {

        String Type = type.getText().toString();
        String Address= addreess.getText().toString();
        String Duration = duration.getText().toString();
        String Email = emailTextView.getText().toString();
        String Name = fullNameTextView.getText().toString();
        String Number = numberTextView.getText().toString();

        ACuserDetails ac_user_details = new ACuserDetails(Address,Duration,Email,Name,Number,Type);
        databaseReference.push().setValue(ac_user_details);

        Intent intent = new Intent(AcFinalPage.this , HomeActivity.class);
        startActivity(intent);


    }
}