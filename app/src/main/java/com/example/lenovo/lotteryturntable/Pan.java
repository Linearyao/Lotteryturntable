package com.example.lenovo.lotteryturntable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by lenovo on 2016/10/6.
 */
public class Pan extends SurfaceView implements SurfaceHolder.Callback,Runnable{
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private Thread t;
    private boolean isRunning;
    private String[] mStrs=new String[]{"单反相机","IPAD","恭喜发财","IPHONE","服装","恭喜发财"};
    private int[] mImgs=new int[]{R.mipmap.danfan,R.mipmap.ipad,R.mipmap.f040,R.mipmap.iphone,R.mipmap.meizi,R.mipmap.f040};
    private int[] mColor=new int[]{R.color.colorAccent,0xFFF17E01,R.color.colorAccent,0xFFF17E01,R.color.colorAccent,0xFFF17E01,};
    private int mItemCount=6;
    private Bitmap[] mImgsBitmap;
    private RectF mRange=new RectF();
    private int mRadius;//盘块直径
    private Paint mArcPaint;//绘制盘块的画笔
    private Paint mTextPaint;//绘制文本的画笔
    private double mSpeed=0;//转动的速度
    private volatile int mStartAngle=0;//初始角度
    private boolean isShouldEnd;
    private int mCenter;
    private int mPadding;
    private Bitmap mBgBitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.bg2);
    private float mTextSize= TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,20,getResources().getDisplayMetrics());


    public Pan(Context context) {
        super(context,null);
    }
    public Pan(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        mHolder=getHolder();
        mHolder.addCallback((SurfaceHolder.Callback) this);
        setFocusable(true);//获得焦点
        setFocusableInTouchMode(true);//设置常量
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width=Math.min(getMeasuredWidth(),getMeasuredHeight());
        mPadding=getPaddingLeft();
        mRadius=width-mPadding*2;
        mCenter=width/2;
        setMeasuredDimension(width,width);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mArcPaint=new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setDither(true);

        mTextPaint=new Paint();
        mTextPaint.setColor(0xffffffff);
        mTextPaint.setTextSize(mTextSize);

        mRange=new RectF(mPadding,mPadding,mPadding+mRadius,mPadding+mRadius);
        mImgsBitmap=new Bitmap[mItemCount];
        for (int i=0;i<mItemCount;i++){
            mImgsBitmap[i]=BitmapFactory.decodeResource(getResources(),mImgs[i]);
        }

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
            long start=System.currentTimeMillis();
            draw();
            long end=System.currentTimeMillis();
            if (end-start<50){
                try {
                    Thread.sleep(50-(end-start));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void draw() {
        try {
            mCanvas=mHolder.lockCanvas();
            if (mCanvas!=null)
            {
                drawBg();
                float tmpAngle=mStartAngle;
                float sweepAngle=360/mItemCount;
                for (int i=0;i<mItemCount;i++){
                    mArcPaint.setColor(mColor[i]);
                    mCanvas.drawArc(mRange,tmpAngle,sweepAngle,true,mArcPaint);
                    drawText(tmpAngle,sweepAngle,mStrs[i]);
                    drawIcon(tmpAngle,mImgsBitmap[i]);
                    tmpAngle+=sweepAngle;
                }
                mStartAngle+=mSpeed;
                //如果点击停止按钮
                if (isShouldEnd){
                    mSpeed-=1;
                }
                if (mSpeed<=0){
                    mSpeed=0;
                    isShouldEnd=false;
                }
            }
        } catch (Exception e) {
        }
        finally {
            if (mCanvas!=null){
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
    public void luckyStart(){
        mSpeed=50;
        isShouldEnd=false;
    }
    public void luckyEnd(){
        isShouldEnd=true;
    }
    public boolean isStart(){
        return mSpeed !=0;
    }
    public boolean isShouldEnd(){
        return isShouldEnd;
    }

    private void drawIcon(float tmpAngle, Bitmap bitmap) {
        int imgWidth=mRadius/8;
        float angle= (float) ((tmpAngle+360/mItemCount/2)*Math.PI/180);
        int x= (int) (mCenter+mRadius/2/2*Math.cos(angle));
        int y= (int) (mCenter+mRadius/2/2*Math.sin(angle));
        Rect rect=new Rect(x-imgWidth/2,y-imgWidth/2,x+imgWidth/2,y+imgWidth/2);
        mCanvas.drawBitmap(bitmap,null,rect,null);
    }

    private void drawText(float tmpAngle, float sweepAngle, String mStr) {
        Path path=new Path();
        path.addArc(mRange,tmpAngle,sweepAngle);
        int textWidth= (int) mTextPaint.measureText(mStr);
        int hOffset= (int) (mRadius*Math.PI/mItemCount/2-textWidth/2);
        int vOffset=mRadius/2/6;
        mCanvas.drawTextOnPath(mStr,path,hOffset,vOffset,mTextPaint);
    }

    private void drawBg() {
        mCanvas.drawColor(0xFFFFFFFF);
        mCanvas.drawBitmap(mBgBitmap,null,new Rect(mPadding/2,mPadding/2,getMeasuredWidth()-mPadding/2,getMeasuredHeight()-mPadding/2),null);
    }
}
