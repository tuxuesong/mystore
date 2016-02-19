package com.qianfeng.zhushou.other.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求版本等接口操作
 * 
 * Created by Liu Jianping
 *
 * @date : 16/1/14.
 */
public class OtherHttpUtil
{

    /**
     * 请求版本url
     */
    public static final String URL_UPGRADE = "http://zhushou.72g.com/app/common/upgrade";

    /**
     * 执行请求版本操作
     * 
     * @param version
     *            版本号
     * @param callback
     *            请求回调
     */
    public static void requestVersion(final String version,
            ZhuShouTask.RequestCallback callback)
    {
        ZhuShouTask.Request request = new ZhuShouTask.Request()
        {
            @Override
            public Object doRequest()
            {
                Map<String, String> params = new HashMap<>();
                params.put("platform", "2");
                params.put("ver", version);
                return ZhuShouHttpUtil.doPost(URL_UPGRADE, params);
            }
        };

        ZhuShouTask task = new ZhuShouTask(request, callback);
        task.execute();
    }

    /**
     * 下载apk
     * 
     * @param url
     *            下载地址
     * @param callback
     *            下载回调
     */
    public static void downLoadApk(final String url,
            ZhuShouTask.RequestCallback callback, final ZhuShouTask.UpgradeListener listener)
    {
        ZhuShouTask.Request request = new ZhuShouTask.Request()
        {
            @Override
            public Object doRequest()
            {
                return ZhuShouHttpUtil.downLoadFile(FileUtil.APK_DIR,
                        "72G.apk", url, listener);
            }
        };

        ZhuShouTask task = new ZhuShouTask(request, callback);

        task.execute();
    }
}
