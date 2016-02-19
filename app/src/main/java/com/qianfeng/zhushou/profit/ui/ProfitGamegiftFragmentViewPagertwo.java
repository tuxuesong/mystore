package com.qianfeng.zhushou.profit.ui;

import android.widget.ListView;

import com.google.gson.Gson;
import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.other.ui.BaseFragment;
import com.qianfeng.zhushou.other.utils.LogUtil;
import com.qianfeng.zhushou.other.utils.ZhuShouTask;
import com.qianfeng.zhushou.profit.bean.Games;
import com.qianfeng.zhushou.profit.utils.ProfitHttpUtils;

public class ProfitGamegiftFragmentViewPagertwo extends BaseFragment {
    private ListView listView;
    private int page;

    @Override
    protected int getLayout() {
        return R.layout.activity_gameinfo_fragment;
    }

    @Override
    protected void initViews() {
        listView = (ListView) root.findViewById(R.id.profit_gameinfo_listview);

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        loadlist();
      //  Bundle b = getArguments();
//        String gamename = b.getString("gamename");
  //      LogUtil.w("---gamename",gamename);
    }



    private void loadlist() {
        ProfitHttpUtils.requestGamesinfolist(new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                try {
                    if (result.toString() == null) {
                        return;
                    }
                    Gson gson = new Gson();
                    Games game = gson.fromJson(result.toString(), Games.class);
                    LogUtil.w("-----gamegift游戏列表请求成功", result.toString());
                    showgames(game);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg) {
                LogUtil.w("-----gamegift游戏列表请求失败", msg);

            }
        });
    }

    public void showgames(Games games) {
        int size = games.getInfo().size();
        for (int i = 0; i < size; i++) {
            String name = games.getInfo().get(i).getName();
        }
    }
}
