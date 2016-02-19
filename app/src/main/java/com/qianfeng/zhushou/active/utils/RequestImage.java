package com.qianfeng.zhushou.active.utils;

import com.qianfeng.zhushou.other.utils.ZhuShouHttpUtil;
import com.qianfeng.zhushou.other.utils.ZhuShouTask;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/2.
 */

/**
 * 下载json数据
 */
public class RequestImage {
    public static final String url = "http://zhushou.72g.com/app/common/banner_info/";
    public static final String active = "http://zhushou.72g.com/app/activity/activity_list/";
    public static final String activeinfo = "http://zhushou.72g.com/app/activity/activity_info/";
    public static void requestloadimage(ZhuShouTask.RequestCallback callback) {
        ZhuShouTask.Request request = new ZhuShouTask.Request() {
            @Override
            public Object doRequest() {
                Map<String, String> map = new HashMap<>();
                map.put("platform", "2");
                map.put("pos", "1");
                return ZhuShouHttpUtil.doPost(url, map);
            }
        };
        ZhuShouTask task = new ZhuShouTask(request, callback);
        task.execute();
    }
    /**
     * 异步下载图片
     */
    public static void requestdownloadimage(final String strurl, ZhuShouTask.RequestCallback callback) {
        ZhuShouTask.Request request = new ZhuShouTask.Request() {
            @Override
            public Object doRequest() {
                return ZhuShouHttpUtil.downLoadBitmap(strurl);
            }
        };
        ZhuShouTask task = new ZhuShouTask(request, callback);
        task.execute();
    }
    public static void requestActive(ZhuShouTask.RequestCallback callback) {
        ZhuShouTask.Request request = new ZhuShouTask.Request() {
            @Override
            public Object doRequest() {
                return ZhuShouHttpUtil.doGet(active);
            }
        };
        ZhuShouTask task = new ZhuShouTask(request, callback);
        task.execute();
    }
    public static void requestContent(final String id,ZhuShouTask.RequestCallback callback){
        ZhuShouTask.Request request = new ZhuShouTask.Request() {
            @Override
            public Object doRequest() {
                Map<String,String> params = new HashMap<>();
                params.put("id",id);
                return ZhuShouHttpUtil.doPost(activeinfo,params);
            }
        };
        ZhuShouTask task = new ZhuShouTask(request,callback);
        task.execute();
    }
}
