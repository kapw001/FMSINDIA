package com.fms.fmsindia.fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.*;

import com.fms.fmsindia.R;


/**
 * Created by androiduser2 on 19/8/15.
 */
public class Vendors extends Fragment {
    private FragmentTabHost mTabHost;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.vendor_table,container,false);

        mTabHost = (FragmentTabHost)v.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realTabContent);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator("ADD "),
                AddVendor.class, null);
            mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator("VIEW"),
                Status.class, null);

        return v;
    }
}
