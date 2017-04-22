package com.fms.fmsindia.fragement;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewAnimator;

import com.fms.fmsindia.R;
import com.fms.fmsindia.Utility.Utility;
import com.fms.fmsindia.fragmentcallback.FragmentCallback;
import com.fms.fmsindia.plugins.TouchImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by krishna on 2/11/15.
 */

public class HospitalGallery extends FragmentCallback {

    public static final String MIME_TYPE_PDF = "application/pdf";
    public Bitmap bm, icon;
    Context context;
    LinearLayout myGallery;
    ArrayList<String> imageGalleryUrls = new ArrayList<String>();
    ViewPager myPager;
    int imagePosition;
    String fileType;
    Fragment fragment;

    public static boolean canDisplayPdf(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType(MIME_TYPE_PDF);
        return packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }

    public static void DownloadFile(String fileURL, File directory) {
        try {

            FileOutputStream f = new FileOutputStream(directory);
            URL u = new URL(fileURL);
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            InputStream in = c.getInputStream();

            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = in.read(buffer)) > 0) {
                f.write(buffer, 0, len1);
            }
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_image_view, container, false);

        context = getActivity();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            imageGalleryUrls = bundle.getStringArrayList("IMAGE_GALLERY_URL");
            imagePosition = bundle.getInt("imagePosition");
        }


        //GalleryAdapter adapter = new GalleryAdapter(getActivity(), imageGalleryUrls);
        myPager = (ViewPager) v.findViewById(R.id.view_pager);
        myPager.setAdapter(new TouchImageAdapter(imageGalleryUrls));

        myPager.setCurrentItem(0);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        myPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //int pos = myPager.getAdapter().getItemPosition(position);
                // imageGalleryUrls[position];

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });





        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

//        AppState appState = ((AppState) getActivity().getApplicationContext());
//        appState.setGallery(true);
    }

    private View insertPhoto(String path, final int i) {

        fileType = Utility.getFileExt(path);

        Picasso.with(getActivity())
                .load(path)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        bm = bitmap;
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                    }
                });

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new ViewGroup.LayoutParams(180, 150));
        layout.setGravity(Gravity.CENTER);

        ImageView imageView = new ImageView(getActivity());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (fileType.equals("jpg") || fileType.equals("png") || fileType.equals("gif")) {
            imageView.setImageBitmap(bm);
        }
        imageView.setId(i);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String path = imageGalleryUrls.get(i);

                myPager.setCurrentItem(i);
            }
        });

        layout.addView(imageView);

        return layout;
    }

    public void navigateDoc(String s, String fileType) {

        if (canDisplayPdf(getActivity())) {

            String extStorageDirectory = Environment.getExternalStorageDirectory()
                    .toString();
            File folder = new File(extStorageDirectory, "pdf");
            folder.mkdir();
            File file = new File(folder, "document.pdf");
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            DownloadFile(s, file);
            showPdf();

        }


    }

    public void showPdf() {
        File file = new File(Environment.getExternalStorageDirectory() + "/pdf/document.pdf");
        PackageManager packageManager = getActivity().getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/pdf");
        startActivity(intent);
    }

    private class TouchImageAdapter extends PagerAdapter {

        ArrayList<String> imageURL;

        public TouchImageAdapter(ArrayList<String> imageUrls) {
            // TODO Auto-generated constructor stub
            imageURL = imageUrls;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public int getCount() {
            return imageURL.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, final int position) {
            View v = null;
            try {
                final TouchImageView imgDisplay;
                final ViewAnimator animator;
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.view_gallery_zoom,
                        container, false);

                imgDisplay = (TouchImageView) v.findViewById(R.id.image);
                animator = (ViewAnimator) v.findViewById(R.id.animator);

                imgDisplay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String fileType = Utility.getFileExt(imageURL.get(position));

                        if (fileType.equals("pdf")) {
                            navigateDoc(imageURL.get(position), fileType);
                        }


                    }
                });

                Picasso.with(context).load(imageURL.get(position)).error(R.drawable.mandefault)
                        .into(imgDisplay, new Callback.EmptyCallback() {
                            @Override
                            public void onSuccess() {
                                // Index 0 is the image view.
                                animator.setDisplayedChild(0);
                            }
                        });



                container.addView(v);
            } catch (Exception e) {
                //setLog(e.toString());
            }

            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}

