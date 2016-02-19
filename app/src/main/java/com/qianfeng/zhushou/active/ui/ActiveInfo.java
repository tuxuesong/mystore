package com.qianfeng.zhushou.active.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.active.bean.Active_infos;
import com.qianfeng.zhushou.active.utils.RequestImage;
import com.qianfeng.zhushou.other.utils.LogUtil;
import com.qianfeng.zhushou.other.utils.ZhuShouTask;

public class ActiveInfo extends Activity {

    private TextView textViewtitle,textViewname,textViewtime,textViewcontent;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_info);
        textViewname = (TextView) findViewById(R.id.active_middle_tv);
        textViewtime = (TextView) findViewById(R.id.active_right_time);
        textViewcontent = (TextView) findViewById(R.id.active_content);
        textViewtitle = (TextView) findViewById(R.id.active_title_top_tv);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        LogUtil.w("----是否得到了传过来的ID",id);
        requestinfo();
    }
    public void requestinfo(){

        RequestImage.requestContent(id, new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                Gson gson = new Gson();
                Active_infos infos =gson.fromJson(result.toString(),Active_infos.class);
                onshowContent(infos);
            }

            @Override
            public void error(String msg) {

            }
        });
    }
    public void onshowContent(Active_infos infos){
        String nname = infos.getInfo().getAname();
        textViewtitle.setText(nname);

        String writer = infos.getInfo().getWriter();
        textViewname.setText(writer);

        String pubdate = infos.getInfo().getPubdate();
        textViewtime.setText(pubdate);

        String content = infos.getInfo().getContent();
        textViewcontent.setText(content);
    }
}
