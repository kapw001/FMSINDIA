package com.fms.fmsindia.fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fms.fmsindia.R;


/**
 * Created by androiduser2 on 14/8/15.
 */
public class Status extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.status, container, false);


        return v;
    }
}