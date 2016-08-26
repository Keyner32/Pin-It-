package com.example.gabekeyner.myfinaltodolist;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewPinsActivity extends AppCompatActivity {
    ListView listView;

    TextView textView;

    ArrayList<String> mArrayItem = new ArrayList<>();

    ImageButton ib2;

    ArrayAdapter<String> arrayAdapteritem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pins);


        textView = (TextView) findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.listIItems);
        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");
        textView.setText(title);

        arrayAdapteritem = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                mArrayItem
        );

        listView.setAdapter(arrayAdapteritem);

        ib2 = (ImageButton) findViewById(R.id.addTheList);
        //creating roatation animation object

        ScaleAnimation anim = new ScaleAnimation(0f, 0f, 5f, 5f);

        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.ABSOLUTE);
        anim.setDuration(800);

        ib2.startAnimation(anim);
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addThePin(view);
            }
        });
    }

    public void addThePin(View V) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);


        builder.setView(input);

        builder.setMessage("Describe Pin Here ")
                .setPositiveButton("Pin Changes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(NewPinsActivity.this, "Pinned Changes!", Toast.LENGTH_SHORT).show();
                        mArrayItem.add(input.getText().toString());
                        arrayAdapteritem.notifyDataSetChanged();

                    }
                })
                .setNegativeButton("Bin Changes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(NewPinsActivity.this, "Binned Changes!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });
        Dialog dialog = builder.create();
        dialog.show();
    }

}


