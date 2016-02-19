package com.qianfeng.zhushou.profit.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.other.ui.BaseFragment;
import com.qianfeng.zhushou.other.ui.HomeActivity;
import com.qianfeng.zhushou.other.utils.LogUtil;
import com.qianfeng.zhushou.other.utils.ZhuShouTask;
import com.qianfeng.zhushou.profit.adapter.ProfitFragmentTypeProfitListAdapter;
import com.qianfeng.zhushou.profit.bean.Games;
import com.qianfeng.zhushou.profit.utils.LoadmoreActivity;
import com.qianfeng.zhushou.profit.utils.ProfitHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 赚钱页面
 */
public class ProfitFragment extends BaseFragment {
    private TextView tvLeft, tvMiddle, tvRight;
    private LoadmoreActivity loadmoreActivityListView;
    private List<Map<String, String>> list = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private Handler handler;
    private int page = 1;
    private int code = 2;
    ProfitFragmentTypeProfitListAdapter profitFragmentTypeProfitListAdapter;
    /**
     * 赚钱页面listview传值给游戏详情页面
     */
    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Map<String, String> gamesmap = list.get(position);
            String itemId = gamesmap.get("Id");
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> gamesId = list.get(i);
                String profitId = gamesId.get("Id");
                if (profitId.equals(itemId)) {
                    Intent intent = new Intent(getActivity(), ProfitGameInfoActivity.class);
                    intent.putExtra("profitId", profitId);
                    getActivity().startActivity(intent);
                }
            }
        }
    };
    /**
     * 三个Btn的监听器
     */
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.fragment_profit_left_tv:
                    intent.setClass(getActivity(), UserLogin.class);
                    break;
                case R.id.fragment_profit_middle_tv:
                    intent.setClass(getActivity(), UserLogin.class);
                    break;
                case R.id.fragment_profit_right_tv:
                    intent.setClass(getActivity(), HomeActivity.class);
                    intent.putExtra("page", 3);
                    break;
                default:
                    break;
            }
            startActivity(intent);
        }
    };
    /**
     * 下拉刷新控件
     */
    private SwipeRefreshLayout.OnRefreshListener swipefreshlistener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            handler.sendEmptyMessageDelayed(code, 2000);
        }
    };

    @Override
    protected int getLayout() {
        return R.layout.fragment_profit;
    }
    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {
        loadmoreActivityListView = (LoadmoreActivity) root.findViewById(R.id.fragment_profit_lv);
        tvLeft = (TextView) root.findViewById(R.id.fragment_profit_left_tv);
        tvMiddle = (TextView) root.findViewById(R.id.fragment_profit_middle_tv);
        tvRight = (TextView) root.findViewById(R.id.fragment_profit_right_tv);
        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.fragment_profit_swipe);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == code) {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "无更多加载项", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
    @Override
    protected void initEvents() {
        tvLeft.setOnClickListener(listener);
        tvMiddle.setOnClickListener(listener);
        tvRight.setOnClickListener(listener);
        loadmoreActivityListView.setLoadMoreListener(new LoadmoreActivity.LoadMoreListener() {
            @Override
            public void loadmorelistener() {
                page++;
                loadlist();
                swipeRefreshLayout.setOnRefreshListener(swipefreshlistener);
            }
        });
        loadmoreActivityListView.setOnItemClickListener(itemClickListener);
    }

    /**
     * 获取数据设置listview的适配器
     */
    @Override
    protected void initData() {
        loadlist();
        profitFragmentTypeProfitListAdapter = new ProfitFragmentTypeProfitListAdapter(list, getActivity());
        loadmoreActivityListView.setAdapter(profitFragmentTypeProfitListAdapter);
    }
    public void showgames(Games games) {
        int gamesize = games.getInfo().size();
        for (int i = 0; i < gamesize; i++) {
            Map<String, String> map = new HashMap<>();
            String Id = games.getInfo().get(i).getId();
            String name = games.getInfo().get(i).getName();
            String icon = games.getInfo().get(i).getIcon();
            String score = games.getInfo().get(i).getScore();
            String count_dl = games.getInfo().get(i).getCount_dl();
            int all_prize = games.getInfo().get(i).getAll_prize();
            String size = games.getInfo().get(i).getSize();
            map.put("Id", Id);
            map.put("size", size);
            map.put("name", name);
            map.put("icon", icon);
            map.put("score", score);
            map.put("count_dl", count_dl);
            map.put("all_prize", all_prize + "");
            list.add(map);
            profitFragmentTypeProfitListAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 网络请求数据
     */
    private void loadlist() {
        ProfitHttpUtils.requestGameslist(page, new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                try {
                    LogUtil.w("-----", result.toString());
                    if (result.toString() == null) {
                    }
                    Gson gson = new Gson();
                    Games game = gson.fromJson(result.toString(), Games.class);
                    showgames(game);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void error(String msg) {
                LogUtil.w("-----游戏列表请求失败", msg);
            }
        });
    }
}
