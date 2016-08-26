package com.example.gabekeyner.myfinaltodolist;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    //these are where user pins will go

    TextView textView;
    //Title on the app "My To Do Pins"

    ArrayList<String> mArrray = new ArrayList<>();
    //This is where all the user pins will be stored in an Arraylist

    ImageButton ib;

    ArrayAdapter<String> arrayAdapter;
    //grabs the strings from my array list and puts them into my List view "Pinsview"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    listView=(ListView) findViewById(R.id.pins_views);

    //declares that pins view is a List View

    textView=(TextView)

    findViewById(R.id.mylistbutton);


    //declares that my list button is a clickable TextView

    arrayAdapter=new ArrayAdapter<String>(
            this,
    android.R.layout.simple_list_item_1,
    mArrray
    );
    //this calls the array adapter to take from the first simple list item


    listView.setAdapter(arrayAdapter);
    //this sets the adapter in the listview


    ib=(ImageButton)

    findViewById(R.id.addTheList);
    //creating roatationanimation object

    RotateAnimation anim = new RotateAnimation(0f, 350f, 15f, 15f);

    anim.setInterpolator(new LinearInterpolator());
    anim.setRepeatCount(Animation.ABSOLUTE);
    anim.setDuration(800);

    ib.startAnimation(anim);
    ib.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View view){
        addThePin(view);
    }
    }

    );


    listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

    {
        @Override
        public void onItemClick (AdapterView < ? > adapterView, View view,int i, long l){
        Toast.makeText(MainActivity.this, "Pinned!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, NewPinsActivity.class);

        intent.putExtra("Title", mArrray.get(i));
        startActivity(intent);
    }
    }

    );
    listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()

    {
        @Override
        public boolean onItemLongClick (AdapterView < ? > adapterView, View view,int i, long l){
        mArrray.remove(i);
        arrayAdapter.notifyDataSetChanged();
        Toast.makeText(MainActivity.this, "Binned!", Toast.LENGTH_SHORT).show();
        return false;

    }
    }

    );

}

    public void addThePin(View V) {
        //method when the blue pin is tapped on

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // brings up my dialog box to enter user input

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        //declares what kind of input the the user can input,
        //uses TYPE CLASS TEXT


        builder.setView(input);

        builder.setMessage("Name New Pin Here")//this gives a title to the pop up dialog box
                .setPositiveButton("Pin It", new DialogInterface.OnClickListener() {
                    //Sets a button on the pop up dialog box to positive and the name "Pin it"

                    public void onClick(DialogInterface dialog, int id) {

                        mArrray.add(input.getText().toString());    //takes the user the input and adds it to the string in the array list
                        arrayAdapter.notifyDataSetChanged();        //notifys that the user inputed has been changed


                    }
                })
                .setNegativeButton("Bin it", new DialogInterface.OnClickListener() {
                    //Sets a button on the pop up dialog box to negative and the name "Bin it"

                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MainActivity.this, "Binned!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                        //tells the dialog box basically nevermind
                    }
                });
        Dialog dialog = builder.create();
        dialog.show();
        // builds the dialog box and shows it on the screeen
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {       //This method keeps data the same when switched to landscape or portrait mode
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(getApplicationContext(), "Portrait Mode", Toast.LENGTH_SHORT).show();

        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(getApplicationContext(), "Landscape mode", Toast.LENGTH_SHORT).show();
        }
    }
}

