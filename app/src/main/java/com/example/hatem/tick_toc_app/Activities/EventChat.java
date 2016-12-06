package com.example.hatem.tick_toc_app.Activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.hatem.tick_toc_app.Adapters.CustomAdapter;
import com.example.hatem.tick_toc_app.R;
import com.example.hatem.tick_toc_app.Utilities.ListViewItem;
import com.example.hatem.tick_toc_app.Utilities.RequestQueueSingelton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EventChat extends AppCompatActivity {

    private ListView listView;
    ImageView mButton;
    EditText mEdit;
    String mContent;
    String tv;
    ListViewItem[] currentList={};
    String uuid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);
        mButton = (ImageView)findViewById(R.id.sendimg);
        mEdit   = (EditText)findViewById(R.id.edittext);

        SharedPreferences prefs = getSharedPreferences(getString(R.string.MY_PREFS_NAME), MODE_PRIVATE);
        final String userID = prefs.getString("userID", null);
        if (userID != null) {
            // Welcome message
            String welcome_url = "http://52.41.53.13/welcome?userID="+userID;
            JsonObjectRequest jsonRequest = new JsonObjectRequest
                    (Request.Method.GET, welcome_url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // the response is already constructed as a JSONObject!
                            try {
                                JSONArray temp=response.getJSONArray("results");
                                JSONObject temp1=temp.getJSONObject(0);
                                String message= temp1.getString("message");
                                uuid= temp1.getString("uuid");
                                ListViewItem[] newlist=createList(message, false, currentList);
                                currentList=newlist;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });

            RequestQueueSingelton.getmInstance(this).getmRequestQueue().add(jsonRequest);
        }else{
            Intent intent = new Intent(this,RegisterationActivity.class);
            startActivity(intent);
        }




//        Volley.newRequestQueue(this).add(jsonRequest);

        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        mContent = mEdit.getText().toString();
                        tv=mContent;
                        //send this to api
                        ListViewItem[] newlist=createList(tv, true, currentList);
                        currentList=newlist;
                        chatPost(userID);
                        mEdit.setText("");

                    }
                });
    }


    public void chatPost (String userID){
        String event_url = "http://52.41.53.13/chat/event?userID="+userID;
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("message", tv);
        JsonObjectRequest postRequest = new JsonObjectRequest(Request.Method.POST, event_url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray temp=response.getJSONArray("results");
                            JSONObject temp1=temp.getJSONObject(0);
                            String message= temp1.getString("message");
                            ListViewItem[] newlist=createList(message, false, currentList);
                            currentList=newlist;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", uuid);
                return params;
            }
        };
        RequestQueueSingelton.getmInstance(this).getmRequestQueue().add(postRequest);


    }

    public ListViewItem[] createList (String tv, boolean user, ListViewItem[] old){
        listView = (ListView) findViewById(R.id.listview);

        final ListViewItem[] items = new ListViewItem[old.length+1];
        for(int i=0; i<old.length; i++){
            items[i]=old[i];
        }
        if(user){
            items[old.length] = new ListViewItem(tv, CustomAdapter.TYPE_EVEN);
        }
        else{
            items[old.length] = new ListViewItem(tv, CustomAdapter.TYPE_ODD);
        }
        CustomAdapter customAdapter = new CustomAdapter(this, R.id.text, items);
        listView.setAdapter(customAdapter);
        return items;
    }
}