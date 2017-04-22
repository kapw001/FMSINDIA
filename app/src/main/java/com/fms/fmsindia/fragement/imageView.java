package com.fms.fmsindia.fragement;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fms.fmsindia.R;
import com.fms.fmsindia.fragmentcallback.FragmentCallback;
import com.squareup.picasso.Picasso;


/**
 * Created by androiduser2 on 18/8/15.
 */
public class imageView extends FragmentCallback {

    View v;

    private Context mCtx;

    ImageView imageView;

    public imageView() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.layout_image_views, container, false);
        // expandableListView = (ExpandableListView) rootView.findViewById(R.id.expListView);

        this.mCtx = getActivity();
        return v;
    }

    @Override
    public void onStart() {


        super.onStart();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = (ImageView) v.findViewById(R.id.imageView2);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
          String position = bundle.getString("Position");
            Picasso.with(getActivity())
                    .load("http://fmsindia.net/"+position).placeholder(R.drawable.mandefault).into(imageView);
        }
    }





}
