package com.qianfeng.zhushou.other.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.qianfeng.zhushou.other.utils.LogUtil;
import com.qianfeng.zhushou.other.utils.ZhuShouHttpUtil;

public class ExchangeCircleLeftiv extends ImageView {

    public ExchangeCircleLeftiv(Context context) {
        super(context);
    }

    public ExchangeCircleLeftiv(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        LogUtil.w("---这是什么width", width + "");

        Paint paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(width / 2, width / 2, (width - 5) / 2, paint);
    }

    public void setImagePath(final String path) {
        new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = ZhuShouHttpUtil.downLoadBitmap(path);
                // 加工在圆形图
                final Bitmap circleBitmap = createCircleBitmap(bitmap);
                //在主线程中设置背景图片
                post(new Runnable() {
                    @Override
                    public void run() {
                        setImageBitmap(circleBitmap);
                    }
                });

            }
        }.start();
    }

    public Bitmap createCircleBitmap(Bitmap resource) {
        int width = resource.getWidth();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        // 创建一张空图片, 这张图片只有宽高，没有内容
        Bitmap circleBitmap = Bitmap.createBitmap(width,width,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(circleBitmap);
        // 画一个和原图片宽高一样的内切圆
        canvas.drawCircle(width / 2, width / 2, (width - 5)/2,paint);

        // 取两图的交集(也就是重合的部分)
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(resource,0,0,paint);
        return circleBitmap;
    }
}
