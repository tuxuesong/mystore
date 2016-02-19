package com.qianfeng.zhushou.exchange.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/2/1.
 */
public class ExchangeAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public ExchangeAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fragments = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position) ;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
