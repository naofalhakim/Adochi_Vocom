package com.motionlaboratory.adochi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import customfont.MyButton;
import de.hdodenhof.circleimageview.CircleImageView;

public class InfoantiAsuhanActivity extends AppCompatActivity {

    private TextView et_nama,et_umur;
    private CircleImageView img;
    public String uname;
    MyButton btn_konfirmasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infoanti_asuhan);

        et_nama = (TextView)findViewById(R.id.nama_anak_1);
        et_umur = (TextView)findViewById(R.id.umur_anak_1);
        img = (CircleImageView) findViewById(R.id.gambar_anak_1);
        btn_konfirmasi = (MyButton) findViewById(R.id.btn_konfirmasi);

        uname = getIntent().getStringExtra("namauser");
        img.setImageResource(getIntent().getIntExtra("gambar",00));
        et_nama.setText(getIntent().getStringExtra("nama"));
        et_umur.setText(getIntent().getStringExtra("Umur"));
        btn_konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goChat();
            }
        });
    }

    private void goChat() {
        Intent i = new Intent(this,InkubasiAdopsi.class);
        i.putExtra("nama",et_nama.getText().toString());
        i.putExtra("Umur",et_umur.getText().toString());
        i.putExtra("namauser", uname);
        i.putExtra("gambar",getIntent().getIntExtra("gambar",00));
        startActivity(i);
        finish();
    }
}
