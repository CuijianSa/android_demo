package com.example.multimedia;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.common.MySurfaceView;

public class SurfaceView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_surface_view);

        //hide status title
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hide activity title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.setContentView(new MySurfaceView(this));
    }
}
