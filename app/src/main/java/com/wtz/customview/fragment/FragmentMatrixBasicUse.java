package com.wtz.customview.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.wtz.customview.R;
import com.wtz.customview.view.MatrixBasicUse;
import com.wtz.customview.view.SetPolyToPoly;

public class FragmentMatrixBasicUse extends Fragment {

    public FragmentMatrixBasicUse() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_matrix_basic_use, container, false);
        final MatrixBasicUse poly = (MatrixBasicUse) root.findViewById(R.id.matrix_basic_use);

        RadioGroup group = (RadioGroup) root.findViewById(R.id.group);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()) {
                    case R.id.point0:
                        poly.setTestMode(0);
                        break;
                    case R.id.point1:
                        poly.setTestMode(1);
                        break;
                    case R.id.point2:
                        poly.setTestMode(2);
                        break;
                    case R.id.point3:
                        poly.setTestMode(3);
                        break;
                    case R.id.point4:
                        poly.setTestMode(4);
                        break;
                }
            }
        });
        return root;
    }

}
