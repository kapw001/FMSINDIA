package com.fms.fmsindia.registion;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.test.mock.MockPackageManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fms.fmsindia.Home;
import com.fms.fmsindia.LocalStorage;
import com.fms.fmsindia.R;
import com.fms.fmsindia.Utility.Utility;
import com.fms.fmsindia.webservice.RequestManager;
import com.fms.fmsindia.webservice.ResponseListener;
import com.fms.fmsindia.webservices.request.LoginReq;
import com.fms.fmsindia.webservices.request.OtpNumberReq;
import com.fms.fmsindia.webservices.response.BaseRS;
import com.fms.fmsindia.webservices.response.LoginRs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Login extends AppCompatActivity implements View.OnClickListener, ResponseListener {

    TextView userName, password, createaccount;

    RelativeLayout linearLayout;

    Button submit;
    LoginReq loginReq;
    OtpNumberReq otpNumberReq;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String[] mPermission = {Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createaccount = (TextView) findViewById(R.id.createaccount);

        linearLayout = (RelativeLayout) findViewById(R.id.LoginLayout);


        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getApplicationContext(), MobileNo.class);
                startActivity(mIntent);
//                finish();
            }
        });
        initAppPermission();
        userName = (TextView) findViewById(R.id.User_Name);
        password = (TextView) findViewById(R.id.Password);
        submit = (Button) findViewById(R.id.signIn);
        otpNumberReq = new OtpNumberReq();

        userName.setOnClickListener(this);

        password.setOnClickListener(this);

        submit.setOnClickListener(this);
//        userName.setText("cieo.vuser@gmail.com");
//        password.setText("vuser@123");
//        userName.setText("8148153229");
//        password.setText("senthil");
//       userName.setText("cieo.vendor@gmail.com");
//       password.setText("vendor@123");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signIn:
                if (Utility.internetIsAvailable(this)) {
                    if (userName.getText().toString().isEmpty()) {
                        userName.setError("Enter Valid Email");
                    } else if (password.getText().toString().isEmpty()) {
                        password.setError("Enter Invalid Password ");
                    } else {
                        loginReq = new LoginReq();
                        loginReq.userName = userName.getText().toString();
                        loginReq.password = password.getText().toString();
                        Utility.showProgressDialog(this);
                        RequestManager.getPrescriptionsHistory(this, loginReq, this);
                    }
                } else {
                    initNetworkSnackBar();
                }
                break;
            case R.id.passsword:
                password.setError(null);
                break;
            case R.id.userName:
                userName.setError(null);
                break;
        }
    }

    @Override
    public void onResponseReceived(Object responseObj, int requestType) {

        if (responseObj != null) {

            String responseCode = ((BaseRS) responseObj).responseCode;

            if (responseCode.equals("Y")) {
                String insert = ((LoginRs) responseObj).message;
                otpNumberReq.userId = ((LoginRs) responseObj).userId;

                otpNumberReq.phoneNo = ((LoginRs) responseObj).logInPhone;
                otpNumberReq.firstName = ((LoginRs) responseObj).firstname;
                otpNumberReq.lastName = ((LoginRs) responseObj).lastname;
                otpNumberReq.email = ((LoginRs) responseObj).email;
                otpNumberReq.currentAddress = ((LoginRs) responseObj).currentaddress;
                otpNumberReq.landmark = ((LoginRs) responseObj).landmark;
                otpNumberReq.role = ((LoginRs) responseObj).role;
                otpNumberReq.houseId = ((LoginRs) responseObj).houseid;
                otpNumberReq.userimage = ((LoginRs) responseObj).userimage;
                otpNumberReq.attachmentname = ((LoginRs) responseObj).attachmentname;
                ;
                LocalStorage.storePatientObj(this, otpNumberReq);

                Utility.setLoginedStatus(this, true);
                this.startActivity(new Intent(this, Home.class));

                finish();

                Toast toast = Toast.makeText(this, Html.fromHtml("<font color='#ffffff' >" + insert + "</font>"), Toast.LENGTH_LONG);

                toast.show();
                Utility.hideProgressDialog(this);
            } else {
                Utility.hideProgressDialog(this);

                Toast toast = Toast.makeText(this, Html.fromHtml("<font color='#ffffff' >Invalid UserName And Password</font>"), Toast.LENGTH_LONG);

                toast.show();
                //this.startActivity(new Intent(this, Home.class));
            }
        }

    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$" ;

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null) {
            return true;
        }
        return false;
    }

    private void initAppPermission() {
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission[0])
                    != MockPackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, mPermission[1])
                            != MockPackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, mPermission[2])
                            != MockPackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(this, mPermission[3])
                            != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        mPermission, REQUEST_CODE_PERMISSION);

                // If any permission aboe not allowed by user, this condition will execute every tim, else your else part will work
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("Req Code", "" + requestCode);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length == 4 &&
                    grantResults[0] == MockPackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == MockPackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == MockPackageManager.PERMISSION_GRANTED &&
                    grantResults[3] == MockPackageManager.PERMISSION_GRANTED) {

                // Success Stuff here

            }
        }

    }

    private void initNetworkSnackBar() {
        Snackbar snackbar = Snackbar.make(linearLayout, "Network Issue ! Unable to fetch the data", Snackbar.LENGTH_LONG)
                .setAction("SETTINGS", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                    }
                });
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.show();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
