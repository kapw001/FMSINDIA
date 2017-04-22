package com.fms.fmsindia.fragement;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.fms.fmsindia.Home;
import com.fms.fmsindia.R;
import com.fms.fmsindia.Utility.Utility;
import com.fms.fmsindia.fragmentcallback.FragmentCallback;
import com.fms.fmsindia.webservice.RequestManager;
import com.fms.fmsindia.webservice.ResponseListener;
import com.fms.fmsindia.webservices.request.TicketReopenReq;
import com.fms.fmsindia.webservices.response.BaseRS;
import com.fms.fmsindia.webservices.response.TicketViewRs;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by androiduser2 on 13/9/16.
 */
public class TicketReOpen extends FragmentCallback implements View.OnClickListener,ResponseListener {
    EditText ticketNoDetail,issueDetail,CategoryDetail,PriorityDetail,targetDateDetail,preferredCallTimeDetail,preferredTimeToAttendDetail,description;
    LinearLayout ticketOpen;

    TicketReopenReq ticketReopenReq =  new TicketReopenReq();
    Button cancelTicket,reopenTicket;
    List<TicketViewRs> listCars = new ArrayList<TicketViewRs>();
    int position;
    TicketReOpenCallback ticketReOpenCallback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ticketReOpenCallback=(TicketReOpenCallback)activity;
    }

    public TicketReOpen() {
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ticket_reopen,container,false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            listCars = (List<TicketViewRs>) bundle.getSerializable("PRESC_LIST");
            position = bundle.getInt("Position");
        }
        getArguments().remove("PRESC_LIST");
        Bundle bundle1 = this.getArguments();
        if (bundle1 != null) {

            ticketNoDetail = (EditText) v.findViewById(R.id.ticketNoDetail);
            issueDetail = (EditText) v.findViewById(R.id.issueDetail);
            CategoryDetail = (EditText) v.findViewById(R.id.CategoryDetail);
            PriorityDetail = (EditText) v.findViewById(R.id.PriorityDetail);
            targetDateDetail = (EditText) v.findViewById(R.id.targetDateDetail);
            preferredCallTimeDetail = (EditText) v.findViewById(R.id.preferredCallTimeDetail);
            preferredTimeToAttendDetail = (EditText) v.findViewById(R.id.preferredTimeToAttendDetail);
            description = (EditText) v.findViewById(R.id.description);
            cancelTicket = (Button) v.findViewById(R.id.closeTicket);
            reopenTicket = (Button) v.findViewById(R.id.reopenTicket);
            ticketOpen = (LinearLayout) v.findViewById(R.id.ticketOpen);
            ticketReopenReq.ticketNoId = listCars.get(position).ticketId;
            ticketReopenReq.ticketStatus = listCars.get(position).ticketstatus;
            ticketReopenReq.houseid = listCars.get(position).houseid;
            ticketNoDetail.setText(listCars.get(position).ticketNo);
            issueDetail.setText(listCars.get(position).issue);
            CategoryDetail.setText(listCars.get(position).category);
            PriorityDetail.setText(listCars.get(position).priority);
            targetDateDetail.setText(Utility.convertDay(listCars.get(position).targetDate));
            preferredCallTimeDetail.setText(listCars.get(position).pct);
            preferredTimeToAttendDetail.setText(listCars.get(position).pat);
            ticketNoDetail.setEnabled(false);
            issueDetail.setEnabled(false);
            CategoryDetail.setEnabled(false);
            PriorityDetail.setEnabled(false);
            targetDateDetail.setEnabled(false);
            preferredCallTimeDetail.setEnabled(false);
            preferredTimeToAttendDetail.setEnabled(false);
            reopenTicket.setOnClickListener(this);

            cancelTicket.setOnClickListener(this);

            Home mainActivity = (Home) getActivity();
            mainActivity.getFragementTiltle().setTitle("Reopen Ticket");
        }
        return v;
    }
    @Override
    public void onStart() {
        Home home = new Home();
        home.ValueTitle = "Reopen";

        super.onStart();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reopenTicket:

                ticketReopenReq.pat = preferredTimeToAttendDetail.getText().toString();
                ticketReopenReq.ptc = preferredCallTimeDetail.getText().toString();
                ticketReopenReq.reopenReason = description.getText().toString();
                ticketReopenReq.ticketId =ticketNoDetail.getText().toString();


                RequestManager.TicketReopen(getActivity(), ticketReopenReq, this);
                Utility.showProgressDialog(getActivity());
                break;
            case R.id.closeTicket:
                Ticket_View fragment = new Ticket_View();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
                break;
        }
    }

    @Override
    public void onResponseReceived(Object responseObj, int requestType) {
        if (responseObj != null) {

            String responseCode = ((BaseRS) responseObj).responseCode;

            if (responseCode.equals("S"))
            {
                Utility.hideProgressDialog(getActivity());

                Ticket_View fragment = new Ticket_View();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
            }else if (responseCode.equals("N")){
                Utility.hideProgressDialog(getActivity());
            }
        }
    }

    public interface TicketReOpenCallback{

        void SetTicketReOpenCallbackTab();
    }
}
