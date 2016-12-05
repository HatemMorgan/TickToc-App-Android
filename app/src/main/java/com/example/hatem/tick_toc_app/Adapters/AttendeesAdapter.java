package com.example.hatem.tick_toc_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hatem.tick_toc_app.ORM.Attendee;
import com.example.hatem.tick_toc_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import com.example.hatem.tick_toc_app.Utilities.CircleTransform;

/**
 * Created by hatem on 12/5/16.
 */
public class AttendeesAdapter extends BaseAdapter{

    Context context;
    List<Attendee> attendeeList;
    LayoutInflater layoutInflater ;

    public AttendeesAdapter(Context context, List<Attendee> attendeeList) {
        this.context = context;
        this.attendeeList = attendeeList;
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(this.context.LAYOUT_INFLATER_SERVICE);
    }

    static class ViewHolder{
        private TextView textView_email;
        private ImageView imageView_status;
        private  ImageView imageView_attendeeProfileImage;
    }

    @Override
    public int getCount() {
        return attendeeList.size();
    }

    @Override
    public Attendee getItem(int position) {
        return attendeeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.attendees_list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.textView_email = (TextView) convertView.findViewById(R.id.detailedEvent_textview_email);
            viewHolder.imageView_status = (ImageView) convertView.findViewById(R.id.detailedEvent_imageview_status);
            viewHolder.imageView_attendeeProfileImage = (ImageView) convertView.findViewById(R.id.imageView_Attendees_image);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Attendee attendee = getItem(position);
        viewHolder.textView_email.setText(attendee.getDisplayName());
        String status = attendee.getResponseStatus();

        if(status.equals("needsAction")){

            viewHolder.imageView_status.setImageResource(R.drawable.pending);
        }else{
            viewHolder.imageView_status.setImageResource(R.drawable.ok);
        }

        Picasso.with(context).load(R.drawable.emptyprofilepicture).transform(new CircleTransform()).into(viewHolder.imageView_attendeeProfileImage);
        return convertView;
    }





}
