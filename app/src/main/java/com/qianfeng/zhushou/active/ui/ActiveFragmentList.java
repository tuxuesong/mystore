package com.qianfeng.zhushou.active.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.active.adapter.ActiveFragmentlistAdapter;
import com.qianfeng.zhushou.active.bean.Activelist;
import com.qianfeng.zhushou.active.utils.RequestImage;
import com.qianfeng.zhushou.other.ui.BaseFragment;
import com.qianfeng.zhushou.other.utils.LogUtil;
import com.qianfeng.zhushou.other.utils.ZhuShouTask;
import com.qianfeng.zhushou.profit.utils.LoadmoreActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActiveFragmentList extends BaseFragment {
    private LoadmoreActivity listview;
    private List<Map<String,String>> list = new ArrayList<>();
    ActiveFragmentlistAdapter adapter;
    private AdapterView.OnItemClickListener listenerlist = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Map<String,String> map = list.get(position);
            String activeid = map.get("id");
            for(int i=0;i<list.size();i++) {
                Map<String,String> params =list.get(i);
                String aid = params.get("id");
                if(activeid.equals(aid)) {
                    Intent intent = new Intent(getActivity(), ActiveInfo.class);
                    intent.putExtra("id", activeid);
                    LogUtil.w("----------", "得到id没有" + activeid);
                    getActivity().startActivity(intent);
                }
            }
        }
    };

    @Override
    protected int getLayout() {
        return R.layout.activity_active_fragment_list;
    }

    @Override
    protected void initViews() {
        listview = (LoadmoreActivity) root.findViewById(R.id.active_fragment_list);

    }

    @Override
    protected void initEvents() {
    listview.setOnItemClickListener(listenerlist);
    }

    @Override
    protected void initData() {
        requestActive();

    }


    public void onshowActive(Activelist activelist) {
        int isize = activelist.getInfo().size();
        for(int i=0;i<isize;i++){
            Map<String,String> map = new HashMap<>();
            String aname = activelist.getInfo().get(i).getAname();
            map.put("aname", aname);

            String shorttime = activelist.getInfo().get(i).getShortname();
            map.put("shorttime", shorttime);

            String hotpic = activelist.getInfo().get(i).getHotpic();
            map.put("hotpic", hotpic);

            int total_join_user = activelist.getInfo().get(i).getTotal_join_user();
            map.put("total_join_user", total_join_user + "");

            String staus = activelist.getInfo().get(i).getStatus();
            map.put("status",staus);

            String activeid = activelist.getInfo().get(i).getId();
            map.put("id",activeid);
            list.add(map);
        }
        adapter = new ActiveFragmentlistAdapter(getActivity(),list);
        LogUtil.w("---active-",list.size()+"");
        listview.setAdapter(adapter);
    }

    public void requestActive() {
        RequestImage.requestActive(new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                Gson gson = new Gson();
                Activelist activelist = gson.fromJson(result.toString(), Activelist.class);
                onshowActive(activelist);
            }

            @Override
            public void error(String msg) {

            }
        });


    }
}
