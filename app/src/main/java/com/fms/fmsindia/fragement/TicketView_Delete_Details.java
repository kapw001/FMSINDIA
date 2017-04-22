package com.fms.fmsindia.fragement;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.fms.fmsindia.Home;
import com.fms.fmsindia.LocalStorage;
import com.fms.fmsindia.LocalStorageSQ;
import com.fms.fmsindia.R;
import com.fms.fmsindia.Utility.Utility;
import com.fms.fmsindia.fragmentcallback.FragmentCallback;
import com.fms.fmsindia.webservice.ResponseListener;
import com.fms.fmsindia.webservices.request.TicketDeleteReq;
import com.fms.fmsindia.webservices.response.BaseRS;
import com.fms.fmsindia.webservices.response.TicketViewDeleteRs;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by androiduser2 on 28/8/15.
 */
public class TicketView_Delete_Details extends FragmentCallback implements View.OnClickListener,ResponseListener {

    EditText ticketNoDetail, issueDetail, CategoryDetail, PriorityDetail, targetDateDetail, preferredCallTimeDetail, preferredTimeToAttendDetail, description,userDetails,ticketAssignedTOEdit,ticketResolvedTO,ticketAmount,ticketModeOfPay,ticketPaymentStatusEdit;

    Button exitTicket;

    TicketDeleteReq ticketDeleteReq;

    private CardView mCardView;

    ImageView imageViewList;

    ArrayList<String> imageGalleryUrls = new ArrayList<String>();

    LocalStorageSQ localStorageSQ;

    LinearLayout linearLayoutTicketStatus,vendorLinearLayout;
    List<TicketViewDeleteRs> listCars = new ArrayList<TicketViewDeleteRs>();
    int position;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ticket_view_delete_detail, container, false);
        ticketNoDetail = (EditText) v.findViewById(R.id.ticketNoDetail);
        issueDetail = (EditText) v.findViewById(R.id.issueDetail);
        CategoryDetail = (EditText) v.findViewById(R.id.CategoryDetail);
        PriorityDetail = (EditText) v.findViewById(R.id.PriorityDetail);
        targetDateDetail = (EditText) v.findViewById(R.id.targetDateDetail);
        preferredCallTimeDetail = (EditText) v.findViewById(R.id.preferredCallTimeDetail);
        preferredTimeToAttendDetail = (EditText) v.findViewById(R.id.preferredTimeToAttendDetail);
        localStorageSQ = new LocalStorageSQ(getActivity());
        description = (EditText) v.findViewById(R.id.description);
        imageViewList = (ImageView) v.findViewById(R.id.imageViewLink);
        exitTicket = (Button) v.findViewById(R.id.exitTicket);
        mCardView = (CardView) v.findViewById(R.id.card_view_image);
        vendorLinearLayout = (LinearLayout) v.findViewById(R.id.vendorLinearLayout);
        userDetails = (EditText) v.findViewById(R.id.userDetails);
        ticketAssignedTOEdit = (EditText) v.findViewById(R.id.ticketAssignedTOEdit);
        ticketResolvedTO = (EditText) v.findViewById(R.id.ticketResolvedTOEdit);
        ticketAmount = (EditText) v.findViewById(R.id.ticketAmountEdit);
        ticketModeOfPay = (EditText) v.findViewById(R.id.ticketModeOfPayEdit);
        ticketPaymentStatusEdit = (EditText) v.findViewById(R.id.ticketPaymentStatusEdit);
        exitTicket.setOnClickListener(this);

        imageViewList.setOnClickListener(this);
        ticketNoDetail.setEnabled(false);
        issueDetail.setEnabled(false);
        CategoryDetail.setEnabled(false);
        PriorityDetail.setEnabled(false);
        targetDateDetail.setEnabled(false);
        preferredCallTimeDetail.setEnabled(false);
        preferredTimeToAttendDetail.setEnabled(false);
        description.setEnabled(false);

        userDetails.setEnabled(false);
        ticketAssignedTOEdit.setEnabled(false);
        ticketResolvedTO.setEnabled(false);
        ticketAmount.setEnabled(false);
        ticketModeOfPay.setEnabled(false);
        ticketPaymentStatusEdit.setEnabled(false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            if((List<TicketViewDeleteRs>) bundle.getSerializable("PRESC_LIST")!= null) {
                listCars = (List<TicketViewDeleteRs>) bundle.getSerializable("PRESC_LIST");
                position = bundle.getInt("Position");
                ticketNoDetail.setText(listCars.get(position).ticketNo);
                issueDetail.setText(listCars.get(position).issue);
                CategoryDetail.setText(listCars.get(position).category);
                PriorityDetail.setText(listCars.get(position).priority);
                targetDateDetail.setText(Utility.convertDay(listCars.get(position).targetDate));
                preferredCallTimeDetail.setText(listCars.get(position).pct);
                preferredTimeToAttendDetail.setText(listCars.get(position).pat);
                description.setText(listCars.get(position).userDescription);
                userDetails.setText("USER NAME : " + listCars.get(position).username + "\n" + "APPARTMENT ID : " + listCars.get(position).appartmentid + "\n BLOCK NUMBER" +
                        listCars.get(position).blocknumber + "\n FLAT NUMBER" + listCars.get(position).flatnumber + "\n PHONE NUMBER" + listCars.get(position).mobile);
                ticketAssignedTOEdit.setText(localStorageSQ.getVendorUserName(listCars.get(position).assignedby));
                ticketResolvedTO.setText(localStorageSQ.getVendorUserResolvedName(listCars.get(position).resolvedby));
                ticketAmount.setText(listCars.get(position).amount);
                ticketModeOfPay.setText(listCars.get(position).paymentmode);
                ticketPaymentStatusEdit.setText(listCars.get(position).paymentStatus);

                imageGalleryUrls.add(listCars.get(position).attachmentpath + "" + listCars.get(position).attachmentname);

                if (listCars.get(position).attachmentpath.isEmpty()) {

                    mCardView.setVisibility(View.GONE);

                } else {

                    mCardView.setVisibility(View.VISIBLE);

                    Picasso.with(getActivity())
                            .load("http://fmsindia.net/"+listCars.get(position).attachmentpath + "" + listCars.get(position).attachmentname).placeholder(R.drawable.mandefault).into(imageViewList);

                }

                if (LocalStorage.getPatientObj(getActivity()).role.equals("4") || LocalStorage.getPatientObj(getActivity()).role.equals("6")) {

                    vendorLinearLayout.setVisibility(View.VISIBLE);

                } else {

                    vendorLinearLayout.setVisibility(View.GONE);

                }


                Home mainActivity = (Home) getActivity();
                mainActivity.getFragementTiltle().setTitle("Closed Ticket");
            }
        }
        getArguments().remove("PRESC_LIST");

        return v;
    }

    @Override
    public void onStart() {
        Home mainActivity = (Home) getActivity();
        mainActivity.getFragementTiltle().setTitle("Closed Ticket");
        if(imageGalleryUrls.size() !=0) {
            if (imageGalleryUrls.get(0).equals("")) {
                mCardView.setVisibility(View.GONE);
            } else {
                mCardView.setVisibility(View.VISIBLE);
                Picasso.with(getActivity())
                        .load("http://fmsindia.net/" + imageGalleryUrls.get(0)).placeholder(R.drawable.mandefault).into(imageViewList);
            }
        }

        if (LocalStorage.getPatientObj(getActivity()).role.equals("4") || LocalStorage.getPatientObj(getActivity()).role.equals("6")) {

            vendorLinearLayout.setVisibility(View.VISIBLE);

        } else {

            vendorLinearLayout.setVisibility(View.GONE);

        }
        super.onStart();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exitTicket:
                Ticket_View_Delete fragment = new Ticket_View_Delete();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
                break;
            case R.id.imageViewLink:
                img();
                break;
        }
    }

    @Override
    public void onResponseReceived(Object responseObj, int requestType) {
        if (responseObj != null) {

            String responseCode = ((BaseRS) responseObj).responseCode;


            if (responseCode.equals("S")) {

                Ticket_View_Delete fragment = new Ticket_View_Delete();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();

            }
        }

            }

    public void img() {
        imageView imageViews = new imageView();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, imageViews).addToBackStack("Ticket Details");
        fragmentTransaction.commit();
        Bundle bundle = new Bundle();
        bundle.putString("Position", imageGalleryUrls.get(0));

        imageViews.setArguments(bundle);
    }
}
