package help.service.kaamwale;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import help.service.kaamwale.Login.MainActivity;
import help.service.kaamwale.R;

public class SplashScreen extends AppCompatActivity {

    Animation topAnimation, bottomAnimation, middleAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_up);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        final ImageView image1 = findViewById(R.id.image1);
        final ImageView image2 = findViewById(R.id.image2);
        final ImageView image3 = findViewById(R.id.image3);
        final ImageView image4 = findViewById(R.id.image4);
        final ImageView image5 = findViewById(R.id.image5);
        final ImageView image6 = findViewById(R.id.image6);
        final ImageView text1 = findViewById(R.id.app_title);


        text1.setAnimation(middleAnimation);
        image1.setAnimation(topAnimation);
        image2.setAnimation(topAnimation);
        image3.setAnimation(topAnimation);
        image4.setAnimation(bottomAnimation);
        image5.setAnimation(bottomAnimation);
        image6.setAnimation(bottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}