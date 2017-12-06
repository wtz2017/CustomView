package com.wtz.customview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.wtz.customview.R;
import com.wtz.customview.view.TouchRegion;

public class FragmentMultitouch extends Fragment {

    public FragmentMultitouch() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_multitouch, container, false);
        return root;
    }

}
