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

import com.fms.fmsindia.R;
import com.fms.fmsindia.Utility.Utility;
import com.fms.fmsindia.webservices.response.TicketFullDetails;

import java.util.List;


/**
 * Created by androiduser2 on 4/9/15.
 */
public class Dailg extends BaseAdapter {

    Context context;

    protected List<TicketFullDetails> listCars;
    LayoutInflater inflater;

    public Dailg(Context context, List<TicketFullDetails> listCars) {
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
        v = inflater.inflate(R.layout.custom_dialog, null);
        Typeface face = Typeface.createFromAsset(context.getAssets(),
                "fonts/calibri.otf");
        if(listCars.get(position).ticketstatus.equals(""))
        {
            ((LinearLayout) v.findViewById(R.id.ValueList)).setVisibility(View.GONE);
        }else {
            ((LinearLayout) v.findViewById(R.id.ValueList)).setVisibility(View.VISIBLE);

            ((TextView) v.findViewById(R.id.reasonLog)).setText(listCars.get(position).ticketstatus);
            ((TextView) v.findViewById(R.id.reasonLog)).setTypeface(face);
            ((TextView) v.findViewById(R.id.reasonLog)).setTextColor(Color.parseColor("#484848"));
            ((TextView) v.findViewById(R.id.LogTime)).setText(Utility.convertDayTime(listCars.get(position).logtime));
            ((TextView) v.findViewById(R.id.LogTime)).setTypeface(face);
            ((TextView) v.findViewById(R.id.LogTime)).setTextColor(Color.parseColor("#484848"));
            if(listCars.get(position).assignedby.equals("")){
                ((LinearLayout) v.findViewById(R.id.assignedByLayout)).setVisibility(View.GONE);
            }else {
                ((LinearLayout) v.findViewById(R.id.assignedByLayout)).setVisibility(View.VISIBLE);
            }
            if(listCars.get(position).assignedto.equals("")){
                ((LinearLayout) v.findViewById(R.id.assignedToLayout)).setVisibility(View.GONE);
            }else {
                ((LinearLayout) v.findViewById(R.id.assignedToLayout)).setVisibility(View.VISIBLE);
            }
            if(listCars.get(position).resolvedby.equals("")){
                ((LinearLayout) v.findViewById(R.id.resolvedByLayout)).setVisibility(View.GONE);
            }else {
                ((LinearLayout) v.findViewById(R.id.resolvedByLayout)).setVisibility(View.VISIBLE);
            }

            if(listCars.get(position).description.equals("")){
                ((LinearLayout) v.findViewById(R.id.descriptionLayout)).setVisibility(View.GONE);
            }else {
                ((LinearLayout) v.findViewById(R.id.descriptionLayout)).setVisibility(View.VISIBLE);
            }

            ((TextView) v.findViewById(R.id.assignedBy)).setText(listCars.get(position).assignedby);
            ((TextView) v.findViewById(R.id.assignedTo)).setText(listCars.get(position).assignedto);
            ((TextView) v.findViewById(R.id.assignedBy)).setTypeface(face);
            ((TextView) v.findViewById(R.id.assignedTo)).setTypeface(face);
            ((TextView) v.findViewById(R.id.assignedBy)).setTextColor(Color.parseColor("#484848"));
            ((TextView) v.findViewById(R.id.assignedTo)).setTextColor(Color.parseColor("#484848"));
            if (listCars.get(position).resolvedby.equals("")) {
                ((TextView) v.findViewById(R.id.resolvedBy)).setVisibility(View.GONE);
            } else {
                ((TextView) v.findViewById(R.id.resolvedBy)).setVisibility(View.VISIBLE);
            }
            ((TextView) v.findViewById(R.id.resolvedBy)).setText(listCars.get(position).resolvedby);
            ((TextView) v.findViewById(R.id.description)).setText(listCars.get(position).description);
            ((TextView) v.findViewById(R.id.resolvedBy)).setTypeface(face);

            ((TextView) v.findViewById(R.id.description)).setTypeface(face);

            ((TextView) v.findViewById(R.id.resolvedBy)).setTextColor(Color.parseColor("#484848"));

            ((TextView) v.findViewById(R.id.description)).setTextColor(Color.parseColor("#484848"));
            ((TextView) v.findViewById(R.id.ticketStatus)).setTypeface(face);

            ((TextView) v.findViewById(R.id.LogTimeTitle)).setTypeface(face);

            ((TextView) v.findViewById(R.id.assignedByTitle)).setTypeface(face);

            ((TextView) v.findViewById(R.id.assignedToTitle)).setTypeface(face);

            ((TextView) v.findViewById(R.id.resolvedByTitle)).setTypeface(face);

            ((TextView) v.findViewById(R.id.descriptionTitle)).setTypeface(face);
            ((TextView) v.findViewById(R.id.ticketStatus)).setTextColor(Color.parseColor("#FF9207A9"));

            ((TextView) v.findViewById(R.id.LogTimeTitle)).setTextColor(Color.parseColor("#FF9207A9"));

            ((TextView) v.findViewById(R.id.assignedByTitle)).setTextColor(Color.parseColor("#FF9207A9"));

            ((TextView) v.findViewById(R.id.assignedToTitle)).setTextColor(Color.parseColor("#FF9207A9"));

            ((TextView) v.findViewById(R.id.resolvedByTitle)).setTextColor(Color.parseColor("#FF9207A9"));

            ((TextView) v.findViewById(R.id.descriptionTitle)).setTextColor(Color.parseColor("#FF9207A9"));

        }
        return v;
    }

    private class ViewHolder {
        TextView tv, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10;
        TextView txtColor;
        TextView txtPrice;
        ImageView imgCar;
    }

}
