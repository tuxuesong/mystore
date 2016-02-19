package com.qianfeng.zhushou.profit.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.qianfeng.zhushou.R;

/**
 * Created by Administrator on 2016/2/1.
 * 用户登录忘记密码页面
 */
public class UserLoginForgotPasswordCheckNum extends Activity {
    private Button checkuser;
    private ImageView image;
    private View.OnClickListener listener = new View.OnClickListener() {
        Intent intent = new Intent();
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                /**
                 * 返回图标按钮
                 */
                case R.id.checkuser_iv:
                    intent.setClass(UserLoginForgotPasswordCheckNum.this, UserLogin.class);
                    break;
                /**
                 * 下一步，确认按钮
                 */
                case R.id.checkuser_btn:
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkuser);
        checkuser = (Button) findViewById(R.id.checkuser_btn);
        image = (ImageView) findViewById(R.id.checkuser_iv);
        checkuser.setOnClickListener(listener);
        image.setOnClickListener(listener);
    }
}
