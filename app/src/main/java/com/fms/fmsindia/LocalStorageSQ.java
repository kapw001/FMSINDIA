package com.fms.fmsindia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.util.Log;


import com.fms.fmsindia.webservices.response.VendorTicketAssignedRs;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by krishna on 5/10/15.
 */
public class LocalStorageSQ extends SQLiteOpenHelper {



    private static final String DB_NAME = "offline.db";
    private static final int DB_VERSION = 4;

    private static final String TABLE_PATIENT_PRESCRIPTION = "contacts";
    private static final String PR_PID = "prid";
    private static final String PR_MEDIC_ID = "medic";
    private static final String PR_PRESCRIPTION_ID = "prescription_id";
    private static final String PR_HOSPITAL_NAME = "hospital_name";





    private Context mContext;

    public LocalStorageSQ(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create  table



        String CREATE_PRESCRIPTION_TABLE = "CREATE TABLE " + TABLE_PATIENT_PRESCRIPTION + "("
                + PR_PID + " INTEGER PRIMARY KEY," + PR_MEDIC_ID + " TEXT," + PR_PRESCRIPTION_ID + " TEXT," + PR_HOSPITAL_NAME+" TEXT "+ ")";




        db.execSQL(CREATE_PRESCRIPTION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older  table if existed

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT_PRESCRIPTION);

        // create fresh  table
        this.onCreate(db);
    }




    public void addVendorUser(List<VendorTicketAssignedRs> medicines_List) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        for(int i =0;i<medicines_List.size();i++)

        {
            values.put(PR_MEDIC_ID, medicines_List.get(i).assignedId);
            values.put(PR_PRESCRIPTION_ID, medicines_List.get(i).userName); // Contact Name


            db.insert(TABLE_PATIENT_PRESCRIPTION, null, values);

        }
        //db.close();

    }

    public String getVendorUser(String valueId) {
        String mList = "";
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PATIENT_PRESCRIPTION + " WHERE "+PR_MEDIC_ID+ " = " + "'" + valueId + "'";;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                mList = cursor.getString(0);



            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return contact list
        return mList;
    }

    public String getVendorUserResolved(String valueId) {
        String mList = "";
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PATIENT_PRESCRIPTION + " WHERE "+PR_MEDIC_ID+ " = " + "'" + valueId + "'";;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                mList = cursor.getString(0);



            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return contact list
        return mList;
    }
    public String getVendorUserName(String valueId) {
        String mList = "";
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PATIENT_PRESCRIPTION + " WHERE "+PR_MEDIC_ID+ " = " + "'" + valueId + "'";;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                mList = cursor.getString(2);



            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return contact list
        return mList;
    }
    public String getVendorUserNameid(String valueId) {
        String mList = "";
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PATIENT_PRESCRIPTION + " WHERE "+PR_PRESCRIPTION_ID+ " = " + "'" + valueId + "'";;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                mList = cursor.getString(1);



            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return contact list
        return mList;
    }
    public String getVendorUserResolvedNameid(String valueId) {
        String mList = "";
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PATIENT_PRESCRIPTION + " WHERE "+PR_PRESCRIPTION_ID+ " = " + "'" + valueId + "'";;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                mList = cursor.getString(1);



            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return contact list
        return mList;
    }

    public String getVendorUserResolvedName(String valueId) {
        String mList = "";
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PATIENT_PRESCRIPTION + " WHERE "+PR_MEDIC_ID+ " = " + "'" + valueId + "'";;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                mList = cursor.getString(2);



            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return contact list
        return mList;
    }
    public void clearPrescTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_PATIENT_PRESCRIPTION);
        db.close();
        // db.delete(TABLE_APPOITMNET,null,null);
    }




}
