package com.example.hatem.tick_toc_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hatem.tick_toc_app.ORM.EventListItem;
import com.example.hatem.tick_toc_app.R;

import java.util.List;

/**
 * Created by hatem on 12/4/16.
 */
public class EventsAdapter extends BaseAdapter {

    List<EventListItem> eventListItemList;
    LayoutInflater layoutInflater;
    Context context;

    public EventsAdapter(List<EventListItem> eventListItemList, Context context) {
        this.eventListItemList = eventListItemList;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    static class ViewHolder{
        private TextView textView_eventName;
        private TextView textView_eventDate;
        private TextView textView_eventTime;
    }

    @Override
    public int getCount() {
        return eventListItemList.size();
    }

    @Override
    public EventListItem getItem(int position) {
        return eventListItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;

        if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.events_list_item,null);
                viewHolder = new ViewHolder();
                viewHolder.textView_eventName = (TextView) convertView.findViewById(R.id.event_item_name);
                viewHolder.textView_eventDate = (TextView) convertView.findViewById(R.id.event_item_date);
                viewHolder.textView_eventTime = (TextView) convertView.findViewById(R.id.event_item_time);
                convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        EventListItem eventListItem = getItem(position);
//        viewHolder.textView_eventName.setText(eventListItem.ge);
        return convertView;
    }
}
