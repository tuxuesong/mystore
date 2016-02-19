package com.qianfeng.zhushou.profit.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.other.ui.BaseFragment;
import com.qianfeng.zhushou.other.utils.ImageLoader;
import com.qianfeng.zhushou.other.utils.LogUtil;
import com.qianfeng.zhushou.other.utils.ZhuShouTask;
import com.qianfeng.zhushou.profit.bean.GameInfo;
import com.qianfeng.zhushou.profit.bean.LoveGame;
import com.qianfeng.zhushou.profit.utils.ProfitHttpUtils;

/**
 * 游戏详情页面第一个viewpage
 */
public class ProfitGameInfoFragmentViewpagerone extends BaseFragment {
    private TextView utextview, textviewintroduce, textViewright, utextview2, textView2right;
    private String gameId;
    private ImageLoader imageLoader = new ImageLoader(getActivity());
    private LinearLayout linearLayout[] = new LinearLayout[4];
    private TextView textViewname[] = new TextView[4];
    private TextView textViewhowmany[] = new TextView[4];
    private ImageView imageview [] = new ImageView[4];
    private LinearLayout firstlinearLayout;
    private String gamename;
    @Override
    protected int getLayout() {
        return R.layout.activity_profitfragment_gifo;
    }
    @Override
    protected void initViews() {
        utextview = (TextView) root.findViewById(R.id.profit_gameinfo_u_tv);
        textViewright = (TextView) root.findViewById(R.id.profit_gameinfo_task_tv);
        utextview2 = (TextView) root.findViewById(R.id.profit_gameinfo_u_tv2);
        textView2right = (TextView) root.findViewById(R.id.profit_gameinfo_task_tv2);
        textviewintroduce = (TextView) root.findViewById(R.id.profit_gameinfo_content_tv);
        firstlinearLayout = (LinearLayout) root.findViewById(R.id.profit_gameinfo_linearlayout);
    }
    @Override
    protected void initEvents() {
    }
    @Override
    protected void initData() {
        /**
         * 从游戏列表获得的gameid：用来网络请求：
         */
        Bundle b = getArguments();
        gameId = b.getString("gameid");
        getContent();
        Gameinfofragment();
    }

    /**
     * 游戏详情页面：网络请求获得的内容
     * @param gameInfo
     */
    public void onshowcontent(GameInfo gameInfo) {
        String Dl_back_jifen = gameInfo.getInfo().getDl_back_jifen();
        utextview.setText(Dl_back_jifen + "u");
        String game_desc = gameInfo.getInfo().getGame_desc();
        textviewintroduce.setText(game_desc);
        gamename = gameInfo.getInfo().getName();
    }

    /**
     * 猜你喜欢：网络请求获得的内容
     * @param loveGame
     */
    public void onshowlovegame(LoveGame loveGame) {
        int lovegamesize = loveGame.getInfo().size();
        for (int i = 0; i < lovegamesize; i++) {
            linearLayout = new LinearLayout[i];
            String lovegamename = loveGame.getInfo().get(i).getName();
            textViewname[i].setText(lovegamename);
            String count_dl = loveGame.getInfo().get(i).getCount_dl();
            textViewhowmany[i].setText(count_dl + "人下载");
            String icon = loveGame.getInfo().get(i).getIcon();
            imageLoader.DisplayImage(icon, imageview[i]);
        }
    }

    /**
     * 猜你喜欢
     */
    public void setLinearLayout() {
        for (int i = 0; i < linearLayout.length; i++) {
            linearLayout[i]= new LinearLayout(getActivity());
            linearLayout[i].setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(230, ViewGroup.LayoutParams.MATCH_PARENT));
            layoutParams.setMargins(7,0,0,0);
            linearLayout[i].setLayoutParams(layoutParams);
                imageview[i] = new ImageView(getActivity());
                imageview[i].setLayoutParams(new ViewGroup.LayoutParams(220, 220));
                linearLayout[i].addView(imageview[i]);
                textViewname[i] = new TextView(getActivity());
                textViewname[i].setLayoutParams(new ViewGroup.LayoutParams(220, ViewGroup.LayoutParams.WRAP_CONTENT));
                textViewname[i].setTextSize(12);
                textViewname[i].setSingleLine(true);
                textViewname[i].setEllipsize(TextUtils.TruncateAt.END);
                linearLayout[i].addView(textViewname[i]);
                textViewhowmany[i] = new TextView(getActivity());
                textViewhowmany[i].setLayoutParams(new ViewGroup.LayoutParams(220, ViewGroup.LayoutParams.WRAP_CONTENT));
                textViewhowmany[i].setTextSize(12);
                textViewhowmany[i].setSingleLine(true);
                textViewhowmany[i].setEllipsize(TextUtils.TruncateAt.END);
                linearLayout[i].addView(textViewhowmany[i]);
                firstlinearLayout.addView(linearLayout[i]);
        }
    }
    private void getContent() {
        ProfitHttpUtils.requestGamesInfo(gameId, new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                Gson gson = new Gson();
                GameInfo info = gson.fromJson(result.toString(), GameInfo.class);
                onshowcontent(info);
            }

            @Override
            public void error(String msg) {

            }
        });
        ProfitHttpUtils.requestloveGameslist(new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                Gson gson = new Gson();
                LoveGame loveGame = gson.fromJson(result.toString(), LoveGame.class);
                setLinearLayout();
                onshowlovegame(loveGame);
            }

            @Override
            public void error(String msg) {

            }
        });
    }

    /**
     * 游戏详情页面第二个viewpage
     */
    public void Gameinfofragment(){
       FragmentManager fragmentManager = getFragmentManager();
       FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       Bundle b = new Bundle();
       b.putString("gamename",gamename);
       new ProfitGamegiftFragmentViewPagertwo().setArguments(b);
   }
}
