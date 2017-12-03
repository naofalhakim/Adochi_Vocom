package com.motionlaboratory.adochi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Date;

import customfont.MyButton;
import customfont.MyTextView;
import de.hdodenhof.circleimageview.CircleImageView;

public class InkubasiProses extends AppCompatActivity {

    private MyTextView txt_tgl, et_nama,et_umur;
    private CircleImageView img;
    private MyButton btn_konfirmasi;
    private int tanggal, bulan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inkubasi_proses);

        tanggal = new Date().getDate() + 7;
        bulan = new Date().getMonth() + 2 ;

        et_nama = (MyTextView)findViewById(R.id.nama_anak_i);
        et_umur = (MyTextView)findViewById(R.id.umur_anak_i);
        img = (CircleImageView) findViewById(R.id.gambar_anak);
        btn_konfirmasi = (MyButton) findViewById(R.id.btn_konfirmasi);
        txt_tgl = (MyTextView) findViewById(R.id.tanggal_konfirmasi);



        img.setImageResource(getIntent().getIntExtra("gambar",00));
        et_nama.setText("Nama "+getIntent().getStringExtra("nama"));
        et_umur.setText("Usia "+getIntent().getStringExtra("Umur"));

        txt_tgl.setText(Integer.toString(tanggal)+" - "+Integer.toString(bulan)+" - 2017");

        btn_konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goKonfirmasi();
            }
        });

    }


    private void goKonfirmasi() {
        Intent i = new Intent(this,PenyelesaianActivity.class);
        startActivity(i);
        finish();
    }

}

