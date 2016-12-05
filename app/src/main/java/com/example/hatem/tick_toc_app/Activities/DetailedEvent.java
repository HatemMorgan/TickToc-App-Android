package com.example.hatem.tick_toc_app.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hatem.tick_toc_app.R;

public class DetailedEvent extends AppCompatActivity {

    TextView textView_eventName;
    TextView textView_startDate;
    TextView textView_startTime;
    TextView textView_endDate;
    TextView textView_endTime;
    ListView listView_Attendees;
    ImageView imageView_location;
    Toolbar toolbar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_event);

        textView_eventName = (TextView) findViewById(R.id.detailedEvent_textView_title);
        textView_startDate = (TextView) findViewById(R.id.detialedEvent_textView_startDate);
        textView_startTime = (TextView) findViewById(R.id.detialedEvent_textView_startTime);
        textView_endDate = (TextView) findViewById(R.id.detialedEvent_textView_endTime);
        textView_endTime = (TextView) findViewById(R.id.detialedEvent_textView_endTime);
        listView_Attendees = (ListView) findViewById(R.id.detailedEvent_listView);
        imageView_location = (ImageView) findViewById(R.id.detailedEvent_imageview_location);

        context = this;
        initToolBar();


    }


    private void getEventDetails(String eventID){

        final String USERID_PARAM = "userID";
        final String EVENTID_PARAM = "id";
        String allEventsUrl = "http://52.41.53.13/events/list";
        Uri buildURI = Uri.parse(allEventsUrl)
                .buildUpon()
                .appendQueryParameter(USERID_PARAM,"5843bbfa010b6d0f739c1c74")
                .appendQueryParameter(EVENTID_PARAM,eventID)
                .build();




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
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
