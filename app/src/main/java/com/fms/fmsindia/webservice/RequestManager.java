package com.fms.fmsindia.webservice;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.fms.fmsindia.LocalStorage;
import com.fms.fmsindia.com.app.madic.Constants;
import com.fms.fmsindia.webservices.request.GetCategory;
import com.fms.fmsindia.webservices.request.GetIssueReq;
import com.fms.fmsindia.webservices.request.InsertTicketReq;
import com.fms.fmsindia.webservices.request.LoginReq;
import com.fms.fmsindia.webservices.request.OtpNumberReq;
import com.fms.fmsindia.webservices.request.PatientReg;
import com.fms.fmsindia.webservices.request.PaymentReq;
import com.fms.fmsindia.webservices.request.PrescriptionsReq;
import com.fms.fmsindia.webservices.request.RegisterReq;
import com.fms.fmsindia.webservices.request.TicketAcceptReq;
import com.fms.fmsindia.webservices.request.TicketDeleteReq;
import com.fms.fmsindia.webservices.request.TicketReopenReq;
import com.fms.fmsindia.webservices.request.UserInfoReq;
import com.fms.fmsindia.webservices.request.VendorTicketCheckReq;
import com.fms.fmsindia.webservices.request.VendorTicketDetailsReq;

import org.json.JSONObject;



/**
 * Created by krishna on 18/8/15.
 */
public class RequestManager {


    public static boolean setPatientInfo(Context activity, PatientReg patientReg){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_PATIENT_REG,Constants.MEMBER_PATIENT_REG_RQ);

            //fillCommonParams(client, activity);

            client.addParam("firstname",
                    String.valueOf(patientReg.firstName));
            client.addParam("lastname",
                    String.valueOf(patientReg.lastName));
            client.addParam("dob",
                    String.valueOf(patientReg.dateOfBirth));
            client.addParam("gender", String.valueOf(patientReg.gender));
            client.addParam("contact_no", String.valueOf(patientReg.contactNumber));
            client.addParam("email_id", String.valueOf(patientReg.email));
            client.addParam("locality", String.valueOf(patientReg.locality));
            client.addParam("address", String.valueOf(patientReg.address));
            client.addParam("blood_group", String.valueOf(patientReg.bloodGroup));


            client.execute(RestClient.RequestMethod.POST,activity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static boolean getPrescriptionsHistory(Context activity, LoginReq loginReq, Activity fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_PATIENT_PRESCRIPTIONS_HISTORY,Constants.MEMBER_PATIENT_PRESCRIPTIONS_RQ);

            //fillCommonParams(client, activity);

            client.addParam("username", loginReq.userName);
            client.addParam("password", loginReq.password);

            client.executeActivity(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static boolean getViewTicket(Context activity, PrescriptionsReq prescriptionsReq, Fragment fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_VIEW_TICKET,Constants.MEMBER_VIEW_TICKET_RQ);

            //fillCommonParams(client, activity);

            client.addParam("userid", LocalStorage.getPatientObj(activity).userId);
            client.addParam("role", LocalStorage.getPatientObj(activity).role);

            //client.addParam("password", loginReq.password);

            client.execute(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static boolean getViewTicketDelete(Context activity, PrescriptionsReq prescriptionsReq, Fragment fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_VIEW_CLOSED_TICKET,Constants.MEMBER_VIEW_CLOSED_TICKET_RQ);

            //fillCommonParams(client, activity);

            client.addParam("userid", LocalStorage.getPatientObj(activity).userId);

            client.addParam("role", LocalStorage.getPatientObj(activity).role);
            //client.addParam("password", loginReq.password);

            client.execute(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static boolean TicketReopen(Context activity, TicketReopenReq ticketReopenReq, Fragment fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_TICKET_REOPEN,Constants.MEMBER_TICKET_REOPEN_RQ);
            //fillCommonParams(client, activity);
            client.addParam("userId", LocalStorage.getPatientObj(activity).userId);
            client.addParam("role", "10");
            client.addParam("reopenReason", ticketReopenReq.reopenReason);
            client.addParam("pct", ticketReopenReq.ptc);
            client.addParam("pat", ticketReopenReq.pat);
            client.addParam("ticketId", ticketReopenReq.ticketId);
            client.addParam("firstname", LocalStorage.getPatientObj(activity).userId);
            client.addParam("lastname", LocalStorage.getPatientObj(activity).lastName);
            client.addParam("houseid", ticketReopenReq.houseid);
            client.addParam("phone_number", LocalStorage.getPatientObj(activity).phoneNo);
            client.addParam("email",  LocalStorage.getPatientObj(activity).email);
            client.addParam("address",  LocalStorage.getPatientObj(activity).currentAddress);
            client.addParam("land_mark", LocalStorage.getPatientObj(activity).landmark);
            client.addParam("tecketID", ticketReopenReq.ticketNoId);
            client.addParam("ticketstatusname", "Reopen");

            //client.addParam("password", loginReq.password);
            client.execute(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static boolean TicketDelete(Context activity, TicketDeleteReq ticketDeleteReq, Fragment fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_TICKET_CLOSE,Constants.MEMBER_TICKET_CLOSE_RQ);
            //fillCommonParams(client, activity);
            client.addParam("userId", LocalStorage.getPatientObj(activity).userId);
            client.addParam("role", "10");

            client.addParam("ticketid", ticketDeleteReq.ticketNo);
            //client.addParam("password", loginReq.password);
            client.execute(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static boolean TicketAccept(Context activity, TicketAcceptReq ticketAcceptReq, Fragment fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_TICKET_ACCEPT,Constants.MEMBER_TICKET_ACCEPT_RQ);
            //fillCommonParams(client, activity);

            client.addParam("ticketno", ticketAcceptReq.ticketNo);
            client.addParam("firstname", ticketAcceptReq.firstname);
            client.addParam("lastname", ticketAcceptReq.lastname);
            client.addParam("email", ticketAcceptReq.email);
            client.addParam("phone_number", ticketAcceptReq.phone_number);
            client.addParam("address", ticketAcceptReq.address);
            client.addParam("land_mark", ticketAcceptReq.land_mark);
            client.addParam("houseid", ticketAcceptReq.houseid);
            client.addParam("assignedtoname", ticketAcceptReq.assignedtoname);
            client.addParam("assignedbyname", ticketAcceptReq.assignedbyname);
            client.addParam("ticketstatusname", ticketAcceptReq.ticketstatusname);
            client.addParam("tecketID", ticketAcceptReq.tecketID);
            client.addParam("userid", LocalStorage.getPatientObj(activity).userId);
            //client.addParam("password", loginReq.password);
            client.execute(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static boolean VendorTicket(Context activity, VendorTicketDetailsReq vendorTicketDetailsReq, Fragment fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_VENDOR_TICKET,Constants.MEMBER_VENDOR_TICKET_RQ);
            //fillCommonParams(client, activity);

            client.addParam("ticketno", vendorTicketDetailsReq.TicketNo);


            //client.addParam("password", loginReq.password);
            client.execute(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean VendorTicketCheck(Context activity, VendorTicketCheckReq vendorTicketCheckReq, Fragment fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_VENDOR_TICKET_STATUS,Constants.MEMBER_VENDOR_TICKET_STATUS_RQ);
            //fillCommonParams(client, activity);

            client.addParam("ticketid", vendorTicketCheckReq.ticketId);
            client.addParam("assignedto", vendorTicketCheckReq.assignedTo);
            client.addParam("assignedtoname", vendorTicketCheckReq.assignedToName);
            client.addParam("assignedbyname", vendorTicketCheckReq.assignedByName);
            client.addParam("vendordescription", vendorTicketCheckReq.vendorDescription);
            client.addParam("ticketstatus", vendorTicketCheckReq.ticketStatus);
            client.addParam("ticketstatusname", vendorTicketCheckReq.ticketStatusName);
            client.addParam("vendoruserdescription", vendorTicketCheckReq.vendorUserDescription);
            client.addParam("amount", vendorTicketCheckReq.amount);
            client.addParam("resolvedby", vendorTicketCheckReq.resolveBy);
            client.addParam("resolvedbyname", vendorTicketCheckReq.resolveByName);
            client.addParam("modeofpayment", vendorTicketCheckReq.modeOfPayment);
            client.addParam("paymentstatus", vendorTicketCheckReq.paymentStatus);
            client.addParam("role", LocalStorage.getPatientObj(activity).role);
            client.addParam("firstname", LocalStorage.getPatientObj(activity).firstName);
            client.addParam("lastname", LocalStorage.getPatientObj(activity).lastName);
            client.addParam("email", LocalStorage.getPatientObj(activity).email);
            client.addParam("phone_number", LocalStorage.getPatientObj(activity).phoneNo);
            client.addParam("address", LocalStorage.getPatientObj(activity).currentAddress);
            client.addParam("land_mark", LocalStorage.getPatientObj(activity).landmark);
            client.addParam("houseid", vendorTicketCheckReq.houseId);
            client.addParam("tecketID", vendorTicketCheckReq.ticketNoId);


            //client.addParam("password", loginReq.password);
            client.execute(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean VendorTicketCheckPayment(Context activity, PaymentReq paymentReq, Fragment fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_PAYMENT,Constants.MEMBER_PAYMENT_RQ);
            //fillCommonParams(client, activity);

            client.addParam("ticketid", paymentReq.ticketid);
            client.addParam("paymode", paymentReq.paymode);
            client.addParam("ps", paymentReq.ps);
            client.addParam("ticketstatus", paymentReq.ticketstatus);
            client.addParam("role", LocalStorage.getPatientObj(activity).role);



            //client.addParam("password", loginReq.password);
            client.execute(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    public static boolean getVendorTicketUser(Context activity, Activity activitys){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_VENDOR_USER,Constants.MEMBER_VENDOR_USER_RQ);
            //fillCommonParams(client, activity);



            //client.addParam("password", loginReq.password);
            client.executeActivity(RestClient.RequestMethod.POST,activity, activitys);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static boolean getCategory(Context activity, GetCategory getCategory, Fragment fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_FMS_GET_CATEGORY,Constants.MEMBER_FMS_GET_CATEGORY_RQ);

            //fillCommonParams(client, activity);



            client.execute(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static boolean getIssue(Context activity, GetIssueReq getIssueReq, Fragment fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_FMS_GET_ISSUE,Constants.MEMBER_FMS_GET_ISSUE_RQ);

            //fillCommonParams(client, activity);

            client.addParam("categoryid", getIssueReq.issueId);

            client.execute(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static boolean getPriority(Context activity, GetCategory getCategory, Fragment fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_FMS_GET_PRIORITY,Constants.MEMBER_FMS_GET_PRIORITY_RQ);

            //fillCommonParams(client, activity);



            client.execute(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static boolean getRegister(Context activity, RegisterReq registerReq, Activity fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_FMS_REGISTER,Constants.MEMBER_FMS_REGISTER_RQ);

            //fillCommonParams(client, activity);
            client.addParam("phone", registerReq.mobileNo);
            client.addParam("password", registerReq.password);
            client.addParam("firstname", registerReq.firstname);
            client.addParam("lastname", registerReq.lastname);
            client.addParam("currentaddress", registerReq.address);
            client.addParam("email", registerReq.emailId);
            client.addParam("landmark", registerReq.landmark);


            client.executeActivity(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static boolean getProfileUpdate(Context activity,JSONObject mJsonObject, OtpNumberReq profileReq, Fragment fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_PROFILE_UPDATE,Constants.MEMBER_PROFILE_UPDATE_RQ);

            //fillCommonParams(client, activity);
            client.addParam("landmark", profileReq.landmark);
            client.addParam("email", profileReq.email);
            client.addParam("firstname", profileReq.firstName);
            client.addParam("lastname", profileReq.lastName);
            client.addParam("currentaddress", profileReq.currentAddress);
            client.addParam("userid", LocalStorage.getPatientObj(activity).userId);
            if (mJsonObject == null) {
                client.addParam("testprescriptionfiles", "N");
            } else {
                client.addParam("testprescriptionfiles", mJsonObject.toString());
            }


            client.execute(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static boolean getOtpConfirm(Context activity, OtpNumberReq otpNumberReq, Activity fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_FMS_OTP,Constants.MEMBER_FMS_OTP_RQ);

            //fillCommonParams(client, activity);
            client.addParam("phone", otpNumberReq.phoneNo);
            client.addParam("otp", otpNumberReq.Otp);


            client.executeActivity(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static boolean getUserInfo(Context activity, UserInfoReq userInfoReq, Activity fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_USER_INFO,Constants.MEMBER_USER_INFO_RQ);

            //fillCommonParams(client, activity);
            client.addParam("userId", userInfoReq.userId);



            client.executeActivity(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static boolean getUserInfoProfile(Context activity, UserInfoReq userInfoReq, Fragment fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_USER_INFO,Constants.MEMBER_USER_INFO_RQ);

            //fillCommonParams(client, activity);
            client.addParam("userId", userInfoReq.userId);



            client.execute(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public static boolean insertTicket(Context activity, JSONObject mJsonObject, InsertTicketReq insertTicketReq, Fragment fragment){

        try {
            // Generating Req
            RestClient client = new RestClient(Constants.URL
                    + Constants.WS_PREFIX+
                    Constants.WS_MEMBER_FMS_INSERT_TICKET,Constants.MEMBER_FMS_INSERT_TICKET_RQ);

            //fillCommonParams(client, activity);
            client.addParam("userid", LocalStorage.getPatientObj(activity).userId);
            client.addParam("role", LocalStorage.getPatientObj(activity).role);
            client.addParam("houseid", LocalStorage.getPatientObj(activity).houseId);
            client.addParam("firstname", insertTicketReq.name);
            client.addParam("lastname", insertTicketReq.lastName);
            client.addParam("phone_number",insertTicketReq.phoneNo);
            client.addParam("email", insertTicketReq.email);
            client.addParam("address", insertTicketReq.address);
            client.addParam("land_mark",insertTicketReq.landMark);
            client.addParam("category", insertTicketReq.category);
            client.addParam("issue", insertTicketReq.issue);
            client.addParam("otherissue", insertTicketReq.otherIssue);
            client.addParam("cboiss", "");
            client.addParam("priority",insertTicketReq.priority);
            client.addParam("call_time", insertTicketReq.callTime);
            client.addParam("time_to_attend",insertTicketReq.timeToAttend);
            client.addParam("description", insertTicketReq.description);
            if (mJsonObject == null) {
                client.addParam("testprescriptionfiles", "N");
            } else {
                client.addParam("testprescriptionfiles", mJsonObject.toString());
            }
            client.execute(RestClient.RequestMethod.POST,activity, fragment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
