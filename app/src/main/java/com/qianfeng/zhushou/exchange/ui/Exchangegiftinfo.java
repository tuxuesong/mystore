package com.qianfeng.zhushou.exchange.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.exchange.bean.Exchangegiftlist;
import com.qianfeng.zhushou.exchange.utils.ExchangeHttpUtils;
import com.qianfeng.zhushou.other.utils.ImageLoader;
import com.qianfeng.zhushou.other.utils.ZhuShouTask;

public class Exchangegiftinfo extends Activity {

    private ImageView imageView;
    private TextView textViewname, textViewconsume;
    private Button btn;
    private ImageLoader imageLoader = new ImageLoader(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchangegiftinfo);
        imageView = (ImageView) findViewById(R.id.exchange_gameinfo_iv);
        textViewname = (TextView) findViewById(R.id.exchange_gameinfo_tv);
        textViewconsume = (TextView) findViewById(R.id.exchange_gameinfo_cousme_tv);
        btn = (Button) findViewById(R.id.exchange_gameinfo_btn);
        onshowcontent();

    }

    public void onshowcontent() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        ExchangeHttpUtils.requestgameinfo(id, new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                Gson gson = new Gson();
                Exchangegiftlist exChangeslist = gson.fromJson(result.toString(), Exchangegiftlist.class);
                onshowgamegift(exChangeslist);
            }

            @Override
            public void error(String msg) {

            }
        });
    }
        public void onshowgamegift(Exchangegiftlist gift){
            String gamename = gift.getInfo().getName();
            String Icon = gift.getInfo().getIcon();
            String remain = gift.getInfo().getRemain();
            String consume = gift.getInfo().getConsume();
            imageLoader.DisplayImage(Icon,imageView);
            textViewname.setText(gamename);
            textViewname.setSingleLine(true);
            textViewname.setEllipsize(TextUtils.TruncateAt.END);
            btn.setText("立即兑换(" + consume + "u币)");
            textViewconsume.setText("剩余:"+remain);
    }

    }


