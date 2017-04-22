package com.fms.fmsindia;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fms.fmsindia.Utility.Utility;
import com.fms.fmsindia.adapter.NavigationAdapter;
import com.fms.fmsindia.adapter.NavigationAdapterUser;
import com.fms.fmsindia.fragement.Booking;
import com.fms.fmsindia.fragement.DashBoard;
import com.fms.fmsindia.fragement.HomeFragment;
import com.fms.fmsindia.fragement.Profile;
import com.fms.fmsindia.fragement.TicketReOpen;
import com.fms.fmsindia.fragement.TicketView_Details;
import com.fms.fmsindia.fragement.Ticket_View;
import com.fms.fmsindia.fragement.Ticket_View_Delete;
import com.fms.fmsindia.fragmentcallback.FragmentCallback;
import com.fms.fmsindia.registion.Login;
import com.fms.fmsindia.webservice.RequestManager;
import com.fms.fmsindia.webservice.ResponseListener;
import com.fms.fmsindia.webservices.request.OtpNumberReq;
import com.fms.fmsindia.webservices.request.UserInfoReq;
import com.fms.fmsindia.webservices.response.BaseRS;
import com.fms.fmsindia.webservices.response.UserInfoRs;
import com.fms.fmsindia.webservices.response.UserRs;
import com.fms.fmsindia.webservices.response.VendorTicketAssignedRs;
import com.fms.fmsindia.webservices.response.VendorUserInfoRs;

import java.util.List;


public class Home extends AppCompatActivity implements FragmentCallback.FragmentCallBackListener, DashBoard.DashCallback, HomeFragment.HomeCallback, TicketReOpen.TicketReOpenCallback,Booking.BookCallback,TicketView_Details.TicketViewDetailsback, ResponseListener, View.OnClickListener {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    LinearLayout dash;
    public String ValueTitle;
    UserInfoReq userInfoReq;
    private NavigationAdapter mAdapter;

    ListView listmenuMain1;

    private NavigationAdapterUser mAdapterUser;

    VendorUserInfoRs userInfoRs = new VendorUserInfoRs();
    TextView phoneNoHome;
    OtpNumberReq otpNumberReq = new OtpNumberReq();
    LocalStorageSQ localStorageSQ;
    private Fragment mProfileFragement;
    LinearLayout painting, pluming, sump_tank_clean, carpenter, electrican;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        userInfoReq = new UserInfoReq();
        userInfoReq.userId = LocalStorage.getPatientObj(this).userId;


        dash = (LinearLayout) findViewById(R.id.dash);
        //RequestManager.getUserInfo(this,userInfoReq,this);


//        painting = (LinearLayout) findViewById(R.id.painting);
//
//
//        pluming = (LinearLayout) findViewById(R.id.pluming);
//
//        sump_tank_clean = (LinearLayout) findViewById(R.id.sump_tank_clean);
//
//        carpenter = (LinearLayout) findViewById(R.id.carpenter);
//
//        electrican = (LinearLayout) findViewById(R.id.electrican);
//        painting.setOnClickListener(this);
//        pluming.setOnClickListener(this);
//        sump_tank_clean.setOnClickListener(this);
//        carpenter.setOnClickListener(this);
//        electrican.setOnClickListener(this);

        localStorageSQ = new LocalStorageSQ(this);

        Utility.showProgressDialog(this);

        RequestManager.getUserInfo(this, userInfoReq, this);
        //RequestManager.getVendorTicketUser(this,this);
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdapter = new NavigationAdapter(this);
        mAdapter.menuType(1);

         listmenuMain1 = (ListView) findViewById(R.id.list1);

        TextView textView = (TextView) findViewById(R.id.bottem);

        textView.setText("A product of \n CIEO Technologies Pvt Ltd");

        textView.setTextColor(Color.parseColor("#ffffff"));
        mAdapterUser = new NavigationAdapterUser(this,LocalStorage.getPatientObj(this).firstName+" "+LocalStorage.getPatientObj(this).lastName);
       // mAdapterUser = new NavigationAdapterUser(this,LocalStorage.getPatientObj(this).firstName+" "+LocalStorage.getPatientObj(this).lastName);
                mAdapterUser.menuType(1);
                listmenuMain1.setAdapter(mAdapterUser);

        ListView listmenuMain = (ListView) findViewById(R.id.list);

        listmenuMain.setAdapter(mAdapter);

        listmenuMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                switch (position) {

                    case 1:

                        mProfileFragement = new Profile();
                        android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.frame, mProfileFragement).addToBackStack("nav");
                        fragmentTransaction1.commit();
                        toolbar.setTitle("Profile");
                        drawerLayout.closeDrawers();

                        break;
                    case 2:

                        mProfileFragement = new Ticket_View();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame, mProfileFragement).addToBackStack("nav");;
                        fragmentTransaction.commit();
                        toolbar.setTitle("Open Tickets");
                        drawerLayout.closeDrawers();
                        break;
                   /* case 3:
                     *//*   DirectBuy buyFragment = new DirectBuy();
                        setFragment(buyFragment, "Direct Buy");*//*
                        break;*/


                    case 0:
                        mProfileFragement = new DashBoard();
                        android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.frame, mProfileFragement).addToBackStack("nav");;
                        fragmentTransaction2.commit();
                        toolbar.setTitle(Html.fromHtml("FMSINDIA"));
                        drawerLayout.closeDrawers();
                        break;


                    default:
                        Toast.makeText(getApplicationContext(), "Happy to coming soon", Toast.LENGTH_SHORT).show();
                        break;

                }

            }
        });

        getSupportActionBar().setTitle(Html.fromHtml("<p>FMSINDIA</p>"));

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);



        //navigationView.setNavigationItemSelectedListener(this);

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override

            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };
        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
        mProfileFragement = new DashBoard();
        android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction2.replace(R.id.frame, mProfileFragement);;
        fragmentTransaction2.commit();
        toolbar.setTitle(Html.fromHtml("FMSINDIA"));


    }


    private void setFragment(Fragment pagePosition, String title) {

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, pagePosition);
        fragmentTransaction.commit();
        toolbar.setTitle(title);
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            this.startActivity(new Intent(this, Login.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void navigateFragment(Fragment fragment, String title) {
        FragmentManager fragmentTransaction;
        fragmentTransaction = getSupportFragmentManager();
        fragmentTransaction.beginTransaction().replace(R.id.frame, fragment, title).addToBackStack(title).commit();
    }


    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onResponseReceived(Object responseObj, int requestType) {
        if (responseObj != null) {

            String responseCode = ((BaseRS) responseObj).responseCode;

            Utility.hideProgressDialog(this);

            if (responseCode.equals("Y")) {

                List<UserRs> userRs = ((UserInfoRs) responseObj).userRs;
                List<VendorTicketAssignedRs> vendorTicketAssignedRs = ((UserInfoRs) responseObj).assignedRs;

//                otpNumberReq.userId = userRs.get(0).userId;
//                otpNumberReq.phoneNo = userRs.get(0).phone;
//                otpNumberReq.firstName = userRs.get(0).firstName;
//                otpNumberReq.lastName = userRs.get(0).lastName;
               //otpNumberReq.email = userRs.get(0).eMail;
//                otpNumberReq.currentAddress = userRs.get(0).currentAddress;
//                otpNumberReq.landmark = userRs.get(0).landmark;
//                otpNumberReq.role = userRs.get(0).role;
//                otpNumberReq.houseId = userRs.get(0).houseId;
//                otpNumberReq.attachmentname = userRs.get(0).attachmentname;
//                otpNumberReq.userimage = userRs.get(0).userimage;
               // LocalStorage.storePatientObj(this, otpNumberReq);
//
                localStorageSQ.clearPrescTable();
                localStorageSQ.addVendorUser(vendorTicketAssignedRs);
                // if (LocalStorage.getPatientObj(this).role.equals("0") || LocalStorage.getPatientObj(this).role.equals("10")) {
//                    dash.setVisibility(View.VISIBLE);

                //onNavigationItemSelected(navigationView.getMenu().findItem(R.id.home));
                //}

            }
//            else if(responseCode.equals("YS")){
//
//                List<VendorTicketAssignedRs> vendorTicketAssignedRs = ((VendorUserInfoRs) responseObj).assignedRs;
//
//                localStorageSQ.clearPrescTable();
//                localStorageSQ.addVendorUser(vendorTicketAssignedRs);
//
//
//            }
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void SetTab(String value) {
        if (value.equals("rasiticket")) {
            if (LocalStorage.getPatientObj(this).role.equals("0") || LocalStorage.getPatientObj(this).role.equals("10")) {
                HomeFragment fragment = new HomeFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment).addToBackStack(value);
                fragmentTransaction.commit();

                toolbar.setTitle("Raise Ticket");
            }
        } else if (value.equals("openticket")) {
            Ticket_View fragment = new Ticket_View();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment).addToBackStack(value);
            fragmentTransaction.commit();

            toolbar.setTitle("Open Tickets");
        } else if (value.equals("closedticket")) {
            Ticket_View_Delete fragment = new Ticket_View_Delete();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment).addToBackStack(value);
            fragmentTransaction.commit();

            toolbar.setTitle("Closed Tickets");
        } else if (value.equals("profiles")) {
            Profile fragment = new Profile();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment).addToBackStack(value);
            fragmentTransaction.commit();
            toolbar.setTitle("Profile");

        } else if (value.equals("feedbacks")) {

            Toast toast = Toast.makeText(this, Html.fromHtml("<font color='#ffffff' >Feedback module is under construction</font>"), Toast.LENGTH_LONG);

            toast.show();
        } else if (value.equals("partnerus")) {

            Toast toast = Toast.makeText(this, Html.fromHtml("<font color='#ffffff' >Partner Us module is under construction</font>"), Toast.LENGTH_LONG);

            toast.show();
        }
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//
//        //Checking if the item is in checked state or not, if not make it in checked state
//        if (menuItem.isChecked()) menuItem.setChecked(false);
//        else menuItem.setChecked(true);
//
//
//        //Closing drawer on item click
//
//
//        drawerLayout.closeDrawers();
//
//        //Check to see which item was being clicked and perform appropriate action
//        switch (menuItem.getItemId()) {
//
//
//            //Replacing the main content with ContentFragment Which is our Inbox View;
//            case R.id.ticket:
//
//                mProfileFragement = new Ticket_View();
//                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame, mProfileFragement);
//                fragmentTransaction.commit();
//                toolbar.setTitle("Ticket");
//                return true;
//            case R.id.home:
//                mProfileFragement = new DashBoard();
//                android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction2.replace(R.id.frame, mProfileFragement);
//                fragmentTransaction2.commit();
//
//
//                toolbar.setTitle(Html.fromHtml("FMSINDIA"));
//
//
//                return true;
//
//            // For rest of the options we just show a toast on click
//
//            case R.id.profile:
//                mProfileFragement = new Profile();
//                android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction1.replace(R.id.frame, mProfileFragement);
//                fragmentTransaction1.commit();
//                toolbar.setTitle("Profile");
//                return true;
////                    case R.id.sent_mail:
////                        Vendors fragment2 = new Vendors();
////                        android.support.v4.app.FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
////                        fragmentTransaction2.replace(R.id.frame,fragment2);
////                        fragmentTransaction2.commit();
////                        toolbar.setTitle("Vendor");
////                        return true;
//
//            default:
//                Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
//                return true;
//        }
//
//    }

    @Override
    public void SetTicketTab(String value) {
        Booking fragment = new Booking();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).addToBackStack(value);
        fragmentTransaction.commit();
        Bundle bundle = new Bundle();
        bundle.putString("values", value);
        fragment.setArguments(bundle);
        toolbar.setTitle("Raise Ticket");
    }

    @Override
    public void onBackPressed() {
        boolean drawerOpen = drawerLayout.isDrawerOpen(navigationView);

        if (drawerOpen) {
            drawerLayout.closeDrawers();
            return;
        }
        FragmentManager fm = getSupportFragmentManager();
        String[] menufragement = {"ViewAppointmentHospital", "AppointmentConfirmation", "AppointmentBookingID"};
        boolean isClear = false;
        FragmentTransaction fragmentTransactionManager1 = fm.beginTransaction();
      // String tiltle1[] = fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 1).getName().split("\\{");

        int k;

            k = fm.getBackStackEntryCount() - 1;

        Log.i("value","===>"+fm.getBackStackEntryCount());



        for (int i = k; i < fm.getBackStackEntryCount(); i++) {

            fm.popBackStack();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionManager = fragmentManager.beginTransaction();
        try {

            if(fragmentManager.getBackStackEntryCount() != 0) {

                String tiltle[] = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName().split("\\{");

                Log.i("senthil", "======>" + tiltle[0]);


                //toolbar.setTitle(tiltle[0]);
                if (tiltle[0].equals("rasiticket") || tiltle[0].equals("openticket") || tiltle[0].equals("closedticket") || tiltle[0].equals("profiles")) {

                    toolbar.setTitle("FMSINDIA");

                } else if (tiltle[0].equals("Ticket Details") || (tiltle[0].equals("Reopen Ticket"))) {

                    toolbar.setTitle("Open Tickets");
                } else if (tiltle[0].equals("Closed Ticket")) {

                    toolbar.setTitle("Closed Tickets");
                }
                else if (tiltle[0].equals("nav")) {

                    toolbar.setTitle("FMSINDIA");
                }else if (tiltle[0].equals("FMSINDIA")){
                    doExit();
                }
            }else {
                toolbar.setTitle("FMSINDIA");

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure,you want to exit this app?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        doExit();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }

                });

                builder.show();


            }

        } catch (ArrayIndexOutOfBoundsException e) {

        }


    }
    public void doExit() {



        finish();

    }
    public Toolbar getFragementTiltle() {
        return toolbar;
    }

    @Override
    public void SetBookTab() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); i++) {

            fm.popBackStack();
        }
        Ticket_View ticket_view = new Ticket_View();
        android.support.v4.app.FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, ticket_view).addToBackStack("openticket");
        fragmentTransaction.commit();
    }

    @Override
    public void SetTicketReOpenCallbackTab() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 1; i < fm.getBackStackEntryCount(); i++) {

            fm.popBackStack();
        }

    }

    @Override
    public void SetTicketViewDetailTab() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); i++) {

            fm.popBackStack();
        }
        Ticket_View_Delete fragment = new Ticket_View_Delete();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}
