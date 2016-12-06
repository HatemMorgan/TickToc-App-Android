package com.example.hatem.tick_toc_app.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.hatem.tick_toc_app.R;
import com.example.hatem.tick_toc_app.Utilities.RequestQueueSingelton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterationActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView textView_firstName;
    TextView textView_lastName;
    TextView textView_email;
    Button btn_register;
    Context context ;
    String firstName =null;
    String lastName = null;
    String email = null;
    TextView textView_token;
    LinearLayout linearLayout_registerationLayout;
    boolean isTokenCodeTaken;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        toolbar = (Toolbar) findViewById(R.id.registeration_toolbar);
        textView_firstName = (TextView) findViewById(R.id.registeration_firstName);
        textView_lastName = (TextView) findViewById(R.id.registeration_lastName);
        textView_email = (TextView) findViewById(R.id.registeration_email);
        btn_register = (Button) findViewById(R.id.email_registeration_button);
        textView_token  = (TextView) findViewById(R.id.registeration_Token);
        linearLayout_registerationLayout = (LinearLayout) findViewById(R.id.registeration_layout);
        context = this;
        initToolBar();
        isTokenCodeTaken = false;
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTokenCodeTaken) {
                    getAuthenticationURl();
                }else{
                    String token = textView_token.getText().toString();
                    if(token == ""){
                        new Toast(context).makeText(context,"You have to type the copied token in occupied place above",Toast.LENGTH_LONG).show();
                    }else{
                        addUser();
                    }

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(isTokenCodeTaken){
            linearLayout_registerationLayout.setVisibility(View.GONE);
            textView_token.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        if(savedInstanceState != null){
//            firstName = savedInstanceState.getString("firstName");
//            lastName = savedInstanceState.getString("lastName");
//            email = savedInstanceState.getString("email");
//
//        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(outState == null)
            outState = new Bundle();

        outState.putString("firstName",textView_firstName.getText().toString());
        outState.putString("lastName",textView_lastName.getText().toString());
        outState.putString("email",textView_email.getText().toString());


        if(firstName != null)
        isTokenCodeTaken =true;
    }

    private void getAuthenticationURl(){

        String userUrl = "http://52.41.53.13/users";
        Uri buildUri = Uri.parse(userUrl).buildUpon().build();
        HashMap<String,String> params = new HashMap<>();

         firstName = textView_firstName.getText().toString();
         lastName = textView_lastName.getText().toString();
         email = textView_email.getText().toString();

        params.put("firstName",firstName);
        params.put("lastName",lastName);
        params.put("email",email);

        JsonObjectRequest getAuthURLRequest = new JsonObjectRequest(Request.Method.POST,buildUri.toString(), new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject result = response.getJSONObject("results");
                    String authURL = result.getString("authURL");
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse(authURL));
                    startActivity(intent);
                    RequestQueueSingelton.getmInstance(context).EmptyQueue();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        RequestQueueSingelton.getmInstance(context).addToRequestQueue(getAuthURLRequest);

    }


    private void addUser (){

        String userUrl = "http://52.41.53.13/users";
        Uri buildUri = Uri.parse(userUrl).buildUpon().build();
        HashMap<String,String> params = new HashMap<>();


        params.put("firstName",firstName);
        params.put("lastName",lastName);
        params.put("email",email);


        JsonObjectRequest addUserRequest = new JsonObjectRequest(Request.Method.POST,buildUri.toString(), new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    String responseStatus = response.getString("Status");
                    if(Integer.parseInt(responseStatus)== 201){
                        JSONObject result = response.getJSONObject("results");
                        String userID = result.getString("userID");

                        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.MY_PREFS_NAME), MODE_PRIVATE).edit();
                        editor.putString("userID", userID);
                        editor.commit();

                        new Toast(context).makeText(context,userID,Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(context,EventChat.class);
                            startActivity(intent);



                    }else{
                        JSONArray error = response.getJSONArray("errors");
                        String errMessage = ((JSONObject)error.get(0)).getString("message");
                        new Toast(context).makeText(context,errMessage,Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(context,RegisterationActivity.class);
                            startActivity(intent);
                            finish();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<>();
                String tokenCode = textView_token.getText().toString();
                params.put("tokenCode", tokenCode);
                return params;
            }
        };;

        RequestQueueSingelton.getmInstance(context).EmptyQueue();
        RequestQueueSingelton.getmInstance(context).addToRequestQueue(addUserRequest);

    }


    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.registeration_toolbar);
        toolbar.setTitle("Registration");

        setSupportActionBar(toolbar);

    }


}
