package com.fms.fmsindia;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;

import com.fms.fmsindia.com.app.madic.Constants;
import com.fms.fmsindia.webservices.request.OtpNumberReq;
import com.google.gson.Gson;


/**
 * Created by androiduser2 on 31/8/16.
 */
public class LocalStorage {



    public static void storePatientData(Context context, String patientString) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                Constants.PREFE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("PATIENT_DATA", patientString);
        editor.commit();
    }
    public static String getPatientdata(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                Constants.PREFE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString("PATIENT_DATA", "");
    }
    public static void storePatientObj(Context context, OtpNumberReq otpInfoRs) {
        Gson gson = new Gson();
        String json = gson.toJson(otpInfoRs);
        LocalStorage.storePatientData(context, json);
    }

    public static OtpNumberReq getPatientObj(Context context) {

        OtpNumberReq patientRegInfo = null;
        String patientInfo = "";
        patientInfo = LocalStorage.getPatientdata(context);
        Gson gson = new Gson();
        patientRegInfo = gson.fromJson(patientInfo, OtpNumberReq.class);

        return patientRegInfo;
    }
}
