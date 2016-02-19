package com.qianfeng.zhushou.profit.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/31.
 */
public class Games {


    /**
     * total : 17
     * pagesize : 10
     * page : 1
     * page_total : 2
     */

    private PageEntity page;
    /**
     * info : [{"id":"789","name":"御剑飞仙","score":"7.8","icon":"http://i3.72g.com/upload/201601/201601291631591381.png","size":"130MB","dl_back_jifen":"300","h5src":"","android_dl":"http://market-download.kkk5.com/yjfx/journey_0_6321.apk","ios_dl":"","count_dl":"3504","all_prize":2500},{"id":"787","name":"CF暴击僵尸2015","score":"7.9","icon":"http://i3.72g.com/upload/201601/201601291217271989.png","size":"21.3MB","dl_back_jifen":"200","h5src":"","android_dl":"http://open.play.cn/api/v2/egame/get_efs.json?efs_id=448f87ach2b5ffa7&channel_type=9&channel_code=20032453&file_type=800","ios_dl":"","count_dl":"3869","all_prize":1200},{"id":"786","name":"天天酷跑3D","score":"7.9","icon":"http://i3.72g.com/upload/201601/201601281445375898.png","size":"170.9MB","dl_back_jifen":"300","h5src":"","android_dl":"http://f1.xinapk.com/data/000004280.apk","ios_dl":"","count_dl":"3454","all_prize":2000},{"id":"784","name":"超神之战","score":"8.7","icon":"http://i3.72g.com/upload/201601/201601251433178468.jpg","size":"300MB","dl_back_jifen":"500","h5src":"","android_dl":"http://cszz.dl.cdnunion.com/ApkServer2/apk/超神之战.apk","ios_dl":"http://cszz.dl.cdnunion.com/ApkServer2/ipa/超神之战.ipa","count_dl":"3925","all_prize":3500},{"id":"785","name":"德州扑克","score":"7.8","icon":"http://i3.72g.com/upload/201601/201601281048107065.png","size":"15.4MB","dl_back_jifen":"200","h5src":"","android_dl":"http://down.72g.com/new/yydzpk.apk","ios_dl":"","count_dl":"2746","all_prize":1500},{"id":"783","name":"六龙争霸3D","score":"7.9","icon":"http://i3.72g.com/upload/201601/201601221544028029.png","size":"255.85MB","dl_back_jifen":"300","h5src":"","android_dl":"http://dlied5.myapp.com/myapp/1104472208/ttcz/10003663_LLZB1.1.25jk.apk","ios_dl":"","count_dl":"4955","all_prize":2500},{"id":"221","name":"神魔","score":"7.3","icon":"http://p16.qhimg.com/t0125c185a342b0a353.png ","size":"160.77MB","dl_back_jifen":"300","h5src":"","android_dl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_157925&appid=730542","ios_dl":"","count_dl":"6759","all_prize":3000},{"id":"150","name":"神雕侠侣","score":"7.2","icon":"http://p19.qhimg.com/t015829182b250407bd.png ","size":"189.28MB","dl_back_jifen":"500","h5src":"","android_dl":"http://api.np.mobilem.360.cn/redirect/down/?from=lm_157925&appid=705490","ios_dl":"","count_dl":"5651","all_prize":3000},{"id":"781","name":"航海归来","score":"7.8","icon":"http://i3.72g.com/upload/201601/201601141722177441.png","size":"81.36MB","dl_back_jifen":"200","h5src":"","android_dl":"http://d.19196.com/HANGHAIGUILAI/HANGHAIGUILAI_21114.apk","ios_dl":"","count_dl":"3512","all_prize":700},{"id":"194","name":"九阴真经","score":"8.6","icon":"http://p19.qhimg.com/t01c1d94d1361b3d618.png ","size":"251MB","dl_back_jifen":"500","h5src":"","android_dl":"http://down.72g.com/new/jyzj.apk","ios_dl":"","count_dl":"6094","all_prize":3000}]
     * page : {"total":17,"pagesize":10,"page":1,"page_total":2}
     * state : success
     */

    private String state;
    /**
     * id : 789
     * name : 御剑飞仙
     * score : 7.8
     * icon : http://i3.72g.com/upload/201601/201601291631591381.png
     * size : 130MB
     * dl_back_jifen : 300
     * h5src :
     * android_dl : http://market-download.kkk5.com/yjfx/journey_0_6321.apk
     * ios_dl :
     * count_dl : 3504
     * all_prize : 2500
     */

    private List<InfoEntity> info;

    public static Games objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), Games.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Games> arrayGamesFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<Games>>() {
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
        private String name;
        private String score;
        private String icon;
        private String size;
        private String dl_back_jifen;
        private String h5src;
        private String android_dl;
        private String ios_dl;
        private String count_dl;
        private int all_prize;

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

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public void setDl_back_jifen(String dl_back_jifen) {
            this.dl_back_jifen = dl_back_jifen;
        }

        public void setH5src(String h5src) {
            this.h5src = h5src;
        }

        public void setAndroid_dl(String android_dl) {
            this.android_dl = android_dl;
        }

        public void setIos_dl(String ios_dl) {
            this.ios_dl = ios_dl;
        }

        public void setCount_dl(String count_dl) {
            this.count_dl = count_dl;
        }

        public void setAll_prize(int all_prize) {
            this.all_prize = all_prize;
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

        public String getIcon() {
            return icon;
        }

        public String getSize() {
            return size;
        }

        public String getDl_back_jifen() {
            return dl_back_jifen;
        }

        public String getH5src() {
            return h5src;
        }

        public String getAndroid_dl() {
            return android_dl;
        }

        public String getIos_dl() {
            return ios_dl;
        }

        public String getCount_dl() {
            return count_dl;
        }

        public int getAll_prize() {
            return all_prize;
        }
    }
}
