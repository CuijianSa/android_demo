package com.example.uicontrol;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button progressBarAddButton;
    private Button alertDialogButton;
    private EditText edit;
    private ImageView imageView;
    private ProgressBar progressBar;

    private int click_num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        progressBarAddButton = (Button) findViewById(R.id.progress_bar_add);
        alertDialogButton = (Button) findViewById(R.id.button_alert_dialog);
        edit = (EditText) findViewById(R.id.edit_text);
        imageView = (ImageView) findViewById(R.id.image_view);
        progressBar = (ProgressBar) findViewById(R.id.process_bar);

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
    }


}
