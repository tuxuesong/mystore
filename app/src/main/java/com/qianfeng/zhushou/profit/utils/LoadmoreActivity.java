package com.qianfeng.zhushou.profit.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.qianfeng.zhushou.other.utils.LogUtil;

public class LoadmoreActivity extends ListView {
    private float lasty;
    private LoadMoreListener loadMoreListener;

    public LoadmoreActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        initScrollListener();
    }

    public LoadmoreActivity(Context context) {
        super(context);
        initScrollListener();
    }

    private void initScrollListener() {
        setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 如果状态不是停止的那么不作下面的操作了
                if (scrollState != SCROLL_STATE_IDLE) {
                    return;
                }
                // 获取listView中item总个数
                int count = view.getCount();
                //当前可见的最后一项
                int lastVisiblePosition = view.getLastVisiblePosition();

                int childCount = view.getChildCount();
                // 如果当前可见的最后一项的position等于最后一个item 的position，表示显示的是最后一项
                if (lastVisiblePosition == count - 1) {
                    //item中最后一个子控件
                    View lastchild = view.getChildAt(childCount-1);

                    float y = lastchild.getY();
                    LogUtil.w("tag", "显示的是最后一项 y = " + y);

                    if (lasty == y) {
                        LogUtil.e("tag", "滑到底了 y = " + y);
                        if(loadMoreListener != null){
                            loadMoreListener.loadmorelistener();
                        }
                    }
                    else{
                        lasty = y;
                    }
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }




    public void setLoadMoreListener(LoadMoreListener loadMoreListener){
        this.loadMoreListener = loadMoreListener;
    }
    public interface LoadMoreListener {
        void loadmorelistener();
    }
}
