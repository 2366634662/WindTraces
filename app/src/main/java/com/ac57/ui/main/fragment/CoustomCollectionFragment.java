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
public class CoustomCollectionFragment extends Fragment {


    public CoustomCollectionFragment() {
        // Required empty public constructor
    }

    public static CoustomCollectionFragment newInstance() {
        CoustomCollectionFragment fragment = new CoustomCollectionFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coustom_collection, container, false);
    }

}
