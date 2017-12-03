package com.motionlaboratory.adochi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.collect.Range;

import customfont.MyEditText;

import static android.R.attr.fragment;

/**
 * Created by naofal on 2/20/2017.
 */

public class AdopsiFragment extends Fragment {
    public AdopsiFragment() {

    }
    View rootView;
    Button btn_request;
    MyEditText et_kriteria, et_umur;
    Spinner sp_lokasi;
    RadioGroup rd_grup;
    DBHelperConfig MyDb;
    RadioButton rd_button;
    String uname;

    AwesomeValidation awesomeValidation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        uname = getArguments().getString("nameuser_key");
        rootView = inflater.inflate(R.layout.adopsi_form_kriteria, container, false);
        btn_request = (Button) rootView.findViewById(R.id.btn_submit_kriteria);
        et_kriteria = (MyEditText) rootView.findViewById(R.id.et_kriteria);
        et_umur = (MyEditText) rootView.findViewById(R.id.et_umur);
        sp_lokasi = (Spinner) rootView.findViewById(R.id.combo_daerah);
        rd_grup = (RadioGroup) rootView.findViewById(R.id.rd_gender);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(rootView.getContext(),
        R.array.lokasi_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_lokasi.setAdapter(adapter);

        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_adopsi();
            }
        });

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(et_umur, Range.closed(1, 9), "Umur hanya dari 1 sampai 9 Tahun");

        return rootView;
    }

    public void go_adopsi(){
        int idSelected = rd_grup.getCheckedRadioButtonId();
        rd_button= (RadioButton) rootView.findViewById(idSelected);

            if(awesomeValidation.validate()){
            Intent i = new Intent(this.getActivity(),AdopsiFragment2_PilAnak.class);
                i.putExtra("umur",et_umur.getText().toString());
                i.putExtra("namauser", uname);
                i.putExtra("asal",sp_lokasi.getSelectedItem().toString().toUpperCase());
                i.putExtra("gender",rd_button.getText().toString().toUpperCase());
            startActivity(i);
        }
    }
}