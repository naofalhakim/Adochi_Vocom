package com.motionlaboratory.adochi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import customfont.MyButton;

public class UploadTransaksi extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    Button btn_pick;
    MyButton btn_upload;
    ImageView imgUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_transaksi);

        btn_pick = (Button) findViewById(R.id.btn_open_galeri);
        btn_upload = (MyButton) findViewById(R.id.btn_upload);
        imgUpload = (ImageView) findViewById(R.id.img_gmb_upload);

        btn_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGalery();
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        UploadTransaksi.this);
                alertDialogBuilder.setTitle("Status");

                alertDialogBuilder
                        .setMessage("Donasi Suksses")
                        .setCancelable(false)
                        .setPositiveButton("Iya",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                    Intent i = new Intent(UploadTransaksi.this, MenuActivity.class);
                                    startActivity(i);
                                    finish();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void openGalery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case PICK_IMAGE:
                if(resultCode==RESULT_OK){
                    Uri uri=data.getData();
                    String[]projection={MediaStore.Images.Media.DATA};

                    Cursor cursor=getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex=cursor.getColumnIndex(projection[0]);
                    String filePath=cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap yourSelectedImage= BitmapFactory.decodeFile(filePath);
                    Drawable d=new BitmapDrawable(yourSelectedImage);

                    imgUpload.setImageDrawable(d);
                }
                break;

            default:
                break;
        }

    }
}