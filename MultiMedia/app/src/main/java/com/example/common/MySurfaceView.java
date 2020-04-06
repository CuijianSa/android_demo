package com.example.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements Runnable, SurfaceHolder.Callback {

    private SurfaceHolder holder; //control surfaceview
    private Thread t;
    private Handler pollingHanler;
    private Boolean flag = false;
    private Canvas canvas;  //画布
    private Paint paint;    //画笔
    private int x = 50, y = 50, r = 100; //circle params
    private Object lockObject = new Object();

    public MySurfaceView(Context context) {
        super(context);

        holder = getHolder();
        holder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        setFocusable(true);
    }

    public void Draw(int x, int y, int radius) {
        canvas = holder.lockCanvas(); //get canvas object
        canvas.drawRGB(0, 0, 0);
        canvas.drawCircle(x, y, radius, paint);
        holder.unlockCanvasAndPost(canvas);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        t = new Thread(this);
        flag = true;
        t.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
    }

    @Override
    public void run() {
        while (flag) {

            synchronized (lockObject) {
                try {
                    lockObject.wait();
                    Draw(x, y, r);
                    Log.d("SurfaceView", "(" + x + "," + y + "," + "r" + ")");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            y--;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) event.getX();
        y = (int) event.getY();
        synchronized (lockObject) {
            try {
                lockObject.notifyAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
