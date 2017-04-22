package com.fms.fmsindia.Utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;


import com.fms.fmsindia.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by androiduser2 on 7/9/16.
 */
public class Utility {
    public static ProgressDialog progress;

    public static void showProgressDialog(Context context) {
        progress = ProgressDialog.show(context, "FMSINDIA",
                context.getResources().getString(R.string.progress_bar), true);
        progress.setCancelable(false);
    }

    public static void hideProgressDialog(Context context) {
        if (progress != null) {
            progress.dismiss();

        }
    }

    public static String convertDay(String inputDate) {

        DateFormat theDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = theDateFormat.parse(inputDate);
        } catch (ParseException parseException) {
            // Date is invalid. Do what you want.
        } catch (Exception exception) {
            // Generic catch. Do what you want.
        }

        theDateFormat = new SimpleDateFormat("dd-MM-yyy");

        return theDateFormat.format(date);
    }

    public static String convertDayTo(String inputDate) {

        DateFormat theDateFormat = new SimpleDateFormat("dd-MM-yyy");
        Date date = null;

        try {
            date = theDateFormat.parse(inputDate);
        } catch (ParseException parseException) {
            // Date is invalid. Do what you want.
        } catch (Exception exception) {
            // Generic catch. Do what you want.
        }

        theDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return theDateFormat.format(date);
    }
    public static String convertTime(String inputDate) {

        DateFormat theDateFormat = new SimpleDateFormat("mm");
        Date date = null;

        try {
            date = theDateFormat.parse(inputDate);
        } catch (ParseException parseException) {
            // Date is invalid. Do what you want.
        } catch (Exception exception) {
            // Generic catch. Do what you want.
        }

        theDateFormat = new SimpleDateFormat("mm");

        return theDateFormat.format(date);
    }
    public static String convertDayTime(String inputDate) {

        DateFormat theDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;

        try {
            date = theDateFormat.parse(inputDate);
        } catch (ParseException parseException) {
            // Date is invalid. Do what you want.
        } catch (Exception exception) {
            // Generic catch. Do what you want.
        }

        theDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

        return theDateFormat.format(date);
    }

    public static void showAlertDialog(Context context, String text) {
        if (context != null) {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.AppTheme);
            final AlertDialog OptionDialog = alertDialog.create();

            alertDialog.setTitle("FMSINDIA");
            alertDialog.setMessage(text);
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    OptionDialog.dismiss();
                }


            });
            alertDialog.show();
        }

    }
    public static boolean internetIsAvailable(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (netInfo != null
                    && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            } else {
                netInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (netInfo != null
                        && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }
    public static String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    }
    public static boolean getLoginedStatus(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                "Login", Context.MODE_PRIVATE);
        return sharedPref.getBoolean("welcomeScreenShown", false);
    }

    public static void setLoginedStatus(Context context, boolean status) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                "Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("welcomeScreenShown", status);
        editor.commit();
    }
}
