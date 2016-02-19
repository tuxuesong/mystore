package com.qianfeng.zhushou.gift.ui;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.gift.adapter.GiftPagerAdapter;
import com.qianfeng.zhushou.other.ui.BaseFragment;

/**
 * 礼包页面
 *
 * Created by Liu Jianping
 *
 * @date : 16/1/12.
 */
public class GiftFragment extends BaseFragment
{

    private ViewPager vpContent;
    private GiftPagerAdapter pagerAdapter;

    private TextView tvMobile, tvWeb;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            int index = 0;
            //如果点击的是手游礼包
            if (id == R.id.gift_type_mobile_tv)
            {
                index = 0;
            }
            //如果点击的是页游礼包
            else if (id == R.id.gift_type_web_tv)
            {
                index = 1;
            }

            vpContent.setCurrentItem(index);
            boolean isCheckedMobile = index == 0 ? true : false;

            tvMobile.setSelected(isCheckedMobile);
            tvWeb.setSelected(!isCheckedMobile);
        }
    };

    private ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            boolean isCheckedMobile = position == 0 ? true : false;

            tvMobile.setSelected(isCheckedMobile);
            tvWeb.setSelected(!isCheckedMobile);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected int getLayout()
    {
        return R.layout.fragment_gift;
    }

    @Override
    protected void initViews()
    {
        vpContent = (ViewPager) root.findViewById(R.id.gift_type_vp);
        tvMobile = (TextView) root.findViewById(R.id.gift_type_mobile_tv);
        tvWeb = (TextView) root.findViewById(R.id.gift_type_web_tv);
        tvMobile.setSelected(true);
    }

    @Override
    protected void initEvents()
    {
        tvMobile.setOnClickListener(onClickListener);
        tvWeb.setOnClickListener(onClickListener);
        vpContent.setOnPageChangeListener(changeListener);
    }

    @Override
    protected void initData()
    {

        GiftListFragment mobileListFragment = new GiftListFragment(1);
        GiftListFragment webListFragment = new GiftListFragment(2);
        List<Fragment> list = new ArrayList<>();
        list.add(mobileListFragment);
        list.add(webListFragment);
        pagerAdapter = new GiftPagerAdapter(getFragmentManager(), list);

        vpContent.setAdapter(pagerAdapter);

    }
}
