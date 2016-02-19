package com.qianfeng.zhushou.exchange.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.exchange.adapter.ExchangeAdapter;
import com.qianfeng.zhushou.other.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/1.
 */
public class ExchangeFragment extends BaseFragment {
    private TextView tvLeft,tvRight;
    private ViewPager vp;
    private List<Fragment> list;
    ExchangeAdapter exchangeAdapter;
    private View.OnClickListener textviewlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int index=0;
            switch (v.getId()){
                case R.id.exchange_left_tv:
                    index=0;
                    break;
                case R.id.exchange_right_tv:
                    index=1;
                    break;
            }
            vp.setCurrentItem(index);
            boolean ischecked= index==0?true:false;
            tvLeft.setSelected(ischecked);
            tvRight.setSelected(!ischecked);


        }
    };

   private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
       @Override
       public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


       }

       @Override
       public void onPageSelected(int position) {
        boolean ischecked =position==0?true:false;
           tvLeft.setSelected(ischecked);
           tvRight.setSelected(!ischecked);

       }

       @Override
       public void onPageScrollStateChanged(int state) {

       }
   };
    @Override
    protected int getLayout() {
        return R.layout.fragment_exchange_viewpage;
    }

    @Override
    protected void initViews() {
        tvLeft = (TextView) root.findViewById(R.id.exchange_left_tv);
        tvRight = (TextView) root.findViewById(R.id.exchange_right_tv);
        vp = (ViewPager) root.findViewById(R.id.fragment_exchange_vp);
        tvLeft.setSelected(true);

    }

    @Override
    protected void initEvents() {
        tvLeft.setOnClickListener(textviewlistener);
        tvRight.setOnClickListener(textviewlistener);
        vp.setOnPageChangeListener(pageChangeListener);

    }

    @Override
    protected void initData() {
        ExchangeListFragment newfragment = new ExchangeListFragment(1);
        ExchangeListFragment hotFragment = new ExchangeListFragment(2);
        list = new ArrayList<>();
        list.add(newfragment);
        list.add(hotFragment);

        exchangeAdapter = new ExchangeAdapter(getFragmentManager(),list);
        vp.setAdapter(exchangeAdapter);


    }
}
