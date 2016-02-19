package com.qianfeng.zhushou.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.qianfeng.zhushou.other.utils.LogUtil;

/**
 * Created by Liu Jianping
 *
 * @date : 16/1/15.
 */
public class LoadMoreListView extends ListView
{
    private float lastY;

    private LoadMoreListener loadMoreListener;

    public LoadMoreListView(Context context)
    {
        super(context);
        initScrollListener();
    }

    public LoadMoreListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initScrollListener();

    }

    private void initScrollListener()
    {
        setOnScrollListener(new AbsListView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(AbsListView parentView,
                    int scrollState)
            {
                // 如果状态不是停止的那么不作下面的操作了
                if (scrollState != SCROLL_STATE_IDLE)
                {
                    return;
                }

                // 获取listView中item总个数
                int count = parentView.getCount();

                int lastVisiblePosition = parentView.getLastVisiblePosition();

                int childCount = parentView.getChildCount();

                // 如果当前可见的最后一项的position等于最后一个item 的position，表示显示的是最后一项
                if (lastVisiblePosition == count - 1)
                {

                    View lastChild = parentView.getChildAt(childCount - 1);

                    float y = lastChild.getY();
                    LogUtil.w("tag", "显示的是最后一项 y = " + y);

                    // 我们自己定义一个滑到底的规则
                    // 如果第一次随意停下来的位置和第二次随意停下来的位置相同,表示已经滑到底了，

                    if (lastY == y)
                    {
                        LogUtil.e("tag", "滑到底了 y = " + y);

                        if (loadMoreListener != null)
                        {
                            loadMoreListener.onLoadMore();
                        }
                    }
                    else
                    {
                        lastY = y;
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                    int visibleItemCount, int totalItemCount)
            {
            }
        });
    }

    /**
     * 设置一个加载更多的监听
     * 
     * @param listener
     *            加载更多的监听
     */
    public void setLoadMoreListener(LoadMoreListener listener)
    {
        loadMoreListener = listener;
    }

    /**
     * 加载更多的监听器
     */
    public interface LoadMoreListener
    {
        /**
         * 加载更多回调
         */
        void onLoadMore();
    }
}
