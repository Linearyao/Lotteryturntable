package com.example.lenovo.lotteryturntable;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

/**
 * Created by lenovo on 2016/10/6.
 */
public class SurfaceViewTempalte extends SurfaceView implements SurfaceHolder.Callback,Runnable{
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private Thread t;
    private boolean isRunning;

    public SurfaceViewTempalte(Context context) {
        super(context,null);
    }
    public SurfaceViewTempalte(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        mHolder=getHolder();
        mHolder.addCallback((SurfaceHolder.Callback) this);
        setFocusable(true);//获得焦点
        setFocusableInTouchMode(true);//设置常量
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        isRunning=true;//初始化线程
        t=new Thread((Runnable) this);
        t.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        isRunning=false;
    }

    @Override
    public void run() {
        while (isRunning)//不断绘制
        {
            draw();
        }
    }

    private void draw() {
        try {
            mCanvas=mHolder.lockCanvas();
            if (mCanvas!=null)
            {
                //draw sth;
            }
        } catch (Exception e) {
        }
        finally {
            if (mCanvas!=null){
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
