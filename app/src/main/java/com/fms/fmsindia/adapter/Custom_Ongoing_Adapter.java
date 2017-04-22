package com.fms.fmsindia.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.fms.fmsindia.LocalStorage;
import com.fms.fmsindia.R;
import com.fms.fmsindia.Utility.Utility;
import com.fms.fmsindia.webservices.response.TicketViewRs;

import java.util.List;


/**
 * Created by androiduser2 on 4/9/15.
 */
public class Custom_Ongoing_Adapter extends BaseAdapter {

    Context context;
    CallbackUploadAdapter mCallback;
    protected List<TicketViewRs> listCars;
    LayoutInflater inflater;

    public Custom_Ongoing_Adapter(Context context, List<TicketViewRs> listCars) {
        this.listCars = listCars;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public int getCount() {
        return listCars.size();
    }

    @Override
    public Object getItem(int position) {

            return listCars.get(position);
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.custom_list, null);
        Typeface face = Typeface.createFromAsset(context.getAssets(),
                "fonts/calibri.otf");
        ((TextView) v.findViewById(R.id.ticketNo)).setText(listCars.get(position).ticketNo);
        ((TextView) v.findViewById(R.id.dateTime)).setText(Utility.convertDayTime(listCars.get(position).dateTime));
        ((TextView) v.findViewById(R.id.type)).setText(listCars.get(position).issue);
        ((TextView) v.findViewById(R.id.action)).setText(listCars.get(position).ticketstatus);
        ((TextView) v.findViewById(R.id.priority)).setText(listCars.get(position).priority);

        ((TextView) v.findViewById(R.id.ticketNo)).setTypeface(face);;
        ((TextView) v.findViewById(R.id.dateTime)).setTypeface(face);;
        ((TextView) v.findViewById(R.id.type)).setTypeface(face);;
        ((TextView) v.findViewById(R.id.action)).setTypeface(face);;
        ((TextView) v.findViewById(R.id.priority)).setTypeface(face);;


        ((TextView) v.findViewById(R.id.ticketNo)).setTextColor(Color.parseColor("#484848"));
        ((TextView) v.findViewById(R.id.dateTime)).setTextColor(Color.parseColor("#484848"));
        ((TextView) v.findViewById(R.id.type)).setTextColor(Color.parseColor("#484848"));
        ((TextView) v.findViewById(R.id.action)).setTextColor(Color.parseColor("#484848"));
        ((TextView) v.findViewById(R.id.priority)).setTextColor(Color.parseColor("#484848"));
        if(LocalStorage.getPatientObj(context).role.equals("10") || LocalStorage.getPatientObj(context).role.equals("0"))
        {
            ((TextView) v.findViewById(R.id.accept)).setVisibility(View.GONE);
        }else {

            ((TextView) v.findViewById(R.id.accept)).setVisibility(View.VISIBLE);
        }

        ((TextView) v.findViewById(R.id.accept)).setTypeface(face);
        ((TextView) v.findViewById(R.id.accept)).setTextColor(Color.parseColor("#484848"));
        if(listCars.get(position).paymentStatus.equals("")){
            ((TextView) v.findViewById(R.id.payment)).setText("__");
            ((TextView) v.findViewById(R.id.payment)).setTextColor(Color.parseColor("#484848"));
        }else {
            ((TextView) v.findViewById(R.id.payment)).setText(listCars.get(position).paymentStatus);
            ((TextView) v.findViewById(R.id.payment)).setTextColor(Color.parseColor("#FF1655C1"));
        }
        ((TextView) v.findViewById(R.id.payment)).setTypeface(face);
        if(LocalStorage.getPatientObj(context).role.equals("4")){

            if (listCars.get(position).accept_status.equals("0")) {
                ((TextView) v.findViewById(R.id.accept)).setText("Not Accept");
            } else {
                ((TextView) v.findViewById(R.id.accept)).setText("Accepted");
            }

        }else if(LocalStorage.getPatientObj(context).role.equals("6")) {
            if (listCars.get(position).accept_status.equals("0")) {
                ((TextView) v.findViewById(R.id.accept)).setText("Accept");
                ((TextView) v.findViewById(R.id.accept)).setTextColor(Color.parseColor("#ffffff"));
                ((TextView) v.findViewById(R.id.accept)).setBackgroundColor(Color.parseColor("#0099cc"));
            } else {
                ((TextView) v.findViewById(R.id.accept)).setText("Accepted");
                ((TextView) v.findViewById(R.id.accept)).setTextColor(Color.parseColor("#484848"));
                ((TextView) v.findViewById(R.id.accept)).setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        ((LinearLayout) v.findViewById(R.id.viewLayout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.setDeleteOnClick(position);
            }
        });
        ((TextView) v.findViewById(R.id.accept)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LocalStorage.getPatientObj(context).role.equals("6"))
                {
                    mCallback.setAcceptOnClick(listCars,position);
                }
            }
        });
        return v;
    }

    private class ViewHolder {
        TextView tv,tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10;
        TextView txtColor;
        TextView txtPrice;
        ImageView imgCar;
    }
    public void setCallback(CallbackUploadAdapter mCallback) {
        this.mCallback = mCallback;

    }


    public interface CallbackUploadAdapter {

        void setDeleteOnClick(int pos);
        void setAcceptOnClick(List<TicketViewRs> listCars,int postion);


    }
}
