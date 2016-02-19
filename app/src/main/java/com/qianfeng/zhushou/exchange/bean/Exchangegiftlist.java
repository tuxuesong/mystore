package com.qianfeng.zhushou.exchange.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Exchangegiftlist {


    /**
     * id : 363
     * name : 30元Q币直充
     * consume : 30600
     * icon : http://i2.265g.com/images/201503/201503251056401698.jpg
     * remain : 97
     */

    private InfoEntity info;
    /**
     * info : {"id":"363","name":"30元Q币直充","consume":"30600","icon":"http://i2.265g.com/images/201503/201503251056401698.jpg","remain":"97"}
     * page : null
     * state : success
     */

    private Object page;
    private String state;

    public static Exchangegiftlist objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Exchangegiftlist.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Exchangegiftlist> arrayExchangegiftlistFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Exchangegiftlist>>() {
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
        private String consume;
        private String icon;
        private String remain;

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

        public void setConsume(String consume) {
            this.consume = consume;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setRemain(String remain) {
            this.remain = remain;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getConsume() {
            return consume;
        }

        public String getIcon() {
            return icon;
        }

        public String getRemain() {
            return remain;
        }
    }
}
