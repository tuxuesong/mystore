package com.qianfeng.zhushou.profit.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.qianfeng.zhushou.R;

/**
 * Created by Administrator on 2016/2/1.
 * 用户注册
 */
public class UserRegist extends Activity {
    private ImageView image;
    private Button registbtn;
    private EditText etphone, etname, etpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_regist);
        image = (ImageView) findViewById(R.id.user_regist_iv_back);
        registbtn = (Button) findViewById(R.id.user_regist_btn);
        etphone = (EditText) findViewById(R.id.user_regist_phone);
        etname = (EditText) findViewById(R.id.user_regist_name);
        etpassword = (EditText) findViewById(R.id.user_regist_password);
        /**
         * 返回图标点击事件
         */
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(UserRegist.this, UserLogin.class);
                startActivity(intent);
            }
        });
        /**
         * 确定按钮点击事件
         */
        registbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String phonenum = etphone.getText().toString();
                String username = etname.getText().toString();
                String password = etpassword.getText().toString();
                /**
                 * 文本框规则校验
                 */
                if ((phonenum.equals("")) || (username.equals("")) || (password.equals(""))) {
                    Toast.makeText(UserRegist.this, "必填项不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    intent.setClass(UserRegist.this, UserRegistCheckPhonenumber.class);
                    intent.putExtra("password", password);
                    intent.putExtra("nickname",username);
                    startActivity(intent);
                }
            }
        });
    }
}
