package help.service.kaamwale.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import help.service.kaamwale.Login.MainActivity;
import help.service.kaamwale.R;
import help.service.kaamwale.Login.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatFragment extends Fragment {

    public ChatFragment() {

    }


    private FirebaseUser user;

    private DatabaseReference reference;

    private String userID;
    private Button logout;
    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_chat, container, false);


        sp = requireContext().getSharedPreferences("autoLogin", Context.MODE_PRIVATE);
        logout = root.findViewById(R.id.signIn);


        logout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("key", 0);
            editor.apply();
            customExitDialog();
        });
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView greetingTextView = root.findViewById(R.id.greeting);
        final TextView fullNameTextView = root.findViewById(R.id.fullName);
        final TextView emailTextView = root.findViewById(R.id.emailAddress);
        final TextView number = root.findViewById(R.id.number1);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String fullName = userProfile.fullName;
                    String email = userProfile.email;
                    String age = userProfile.number;

                    greetingTextView.setText("Welcome, " + fullName + "!");
                    fullNameTextView.setText(fullName);
                    emailTextView.setText(email);
                    number.setText(age);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });
        return root;
    }

    public void customExitDialog() {
        // creating custom dialog
        final Dialog dialog = new Dialog(getActivity());

        // setting content view to dialog
        dialog.setContentView(R.layout.custom_exit_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // getting reference of TextView
        TextView dialogButtonYes = (TextView) dialog.findViewById(R.id.textViewYes);
        TextView dialogButtonNo = (TextView) dialog.findViewById(R.id.textViewNo);

        // click listener for No
        dialogButtonNo.setOnClickListener(v -> {
            //dismiss the dialog
            dialog.dismiss();
        });
        // click listener for Yes
        dialogButtonYes.setOnClickListener(v -> {
            // dismiss the dialog
            // and exit the exit
            dialog.dismiss();
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getActivity(), MainActivity.class));
        });
        // show the exit dialog
        dialog.show();
    }

}