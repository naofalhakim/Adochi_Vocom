package com.motionlaboratory.adochi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by naofal on 2/20/2017.
 */

public class HelpFragment extends Fragment {
    public HelpFragment() {

    }
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.help_activity, container, false);

        return rootView;
    }
}
