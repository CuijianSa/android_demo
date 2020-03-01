package com.example.firstcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "you click button1",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);//显示Intent
                Intent intent = new Intent("com.example.activity.ACTION_START");
                intent.addCategory("com.example.activity.MY_CATEGORY");
//                startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });

        Button destory_button = (Button) findViewById(R.id.destory_activity);
        destory_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "destory",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        Button baidu_button = (Button) findViewById(R.id.Baidu);
        baidu_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if(resultCode == RESULT_OK) {
                    String resultData = data.getStringExtra("data_return");

                    try {
                        Log.d("FirstActivity", resultData);
                        Toast.makeText(MainActivity.this, "FirstActivity" + resultData, Toast.LENGTH_SHORT).show();
                    }catch(Exception e) {
                        Log.d("FirstActivity", e.toString());
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item: {
                Toast.makeText(this, "clicked add", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.remove_item: {
                Toast.makeText(this, "clicked remvoe", Toast.LENGTH_SHORT).show();
                break;
            }
            default: {
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
