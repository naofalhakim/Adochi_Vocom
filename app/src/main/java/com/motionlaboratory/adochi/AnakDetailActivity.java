package com.motionlaboratory.adochi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import customfont.MyButton;
import de.hdodenhof.circleimageview.CircleImageView;

public class AnakDetailActivity extends AppCompatActivity {

    private TextView et_nama,et_umur;
    private CircleImageView img;
    public String uname;
    MyButton btn_adopsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anak_detail);

        et_nama = (TextView)findViewById(R.id.nama_anak);
        et_umur = (TextView)findViewById(R.id.umur_anak);
        img = (CircleImageView) findViewById(R.id.gambar_anak);
        btn_adopsi = (MyButton) findViewById(R.id.btn_adopsi);
        uname = getIntent().getStringExtra("namauser");
        img.setImageResource(getIntent().getIntExtra("gambar",00));
        et_nama.setText("Nama "+getIntent().getStringExtra("nama"));
        et_umur.setText("Usia "+getIntent().getStringExtra("Umur"));


        btn_adopsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goChat();
            }
        });
    }

    private void goChat() {
        Intent i = new Intent(this,InfoantiAsuhanActivity.class);
        i.putExtra("nama",et_nama.getText().toString());
        i.putExtra("Umur",et_umur.getText().toString());
        i.putExtra("namauser", uname);
        i.putExtra("gambar",getIntent().getIntExtra("gambar",00));
        startActivity(i);
        finish();
    }
}

