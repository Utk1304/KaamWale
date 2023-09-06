package help.service.kaamwale.Acservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import help.service.kaamwale.HomeActivity;
import help.service.kaamwale.R;

public class AcService extends AppCompatActivity {

    Button bs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_service);

        bs=findViewById(R.id.acbs1);
        bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(AcService.this,AcDetails.class);
                startActivity(intent1);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(AcService.this, HomeActivity.class);
        startActivity(intent);
    }
}
