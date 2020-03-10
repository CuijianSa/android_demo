package com.example.uicontrol;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fruit.Fruit;
import com.example.fruit.FruitAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button progressBarAddButton;
    private Button alertDialogButton;
    private Button linearLayoutButton;
    private Button relativeLayoutButton;
    private Button frameLayoutButton;
    private EditText edit;
    private ImageView imageView;
    private ProgressBar progressBar;

    private int click_num = 0;

    private  String[] data = {"Apple", "Banana", "Orange", "Watermelon",
    "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango",
        "Pineapple", "Straberry"};

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        progressBarAddButton = (Button) findViewById(R.id.progress_bar_add);
        alertDialogButton = (Button) findViewById(R.id.button_alert_dialog);
        linearLayoutButton = (Button) findViewById(R.id.linearLayout);
        relativeLayoutButton = (Button) findViewById(R.id.relativeLayout);
        frameLayoutButton = (Button) findViewById(R.id.frameLayout);
        edit = (EditText) findViewById(R.id.edit_text);
        imageView = (ImageView) findViewById(R.id.image_view);
        progressBar = (ProgressBar) findViewById(R.id.process_bar);

        initFruits();

        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button:{
                        String inputText = edit.getText().toString();
                        Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();

                        if(++click_num % 2 == 0) {
                            imageView.setImageResource(R.drawable.shaiya101);
                        }else {
                            imageView.setImageResource(R.drawable.shaiya102);
                        }

                        if(progressBar.getVisibility() == View.GONE) {
                            progressBar.setVisibility(View.VISIBLE);
                        }else {
                            progressBar.setVisibility(View.GONE);
                        }

                    }break;
                    default:
                        break;
                }
            }
        });
        progressBarAddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.progress_bar_add: {
                        int progress = progressBar.getProgress();
                        progress += 10;
                        progressBar.setProgress(progress);
                    }break;
                    default:
                        break;
                }
            }
        });

        alertDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_alert_dialog: {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("This is Dialog");
                        dialog.setMessage("something important.");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog.show();
                    }break;
                    default:
                        break;
                }
            }
        });

        linearLayoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.activity.LinearLayout");
                startActivity(intent);
            }
        });

        relativeLayoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.activity.RelativeLayout");
                startActivity(intent);
            }
        });

        frameLayoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.activity.FrameLayout");
                startActivity(intent);
            }
        });

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    private void initFruits() {
        for(int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry", R.drawable.banana_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.drawable.banana_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit("Mango", R.drawable.banana_pic);
            fruitList.add(mango);
        }
    }
}
