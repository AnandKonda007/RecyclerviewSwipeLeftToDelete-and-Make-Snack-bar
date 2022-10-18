package com.example.recyclerviewwithswipelefttodeleteandlongpresstodelete;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MainActivity extends AppCompatActivity {
    ArrayList<contact_model> arrContacts = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerContactAdapter adapter;
    FloatingActionButton addbutton;
    contact_model deleted = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrContacts.add(new contact_model(R.drawable.img1, "Santosh", "8179629482"));
        arrContacts.add(new contact_model(R.drawable.img10, "Sharan", "9848424711 "));
        arrContacts.add(new contact_model(R.drawable.img3, "Apporav", "9542271872"));
        arrContacts.add(new contact_model(R.drawable.img4, "srimanth", "9908633308 "));
        arrContacts.add(new contact_model(R.drawable.img5, "Shireesha", "9381241894 "));
        arrContacts.add(new contact_model(R.drawable.img6, "Raju", "9885991661"));
        arrContacts.add(new contact_model(R.drawable.img7, "Ramya", "7601092810"));
        arrContacts.add(new contact_model(R.drawable.img8, "Jashwanth", "9010930145 "));
        arrContacts.add(new contact_model(R.drawable.img9, "Balu", "8688398540"));
        arrContacts.add(new contact_model(R.drawable.img10, "Rohith", "8341291824"));
        arrContacts.add(new contact_model(R.drawable.img2, "Parimala", "9392836867"));
        arrContacts.add(new contact_model(R.drawable.img5, "Niharika", "8332981905"));
        arrContacts.add(new contact_model(R.drawable.img33, "Uha", "9502765780 "));
        arrContacts.add(new contact_model(R.drawable.img7, "Meghana", " 9160127266"));
        arrContacts.add(new contact_model(R.drawable.img1, "Fazil", "7396261841"));
        arrContacts.add(new contact_model(R.drawable.img3, "Gouse", "9390878119 "));
        arrContacts.add(new contact_model(R.drawable.img4, "Mohan", "810602243"));
        arrContacts.add(new contact_model(R.drawable.img3, "ismail", "8977504767"));
        arrContacts.add(new contact_model(R.drawable.img6, "Vinay", "9848228881"));
        arrContacts.add(new contact_model(R.drawable.img8, "Srimanth", "9908633308"));
        arrContacts.add(new contact_model(R.drawable.img9, "Jagadeesh", "7731067870"));
        arrContacts.add(new contact_model(R.drawable.img10, "Saleem", "6300659739"));

        adapter = new RecyclerContactAdapter(this, arrContacts);
        recyclerView.setAdapter(adapter);
        buttonActions();

    }

    private void buttonActions() {
        addbutton = findViewById(R.id.add);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);



                dialog.setContentView(R.layout.add_contact);
                EditText edname = dialog.findViewById(R.id.name);
                EditText ednumber = dialog.findViewById(R.id.contact);
                Button button = dialog.findViewById(R.id.addcontact);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "", number = "";
                        name = edname.getText().toString();
                        number = ednumber.getText().toString();
                       /*  if (name.isEmpty()) {
                            Toast.makeText(MainActivity.this, "please enter contact name", Toast.LENGTH_SHORT).show();
                        } else if (number.isEmpty()) {
                            Toast.makeText(MainActivity.this, "please enter contact number", Toast.LENGTH_SHORT).show();
                        }*/
                        if (edname.getText().toString().length() == 0) {
                            edname.setError("please enter your name");
                            Toast.makeText(MainActivity.this, "please enter contact name", Toast.LENGTH_SHORT).show();

                        } else if (ednumber.getText().toString().length() == 0) {
                            ednumber.setError("please enter your mobile number");
                            Toast.makeText(MainActivity.this, "please enter contact number", Toast.LENGTH_SHORT).show();

                        }
                        //In Logcat we see error message
                        //else if(name.isEmpty()&& number.isEmpty()) {

                        //  Log.e("TAG","Edit 1 or Edit 2 may be empty. Please add values");
                        // }





                        else {

                            arrContacts.add(new contact_model(R.drawable.img, name, number));
                            adapter.notifyItemInserted(arrContacts.size() - 1);
                            recyclerView.scrollToPosition(arrContacts.size() - 1);
                            dialog.dismiss();

                        }
                    }
                });

                dialog.show();
            }

        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);

        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    //for Left and Right Swipe actions
    //ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction) {
                //if we want to set both swipe actions same we use code below.
               /* case ItemTouchHelper.LEFT:
                  case ItemTouchHelper.RIGHT:
                    deleted = arrContacts.get(position);
                    arrContacts.remove(position);
                    adapter.notifyItemRemoved(position);
                    Snackbar snackbar = Snackbar
                            .make(recyclerView, "deleted", Snackbar.LENGTH_LONG)
                            .setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    arrContacts.add(position, deleted);
                                    adapter.notifyItemInserted(position);
                                }
                            });
                    snackbar.show();

                    break; */
                case ItemTouchHelper.LEFT:
                    deleted = arrContacts.get(position);
                    arrContacts.remove(position);
                    adapter.notifyItemRemoved(position);
                    Snackbar snackbar = Snackbar
                            .make(recyclerView, "deleted", Snackbar.LENGTH_LONG)
                            .setAction("UNDO", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    arrContacts.add(position, deleted);
                                    adapter.notifyItemInserted(position);
                                }
                            });
                    snackbar.show();

                    break;
                case ItemTouchHelper.RIGHT:
                    break;

            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    /*.addSwipeRightBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.red))
                    .addSwipeRightActionIcon(R.drawable.ic_baseline_delete_24)*/
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };
}



