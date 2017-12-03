package com.motionlaboratory.adochi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import customfont.MyButton;
import customfont.MyEditText;

public class FeddbackActivity extends AppCompatActivity {

    MyButton btn_feedback;
    MyEditText et_feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feddback);

        btn_feedback = (MyButton) findViewById(R.id.btn_feedback);
        et_feedback = (MyEditText) findViewById(R.id.et_feedback);

        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMenu();
            }
        });
    }

    public void goMenu() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Saran Anda");
        alertDialogBuilder
                .setMessage("Terimakasih Atas Masukan Anda")
                .setCancelable(false)
                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        menuIn();
                        finish();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void menuIn(){
        Intent i = new Intent(this,MenuActivity.class);
        startActivity(i);
        finish();
    }
}