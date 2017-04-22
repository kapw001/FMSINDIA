package com.fms.fmsindia.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fms.fmsindia.R;
import com.fms.fmsindia.webservices.request.DeleteImageReq;

import java.util.List;


/**
 * Created by krishna on 16/12/15.
 */
public class UpoladedPhotoAdapter extends BaseAdapter {

    CallbackUploadAdapter mCallback;
    View v;
    private Activity context;
    private List<AddTagRaw> mList;

    public UpoladedPhotoAdapter(Activity activity) {
        context = activity;
    }

    public void setList(List<AddTagRaw> mList) {
        this.mList = mList;
        // notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        v = convertView;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.test_report_add_file, null);

        final ImageView img = (ImageView) v.findViewById(R.id.uploadedImg);
        if (mList.get(position).getImgUrl() != null) {
//            Picasso.with(context)
//                    .load(mList.get(position).getImgUrl()).into(new Target() {
//
//                @Override
//                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                    img.setBackground(new BitmapDrawable(context.getResources(), bitmap));
//
//                }
//
//                @Override
//                public void onBitmapFailed(final Drawable errorDrawable) {
//                    Log.d("TAG", "FAILED");
//                }
//
//                @Override
//                public void onPrepareLoad(final Drawable placeHolderDrawable) {
//                    Log.d("TAG", "Prepare Load");
//
//
//                }
//            });
        }

        if (mList.get(position).getBitmap() != null) {
            BitmapDrawable ob = new BitmapDrawable(context.getResources(), getRoundedCornerBitmap(mList.get(position).getBitmap()));
            v.findViewById(R.id.uploadedImg).setBackgroundDrawable(ob);
        } else {

        }

        ((TextView) v.findViewById(R.id.fileName)).setText(mList.get(position).getTitle());
        ((TextView) v.findViewById(R.id.fileType)).setText(mList.get(position).getFileType());
        ((TextView) v.findViewById(R.id.fileName)).setTextColor(Color.parseColor("#ffffff"));
        ((TextView) v.findViewById(R.id.fileType)).setTextColor(Color.parseColor("#ffffff"));
        ImageView mImageView = (ImageView) v.findViewById(R.id.deletePhoto);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteImageReq deleteImageReq = new DeleteImageReq();
                deleteImageReq.imgLink = mList.get(position).getImgUrl();
                deleteImageReq.imgId = mList.get(position).getImgId();
                deleteImageReq.imgPrescriptionId = mList.get(position).getImgPrescription();

                    mList.remove(position);

                notifyDataSetChanged();
                mCallback.setDeleteImage(deleteImageReq);
                mCallback.setDeleteOnClick(mList);
            }
        });
        return v;


    }

    public Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = 10;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public void setCallback(CallbackUploadAdapter mCallback) {
        this.mCallback = mCallback;

    }

    public interface CallbackUploadAdapter {

        void setDeleteOnClick(List<AddTagRaw> mList);

        void setDeleteImage(DeleteImageReq deleteImageReq);

    }

}





