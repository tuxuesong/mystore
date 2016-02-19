package com.qianfeng.zhushou.other.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * 本应用里面所有的Fragment的父类
 *
 * Created by Liu Jianping
 *
 * @date : 16/1/12.
 */
public abstract class BaseFragment extends Fragment
{
    /** 该页面最低层的布局 */
    protected View root;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        root = inflater.inflate(getLayout(), container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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

}
