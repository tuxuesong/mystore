package com.qianfeng.zhushou.active.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.zhushou.R;
import com.qianfeng.zhushou.other.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActiveFragmentlistAdapter extends BaseAdapter {

    private List<Map<String,String>> objects = new ArrayList<Map<String,String>>();
    private ImageLoader imageloader;

    private Context context;
    private LayoutInflater layoutInflater;

    public ActiveFragmentlistAdapter(Context context,List<Map<String,String>> list) {
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
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_activelist,parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.activeImage = (ImageView) convertView.findViewById(R.id.active_image);
            viewHolder.activeTitleTextview = (TextView) convertView.findViewById(R.id.active_title_textview);
            viewHolder.activeMiddleTextview = (TextView) convertView.findViewById(R.id.active_Middle_textview);
            viewHolder.activeBottomTextview = (TextView) convertView.findViewById(R.id.active_bottom_textview);
            viewHolder.activeBottomTextviewState = (TextView) convertView.findViewById(R.id.active_bottom_textview_state);

            convertView.setTag(viewHolder);
        }
        initializeViews((Map<String,String>)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Map<String,String> map, ViewHolder holder) {
        //TODO implement
        String nname = map.get("hotpic");
        imageloader.DisplayImage(nname,holder.activeImage);
        holder.activeTitleTextview.setText(map.get("aname"));
        holder.activeMiddleTextview.setText(map.get("shorttime"));
        holder.activeBottomTextview.setText(map.get("total_join_user")+"人参加");
        holder.activeBottomTextviewState.setText(map.get("status"));
    }

    protected class ViewHolder {
        private ImageView activeImage;
    private TextView activeTitleTextview;
    private TextView activeMiddleTextview;
    private TextView activeBottomTextview;
    private TextView activeBottomTextviewState;
    }
}
