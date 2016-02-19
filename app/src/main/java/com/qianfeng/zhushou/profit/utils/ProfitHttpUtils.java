package com.qianfeng.zhushou.profit.utils;

import com.qianfeng.zhushou.other.utils.ZhuShouHttpUtil;
import com.qianfeng.zhushou.other.utils.ZhuShouTask;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 16-1-28.
 */
public class ProfitHttpUtils {
    public static final String url = "http://zhushou.72g.com/app/game/game_list/";
    private static final String gameinfourl = "http://zhushou.72g.com/app/game/game_info/";
    private static final String loveuri = "http://zhushou.72g.com/app/game/game_like/";
    private static final String checkPhone = "http://www.yuu1.com/app_api/reg_yuu1";
    private static final String nianzhenma = "http://www.yuu1.com/app_api/reg_yuu1";
    /**
     * 请求游戏列表
     */
    public static void requestGameslist(final int page, ZhuShouTask.RequestCallback callback) {
        ZhuShouTask.Request request = new ZhuShouTask.Request() {
            @Override
            public Object doRequest() {
                Map<String, String> map = new HashMap<>();
                map.put("platform", "2");
                map.put("page", page + "");
                return ZhuShouHttpUtil.doPost(url, map);
            }
        };
        ZhuShouTask task = new ZhuShouTask(request, callback);
        task.execute();
    }
    public static void requestGamesinfolist(ZhuShouTask.RequestCallback callback) {
        ZhuShouTask.Request request = new ZhuShouTask.Request() {
            @Override
            public Object doRequest() {
                Map<String, String> map = new HashMap<>();
                map.put("platform", "2");
                return ZhuShouHttpUtil.doPost(url, map);
            }
        };
        ZhuShouTask task = new ZhuShouTask(request, callback);
        task.execute();
    }
    /**
    * 游戏详情请求
    * */
    public static void requestGamesInfo(final String id, ZhuShouTask.RequestCallback callback) {
        ZhuShouTask.Request request = new ZhuShouTask.Request() {
            @Override
            public Object doRequest() {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return ZhuShouHttpUtil.doPost(gameinfourl, params);
            }
        };
        ZhuShouTask task = new ZhuShouTask(request, callback);
        task.execute();
    }
    /**
    * 猜你喜欢请求
    * */
    public static void requestloveGameslist(ZhuShouTask.RequestCallback callback) {
        ZhuShouTask.Request request = new ZhuShouTask.Request() {
            @Override
            public Object doRequest() {
                Map<String, String> map = new HashMap<>();
                map.put("platform", "2");
                return ZhuShouHttpUtil.doPost(loveuri, map);
            }
        };
        ZhuShouTask task = new ZhuShouTask(request, callback);
        task.execute();
    }
    /**
    * 手机号验证
    * */
    public static void checkphonenum(final String op, final String phonenumber, ZhuShouTask.RequestCallback callback) {
        ZhuShouTask.Request request = new ZhuShouTask.Request() {
            @Override
            public Object doRequest() {
                Map<String, String> params = new HashMap<>();
                params.put("op", op);
                params.put("username", phonenumber);
                return ZhuShouHttpUtil.doPost(checkPhone, params);
            }
        };
        ZhuShouTask task = new ZhuShouTask(request, callback);
        task.execute();
    }
    /**
     * 验证码
     */

    public static void checknianzhenma(final String op, final String usernamena, final String nianzhen, final String password, final String username, ZhuShouTask.RequestCallback callback) {
        ZhuShouTask.Request request = new ZhuShouTask.Request() {
            @Override
            public Object doRequest() {
                Map<String, String> params = new HashMap<>();
                params.put("op", op);
                params.put("username", usernamena);
                params.put("code", nianzhen);
                params.put("password", password);
                params.put("nickname", username);
                return ZhuShouHttpUtil.doPost(nianzhenma, params);
            }
        };
        ZhuShouTask task = new ZhuShouTask(request, callback);
        task.execute();
    }
}
