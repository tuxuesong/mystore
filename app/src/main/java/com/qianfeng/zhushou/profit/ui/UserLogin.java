package com.qianfeng.zhushou.profit.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.zhushou.R;

/**
 * Created by Administrator on 2016/1/31.
 * 用户登录页面
 */
public class UserLogin extends Activity {
    private ImageView image;
    private TextView tvResigst,tvpassword,tvexceptionBottom;
    private Button loginButton;
    /**
     * 监听器
     */
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()){
                case R.id.user_login_lv:
                    intent.setClass(UserLogin.this,ProfitFragment.class);
                    startActivity(intent);
                    break;
                case R.id.user_regist:
                    intent.setClass(UserLogin.this,UserRegist.class);
                    startActivity(intent);
                    break;
                case R.id.user_password:
                    intent.setClass(UserLogin.this,UserLoginForgotPasswordCheckNum.class);
                    startActivity(intent);
                    break;
                case R.id.user_login_btn:
                    Toast.makeText(UserLogin.this,"登录成功",Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case R.id.user_exception:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        image = (ImageView) findViewById(R.id.user_login_lv);
        tvResigst = (TextView) findViewById(R.id.user_regist);
        tvpassword = (TextView) findViewById(R.id.user_password);
        loginButton = (Button) findViewById(R.id.user_login_btn);
        tvexceptionBottom = (TextView) findViewById(R.id.user_exception);
        image.setOnClickListener(listener);
        tvResigst.setOnClickListener(listener);
        tvpassword.setOnClickListener(listener);
        loginButton.setOnClickListener(listener);
        tvexceptionBottom.setOnClickListener(listener);
    }
}
