package com.example.hatem.tick_toc_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hatem.tick_toc_app.ORM.TasksListItem;
import com.example.hatem.tick_toc_app.R;

import java.util.List;

import Utilities.DateUtility;

/**
 * Created by hatem on 12/5/16.
 */
public class TasksAdapter extends BaseAdapter {
    List<TasksListItem> tasksListItems ;
    Context context;
    LayoutInflater layoutInflater ;

    public TasksAdapter( Context context,List<TasksListItem> tasksListItems) {
        this.tasksListItems = tasksListItems;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

        static class ViewHolder{
            private TextView textView_taskName;
            private TextView textView_tasksDate;
//            private  ImageButton buttonDelete;
//            private ImageButton buttonUpate;

    }

    @Override
    public int getCount() {
        return tasksListItems.size();
    }

    @Override
    public TasksListItem getItem(int position) {
        return tasksListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder viewHolder ;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.tasks_list_item,null);
            viewHolder.textView_taskName = (TextView) convertView.findViewById(R.id.task_item_name);
            viewHolder.textView_tasksDate = (TextView) convertView.findViewById(R.id.task_item_date);
//            viewHolder.buttonDelete = (ImageButton) convertView.findViewById(R.id.task_btn_delete);
//            viewHolder.buttonUpate = (ImageButton) convertView.findViewById(R.id.task_btn_update);



            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        TasksListItem tasksListItem = getItem(position);
        viewHolder.textView_taskName.setText(tasksListItem.getTitle());
        String strDate = tasksListItem.getStartDateTime();
        viewHolder.textView_tasksDate.setText(DateUtility.getFormateDate(strDate.substring(0, 19)+"Z"));
//        viewHolder.buttonDelete.setContentDescription(tasksListItem.getId());
//        viewHolder.buttonUpate.setContentDescription(tasksListItem.getId());

        return  convertView;
    }
}
