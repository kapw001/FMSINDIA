package com.fms.fmsindia.fragement;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fms.fmsindia.Home;
import com.fms.fmsindia.LocalStorage;
import com.fms.fmsindia.LocalStorageSQ;
import com.fms.fmsindia.R;
import com.fms.fmsindia.Utility.Utility;
import com.fms.fmsindia.adapter.Dailg;
import com.fms.fmsindia.fragmentcallback.FragmentCallback;
import com.fms.fmsindia.webservice.RequestManager;
import com.fms.fmsindia.webservice.ResponseListener;
import com.fms.fmsindia.webservices.request.PaymentReq;
import com.fms.fmsindia.webservices.request.VendorTicketCheckReq;
import com.fms.fmsindia.webservices.request.VendorTicketDetailsReq;
import com.fms.fmsindia.webservices.response.BaseRS;
import com.fms.fmsindia.webservices.response.PaymentInfoRs;
import com.fms.fmsindia.webservices.response.TicketFullDetails;
import com.fms.fmsindia.webservices.response.TicketViewRs;
import com.fms.fmsindia.webservices.response.VendorTicketAssignedRs;
import com.fms.fmsindia.webservices.response.VendorTicketDetailsInfoRs;
import com.fms.fmsindia.webservices.response.VendorTicketDetailsRs;
import com.fms.fmsindia.webservices.response.VendorTicketStatusRs;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by senthil on 9/13/16.
 */
public class VendorTicketDetails extends FragmentCallback implements View.OnClickListener, ResponseListener {

    EditText ticketNoDetail, issueDetail, CategoryDetail, PriorityDetail, targetDateDetail, preferredCallTimeDetail, preferredTimeToAttendDetail, description, name, ticketLastName, ticketPhoneNo, ticketEmailAddress, ticketAddressForCommunication, ticketLandMark, userDetails, vendorDescription, vendorUserDescription, amount, ReopenTicketLog;

    Button exitTicket, submitTicket, cancel, ok, close;

    TextView details;

    String LogTicket;

    ArrayList<String> imageGalleryUrls = new ArrayList<String>();

    private CardView mCardView;

    Dailg adapter;

    LocalStorageSQ localStorage;

    String payStatus;

    LinearLayout resolvedLinearLayout;


    List<TicketFullDetails> ticketFullDet = new ArrayList<TicketFullDetails>();

    List<VendorTicketAssignedRs> getTicketAssigned = new ArrayList<VendorTicketAssignedRs>();

    int status = 0;

    VendorTicketCheckReq vendorTicketCheckReq;

    private boolean isCalledStatus = false;

    private boolean isCalled = false;

    VendorTicketDetailsReq vendorTicketDetailsReq;

    List<VendorTicketStatusRs> getCategoryRS = new ArrayList<VendorTicketStatusRs>();

    List<VendorTicketAssignedRs> getCategoryAssigned = new ArrayList<VendorTicketAssignedRs>();

    PaymentReq paymentReq = new PaymentReq();
    List<String> sam = new ArrayList<String>();

    List<String> listStatus = new ArrayList<String>();

    List<String> listAssignedTo = new ArrayList<String>();

    List<String> listResolvedBy = new ArrayList<String>();

    List<String> listPayment = new ArrayList<String>();

    Spinner assignedTo, resolvedBy, ticketStatus, modeOfPayment;

    ImageView imageViewList;

    LinearLayout linearLayoutTicketStatus, amountLinearLayout, reasonLayout;

    List<TicketViewRs> listCars = new ArrayList<TicketViewRs>();

    int position;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.vendor_ticket_details, container, false);

        ticketNoDetail = (EditText) v.findViewById(R.id.ticketNoDetail);

        issueDetail = (EditText) v.findViewById(R.id.issueDetail);

        mCardView = (CardView) v.findViewById(R.id.card_view_image);

        CategoryDetail = (EditText) v.findViewById(R.id.CategoryDetail);

        imageViewList = (ImageView) v.findViewById(R.id.imageViewLink);

        PriorityDetail = (EditText) v.findViewById(R.id.PriorityDetail);

        targetDateDetail = (EditText) v.findViewById(R.id.targetDateDetail);

        preferredCallTimeDetail = (EditText) v.findViewById(R.id.preferredCallTimeDetail);

        preferredTimeToAttendDetail = (EditText) v.findViewById(R.id.preferredTimeToAttendDetail);

        linearLayoutTicketStatus = (LinearLayout) v.findViewById(R.id.linearLayoutTicketStatus);

        description = (EditText) v.findViewById(R.id.description);

        name = (EditText) v.findViewById(R.id.name);

        ticketLastName = (EditText) v.findViewById(R.id.ticketLastName);

        ticketPhoneNo = (EditText) v.findViewById(R.id.ticketPhoneNo);

        ticketEmailAddress = (EditText) v.findViewById(R.id.ticketEmailAddress);

        ticketAddressForCommunication = (EditText) v.findViewById(R.id.ticketAddressForCommunication);

        ticketLandMark = (EditText) v.findViewById(R.id.ticketLandMark);

        userDetails = (EditText) v.findViewById(R.id.userDetails);

        assignedTo = (Spinner) v.findViewById(R.id.assignedTo);

        resolvedBy = (Spinner) v.findViewById(R.id.resolvedBy);

        resolvedLinearLayout = (LinearLayout) v.findViewById(R.id.resolvedLinearLayout);

        ticketStatus = (Spinner) v.findViewById(R.id.ticketStatus);

        modeOfPayment = (Spinner) v.findViewById(R.id.modeOfPayment);

        vendorDescription = (EditText) v.findViewById(R.id.vendorDescriptionOne);

        vendorUserDescription = (EditText) v.findViewById(R.id.vendorUserDescription);


        ReopenTicketLog = (EditText) v.findViewById(R.id.ReopenTicketLog);

        details = (TextView) v.findViewById(R.id.details);

        close = (Button) v.findViewById(R.id.close);

        amount = (EditText) v.findViewById(R.id.amount);
        //  exitTicket = (Button) v.findViewById(R.id.exitTicket);
        submitTicket = (Button) v.findViewById(R.id.submitTicket);
        cancel = (Button) v.findViewById(R.id.cancel);
        ok = (Button) v.findViewById(R.id.ok);

        Home mainActivity = (Home) getActivity();
        mainActivity.getFragementTiltle().setTitle("Ticket Details");

        vendorTicketCheckReq = new VendorTicketCheckReq();

        amountLinearLayout = (LinearLayout) v.findViewById(R.id.amountLinearLayout);

        reasonLayout = (LinearLayout) v.findViewById(R.id.reasonLayout);

        reasonLayout.setVisibility(View.GONE);

        amountLinearLayout.setVisibility(View.GONE);

        submitTicket.setOnClickListener(this);

        cancel.setOnClickListener(this);

        imageViewList.setOnClickListener(this);

        localStorage = new LocalStorageSQ(getActivity());

        resolvedLinearLayout.setVisibility(View.GONE);

        listResolvedBy.add("Select");

        ok.setVisibility(View.VISIBLE);

        ok.setOnClickListener(this);

        listPayment.add("Select");

        listPayment.add("Cash");

        listPayment.add("Swipe Machine");

        listPayment.add("Online Payment");

        ArrayAdapter<String> listPaymentAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listPayment);

        // Drop down layout style - list view with radio button

        listPaymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner

        modeOfPayment.setAdapter(listPaymentAdapter);

        vendorTicketDetailsReq = new VendorTicketDetailsReq();

        Bundle bundle = this.getArguments();

        if (bundle != null) {

            listCars = (List<TicketViewRs>) bundle.getSerializable("PRESC_LIST");

            if((List<TicketViewRs>) bundle.getSerializable("PRESC_LIST") != null) {

                position = bundle.getInt("Position");

                sam.add(listCars.get(position).ticketNo);

                vendorTicketDetailsReq.TicketNo = listCars.get(position).ticketNo;
            }else {
                vendorTicketDetailsReq.TicketNo = sam.get(0);
            }
        }
        getArguments().remove("PRESC_LIST");


        RequestManager.VendorTicket(getActivity(), vendorTicketDetailsReq, this);
        Utility.showProgressDialog(getActivity());

        ticketStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                if (isCalledStatus == true) {


                    if (arg2 != 0) {
                        if (LocalStorage.getPatientObj(getActivity()).role.equals("4")) {

                            vendorDescription.setText("");

                        } else {

                            vendorUserDescription.setText("");
                        }

                        Log.d("value", "========");


                        vendorTicketCheckReq.ticketStatus = "" + getCategoryRS.get(arg2 - 1).statusId;

                        if (ticketStatus.getSelectedItem().toString().equals("Resolved")) {


                            if (modeOfPayment.getSelectedItem().toString().equals("Select")) {

                                modeOfPayment.setEnabled(true);
                                amount.setEnabled(true);

                            } else {
                                modeOfPayment.setEnabled(false);
                                amount.setEnabled(false);
                            }
                        }

                        if (vendorTicketCheckReq.ticketStatus.equals("5")) {
                            resolvedLinearLayout.setVisibility(View.VISIBLE);
                            amountLinearLayout.setVisibility(View.VISIBLE);


                        } else {
                            resolvedLinearLayout.setVisibility(View.GONE);
                            amountLinearLayout.setVisibility(View.GONE);
                        }

                    }

                } else {
                    isCalledStatus = true;
                    if (ticketStatus.getSelectedItem().toString().equals("Select")) {

                    } else {
                        vendorTicketCheckReq.ticketStatus = "" + getCategoryRS.get(arg2 - 1).statusId;
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        assignedTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                if (isCalled == true) {

                    if (arg2 != 0) {

                        Log.d("value", "========" + arg2);

                        vendorTicketCheckReq.assignedTo = "" + getCategoryAssigned.get(arg2 - 1).assignedId;

                    }

                } else {
                    isCalled = true;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        resolvedBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                if (isCalled == true) {

                    if (arg2 != 0) {

                        Log.d("value", "========");

                        vendorTicketCheckReq.resolveBy = "" + getCategoryAssigned.get(arg2 - 1).assignedId;

                    }

                } else {
                    isCalled = true;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        details.setOnClickListener(this);
        close.setOnClickListener(this);
        ticketNoDetail.setEnabled(false);
        issueDetail.setEnabled(false);
        CategoryDetail.setEnabled(false);
        PriorityDetail.setEnabled(false);
        targetDateDetail.setEnabled(false);
        preferredCallTimeDetail.setEnabled(false);
        preferredTimeToAttendDetail.setEnabled(false);
        description.setEnabled(false);
        name.setEnabled(false);
        ticketLastName.setEnabled(false);
        ticketPhoneNo.setEnabled(false);
        ticketEmailAddress.setEnabled(false);
        ticketAddressForCommunication.setEnabled(false);
        ticketLandMark.setEnabled(false);
        userDetails.setBackgroundColor(Color.parseColor("#c1c1c1"));
        userDetails.setEnabled(false);
        ReopenTicketLog.setEnabled(false);
        ReopenTicketLog.setBackgroundColor(Color.parseColor("#c1c1c1"));
        if (LocalStorage.getPatientObj(getActivity()).role.equals("6")) {
            vendorDescription.setEnabled(false);
            vendorUserDescription.setEnabled(true);
            vendorDescription.setBackgroundColor(Color.parseColor("#c1c1c1"));

        } else {
            //vendorDescription.setBackgroundColor(Color.parseColor("#ffffff"));

            vendorUserDescription.setEnabled(false);
            vendorDescription.setEnabled(true);
        }
        close.setVisibility(View.GONE);

        vendorDescription.setOnClickListener(this);
//        vendorDescription.addTextChangedListener(watch);

        return v;

    }


    public void check() {

        vendorTicketCheckReq.ticketId = ticketNoDetail.getText().toString();

//        if (resolvedBy.getSelectedItem().toString().equals("CEIO VendorUser")) {
//            vendorTicketCheckReq.resolveBy = "5";
//        } else {
//            vendorTicketCheckReq.resolveBy ="";
//        }
//        if (assignedTo.getSelectedItem().toString().equals("CEIO VendorUser")) {
//            if (LocalStorage.getPatientObj(getActivity()).role.equals("4")) {
//                vendorTicketCheckReq.assignedTo = "5";
//            } else {
//                vendorTicketCheckReq.assignedTo = "5";
//            }
//        } else {
//            vendorTicketCheckReq.assignedTo = "";
//        }
        vendorTicketCheckReq.vendorDescription = "" + vendorDescription.getText().toString();
        vendorTicketCheckReq.vendorUserDescription = "" + vendorUserDescription.getText().toString();
        vendorTicketCheckReq.assignedToName = "" + assignedTo.getSelectedItem().toString();

        if (resolvedBy.getSelectedItem().toString().equals("Select")) {

            vendorTicketCheckReq.resolveByName = "";

        } else {
            vendorTicketCheckReq.resolveByName = "" + resolvedBy.getSelectedItem().toString();
        }


        vendorTicketCheckReq.assignedTo = localStorage.getVendorUserNameid(assignedTo.getSelectedItem().toString());

        vendorTicketCheckReq.resolveBy = localStorage.getVendorUserResolvedNameid(resolvedBy.getSelectedItem().toString());

        vendorTicketCheckReq.ticketStatusName = "" + ticketStatus.getSelectedItem().toString();

        if (ticketStatus.getSelectedItem().toString().equals("Select")) {

        } else if (vendorTicketCheckReq.ticketStatus.equals("5")) {
            if (modeOfPayment.getSelectedItem().toString().equals("Cash") || modeOfPayment.getSelectedItem().toString().equals("Swipe Machine")) {
                vendorTicketCheckReq.amount = "" + amount.getText().toString();
                if (payStatus.equals("Paid")) {
                    vendorTicketCheckReq.paymentStatus = "Paid";
                    vendorTicketCheckReq.modeOfPayment = "" + modeOfPayment.getSelectedItem().toString();
                } else {
                    vendorTicketCheckReq.paymentStatus = "Payment Pending";
                    vendorTicketCheckReq.modeOfPayment = "" + modeOfPayment.getSelectedItem().toString();

                }
            } else if (modeOfPayment.getSelectedItem().toString().equals("Online Payment")) {
                vendorTicketCheckReq.amount = "" + amount.getText().toString();
                if (payStatus.equals("Paid")) {
                    vendorTicketCheckReq.paymentStatus = "Paid";
                    vendorTicketCheckReq.modeOfPayment = "" + modeOfPayment.getSelectedItem().toString();
                } else {
                    vendorTicketCheckReq.paymentStatus = "Payment Pending";
                    vendorTicketCheckReq.modeOfPayment = "" + modeOfPayment.getSelectedItem().toString();

                }
            }
        } else {
            if (modeOfPayment.getSelectedItem().toString().equals("Cash") || modeOfPayment.getSelectedItem().toString().equals("Swipe Machine")
                    || modeOfPayment.getSelectedItem().toString().equals("Online Payment")) {
                if (payStatus.equals("Paid")) {
                    vendorTicketCheckReq.amount = "" + amount.getText().toString();
                    vendorTicketCheckReq.paymentStatus = "Paid";
                    vendorTicketCheckReq.modeOfPayment = "" + modeOfPayment.getSelectedItem().toString();
                } else {
                    vendorTicketCheckReq.paymentStatus = "";
                    vendorTicketCheckReq.amount = "";
                    vendorTicketCheckReq.modeOfPayment = "0";
                }
            } else {
                vendorTicketCheckReq.paymentStatus = "";
                vendorTicketCheckReq.amount = "";
                vendorTicketCheckReq.modeOfPayment = "0";

            }

        }

        vendorUserDescription.setOnClickListener(this);

//        vendorDescription.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),"alert test",Toast.LENGTH_SHORT).show();
//                vendorDescription.setText("");
//            }
//        });


        if (ticketStatus.getSelectedItem().toString().equals("Select")) {
            Toast.makeText(getActivity(), "Plz Select the Ticket Status", Toast.LENGTH_LONG).show();
        } else if (ticketStatus.getSelectedItem().toString().equals("Open")) {
            Toast.makeText(getActivity(), "Go Back,Press Cancel or change Ticket Status", Toast.LENGTH_LONG).show();
        } else if (assignedTo.getSelectedItem().toString().equals("Select")) {
            Toast.makeText(getActivity(), "Plz Select the Assigned To", Toast.LENGTH_LONG).show();
        } else if (vendorTicketCheckReq.ticketStatus.equals("5") && resolvedBy.getSelectedItem().toString().equals("Select")) {
            Toast.makeText(getActivity(), "Plz Select the Resoled By", Toast.LENGTH_LONG).show();
        } else if (vendorTicketCheckReq.ticketStatus.equals("5") && modeOfPayment.getSelectedItem().toString().equals("Select")) {
            Toast.makeText(getActivity(), "Plz Select the Mode Of Payment", Toast.LENGTH_LONG).show();
        } else {
            Utility.showProgressDialog(getActivity());
            RequestManager.VendorTicketCheck(getActivity(), vendorTicketCheckReq, this);
        }

    }

    @Override
    public void onStart() {

        Home mainActivity = (Home) getActivity();
        mainActivity.getFragementTiltle().setTitle("Ticket Details");

        if(imageGalleryUrls.size() !=0) {

            if (imageGalleryUrls.get(0).equals("")) {
                mCardView.setVisibility(View.GONE);
            } else {
                mCardView.setVisibility(View.VISIBLE);
                Picasso.with(getActivity())
                        .load("http://fmsindia.net/" + imageGalleryUrls.get(0)).placeholder(R.drawable.mandefault).into(imageViewList);
            }
        }

        super.onStart();
    }

    public void showPopup(List<TicketFullDetails> ticketFull) {
        final Dialog dialog = new Dialog(getActivity());
        // Include dialog.xml file
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        // Set dialog title

        dialog.show();
        List<TicketFullDetails> ticketFull1 = new ArrayList<TicketFullDetails>();


        ticketFull1 = ticketFull;

        for (int i = 0; i < ticketFull.size(); i++) {
            if (ticketFull.get(i).ticketstatus.equals("")) {
                ticketFull.remove(i);
            }
        }
        ListView listView = (ListView) dialog.findViewById(R.id.listView2);

        TextView detailTitle = (TextView) dialog.findViewById(R.id.detailTitle);

        detailTitle.setTextColor(Color.parseColor("#ffffff"));

        adapter = new Dailg(getActivity(), ticketFull1);

        listView.setAdapter(adapter);
        TextView declineButton = (TextView) dialog.findViewById(R.id.declineButton);
        // if decline button is clicked, close the custom dialog
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.submitTicket:

                check();

                break;
            case R.id.cancel:
                Ticket_View fragment = new Ticket_View();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
                break;
            case R.id.details:
                showPopup(ticketFullDet);
                break;
            case R.id.ok:
                Utility.showProgressDialog(getActivity());
                payMent();
                break;
            case R.id.close:
                Ticket_View fragment5 = new Ticket_View();
                android.support.v4.app.FragmentTransaction fragmentTransaction5 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction5.replace(R.id.frame, fragment5);
                fragmentTransaction5.commit();
                break;
            case R.id.vendorUserDescription:

                break;
            case R.id.vendorDescriptionOne:

                break;
            case R.id.imageViewLink:
                img();
                break;
        }
    }

    public void payMent() {
        paymentReq.paymode = modeOfPayment.getSelectedItem().toString();
        paymentReq.ps = "Paid";
        paymentReq.ticketstatus = "5";

        RequestManager.VendorTicketCheckPayment(getActivity(), paymentReq, this);
    }


    @Override
    public void onResponseReceived(Object responseObj, int requestType) {
        if (responseObj != null) {

            String responseCode = ((BaseRS) responseObj).responseCode;


            if (responseCode.equals("S")) {

                List<VendorTicketDetailsRs> vendorTicketDetailsRs = ((VendorTicketDetailsInfoRs) responseObj).ticketView;
                List<VendorTicketStatusRs> vendorTicketStatusRs = ((VendorTicketDetailsInfoRs) responseObj).ticketStatus;
                List<VendorTicketAssignedRs> vendorTicketAssignedRs = ((VendorTicketDetailsInfoRs) responseObj).assignedRs;
                List<TicketFullDetails> ticketFullDetails = ((VendorTicketDetailsInfoRs) responseObj).ticketDetails;

                for (int i = 0; i < ticketFullDetails.size(); i++) {
                    if (ticketFullDetails.get(i).ticketstatus.equals("Reopen")) {

                        vendorTicketCheckReq.assignedByName = LocalStorage.getPatientObj(getActivity()).email;

                    } else {
                        if (ticketFullDetails.get(i).assignedby.equals("")) {
                            vendorTicketCheckReq.assignedByName = LocalStorage.getPatientObj(getActivity()).email;
                        } else {
                            vendorTicketCheckReq.assignedByName = ticketFullDetails.get(i).assignedby;
                        }
                    }
                }

                ticketFullDet = ticketFullDetails;
                getCategoryRS = vendorTicketStatusRs;
                getCategoryAssigned = vendorTicketAssignedRs;

                if (listAssignedTo.size() != 0) {
                    listAssignedTo.clear();
                }
                listAssignedTo.add("Select");
                for (int i = 0; i < vendorTicketAssignedRs.size(); i++) {
                    listAssignedTo.add(vendorTicketAssignedRs.get(i).userName);
                }

                ArrayAdapter<String> listAssignedToAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listAssignedTo);

                // Drop down layout style - list view with radio button
                listAssignedToAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // attaching data adapter to spinner
                assignedTo.setAdapter(listAssignedToAdapter);

                resolvedBy.setAdapter(listAssignedToAdapter);


                if (listStatus.size() != 0) {
                    listStatus.clear();
                }
                listStatus.add("Select");
                for (int i = 0; i < 6; i++) {
                    listStatus.add(vendorTicketStatusRs.get(i).ticketstatus);
                }

                ArrayAdapter<String> listStatusAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listStatus);

                // Drop down layout style - list view with radio button
                listStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // attaching data adapter to spinner
                ticketStatus.setAdapter(listStatusAdapter);


                for (int i = 0; i < vendorTicketDetailsRs.size(); i++) {
                    ticketNoDetail.setText(vendorTicketDetailsRs.get(i).ticketNo);
                    vendorTicketCheckReq.ticketNoId = vendorTicketDetailsRs.get(i).ticketId;
                    vendorTicketCheckReq.houseId = vendorTicketDetailsRs.get(i).houseid;
                    issueDetail.setText(vendorTicketDetailsRs.get(i).issue);
                    CategoryDetail.setText(vendorTicketDetailsRs.get(i).category);
                    PriorityDetail.setText(vendorTicketDetailsRs.get(i).priority);
                    targetDateDetail.setText(Utility.convertDay(vendorTicketDetailsRs.get(i).targetDate));
                    preferredCallTimeDetail.setText(Utility.convertDay(vendorTicketDetailsRs.get(i).pct));
                    preferredTimeToAttendDetail.setText(vendorTicketDetailsRs.get(i).pat);
                    description.setText(vendorTicketDetailsRs.get(i).userDescription);
                    name.setText(vendorTicketDetailsRs.get(i).firstname);
                    ticketLastName.setText(vendorTicketDetailsRs.get(i).lastname);
                    ticketPhoneNo.setText(vendorTicketDetailsRs.get(i).mobile);
                    ticketEmailAddress.setText(vendorTicketDetailsRs.get(i).username);
                    ticketLandMark.setText(vendorTicketDetailsRs.get(i).landmark);
                    ticketAddressForCommunication.setText(vendorTicketDetailsRs.get(i).currentaddress);
                    status = (Integer.parseInt(vendorTicketDetailsRs.get(i).statusid));
                    LogTicket = vendorTicketDetailsRs.get(i).ticketstatus;


                    imageGalleryUrls.add(vendorTicketDetailsRs.get(i).attachmentpath + "" + vendorTicketDetailsRs.get(i).attachmentname);
                    if (vendorTicketDetailsRs.get(i).attachmentpath.isEmpty()) {

                        mCardView.setVisibility(View.GONE);

                    } else {

                        mCardView.setVisibility(View.VISIBLE);

                        Picasso.with(getActivity())
                                .load("http://fmsindia.net/"+vendorTicketDetailsRs.get(i).attachmentpath + "" + vendorTicketDetailsRs.get(i).attachmentname).placeholder(R.drawable.mandefault).into(imageViewList);


                    }

                    paymentReq.ticketid = vendorTicketDetailsRs.get(i).ticketId;

                    payStatus = vendorTicketDetailsRs.get(i).paymentStatus;


                    //ticketStatus.setSelection(Integer.parseInt(vendorTicketDetailsRs.get(i).status) - 1);

                    if (vendorTicketDetailsRs.get(i).ticketstatus.equals("Resolved")) {
                        if (vendorTicketDetailsRs.get(i).paymentMode.equals("Online Payment") || vendorTicketDetailsRs.get(i).paymentStatus.equals("Paid")) {
                            close.setVisibility(View.VISIBLE);
                            ok.setVisibility(View.GONE);
                            submitTicket.setVisibility(View.GONE);
                            cancel.setVisibility(View.GONE);


                        } else {
                            ok.setVisibility(View.VISIBLE);
                            submitTicket.setVisibility(View.GONE);
                            cancel.setVisibility(View.GONE);
                            close.setVisibility(View.VISIBLE);
                        }

                    } else {
                        ok.setVisibility(View.GONE);
                        submitTicket.setVisibility(View.VISIBLE);
                        cancel.setVisibility(View.VISIBLE);
                    }
                    if (vendorTicketDetailsRs.get(i).ticketstatus.equals("Reopen")) {
                    } else {
                        ticketStatus.setSelection(status);
                        String assignId = localStorage.getVendorUser(vendorTicketDetailsRs.get(i).assignedBy);

                        if (assignId.equals("")) {
                            assignedTo.setSelection(0);
                        } else {
                            assignedTo.setSelection(Integer.parseInt(assignId));
                        }

                        String ResolvedBy = localStorage.getVendorUserResolved(vendorTicketDetailsRs.get(i).resolvedBy);


                        if (ResolvedBy.equals("")) {
                            resolvedBy.setSelection(0);
                        } else {
                            resolvedBy.setSelection(Integer.parseInt(ResolvedBy));
                        }
                    }
                    if (vendorTicketDetailsRs.get(i).ticketstatus.equals("Resolved")) {
                        assignedTo.setEnabled(false);

                        //resolvedLinearLayout.setVisibility(View.VISIBLE);

                        resolvedBy.setEnabled(false);

                        ticketStatus.setEnabled(false);

                        modeOfPayment.setEnabled(false);

                        vendorDescription.setEnabled(false);

                        vendorUserDescription.setEnabled(false);

                        amount.setEnabled(false);
                        //  exitTicket = (Button) v.findViewById(R.id.exitTicket);

                    }

                    vendorDescription.setText(vendorTicketDetailsRs.get(i).vendorDescription);

                    vendorUserDescription.setText(vendorTicketDetailsRs.get(i).vendorUserDescription);

                    userDetails.setText("USER NAME : " + vendorTicketDetailsRs.get(i).username + "\n" + "APPARTMENT ID : " + vendorTicketDetailsRs.get(i).appartmentid + "\n BLOCK NUMBER" +
                            vendorTicketDetailsRs.get(i).blocknumber + "\n FLAT NUMBER" + vendorTicketDetailsRs.get(i).flatnumber + "\n PHONE NUMBER" + vendorTicketDetailsRs.get(i).mobile);
                    ReopenTicketLog.setText("TICKETSTATUS: " + vendorTicketDetailsRs.get(i).ticketstatus + "\n" + "REASON FOR REOPEN:  " + vendorTicketDetailsRs.get(i).reason
                            + "\n ASSIGNED BY :" + localStorage.getVendorUserName(vendorTicketDetailsRs.get(i).statusid) + "\n Resolved By :" + localStorage.getVendorUserResolvedName(vendorTicketDetailsRs.get(i).statusid));
                    if (vendorTicketDetailsRs.get(i).ticketstatus.equals("Reopen") || vendorTicketDetailsRs.get(i).paymentStatus.equals("Paid") || vendorTicketDetailsRs.get(i).ticketstatus.equals("Resolved")) {

                        if (vendorTicketDetailsRs.get(i).ticketstatus.equals("Resolved")) {
                            amountLinearLayout.setVisibility(View.VISIBLE);
                            resolvedLinearLayout.setVisibility(View.VISIBLE);
                        } else {
                            amountLinearLayout.setVisibility(View.GONE);
                            resolvedLinearLayout.setVisibility(View.GONE);
                        }

                        //reasonLayout.setVisibility(View.VISIBLE);
                        amount.setText(vendorTicketDetailsRs.get(i).amount);
                        // resolvedLinearLayout.setVisibility(View.VISIBLE);
                        if (vendorTicketDetailsRs.get(i).paymentMode.equals("Cash")) {
                            modeOfPayment.setSelection(1);
                        } else if (vendorTicketDetailsRs.get(i).paymentMode.equals("Swipe Machine")) {
                            modeOfPayment.setSelection(2);
                        } else {
                            modeOfPayment.setSelection(3);
                        }
                        amount.setEnabled(false);
                        modeOfPayment.setEnabled(false);
                    } else {
                        resolvedLinearLayout.setVisibility(View.GONE);
                        amountLinearLayout.setVisibility(View.GONE);
                        reasonLayout.setVisibility(View.GONE);
                        amount.setEnabled(true);
                        modeOfPayment.setEnabled(true);
                    }
                }


                if (modeOfPayment.getSelectedItem().toString().equals("Select")) {

                    modeOfPayment.setEnabled(true);
                    amount.setEnabled(true);

                } else {
                    modeOfPayment.setEnabled(false);
                    amount.setEnabled(false);
                }

                isCalledStatus = false;
                Utility.hideProgressDialog(getActivity());

            } else if (responseCode.equals("YS")) {
                Utility.hideProgressDialog(getActivity());
                if (vendorTicketCheckReq.ticketStatus.equals("5")) {
                    ok.setVisibility(View.VISIBLE);
                }

                Ticket_View fragment = new Ticket_View();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
            } else if (responseCode.equals("SS")) {
                Utility.hideProgressDialog(getActivity());
                String ValueId = ((PaymentInfoRs) responseObj).ticketid;

                //Toast.makeText(getActivity(), "" + ValueId, Toast.LENGTH_LONG).show();

                Ticket_View fragment5 = new Ticket_View();
                android.support.v4.app.FragmentTransaction fragmentTransaction5 = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction5.replace(R.id.frame, fragment5);
                fragmentTransaction5.commit();

            } else if (responseCode.equals("N")) {
                Utility.hideProgressDialog(getActivity());
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
