package com.wtz.customview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.wtz.customview.data.ListItem;
import com.wtz.customview.fragment.FragmentAccumulateDrawbitmap;
import com.wtz.customview.fragment.FragmentAccumulateRotate;
import com.wtz.customview.fragment.FragmentAccumulateScale;
import com.wtz.customview.fragment.FragmentBezier;
import com.wtz.customview.fragment.FragmentLeafLoading;
import com.wtz.customview.fragment.FragmentList;
import com.wtz.customview.fragment.FragmentMatrixBasicUse;
import com.wtz.customview.fragment.FragmentPathBooleanOp;
import com.wtz.customview.fragment.FragmentPathMeasure;
import com.wtz.customview.fragment.FragmentPie;
import com.wtz.customview.fragment.FragmentPoly;
import com.wtz.customview.fragment.FragmentVerificationCode;
import com.wtz.customview.fragment.FragmentVerticalOffsetLayout;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements FragmentList.OnFragmentListClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayList<ListItem> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initList();

        FragmentList frag = FragmentList.newInstance(mList);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_container, frag);
        transaction.commit();
    }

    private void initList() {
        mList = new ArrayList<ListItem>();
        mList.add(new ListItem("VerticalOffsetLayout", FragmentVerticalOffsetLayout.class.getName()));
        mList.add(new ListItem("验证码", FragmentVerificationCode.class.getName()));
        mList.add(new ListItem("GALeafLoading", FragmentLeafLoading.class.getName()));
        mList.add(new ListItem("canvas.scale累积效果", FragmentAccumulateScale.class.getName()));
        mList.add(new ListItem("canvas.rotate累积效果", FragmentAccumulateRotate.class.getName()));
        mList.add(new ListItem("canvas.drawBitmap指定区域绘制", FragmentAccumulateDrawbitmap.class.getName()));
        mList.add(new ListItem("canvas.drawArc饼图", FragmentPie.class.getName()));
        mList.add(new ListItem("canvas.drawPath-贝赛尔曲线", FragmentBezier.class.getName()));
        mList.add(new ListItem("canvas.drawPath-Op(api>=19)", FragmentPathBooleanOp.class.getName()));
        mList.add(new ListItem("canvas.drawPath-PathMeasure", FragmentPathMeasure.class.getName()));
        mList.add(new ListItem("Matrix基本用法", FragmentMatrixBasicUse.class.getName()));
        mList.add(new ListItem("Matrix.setPolyToPoly(多边形)", FragmentPoly.class.getName()));
    }

    @Override
    public void onFragmentListClick(ListItem item) {
        if (item != null) {
            startFragment(item.getClassName());
        }
    }

    private void startFragment(String className) {
        Log.d(TAG, "startFragment...className=" + className);
        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (cls == null) {
            toast("未找到" + className);
        } else {
            Fragment frag = null;
            try {
                Constructor c1 = cls.getDeclaredConstructor();
                c1.setAccessible(true);
                frag = (Fragment) c1.newInstance();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (frag == null) {
                toast("无法创建" + className);
            } else {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fl_container, frag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
    }

    private void toast(String msg) {
        Toast toast = Toast.makeText(MainActivity.this, "" + msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 200);
        toast.show();
    }
}
