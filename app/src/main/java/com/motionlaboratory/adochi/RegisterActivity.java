package com.motionlaboratory.adochi;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import customfont.MyButton;
import customfont.MyEditText;

public class RegisterActivity extends AppCompatActivity {

    DBHelperConfig myDb;
    MyButton btn_daftar_in;
    MyEditText et_noktp, et_nama, et_email, et_pass, et_pass_core, et_alamat;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDb = new DBHelperConfig(this);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        btn_daftar_in = (MyButton)findViewById(R.id.btn_daftar_in);

        et_noktp = (MyEditText) findViewById(R.id.et_no_ktp);
        et_nama = (MyEditText) findViewById(R.id.et_nama);
        et_email = (MyEditText) findViewById(R.id.et_email);
        et_alamat = (MyEditText) findViewById(R.id.et_alamat);
        et_pass = (MyEditText) findViewById(R.id.et_pass_in);
        et_pass_core = (MyEditText) findViewById(R.id.et_pass_in_core);

        btn_daftar_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daftar_proses();
            }
        });

        awesomeValidation.addValidation(this, R.id.et_nama, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.et_email, Patterns.EMAIL_ADDRESS, R.string.emailerror);

    }

    private void daftar_proses(){
        String username = et_email.getText().toString();
        String pass = et_pass.getText().toString();
        Toast toast;

        if(et_noktp.getText().toString().equals("")){
            toast = Toast.makeText(RegisterActivity.this,"No KTP Harus Di isi",Toast.LENGTH_SHORT);
            toast.show();
        }else if(et_nama.getText().toString().equals("")){
            toast = Toast.makeText(RegisterActivity.this,"Nama Harus Di isi",Toast.LENGTH_SHORT);
            toast.show();
        }else if(et_alamat.getText().toString().equals("")) {
            toast = Toast.makeText(RegisterActivity.this, "Alamat Harus Di isi", Toast.LENGTH_SHORT);
            toast.show();
        }else if(username.equals("")){
            toast = Toast.makeText(RegisterActivity.this,"Email Harus Di isi",Toast.LENGTH_SHORT);
            toast.show();
        }else if(pass.equals("")){
            toast = Toast.makeText(RegisterActivity.this,"Password Harus Di isi",Toast.LENGTH_SHORT);
            toast.show();
        }else if(!et_pass_core.getText().toString().equals(pass)){
            toast = Toast.makeText(RegisterActivity.this,"Password tidak Sama",Toast.LENGTH_SHORT);
            toast.show();
        }else if(awesomeValidation.validate()){

            boolean a = myDb.insertData(
                    et_noktp.getText().toString(),
                    et_nama.getText().toString(),
                    et_email.getText().toString(),
                    et_pass.getText().toString(),
                    et_alamat.getText().toString()
            );

            if(a == true){
                toast = Toast.makeText(RegisterActivity.this,"Pendaftaran Berhasil",Toast.LENGTH_SHORT);
                toast.show();
            }
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            intent.putExtra("user",username);
            intent.putExtra("pass",pass);
            startActivity(intent);
            finish();
        }
    }
}