package com.qianfeng.zhushou.my.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.other.ui.BaseFragment;
import com.qianfeng.zhushou.profit.ui.UserLogin;

/**
 * 我的页面
 * <p/>
 * Created by Liu Jianping
 *
 * @date : 16/1/12.
 */
public class MyFragment extends BaseFragment {
    private ImageView imageView, imageViewlogin;
    private TextView textViewfirst, textViewsecond;
    private LinearLayout linearone, lineartwo, linearthree, linearfour,
    linearmytask,linearmyweixin,linearmyfriend;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), UserLogin.class);
            switch (v.getId()) {
                case R.id.my_circle:
                case R.id.my_qq_name:
                case R.id.my_qq_money:
                case R.id.my_userlogin:
                case R.id.my_mygamegift:
                case R.id.my_mygameactive:
                case R.id.my_mygameexchage:
                case R.id.my_mygameplay:
                case R.id.my_mygametask:
                case R.id.my_mygameweixin:
                case R.id.my_mygamefriend:
                    getActivity().startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initViews() {
        imageView = (ImageView) root.findViewById(R.id.my_circle);
        textViewfirst = (TextView) root.findViewById(R.id.my_qq_name);
        textViewsecond = (TextView) root.findViewById(R.id.my_qq_money);
        imageViewlogin = (ImageView) root.findViewById(R.id.my_userlogin);
        linearone = (LinearLayout) root.findViewById(R.id.my_mygamegift);
        lineartwo = (LinearLayout) root.findViewById(R.id.my_mygameactive);
        linearthree = (LinearLayout) root.findViewById(R.id.my_mygameexchage);
        linearfour = (LinearLayout) root.findViewById(R.id.my_mygameplay);
        linearmytask = (LinearLayout) root.findViewById(R.id.my_mygametask);
        linearmyweixin = (LinearLayout) root.findViewById(R.id.my_mygameweixin);
        linearmyfriend = (LinearLayout) root.findViewById(R.id.my_mygamefriend);
    }

    @Override
    protected void initEvents() {
        linearmytask.setOnClickListener(listener);
        linearmyweixin.setOnClickListener(listener);
        linearmyfriend.setOnClickListener(listener);
        imageView.setOnClickListener(listener);
        imageViewlogin.setOnClickListener(listener);
        textViewfirst.setOnClickListener(listener);
        textViewsecond.setOnClickListener(listener);
        linearone.setOnClickListener(listener);
        lineartwo.setOnClickListener(listener);
        linearthree.setOnClickListener(listener);
        linearfour.setOnClickListener(listener);
    }

    @Override
    protected void initData() {

    }
}
