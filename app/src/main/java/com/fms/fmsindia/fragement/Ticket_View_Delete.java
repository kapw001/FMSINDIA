package com.fms.fmsindia.fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.fms.fmsindia.Home;
import com.fms.fmsindia.LocalStorage;
import com.fms.fmsindia.R;
import com.fms.fmsindia.Utility.Utility;
import com.fms.fmsindia.adapter.Custom_Ongoing_Adapter_Delete;
import com.fms.fmsindia.fragmentcallback.FragmentCallback;
import com.fms.fmsindia.webservice.RequestManager;
import com.fms.fmsindia.webservice.ResponseListener;
import com.fms.fmsindia.webservices.request.PrescriptionsReq;
import com.fms.fmsindia.webservices.response.BaseRS;
import com.fms.fmsindia.webservices.response.PrescriptionsHistoryRs;
import com.fms.fmsindia.webservices.response.TicketViewDeleteInfoRs;
import com.fms.fmsindia.webservices.response.TicketViewDeleteRs;
import com.fms.fmsindia.webservices.response.TicketViewRs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by androiduser2 on 13/9/16.
 */
public class Ticket_View_Delete extends FragmentCallback implements ResponseListener,Custom_Ongoing_Adapter_Delete.CallbackUploadAdapter {
    android.view.View rootView;
    ListView lv;
    LinearLayout noTicket;
    private List<PrescriptionsHistoryRs> prescriptionsHistoryRsList = new ArrayList<PrescriptionsHistoryRs>();
    PrescriptionsReq prescriptions;

    ArrayList<TicketViewRs> arrayCars;
    ArrayList<String> imageGalleryUrls = new ArrayList<String>();
    private Fragment fragment;
    List<TicketViewDeleteRs> listCars = new ArrayList<TicketViewDeleteRs>();
    public Ticket_View_Delete() {

    }
    public static String [] prgmNameList={"Let Us C","c++","JAVA","Jsp","ttt"};
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.view_ticket, container, false);

        // expandableListView = (ExpandableListView) rootView.findViewById(R.id.expListView);

        return rootView;
    }

    @Override
    public void onViewCreated(android.view.View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv=(ListView) rootView.findViewById(R.id.listView);

        noTicket = (LinearLayout) rootView.findViewById(R.id.noTicket);

        arrayCars = new ArrayList<TicketViewRs>();

        prescriptions = new PrescriptionsReq();

        prescriptions.UserId = LocalStorage.getPatientObj(getActivity()).userId;

        Utility.showProgressDialog(getActivity());

        noTicket.setVisibility(View.GONE);

        RequestManager.getViewTicketDelete(getActivity(), prescriptions, this);

    }

    @Override
    public void onStart() {
        Home mainActivity = (Home) getActivity();
        mainActivity.getFragementTiltle().setTitle("Closed Tickets");

        super.onStart();
    }
    @Override
    public void onResponseReceived(Object responseObj, int requestType) {
        //Utility.hideProgressDialog(getActivity());
        if (responseObj != null) {

            String responseCode = ((BaseRS) responseObj).responseCode;


            if (responseCode.equals("S")) {
                noTicket.setVisibility(View.GONE);
                List<TicketViewDeleteRs> ticketViewDeleteRs = ((TicketViewDeleteInfoRs) responseObj).ticketView;
                Custom_Ongoing_Adapter_Delete adapter = new Custom_Ongoing_Adapter_Delete(getActivity(), ticketViewDeleteRs);
                adapter.setCallback(this);
                lv.setAdapter(adapter);
                listCars = ticketViewDeleteRs;

                Utility.hideProgressDialog(getActivity());

            }else if(responseCode.equals("N")){
                noTicket.setVisibility(View.VISIBLE);
                Utility.hideProgressDialog(getActivity());

            }
        }

    }


    @Override
    public void setDeleteOnClick(int pos) {
        fragment = new TicketView_Delete_Details();

        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).addToBackStack("Closed Ticket");
        fragmentTransaction.commit();
        Bundle bundle = new Bundle();
        bundle.putInt("Position",pos);
        bundle.putSerializable("PRESC_LIST", (Serializable) listCars);

        fragment.setArguments(bundle);
    }


}