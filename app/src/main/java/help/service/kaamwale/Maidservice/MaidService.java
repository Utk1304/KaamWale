package help.service.kaamwale.Maidservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import help.service.kaamwale.HomeActivity;
import help.service.kaamwale.R;

public class MaidService extends AppCompatActivity {

    Button bs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maid_service);

        bs=findViewById(R.id.Maidbs2);
        bs.setOnClickListener(v -> {
            Intent intent1=new Intent(MaidService.this,MaidDetails.class);
            startActivity(intent1);
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(MaidService.this, HomeActivity.class);
        startActivity(intent);
    }
}
