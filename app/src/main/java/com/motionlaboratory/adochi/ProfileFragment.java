package com.motionlaboratory.adochi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by naofal on 3/9/2017.
 */

public class ProfileFragment extends Fragment {

    public ProfileFragment() {

    }

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.activity_beranda, container, false);

        return rootView;
    }
}
