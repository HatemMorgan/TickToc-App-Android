package com.example.baselabdallah.chatbot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by baselabdallah on 12/5/16.
 */

public class CustomAdapter extends ArrayAdapter {

        public static final int TYPE_ODD = 0;
        public static final int TYPE_EVEN = 1;

        private ListViewItem[] objects;

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            return objects[position].getType();
        }

        public CustomAdapter(Context context, int resource, ListViewItem[] objects) {
            super(context, resource, objects);
            this.objects = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
            ListViewItem listViewItem = objects[position];
            int listViewItemType = getItemViewType(position);
            TextView textView;

            if (convertView == null) {

                if (listViewItemType == TYPE_EVEN) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.rblayout, null);
                    textView = (TextView) convertView.findViewById(R.id.rtext);
                } else {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.lblayout, null);
                    textView = (TextView) convertView.findViewById(R.id.ltext);
                }

                viewHolder = new ViewHolder(textView);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.getText().setText(listViewItem.getText());

            return convertView;
        }

}
