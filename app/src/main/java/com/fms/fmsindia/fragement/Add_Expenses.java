package com.fms.fmsindia.fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fms.fmsindia.R;


/**
 * Created by androiduser2 on 3/9/15.
 */
public class Add_Expenses extends Fragment {

    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        android.view.View v = inflater.inflate(R.layout.expenses_add, container, false);


        return v;
    }
}