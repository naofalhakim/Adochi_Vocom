package com.motionlaboratory.adochi;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import customfont.MyButton;
import customfont.MyEditText;

public class LoginActivity extends AppCompatActivity {

    SessionManager session;
    MyButton btn_login, btn_daftar;
    MyEditText et_username, et_password;

    SQLiteDatabase db;
    Cursor c;

    private String SELECT_USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        OpenDatabase();

        session = new SessionManager(getApplicationContext());
        btn_login = (MyButton)findViewById(R.id.btn_login);
        btn_daftar = (MyButton)findViewById(R.id.btn_daftar);
        et_username = (MyEditText)findViewById(R.id.et_username);
        et_password = (MyEditText)findViewById(R.id.et_password);

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_daftar();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_login();
            }
        });
    }

    private void go_daftar(){
        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
        startActivity(intent);
    }

    private void go_login(){

        String et_user = et_username.getText().toString();
        String et_pass = et_password.getText().toString();
        String password;
        password = searchPass(et_user);
        String nama_user = searchUname(et_user);

        DBHelperConfig db = new DBHelperConfig(this);

            if (et_pass.equals(password)) {
                session.createUserLoginSession(et_user, et_pass);

//                boolean insert = db.insertLogin(et_user);
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("username", et_username.getText().toString());
                intent.putExtra("namauser", nama_user);
                startActivity(intent);
                finish();

            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(), "Login gagal", Toast.LENGTH_SHORT);
                toast.show();
            }
    }

        public String searchUname(String a){
            String username;
            String uname = null;

            SELECT_USER = "SELECT * FROM User_table where EMAIL ='"+a+"';";
            c = db.rawQuery(SELECT_USER, null);
            if(c.moveToFirst()){

                do{
                    username = c.getString(2);
                    if( username.equals(a) ){
                        uname = c.getString(1);
                        break;
                    }
                }while(c.moveToNext());
            }

            return uname;
        }

    protected void OpenDatabase(){
        db = openOrCreateDatabase("Adochi.db",MODE_PRIVATE,null);
    }

    public String searchPass(String a){
        String username;
        String password = null;

        SELECT_USER = "SELECT * FROM User_table where EMAIL ='"+a+"';";
        c = db.rawQuery(SELECT_USER, null);
        if(c.moveToFirst()){

            do{
                username = c.getString(2);
                if( username.equals(a) ){
                    password = c.getString(4);
                    break;
                }
            }while(c.moveToNext());
        }

        return password;
    }
}
