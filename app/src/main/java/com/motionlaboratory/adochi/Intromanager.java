package com.motionlaboratory.adochi;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by naofal on 2/12/2017.
 */

public class Intromanager {

    SharedPreferences sp; // class sharepreference ini jenis class untuk penyimpanan
    SharedPreferences.Editor editor;
    Context context;//context yang hubungannya sama activiy

    public Intromanager(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("first",0);
        editor = sp.edit();
    }

    public void setFirst(boolean isFirst){
        editor.putBoolean("check",isFirst);
        editor.commit();
    };

    public boolean chek(){
        return sp.getBoolean("chek",true);
    }
}
