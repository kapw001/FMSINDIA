package com.fms.fmsindia.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.fms.fmsindia.R;


/**
 * Created by krishna on 12/1/16.
 */
public class DialogTestReportAddPhoto extends Dialog implements View.OnClickListener {
    public DialogCallBack myListener;

    private TextView testType, rightText;
    private Context mContext;

    public DialogTestReportAddPhoto(Context context, DialogCallBack myListener) {
        super(context);
        this.mContext = context;
        this.myListener = myListener;

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.test_report_dialog_add_photo);
        setCanceledOnTouchOutside(true);
        findViewById(R.id.layoutGallery).setOnClickListener(this);
        findViewById(R.id.layoutPicture).setOnClickListener(this);

    }

//    private Drawable initDrawbleColor() {
//
//        Drawable myIcon = mContext.getResources().getDrawable(R.mipmap.ic_file_document_box_white_18dp);
//        ColorFilter filter = new LightingColorFilter(mContext.getResources().getColor(R.color.black_dark), mContext.getResources().getColor(R.color.green));
//        myIcon.setColorFilter(filter);
//        return myIcon;
//    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layoutGallery:
                myListener.onClickDialog(SelectPhoto.GALLERY);
                dismiss();
                break;
            case R.id.layoutPicture:
                myListener.onClickDialog(SelectPhoto.PICTURE);
                dismiss();
                break;


        }
    }

    public enum SelectPhoto {GALLERY, PICTURE}


    public interface DialogCallBack {
        void onClickDialog(SelectPhoto type);


    }

}
