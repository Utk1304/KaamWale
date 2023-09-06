package help.service.kaamwale.Plumbingservice;

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

public class PlumbingDetails extends AppCompatActivity {

    RadioButton rd1, rd2, rd3;
    MaterialCardView plumb1,card1,card2;
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
        setContentView(R.layout.activity_plumbing_details);

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
        plumb1 = findViewById(R.id.plumb1);
        card1 = findViewById(R.id.plumbingcard1);
        plumb1.setOnClickListener(v -> {
            card1.setVisibility(View.VISIBLE);
            plumb1.setChecked(true);
        });
        rd1 = findViewById(R.id.plumbingrd1);
        rd2 = findViewById(R.id.plumbingrd2);
        rdg = findViewById(R.id.plumbingRadiogroup);
        bs = findViewById(R.id.plumbingbs);
        card2 = findViewById(R.id.plumbingcard2);
        rdg.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
        });
        bs.setClickable(true);
        bs.setOnClickListener(v -> {
            int selectedId = rdg.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(PlumbingDetails.this, "Please Select Any One Option !",Toast.LENGTH_SHORT).show();
            } else {
                RadioButton radioButton = (RadioButton) rdg.findViewById(selectedId);
             /*   Toast.makeText(PlumbingDetails.this, radioButton.getText(), Toast.LENGTH_SHORT).show();*/
                txtvw.setText(radioButton.getText());
                card2.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
            }
        });
        scrollView = findViewById(R.id.plumbingscrollView2);
        next = findViewById(R.id.plumbingNext);
        editTextsociety = findViewById(R.id.plumbingSociety);
        editTextstreet = findViewById(R.id.plumbingstreet);
        editTextpostal = findViewById(R.id.plumbingpostal);
        editTextnearby = findViewById(R.id.plumbingnearby);
        editTextcity = findViewById(R.id.plumbingcity);
        txtvw = findViewById(R.id.plumbingtxtvw1);
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
                    Intent intent= new Intent(PlumbingDetails.this, PlumbingFinalPage.class);
                    startActivity(intent);
                }

                String society = editTextsociety.getText().toString();
                String Street = editTextstreet.getText().toString();
                String City = editTextcity.getText().toString();
                String Pincode = editTextpostal.getText().toString();
                String Landmark = editTextnearby.getText().toString();
                String Text = txtvw.getText().toString();

                Intent intent = new Intent(getApplicationContext() , PlumbingFinalPage.class);
                intent.putExtra("society", society+","+Landmark+","+Street+","+City+"-"+Pincode);
                intent.putExtra("Text","• "+Text);

                startActivity(intent);
            }
        });
    }
}