package com.qianfeng.zhushou.active.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Active_infos{

    /**
     * id : 52
     * aname : 获奖名单
     * shortname : 新游《攻城掠地》火热上线
     * hotpic : http://i3.72g.com/upload/201512/201512101040321263.jpg
     * expimg :
     * astime : 2015-12-10 10:00
     * aetime : 2015-12-16 10:00
     * content : 以下是“新游《攻城掠地》火热上线 试玩就送2000U币”活动获奖名单：

     板砖
     丶Li
     仰望那片天
     快乐路口
     六月的雨
     mirliu_20
     zuishaofeng
     zj3551550
     信hu
     6V
     小炮
     撒比
     雪纷飞
     安然乐
     妮妮
     2448443771
     爱神
     yuanmo
     362430
     2970704074
     为你而生
     魅荣
     我爱的小杰杰
     斜月三星洞
     好人好梦
     你是碎嘴子不
     敏敏


     说明：U币奖励已全部发送到所有获奖用户的优易网账户中，请查收哦！
     * isimg : 1
     * writer : 72G纱纱
     * pubdate : 2015-12-10
     * comment_total : 41
     * status : 已结束
     */

    private InfoEntity info;
    /**
     * info : {"id":"52","aname":"获奖名单","shortname":"新游《攻城掠地》火热上线 ","hotpic":"http://i3.72g.com/upload/201512/201512101040321263.jpg","expimg":"","astime":"2015-12-10 10:00","aetime":"2015-12-16 10:00","content":"以下是\u201c新游《攻城掠地》火热上线 试玩就送2000U币\u201d活动获奖名单：\n\n板砖\n丶Li\n仰望那片天\n快乐路口\n六月的雨\nmirliu_20\nzuishaofeng\nzj3551550\n信hu\n6V\n小炮\n撒比\n雪纷飞\n安然乐\n妮妮\n2448443771\n爱神\nyuanmo\n362430\n2970704074\n为你而生\n魅荣\n我爱的小杰杰\n斜月三星洞\n好人好梦\n你是碎嘴子不\n敏敏\n\n\n说明：U币奖励已全部发送到所有获奖用户的优易网账户中，请查收哦！","isimg":"1","writer":"72G纱纱","pubdate":"2015-12-10","comment_total":41,"status":"已结束"}
     * page : null
     * state : success
     */

    private Object page;
    private String state;

    public static Active_infos objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Active_infos.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Active_infos> arrayActive_infosFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Active_infos>>() {
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
        private String aname;
        private String shortname;
        private String hotpic;
        private String expimg;
        private String astime;
        private String aetime;
        private String content;
        private String isimg;
        private String writer;
        private String pubdate;
        private int comment_total;
        private String status;

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

        public void setAname(String aname) {
            this.aname = aname;
        }

        public void setShortname(String shortname) {
            this.shortname = shortname;
        }

        public void setHotpic(String hotpic) {
            this.hotpic = hotpic;
        }

        public void setExpimg(String expimg) {
            this.expimg = expimg;
        }

        public void setAstime(String astime) {
            this.astime = astime;
        }

        public void setAetime(String aetime) {
            this.aetime = aetime;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setIsimg(String isimg) {
            this.isimg = isimg;
        }

        public void setWriter(String writer) {
            this.writer = writer;
        }

        public void setPubdate(String pubdate) {
            this.pubdate = pubdate;
        }

        public void setComment_total(int comment_total) {
            this.comment_total = comment_total;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public String getAname() {
            return aname;
        }

        public String getShortname() {
            return shortname;
        }

        public String getHotpic() {
            return hotpic;
        }

        public String getExpimg() {
            return expimg;
        }

        public String getAstime() {
            return astime;
        }

        public String getAetime() {
            return aetime;
        }

        public String getContent() {
            return content;
        }

        public String getIsimg() {
            return isimg;
        }

        public String getWriter() {
            return writer;
        }

        public String getPubdate() {
            return pubdate;
        }

        public int getComment_total() {
            return comment_total;
        }

        public String getStatus() {
            return status;
        }
    }
}
