package com.example.recyclerviewwithswipelefttodeleteandlongpresstodelete;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder>{
    Context context;
    ArrayList<contact_model>arrContacts;
    RecyclerContactAdapter(Context context, ArrayList<contact_model>arrContacts){
        this.context=context;
        this.arrContacts=arrContacts;

    }



    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v =LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, @SuppressLint("RecyclerView") int position){
        holder.textname.setText(arrContacts.get(position).name);
        holder.textnumber.setText(arrContacts.get(position).number);
        holder.contactImage.setImageResource(arrContacts.get(position).img);

        holder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.add_contact);
                EditText edname=dialog.findViewById(R.id.name);
                EditText ednumber=dialog.findViewById(R.id.contact);
                Button button=dialog.findViewById(R.id.addcontact);
                TextView txttitle=dialog.findViewById(R.id.title);
                txttitle.setText("update Contact");
                button.setText("update");
                edname.setText(arrContacts.get(position).name);
                ednumber.setText(arrContacts.get(position).number);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name="",number="";
                        name = edname.getText().toString();
                        number = ednumber.getText().toString();
                       /* //if(edname.getText().toString().isEmpty()){
                        if(!edname.getText().toString().equals("")){
                            name=edname.getText().toString();
                        }else{
                            Toast.makeText(context, "please enter contact name", Toast.LENGTH_SHORT).show();
                        }
                        //if(ednumber.getText().toString().isEmpty()){
                        if(!ednumber.getText().toString().equals("")){
                            number=ednumber.getText().toString();
                        }else{
                            Toast.makeText(context, "please Enter mobile number", Toast.LENGTH_SHORT).show();
                        }*/
                        if (edname.getText().toString().length()==0) {
                            edname.setError("please enter your name");
                            Toast.makeText(context.getApplicationContext(), "please enter contact name", Toast.LENGTH_SHORT).show();

                        }else if(ednumber.getText().toString().length()==0){
                            ednumber.setError("please enter your mobile number");
                            Toast.makeText(context.getApplicationContext(), "please enter contact number", Toast.LENGTH_SHORT).show();

                        }
                        else {

                            arrContacts.set(position, new contact_model(arrContacts.get(position).img, name, number));
                            notifyItemChanged(position);
                            dialog.dismiss();
                        }

                    }
                });
                dialog.show();
            }
        });
        holder.row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context)
                        .setTitle("delete Contact")
                        .setMessage("Are you sure want to delete contact")
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrContacts.remove(position);
                                notifyItemRemoved(position);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        if(arrContacts.size()>0){
            return arrContacts.size();
        }else {
            return 0;
        }
        //return arrContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textname,textnumber;
        ImageView contactImage;
        LinearLayout row;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactImage= itemView.findViewById(R.id.image);
            textname=itemView.findViewById(R.id.name);
            textnumber=itemView.findViewById(R.id.number);
            row=itemView.findViewById(R.id.row);

        }
    }
}
