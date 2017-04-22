package com.fms.fmsindia.fragement;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fms.fmsindia.Dialog.DialogTestReportAddPhoto;
import com.fms.fmsindia.Home;
import com.fms.fmsindia.LocalStorage;
import com.fms.fmsindia.R;
import com.fms.fmsindia.Utility.Utility;
import com.fms.fmsindia.adapter.AddTagRaw;
import com.fms.fmsindia.adapter.UpoladedPhotoAdapter;
import com.fms.fmsindia.fragmentcallback.FragmentCallback;
import com.fms.fmsindia.webservice.RequestManager;
import com.fms.fmsindia.webservice.ResponseListener;
import com.fms.fmsindia.webservices.request.DeleteImageReq;
import com.fms.fmsindia.webservices.request.OtpNumberReq;
import com.fms.fmsindia.webservices.request.UserInfoReq;
import com.fms.fmsindia.webservices.response.BaseRS;
import com.fms.fmsindia.webservices.response.ProfileUpdateRsInfo;
import com.fms.fmsindia.webservices.response.UserInfoRs;
import com.fms.fmsindia.webservices.response.UserRs;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by androiduser2 on 18/8/15.
 */
public class Profile extends FragmentCallback implements View.OnClickListener,DialogTestReportAddPhoto.DialogCallBack, UpoladedPhotoAdapter.CallbackUploadAdapter, ResponseListener {

    View v;
    private Fragment fragment;

//    ListView lv;

    TextView upadte,addimage;

    EditText firstname, lastname, phonenumber, emailid, address, landmark;

    private DialogTestReportAddPhoto mDialog;

    CircleImageView circleImageView;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    OtpNumberReq otpNumberReq = new OtpNumberReq();

    Uri imageUri = null;

    UserInfoReq userInfoReq;

    AddTagRaw mTagRaw;
    private List<AddTagRaw> mList = new ArrayList<AddTagRaw>();

    private int requestCode;

    private Context mCtx;

    public Profile() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.row_profile, container, false);
        // expandableListView = (ExpandableListView) rootView.findViewById(R.id.expListView);

        this.mCtx = getActivity();
        return v;
    }

    @Override
    public void onStart() {
        Home home = new Home();
        home.ValueTitle = "Profile";

        super.onStart();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        lv=(ListView) view.findViewById(R.id.listView);
        firstname = (EditText) view.findViewById(R.id.firstname);
        lastname = (EditText) view.findViewById(R.id.lastname);
        phonenumber = (EditText) view.findViewById(R.id.phonenumber);
        emailid = (EditText) view.findViewById(R.id.emailid);
        address = (EditText) view.findViewById(R.id.address);
        landmark = (EditText) view.findViewById(R.id.landmark);
        upadte= (TextView) view.findViewById(R.id.upadte);
        addimage = (TextView) view.findViewById(R.id.addimage);
        circleImageView = (CircleImageView) view.findViewById(R.id.picid);

        userInfoReq = new UserInfoReq();
        userInfoReq.userId = LocalStorage.getPatientObj(getActivity()).userId;
        OtpNumberReq otpNumberReq = LocalStorage.getPatientObj(getActivity());
        firstname.setText(otpNumberReq.firstName);
        lastname.setText(otpNumberReq.lastName);
        phonenumber.setText(otpNumberReq.phoneNo);
        emailid.setText(otpNumberReq.email);
        address.setText(otpNumberReq.currentAddress);
        landmark.setText(otpNumberReq.landmark);
        mDialog = new DialogTestReportAddPhoto(mCtx, this);
        firstname.setEnabled(false);
        lastname.setEnabled(false);
        phonenumber.setEnabled(false);
        emailid.setEnabled(false);
        address.setEnabled(false);
        landmark.setEnabled(false);
        firstname.setOnClickListener(this);
        lastname.setOnClickListener(this);
        phonenumber.setOnClickListener(this);
        emailid.setOnClickListener(this);
        address.setOnClickListener(this);
        landmark.setOnClickListener(this);
        upadte.setOnClickListener(this);
        addimage.setOnClickListener(this);
        Utility.showProgressDialog(getActivity());
        RequestManager.getUserInfoProfile(getActivity(), userInfoReq, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.firstname:
                SetEnable();
                break;
            case R.id.lastname:
                SetEnable();
                break;
            case R.id.phonenumber:
                SetEnable();
                break;
            case R.id.emailid:
                SetEnable();
                break;
            case R.id.address:
                SetEnable();
                break;
            case R.id.landmark:
                SetEnable();
                break;
            case R.id.upadte:
                if(upadte.getText().toString().equals("DONE")){
                    //upadte.setText("UPDATE");
                    Log.i("nxcxn","==="+createObject());
                    addimage.setVisibility(View.GONE);
                    otpNumberReq.firstName = firstname.getText().toString();
                    otpNumberReq.lastName = lastname.getText().toString();
                    otpNumberReq.email = emailid.getText().toString();
                    otpNumberReq.currentAddress = address.getText().toString();
                    otpNumberReq.landmark = landmark.getText().toString();
                    Utility.showProgressDialog(getActivity());
                    RequestManager.getProfileUpdate(getActivity(),createObject(),otpNumberReq,this);
                    SetDisable();
                }else {
                    SetEnable();
                    addimage.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.addimage:
                initDialog();
                break;
        }
    }
    public void SetEnable(){
        firstname.setEnabled(true);
        lastname.setEnabled(true);

        emailid.setEnabled(true);
        address.setEnabled(true);
        landmark.setEnabled(true);
        upadte.setText("DONE");

    }
    public void SetDisable(){
        firstname.setEnabled(false);
        lastname.setEnabled(false);

        emailid.setEnabled(false);
        address.setEnabled(false);
        landmark.setEnabled(false);
        upadte.setText("UPDATE");

    }

    private void initDialog() {

        mDialog.show();


    }

    @Override
    public void onClickDialog(DialogTestReportAddPhoto.SelectPhoto type) {
        if (type == DialogTestReportAddPhoto.SelectPhoto.PICTURE) {
//            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            if (takePictureIntent.resolveActivity(mCtx.getPackageManager()) != null) {
//                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//            }
            String fileName = "Camera_Example.jpg";

            // Create parameters for Intent with filename

            ContentValues values = new ContentValues();

            values.put(MediaStore.Images.Media.TITLE, fileName);

            values.put(MediaStore.Images.Media.DESCRIPTION, "Image capture by camera");

            imageUri = mCtx.getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);


            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        } else {
            if (type == DialogTestReportAddPhoto.SelectPhoto.GALLERY) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, 2);
            }
        }
    }
    private long resizeImage(String filePath) {
        File file = new File(filePath);

        long length = file.length() / 1024;

        return length;
    }
    private String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String picturePath = null;

        this.requestCode = requestCode;

        if (resultCode == getActivity().RESULT_OK) {

            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                // String  imageId[] = convertImageUriToFile(imageUri).split("-");
                LoadImagesFromSDCard1 loadImagesFromSDCard = new LoadImagesFromSDCard1();

                loadImagesFromSDCard.execute(imageUri);
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();

                String[] filePath = {MediaStore.Images.Media.DATA};

                Cursor c = mCtx.getContentResolver().query(selectedImage, filePath, null, null, null);

                c.moveToFirst();

                int columnIndex = c.getColumnIndex(filePath[0]);

                picturePath = c.getString(columnIndex);

                c.close();
                // Bitmap mBitmap = (BitmapFactory.decodeFile(picturePath));
                // Bitmap resizeBitmap = getResizedBitmap(thumbnail, 648, 480);

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

        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encodedImage;
    }

    private String ecodeFile(String fileName) {

        try {
            InputStream inputStream = new FileInputStream(fileName);//You can get an inputStream using any IO API

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
        }
        return null;
    }

    private void addBitmap(Bitmap mBitmap, String filenames, String pdfFilename, String fileType) {

        if (fileType.toLowerCase().equals("jpg") || fileType.toLowerCase().equals("png") || fileType.toLowerCase().equals("gif") || fileType.toLowerCase().equals("pdf")) {

           circleImageView.setImageBitmap(mBitmap);

            mTagRaw = new AddTagRaw();

            mTagRaw.setTitle(filenames);

            mTagRaw.setBitmap(mBitmap);

            mTagRaw.setPdfFile(pdfFilename);

            mTagRaw.setFileType("." + fileType);

            mList.add(mTagRaw);
        } else {

            Toast toast = Toast.makeText(getActivity(), Html.fromHtml("<font color='#ffffff' >Pdf file only added</font>"), Toast.LENGTH_LONG);

            toast.show();
        }


    }

    @Override
    public void setDeleteOnClick(List<AddTagRaw> mList) {

    }

    @Override
    public void setDeleteImage(DeleteImageReq deleteImageReq) {

    }

    @Override
    public void onResponseReceived(Object responseObj, int requestType) {

        if (responseObj != null) {


            String responseCode = ((BaseRS) responseObj).responseCode;

            if (responseCode.equals("Y")) {
                List<UserRs> userRs = ((UserInfoRs) responseObj).userRs;
                otpNumberReq.userId = userRs.get(0).userId;
                otpNumberReq.phoneNo = userRs.get(0).phone;
                otpNumberReq.firstName = userRs.get(0).firstName;
                otpNumberReq.lastName = userRs.get(0).lastName;
                otpNumberReq.email = userRs.get(0).eMail;
                otpNumberReq.currentAddress = userRs.get(0).currentAddress;
                otpNumberReq.landmark = userRs.get(0).landmark;
                otpNumberReq.role = userRs.get(0).role;
                otpNumberReq.houseId = userRs.get(0).houseId;

                if(userRs.get(0).userimage.equals("")) {
                    Picasso.with(getActivity())
                            .load(R.drawable.mandefault).into(circleImageView);
                }else {
                    Picasso.with(getActivity())
                            .load("http://fmsindia.net/"+userRs.get(0).userimage + "" + userRs.get(0).attachmentname).placeholder(R.drawable.mandefault).into(circleImageView);
                }

                LocalStorage.storePatientObj(getActivity(), otpNumberReq);
                Utility.hideProgressDialog(getActivity());
            }else if(responseCode.equals("S")){

                otpNumberReq.userId = ((ProfileUpdateRsInfo) responseObj).userId;

                otpNumberReq.firstName = ((ProfileUpdateRsInfo) responseObj).firstName;;
                otpNumberReq.lastName = ((ProfileUpdateRsInfo) responseObj).lastName;
                otpNumberReq.email = ((ProfileUpdateRsInfo) responseObj).eMail;
                otpNumberReq.currentAddress = ((ProfileUpdateRsInfo) responseObj).currentAddress;
                otpNumberReq.landmark = ((ProfileUpdateRsInfo) responseObj).landmark;
                LocalStorage.storePatientObj(getActivity(), otpNumberReq);
                Utility.hideProgressDialog(getActivity());

            }else if(responseCode.equals("N")){
                Utility.hideProgressDialog(getActivity());
            }
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

                addBitmap(bitmap, filenames, null, fileType);
            }
            super.onPostExecute(bitmap);
        }
    }
}
