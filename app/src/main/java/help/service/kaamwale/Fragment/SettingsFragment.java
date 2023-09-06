package help.service.kaamwale.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import help.service.kaamwale.BuildConfig;
import help.service.kaamwale.Login.User;
import help.service.kaamwale.PrivacyPolicy;



import help.service.kaamwale.R;
import help.service.kaamwale.Settings.ViewOrders;



public class SettingsFragment extends Fragment {
    Button privacy,vieworder,share,help,use;
    private FirebaseUser user;
    private String userID;
    private DatabaseReference reference;
    String fullName;
    public SettingsFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    fullName = userProfile.fullName;

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });

        privacy = root.findViewById(R.id.Privacy);
        vieworder = root.findViewById(R.id.vieworder);

        privacy.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PrivacyPolicy.class);
            startActivity(intent);
        });

        vieworder.setOnClickListener(v -> {
            Intent intent1 = new Intent(getActivity(), ViewOrders.class);
            startActivity(intent1);
        });
        share = root.findViewById(R.id.share);
        share.setOnClickListener(v -> {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String shareMessage= "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }
        });
        help = root.findViewById(R.id.help);
        help.setOnClickListener(v -> {

            String phoneNumberWithCountryCode = "+918980232138";
            String message = "Hello ! My Name Is " + fullName + ", I Want Some Help About Application !";

            startActivity(
                    new Intent(Intent.ACTION_VIEW,
                            Uri.parse(
                                    String.format("https://api.whatsapp.com/send?phone=%s&text=%s", phoneNumberWithCountryCode, message)
                            )
                    )
            );
        });
        
        use = root.findViewById(R.id.use);
        use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(
                        new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://kaamwale-3d3ec.web.app/")
                        )
                );
            }
        });

        return root;
    }

}
