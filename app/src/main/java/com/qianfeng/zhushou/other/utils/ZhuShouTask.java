package com.qianfeng.zhushou.other.utils;

import android.os.AsyncTask;

/**
 * Created by Liu Jianping
 *
 * @date : 16/1/13.
 */
public class ZhuShouTask extends AsyncTask<Void, Integer, Object>
{
    private Request request;

    private RequestCallback callback;


    public ZhuShouTask(Request request, RequestCallback callback)
    {
        if (request == null || callback == null)
        {
            throw new NullPointerException(
                    "request or call can not be null ...");
        }
        this.request = request;
        this.callback = callback;
    }

    @Override
    protected Object doInBackground(Void... params)
    {
        return request.doRequest();
    }

    @Override
    protected void onPostExecute(Object product)
    {
        // 如果请求的结果为空，表示请求失败了。
        if (product == null)
        {
            callback.error("请求失败!!!");
        }
        else
        {
            callback.success(product);
        }
    }


    /**
     * 请求接口
     */
    public interface Request
    {
        /**
         * 执行请求的方法
         * 
         * @return 返回请求结果
         */
        Object doRequest();
    }

    /**
     * 请求回调接口
     */
    public interface RequestCallback
    {
        /**
         * 请求成功回调方法
         * 
         * @param result
         *            请求成功的结果
         */
        void success(Object result);

        /**
         * 请求失败回调方法
         * 
         * @param msg
         *            请求失败的错误信息
         */
        void error(String msg);
    }

    /**
     * 更新下载进度接口
     */
    public interface UpgradeListener
    {
        /**
         * 显示进度
         * 
         * @param progress
         *            进度百分比
         */
        void upgradeProgress(int progress);
    }

}
