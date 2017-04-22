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
 * Created by androiduser2 on 18/8/15.
 */
public class HomeFragment extends FragmentCallback implements View.OnClickListener {

    View v;
    private Fragment fragment;
    LinearLayout painting, pluming, tankCleaning, carpenter, electrican,motorservice,rosolarservice,itservices;

    TextView paintingText, plumingText, tankCleaningText, carpenterText, electricanText,motorserviceText,rosolarserviceText,itservicesText;

    HomeCallback homeCallback;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        homeCallback=(HomeCallback)activity;
    }

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.home_fragment, container, false);
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

        painting = (LinearLayout)view. findViewById(R.id.painting);

        pluming = (LinearLayout)view. findViewById(R.id.pluming);

        tankCleaning = (LinearLayout) view.findViewById(R.id.tankCleaning);

        carpenter = (LinearLayout)view. findViewById(R.id.carpenter);

        electrican = (LinearLayout) view.findViewById(R.id.electrican);

        itservices = (LinearLayout) view.findViewById(R.id.itservices);

        motorservice = (LinearLayout) view.findViewById(R.id.motorservice);

        rosolarservice = (LinearLayout) view.findViewById(R.id.rosolarservice);

        paintingText = (TextView)view. findViewById(R.id.paintingText);

        plumingText = (TextView)view. findViewById(R.id.plumingText);

        tankCleaningText = (TextView) view.findViewById(R.id.tankCleaningText);

        carpenterText = (TextView)view. findViewById(R.id.carpenterText);

        electricanText = (TextView) view.findViewById(R.id.electricanText);

        itservicesText = (TextView) view.findViewById(R.id.itservicesText);

        motorserviceText = (TextView) view.findViewById(R.id.motorserviceText);

        rosolarserviceText = (TextView) view.findViewById(R.id.rosolarserviceText);


        paintingText.setTextColor(Color.parseColor("#ffffff"));

        plumingText.setTextColor(Color.parseColor("#ffffff"));

        tankCleaningText.setTextColor(Color.parseColor("#ffffff"));

        carpenterText.setTextColor(Color.parseColor("#ffffff"));

        electricanText.setTextColor(Color.parseColor("#ffffff"));

        itservicesText.setTextColor(Color.parseColor("#ffffff"));

        motorserviceText.setTextColor(Color.parseColor("#ffffff"));

        rosolarserviceText.setTextColor(Color.parseColor("#ffffff"));
        rosolarserviceText.setText("RO & Solar Services");
        painting.setOnClickListener(this);
        pluming.setOnClickListener(this);
        tankCleaning.setOnClickListener(this);
        carpenter.setOnClickListener(this);
        electrican.setOnClickListener(this);
        itservices.setOnClickListener(this);
        motorservice.setOnClickListener(this);
        rosolarservice.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.painting:
                homeCallback.SetTicketTab("2");

                break;
            case R.id.pluming:
                homeCallback.SetTicketTab("1");
                break;
            case R.id.tankCleaning:
                homeCallback.SetTicketTab("6");
                break;
            case R.id.carpenter:
                homeCallback.SetTicketTab("3");
                break;
            case R.id.electrican:

                homeCallback.SetTicketTab("5");
                break;
            case R.id.itservices:
                homeCallback.SetTicketTab("9");
                break;
            case R.id.motorservice:
                homeCallback.SetTicketTab("7");
                break;
            case R.id.rosolarservice:

                homeCallback.SetTicketTab("8");
                break;

        }
    }


    public interface HomeCallback{

        void SetTicketTab(String value);
    }

}
