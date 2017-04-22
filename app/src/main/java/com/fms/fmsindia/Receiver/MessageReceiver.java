package com.fms.fmsindia.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.fms.fmsindia.registion.VerifyOtp;


/**
 * Created by androiduser2 on 12/8/16.
 */
public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();


        try {
            if (bundle != null) {
                Log.i("OTP",  "--"+bundle);
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                if (pdusObj != null) {
                    for (int i = 0; i < pdusObj.length; i++) {
                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                        String senderNum = phoneNumber;
                        String message = currentMessage.getDisplayMessageBody();

                        Log.i("OTP", phoneNumber + "" + senderNum + "" + message + "--");
                        try {
                            if (senderNum.contains("FMSIND")) {
                                Log.i("OTP", "" + message + "--");
                                String num = message.substring(57,63);
                                VerifyOtp.otpVerify.setText(num);
                                if (message.contains("OTP")) {
                                    String spMessage[] = message.split(":");
                                    //Log.i("test", spMessage[1]);
                                  /*  Intent navigateIntent = new Intent();
                                    navigateIntent.setClassName("com.patientmedic", "com.patientmedic.registration.AddPatientDetails");
                                    navigateIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(navigateIntent);*/

                                    //VerifyNumber.otpText.setText(spMessage[1].trim());

                                    return;

                                }
                                if (message.contains("VMN")) {
                                    //Utility.hideProgressDialog(context);

                                }


                            }
                        } catch (Exception e) {
                        }

                    }
                }

            }

        } catch (Exception e) {

        }
    }
}
