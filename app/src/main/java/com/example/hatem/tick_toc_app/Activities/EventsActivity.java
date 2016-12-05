package com.example.hatem.tick_toc_app.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hatem.tick_toc_app.Adapters.EventsAdapter;
import com.example.hatem.tick_toc_app.ORM.EventListItem;
import com.example.hatem.tick_toc_app.ORM.EventsResponseObject;
import com.example.hatem.tick_toc_app.R;
import com.google.gson.Gson;

import java.util.List;

import com.example.hatem.tick_toc_app.Utilities.RequestQueueSingelton;

public class EventsActivity extends AppCompatActivity {

   private Toolbar toolbar;
    private ListView eventsListView;
   private TextView textView_NoEvents ;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);


        eventsListView = (ListView) findViewById(R.id.events_listview);
        textView_NoEvents = (TextView) findViewById(R.id.event_textView_noEvents);
        context = this;
        initToolBar();




        eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textViewName = (TextView) view.findViewById(R.id.event_item_name);
                String eventID = (String) textViewName.getContentDescription();

                Bundle bundle = new Bundle();
                bundle.putString("eventID",eventID);
                Intent intent = new Intent(context,DetailedEvent.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

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
                EventsResponseObject eventsResponseObject = gson.fromJson(response,EventsResponseObject.class);
                List<EventListItem> eventListItems = eventsResponseObject.getResults();

                if(eventListItems.size() == 0){
                    textView_NoEvents.setText("You have no upcoming events");
                }else{
                    EventsAdapter eventsAdapter = new EventsAdapter(context,eventListItems);
                    eventsListView.setAdapter(eventsAdapter);
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



}
