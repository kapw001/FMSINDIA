package com.fms.fmsindia.fragement;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fms.fmsindia.Home;
import com.fms.fmsindia.R;
import com.fms.fmsindia.fragmentcallback.FragmentCallback;

/**
 * Created by admin on 9/26/2016.
 */
public class DashBoard extends FragmentCallback implements View.OnClickListener {

    View v;
    private Fragment fragment;
    LinearLayout rasiticket, openticket, closedticket, profiles, feedbacks,partnerus;

    TextView rasiticketText, openticketText, closedtickeText, profilesText, feedbacksText,partnerusText;

    DashCallback dashCallback;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        dashCallback=(DashCallback)activity;
    }

    public DashBoard() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.dashboard, container, false);
        // expandableListView = (ExpandableListView) rootView.findViewById(R.id.expListView);

        return v;
    }

    @Override
    public void onStart() {
        Home home = new Home();
        home.ValueTitle = "Profile";

        super.onStart();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        rasiticket = (LinearLayout)view. findViewById(R.id.rasiticket);

        openticket = (LinearLayout)view. findViewById(R.id.openticket);

        closedticket = (LinearLayout) view.findViewById(R.id.closedticket);

        profiles = (LinearLayout)view. findViewById(R.id.profiles);

        feedbacks = (LinearLayout) view.findViewById(R.id.feedbacks);

        partnerus = (LinearLayout) view.findViewById(R.id.partnerus);


        rasiticketText = (TextView)view. findViewById(R.id.rasieTicketText);

        openticketText = (TextView)view. findViewById(R.id.openTicketText);

        closedtickeText = (TextView) view.findViewById(R.id.closedTicketText);

        profilesText = (TextView)view. findViewById(R.id.profileTicket);

        feedbacksText = (TextView) view.findViewById(R.id.feedbacksText);

        partnerusText = (TextView) view.findViewById(R.id.partnerusText);


        rasiticketText.setTextColor(Color.parseColor("#ffffff"));

        openticketText.setTextColor(Color.parseColor("#ffffff"));

        closedtickeText.setTextColor(Color.parseColor("#ffffff"));

        profilesText.setTextColor(Color.parseColor("#ffffff"));

        feedbacksText.setTextColor(Color.parseColor("#ffffff"));

        partnerusText.setTextColor(Color.parseColor("#ffffff"));

        rasiticket.setOnClickListener(this);
        openticket.setOnClickListener(this);
        closedticket.setOnClickListener(this);
        profiles.setOnClickListener(this);
        feedbacks.setOnClickListener(this);
        partnerus.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rasiticket:
                dashCallback.SetTab("rasiticket");
                break;
            case R.id.openticket:
                dashCallback.SetTab("openticket");
                break;
            case R.id.closedticket:
                dashCallback.SetTab("closedticket");
                break;
            case R.id.profiles:
                dashCallback.SetTab("profiles");
                break;
            case R.id.feedbacks:

                dashCallback.SetTab("feedbacks");
                break;
            case R.id.partnerus:
                dashCallback.SetTab("partnerus");
                break;

        }
    }

    public interface DashCallback{

        void SetTab(String value);
    }

}
