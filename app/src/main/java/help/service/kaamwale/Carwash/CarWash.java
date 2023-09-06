package help.service.kaamwale.Carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import help.service.kaamwale.R;

public class CarWash extends AppCompatActivity {

    Button bs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_wash);

        bs=findViewById(R.id.carbs1);
        bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(CarWash.this,Cardetail.class);
                startActivity(intent1);
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
/*        Intent intent=new Intent(CarWash.this, HomeActivity.class);
        startActivity(intent);*/
    }
}