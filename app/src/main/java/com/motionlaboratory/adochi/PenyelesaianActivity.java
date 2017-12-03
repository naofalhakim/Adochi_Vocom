package com.motionlaboratory.adochi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import customfont.MyButton;

public class PenyelesaianActivity extends AppCompatActivity {

    MyButton btnBerhasil,btnGgl, btnSelesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyelesaian);

        btnBerhasil = (MyButton) findViewById(R.id.btnBerhasil);
        btnGgl = (MyButton) findViewById(R.id.btnGagal);
        btnSelesai = (MyButton) findViewById(R.id.btn_selesai);

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goFeddback();
            }
        });
    }

    private void goFeddback() {
        Intent i = new Intent(this,FeddbackActivity.class);
        startActivity(i);
        finish();
    }
}
