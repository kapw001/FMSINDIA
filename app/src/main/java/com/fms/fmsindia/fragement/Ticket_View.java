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
import com.fms.fmsindia.adapter.Custom_Ongoing_Adapter;
import com.fms.fmsindia.fragmentcallback.FragmentCallback;
import com.fms.fmsindia.webservice.RequestManager;
import com.fms.fmsindia.webservice.ResponseListener;
import com.fms.fmsindia.webservices.request.PrescriptionsReq;
import com.fms.fmsindia.webservices.request.TicketAcceptReq;
import com.fms.fmsindia.webservices.response.BaseRS;
import com.fms.fmsindia.webservices.response.PrescriptionsHistoryRs;
import com.fms.fmsindia.webservices.response.TicketAcceptInfoRs;
import com.fms.fmsindia.webservices.response.TicketViewInfoRs;
import com.fms.fmsindia.webservices.response.TicketViewRs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by androiduser2 on 18/8/15.
 */
public class Ticket_View extends FragmentCallback implements ResponseListener,Custom_Ongoing_Adapter.CallbackUploadAdapter {
    android.view.View rootView;
    ListView lv;
    LinearLayout noTicket;
    private List<PrescriptionsHistoryRs> prescriptionsHistoryRsList = new ArrayList<PrescriptionsHistoryRs>();
    PrescriptionsReq prescriptions;
    Custom_Ongoing_Adapter adapter;
    TicketAcceptReq ticketAcceptReq;
    ArrayList<TicketViewRs> arrayCars;
    List<TicketViewRs> ticketViewRs;
    private Fragment fragment;
    List<TicketViewRs> listCars = new ArrayList<TicketViewRs>();
    public Ticket_View() {

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
    public void onStart()
    {
        super.onStart();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            getArguments().remove("PRESC_LIST");
        }

        Home mainActivity = (Home) getActivity();
        mainActivity.getFragementTiltle().setTitle("Open Tickets");



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

        RequestManager.getViewTicket(getActivity(), prescriptions, this);





    }
    @Override
    public void onResponseReceived(Object responseObj, int requestType) {

        if (responseObj != null) {

            String responseCode = ((BaseRS) responseObj).responseCode;


            if (responseCode.equals("S")) {
                noTicket.setVisibility(View.GONE);
                 ticketViewRs = ((TicketViewInfoRs) responseObj).ticketView;
                adapter = new Custom_Ongoing_Adapter(getActivity(), ticketViewRs);
                adapter.setCallback(this);
                lv.setAdapter(adapter);
                listCars = ticketViewRs;
                Utility.hideProgressDialog(getActivity());
            }else if(responseCode.equals("YS")){
                Utility.hideProgressDialog(getActivity());
                String acceptTicket = ((TicketAcceptInfoRs) responseObj).ticketNo;
                String ticketStatus = ((TicketAcceptInfoRs) responseObj).assigned;
                for (int i = 0;i<ticketViewRs.size();i++){
                    if(ticketViewRs.get(i).ticketNo.equals(acceptTicket)){
                        ticketViewRs.get(i).accept_status = "1";
                        ticketViewRs.get(i).ticketstatus = "Assigned";
                    }
                }
               adapter.notifyDataSetChanged();
            }
            else if(responseCode.equals("N")){
                Utility.hideProgressDialog(getActivity());
                noTicket.setVisibility(View.VISIBLE);
            }
        }else {
            Utility.hideProgressDialog(getActivity());
        }

    }


    @Override
    public void setDeleteOnClick(int pos) {
        if(LocalStorage.getPatientObj(getActivity()).role.equals("10") || LocalStorage.getPatientObj(getActivity()).role.equals("0")) {
            fragment = new TicketView_Details();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment).addToBackStack("Ticket Details");
            fragmentTransaction.commit();

            Bundle bundle = new Bundle();
            bundle.putInt("Position", pos);
            bundle.putSerializable("PRESC_LIST", (Serializable) listCars);
            fragment.setArguments(bundle);
        }else {
            fragment = new VendorTicketDetails();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment).addToBackStack("Ticket Details");
            fragmentTransaction.commit();
            Bundle bundle = new Bundle();
            bundle.putInt("Position", pos);
            bundle.putSerializable("PRESC_LIST", (Serializable) listCars);
            fragment.setArguments(bundle);
        }
    }

    @Override
    public void setAcceptOnClick(List<TicketViewRs> listCars,int postion) {

        ticketAcceptReq = new TicketAcceptReq();
        ticketAcceptReq.tecketID = listCars.get(postion).ticketId;
        ticketAcceptReq.ticketNo = listCars.get(postion).ticketNo;
        ticketAcceptReq.firstname = listCars.get(postion).firstname;
        ticketAcceptReq.lastname = listCars.get(postion).lastname;
        ticketAcceptReq.email = listCars.get(postion).username;
        ticketAcceptReq.phone_number = listCars.get(postion).mobile;
        ticketAcceptReq.address = listCars.get(postion).currentaddress;
        ticketAcceptReq.land_mark = listCars.get(postion).landmark;
        ticketAcceptReq.houseid = listCars.get(postion).houseid;
        ticketAcceptReq.assignedtoname = LocalStorage.getPatientObj(getActivity()).email;
        ticketAcceptReq.assignedbyname = LocalStorage.getPatientObj(getActivity()).email;
        ticketAcceptReq.ticketstatusname = "Assigned";
        Utility.showProgressDialog(getActivity());
        RequestManager.TicketAccept(getActivity(),ticketAcceptReq,this);
    }
    public interface OpenTicketCallback{

        void SetOpenTicketTab(String value);
    }

}