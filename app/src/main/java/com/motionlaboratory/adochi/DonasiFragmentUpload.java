package com.motionlaboratory.adochi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by naofal on 2/20/2017.
 */

public class DonasiFragmentUpload extends Fragment {
    public DonasiFragmentUpload() {

    }
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.donasi_upload_form_, container, false);

        return rootView;
    }
}
