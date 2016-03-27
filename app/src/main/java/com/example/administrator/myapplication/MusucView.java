package com.example.administrator.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/26.
 */
public class MusucView extends BaseView{

    //private MusicItem mMusicItem;
    private List<MusicItem> mMusicItemList = new ArrayList<>();
    private int itemNum = 20;
    private int itemWidth = 20;
    private int musicColor;
    private boolean randColor;


    public MusucView(Context context) {
        super(context);
    }


    public MusucView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable
                .MusicView);
        itemNum = ta.getInteger(R.styleable.MusicView_itemNum,20);
        itemWidth = ta.getDimensionPixelSize(R.styleable.MusicView_itemWidth,
                20);
        musicColor = ta.getColor(R.styleable.MusicView_musicColor,0xff0000ff);
        randColor = ta.getBoolean(R.styleable.MusicView_randColor,false);
        ta.recycle();
    }


    @Override protected void init() {

        //mMusicItem = new MusicItem(getHeight());
        sleepTime = 150;
        for (int i = 0;i<itemNum;i++){
            mMusicItemList.add(new MusicItem(i*getWidth()/itemNum,itemWidth,
                    getHeight(),musicColor,randColor));
        }
    }


    @Override protected void drawSub(Canvas canvas) {

        //mMusicItem.draw(canvas);

        for (MusicItem item : mMusicItemList){
            item.draw(canvas);
        }
    }


    @Override protected void logic() {
        //mMusicItem.move();
        for (MusicItem item : mMusicItemList){
            item.move();
        }
    }
}
