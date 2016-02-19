package com.qianfeng.zhushou.other.utils;

import android.app.Activity;
import android.content.Intent;

import com.qianfeng.zhushou.other.ui.HomeActivity;

/**
 * 跳转工具类
 *
 * Created by Liu Jianping
 *
 * @date : 16/1/11.
 */
public class JumpManager
{

    /**
     * 跳转到主页面
     * 
     * @param activity
     *            发起页面
     */
    public static void jumpToHome(Activity activity)
    {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }
}
