package com.motionlaboratory.adochi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

import customfont.MyButton;
import customfont.MyTextView;
import de.hdodenhof.circleimageview.CircleImageView;

public class InkubasiAdopsi extends AppCompatActivity {

    private MyTextView txt_tgl, et_nama,et_umur;
    private CircleImageView img;
    MyButton btn_inkubasi, btn_chat;
    private int tanggal, bulan, tahun;
    String uname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inkubasi_adopsi);

        tanggal = new Date().getDate() + 7;
        bulan = new Date().getMonth() + 2 ;

        et_nama = (MyTextView)findViewById(R.id.nama_anak_i);
        et_umur = (MyTextView)findViewById(R.id.umur_anak_i);
        img = (CircleImageView) findViewById(R.id.gambar_anak);
        btn_inkubasi = (MyButton) findViewById(R.id.btn_proses_inkubasi);
        txt_tgl = (MyTextView) findViewById(R.id.tanggal_inkubasi);

        uname = getIntent().getStringExtra("namauser");
        btn_chat = (MyButton) findViewById(R.id.btn_chat);

        img.setImageResource(getIntent().getIntExtra("gambar",00));
        et_nama.setText("Nama "+getIntent().getStringExtra("nama"));
        et_umur.setText("Usia "+getIntent().getStringExtra("Umur"));

        txt_tgl.setText(Integer.toString(tanggal)+" - "+Integer.toString(bulan)+" - 2017");

        btn_inkubasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goInkubasi();
            }
        });


        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goChat();
            }
        });
    }

    private void goChat() {
        Intent i = new Intent(this,ChatList.class);
        i.putExtra("namauser", uname);
        startActivity(i);
    }

    private void goInkubasi() {
        Intent i = new Intent(this,InkubasiProses.class);
        i.putExtra("nama",et_nama.getText().toString());
        i.putExtra("Umur",et_umur.getText().toString());
        i.putExtra("gambar",getIntent().getIntExtra("gambar",00));
        startActivity(i);
        finish();
    }

    }

