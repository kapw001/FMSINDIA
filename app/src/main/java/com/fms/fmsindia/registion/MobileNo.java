
package com.fms.fmsindia.registion;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fms.fmsindia.R;
import com.fms.fmsindia.Utility.Utility;
import com.fms.fmsindia.webservice.RequestManager;
import com.fms.fmsindia.webservice.ResponseListener;
import com.fms.fmsindia.webservices.request.RegisterReq;
import com.fms.fmsindia.webservices.response.BaseRS;
import com.fms.fmsindia.webservices.response.RegisterInfoRs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MobileNo extends AppCompatActivity implements View.OnClickListener, ResponseListener {

    EditText mobileNo, password,firstname,lastname,emailId,address,landmark;

    TextView reg;

    Button register;

    RegisterReq registerReq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_no);

        mobileNo = (EditText) findViewById(R.id.mobileNo);
        password = (EditText) findViewById(R.id.passwordNew);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        emailId = (EditText) findViewById(R.id.emailId);
        address = (EditText) findViewById(R.id.address);
        landmark = (EditText) findViewById(R.id.landmark);
        register = (Button) findViewById(R.id.register);
        reg = (TextView) findViewById(R.id.reg);

        reg.setTextColor(Color.parseColor("#ffffff"));




        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                registerReq = new RegisterReq();
                Log.i("value", "" + password.getText().toString());
                registerReq.mobileNo = mobileNo.getText().toString();
                registerReq.password = password.getText().toString();
                registerReq.firstname = firstname.getText().toString();
                registerReq.lastname = lastname.getText().toString();
                registerReq.mobileNo = mobileNo.getText().toString();
                registerReq.address = address.getText().toString();
                registerReq.landmark = landmark.getText().toString();
                registerReq.emailId = emailId.getText().toString();
                if(!isValidMobileNo(mobileNo.getText().toString()))
                {
                    mobileNo.setError("Please Enter the Valid Mobile No");

                }else if(!isValidPassword(password.getText().toString())){
                    password.setError("Please Enter Password");
                }else if(!isValidFirstName(firstname.getText().toString())){
                    firstname.setError("Please Enter the First Name");
                }else if(!isValidLastName(lastname.getText().toString())){
                    lastname.setError("Please Enter the Last Name");
                }else if(address.getText().toString().equals("")){
                    address.setError("Please Enter the address");
                }else if(!isValidEmail(emailId.getText().toString())){
                    emailId.setError("Please Enter the Valid E-mail id");
                }else {

                    Utility.showProgressDialog(this);
                    RequestManager.getRegister(this, registerReq, this);
                }
                break;
        }
    }

    @Override
    public void onResponseReceived(Object responseObj, int requestType) {

        if (responseObj != null) {
            String responseCode = ((BaseRS) responseObj).responseCode;
            if (responseCode.equals("S")) {
                String message = ((RegisterInfoRs) responseObj).message;
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                Intent mIntent = new Intent(this, VerifyOtp.class);
                mIntent.putExtra("phoneNo", mobileNo.getText().toString());
                startActivity(mIntent);
                finish();
                Utility.hideProgressDialog(this);
            }else if (responseCode.equals("N")) {
                String message = ((RegisterInfoRs) responseObj).message;
                Toast.makeText(this, ""+message, Toast.LENGTH_LONG).show();
                Utility.hideProgressDialog(this);
            }
        }
    }
    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidPassword(String email) {
        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(email);

        return matcher.matches() && email.length() > 6;

    }
    private boolean isValidFirstName(String name) {

        return name != null && name.length() > 1;
    }

    private boolean isValidLastName(String name) {

        return name != null && name.length() > 1;
    }

    private boolean isValidMobileNo(String name) {

        return name != null && name.length() == 10;
    }
}
