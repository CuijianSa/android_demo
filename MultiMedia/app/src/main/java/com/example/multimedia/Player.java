package com.example.multimedia;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Player extends AppCompatActivity implements View.OnClickListener {
    static final int REQUEST_FILE = 0;
    static final String TAG = "Player";

    private Button play;
    private Button pause;
    private Button stop;
    private Button file;
    private TextView filePathTextView;
    private VideoView videoView;
//    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);
        file = findViewById(R.id.file);
        filePathTextView = findViewById(R.id.file_path);
        videoView = findViewById(R.id.video_view);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                <4.4 URI:content://media/external/images/media/164 含有文件的绝对路径
//                >4.4URI ：content://com.android.providers.media.documents/document/image:3951，只有文件的相对编号
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("video/*");
                startActivityForResult(intent, REQUEST_FILE);
            }
        });

        if (ContextCompat.checkSelfPermission(Player.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Player.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_FILE);
        }
    }

    public void initMediaPlayer(String uri) {
        File file = new File(uri);
        videoView.setVideoPath(file.getPath());
//        try {
//            File file = new File(uri);
//            videoView.setVideoPath(file.getPath());
//            mediaPlayer.setDataSource(file.getPath());
//            mediaPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
//                if (!mediaPlayer.isPlaying()) {
//                    mediaPlayer.start();
//                }

                break;
            case R.id.pause:
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
//                if (mediaPlayer.isPlaying()) {
//                    mediaPlayer.pause();
//                }
                break;
            case R.id.stop:
                if (videoView.isPlaying()) {
                    videoView.stopPlayback();
                    initMediaPlayer(filePathTextView.getText().toString());
                }
//                if (mediaPlayer.isPlaying()) {
//                    mediaPlayer.reset();
//                    initMediaPlayer(filePathTextView.getText().toString());
//                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();
        }
//        if (mediaPlayer != null) {
//            mediaPlayer.stop();
//            mediaPlayer.release();
//        }
    }

    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_FILE: {
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    Log.d(TAG, "File Uri: " + uri.toString());
                    String path = null;
                    try {
                        path = getPath(this, uri);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "File Path: " + path);
                    filePathTextView.setText(path);

                    initMediaPlayer(path);
                }
            }
            break;
        }
    }
}
