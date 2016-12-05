package com.example.hatem.tick_toc_app.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hatem.tick_toc_app.Adapters.AttendeesAdapter;
import com.example.hatem.tick_toc_app.ORM.Attendee;
import com.example.hatem.tick_toc_app.ORM.DetailedEventObj;
import com.example.hatem.tick_toc_app.ORM.DetailedEventResponseObject;
import com.example.hatem.tick_toc_app.R;
import com.google.gson.Gson;
import com.example.hatem.tick_toc_app.Utilities.*;
import com.squareup.picasso.Picasso;

import java.util.List;


public class DetailedEvent extends AppCompatActivity {

    TextView textView_eventName;
    TextView textView_startDate;
    TextView textView_startTime;
    TextView textView_endDate;
    TextView textView_endTime;
    ListView listView_Attendees;
    ImageButton imageBtn_location;
    ImageView imageView_organizerProfileImage;
    Toolbar toolbar;
    Context context;
    TextView textView_organizer;
    DetailedEventObj detailedEvent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_event);

        textView_eventName = (TextView) findViewById(R.id.detailedEvent_textView_title);
        textView_startDate = (TextView) findViewById(R.id.detialedEvent_textView_startDate);
        textView_startTime = (TextView) findViewById(R.id.detialedEvent_textView_startTime);
        textView_endDate = (TextView) findViewById(R.id.detialedEvent_textView_endDate);
        textView_endTime = (TextView) findViewById(R.id.detialedEvent_textView_endTime);
        textView_organizer = (TextView) findViewById(R.id.detailedEvent_textview_oragnizeEmail);
        listView_Attendees = (ListView) findViewById(R.id.detailedEvent_listView);
        imageBtn_location = (ImageButton) findViewById(R.id.detailedEvent_imageview_location);
        imageView_organizerProfileImage = (ImageView) findViewById(R.id.detailedEvent_imageView_organizeImage);
        context = this;
        initToolBar();


        imageBtn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] location = detailedEvent.getLocation().split(",");
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr="+location[0]+","+location[1]+"&daddr=20.5666,45.345"));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = this.getIntent().getExtras();
        String eventID = bundle.getString("eventID");
        getEventDetails(eventID);
    }

    private void getEventDetails(String eventID){

        final String USERID_PARAM = "userID";
        final String EVENTID_PARAM = "id";
        String allEventsUrl = "http://52.41.53.13/events";
        Uri buildURI = Uri.parse(allEventsUrl)
                .buildUpon()
                .appendQueryParameter(USERID_PARAM,"5843bbfa010b6d0f739c1c74")
                .appendQueryParameter(EVENTID_PARAM,eventID)
                .build();

        StringRequest getEventDetails = new StringRequest(Request.Method.GET, buildURI.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson() ;
                DetailedEventResponseObject detailedEventResponseObject = gson.fromJson(response,DetailedEventResponseObject.class);
                detailedEvent = detailedEventResponseObject.getResults();
                textView_eventName.setText(detailedEvent.getSummary());
                textView_organizer.setText(detailedEvent.getOrganizer().getDisplayName());
                textView_startDate.setText(DateUtility.getDetailedEventFormatedDate(detailedEvent.getStart().getDateTime()));
                textView_startTime.setText(DateUtility.getFormattedTime(detailedEvent.getStart().getDateTime()));
                textView_endDate.setText(DateUtility.getDetailedEventFormatedDate(detailedEvent.getEnd().getDateTime()));
                textView_endTime.setText(DateUtility.getFormattedTime(detailedEvent.getEnd().getDateTime()));

                String[] location = detailedEvent.getLocation().split(",");
                String locationURL = "http://maps.google.com/maps/api/staticmap?center="+location[0]+","+location[1]+"&zoom=15&size=1000x400&sensor=false";
                Picasso.with(context).load(locationURL).into(imageBtn_location);

                List<Attendee> attendeeList = detailedEvent.getAttendees();
                AttendeesAdapter attendeesAdapter = new AttendeesAdapter(context,attendeeList);
                listView_Attendees.setAdapter(attendeesAdapter);
                setListViewHeightBasedOnChildren(listView_Attendees);

                Picasso.with(context).load(R.drawable.emptyprofilepicture).transform(new CircleTransform()).into(imageView_organizerProfileImage);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueueSingelton.getmInstance(context).getmRequestQueue().add(getEventDetails);
    }


    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.detailedEvents_toolbar);
        toolbar.setTitle("Event Details");

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    // used to set the hieght of the list view based on the children when adding a listview to scroll view
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }else{
            int targetWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            int totalHeight = 0;
            View view = null;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                view = listAdapter.getView(i, null, listView);

                if (i == 0) {
                    view.setLayoutParams(new ViewGroup.LayoutParams(targetWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

                }

                view.measure(targetWidth, View.MeasureSpec.UNSPECIFIED);
                totalHeight += view.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
        }


    }


}
