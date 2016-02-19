package com.qianfeng.zhushou.exchange.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.exchange.adapter.ExchangeActivityListAdapter;
import com.qianfeng.zhushou.exchange.bean.ExChanges;
import com.qianfeng.zhushou.exchange.utils.ExchangeHttpUtils;
import com.qianfeng.zhushou.other.ui.BaseFragment;
import com.qianfeng.zhushou.other.utils.LogUtil;
import com.qianfeng.zhushou.other.utils.ZhuShouTask;
import com.qianfeng.zhushou.profit.utils.LoadmoreActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExchangeListFragment extends BaseFragment {
    private LoadmoreActivity listView;
    private List<Map<String, String>> list = new ArrayList<>();
    ExchangeActivityListAdapter adapter;
    private int type;
    private int page = 1;
    private String exchangeid;
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getActivity(),Exchangegiftinfo.class);
            intent.putExtra("id", exchangeid);
          getActivity().startActivity(intent);
        }
    };

    public ExchangeListFragment(int type) {
        this.type = type;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_exchange_list;
    }
    @Override
    protected void initViews() {
        listView = (LoadmoreActivity) root.findViewById(R.id.exchange_lv);
        listView.setOnItemClickListener(itemClickListener);
    }
    @Override
    protected void initEvents() {
    listView.setLoadMoreListener(new LoadmoreActivity.LoadMoreListener() {

        @Override
        public void loadmorelistener() {
            page++;
            loadexchange();
        }
    });
    }
    @Override
    protected void initData() {
        loadexchange();
    }
    public void onshowExchanges(ExChanges exChanges) {
        int exchangesize = exChanges.getInfo().size();
        for (int i = 0; i < exchangesize; i++) {
            Map<String, String> map = new HashMap<>();
            String name = exChanges.getInfo().get(i).getName();
            String consume = exChanges.getInfo().get(i).getConsume();
            String icon = exChanges.getInfo().get(i).getIcon();
            String remain = exChanges.getInfo().get(i).getRemain();
             exchangeid = exChanges.getInfo().get(i).getId();

            LogUtil.w("---exchangelist...id",exchangeid);
            map.put("name", name);
            map.put("id",exchangeid);
            map.put("consume", consume);
            map.put("icon", icon);
            map.put("remain", remain);
            list.add(map);
        }
        adapter = new ExchangeActivityListAdapter(list, getActivity());
        listView.setAdapter(adapter);
    }
    public void loadexchange() {
        ExchangeHttpUtils.requestExchange(type, page, new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                Log.w("----exchanges数据111", result.toString());
                Gson gson = new Gson();
                ExChanges exchanges = gson.fromJson(result.toString(), ExChanges.class);
                onshowExchanges(exchanges);
            }
            @Override
            public void error(String msg) {

            }
        });
    }
}
