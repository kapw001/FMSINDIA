package com.fms.fmsindia.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fms.fmsindia.R;


/**
 * Created by karuppasamy on 4/2/16.
 */
public class NavigationAdapter extends BaseAdapter {


    LinearLayout mLayout;
    int imgIcons[] = {R.drawable.ic_home_black, R.mipmap.ic_account_outline_black_24dp, R.mipmap.ic_file_document_box_black_24dp};
    private LayoutInflater inflater;
    // private List<BookingHistoryList> historyLists = new ArrayList<BookingHistoryList>();
    private Context mCtx;
    private View v;
    private String[] mainMenu;

    private int type;
    private String[] menuType;


    public NavigationAdapter(Context activity) {


        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //  historyLists = bookingHistoryRsList;
        this.mCtx = activity;

        mainMenu = new String[]{"Home", "Profile", "Tickets"};

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

        v = inflater.inflate(R.layout.navigation_list, viewGroup, false);
        TextView title = (TextView) v.findViewById(R.id.title);


        title.setTextColor(Color.parseColor("#ffffff"));
        ImageView navigateImage = (ImageView) v.findViewById(R.id.imageView);
        LinearLayout header = (LinearLayout) v.findViewById(R.id.headeLayout);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) header.getLayoutParams();


        if (type == 1) {
            //getItem()
            navigateImage.setVisibility(View.VISIBLE);
            title.setText(mainMenu[position]);
            navigateImage.setImageBitmap(BitmapFactory.decodeResource(
                    mCtx.getResources(), imgIcons[position]));

            Drawable myIcon = mCtx.getResources().getDrawable(imgIcons[position]);
            ColorFilter filter = new LightingColorFilter(mCtx.getResources().getColor(R.color.white), mCtx.getResources().getColor(R.color.white));
            myIcon.setColorFilter(filter);
            navigateImage.setImageDrawable(myIcon);
        }


            return v;


        }



}
