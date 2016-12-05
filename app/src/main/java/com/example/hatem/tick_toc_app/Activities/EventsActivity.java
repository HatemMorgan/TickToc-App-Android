package com.example.hatem.tick_toc_app.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

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

public class EventsActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);


        listView = (ListView) findViewById(R.id.events_listview);
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

        String allEventsUrl = "http://52.41.53.13/events/list";
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
                EventsAdapter eventsAdapter = new EventsAdapter(context,eventListItems);
                listView.setAdapter(eventsAdapter);
//                setListViewHeightBasedOnChildren(listView);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueueSingelton.getmInstance(context).getmRequestQueue().add(getAllEventsRequest);

    }
    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.events_toolbar);
        toolbar.setTitle("Events");

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

    // used to set the hieght of the list view based on the children when adding a listview to scroll view
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


}
