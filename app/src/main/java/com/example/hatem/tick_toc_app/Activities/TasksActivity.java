package com.example.hatem.tick_toc_app.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.hatem.tick_toc_app.Adapters.TasksAdapter;
import com.example.hatem.tick_toc_app.ORM.TasksListItem;
import com.example.hatem.tick_toc_app.ORM.TasksResponseObject;
import com.example.hatem.tick_toc_app.R;
import com.example.hatem.tick_toc_app.Utilities.RequestQueueSingelton;
import com.google.gson.Gson;

import java.util.List;

public class TasksActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private TextView textView_NoTasks ;
   private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);


        listView = (ListView) findViewById(R.id.tasks_listview);
        textView_NoTasks = (TextView) findViewById(R.id.tasks_textView_noTasks);
        context = this;
        initToolBar();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textViewName = (TextView) view.findViewById(R.id.task_item_name);
                String taskID = (String) textViewName.getContentDescription();

                Bundle bundle = new Bundle();
                bundle.putString("taskID",taskID);
                Intent intent = new Intent(context,DetailedTask.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        System.out.print("here");
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences prefs = getSharedPreferences(getString(R.string.MY_PREFS_NAME), MODE_PRIVATE);
        final String userID = prefs.getString("userID", null);
        if (userID != null) {
            getAllTasks(userID);
        }


    }


    private void getAllTasks (String userID) {
        final String URID_PARAM = "userID";

        String allEventsUrl = "http://52.41.53.13/tasks/list";
        Uri buildURI = Uri.parse(allEventsUrl)
                .buildUpon()
                .appendQueryParameter(URID_PARAM,userID)
                .build();

        StringRequest getAllTasksRequest = new StringRequest(Request.Method.GET, buildURI.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                TasksResponseObject tasksResponseObject = gson.fromJson(response, TasksResponseObject.class);
                List<TasksListItem> tasksListItems = tasksResponseObject.getResults();
                if(tasksListItems.size() == 0){
                    textView_NoTasks.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                    textView_NoTasks.setText("You have no upcoming tasks");
                }else{
                    TasksAdapter tasksAdapter = new TasksAdapter(context,tasksListItems);
                    listView.setAdapter(tasksAdapter);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueueSingelton.getmInstance(context).getmRequestQueue().add(getAllTasksRequest);

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
