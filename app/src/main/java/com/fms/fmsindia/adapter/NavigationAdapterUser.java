package com.fms.fmsindia.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fms.fmsindia.R;


/**
 * Created by karuppasamy on 4/2/16.
 */
public class NavigationAdapterUser extends BaseAdapter {


       private LayoutInflater inflater;
    // private List<BookingHistoryList> historyLists = new ArrayList<BookingHistoryList>();
    private Context mCtx;
    private View v;
    private String[] mainMenu;

    private int type;
    private String[] menuType;


    public NavigationAdapterUser(Context activity,String userName) {


        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //  historyLists = bookingHistoryRsList;
        this.mCtx = activity;

        mainMenu = new String[]{userName};

    }

    public void menuType(int type) {
        this.type = type;

        if (type == 1) {
            this.menuType = mainMenu;
        }
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mainMenu.length;
    }

    @Override
    public Object getItem(int position) {
        return mainMenu[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        v = convertView;

        v = inflater.inflate(R.layout.loginby, viewGroup, false);
        TextView title = (TextView) v.findViewById(R.id.userName);

        TextView title1 = (TextView) v.findViewById(R.id.userNameTitle);

        title.setTextColor(Color.parseColor("#ffffff"));

        title1.setTextColor(Color.parseColor("#ffffff"));

        title.setText(mainMenu[position]);

            return v;


        }



}
