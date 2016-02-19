package com.qianfeng.zhushou.active.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/2.
 */
public class Image {

    /**
     * info : [{"id":"27","bname":"《航海归来》盛大公测 环球探险即刻起航","target":"1","target_id":"781","bimg":"http://i3.72g.com/upload/201601/201601191023052738.jpg","px":"0"},{"id":"25","bname":"72G游戏助手新版上线","target":"3","target_id":"49","bimg":"http://i3.72g.com/upload/201512/201512041834559608.jpg","px":"1"}]
     * page : null
     * state : success
     */

    private Object page;
    private String state;
    /**
     * id : 27
     * bname : 《航海归来》盛大公测 环球探险即刻起航
     * target : 1
     * target_id : 781
     * bimg : http://i3.72g.com/upload/201601/201601191023052738.jpg
     * px : 0
     */

    private List<InfoEntity> info;

    public static Image objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Image.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Image> arrayImageFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Image>>() {
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
        private String bimg;

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

        public void setBimg(String bimg) {
            this.bimg = bimg;
        }

        public String getBimg() {
            return bimg;
        }
    }
}
