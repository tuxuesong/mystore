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

import com.qianfeng.zhushou.other.utils.ZhuShouHttpUtil;

/**
 * 显示圆形图片的ImageView
 * 
 * Created by Liu Jianping
 *
 * @date : 16/1/14.
 */
public class CircleImageView extends ImageView
{

    public CircleImageView(Context context)
    {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();

        //在外面画一个边框
        Paint strokeP = new Paint();
        strokeP.setColor(Color.YELLOW);
        strokeP.setStrokeWidth(5);
        strokeP.setAntiAlias(true);
        strokeP.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(width / 2, width / 2, (width - 5)  / 2, strokeP);

    }

    /**
     * 设置ImageView的Url
     * 
     * @param httpUrl
     */
    public void setImageUrl(final String httpUrl)
    {
        new Thread()
        {
            @Override
            public void run()
            {

                Bitmap bitmap = ZhuShouHttpUtil.downLoadBitmap(httpUrl);
                // 加工在圆形图
                final Bitmap circleBitmap = createCircleBitmap(bitmap);

                //在主线程中设置背景图片
                post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        setImageBitmap(circleBitmap);
                    }
                });

            }
        }.start();
    }

    /**
     * 把 源圆片 加工成 圆形图片
     * 
     * @param resource
     *            源圆片
     * @return
     */
    private Bitmap createCircleBitmap(Bitmap resource)
    {
        int width = resource.getWidth();
        Paint paint = new Paint();
        // 画圆或者弧形图，需要抗锯齿
        paint.setAntiAlias(true);

        // 创建一张空图片, 这张图片只有宽高，没有内容
        Bitmap circleBitmap = Bitmap.createBitmap(width ,
                width , Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(circleBitmap);

        // 画一个和原图片宽高一样的内切圆
        canvas.drawCircle(width  / 2, width  / 2,
                (width - 5 )/ 2, paint);

        // 取两图的交集(也就是重合的部分)
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        // 把源图覆盖上去
        canvas.drawBitmap(resource, 0, 0, paint);


        return circleBitmap;
    }

}
