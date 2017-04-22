package com.fms.fmsindia.fragement;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fms.fmsindia.Home;
import com.fms.fmsindia.R;
import com.fms.fmsindia.Utility.Utility;
import com.fms.fmsindia.adapter.Dailg;
import com.fms.fmsindia.fragmentcallback.FragmentCallback;
import com.fms.fmsindia.webservice.RequestManager;
import com.fms.fmsindia.webservice.ResponseListener;
import com.fms.fmsindia.webservices.request.TicketDeleteReq;
import com.fms.fmsindia.webservices.request.VendorTicketDetailsReq;
import com.fms.fmsindia.webservices.response.BaseRS;
import com.fms.fmsindia.webservices.response.TicketFullDetails;
import com.fms.fmsindia.webservices.response.TicketViewRs;
import com.fms.fmsindia.webservices.response.VendorTicketDetailsInfoRs;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by androiduser2 on 28/8/15.
 */
public class TicketView_Details extends FragmentCallback implements View.OnClickListener,ResponseListener {

    EditText ticketNoDetail, issueDetail, CategoryDetail, PriorityDetail, targetDateDetail, preferredCallTimeDetail, preferredTimeToAttendDetail, description;

    Button exitTicket,closeTicket,reOpenTicket;

    TextView ticketDetails;

    private CardView mCardView;

    Dailg adapter;

    List<TicketFullDetails> ticketFullDet = new ArrayList<TicketFullDetails>();

    ArrayList<String> imageGalleryUrls = new ArrayList<String>();

    VendorTicketDetailsReq vendorTicketDetailsReq;

    TicketDeleteReq ticketDeleteReq;

    ImageView imageViewList;

    LinearLayout linearLayoutTicketStatus;
    List<TicketViewRs> listCars = new ArrayList<TicketViewRs>();
    int position;
    TicketViewDetailsback ticketViewDetailsback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ticketViewDetailsback=(TicketViewDetailsback)activity;
    }

    public TicketView_Details() {
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ticket_view_detail, container, false);

        imageViewList = (ImageView) v.findViewById(R.id.imageViewLink);
        ticketNoDetail = (EditText) v.findViewById(R.id.ticketNoDetail);
        issueDetail = (EditText) v.findViewById(R.id.issueDetail);
        CategoryDetail = (EditText) v.findViewById(R.id.CategoryDetail);
        PriorityDetail = (EditText) v.findViewById(R.id.PriorityDetail);
        mCardView = (CardView) v.findViewById(R.id.card_view_image);
        targetDateDetail = (EditText) v.findViewById(R.id.targetDateDetail);
        preferredCallTimeDetail = (EditText) v.findViewById(R.id.preferredCallTimeDetail);
        preferredTimeToAttendDetail = (EditText) v.findViewById(R.id.preferredTimeToAttendDetail);
        linearLayoutTicketStatus = (LinearLayout) v.findViewById(R.id.linearLayoutTicketStatus);
        description = (EditText) v.findViewById(R.id.description);
        exitTicket = (Button) v.findViewById(R.id.exitTicket);
        closeTicket = (Button) v.findViewById(R.id.closeTicket);
        reOpenTicket = (Button) v.findViewById(R.id.reOpenTicket);
        ticketDetails = (TextView) v.findViewById(R.id.ticketDetails);
        exitTicket.setOnClickListener(this);
        closeTicket.setOnClickListener(this);
        reOpenTicket.setOnClickListener(this);
        ticketDetails.setOnClickListener(this);
        linearLayoutTicketStatus.setVisibility(View.GONE);
        Home mainActivity = (Home) getActivity();
        mainActivity.getFragementTiltle().setTitle("Ticket Details");
        ticketNoDetail.setEnabled(false);
        issueDetail.setEnabled(false);
        CategoryDetail.setEnabled(false);
        PriorityDetail.setEnabled(false);
        targetDateDetail.setEnabled(false);
        preferredCallTimeDetail.setEnabled(false);
        preferredTimeToAttendDetail.setEnabled(false);
        description.setEnabled(false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            if((List<TicketViewRs>) bundle.getSerializable("PRESC_LIST") != null){
            listCars = (List<TicketViewRs>) bundle.getSerializable("PRESC_LIST");

            position = bundle.getInt("Position");

            ticketNoDetail.setText(listCars.get(position).ticketNo);
            issueDetail.setText(listCars.get(position).issue);
            CategoryDetail.setText(listCars.get(position).category);
            PriorityDetail.setText(listCars.get(position).priority);
            targetDateDetail.setText(Utility.convertDay(listCars.get(position).targetDate));
            preferredCallTimeDetail.setText(Utility.convertDay(listCars.get(position).pct));
            preferredTimeToAttendDetail.setText(listCars.get(position).pat);
            description.setText(listCars.get(position).userDescription);


                imageViewList.setOnClickListener(this);

                imageGalleryUrls.add(listCars.get(position).attachmentpath+""+listCars.get(position).attachmentname);

                if(listCars.get(position).attachmentpath.equals("")){

                    mCardView.setVisibility(View.GONE);

                }else {
                    mCardView.setVisibility(View.VISIBLE);
                    Picasso.with(getActivity())
                            .load("http://fmsindia.net/"+listCars.get(position).attachmentpath + "" + listCars.get(position).attachmentname).placeholder(R.drawable.mandefault).into(imageViewList);


                }

            if (listCars.get(position).paymentStatus.equals("Paid") && listCars.get(position).ticketstatus.equals("Resolved")) {

                linearLayoutTicketStatus.setVisibility(View.VISIBLE);

            } else {

                linearLayoutTicketStatus.setVisibility(View.GONE);

            }

        }
    }
        getArguments().remove("PRESC_LIST");
        vendorTicketDetailsReq = new VendorTicketDetailsReq();
        vendorTicketDetailsReq.TicketNo = ticketNoDetail.getText().toString();
        RequestManager.VendorTicket(getActivity(), vendorTicketDetailsReq, this);
        Utility.showProgressDialog(getActivity());

        return v;
    }
    public void delete()
    {
        RequestManager.TicketDelete(getActivity(), ticketDeleteReq, this);
    }

    @Override
    public void onStart() {

        Home mainActivity = (Home) getActivity();
        mainActivity.getFragementTiltle().setTitle("Ticket Details");
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            if (imageGalleryUrls.size() != 0) {
                if (imageGalleryUrls.get(0).equals("")) {
                    mCardView.setVisibility(View.GONE);
                } else {
                    mCardView.setVisibility(View.VISIBLE);
                    Picasso.with(getActivity())
                            .load("http://fmsindia.net/" + imageGalleryUrls.get(0)).placeholder(R.drawable.mandefault).into(imageViewList);
                }
            }
        }
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exitTicket:
                Ticket_View fragment = new Ticket_View();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
                break;
            case R.id.closeTicket:

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("If you are satisfied with the solution provided by us,please close the ticket. However the ticket shall be" +
                " auto hard closed after 24 hours.");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        ticketDeleteReq= new TicketDeleteReq();
                        ticketDeleteReq.ticketNo = ticketNoDetail.getText().toString();
                        delete();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }

                });

                builder.show();

                break;
            case R.id.reOpenTicket:

                final AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setMessage("If the problem reoccurs again, you can reopen the ticket");
                builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        TicketReOpen ticketReOpen = new TicketReOpen();
                        fragmentCallBackListener.navigateFragment(ticketReOpen, "Prescription");
                        Bundle bundle = new Bundle();
                        bundle.putInt("Position",position);
                        bundle.putSerializable("PRESC_LIST", (Serializable) listCars);
                        ticketReOpen.setArguments(bundle);
                        delete();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }

                });

                builder1.show();

                break;
            case R.id.ticketDetails:
                showPopup(ticketFullDet);
                break;
            case R.id.imageViewLink:
                img();
                break;
        }
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
    public void onResponseReceived(Object responseObj, int requestType) {
        if (responseObj != null) {

            String responseCode = ((BaseRS) responseObj).responseCode;
            if (responseCode.equals("S")) {
                List<TicketFullDetails> ticketFullDetails = ((VendorTicketDetailsInfoRs) responseObj).ticketDetails;

                ticketFullDet = ticketFullDetails;

                Utility.hideProgressDialog(getActivity());
            }else if (responseCode.equals("SS")) {


                Ticket_View_Delete fragment = new Ticket_View_Delete();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();

                Utility.hideProgressDialog(getActivity());

            }else if(responseCode.equals("N")){
                Utility.hideProgressDialog(getActivity());
            }
        }

            }

    public interface TicketViewDetailsback{

        void SetTicketViewDetailTab();
    }

public void  img(){
    imageView imageViews = new imageView();
    android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
    fragmentTransaction.replace(R.id.frame, imageViews).addToBackStack("Ticket Details");
    fragmentTransaction.commit();
    Bundle bundle = new Bundle();
    bundle.putString("Position", imageGalleryUrls.get(0));

    imageViews.setArguments(bundle);
}


}
