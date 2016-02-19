package com.qianfeng.zhushou.other.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.active.ui.ActiveFragment;
import com.qianfeng.zhushou.exchange.ui.ExchangeFragment;
import com.qianfeng.zhushou.gift.ui.GiftFragment;
import com.qianfeng.zhushou.my.ui.MyFragment;
import com.qianfeng.zhushou.profit.ui.ProfitFragment;

/**
 * 主页面
 * <p/>
 * Created by Liu Jianping
 *
 * @date : 16/1/11.
 */
public class HomeActivity extends FragmentActivity {
    private Fragment[] fragments;

    private FrameLayout flContent;

    private RadioGroup radioGroup;



    private View checkButton;
    private int index;

    private int checkIndex;

    private RadioGroup.OnCheckedChangeListener changeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            View radioButton = findViewById(checkedId);
            //放大当前选中的
            zoomBig(radioButton);

            if (radioButton == checkButton) {
                return;
            }
            //缩小上一次选中的
            zoomSmall(checkButton);

            //保存当前选中的RadioButton
            checkButton = radioButton;

            index = 0;
            switch (checkedId) {
                case R.id.home_profit_rb:
                    index = 0;

                    break;
                case R.id.home_gift_rb:
                    index = 1;
                    break;
                case R.id.home_active_rb:
                    index = 2;
                    break;
                case R.id.home_exchange_rb:
                    index = 3;
                    break;
                case R.id.home_my_rb:
                    index = 4;
                    break;
                default:
                    index = 0;
                    break;
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            transaction.show(fragments[index]);
            transaction.hide(fragments[checkIndex]);
            transaction.commit();
            checkIndex = index;
        }
    };


    /**
     * 执行放大动画
     *
     * @param view 要执行动画的view
     */
    private void zoomBig(View view) {
        Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.anim_rb_zoom_big);
        view.startAnimation(animation);
    }

    /**
     * 执行缩小动画
     *
     * @param view 要执行动画的view
     */
    private void zoomSmall(View view) {
        Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.anim_rb_zoom_small);
        view.startAnimation(animation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        // 设置监听
        radioGroup = (RadioGroup) findViewById(R.id.home_rg);
        radioGroup.setOnCheckedChangeListener(changeListener);

        //从RadioGroup获取第一个RadioButton
        View firstChild = radioGroup.getChildAt(0);
       // View thirdChild = radioGroup.getChildAt(3);


        fragments = new Fragment[]{new ProfitFragment(), new GiftFragment(),
                new ActiveFragment(), new ExchangeFragment(), new MyFragment()};
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        flContent = (FrameLayout) findViewById(R.id.home_content_fl);

        for (int i = 0; i < fragments.length; i++) {
            Fragment fragment = fragments[i];
            transaction.add(R.id.home_content_fl, fragment);
            transaction.hide(fragment);

        }
//        Intent intent = getIntent();
//        int page = intent.getIntExtra("page", 0);
//        if(page==3){
//            transaction.show(fragments[page]);
//            transaction.commit();
//            checkButton = thirdChild;
//            thirdChild.performClick();
//        }

            transaction.show(fragments[0]);
            transaction.commit();
            checkButton = firstChild;
            //让第一个执行一下点击事件
            firstChild.performClick();

    }
}
