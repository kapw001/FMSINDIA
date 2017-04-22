package com.fms.fmsindia.fragement;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.test.mock.MockPackageManager;
import android.text.Html;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fms.fmsindia.Dialog.DialogTestReportAddPhoto;
import com.fms.fmsindia.Home;
import com.fms.fmsindia.LocalStorage;
import com.fms.fmsindia.R;
import com.fms.fmsindia.Utility.Utility;
import com.fms.fmsindia.adapter.AddTagRaw;
import com.fms.fmsindia.adapter.UpoladedPhotoAdapter;
import com.fms.fmsindia.fragmentcallback.FragmentCallback;
import com.fms.fmsindia.plugins.HorizontalListView;
import com.fms.fmsindia.webservice.RequestManager;
import com.fms.fmsindia.webservice.ResponseListener;
import com.fms.fmsindia.webservices.request.DeleteImageReq;
import com.fms.fmsindia.webservices.request.GetCategory;
import com.fms.fmsindia.webservices.request.GetIssueReq;
import com.fms.fmsindia.webservices.request.InsertTicketReq;
import com.fms.fmsindia.webservices.response.BaseRS;
import com.fms.fmsindia.webservices.response.GetCategoryRSinfo;
import com.fms.fmsindia.webservices.response.GetCategoryRs;
import com.fms.fmsindia.webservices.response.GetIssueRs;
import com.fms.fmsindia.webservices.response.GetIssueRsInfo;
import com.fms.fmsindia.webservices.response.GetPriorityRs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by androiduser2 on 14/8/15.
 */
public class Booking extends FragmentCallback implements View.OnClickListener, DialogTestReportAddPhoto.DialogCallBack, UpoladedPhotoAdapter.CallbackUploadAdapter, ResponseListener {

    TextView add, ticketNo;

    private DialogTestReportAddPhoto mDialog;

    static final int REQUEST_IMAGE_CAPTURES = 1;

    EditText otherIssue;

    static EditText preferredCalTime;

    static EditText preferredTimeToAttend;

    EditText description;

    EditText name;

    EditText ticketLastName;

    EditText ticketPhoneNo;

    EditText ticketEmailAddress;

    EditText ticketAddressForCommunication;

    EditText ticketLandMark,dateAndTimeText;

    private Context mCtx;

    private int requestCode;

    private boolean isCalled = false;

    int hide = 0;

    Fragment mFragment;

    String value;

    List<GetCategoryRSinfo> getCategoryRS = new ArrayList<GetCategoryRSinfo>();

    List<GetIssueRs> getIssueRs = new ArrayList<GetIssueRs>();

    List<GetPriorityRs> getPriorityRs = new ArrayList<GetPriorityRs>();

    Spinner categorySpinner, issueSpinner, prioritySpinner;
    ArrayAdapter<String> issueAdapter;
    private HorizontalListView mlistUploadPhoto;

    List<String> listCategory = new ArrayList<String>();

    List<String> id = new ArrayList<String>();

    List<String> listIssue = new ArrayList<String>();

    List<String> listPriority = new ArrayList<String>();

    private UpoladedPhotoAdapter mAdapter;

    String category="", issue="", priority="";

    AddTagRaw mTagRaw;

    CheckBox otherIssueCheck;

    Button insertTicket;

    GetCategory getCategory;

    InsertTicketReq insertTicketReq;

    Uri imageUri = null;

    String catId;

        String img = "content://media/external/images/media/14380";

    public String formattedDate;

    private Fragment fragment;

    private static final int REQUEST_CODE_PERMISSION = 2;
    String[] mPermission = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private List<AddTagRaw> mList = new ArrayList<AddTagRaw>();

    BookCallback bookCallback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        bookCallback=(BookCallback)activity;
    }

    public Booking() {
    }
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.booking, container, false);

        this.mCtx = getActivity();
        Home home = new Home();
        home.ValueTitle = "Raise Ticket";
        ticketNo = (TextView) v.findViewById(R.id.ticketNo);

        add = (TextView) v.findViewById(R.id.add);

        otherIssueCheck = (CheckBox) v.findViewById(R.id.otherIssueCheck);

        categorySpinner = (Spinner) v.findViewById(R.id.categorySpinner);

        issueSpinner = (Spinner) v.findViewById(R.id.issueSpinner);

        prioritySpinner = (Spinner) v.findViewById(R.id.prioritySpinner);

        insertTicket = (Button) v.findViewById(R.id.insertTicket);

        mDialog = new DialogTestReportAddPhoto(mCtx, this);

        mlistUploadPhoto = (HorizontalListView) v.findViewById(R.id.listUpoloadPhoto);

        otherIssue = (EditText) v.findViewById(R.id.otherIssue);

        preferredCalTime = (EditText) v.findViewById(R.id.preferredCalTime);

        description = (EditText) v.findViewById(R.id.description);

        preferredTimeToAttend = (EditText) v.findViewById(R.id.preferredTimeToAttend);

        name = (EditText) v.findViewById(R.id.name);

        name.setVisibility(View.GONE);

        ticketLastName = (EditText) v.findViewById(R.id.ticketLastName);

        ticketLastName.setVisibility(View.GONE);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formattedDate = df.format(c.getTime());

        dateAndTimeText = (EditText) v.findViewById(R.id.dateAndTimeText);

        dateAndTimeText.setEnabled(false);

        dateAndTimeText.setText(formattedDate);


        ticketPhoneNo = (EditText) v.findViewById(R.id.ticketPhoneNo);

        ticketPhoneNo.setVisibility(View.GONE);

        ticketPhoneNo.setText(LocalStorage.getPatientObj(getActivity()).phoneNo);

        ticketEmailAddress = (EditText) v.findViewById(R.id.ticketEmailAddress);

        ticketEmailAddress.setVisibility(View.GONE);

        ticketAddressForCommunication = (EditText) v.findViewById(R.id.ticketAddressForCommunication);

        ticketAddressForCommunication.setEnabled(false);

        ticketLandMark = (EditText) v.findViewById(R.id.ticketLandMark);

        ticketLandMark.setEnabled(false);

        insertTicketReq = new InsertTicketReq();

        ticketNo.setEnabled(false);

        add.setOnClickListener(this);
        name.setText(LocalStorage.getPatientObj(getActivity()).firstName);

        ticketLastName.setText(LocalStorage.getPatientObj(getActivity()).lastName);

        ticketEmailAddress.setText(LocalStorage.getPatientObj(getActivity()).email);

        ticketAddressForCommunication.setText(LocalStorage.getPatientObj(getActivity()).currentAddress);

        ticketLandMark.setText(LocalStorage.getPatientObj(getActivity()).landmark);

        preferredCalTime.setOnClickListener(this);

        preferredTimeToAttend.setOnClickListener(this);

        preferredCalTime.setFocusable(false);

        preferredTimeToAttend.setFocusable(false);

        insertTicket.setOnClickListener(this);

        otherIssueCheck.setOnClickListener(this);

        description.setOnClickListener(this);

        mAdapter = new UpoladedPhotoAdapter((Activity) mCtx);

        mAdapter.setCallback(this);

        getCategory = new GetCategory();

        RequestManager.getCategory(mCtx, getCategory, this);

        //RequestManager.getPriority(mCtx, getCategory, this);
        Utility.showProgressDialog(getActivity());
        listCategory.add("Select");
        Log.v("oncreate","=======");
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            catId = bundle.getString("values");

            }


        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                if (isCalled == true) {

                    if (arg2 != 0) {

                        showRes("" + getCategoryRS.get(arg2 - 1).categoryId);

                    }

                } else {
                    isCalled = true;

                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        issueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    issue = getIssueRs.get(position - 1).issueName;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        prioritySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    priority = getPriorityRs.get(position).priorityId;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return v;

    }

    @Override
    public void onStart() {
        super.onStart();
        isCalled = false;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.add:
                //initAppPermission();
                initDialog();

                break;

            case R.id.insertTicket:


                if(categorySpinner.getSelectedItem().toString().equals("Select"))
                {


                    Toast toast = Toast.makeText(getActivity(), Html.fromHtml("<font color='#ffffff' >Plz Select Category</font>"), Toast.LENGTH_LONG);

                    toast.show();

                }else if(preferredCalTime.getText().toString().equals("")) {
                    preferredCalTime.setError("please Enter the Date");
                }
                else if(preferredTimeToAttend.getText().toString().equals("")) {
                    preferredTimeToAttend.setError("please Enter the Date");
                }
                else if(description.getText().toString().equals("")) {
                    description.setError("please Enter the Description");
                }
                else
                {
                    insertTicketReq.name = name.getText().toString();
                    insertTicketReq.lastName = ticketLastName.getText().toString();
                    insertTicketReq.phoneNo = ticketPhoneNo.getText().toString();
                    insertTicketReq.email = ticketEmailAddress.getText().toString();
                    insertTicketReq.address = ticketAddressForCommunication.getText().toString();
                    insertTicketReq.landMark = ticketLandMark.getText().toString();
                    insertTicketReq.category = "" + category;
                    if(issue.equals("")){
                        insertTicketReq.issue =otherIssue.getText().toString();
                    }else {
                        insertTicketReq.issue = "" + issue;
                    }
                    //insertTicketReq.otherIssue = otherIssue.getText().toString();
                    insertTicketReq.cboIssue = "" + name.getText().toString();
                    insertTicketReq.priority = "" + priority;
                    if(preferredCalTime.getText().toString().equals("")){

                        insertTicketReq.callTime = "";
                    }else {
                        insertTicketReq.callTime = "" + Utility.convertDayTo(preferredCalTime.getText().toString());
                    }
                    insertTicketReq.timeToAttend =""+ preferredTimeToAttend.getText().toString();
                    insertTicketReq.description =""+ description.getText().toString();
                    Utility.showProgressDialog(getActivity());
                    AsyncTaskListener mListener = new AsyncTaskListener();
                    mListener.execute();
                }


                //RequestManager.insertTicket(mCtx,createObject(), insertTicketReq, this);

                Log.i("Json", "Value" + Environment.getExternalStorageDirectory());

//
                break;

            case R.id.otherIssueCheck:

                if (otherIssueCheck.isChecked() == true) {

                    otherIssue.setVisibility(View.VISIBLE);

                } else {

                    otherIssue.setVisibility(View.GONE);

                }

                break;
            case R.id.preferredTimeToAttend:
                if(preferredCalTime.getText().toString().equals("")){



                    Toast toast = Toast.makeText(getActivity(), Html.fromHtml("<font color='#ffffff' >Please set the Preferred Date Time</font>"), Toast.LENGTH_LONG);

                    toast.show();

                }else {
                    DialogFragment newFragment1 = new TimePickerFragment();
                    newFragment1.show(getFragmentManager(), "TimePicker");

                }
                preferredTimeToAttend.setError(null);
                break;
            case R.id.preferredCalTime:

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getChildFragmentManager(),"Date Picker");
                preferredCalTime.setError(null);
                break;
            case R.id.description:

                description.setError(null);

                break;
        }
    }

    private void initDialog() {
        if (mList.size() == 1) {
            Toast toast = Toast.makeText(getActivity(), Html.fromHtml("<font color='#ffffff' >Can't share more than 1 file</font>"), Toast.LENGTH_LONG);

            toast.show();
        } else {
            mDialog.show();
        }


    }

    @Override
    public void onResponseReceived(Object responseObj, int requestType) {
        Utility.hideProgressDialog(getActivity());
        if (!listIssue.isEmpty()) {
            listIssue.clear();
            issueAdapter.notifyDataSetChanged();
        }
        if (responseObj != null) {

            String responseCode = ((BaseRS) responseObj).responseCode;

            if (responseCode.equals("S")) {
                //hide = hide + 1;
                List<GetCategoryRSinfo> getCategoryRSInfo = ((GetCategoryRs) responseObj).categoryDetails;
                getCategoryRS = getCategoryRSInfo;
                for (int i = 0; i < getCategoryRSInfo.size(); i++) {

                    listCategory.add(getCategoryRSInfo.get(i).categoryName);
                    id.add(getCategoryRSInfo.get(i).categoryId);

                }

                if (listCategory.size()>0) {

                    ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listCategory);

                    // Drop down layout style - list view with radio button
                    categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // attaching data adapter to spinner
                    categorySpinner.setAdapter(categoryAdapter);
                }
                List<GetPriorityRs> getPriorityRses = ((GetCategoryRs) responseObj).priorityDetails;

                getPriorityRs = getPriorityRses;

                for (int i = 0; i < getPriorityRses.size(); i++) {

                    listPriority.add(getPriorityRses.get(i).priorityName);

                }

                try{
                    ArrayAdapter<String> issueAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listPriority);

                    // Drop down layout style - list view with radio button
                    issueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // attaching data adapter to spinner
                    prioritySpinner.setAdapter(issueAdapter);

                }catch (NullPointerException e){
                    Log.e("Booking", "onResponseReceived: null pointer exception "+e );
                }

                if(catId.equals("")) {
                    categorySpinner.setEnabled(true);
                    categorySpinner.setSelection(0);

                }else {



                    categorySpinner.setSelection(Integer.parseInt(catId));

                    categorySpinner.setEnabled(false);

                    showRes(catId);
                }


                    Utility.hideProgressDialog(getActivity());


            } else if (responseCode.equals("Y")) {

                List<GetIssueRs> getIssueRses = ((GetIssueRsInfo) responseObj).issueDetails;
                getIssueRs = getIssueRses;
//                if(!listIssue.isEmpty()){
//                    listIssue.clear();
//                }

                listIssue.add("Select");

                for (int i = 0; i < getIssueRses.size(); i++) {

                    listIssue.add(getIssueRses.get(i).issueName);

                }


                try{
                    issueAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, listIssue);

                    // Drop down layout style - list view with radio button
                    issueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // attaching data adapter to spinner
                    issueSpinner.setAdapter(issueAdapter);

                }catch (NullPointerException e){
                    Log.e("Booking", "onResponseReceived: null pointer exception "+e );
                }



                Utility.hideProgressDialog(getActivity());


            } else if (responseCode.equals("YS")) {


            } else if (responseCode.equals("SS")) {
                Utility.hideProgressDialog(getActivity());


                Toast toast = Toast.makeText(getActivity(), Html.fromHtml("<font color='#ffffff' >Ticket Successfully Created</font>"), Toast.LENGTH_LONG);

                toast.show();
                bookCallback.SetBookTab();

            } else if (responseCode.equals("SSS")) {
                Utility.hideProgressDialog(getActivity());

                Toast toast = Toast.makeText(getActivity(), Html.fromHtml("<font color='#ffffff' >Ticket Successfully Created</font>"), Toast.LENGTH_LONG);

                toast.show();

                bookCallback.SetBookTab();
            }
        }

    }

    public interface BookCallback{

        void SetBookTab();
    }

    public void showRes(String val){
        GetIssueReq getIssueReq = new GetIssueReq();
        Log.d("value", "========");
        getIssueReq.issueId = val;
        Utility.showProgressDialog(getActivity());
        RequestManager.getIssue(mCtx, getIssueReq, Booking.this);
        category = val;

    }
    @Override
    public void onClickDialog(DialogTestReportAddPhoto.SelectPhoto type) {

        if (type == DialogTestReportAddPhoto.SelectPhoto.PICTURE) {


            String fileName = "Camera_Example.jpg";


            ContentValues values = new ContentValues();

            values.put(MediaStore.Images.Media.TITLE, fileName);

            values.put(MediaStore.Images.Media.DESCRIPTION, "Image capture by camera");

            imageUri = getActivity().getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            Intent inten = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            inten.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

            inten.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

            Log.e("int"+inten,"bxcb");


            startActivityForResult(inten, REQUEST_IMAGE_CAPTURES);
        } else {
            if (type == DialogTestReportAddPhoto.SelectPhoto.GALLERY) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                Log.v("====9==", "==========" );
                startActivityForResult(intent, 2);
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String picturePath = null;

        Log.v("====12E==", "=========="+data);

        this.requestCode = requestCode;

        if (resultCode == getActivity().RESULT_OK) {

            if (requestCode == REQUEST_IMAGE_CAPTURES) {

                    LoadImagesFromSDCard1 loadImagesFromSDCard = new LoadImagesFromSDCard1();

                    loadImagesFromSDCard.execute(imageUri);

                Log.v("====6==", "=========="+imageUri );
            } else if (requestCode == 2) {
                Log.v("====7==", "==========" );
                Uri selectedImage = data.getData();

                String[] filePath = {MediaStore.Images.Media.DATA};

                Cursor c = mCtx.getContentResolver().query(selectedImage, filePath, null, null, null);

                c.moveToFirst();

                int columnIndex = c.getColumnIndex(filePath[0]);

                picturePath = c.getString(columnIndex);

                c.close();

                resizeImage(picturePath);

                String filenames = picturePath.substring(picturePath.lastIndexOf("/") + 1);

                String str = filenames.substring(0, filenames.length() - 5);

                String fileType = filenames.substring(filenames.lastIndexOf(".") + 1);

                Bitmap mBitmap = null;
                try {
                    if (resizeImage(picturePath) > 1024) {

                        BitmapFactory.Options options = new BitmapFactory.Options();

                        options.inSampleSize = 8;

                        Bitmap bitmap1 = BitmapFactory.decodeFile(picturePath, options);
                        //mBitmap = BitmapFactory.decodeFile(picturePath);
                        addBitmap(bitmap1, str, null, fileType);

                    } else {
                        mBitmap = MediaStore.Images.Media.getBitmap(mCtx.getContentResolver(), selectedImage);

                        addBitmap(mBitmap, str, null, fileType);
                    }


                    // addBitmap(mBitmap, str, null, fileType);
                    requestCode = 2;
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    private void addBitmap(Bitmap mBitmap, String filenames, String pdfFilename, String fileType) {
        Log.v("====5==", "==========" );
        Log.v("====12D==", "=========="+getActivity() );
        if (fileType.toLowerCase().equals("jpg") || fileType.toLowerCase().equals("png") || fileType.toLowerCase().equals("gif") || fileType.toLowerCase().equals("pdf")) {

            mTagRaw = new AddTagRaw();

            mTagRaw.setTitle(filenames);

            mTagRaw.setBitmap(mBitmap);

            mTagRaw.setPdfFile(pdfFilename);

            mTagRaw.setFileType("." + fileType);

            mList.add(mTagRaw);

            mAdapter.setList(mList);

            mlistUploadPhoto.setVisibility(View.VISIBLE);

            mlistUploadPhoto.setAdapter(mAdapter);

        } else {

            Toast toast = Toast.makeText(getActivity(), Html.fromHtml("<font color='#ffffff' >Pdf file only added</font>"), Toast.LENGTH_LONG);

            toast.show();
        }


    }

    public JSONObject createObject() {

        JSONObject mainObj = null;

        if (mList.size() == 0) {

            Log.i("null", "null");

            return null;
        }
        try {

            JSONArray ja = new JSONArray();

            for (int i = 0; i < mList.size(); i++) {

                JSONObject pnObj = new JSONObject();

                if (mList.get(i).getPdfFile() != null) {

                    pnObj.put("imagename", mList.get(i).getTitle() + mList.get(i).getFileType());

                    pnObj.put("image", ecodeFile(mList.get(i).getPdfFile()));


                } else {

                    pnObj.put("imagename", mList.get(i).getTitle() + mList.get(i).getFileType());

                    pnObj.put("image", encodeToString(mList.get(i).getBitmap()));
                }
                ja.put(pnObj);
            }


            mainObj = new JSONObject();

            mainObj.put("prescriptionfiles", ja);

            Log.v("Json oject", "===" + mainObj.toString());

            return mainObj;
        } catch (JSONException ex) {
            ex.printStackTrace();
        }


        return null;
    }

    private String encodeToString(Bitmap mBitmap) {
        // Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_account_outline_black_24dp);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object

        byte[] b = baos.toByteArray();
        Log.v("====12C==", "=========="+getActivity() );
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encodedImage;
    }

    private String ecodeFile(String fileName) {

        try {
            InputStream inputStream = new FileInputStream(fileName);//You can get an inputStream using any IO API
            Log.v("====12==", "=========="+getActivity() );
            byte[] bytes;

            byte[] buffer = new byte[8192];

            int bytesRead;

            ByteArrayOutputStream output = new ByteArrayOutputStream();

            while ((bytesRead = inputStream.read(buffer)) != -1) {

                output.write(buffer, 0, bytesRead);
            }

            bytes = output.toByteArray();

            return Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("====12B==", "=========="+getActivity() );
        }
        return null;
    }

    private long resizeImage(String filePath) {
        File file = new File(filePath);
        Log.v("====13==", "=========="+getActivity() );

        long length = file.length() / 1024;
        Log.v("====4==", "==========" );
        return length;
    }

    private String getRealPathFromURI(Uri uri) {
        Log.v("====14==", "=========="+getActivity() );
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    @Override
    public void setDeleteOnClick(List<AddTagRaw> mList) {
        if (mList.size() == 0) {
            mlistUploadPhoto.setVisibility(View.GONE);
        }
    }

    @Override
    public void setDeleteImage(DeleteImageReq deleteImageReq) {

    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //Use the current time as the default values for the time picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create and return a new instance of TimePickerDialog
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        //onTimeSet() callback method
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            //Do something with the user chosen time
            //Get reference of host activity (XML Layout File) TextView widget
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate = df.format(c.getTime());
            String dateValue[] = preferredCalTime.getText().toString().split("-");
            String dateValue1[] = formattedDate.split("-");

            if(Integer.parseInt(dateValue[0]) == Integer.parseInt(dateValue1[0]) && Integer.parseInt(dateValue[1]) == Integer.parseInt(dateValue1[1]) && Integer.parseInt(dateValue[2]) == Integer.parseInt(dateValue1[2])){
                SimpleDateFormat hf = new SimpleDateFormat("HH");
                SimpleDateFormat hm = new SimpleDateFormat("mm");
                String hour = hf.format(c.getTime());

                int hourOfDays = Integer.parseInt(hour);
                String mintes = hm.format(c.getTime());

                int min = Integer.parseInt(mintes);

                int hourOfDayss = (hourOfDays * 60 )+min+30;

                int hoursi = hourOfDayss/60;

                int mintes1 = hourOfDayss - (hoursi * 60);



                int hourOfDaysss = (hourOfDay * 60 )+minute;
                Log.i("fdfhhd","tt"+hourOfDayss+":"+hourOfDaysss);
                String val;
                if(minute <10){
                     val ="0";
                }else {
                    val ="";
                }


                if (hourOfDaysss >= hourOfDayss){
                        preferredTimeToAttend.setText(hourOfDay + ":"+val + minute);

                }else {

                    Toast toast = Toast.makeText(getActivity(), Html.fromHtml("<font color='#ffffff' >Please provide your convenient time - 30 minutes from now.</font>"), Toast.LENGTH_LONG);

                    toast.show();
                }
            }else
            {
                String val;
                if(minute <10){
                    val ="0";
                }else {
                    val ="";
                }
                SimpleDateFormat hf = new SimpleDateFormat("HH");
                String hour = hf.format(c.getTime());
                preferredTimeToAttend.setText(hourOfDay + ":"+val + minute);
            }



        }
    }

    public static class TimePickerCallFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //Use the current time as the default values for the time picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            //Create and return a new instance of TimePickerDialog
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        //onTimeSet() callback method
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            //Do something with the user chosen time
            //Get reference of host activity (XML Layout File) TextView widget
            preferredCalTime.setText(hourOfDay + ":" + minute);
        }
    }

    public class LoadImagesFromSDCard1 extends AsyncTask<Uri, Uri, Bitmap> {

        Bitmap mBitmap;

        private Context context;

        private String filePath;


        @Override
        protected Bitmap doInBackground(Uri... params) {

            Bitmap bitmap = null;

            Bitmap newBitmap = null;

            Uri uri = null;
            Log.v("===sen=======", "99999999" + params[0]);

            filePath = getRealPathFromURI(params[0]);

            Log.v("==========", "99999999" + filePath);

            try {

                //  uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "" + params[0]);
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();

                bitmap = BitmapFactory.decodeFile(getRealPathFromURI(params[0]), bmOptions);
                //  bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));

                if (bitmap != null) {

                    newBitmap = Bitmap.createScaledBitmap(bitmap, 440, 600, true);

                    bitmap.recycle();
                    Log.v("====1==", "==========" );
                    return mBitmap = newBitmap;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                // String  imageId[] = convertImageUriToFile(imageUri, (Activity) mCtx).split("-");
                Log.v("======", "==========" + bitmap);

                String fileType = filePath.substring(filePath.lastIndexOf(".") + 1);

                String filenames = filePath.substring(filePath.lastIndexOf("/") + 1);

                Log.v("====file name==", "==========" + fileType + "====" + filenames);

                Log.v("====2==", "==========" );
                addBitmap(bitmap, filenames, null, fileType);
            }
            super.onPostExecute(bitmap);
        }


    }

    public class AsyncTaskListener extends AsyncTask<JSONObject, JSONObject, JSONObject> {
        @Override
        protected void onPreExecute() {
            // Utility.showProgressDialog(mCtx);
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(JSONObject... jsonObjects) {

            return createObject();
        }

        @Override
        protected void onPostExecute(JSONObject s) {

            //RequestManager.uploadPrescription(mCtx, medicineObject, uploadPrescriptionReq, s, AddPrescriptionFragment.this);
            RequestManager.insertTicket(mCtx, s, insertTicketReq, Booking.this);

            super.onPostExecute(s);
        }


    }


    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            //Use the current date as the default date in the date picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);

            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

//            c.add(Calendar.DATE, 15);
//            datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());



            return datePickerDialog;
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
            //Do something with the date chosen by the user

            int months = month + 1;
            String stringOfDate = day + "-" + months + "-" + year;
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = df.format(c.getTime());

           preferredCalTime.setText(stringOfDate);

           
        }
    }
    private void initAppPermission() {
        try {
            if (ActivityCompat.checkSelfPermission(getActivity(), mPermission[0])
                    != MockPackageManager.PERMISSION_GRANTED ) {

                ActivityCompat.requestPermissions(getActivity(),
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
            if (grantResults.length == 1 &&
                    grantResults[0] == MockPackageManager.PERMISSION_GRANTED) {

                // Success Stuff here

            }
        }

    }

}