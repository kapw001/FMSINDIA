package com.fms.fmsindia.fragement;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.fms.fmsindia.R;


/**
 * Created by Admin on 04-06-2015.
 */
public class ContentFragment extends Fragment {
    public TabLayout tabLayout;
    public ViewPager viewPager;
    private Toolbar toolbar;

    String value="";

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_fragment,container,false);

//        mTabHost = (FragmentTabHost)v.findViewById(android.R.id.tabhost);
//        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realTabContent);
//        mTabHost.setBackgroundColor(getResources().getColor(R.color.blue));
        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Ticket"));
        tabLayout.addTab(tabLayout.newTab().setText("Closed tickets"));
        tabLayout.setTabTextColors(Color.parseColor("#484848"), Color.parseColor("#891e9a"));

        final ViewPager viewPager = (ViewPager) v.findViewById(R.id.viewpager);

        PagerAdapter myFragmentPagerAdapter = new PagerAdapter(getChildFragmentManager(), 2);
        viewPager.setAdapter(myFragmentPagerAdapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        return v;
    }

    private void changeTabsFont() {

        String fontPath = "fonts/Helvetica_font.otf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(tf);
                }
            }
        }
    }


    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    Ticket_View tab1 = new Ticket_View();
                    return tab1;
                case 1:
                    Ticket_View_Delete tab2 = new Ticket_View_Delete();
                    return tab2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}
//        Home home = new Home();
//        home.ValueTitle = "Ticket";
//        Bundle bundle1 = this.getArguments();
//        if (bundle1 != null) {
//
//            value = bundle1.getString("Position");
//
//        }
//        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator("Ticket"),
//                Ticket_View.class, null);
//
////        if(LocalStorage.getPatientObj(getActivity()).role.equals("10")  || LocalStorage.getPatientObj(getActivity()).role.equals("0"))
////        {
////            Bundle b = new Bundle();
////            b.putString("values",value);
////            mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator("Raise Ticket"),
////                    Booking.class, b);
////        }
//
//
//        mTabHost.addTab(mTabHost.newTabSpec("fragmentd").setIndicator("Closed tickets"),
//                Ticket_View_Delete.class, null);
//
//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            mTabHost.setCurrentTab(1);
//
//        }
//
//
//        ;
//        return v;
//    }
//}
