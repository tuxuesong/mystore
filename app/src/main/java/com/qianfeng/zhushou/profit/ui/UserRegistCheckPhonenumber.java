package com.qianfeng.zhushou.profit.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.other.utils.LogUtil;
import com.qianfeng.zhushou.other.utils.ZhuShouTask;
import com.qianfeng.zhushou.profit.utils.ProfitHttpUtils;

/**
 * 用户注册：下一步：手机号验证页面
 */
public class UserRegistCheckPhonenumber extends Activity implements View.OnClickListener {
    private EditText etnum, firstet;
    private Button btnphonenum, btnnum;
    String send = "send";
    String password,nickname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_phonenumber);
        btnnum = (Button) findViewById(R.id.checkbtn);
        btnphonenum = (Button) findViewById(R.id.checkuser_btn);
        etnum = (EditText) findViewById(R.id.checkphone_sec_et);
        firstet = (EditText) findViewById(R.id.checkphone_first_et);
        btnnum.setOnClickListener(this);
        btnphonenum.setOnClickListener(this);
        Intent intent = getIntent();
        /**
         * 用户注册页面传递过来的密码：昵称
         */
        password = intent.getStringExtra("password");
        nickname= intent.getStringExtra("nickname");
    }
    /**
     * 发送验证码
     */
    public void checkphonenum() {
        String phonenumber = firstet.getText().toString();
        ProfitHttpUtils.checkphonenum(send, phonenumber, new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
            }
            @Override
            public void error(String msg) {
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkbtn:
                checkphonenum();
                break;
            case R.id.checkuser_btn:
                commitphonenum();
                finish();
                break;
        }
    }
    /**
     * 手机号验证
     */
    public void commitphonenum() {
        String op = "check";
        String username = firstet.getText().toString();
        String nizhengma = etnum.getText().toString();
        String passwordnum = new String(Base64.encode(password.getBytes(), Base64.DEFAULT));
        LogUtil.w("--password",password);
        LogUtil.w("--nickname",nickname);
        ProfitHttpUtils.checknianzhenma(op, username, nizhengma, passwordnum,nickname, new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                Toast.makeText(UserRegistCheckPhonenumber.this,"注册成功",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void error(String msg) {
            }
        });
    }
}
