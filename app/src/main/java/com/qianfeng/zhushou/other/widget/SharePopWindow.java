package com.qianfeng.zhushou.other.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.qianfeng.zhushou.R;

/**
 * PopupWindow弹出窗口
 */
public class SharePopWindow extends PopupWindow
{

    private Button btn;

    public SharePopWindow(Context context)
    {
        super(context);

        //设置pop的内容布局
        View content = LayoutInflater.from(context).inflate(R.layout.pop_share,
                null);
        setContentView(content);

        //设置pop的宽高,如果不设置的话,那么不显示!
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        //设置进出动画
        setAnimationStyle(R.style.share_pop_anim);

        //点击在pop以外的区域会消失
        setFocusable(true);

        btn = (Button) content.findViewById(R.id.share_btn);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });

    }
}
