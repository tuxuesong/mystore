package com.qianfeng.zhushou.other.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.other.utils.FileUtil;
import com.qianfeng.zhushou.other.utils.JumpManager;
import com.qianfeng.zhushou.other.utils.LogUtil;
import com.qianfeng.zhushou.other.utils.OtherHttpUtil;
import com.qianfeng.zhushou.other.utils.ZhuShouContants;
import com.qianfeng.zhushou.other.utils.ZhuShouTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * 主页面
 */
public class MainActivity extends Activity
{

    private ImageView ivIcon, ivLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        try
        {
            // 获取包信息
            PackageInfo packageInfo = getPackageManager().getPackageInfo(
                    getPackageName(), 0);

            if (packageInfo != null)
            {
                final String versionName = packageInfo.versionName;

                LogUtil.w("tag", "versionName = " + versionName);
                OtherHttpUtil.requestVersion(versionName,
                        new ZhuShouTask.RequestCallback()
                        {
                            @Override
                            public void success(Object result)
                            {
                                try
                                {
                                    JSONObject jsonObject = new JSONObject(
                                            result.toString());

                                    String state = jsonObject
                                            .getString(ZhuShouContants.FLAG_REQUEST_STATE);

                                    if (ZhuShouContants.FLAG_REQUEST_SUCCESS
                                            .equals(state))
                                    {
                                        JSONObject info = jsonObject
                                                .getJSONObject("info");

                                        String msg = info.getString("msg");

                                        msg = Html.fromHtml(msg).toString();

                                        String apkUrl = info.getString("src");

                                        String ver = info.getString("ver");
                                        if (versionName.equals(ver))
                                        {
                                            return;
                                        }

                                        Dialog dialog = getDialog(msg, apkUrl);

                                        dialog.show();
                                    }
                                }
                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void error(String msg)
                            {
                                Toast.makeText(MainActivity.this, msg,
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        }
        catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }

        // 如果是第一次使用该应用，那么跳转到引导页
        if (isFirstUsed())
        {
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            finish();
        }
        // 否则进到主页面
        else
        {
             showAnim();
        }

    }

    @NonNull
    private Dialog getDialog(String msg, final String apkUrl)
    {
        // 如果请求成功了，那么用Dialog显示版本信息
        final Dialog dialog = new Dialog(MainActivity.this,
                R.style.upgrade_dialog_style);

        dialog.setContentView(R.layout.dialog_upgrade);

        TextView tvMsg = (TextView) dialog.findViewById(R.id.upgrade_msg_tv);
        tvMsg.setText(msg);

        Button btnCancle = (Button) dialog
                .findViewById(R.id.upgrade_cancel_btn);
        final Button btnUpgrade = (Button) dialog.findViewById(R.id.upgrade_ok_btn);

        final ProgressBar progressBar = (ProgressBar) dialog
                .findViewById(R.id.upgrade_pb);

        btnCancle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });

        btnUpgrade.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 如果开始下载，那么先显示进度条
                progressBar.setVisibility(View.VISIBLE);
                //点击更新让它不能再点击了
                btnUpgrade.setEnabled(false);

                // 执行下载apk操作
                OtherHttpUtil.downLoadApk(apkUrl,
                        new ZhuShouTask.RequestCallback()
                        {
                            @Override
                            public void success(Object result)
                            {
                                File apk = (File) result;

                                FileUtil.installApk(MainActivity.this, apk);
                            }

                            @Override
                            public void error(String msg)
                            {
                                Toast.makeText(MainActivity.this,
                                        "无法下载apk, 是不是不地址写错了?",
                                        Toast.LENGTH_SHORT).show();
                            }
                        },

                        new ZhuShouTask.UpgradeListener()
                        {
                            @Override
                            public void upgradeProgress(int progress)
                            {
                                progressBar.setProgress(progress);
                            }
                        });

            }
        });

        return dialog;
    }

    private void showAnim()
    {
        setContentView(R.layout.activity_main);

        ivIcon = (ImageView) findViewById(R.id.main_icon_iv);
        ivLabel = (ImageView) findViewById(R.id.main_label_iv);

        // 初始化label和icon的动画
        Animation labelAnim = AnimationUtils.loadAnimation(this,
                R.anim.anim_main_label_in);
        final Animation iconAnim = AnimationUtils.loadAnimation(this,
                R.anim.anim_main_icon_in);

        // 分别设置动画的监听
        labelAnim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                // 在label动画结束后再执行icon的动画
                ivIcon.startAnimation(iconAnim);
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });

        iconAnim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                // 在icon动画结束后显示icon
                ivIcon.setVisibility(View.VISIBLE);
                // 跳转到主页面
                JumpManager.jumpToHome(MainActivity.this);
                // 当前页面已经不需要了
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
        ivLabel.startAnimation(labelAnim);
    }

    private boolean isFirstUsed()
    {
        SharedPreferences preferences = getSharedPreferences(
                ZhuShouContants.PERFERENCE_FIRST_USED, Context.MODE_PRIVATE);
        return preferences.getBoolean(ZhuShouContants.PERFERENCE_FLAG_USED,
                true);
    }

}
