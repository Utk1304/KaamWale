package help.service.kaamwale.Plumbingservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import help.service.kaamwale.HomeActivity;
import help.service.kaamwale.R;

public class PlumbingService extends AppCompatActivity {

    Button bs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumbing_service);

        bs=findViewById(R.id.plumbingbs12);
        bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(PlumbingService.this,PlumbingDetails.class);
                startActivity(intent1);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(PlumbingService.this, HomeActivity.class);
        startActivity(intent);
    }
}