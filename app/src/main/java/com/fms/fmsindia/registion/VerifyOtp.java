package com.fms.fmsindia.registion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fms.fmsindia.Home;
import com.fms.fmsindia.LocalStorage;
import com.fms.fmsindia.R;
import com.fms.fmsindia.Utility.Utility;
import com.fms.fmsindia.webservice.RequestManager;
import com.fms.fmsindia.webservice.ResponseListener;
import com.fms.fmsindia.webservices.request.OtpNumberReq;
import com.fms.fmsindia.webservices.response.BaseRS;
import com.fms.fmsindia.webservices.response.OtpInfoRs;


public class VerifyOtp extends AppCompatActivity implements TextWatcher,View.OnClickListener, ResponseListener {

    public static EditText otpVerify;
    TextView reg;
    Button submit;
    String userName;
    OtpNumberReq otpNumberReq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        otpVerify = (EditText)findViewById(R.id.otpVerify);
        reg = (TextView) findViewById(R.id.reg);
        submit = (Button)findViewById(R.id.signIn);
        submit.setOnClickListener(this);
        reg.setTextColor(Color.parseColor("#ffffff"));
        reg.setText("Sit back & Relax! While we verify your mobile number");
        otpVerify.setTextColor(Color.parseColor("#ffffff"));
        Bundle extras = getIntent().getExtras();
        otpNumberReq = new OtpNumberReq();

        if (extras != null) {
            userName = extras.getString("phoneNo");
            // and get whatever type user account id is
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        this.startActivity(new Intent(this, Home.class));
    }

    @Override
    public void afterTextChanged(Editable s) {
        this.startActivity(new Intent(this, Home.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signIn:
                if(!otpVerify.getText().toString().isEmpty()){

                    otpNumberReq.phoneNo = userName;
                    otpNumberReq.Otp = otpVerify.getText().toString();
                    Utility.showProgressDialog(this);
                    RequestManager.getOtpConfirm(this, otpNumberReq, this);

                    //this.startActivity(new Intent(this, Home.class));
                }
                break;
        }
    }

    @Override
    public void onResponseReceived(Object responseObj, int requestType) {
        if (responseObj != null) {

            String responseCode = ((BaseRS) responseObj).responseCode;

            if (responseCode.equals("S")) {

                otpNumberReq.userId = ((OtpInfoRs) responseObj).userId;
                otpNumberReq.phoneNo = ((OtpInfoRs) responseObj).phone;
                otpNumberReq.firstName = ((OtpInfoRs) responseObj).firstName;
                otpNumberReq.lastName = ((OtpInfoRs) responseObj).lastName;
                otpNumberReq.email = ((OtpInfoRs) responseObj).emailId;
                otpNumberReq.currentAddress = ((OtpInfoRs) responseObj).currentAddress;
                otpNumberReq.landmark = ((OtpInfoRs) responseObj).landmark;
                otpNumberReq.houseId = ((OtpInfoRs) responseObj).houseid;
                otpNumberReq.userimage = ((OtpInfoRs) responseObj).userimage;
                otpNumberReq.attachmentname = ((OtpInfoRs) responseObj).attachmentname;
                otpNumberReq.role = ((OtpInfoRs) responseObj).role;
                LocalStorage.storePatientObj(this, otpNumberReq);
                this.startActivity(new Intent(this, Home.class));
                Utility.hideProgressDialog(this);
                Toast.makeText(this,"Success",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this,"Please Enter Correct OTP",Toast.LENGTH_LONG).show();
                Utility.hideProgressDialog(this);
            }
        }else {
            Toast.makeText(this,"Please Enter Correct OTP",Toast.LENGTH_LONG).show();
            Utility.hideProgressDialog(this);
        }
    }
}
