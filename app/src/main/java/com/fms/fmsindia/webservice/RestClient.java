package com.fms.fmsindia.webservice;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fms.fmsindia.com.app.madic.Constants;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by krishna on 18/8/15.
 */
public class RestClient {
    public static final String URL_PWD = Constants.USER_NAME + ":"
            + Constants.PASSWORD;
    public static String timout;
    private static RequestQueue queue;
    final String basicAuth = "Basic "
            + Base64.encodeToString(URL_PWD.getBytes(), Base64.NO_WRAP);
    //AppState appState;
    private ArrayList<NameValuePair> headers;
    private ArrayList<NameValuePair> params;
    private String url;
    private int requestType;

    public RestClient(String url, int requestType) {
        this.url = url;
        this.requestType = requestType;
        headers = new ArrayList<NameValuePair>();
        params = new ArrayList<NameValuePair>();
    }

    public void addParam(String name, String value) {
        params.add(new BasicNameValuePair(name, value));
    }

    public void addHeader(String name, String value) {
        headers.add(new BasicNameValuePair(name, value));
    }

    public void execute(RequestMethod method, Context activity)
            throws Exception {
        Log.d("", "Request params " + url);

        // For debugging
        for (NameValuePair p : params) {
            Log.d("", "Setting param :" + p.getName() + " = " + p.getValue());
        }
//        if (Utility.internetIsAvailable(activity)) {
//            postData(url, activity, (ResponseListener) activity);
//        } else {
//            Utility.hideProgressDialog(activity);
//            Utility.showConnectionNotAvailable(activity);

        //      }


    }

    public void execute(RequestMethod method, Context activity,
                        Fragment fragment) throws Exception {
        Log.d("", "Request params " + url);

        // For debugging
        for (NameValuePair p : params) {
            Log.i("==========", "Setting param :" + p.getName() + " = " + p.getValue());

        }

        postData(url, activity, (ResponseListener) fragment);

     /*   if (Utility.internetIsAvailable(activity)) {
            postData(url, activity, (ResponseListener) fragment);
        } else {
            Utility.hideProgressDialog(activity);
            Utility.showConnectionNotAvailable(activity);

        }*/


    }

    public void executeActivity(RequestMethod method, Context activity,
                                Activity fragment) throws Exception {
        Log.d("", "Request params " + url);

        // For debugging
        for (NameValuePair p : params) {
            Log.i("==========", "Setting param :" + p.getName() + " = " + p.getValue());

        }

        postData(url, activity, (ResponseListener) fragment);

     /*   if (Utility.internetIsAvailable(activity)) {
            postData(url, activity, (ResponseListener) fragment);
        } else {
            Utility.hideProgressDialog(activity);
            Utility.showConnectionNotAvailable(activity);

        }*/


    }

    private void postData(String url, final Context activity,
                          final ResponseListener resplist) {
        try {
            //appState = (AppState) activity.getApplicationContext();


            queue = Volley.newRequestQueue(activity);
            int timeout = 60000; // 60 seconds - time out


            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // response
                            Log.d("Response", response.toString());
                            // try {
                            Object object = invokeParser(response, requestType);

//                            if (object == null) {
////                                Utility.hideProgressDialog(activity);
////                               Utility.showAlertDialog(activity, "Connection Problem. Unable to fetch the data");
//
//                            }
                            //Log.v("URL", "--" + object.toString());
                            resplist.onResponseReceived(object, requestType);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // error
                    Log.d("Error.Response", "" + error.getMessage());
                    //Utility.hideProgressDialog(activity);
                    if (error.getMessage() == null) {
                        //   Utility.showAlertDialog(activity, "Connection Problem. Please try after some time");
                    }

                    resplist.onResponseReceived(null, requestType);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type",
                            "application/x-www-form-urlencoded");
                    headers.put("Authorization", basicAuth);
                    headers.put("Accept-Encoding", "");
                    return headers;
                }

                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> pvalues = new HashMap<String, String>();
                    for (NameValuePair p : params) {
                        pvalues.put("" + p.getName(), "" + p.getValue());
                    }
                    return pvalues;
                }
            };


            // Request Time out
            postRequest.setRetryPolicy(new DefaultRetryPolicy(timeout,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            postRequest.setRetryPolicy(new DefaultRetryPolicy(timeout,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            System.setProperty("http.keepAlive", "false");

            // Default request queue
            queue.add(postRequest);
        } catch (Exception e) {
        }
    }


    private Object invokeParser(String response, int req_code) {

        switch (req_code) {

            case Constants.MEMBER_PATIENT_PRESCRIPTIONS_RQ:
                return Parser.getPrescriptionsHistory(response);
            case Constants.MEMBER_FMS_GET_CATEGORY_RQ:
                return Parser.getCategoryRs(response);
            case Constants.MEMBER_FMS_GET_ISSUE_RQ:
                return Parser.getIssueRsInfo(response);
            case Constants.MEMBER_FMS_GET_PRIORITY_RQ:
                return Parser.getPriorityInfoRs(response);
            case Constants.MEMBER_FMS_INSERT_TICKET_RQ:
                return Parser.insertTicketInfoRs(response);
            case Constants.MEMBER_FMS_REGISTER_RQ:
                return Parser.registerInfoRs(response);
            case Constants.MEMBER_FMS_OTP_RQ:
                return Parser.otpInfoRs(response);
            case Constants.MEMBER_VIEW_TICKET_RQ:
                return Parser.ticketViewInfoRs(response);
            case Constants.MEMBER_VIEW_CLOSED_TICKET_RQ:
                return Parser.ticketViewDeleteInfoRs(response);
            case Constants.MEMBER_USER_INFO_RQ:
                return Parser.userInfoRs(response);
            case Constants.MEMBER_TICKET_REOPEN_RQ:
                return Parser.ticketReopenInfoRs(response);
            case Constants.MEMBER_TICKET_CLOSE_RQ:
                return Parser.ticketDeleteInfoRs(response);
            case Constants.MEMBER_TICKET_ACCEPT_RQ:
                return Parser.ticketAcceptInfoRs(response);
            case Constants.MEMBER_VENDOR_TICKET_RQ:
                return Parser.vendorTicketDetailsInfoRs(response);
            case Constants.MEMBER_VENDOR_TICKET_STATUS_RQ:
                return Parser.ticketDeleteInfoRs(response);
            case Constants.MEMBER_VENDOR_USER_RQ:
                return Parser.vendorUserInfoRs(response);
            case Constants.MEMBER_PAYMENT_RQ:
                return Parser.getPayment(response);
            case Constants.MEMBER_PROFILE_UPDATE_RQ:
                return Parser.profileUpdateRsInfo(response);
        }

        Log.e("", "Unknown commands");
        return null;

    }

    public enum RequestMethod {
        POST, GET

    }

}
