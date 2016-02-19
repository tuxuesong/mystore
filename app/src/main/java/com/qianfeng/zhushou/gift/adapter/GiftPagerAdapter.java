package com.qianfeng.zhushou.gift.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 礼包页面下面ViewPager 适配器
 * 
 * Created by Liu Jianping
 *
 * @date : 16/1/12.
 */
public class GiftPagerAdapter extends FragmentPagerAdapter
{
    private List<Fragment> fragments;

    public GiftPagerAdapter(FragmentManager fm, List<Fragment> list)
    {
        super(fm);
        fragments = list;
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }
}
