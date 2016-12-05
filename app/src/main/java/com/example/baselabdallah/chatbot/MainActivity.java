package com.example.baselabdallah.chatbot;


        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    ImageView mButton;
    EditText mEdit;
    String mContent;
    String tv;
    ListViewItem[] currentList={};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        mButton = (ImageView)findViewById(R.id.sendimg);
        mEdit   = (EditText)findViewById(R.id.edittext);

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
                        mEdit.setText("");

                    }
                });
        //Get from api and call createlist as not a user
        //String response;
        //ListViewItem[] newlist=createList(response, false, currentList);
        //currentList=newlist;

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