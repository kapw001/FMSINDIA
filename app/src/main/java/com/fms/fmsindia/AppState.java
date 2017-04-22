package com.fms.fmsindia;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by krishna on 18/8/15.
 */
public class AppState extends Application {
    public static final String TAG = "VolleyPatterns";
    // System info
    public static String SYSTEM_INFO;
    private static AppState mInstance;

    private ArrayList<String> calendarDates;


    private String medicID;
    private String medicLoginNumber;
    private int position;
    private String selectedDate;

    Boolean filter = false;


    private String dateSelection;
    private String apoinmentDate;
    private RequestQueue mRequestQueue;
    private boolean isRemoveFilterScreen;
    private boolean isGallery;


    public boolean isBottomDateSelection() {
        return isBottomDateSelection;
    }

    public void setBottomDateSelection(boolean bottomDateSelection) {
        isBottomDateSelection = bottomDateSelection;
    }

    private boolean isBottomDateSelection;

    private boolean isBottomDate;

    public boolean isRebookFromHistory() {
        return isRebookFromHistory;
    }

    public void setRebookFromHistory(boolean rebookFromHistory) {
        isRebookFromHistory = rebookFromHistory;
    }

    private boolean isRebookFromHistory;

    private boolean isRebookFromDetail;

    private boolean isAppointmentTab;

    public AppState() {
        super();

    }


    @Override
    public void onCreate() {

        super.onCreate();


        mInstance = this;

        if (AppState.SYSTEM_INFO == null) {
            //Utility.updateSystemInfo(this);
        }


    }


    public static void setLocaleTamil(Context context) {
        Locale locale = new Locale("ta");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(config, null);
    }

    public static void setLocaleEnglish(Context context) {
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(config, null);
    }

    public boolean isRebookFromDetail() {
        return isRebookFromDetail;
    }

    public void setRebookFromDetail(boolean rebookFromDetail) {
        isRebookFromDetail = rebookFromDetail;
    }

    public boolean isBottomDate() {
        return isBottomDate;
    }

    public void setBottomDate(boolean bottomDate) {
        isBottomDate = bottomDate;
    }

    public boolean isGallery() {
        return isGallery;
    }

    public void setGallery(boolean gallery) {
        isGallery = gallery;
    }




    public boolean isRemoveFilterScreen() {
        return isRemoveFilterScreen;
    }

    public void setIsRemoveFilterScreen(boolean isRemoveFilterScreen) {
        this.isRemoveFilterScreen = isRemoveFilterScreen;
    }

    public boolean isAppointmentTab() {
        return isAppointmentTab;
    }

    public void setAppointmentTab(boolean appointmentTab) {
        isAppointmentTab = appointmentTab;
    }


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public ArrayList<String> getCalendarDates() {
        return calendarDates;
    }

    public void setCalendarDates(ArrayList<String> calendarDates) {
        this.calendarDates = calendarDates;
    }

    public String getMedicLoginNumber() {
        return medicLoginNumber;
    }

    public void setMedicLoginNumber(String medicLoginNumber) {
        this.medicLoginNumber = medicLoginNumber;
    }


    public String getMedicID() {
        return medicID;
    }

    public void setMedicID(String medicID) {
        this.medicID = medicID;
    }


    public String getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(String selectedDate) {
        this.selectedDate = selectedDate;
    }

    public String getApoinmentDate() {
        return apoinmentDate;
    }

    public void setApoinmentDate(String apoinmentDate) {
        this.apoinmentDate = apoinmentDate;
    }


    /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    /**
     * Adds the specified request to the global queue, if tag is specified
     * then it is used else Default TAG is used.
     *
     * @param req
     * @param tag
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        VolleyLog.d("Adding request to queue: %s", req.getUrl());

        getRequestQueue().add(req);
    }

    /**
     * Adds the specified request to the global queue using the Default TAG.
     *
     * @param req
     */
    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);

        getRequestQueue().add(req);
    }


}
