package com.motionlaboratory.adochi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Rayiemas Manggala P on 3/11/2017.
 */

public class ChatList extends AppCompatActivity{
    private Button addroom;
    private EditText room_name;

    private ListView list_View;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_room = new ArrayList();
    private String name, childname;
    SessionManager session;

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        session = new SessionManager(getApplicationContext());
        HashMap<String,String> user = session.getUserDetails();

        if(session.checkLogin()) {
            finish();
        }

        name =  user.get(SessionManager.KEY_EMAIL); //iki get session e email;

        childname = getIntent().getStringExtra("namauser");
        list_View = (ListView) findViewById(R.id.listroom);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list_of_room);
        list_View.setAdapter(arrayAdapter);


        Map<String,Object> map = new HashMap<String, Object>();
        root.updateChildren(map);
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> setroom = new HashSet<String>();

                Iterator i = dataSnapshot.getChildren().iterator();
                while(i.hasNext()){
                    setroom.add(((DataSnapshot)i.next()).getKey());
                }
                list_of_room.clear();
                list_of_room.addAll(setroom);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override

            public void onCancelled(DatabaseError databaseError) {

            }
        });
        list_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ChatRoom.class);
                intent.putExtra("room_name", ((TextView)view).getText().toString());
                intent.putExtra("user_name", name);
                intent.putExtra("child", childname);
                startActivity(intent);
            }
        });
        //requestUsername();

       /*root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(childname)==true) {
                    // run some code
                    root.child(childname).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Set<String> setroom = new HashSet<String>();

                            Iterator i = dataSnapshot.getChildren().iterator();
                            while(i.hasNext()){
                                setroom.add(((DataSnapshot)i.next()).getKey());
                            }
                            list_of_room.clear();
                            list_of_room.addAll(setroom);

                            arrayAdapter.notifyDataSetChanged();
                        }

                        @Override

                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                } else {
                    Map<String,Object> map = new HashMap<String, Object>();
                    map.put(childname,"");
                    root.updateChildren(map);
                    root.child(childname).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Set<String> setroom = new HashSet<String>();

                            Iterator i = dataSnapshot.getChildren().iterator();
                            while(i.hasNext()){
                                setroom.add(((DataSnapshot)i.next()).getKey());
                            }
                            list_of_room.clear();
                            list_of_room.addAll(setroom);

                            arrayAdapter.notifyDataSetChanged();
                        }

                        @Override

                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/




    }

    /*private void requestUsername(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Name : ");

        final EditText input_field = new EditText(this);
        builder.setView(input_field);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name = input_field.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                requestUsername();
            }
        });
        builder.show();
    }*/

}
