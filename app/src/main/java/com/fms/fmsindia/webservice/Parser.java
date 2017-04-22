package com.fms.fmsindia.webservice;

import android.util.Log;

import com.fms.fmsindia.webservices.response.GetCategoryRs;
import com.fms.fmsindia.webservices.response.GetIssueRsInfo;
import com.fms.fmsindia.webservices.response.GetPriorityInfoRs;
import com.fms.fmsindia.webservices.response.InsertTicketInfoRs;
import com.fms.fmsindia.webservices.response.LoginRs;
import com.fms.fmsindia.webservices.response.OtpInfoRs;
import com.fms.fmsindia.webservices.response.PaymentInfoRs;
import com.fms.fmsindia.webservices.response.ProfileUpdateRsInfo;
import com.fms.fmsindia.webservices.response.RegisterInfoRs;
import com.fms.fmsindia.webservices.response.TicketAcceptInfoRs;
import com.fms.fmsindia.webservices.response.TicketDeleteInfoRs;
import com.fms.fmsindia.webservices.response.TicketReopenInfoRs;
import com.fms.fmsindia.webservices.response.TicketViewDeleteInfoRs;
import com.fms.fmsindia.webservices.response.TicketViewInfoRs;
import com.fms.fmsindia.webservices.response.UserInfoRs;
import com.fms.fmsindia.webservices.response.VendorTicketDetailsInfoRs;
import com.fms.fmsindia.webservices.response.VendorUserInfoRs;
import com.google.gson.Gson;


/**
 * Created by krishna on 22/8/15.
 */
public class Parser {

//    public static synchronized PatientMedic parseViewResponse(String response) {
//
//        try {
//
//            String res = response.toString();
//            Log.d("", res);
//
//            Gson gson = new Gson();
//            // converting or parsing the content
//            ProfileRS out = gson.fromJson(res, ProfileRS.class);
//
//            return out.patientMedic;
//
//        } catch (IllegalStateException e) {
//            Log.e("", "MyResponse WS Parsing failed in Parser");
//            e.printStackTrace();
//            return null;
//        } catch (Exception e) {
//            Log.e("", "MyResponse WS Parsing failed in Parser");
//            e.printStackTrace();
//            return null;
//        }
//    }


    public static synchronized LoginRs getPrescriptionsHistory(String response) {

        try {

            String res = response.toString();
            Log.d("", res);

            Gson gson = new Gson();
            // converting or parsing the content
            LoginRs out = gson.fromJson(res, LoginRs.class);

            return out;

        } catch (IllegalStateException e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        }
    }
    public static synchronized GetCategoryRs getCategoryRs(String response) {

        try {

            String res = response.toString();
            Log.d("", res);

            Gson gson = new Gson();
            // converting or parsing the content
            GetCategoryRs out = gson.fromJson(res, GetCategoryRs.class);

            return out;

        } catch (IllegalStateException e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        }
    }
    public static synchronized PaymentInfoRs getPayment(String response) {

        try {

            String res = response.toString();
            Log.d("", res);

            Gson gson = new Gson();
            // converting or parsing the content
            PaymentInfoRs out = gson.fromJson(res, PaymentInfoRs.class);

            return out;

        } catch (IllegalStateException e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized GetIssueRsInfo getIssueRsInfo(String response) {

        try {

            String res = response.toString();
            Log.d("", res);

            Gson gson = new Gson();
            // converting or parsing the content
            GetIssueRsInfo out = gson.fromJson(res, GetIssueRsInfo.class);

            return out;

        } catch (IllegalStateException e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        }
    }
    public static synchronized GetPriorityInfoRs getPriorityInfoRs(String response) {

        try {

            String res = response.toString();
            Log.d("", res);

            Gson gson = new Gson();
            // converting or parsing the content
            GetPriorityInfoRs out = gson.fromJson(res, GetPriorityInfoRs.class);

            return out;

        } catch (IllegalStateException e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        }
    }
    public static synchronized InsertTicketInfoRs insertTicketInfoRs(String response) {

        try {

            String res = response.toString();
            Log.d("", res);

            Gson gson = new Gson();
            // converting or parsing the content
            InsertTicketInfoRs out = gson.fromJson(res, InsertTicketInfoRs.class);

            return out;

        } catch (IllegalStateException e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        }
    }
    public static synchronized TicketViewInfoRs ticketViewInfoRs(String response) {

        try {

            String res = response.toString();
            Log.d("", res);

            Gson gson = new Gson();
            // converting or parsing the content
            TicketViewInfoRs out = gson.fromJson(res, TicketViewInfoRs.class);

            return out;

        } catch (IllegalStateException e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        }
    }
    public static synchronized TicketViewDeleteInfoRs ticketViewDeleteInfoRs(String response) {

        try {

            String res = response.toString();
            Log.d("", res);

            Gson gson = new Gson();
            // converting or parsing the content
            TicketViewDeleteInfoRs out = gson.fromJson(res, TicketViewDeleteInfoRs.class);

            return out;

        } catch (IllegalStateException e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        }
    }
    public static synchronized TicketReopenInfoRs ticketReopenInfoRs(String response) {

        try {

            String res = response.toString();
            Log.d("", res);

            Gson gson = new Gson();
            // converting or parsing the content
            TicketReopenInfoRs out = gson.fromJson(res, TicketReopenInfoRs.class);

            return out;

        } catch (IllegalStateException e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        }
    }
    public static synchronized TicketDeleteInfoRs ticketDeleteInfoRs(String response) {

        try {

            String res = response.toString();
            Log.d("", res);

            Gson gson = new Gson();
            // converting or parsing the content
            TicketDeleteInfoRs out = gson.fromJson(res, TicketDeleteInfoRs.class);

            return out;

        } catch (IllegalStateException e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        }
    }
    public static synchronized TicketAcceptInfoRs ticketAcceptInfoRs(String response) {

        try {

            String res = response.toString();
            Log.d("", res);

            Gson gson = new Gson();
            // converting or parsing the content
            TicketAcceptInfoRs out = gson.fromJson(res, TicketAcceptInfoRs.class);

            return out;

        } catch (IllegalStateException e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        }
    }
    public static synchronized VendorTicketDetailsInfoRs vendorTicketDetailsInfoRs(String response) {

        try {

            String res = response.toString();
            Log.d("", res);

            Gson gson = new Gson();
            // converting or parsing the content
            VendorTicketDetailsInfoRs out = gson.fromJson(res, VendorTicketDetailsInfoRs.class);

            return out;

        } catch (IllegalStateException e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized RegisterInfoRs registerInfoRs(String response) {

        try {

            String res = response.toString();
            Log.d("", res);

            Gson gson = new Gson();
            // converting or parsing the content

            RegisterInfoRs out = gson.fromJson(res, RegisterInfoRs.class);

            return out;

        } catch (IllegalStateException e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        }
    }
    public static synchronized ProfileUpdateRsInfo profileUpdateRsInfo(String response) {

        try {

            String res = response.toString();
            Log.d("", res);

            Gson gson = new Gson();
            // converting or parsing the content

            ProfileUpdateRsInfo out = gson.fromJson(res, ProfileUpdateRsInfo.class);

            return out;

        } catch (IllegalStateException e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        }
    }
    public static synchronized OtpInfoRs otpInfoRs(String response) {

        try {

            String res = response.toString();
            Log.d("", res);

            Gson gson = new Gson();
            // converting or parsing the content
            OtpInfoRs out = gson.fromJson(res, OtpInfoRs.class);

            return out;

        } catch (IllegalStateException e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized VendorUserInfoRs vendorUserInfoRs(String response) {

        try {

            String res = response.toString();
            Log.d("", res);

            Gson gson = new Gson();
            // converting or parsing the content
            VendorUserInfoRs out = gson.fromJson(res, VendorUserInfoRs
                    .class);

            return out;

        } catch (IllegalStateException e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized UserInfoRs userInfoRs(String response) {

        try {

            String res = response.toString();
            Log.d("", res);

            Gson gson = new Gson();
            // converting or parsing the content
            UserInfoRs out = gson.fromJson(res, UserInfoRs.class);

            return out;

        } catch (IllegalStateException e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            Log.e("", "MyResponse WS Parsing failed in Parser");
            e.printStackTrace();
            return null;
        }
    }
}
