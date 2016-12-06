package com.example.hatem.tick_toc_app.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.hatem.tick_toc_app.R;

;

public class MainActivity extends AppCompatActivity {
    ImageView add_event;
    ImageView add_task;
    ImageView show_event;
    ImageView show_task;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences(getString(R.string.MY_PREFS_NAME), MODE_PRIVATE);
        final String userID = prefs.getString("userID", null);
        if (userID == null) {
                Intent intent = new Intent(this,RegisterationActivity.class);
                startActivity(intent);
            finish();
        }


        add_event = (ImageView)findViewById(R.id.imageView22);
        add_task = (ImageView)findViewById(R.id.imageView23);
        show_event = (ImageView)findViewById(R.id.imageView24);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        show_task = (ImageView)findViewById(R.id.imageView25);
        initToolBar();
        add_event.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent intent = new Intent(view.getContext(), EventChat.class);
                        view.getContext().startActivity(intent);
                    }
                });
        add_task.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent intent = new Intent(view.getContext(), TaskChat.class);
                        view.getContext().startActivity(intent);
                    }
                });
        show_event.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent intent = new Intent(view.getContext(), EventsActivity.class);
                        view.getContext().startActivity(intent);
                    }
                });
        show_task.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent intent = new Intent(view.getContext(), TasksActivity.class);
                        view.getContext().startActivity(intent);
                    }
                });
    }


    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.detailedEvents_toolbar);
        toolbar.setTitle("TicToc App");

        setSupportActionBar(toolbar);


    }
}