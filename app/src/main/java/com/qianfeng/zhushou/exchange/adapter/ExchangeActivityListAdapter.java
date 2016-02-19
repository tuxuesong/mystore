package com.qianfeng.zhushou.exchange.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.other.utils.ImageLoader;
import com.qianfeng.zhushou.other.widget.ExchangeCircleLeftiv;

import java.util.List;
import java.util.Map;

public class ExchangeActivityListAdapter extends BaseAdapter {

    private List<Map<String,String>> objects ;

    private Context context;
    private LayoutInflater layoutInflater;
    private Map<String,String> map;
    ImageLoader imageloader;
    public ExchangeActivityListAdapter(List<Map<String,String>> list,Context context) {
        this.context = context;
        this.objects = list;
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
        map = objects.get(position);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.exchange_activity_list, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.exchangeLeftIv = (ExchangeCircleLeftiv) convertView.findViewById(R.id.exchange_left_iv);
            viewHolder.exchangeMiddleTitleTop = (TextView) convertView.findViewById(R.id.exchange_middle_title_Top);
            viewHolder.exchangeMiddleMiddle = (TextView) convertView.findViewById(R.id.exchange_middle_middle);
            viewHolder.exchangeMiddleBottom = (TextView) convertView.findViewById(R.id.exchange_middle_bottom);
            viewHolder.exchangeRightBtn = (Button) convertView.findViewById(R.id.exchange_right_btn);
            viewHolder.exchangeRightBtn.setFocusable(false);
            convertView.setTag(viewHolder);
        }
        initializeViews((Map<String,String>)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Map<String,String> map, ViewHolder holder) {

        holder.exchangeMiddleTitleTop.setText(map.get("name"));
        holder.exchangeMiddleMiddle.setText("价值：" + map.get("consume")+"U币");
        holder.exchangeMiddleBottom.setText("剩余:" + map.get("remain"));
       // imageloader.DisplayImage(map.get("icon").toString(),holder.exchangeLeftIv);
        holder.exchangeLeftIv.setImagePath(map.get("icon"));
    }

    protected class ViewHolder {
        private ExchangeCircleLeftiv exchangeLeftIv;
    private TextView exchangeMiddleTitleTop;
    private TextView exchangeMiddleMiddle;
    private TextView exchangeMiddleBottom;
    private Button exchangeRightBtn;
    }
}
