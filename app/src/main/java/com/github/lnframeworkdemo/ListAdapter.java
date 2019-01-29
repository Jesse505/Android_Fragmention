package com.github.lnframeworkdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * 类描述
 * 创建者:tiny
 * 日期:17/11/2
 */

public class ListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<String> datas;

    public ListAdapter(Context context, List<String> datas) {
        this.inflater = LayoutInflater.from(context);
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_listview_item, null);
            holder.tv_item = (TextView) convertView.findViewById(R.id.tv_item);
            holder.tv_item.setText(datas.get(position));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.tv_item.setText(datas.get(position));
        }

        return convertView;
    }

    static class ViewHolder {
        TextView tv_item;
    }
}
