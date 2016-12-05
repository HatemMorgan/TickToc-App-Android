package com.example.hatem.tick_toc_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hatem.tick_toc_app.ORM.EventListItem;
import com.example.hatem.tick_toc_app.R;

import java.util.List;

import Utilities.DateUtility;

/**
 * Created by hatem on 12/4/16.
 */
public class EventsAdapter extends BaseAdapter {

    List<EventListItem> eventListItemList;
    LayoutInflater layoutInflater;
    Context context;

    public EventsAdapter(Context context,List<EventListItem> eventListItemList) {
        this.eventListItemList = eventListItemList;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    static class ViewHolder{
        private TextView textView_eventName;
        private TextView textView_eventDate;
        private TextView textView_eventTime;
        private ImageButton buttonDelete;
        private ImageButton buttonUpate;
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
                viewHolder.buttonDelete = (ImageButton) convertView.findViewById(R.id.event_btn_delete);
                viewHolder.buttonUpate = (ImageButton) convertView.findViewById(R.id.event_btn_update);
                convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        EventListItem eventListItem = getItem(position);
        viewHolder.textView_eventName.setText(eventListItem.getSummary());
        viewHolder.textView_eventDate.setText(DateUtility.getFormateDate(eventListItem.getStart().getDateTime()));
        viewHolder.textView_eventTime.setText(DateUtility.getFormattedTime(eventListItem.getStart().getDateTime()));
        viewHolder.buttonDelete.setContentDescription(eventListItem.getId());
        viewHolder.buttonUpate.setContentDescription(eventListItem.getId());
        return convertView;
    }
}
