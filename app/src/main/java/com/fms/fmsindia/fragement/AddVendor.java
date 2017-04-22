package com.fms.fmsindia.fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fms.fmsindia.R;


/**
 * Created by androiduser2 on 19/8/15.
 */
public class AddVendor extends Fragment {

    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        android.view.View v = inflater.inflate(R.layout.vendors,container,false);


        return v;
    }
}
