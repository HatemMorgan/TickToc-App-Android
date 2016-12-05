package com.example.hatem.tick_toc_app.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hatem.tick_toc_app.ORM.DetailedTaskObj;
import com.example.hatem.tick_toc_app.ORM.DetailedTaskResponseObject;
import com.example.hatem.tick_toc_app.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import com.example.hatem.tick_toc_app.Utilities.DateUtility;
import com.example.hatem.tick_toc_app.Utilities.RequestQueueSingelton;

public class DetailedTask extends AppCompatActivity {

    TextView textView_eventName;
    TextView textView_startDate;
    TextView textView_startTime;
    TextView textView_endDate;
    TextView textView_endTime;
    ImageButton imageBtn_location;
    Toolbar toolbar;
    Context context;
    DetailedTaskObj detailedTask ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_task);

        textView_eventName = (TextView) findViewById(R.id.detailedTask_textView_title);
        textView_startDate = (TextView) findViewById(R.id.detialedTask_textView_startDate);
        textView_startTime = (TextView) findViewById(R.id.detialedTask_textView_startTime);
        textView_endDate = (TextView) findViewById(R.id.detialedTask_textView_endTime);
        textView_endTime = (TextView) findViewById(R.id.detialedTask_textView_endTime);
        imageBtn_location = (ImageButton) findViewById(R.id.detailedTask_imageview_location);
        context = this;
        initToolBar();


        imageBtn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] location = {detailedTask.getLocation().getLongitude(),detailedTask.getLocation().getLatitude()};
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
        String eventID = bundle.getString("taskID");
        getEventDetails(eventID);
    }

    private void getEventDetails(String taskID){

        final String USERID_PARAM = "userID";
        final String EVENTID_PARAM = "id";
        String allEventsUrl = "http://52.41.53.13/tasks";
        Uri buildURI = Uri.parse(allEventsUrl)
                .buildUpon()
                .appendQueryParameter(USERID_PARAM,"5843bbfa010b6d0f739c1c74")
                .appendQueryParameter(EVENTID_PARAM,taskID)
                .build();

        StringRequest getEventDetails = new StringRequest(Request.Method.GET, buildURI.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson() ;
                DetailedTaskResponseObject detailedTaskResponseObject = gson.fromJson(response,DetailedTaskResponseObject.class);
                detailedTask = detailedTaskResponseObject.getResults();
                textView_eventName.setText(detailedTask.getTitle());
                String strStartDate = detailedTask.getStartDateTime();
                textView_startDate.setText(DateUtility.getDetailedEventFormatedDate(strStartDate.substring(0, 19)+"Z"));
                String strEndDate = detailedTask.getStartDateTime();
                textView_startTime.setText(DateUtility.getFormattedTime(strEndDate.substring(0, 19)+"Z"));
                textView_endDate.setText(DateUtility.getDetailedEventFormatedDate(detailedTask.getEndDateTime()));
                textView_endTime.setText(DateUtility.getFormattedTime(detailedTask.getEndDateTime()));

                String[] location = {detailedTask.getLocation().getLongitude(),detailedTask.getLocation().getLatitude()};
                String locationURL = "http://maps.google.com/maps/api/staticmap?center="+location[0]+","+location[1]+"&zoom=15&size=1000x400&sensor=false";
                Picasso.with(context).load(locationURL).into(imageBtn_location);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueueSingelton.getmInstance(context).getmRequestQueue().add(getEventDetails);
    }


    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.detailedTasks_toolbar);
        toolbar.setTitle("Task Details");

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TasksActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
