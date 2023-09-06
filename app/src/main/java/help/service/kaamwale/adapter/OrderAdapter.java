package help.service.kaamwale.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import help.service.kaamwale.Carwash.Caruserdetails;
import help.service.kaamwale.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    Context context;
    Caruserdetails caruserdetails;
    FirebaseUser user;
    DatabaseReference reference;
    ArrayList<Caruserdetails> list;


    public OrderAdapter(Context context, ArrayList<Caruserdetails> list) {
        this.context = context;
        this.list = list;
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.orderdetails,parent,false);
        return new OrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        reference = FirebaseDatabase.getInstance().getReference("Car_Details");

        Caruserdetails caruserdetails = list.get(position);
        holder.Type.setText(caruserdetails.getcarName());
        holder.Service.setText(caruserdetails.getduration());
        holder.Address.setText(caruserdetails.getaddress());
        holder.delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog (context);
                dialog.setContentView(R.layout.deletedialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                // getting reference of TextView
                TextView dialogButtonYes = (TextView) dialog.findViewById(R.id.textViewYes);
                TextView dialogButtonNo = (TextView) dialog.findViewById(R.id.textViewNo);

                // click listener for No
                dialogButtonNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                //dismiss the dialog


                // click listener for Yes
                dialogButtonYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delete(caruserdetails.getId());
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    private void delete(String id) {
        DatabaseReference dbref= FirebaseDatabase.getInstance().getReference("Car_Details");
        Query query=dbref.child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{

        TextView Type,Service,Address;
        ImageView delete1;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            Type = itemView.findViewById(R.id.ordertype);
            Service = itemView.findViewById(R.id.orderservice);
            Address = itemView.findViewById(R.id.orderadress);
            delete1 = itemView.findViewById(R.id.orderdelete1);

        }
    }

}
