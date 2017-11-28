package com.wtz.customview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.wtz.customview.R;
import com.wtz.customview.view.Bezier3Order;

public class FragmentAccumulateScale extends Fragment {

    public FragmentAccumulateScale() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accumulate_scale, container, false);
    }

}
