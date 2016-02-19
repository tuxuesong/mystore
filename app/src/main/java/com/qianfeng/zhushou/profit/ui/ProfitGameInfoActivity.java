package com.qianfeng.zhushou.profit.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.other.utils.ImageLoader;
import com.qianfeng.zhushou.other.utils.ZhuShouTask;
import com.qianfeng.zhushou.profit.adapter.ProfitGameinfoMyViewPagerFragmentOne;
import com.qianfeng.zhushou.profit.bean.GameInfo;
import com.qianfeng.zhushou.profit.utils.ProfitHttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/2.
 */
public class ProfitGameInfoActivity extends FragmentActivity {
    private TextView gamenametv, gameversiontv, gamesizetv, gamesuser;
    private Button gameinfogamebtn, gamegiftbtn, gamepingbtn, gamecommitbtn, gamestartbtn;
    private ViewPager vp;
    private RatingBar ratingBar;
    private ImageView imageViewleft, imageViewbig, imageback, imageshare;
    private ImageLoader imageLoader = new ImageLoader(this);
    private List<String> list = new ArrayList<>();
    private List<Fragment> listfragments = new ArrayList<>();
    private String gameId, gamename;
    private PopupWindow popupWindow;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.profit_back_iv:
                    finish();
                    break;
                case R.id.profit_share_iv:
                    if (popupWindow == null) {
                        initPopuWindow();
                    }
                    /**
                     * 显示窗口
                     */
                    popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
                    break;
            }

        }
    };

    public void initPopuWindow() {
        /**
         * 创建一个popuwindow
         */
        View view = getLayoutInflater().inflate(R.layout.pop_share, (ViewGroup) getWindow().getDecorView(), false);
        /**
         * 设置window的宽高
         */
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT
                , true);
        /**
         * 设置背景，点击背景，window消失
         */
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        /**
         *
         设置window进入方式
         */
        popupWindow.setAnimationStyle(R.style.share_pop_anim);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profitinfo);
        initViews();
        initData();
        addgameinfofragments();
    }

    protected void initViews() {
        gamenametv = (TextView) findViewById(R.id.profit_gamename_tv);
        gameversiontv = (TextView) findViewById(R.id.profit_version_tv);
        gamesizetv = (TextView) findViewById(R.id.profitInfo_size_tv);
        gamesuser = (TextView) findViewById(R.id.profit_howmanydown_tv);
        imageViewleft = (ImageView) findViewById(R.id.profit_small_left_iv);
        imageViewbig = (ImageView) findViewById(R.id.profit_type_viewpage);
        ratingBar = (RatingBar) findViewById(R.id.profit_ratingbar);
        vp = (ViewPager) findViewById(R.id.profit_type_vp);
        gameinfogamebtn = (Button) findViewById(R.id.profit_gameinfo_btn);
        gamegiftbtn = (Button) findViewById(R.id.profit_gamegift_btn);
        gamepingbtn = (Button) findViewById(R.id.profit_gameping_btn);
        gamecommitbtn = (Button) findViewById(R.id.profit_commit_btn);
        gamestartbtn = (Button) findViewById(R.id.profit_start_btn);
        imageback = (ImageView) findViewById(R.id.profit_back_iv);
        imageshare = (ImageView) findViewById(R.id.profit_share_iv);
        imageshare.setOnClickListener(onClickListener);
        imageback.setOnClickListener(onClickListener);
    }

    /**
     * 下载完数据并展示出来
     *
     * @param gameInfo
     */
    public void onshowContent(GameInfo gameInfo) {
        String gamescore = gameInfo.getInfo().getScore();
        // ratingBar.setRating(Float.valueOf(gamescore));

        String gameversion = gameInfo.getInfo().getVersion();
        gameversiontv.setText("版本：" + gameversion);

        String gameIcon = gameInfo.getInfo().getIcon();
        imageLoader.DisplayImage(gameIcon, imageViewleft);

        String gamesize = gameInfo.getInfo().getSize();
        gamesizetv.setText(gamesize);

        String gameHowmany = gameInfo.getInfo().getCount_dl();
        gamesuser.setText(gameHowmany + "下载");

        gamename = gameInfo.getInfo().getName();
        gamenametv.setText(gamename);

        String gameSnap = gameInfo.getInfo().getSnapshot();
        String picOne = gameSnap.substring(0, 54);
        //  String picTwo = gameSnap.substring(55, 109);
        // String picThree = gameSnap.substring(110, 164);
        // String picFour = gameSnap.substring(165, 219);
        // String picFive = gameSnap.substring(220);
        list.add(picOne);
        // list.add(picTwo);
        //list.add(picThree);
        //list.add(picFour);
        //list.add(picFive);

        for (int i = 0; i < list.size(); i++) {
            imageLoader.DisplayImage(list.get(i), imageViewbig);
        }
        // String gameapk = gameInfo.getInfo().getAndroid_dl();

    }

    protected void initData() {
        /**
         * 通过游戏列表页面传过来的id，游戏详情页面得到id去下载
         */
        gameId = getIntent().getStringExtra("profitId");
        ProfitHttpUtils.requestGamesInfo(gameId, new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                Gson gson = new Gson();
                GameInfo info = gson.fromJson(result.toString(), GameInfo.class);
                onshowContent(info);
            }

            @Override
            public void error(String msg) {

            }
        });
    }

    /**
     * 添加三个fragment,并加到list集合中，游戏信息
     */
    public void addgameinfofragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        //得到事务管理器
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //实例化fragment
        ProfitGameInfoFragmentViewpagerone profitGameInfoFragmentViewpagerone = new ProfitGameInfoFragmentViewpagerone();
        Bundle b = new Bundle();
        b.putString("gameid", gameId);

        profitGameInfoFragmentViewpagerone.setArguments(b);

        fragmentTransaction.commit();
        listfragments.add(profitGameInfoFragmentViewpagerone);
        addgamegiftfragments();
        ProfitGameinfoMyViewPagerFragmentOne adapter = new ProfitGameinfoMyViewPagerFragmentOne(fragmentManager, listfragments);
        vp.setAdapter(adapter);
    }

    /*
    * 游戏礼包
    * */
    public void addgamegiftfragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        //得到事务管理器
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ProfitGamegiftFragmentViewPagertwo profitGamegiftFragmentViewPagertwo = new ProfitGamegiftFragmentViewPagertwo();
        fragmentTransaction.commit();
        listfragments.add(profitGamegiftFragmentViewPagertwo);
    }
}
