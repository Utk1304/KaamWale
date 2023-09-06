package help.service.kaamwale.Settings;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import help.service.kaamwale.Carwash.Caruserdetails;
import help.service.kaamwale.R;
import help.service.kaamwale.adapter.OrderAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ViewOrders extends AppCompatActivity {


    RecyclerView recyclerView;
    OrderAdapter orderAdapter;
    FirebaseUser user;
    FirebaseUser user1;
    DatabaseReference databaseReference,reference;
    String userID;

    ArrayList<Caruserdetails> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        recyclerView = findViewById(R.id.recycleview);
        databaseReference = FirebaseDatabase.getInstance().getReference("Car_Details");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        orderAdapter = new OrderAdapter(this,list);
        recyclerView.setAdapter(orderAdapter);

        reference = FirebaseDatabase.getInstance().getReference("Users");
        user = FirebaseAuth.getInstance().getCurrentUser();

        String email = user.getEmail().toString();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Caruserdetails caruserdetails = dataSnapshot.getValue(Caruserdetails.class);
                    caruserdetails.setId(dataSnapshot.getKey());
                    list.add(caruserdetails);
                }
                List<Caruserdetails> data =  list.stream().filter(c -> c.getEmail().equalsIgnoreCase(String.valueOf(email))).collect(Collectors.toList());
                list.clear();
                list.addAll(data);
                orderAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*FirebaseDatabase.getInstance().getReference().child("Users").child(post.getPublisher()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                if (user.getImageurl().equals("default")) {
                    holder.imageProfile.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Picasso.get().load(user.getImageurl()).placeholder(R.mipmap.ic_launcher).into(holder.imageProfile);
                }
                holder.username.setText(user.getUsername());
                holder.author.setText(user.getName());
            }*/
    }
}