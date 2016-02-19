package com.qianfeng.zhushou.profit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.other.utils.ImageLoader;

import java.util.List;
import java.util.Map;

/**
 * 游戏列表apapter:
 */
public class ProfitFragmentTypeProfitListAdapter extends BaseAdapter {
    private List<Map<String,String>> objects ;
    private Context context;
    private LayoutInflater layoutInflater;
    private  Map<String,String> map;
    private ImageLoader imageloader;
    public ProfitFragmentTypeProfitListAdapter(List<Map<String, String>> objects, Context context) {
        this.objects = objects;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        imageloader = new ImageLoader(context);
    }
    @Override
    public int getCount() {
        return objects.size();
    }
    @Override
    public Map<String,String> getItem(int position) {
        return objects.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        map  =  objects.get(position);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.fragment_type_profit_list, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.listProfitLeftIv = (ImageView) convertView.findViewById(R.id.list_profit_left_iv);
            viewHolder.profitTitleTop = (TextView) convertView.findViewById(R.id.profit_title_top);
            viewHolder.profitRatingbar = (RatingBar) convertView.findViewById(R.id.profit_ratingbar);
            viewHolder.profitTitleBottom = (TextView) convertView.findViewById(R.id.profit_title_bottom);
            viewHolder.profitRightIv = (TextView) convertView.findViewById(R.id.profit_right_iv);
            viewHolder.profitSizeTv = (TextView) convertView.findViewById(R.id.profit_size_tv);
            convertView.setTag(viewHolder);
        }
        initializeViews((Map<String,String>)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }
    private void initializeViews(Map<String,String> map, ViewHolder holder) {
        imageloader.DisplayImage(map.get("icon").toString(), holder.listProfitLeftIv);
        holder.profitTitleTop.setText(map.get("name").toString());
        holder.profitRatingbar.setRating(Float.parseFloat(map.get("score")));
        holder.profitTitleBottom.setText(map.get("count_dl").toString() + "人下载");
        holder.profitRightIv.setText("奖"+map.get("all_prize").toString()+"u币");
        holder.profitSizeTv.setText(map.get("size").toString());
    }
    protected class ViewHolder {
        private ImageView listProfitLeftIv;
        private TextView profitTitleTop;
        private RatingBar profitRatingbar;
        private TextView profitTitleBottom;
         private TextView profitRightIv,profitSizeTv;
    }
}
