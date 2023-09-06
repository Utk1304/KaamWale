package help.service.kaamwale.Maidservice;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import help.service.kaamwale.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.card.MaterialCardView;
public class MaidDetails extends AppCompatActivity {

    RadioButton rd1, rd2, rd3;
    MaterialCardView Maid1,card1,card2;
    RadioGroup rdg;
    Button bs,next;
    TextView txtvw;
    ScrollView scrollView;
    EditText editTextnumplate,editTextcarName,editTextcity,editTextpostal,editTextnearby,editTextstreet,editTextsociety;
    private LinearLayout mBottomSheetLayout;
    private BottomSheetBehavior sheetBehavior;
    private ImageView header_Arrow_Image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maid_details);

        mBottomSheetLayout = findViewById(R.id.bottom_sheet_layout);
        sheetBehavior = BottomSheetBehavior.from(mBottomSheetLayout);
        header_Arrow_Image = findViewById(R.id.bottom_sheet_arrow);
        header_Arrow_Image.setOnClickListener(v -> {

            if(sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED){
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }

        });
        sheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

                header_Arrow_Image.setRotation(slideOffset * 180);
            }
        });
        Maid1 = findViewById(R.id.Maid1);
        card1 = findViewById(R.id.Maidcard1);
        Maid1.setOnClickListener(v -> {
            rd1.setText("1500₹ Broom,1-floor");
            rd2.setText("1500₹ Mop,1-floor");
            rd3.setText("2500₹ Broom,Mop,1-floor");
            card1.setVisibility(View.VISIBLE);
            Maid1.setChecked(true);
        });
        rd1 = findViewById(R.id.Maidrd1);
        rd2 = findViewById(R.id.Maidrd2);
        rd3 = findViewById(R.id.Maidrd3);
        rdg = findViewById(R.id.MaidRadiogroup);
        bs = findViewById(R.id.Maidbs);
        card2 = findViewById(R.id.Maidcard2);
        rdg.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
        });
        bs.setClickable(true);
        bs.setOnClickListener(v -> {
            int selectedId = rdg.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(MaidDetails.this, "Please Select Any One Option !",Toast.LENGTH_SHORT).show();
            } else {
                RadioButton radioButton = (RadioButton) rdg.findViewById(selectedId);
              /*  Toast.makeText(MaidDetails.this, radioButton.getText(), Toast.LENGTH_SHORT).show();*/
                txtvw.setText(radioButton.getText());
                card2.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
            }
        });
        scrollView = findViewById(R.id.MaidscrollView2);
        next = findViewById(R.id.MaidNext);
        editTextsociety = findViewById(R.id.MaidSociety);
        editTextstreet = findViewById(R.id.Maidstreet);
        editTextpostal = findViewById(R.id.Maidpostal);
        editTextnearby = findViewById(R.id.Maidnearby);
        editTextcity = findViewById(R.id.Maidcity);
        txtvw = findViewById(R.id.Maidtxtvw1);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Submit();
            }
            private void Submit() {
                String city = editTextcity.getText().toString().trim();
                String postal = editTextpostal.getText().toString().trim();
                String nearby = editTextnearby.getText().toString().trim();
                String street = editTextstreet.getText().toString().trim();
                String fullsociety = editTextsociety.getText().toString().trim();


                if(postal.isEmpty()){
                    editTextpostal.setError("Pincode is required!");
                    editTextpostal.requestFocus();
                    return;
                }

                if (postal.length() != 6){
                    editTextpostal.setError("Pincode should be 6!");
                    editTextpostal.requestFocus();
                    return;
                }

                if(city.isEmpty()){
                    editTextcity.setError("City is required!");
                    editTextcity.requestFocus();
                    return;
                }

                if(fullsociety.isEmpty()){
                    editTextsociety.setError("Society is required!");
                    editTextsociety.requestFocus();
                    return;
                }

                if(nearby.isEmpty()){
                    editTextnearby.setError("LandMark is required!");
                    editTextnearby.requestFocus();
                    return;
                }

                if(street.isEmpty()){
                    editTextstreet.setError("Street is required!");
                    editTextstreet.requestFocus();
                    return;
                }


                else {
                    Intent intent= new Intent(MaidDetails.this, MaidFinalPage.class);
                    startActivity(intent);
                }

                String society = editTextsociety.getText().toString();
                String Street = editTextstreet.getText().toString();
                String City = editTextcity.getText().toString();
                String Pincode = editTextpostal.getText().toString();
                String Landmark = editTextnearby.getText().toString();
                String Text = txtvw.getText().toString();

                Intent intent = new Intent(getApplicationContext() , MaidFinalPage.class);
                intent.putExtra("society", society+","+Landmark+","+Street+","+City+"-"+Pincode);
                intent.putExtra("Text","• "+Text);

                startActivity(intent);
            }
        });
    }
}


