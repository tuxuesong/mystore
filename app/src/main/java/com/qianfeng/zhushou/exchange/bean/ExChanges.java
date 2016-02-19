package com.qianfeng.zhushou.exchange.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/1.
 */
public class ExChanges {

    /**
     * total : 75
     * pagesize : 10
     * page : 1
     * page_total : 8
     */

    private PageEntity page;
    /**
     * info : [{"id":"573","name":"运河电热毯 双档调温安全保护单人斑点花 150*70cm","consume":"80000","icon":"http://i3.265g.com/images/201510/201510211650104413.jpg","remain":"3"},{"id":"363","name":"30元Q币直充","consume":"30600","icon":"http://i2.265g.com/images/201503/201503251056401698.jpg","remain":"38"},{"id":"560","name":"小米（MI）小米手环","consume":"89000","icon":"http://i2.265g.com/images/201501/201501221518213753.jpg","remain":"4"},{"id":"578","name":"澳柯玛（AUCMA）NF18N316陶瓷发热暖风机","consume":"100000","icon":"http://i5.265g.com/images/201511/201511301404125757.jpg","remain":"35"},{"id":"559","name":"265G定制电源","consume":"60000","icon":"http://i4.265g.com/images/201501/201501161438002343.jpg","remain":"37"},{"id":"575","name":"全国联通流量100M充值","consume":"11000","icon":"http://i5.265g.com/images/201510/201510281431568786.jpg","remain":"7"},{"id":"577","name":"全国电信流量100M充值","consume":"11000","icon":"http://i4.265g.com/images/201510/201510281431408959.jpg","remain":"10"},{"id":"576","name":"全国移动流量充值70M流量包","consume":"11000","icon":"http://i4.265g.com/images/201510/201510281431251486.jpg","remain":"9"},{"id":"579","name":"飞科(FLYCO)专业电动理发器成人儿童电推剪","consume":"75000","icon":"http://i6.265g.com/images/201512/201512011651149252.png","remain":"2"},{"id":"414","name":"雷蛇（Razer）地狱狂蛇Abyssus游戏鼠标镜面版","consume":"169000","icon":"http://i6.265g.com/images/201401/201401170921492767.png","remain":"8"}]
     * page : {"total":"75","pagesize":10,"page":1,"page_total":8}
     * state : success
     */

    private String state;
    /**
     * id : 573
     * name : 运河电热毯 双档调温安全保护单人斑点花 150*70cm
     * consume : 80000
     * icon : http://i3.265g.com/images/201510/201510211650104413.jpg
     * remain : 3
     */

    private List<InfoEntity> info;

    public static ExChanges objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ExChanges.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ExChanges> arrayExChangesFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ExChanges>>() {
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
        private String total;
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

        public void setTotal(String total) {
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

        public String getTotal() {
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
