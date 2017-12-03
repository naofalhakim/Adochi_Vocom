package com.motionlaboratory.adochi;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;

import customfont.MyButton;
import customfont.MyEditText;

/**
 * Created by naofal on 2/20/2017.
 */

public class DonasiFragment extends Fragment {

    public DonasiFragment() {

    }
    MyButton btn_donasi;
    MyEditText et_donasi;
    View rootView;
    RadioGroup radioGroup;
    RadioButton radioButton;
    DBHelperConfig MyDb;

    private String name;
    SessionManager session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.donasi_form_kriteria, container, false);


        MyDb = new DBHelperConfig(this.getActivity());
        btn_donasi = (MyButton) rootView.findViewById(R.id.btn_donasi);
        et_donasi = (MyEditText) rootView.findViewById(R.id.et_donasi);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.rg_kategori);

        session = new SessionManager(this.getActivity().getApplicationContext());
        if(session.checkLogin())
            getActivity().finish();

        HashMap<String, String> user = new HashMap<String, String>();
        name = user.get(session.KEY_EMAIL);

        btn_donasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go_upload();
            }
        });
        return rootView;
    }

    private void go_upload() {

        int idSelected = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) rootView.findViewById(idSelected);

        if (!et_donasi.getText().toString().equals("") && !radioButton.getText().toString().equals("")) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this.getActivity());
            alertDialogBuilder.setTitle("Konfirmasi Donasi");

            final Context ctx = this.getActivity();

            alertDialogBuilder
                    .setMessage("Apakah Anda Yakin ?")
                    .setCancelable(false)
                    .setPositiveButton("Iya",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {

                            boolean a = MyDb.insertDataDonasi(
                                    name,
                                    Integer.parseInt(et_donasi.getText().toString()),
                                    radioButton.getText().toString()
                            );

                            if(a == true) {
                                Toast toast = Toast.makeText(ctx, "Terimakasih Atas Donasi Anda", Toast.LENGTH_SHORT);
                                toast.show();
                                Intent i = new Intent(ctx, UploadTransaksi.class);
                                startActivity(i);
                            }
                        }
                    })
                    .setNegativeButton("Batalkan",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }else{
            Toast toast = Toast.makeText(this.getActivity(), "Nominal Harus Diisi", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void alertDialog(final String email_1, final String nominal, final String kategori) {


    }
}