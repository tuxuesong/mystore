package com.qianfeng.zhushou.gift.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.gift.adapter.GiftListAdapter;
import com.qianfeng.zhushou.gift.bean.Gift;
import com.qianfeng.zhushou.gift.util.GiftHttpUtil;
import com.qianfeng.zhushou.other.ui.BaseFragment;
import com.qianfeng.zhushou.other.utils.LogUtil;
import com.qianfeng.zhushou.other.utils.ZhuShouTask;
import com.qianfeng.zhushou.other.widget.LoadMoreListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 礼包列表页面
 *
 * Created by Liu Jianping
 *
 * @date : 16/1/12.
 */
public class GiftListFragment extends BaseFragment
{
    private LoadMoreListView listView;

    private int type;

    private List<Gift> list = new ArrayList<>();

    private float lastY;

    private int page = 1;

    private GiftListAdapter adapter;

    public GiftListFragment(int gifttype)
    {
        type = gifttype;
    }

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_type_list;
    }

    @Override
    protected void initViews()
    {
        listView = (LoadMoreListView) root;
    }

    @Override
    protected void initEvents()
    {
        listView.setLoadMoreListener(new LoadMoreListView.LoadMoreListener()
        {
            @Override
            public void onLoadMore()
            {
                page++;
                loadList();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id)
            {
                Gift gift = list.get(position);
                String gameId = gift.getId();

                Intent intent = new Intent(getActivity(),
                        GiftInfoActivity.class);
                intent.putExtra("id", gameId);
                getActivity().startActivity(intent);
            }
        });

    }

    @Override
    protected void initData()
    {
        adapter = new GiftListAdapter(getActivity(), list);

        listView.setAdapter(adapter);

        loadList();
    }

    private void loadList()
    {
        GiftHttpUtil.requestGiftList(type, page,
                new ZhuShouTask.RequestCallback()
                {
                    @Override
                    public void success(Object result)
                    {
                        try
                        {
                            if (result == null)
                            {
                                return;
                            }
                            LogUtil.w("-----礼包数据", result.toString());
                            JSONObject json = new JSONObject(result.toString());

                            String state = json.getString("state");
                            // 如果解析的状态是成功
                            if ("success".equals(state))
                            {
                                // JSONArray info = json.getJSONArray("info");

                                // List<Gift> list =
                                // Gift.arrayGiftFromData(info.toString());

                                List<Gift> gifts = Gift.arrayGiftFromData(
                                        json.toString(), "info");

                                list.addAll(gifts);

                                Log.w("tag", "list.size = " + list.size());

                                adapter.notifyDataSetChanged();
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void error(String msg)
                    {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }
}
