package com.example.administrator.myapplication;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.Random;

/**
 * Created by Administrator on 2016/3/26.
 */
public class MusicItem implements IAnimation{

    private Paint mPaint = new Paint();

    private int maxHeight;
    private Random mRandom = new Random();
    private int musicColor;

    //矩形
    private int x;
    private int height;
    private int itemWidth;

    //多矩形
    private int maxRectNum;
    private int rectNum;
    private int distance = 3;

    //镜像
    private int centerY;
    private boolean randColor;


    private final int RECT = 1;//矩形
    private final int MRECT = 2;//多矩形
    private final int MMRECT = 3;//镜像
    private int state = MMRECT;

    public MusicItem(int height){
        this.maxHeight = height;
        mPaint.setColor(0xff0000ff);
    }

    public MusicItem(int x,int itemWidth,int height){
        this.maxHeight = height;
        this.x = x;
        this.itemWidth = itemWidth;
        init();
    }

    public MusicItem(int x,int itemWidth,int height,int musicColor){
        this.maxHeight = height;
        this.musicColor = musicColor;
        this.x = x;
        this.itemWidth = itemWidth;
        init();
    }

    public MusicItem(int x,int itemWidth,int height,int musicColor,boolean randColor){
        this.maxHeight = height;
        this.musicColor = musicColor;
        this.x = x;
        this.itemWidth = itemWidth;
        this.randColor = randColor;
        init();
    }

    private void init(){

        switch (state){
            case MRECT:
                maxRectNum = maxHeight/(itemWidth+distance);
                break;
            case MMRECT:
                centerY = maxHeight/2;
                maxRectNum = centerY/(itemWidth+distance);
                break;

        }
    }

    @Override public void draw(Canvas canvas) {


        switch (state){
            case RECT:
                canvas.drawRect(x,height,x + itemWidth,
                        maxHeight,
                        mPaint);
                break;
            case MRECT:
                int num = maxRectNum -rectNum;
                for (int i = 0;i<rectNum;i++){
                    canvas.drawRect(x,(num+i)*(itemWidth+distance),x + itemWidth,
                            (num+i+1)
                                    *(itemWidth+distance)-distance,
                            mPaint);
                }
                break;
            case MMRECT:
                mPaint.setAlpha(255);
                int num1 = maxRectNum -rectNum;
                for (int i = 0;i<rectNum;i++){
                    canvas.drawRect(x,(num1+i)*(itemWidth+distance),x +
                            itemWidth,
                            (num1+i+1)
                                    *(itemWidth+distance)-distance,
                            mPaint);
                }
                mPaint.setAlpha(60);
                for (int i = 0;i<rectNum;i++){
                    canvas.drawRect(x,(maxRectNum+i)*(itemWidth+distance),x +
                                    itemWidth,
                            (maxRectNum+i+1)
                                    *(itemWidth+distance)-distance,
                            mPaint);
                }

                break;
        }


    }


    @Override public void move() {
        if (randColor) {
            int r = mRandom.nextInt(256);
            int g = mRandom.nextInt(256);
            int b = mRandom.nextInt(256);
            mPaint.setARGB(255,r,g,b);
        }else {
            mPaint.setColor(musicColor);
        }
        switch (state){
            case RECT:
                maxHeight = mRandom.nextInt(maxHeight);
                break;
            case MRECT:
            case MMRECT:
                rectNum = 1+mRandom.nextInt(maxRectNum);
                break;

        }



    }
}
