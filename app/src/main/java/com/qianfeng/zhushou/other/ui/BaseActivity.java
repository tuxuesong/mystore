package com.qianfeng.zhushou.other.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qianfeng.zhushou.R;

import java.awt.font.TextAttribute;

/**
 * Created by Liu Jianping
 *
 * @date : 16/1/13.
 */
public abstract class BaseActivity extends Activity
{
    private ImageView ivLeft, ivRight;

    private TextView tvTitle, tvRight;

    protected LinearLayout llContent;

    private RelativeLayout rlRight;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);

        ivLeft = (ImageView) findViewById(R.id.title_bar_left_iv);
        ivRight = (ImageView) findViewById(R.id.title_bar_right_iv);
        tvTitle = (TextView) findViewById(R.id.title_bar_title_tv);
        tvRight = (TextView) findViewById(R.id.title_bar_right_tv);
        llContent = (LinearLayout) findViewById(R.id.base_content_ll);
        rlRight = (RelativeLayout) findViewById(R.id.title_bar_right_rl);

        setDefaultEvent();

        // 把子类里面的布局添加到title_bar下面的LinearLayout中
        getLayoutInflater().inflate(getLayout(), llContent);

        initViews();

        initEvents();

        initData();
    }

    /**
     * 获取布局
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 初始化视图
     */
    protected abstract void initViews();

    /**
     * 初始化事件
     */
    protected abstract void initEvents();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    protected void setTitleText(String title)
    {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
    }

    protected void setTitleText(int stringId)
    {
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(stringId);
    }

    protected void setRightText(String text)
    {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(text);
    }

    protected void setRightText(int stringId)
    {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(stringId);
    }

    protected void setRightImage(int selectorId)
    {
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(selectorId);
    }

    private void setDefaultEvent()
    {
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected void setRightOnClickListener(View.OnClickListener onClickListener)
    {
        rlRight.setOnClickListener(onClickListener);
    }

    protected void showLeftImage()
    {
        ivLeft.setVisibility(View.VISIBLE);
    }

}
