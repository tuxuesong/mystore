package com.qianfeng.zhushou.exchange.utils;

import com.qianfeng.zhushou.other.utils.ZhuShouHttpUtil;
import com.qianfeng.zhushou.other.utils.ZhuShouTask;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/1.
 */
public class ExchangeHttpUtils {
    public static final String url = "http://www.yuu1.com/app_api/prize_list/";
    public static final String path = "http://www.yuu1.com/app_api/prize_info/";

    public static void requestExchange(final int type, final int page, ZhuShouTask.RequestCallback callback) {
        ZhuShouTask.Request request = new ZhuShouTask.Request() {
            @Override
            public Object doRequest() {
                Map<String, String> params = new HashMap<>();
                params.put("type", type + "");
                params.put("page", page + "");
                return ZhuShouHttpUtil.doPost(url, params);
            }
        };

        ZhuShouTask task = new ZhuShouTask(request, callback);
        task.execute();
    }

    public static void requestgameinfo(final String id, ZhuShouTask.RequestCallback callback) {
        ZhuShouTask.Request request = new ZhuShouTask.Request() {
            @Override
            public Object doRequest() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return ZhuShouHttpUtil.doPost(path, params);
            }
        };
        ZhuShouTask task = new ZhuShouTask(request, callback);
        task.execute();
    }
}

