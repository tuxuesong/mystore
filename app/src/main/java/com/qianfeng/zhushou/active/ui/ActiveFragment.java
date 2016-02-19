package com.qianfeng.zhushou.active.ui;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.active.adapter.ActialPageradapter;
import com.qianfeng.zhushou.active.adapter.MyFragmentPagerAdapter;
import com.qianfeng.zhushou.active.bean.Image;
import com.qianfeng.zhushou.active.utils.RequestImage;
import com.qianfeng.zhushou.other.ui.BaseFragment;
import com.qianfeng.zhushou.other.utils.LogUtil;
import com.qianfeng.zhushou.other.utils.ZhuShouTask;

import java.util.ArrayList;
import java.util.List;

public class ActiveFragment extends BaseFragment {
    private ViewPager vp;
    private List<ImageView> list = new ArrayList<>();
    private ImageView imageView1, imageView2;
    private ViewPager viewPager;
    private Button btnone,btntwo;
    private List<Fragment> fragments = new ArrayList<>();
    private View.OnClickListener btnlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            int index =0;
            switch (id){
                case R.id.active_btnone:
                    index =0;
                    break;
                case R.id.active_btntwo:
                    index=1;
                    default:break;

            }
            vp.setCurrentItem(index);
            boolean ischecked = index== 0?true:false;
            btnone.setSelected(ischecked);
            btntwo.setSelected(!ischecked);

        }
    };
    private ViewPager.OnPageChangeListener pagelistener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


        }

        @Override
        public void onPageSelected(int position) {
        boolean ischecked = position==0?true:false;
            btnone.setSelected(ischecked);
            btntwo.setSelected(!ischecked);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    public void showimage() {
        RequestImage.requestloadimage(new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                Gson gson = new Gson();
                Image image = gson.fromJson(result.toString(), Image.class);
                String pic1 = image.getInfo().get(1).getBimg();
                String pic2 = image.getInfo().get(0).getBimg();
                LogUtil.w("---pic1图片地址--", pic1);
                LogUtil.w("---pic2图片地址--", pic2);
                RequestImage.requestdownloadimage(pic1, new ZhuShouTask.RequestCallback() {
                    @Override
                    public void success(Object result) {
                        Bitmap bitmap = (Bitmap) result;
                        imageView1 = new ImageView(getActivity());
                        imageView1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
                        imageView1.setImageBitmap(bitmap);
                        list.add(imageView1);
                        LogUtil.w("---list", list.size() + "");
                        LogUtil.w("---bitmap", bitmap.toString());

                    }

                    @Override
                    public void error(String msg) {

                    }
                });
                RequestImage.requestdownloadimage(pic2, new ZhuShouTask.RequestCallback() {
                    @Override
                    public void success(Object result) {
                        Bitmap bitmap2 = (Bitmap) result;
                        imageView2 = new ImageView(getActivity());
                        imageView2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
                        imageView2.setImageBitmap(bitmap2);
                        list.add(imageView2);
                        ActialPageradapter adapter = new ActialPageradapter(list, getActivity());
                        viewPager.setAdapter(adapter);
                        LogUtil.w("---list", list.size() + "");
                        LogUtil.w("---bitmap", bitmap2.toString());
                    }

                    @Override
                    public void error(String msg) {

                    }
                });

            }

            @Override
            public void error(String msg) {
            }
        });
    }

    public void onshowfragment(){
        FragmentManager fragmentManager =getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ActiveFragmentList listfragment = new ActiveFragmentList();
        transaction.commit();
        fragments.add(listfragment);

        FragmentManager fragmentManagertwo = getFragmentManager();
        FragmentTransaction transactiontwo = fragmentManagertwo.beginTransaction();
        ActiveFragmentalready activeFragmentalready = new ActiveFragmentalready();
        transactiontwo.commit();
        fragments.add(activeFragmentalready);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(fragmentManager,fragments);
        vp.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_active;
    }

    @Override
    protected void initViews() {
        viewPager = (ViewPager)root.findViewById(R.id.active_iv);
        vp = (ViewPager)root.findViewById(R.id.active_vp);
        btnone = (Button) root.findViewById(R.id.active_btnone);
        btntwo = (Button) root.findViewById(R.id.active_btntwo);
        btnone.setSelected(true);
    }

    @Override
    protected void initEvents() {
        vp.setOnPageChangeListener(pagelistener);
        btnone.setOnClickListener(btnlistener);
        btntwo.setOnClickListener(btnlistener);
    }

    @Override
    protected void initData() {
        showimage();
        onshowfragment();
    }
}
