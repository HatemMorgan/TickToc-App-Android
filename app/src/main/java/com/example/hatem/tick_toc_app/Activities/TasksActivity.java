package com.example.hatem.tick_toc_app.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hatem.tick_toc_app.Adapters.EventsAdapter;
import com.example.hatem.tick_toc_app.ORM.EventListItem;
import com.example.hatem.tick_toc_app.ORM.ResponseObject;
import com.example.hatem.tick_toc_app.R;
import com.google.gson.Gson;

import java.util.List;

import Utilities.RequestQueueSingelton;

public class TasksActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    TextView textView_NoTasks ;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);


        listView = (ListView) findViewById(R.id.tasks_listview);
        textView_NoTasks = (TextView) findViewById(R.id.tasks_textView_noTasks);
        context = this;
        initToolBar();

    }

    @Override
    protected void onStart() {
        super.onStart();
        getAllEvents();
    }


    private void getAllEvents () {
        final String URID_PARAM = "userID";

        String allEventsUrl = "http://52.41.53.13/tasks/list";
        Uri buildURI = Uri.parse(allEventsUrl)
                .buildUpon()
                .appendQueryParameter(URID_PARAM,"5843bbfa010b6d0f739c1c74")
                .build();

        StringRequest getAllEventsRequest = new StringRequest(Request.Method.GET, buildURI.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                ResponseObject responseObject = gson.fromJson(response,ResponseObject.class);
                List<EventListItem> eventListItems = responseObject.getResults();
                if(eventListItems.size() == 0){
                    textView_NoTasks.setText("You have no upcoming tasks");
                }else{
                    EventsAdapter eventsAdapter = new EventsAdapter(context,eventListItems);
                    listView.setAdapter(eventsAdapter);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueueSingelton.getmInstance(context).getmRequestQueue().add(getAllEventsRequest);

    }
    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.tasks_toolbar);
        toolbar.setTitle("Tasks");

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
