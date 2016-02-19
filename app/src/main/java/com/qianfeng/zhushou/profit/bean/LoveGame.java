package com.qianfeng.zhushou.profit.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LoveGame  {


    /**
     * info : [{"id":"789","name":"御剑飞仙","icon":"http://i3.72g.com/upload/201601/201601291631591381.png","count_dl":"498"},{"id":"787","name":"CF暴击僵尸2015","icon":"http://i3.72g.com/upload/201601/201601291217271989.png","count_dl":"469"},{"id":"786","name":"天天酷跑3D","icon":"http://i3.72g.com/upload/201601/201601281445375898.png","count_dl":"377"},{"id":"784","name":"超神之战","icon":"http://i3.72g.com/upload/201601/201601251433178468.jpg","count_dl":"512"}]
     * page : null
     * state : success
     */

    private Object page;
    private String state;
    /**
     * id : 789
     * name : 御剑飞仙
     * icon : http://i3.72g.com/upload/201601/201601291631591381.png
     * count_dl : 498
     */

    private List<InfoEntity> info;

    public static LoveGame objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), LoveGame.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<LoveGame> arrayLoveGameFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<LoveGame>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public void setPage(Object page) {
        this.page = page;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setInfo(List<InfoEntity> info) {
        this.info = info;
    }

    public Object getPage() {
        return page;
    }

    public String getState() {
        return state;
    }

    public List<InfoEntity> getInfo() {
        return info;
    }

    public static class InfoEntity {
        private String id;
        private String name;
        private String icon;
        private String count_dl;

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

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setCount_dl(String count_dl) {
            this.count_dl = count_dl;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getIcon() {
            return icon;
        }

        public String getCount_dl() {
            return count_dl;
        }
    }
}
