package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/26.
 */
public abstract class BaseView extends View{

    private MyThread mThread;
    protected long sleepTime = 300;


    public BaseView(Context context) {
        super(context);
    }


    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected abstract void init();
    protected abstract void drawSub(Canvas canvas);
    protected abstract void logic();


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mThread == null){
            mThread = new MyThread();
            mThread.start();
        }else {
            drawSub(canvas);
        }
    }


    private boolean running = true;
    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        running = false;
    }

    class MyThread extends Thread{

        @Override public void run() {
            super.run();
            init();
            long workTime;
            while (running)
            {
                workTime = System.currentTimeMillis();
                postInvalidate();
                logic();
                workTime = System.currentTimeMillis() - workTime;
            try {
                if (workTime<sleepTime) {
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }
    }
}
