package com.motionlaboratory.adochi;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Rayiemas Manggala P on 3/10/2017.
 */

public class ChatRoom extends AppCompatActivity {

    private Button btnsendmsg;
    private EditText inputmsg;
    private TextView chatconv;
    private String uname, roomname;
    private DatabaseReference root;
    private String temp_key;
    SessionManager session;
    Cursor cr;
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        btnsendmsg = (Button) findViewById(R.id.btnsend);
        inputmsg = (EditText) findViewById(R.id.editText);
        chatconv = (TextView) findViewById(R.id.textView);

        session = new SessionManager(getApplicationContext());
        HashMap<String,String> user = session.getUserDetails();

        uname = user.get(SessionManager.KEY_EMAIL); //iki get session e email;
        roomname = getIntent().getExtras().get("room_name").toString();
        setTitle("Room-"+roomname);

        root = FirebaseDatabase.getInstance().getReference().child(roomname);

        btnsendmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> mapq = new HashMap<String, Object>();
                temp_key = root.push().getKey();
                root.updateChildren(mapq);

                DatabaseReference message_root = root.child(temp_key);
                Map<String, Object> mapw  = new HashMap<String, Object>();
                mapw.put("name", uname);
                mapw.put("msg", inputmsg.getText().toString());

                message_root.updateChildren(mapw);
                InputMethodManager inputManager =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(
                        getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                inputmsg.setText("");
            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                append_chat_conversation(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private String chat_msg, chat_user_name;
    private void append_chat_conversation(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();
        while(i.hasNext()){
            chat_msg = (String) ((DataSnapshot)i.next()).getValue();
            chat_user_name = (String) ((DataSnapshot)i.next()).getValue();
            chatconv.append(chat_user_name+" : "+chat_msg+" \n");
        }
    }


}
