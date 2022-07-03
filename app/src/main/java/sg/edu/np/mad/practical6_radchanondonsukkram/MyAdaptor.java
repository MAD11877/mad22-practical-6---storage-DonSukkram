package sg.edu.np.mad.practical6_radchanondonsukkram;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdaptor extends RecyclerView.Adapter<MyViewHolder> {
    ArrayList<User> data;

    public MyAdaptor(ArrayList<User> input) {
        data = input;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout,parent,false);
        return new MyViewHolder(item);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        User u = data.get(position);
        holder.txtUserName.setText(u.getName());
        holder.txtUserDesc.setText(u.getDescription());
        holder.userIconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.userIconImage.getContext());
                builder.setMessage(u.getName()).setCancelable(true);
                builder.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // launch
                        Bundle extras = new Bundle();
                        Intent myIntent = new Intent(holder.userIconImage.getContext(), MainActivity.class);
                        extras.putString("New Name", u.getName());
                        extras.putString("New Desc", u.getDescription());
                        extras.putInt("New Id", u.getId());
                        extras.putBoolean("New Followed Status", u.isFollowed());
                        myIntent.putExtras(extras);

                        holder.userIconImage.getContext().startActivity(myIntent);
                    }
                });
                builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }});

                AlertDialog alert = builder.create();
                alert.setTitle("Profile");
                alert.show();
            }
        });
    }

    public int getItemCount(){
        return data.size();
    }
}
