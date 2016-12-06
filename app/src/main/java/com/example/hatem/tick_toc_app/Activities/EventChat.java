package com.example.hatem.tick_toc_app.Activities;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TimePicker;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.hatem.tick_toc_app.Adapters.CustomAdapter;
import com.example.hatem.tick_toc_app.R;
import com.example.hatem.tick_toc_app.Utilities.ListViewItem;
import com.example.hatem.tick_toc_app.Utilities.RequestQueueSingelton;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EventChat extends AppCompatActivity {
    //        SharedPreferences prefs = getSharedPreferences(getString(R.string.MY_PREFS_NAME), MODE_PRIVATE);
//        final String userID = prefs.getString("userID", null);
//        if (userID != null) {

    private ListView listView;
    ImageView mButton;
    EditText mEdit;
    String mContent;
    String tv;
    ListViewItem[] currentList={};
    String uuid;
    String userID ;
    //For Calendar

    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);
        mButton = (ImageView)findViewById(R.id.sendimg);
        mEdit   = (EditText)findViewById(R.id.edittext);

                SharedPreferences prefs = getSharedPreferences(getString(R.string.MY_PREFS_NAME), MODE_PRIVATE);
        final String struserID = prefs.getString("userID", null);
        if (struserID != null) {
            userID = struserID;
            // Welcome message
            String welcome_url = "http://52.41.53.13/welcome?userID="+userID;

            JsonObjectRequest jsonRequest = new JsonObjectRequest
                    (Request.Method.GET, welcome_url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // the response is already constructed as a JSONObject!
                            try {
                                JSONArray temp = response.getJSONArray("results");
                                JSONObject temp1 = temp.getJSONObject(0);
                                String message = temp1.getString("message");
                                uuid = temp1.getString("uuid");
                                ListViewItem[] newlist = createList(message, false, currentList);
                                currentList = newlist;
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

//            Volley.newRequestQueue(this).add(jsonRequest);
            RequestQueueSingelton.getmInstance(this).addToRequestQueue(jsonRequest);
        }
        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        if(currentList[currentList.length-1].getText().startsWith("Please enter the location")){
                            //direct to map
                            PlacePicker.IntentBuilder builder =new PlacePicker.IntentBuilder();
                            Intent placeIntent;
                            try {
                                placeIntent=builder.build(getApplicationContext());
                                startActivityForResult(placeIntent,1);
                            } catch (GooglePlayServicesRepairableException e) {
                                e.printStackTrace();
                            } catch (GooglePlayServicesNotAvailableException e) {
                                e.printStackTrace();
                            }
                        }
                        else if (currentList[currentList.length-1].getText().startsWith("Please enter the start dateTime") ||
                                currentList[currentList.length-1].getText().startsWith("Please enter the end dateTime")){
                            getDateFromCalendar();
                            getTimeFromCalendar();
                        }
                        else {
                            mContent = mEdit.getText().toString();
                            tv = mContent;
                            //send this to api
                            ListViewItem[] newlist = createList(tv, true, currentList);
                            currentList = newlist;
                            chatPost(userID);
                            mEdit.setText("");
                        }

                    }
                });
    }

    public void  updateDateAndTimeTextField()
    {

        ListViewItem[] newlist = createList(convertDateToUTC(), true, currentList);
        currentList = newlist;
        tv=convertDateToUTC();
        chatPost(userID);
    }

    public void getDateFromCalendar()
    {
        new DatePickerDialog(this,dp, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();
    }
    DatePickerDialog.OnDateSetListener dp;

    {
        dp = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {


                dateTime.set(Calendar.YEAR,i);

                dateTime.set(Calendar.MONTH,i1);
                dateTime.set(Calendar.DAY_OF_MONTH,i2);
                updateDateAndTimeTextField();

            }
        };
    }

    public void getTimeFromCalendar()
    {
        new TimePickerDialog(this,tp,dateTime.get(Calendar.HOUR_OF_DAY),dateTime.get(Calendar.MINUTE),true).show();
    }
    TimePickerDialog.OnTimeSetListener tp = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            dateTime.set(Calendar.HOUR_OF_DAY,i);
            dateTime.set(Calendar.MINUTE,i1);
            //updateDateAndTimeTextField();
        }
    };
    String convertDateToUTC(){
        String hour;
        String minute;
        String day;
        String month;

        if(dateTime.get(Calendar.MONTH)<10)
        {
            month ="0"+(dateTime.get(Calendar.MONTH)+1);
        }
        else
        {
            month =""+(dateTime.get(Calendar.MONTH)+1);
        }
        if(dateTime.get(Calendar.DAY_OF_MONTH)<10)
        {
            day ="0"+dateTime.get(Calendar.DAY_OF_MONTH);
        }
        else
        {
            day =""+dateTime.get(Calendar.DAY_OF_MONTH);
        }

        if(dateTime.get(Calendar.HOUR_OF_DAY)<10)
        {
            hour ="0"+dateTime.get(Calendar.HOUR_OF_DAY);
        }
        else
        {
            hour =""+dateTime.get(Calendar.HOUR_OF_DAY);
        }

        if(dateTime.get(Calendar.MINUTE)<10)
        {
            minute ="0"+dateTime.get(Calendar.MINUTE);
        }
        else
        {
            minute =""+dateTime.get(Calendar.MINUTE);
        }
        String newDate =  dateTime.get(Calendar.YEAR)+"-"+ month +"-"+ day+"T"+hour+":"+minute+":"+"00"+".933Z";
        return newDate;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                Place place= PlacePicker.getPlace(data,this);
                String address1=  String.format("%s",place.getLatLng().latitude);
                String address2=  String.format("%s",place.getLatLng().longitude);
                String dd= address1+","+address2;
                ListViewItem[] newlist = createList(dd, true, currentList);
                currentList = newlist;
                tv=dd;
                chatPost(userID);
            }
        }
    }
    public  JSONObject generateJSON (String m) {
        JSONObject temp = new JSONObject();
        try {
            temp.put("message", m);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return temp;
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
//        Volley.newRequestQueue(this).add(postRequest);
        RequestQueueSingelton.getmInstance(this).addToRequestQueue(postRequest);
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