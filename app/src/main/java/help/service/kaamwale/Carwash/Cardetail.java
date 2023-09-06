package help.service.kaamwale.Carwash;
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


public class Cardetail extends AppCompatActivity {

    MaterialCardView hatch1, sedan1, compact1, suv1, linearLayout,linearLayout2;
    RadioButton rd1, rd2, rd3;
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
        setContentView(R.layout.activity_cardetail);

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

        hatch1 = findViewById(R.id.hatch1);
        sedan1 = findViewById(R.id.sedan1);
        compact1 = findViewById(R.id.compact1);
        suv1 = findViewById(R.id.suv1);
        hatch1.setOnClickListener(v -> {
            rd1.setText("1 Month Service In 550 rs");
            rd2.setText("3 Month Service In 1650 rs");
            rd3.setText("6 Month Service In 3200 rs");
            linearLayout.setVisibility(View.VISIBLE);
            hatch1.setChecked(true);
            sedan1.setChecked(false);
            compact1.setChecked(false);
            suv1.setChecked(false);
        });

        sedan1.setOnClickListener(v -> {
            rd1.setText("1 Month Service In 600 rs");
            rd2.setText("3 Month Service In 1800 rs");
            rd3.setText("6 Month Service In 3500 rs");
            linearLayout.setVisibility(View.VISIBLE);
            hatch1.setChecked(false);
            sedan1.setChecked(true);
            compact1.setChecked(false);
            suv1.setChecked(false);
        });
        compact1.setOnClickListener(v -> {
            rd1.setText("1 Month Service In 650 rs");
            rd2.setText("3 Month Service In 1950 rs");
            rd3.setText("6 Month Service In 3800 rs");
            linearLayout.setVisibility(View.VISIBLE);
            hatch1.setChecked(false);
            sedan1.setChecked(false);
            compact1.setChecked(true);
            suv1.setChecked(false);
        });
        suv1.setOnClickListener(v -> {
            rd1.setText("1 Month Service In 700 rs");
            rd2.setText("3 Month Service In 2100 rs");
            rd3.setText("6 Month Service In 4100 rs");
            linearLayout.setVisibility(View.VISIBLE);
            hatch1.setChecked(false);
            sedan1.setChecked(false);
            compact1.setChecked(false);
            suv1.setChecked(true);
        });
        rd1 = findViewById(R.id.carrd1);
        rd2 = findViewById(R.id.carrd21);
        rd3 = findViewById(R.id.carrd3);
        rdg = findViewById(R.id.carRadiogroup1);
        bs = findViewById(R.id.carbs2);
        linearLayout = findViewById(R.id.linear3);
        rdg.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
        });
        bs.setClickable(true);
        bs.setOnClickListener(v -> {
            int selectedId = rdg.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(Cardetail.this, "Please Select Any One Option !",Toast.LENGTH_SHORT).show();
            } else {
                RadioButton radioButton = (RadioButton) rdg.findViewById(selectedId);
             /*   Toast.makeText(Cardetail.this, radioButton.getText(), Toast.LENGTH_SHORT).show();*/
                txtvw.setText(radioButton.getText());
                linearLayout2.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
            }
        });

        scrollView = findViewById(R.id.carscrollView2);
        linearLayout2 = findViewById(R.id.linear4);
        next = findViewById(R.id.carNext);
        editTextsociety = findViewById(R.id.carSociety);
        editTextstreet = findViewById(R.id.carstreet);
        editTextpostal = findViewById(R.id.carpostal);
        editTextnearby = findViewById(R.id.carnearby);
        editTextcity = findViewById(R.id.carcity);
        editTextcarName = findViewById(R.id.carName);
        editTextnumplate = findViewById(R.id.numplate);
        txtvw = findViewById(R.id.cartxtvw1);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Submit();
            }
            private void Submit() {
                String numplate = editTextnumplate.getText().toString().trim();
                String carName = editTextcarName.getText().toString().trim();
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

                if(carName.isEmpty()){
                    editTextcarName.setError("Car Name is required!");
                    editTextcarName.requestFocus();
                    return;
                }

                if(numplate.isEmpty()){
                    editTextnumplate.setError("Car Number is required!");
                    editTextnumplate.requestFocus();
                    return;
                }

                else {
                    Intent intent= new Intent(Cardetail.this, CarFinalPage.class);
                    startActivity(intent);
                }

                String CarName = editTextcarName.getText().toString();
                String CarNum = editTextnumplate.getText().toString();
                String society = editTextsociety.getText().toString();
                String Street = editTextstreet.getText().toString();
                String City = editTextcity.getText().toString();
                String Pincode = editTextpostal.getText().toString();
                String Landmark = editTextnearby.getText().toString();
                String Text = txtvw.getText().toString();

                Intent intent = new Intent(getApplicationContext() , CarFinalPage.class);
                intent.putExtra("carname", CarName);
                intent.putExtra("carnum",CarNum);
                intent.putExtra("society", society+","+Landmark+","+Street+","+City+"-"+Pincode);
                intent.putExtra("Text","• "+Text);

                if (hatch1.isChecked()){
                    intent.putExtra("image",R.drawable.hatch);
                }
                if (sedan1.isChecked()){
                    intent.putExtra("image",R.drawable.sedan);
                }
                if (compact1.isChecked()){
                    intent.putExtra("image",R.drawable.compactsuv);
                }
                if (suv1.isChecked()){
                    intent.putExtra("image",R.drawable.suv);
                }

                startActivity(intent);
            }
        });
    }
}


