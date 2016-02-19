package com.qianfeng.zhushou.profit.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GameInfo {


    /**
     * id : 789
     * name : 御剑飞仙
     * score : 7.8
     * version : 1.0.101
     * icon : http://i3.72g.com/upload/201601/201601291631591381.png
     * snapshot : http://i3.72g.com/upload/201601/201601291631379577.png,http://i3.72g.com/upload/201601/201601291631371612.png,http://i3.72g.com/upload/201601/201601291631381365.png,http://i3.72g.com/upload/201601/201601291631391296.png,http://i3.72g.com/upload/201601/201601291631407363.png
     * size : 130MB
     * dl_back_jifen : 300
     * android_dl : http://market-download.kkk5.com/yjfx/journey_0_6321.apk
     * ios_dl : null
     * count_dl : 3454
     * game_desc : 《御剑飞仙》是东方玄幻飞仙回合手游，以独具神韵的时尚画风，开启回合RPG的御剑修仙新时代：飞仙坐骑、上古神翼，打造个性化飞行新体验！悟空，天蓬等数十位强力伙伴加盟助战，百种阵型搭配，突破策略极限激发最强战力！多样真人组队副本，昔日战友正等你一同捉鬼封妖！真实寄售摆摊，珍贵材料无绑定寄售，玩游戏也能赚钱！
     * swxx :
     * game_task_state : 1
     * limit_number : 0
     */

    private InfoEntity info;
    /**
     * info : {"id":"789","name":"御剑飞仙","score":"7.8","version":"1.0.101","icon":"http://i3.72g.com/upload/201601/201601291631591381.png","snapshot":"http://i3.72g.com/upload/201601/201601291631379577.png,http://i3.72g.com/upload/201601/201601291631371612.png,http://i3.72g.com/upload/201601/201601291631381365.png,http://i3.72g.com/upload/201601/201601291631391296.png,http://i3.72g.com/upload/201601/201601291631407363.png","size":"130MB","dl_back_jifen":"300","android_dl":"http://market-download.kkk5.com/yjfx/journey_0_6321.apk","ios_dl":null,"count_dl":"3454","game_desc":"《御剑飞仙》是东方玄幻飞仙回合手游，以独具神韵的时尚画风，开启回合RPG的御剑修仙新时代：飞仙坐骑、上古神翼，打造个性化飞行新体验！悟空，天蓬等数十位强力伙伴加盟助战，百种阵型搭配，突破策略极限激发最强战力！多样真人组队副本，昔日战友正等你一同捉鬼封妖！真实寄售摆摊，珍贵材料无绑定寄售，玩游戏也能赚钱！","swxx":"","game_task_state":"1","limit_number":"0"}
     * page : null
     * state : success
     */

    private Object page;
    private String state;

    public static GameInfo objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), GameInfo.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<GameInfo> arrayGameInfoFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<GameInfo>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public void setInfo(InfoEntity info) {
        this.info = info;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public void setState(String state) {
        this.state = state;
    }

    public InfoEntity getInfo() {
        return info;
    }

    public Object getPage() {
        return page;
    }

    public String getState() {
        return state;
    }

    public static class InfoEntity {
        private String id;
        private String name;
        private String score;
        private String version;
        private String icon;
        private String snapshot;
        private String size;
        private String dl_back_jifen;
        private String android_dl;
        private Object ios_dl;
        private String count_dl;
        private String game_desc;
        private String swxx;
        private String game_task_state;
        private String limit_number;

        public static InfoEntity objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), InfoEntity.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<InfoEntity> arrayInfoEntityFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<InfoEntity>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setSnapshot(String snapshot) {
            this.snapshot = snapshot;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public void setDl_back_jifen(String dl_back_jifen) {
            this.dl_back_jifen = dl_back_jifen;
        }

        public void setAndroid_dl(String android_dl) {
            this.android_dl = android_dl;
        }

        public void setIos_dl(Object ios_dl) {
            this.ios_dl = ios_dl;
        }

        public void setCount_dl(String count_dl) {
            this.count_dl = count_dl;
        }

        public void setGame_desc(String game_desc) {
            this.game_desc = game_desc;
        }

        public void setSwxx(String swxx) {
            this.swxx = swxx;
        }

        public void setGame_task_state(String game_task_state) {
            this.game_task_state = game_task_state;
        }

        public void setLimit_number(String limit_number) {
            this.limit_number = limit_number;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getScore() {
            return score;
        }

        public String getVersion() {
            return version;
        }

        public String getIcon() {
            return icon;
        }

        public String getSnapshot() {
            return snapshot;
        }

        public String getSize() {
            return size;
        }

        public String getDl_back_jifen() {
            return dl_back_jifen;
        }

        public String getAndroid_dl() {
            return android_dl;
        }

        public Object getIos_dl() {
            return ios_dl;
        }

        public String getCount_dl() {
            return count_dl;
        }

        public String getGame_desc() {
            return game_desc;
        }

        public String getSwxx() {
            return swxx;
        }

        public String getGame_task_state() {
            return game_task_state;
        }

        public String getLimit_number() {
            return limit_number;
        }
    }
}
