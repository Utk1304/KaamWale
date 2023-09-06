package help.service.kaamwale.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import help.service.kaamwale.Acservice.AcService;
import help.service.kaamwale.Carwash.CarWash;
import help.service.kaamwale.Maidservice.MaidService;
import help.service.kaamwale.Plumbingservice.PlumbingService;
import help.service.kaamwale.R;
import help.service.kaamwale.adapter.ViewPagerAdapter;

import java.util.List;
import java.util.Locale;


public class HomeFragment extends Fragment implements LocationListener{
    TextView addresshome;
    ImageView getloc;
    LocationManager locationManager;
    ViewPager mSLideViewPager;
    LinearLayout mDotLayout;
    TextView[] dots;
    ViewPagerAdapter viewPagerAdapter;
    CardView carwash,Maid,AC,Plumbing;

    public HomeFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        addresshome=root.findViewById(R.id.addresshome);
        getloc=root.findViewById(R.id.getloc);
        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }
            getLocation();


        mSLideViewPager = root.findViewById(R.id.slideViewPager);
        mDotLayout = root.findViewById(R.id.indicator_layout);

        viewPagerAdapter = new ViewPagerAdapter(getActivity());

        mSLideViewPager.setAdapter(viewPagerAdapter);
        setUpindicator(0);
        mSLideViewPager.addOnPageChangeListener(viewListener);

        carwash=root.findViewById(R.id.carwash);
        carwash.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), CarWash.class));
        });

        Maid = root.findViewById(R.id.Maid);
        Maid.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), MaidService.class));
        });

        AC = root.findViewById(R.id.AC);
        AC.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AcService.class));

        });

        Plumbing = root.findViewById(R.id.Plumbing);
        Plumbing.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), PlumbingService.class));
        });
        return root;
    }
    @SuppressLint("MissingPermission")
    private void getLocation(){
        try {
            locationManager = (LocationManager) getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, HomeFragment.this);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        try {
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address = addresses.get(0).getAddressLine(0);

            addresshome.setText(address);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }


    public void setUpindicator(int position){

        dots = new TextView[2];
        mDotLayout.removeAllViews();

        for (int i = 0 ; i < dots.length ; i++){

            dots[i] = new TextView(getActivity());
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getActivity().getTheme()));
            mDotLayout.addView(dots[i]);

        }

        dots[position].setTextColor(getResources().getColor(R.color.active,getActivity().getTheme()));

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i){

        return mSLideViewPager.getCurrentItem() + i;
    }

}
