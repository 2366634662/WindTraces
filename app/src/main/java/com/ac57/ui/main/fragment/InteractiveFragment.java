package com.ac57.ui.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ac57.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InteractiveFragment extends Fragment {

    public static InteractiveFragment newInstance() {
        InteractiveFragment fragment = new InteractiveFragment();
        return fragment;
    }

    public InteractiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_interactive, container, false);
    }

}
