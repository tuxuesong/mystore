package com.qianfeng.zhushou.active.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Activelist{


    /**
     * total : 5
     * pagesize : 10
     * page : 1
     * page_total : 1
     */

    private PageEntity page;
    /**
     * info : [{"id":"52","aname":"获奖名单","shortname":"新游《攻城掠地》火热上线 ","hotpic":"http://i3.72g.com/upload/201512/201512101040321263.jpg","astime":"2015-12-10 10:00","aetime":"2015-12-16 10:00","total_join_user":41,"status":"已结束"},{"id":"51","aname":"获奖名单","shortname":"《火影忍者》手游终极内测格斗重燃","hotpic":"http://i3.72g.com/upload/201512/201512071000145673.jpg","astime":"2015-12-07 09:00","aetime":"2015-12-13 09:00","total_join_user":28,"status":"已结束"},{"id":"50","aname":"活动奖品发放通知","shortname":"获奖名单","hotpic":"http://i3.72g.com/upload/201512/201512031757259436.jpg","astime":"2015-12-03 17:00","aetime":"2015-12-20 17:00","total_join_user":63,"status":"已结束"},{"id":"48","aname":"新游《攻城掠地》火热上线 ","shortname":"试玩就送2000U币","hotpic":"http://i3.72g.com/upload/201512/201512041832549964.jpg","astime":"2015-12-03 11:00","aetime":"2015-12-09 23:59","total_join_user":54,"status":"已结束"},{"id":"49","aname":"72G游戏助手新版更新公告","shortname":"合并公告","hotpic":"http://i3.72g.com/upload/201512/201512041833134086.jpg","astime":"2015-12-03 11:00","aetime":"2015-12-20 11:00","total_join_user":995,"status":"已结束"}]
     * page : {"total":5,"pagesize":10,"page":1,"page_total":1}
     * state : success
     */

    private String state;
    /**
     * id : 52
     * aname : 获奖名单
     * shortname : 新游《攻城掠地》火热上线
     * hotpic : http://i3.72g.com/upload/201512/201512101040321263.jpg
     * astime : 2015-12-10 10:00
     * aetime : 2015-12-16 10:00
     * total_join_user : 41
     * status : 已结束
     */

    private List<InfoEntity> info;

    public static Activelist objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Activelist.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Activelist> arrayActivelistFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Activelist>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public void setPage(PageEntity page) {
        this.page = page;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setInfo(List<InfoEntity> info) {
        this.info = info;
    }

    public PageEntity getPage() {
        return page;
    }

    public String getState() {
        return state;
    }

    public List<InfoEntity> getInfo() {
        return info;
    }

    public static class PageEntity {
        private int total;
        private int pagesize;
        private int page;
        private int page_total;

        public static PageEntity objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), PageEntity.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<PageEntity> arrayPageEntityFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<PageEntity>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public void setTotal(int total) {
            this.total = total;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public void setPage_total(int page_total) {
            this.page_total = page_total;
        }

        public int getTotal() {
            return total;
        }

        public int getPagesize() {
            return pagesize;
        }

        public int getPage() {
            return page;
        }

        public int getPage_total() {
            return page_total;
        }
    }

    public static class InfoEntity {
        private String id;
        private String aname;
        private String shortname;
        private String hotpic;
        private String astime;
        private String aetime;
        private int total_join_user;
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

        public void setAstime(String astime) {
            this.astime = astime;
        }

        public void setAetime(String aetime) {
            this.aetime = aetime;
        }

        public void setTotal_join_user(int total_join_user) {
            this.total_join_user = total_join_user;
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

        public String getAstime() {
            return astime;
        }

        public String getAetime() {
            return aetime;
        }

        public int getTotal_join_user() {
            return total_join_user;
        }

        public String getStatus() {
            return status;
        }
    }
}
