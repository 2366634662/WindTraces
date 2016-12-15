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
public class ReadingTypeFragment extends Fragment {

    public static ReadingTypeFragment newInstance() {
        ReadingTypeFragment fragment = new ReadingTypeFragment();
        return fragment;
    }

    public ReadingTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reading_type, container, false);
    }

}
