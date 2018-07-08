package test.chenfengweiqing.com.companyinfo.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import test.chenfengweiqing.com.companyinfo.db.Constants;

import static test.chenfengweiqing.com.companyinfo.db.Constants.IS_DEBUG;

/**
 * Created by lcz on 17-8-28.
 */

public class CompanyInfoUtils {
    private static final String TAG = "CompanyInfoUtils";

    public static Cursor queryCompany(@NonNull Context context, @NonNull Uri table, @Nullable String[] projection,
                                      @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder
            , int queryType, int start) {
        Cursor cursor = null;
        try {
            switch (queryType){
                case Constants.QueryType.ALL:
                    cursor = context.getContentResolver().query(table, projection, selection, selectionArgs, sortOrder);
                    break;
                case Constants.QueryType.HOPE:
                    StringBuffer where = new StringBuffer();
                    where.append(Constants.Columns.IS_HOPE);
                    where.append("=");
                    where.append("\'" + 1 + "\'");
                    cursor = context.getContentResolver().query(table, projection,where.toString() , selectionArgs, sortOrder);
                    break;
                case Constants.QueryType.UN_CALL:
                    StringBuffer sb = new StringBuffer();
                    sb.append(Constants.Columns.IS_CALLED);
                    sb.append("=");
                    sb.append("\'" + 0 + "\'");
                    cursor = context.getContentResolver().query(table, projection, sb.toString(), selectionArgs, sortOrder);
                    break;
            }
        } catch (Exception e) {
            if(IS_DEBUG){
                Log.d(TAG, "queryNaviHistory error   " + e.toString());
            }
        }
        return cursor;
    }

    public static void deleteCompany(@NonNull Context context, @NonNull Uri table, String where, String[] selectionArgs) {
        if(IS_DEBUG){
            Log.d(TAG, "deleteCompany table " + table);
        }
        context.getContentResolver().delete(table, where, selectionArgs);
    }

    public static void updateCompanyInfo(@NonNull Context context, @NonNull Uri table, ContentValues values, String where, String[] selectionArgs) {
        if(IS_DEBUG){
            Log.d(TAG, "updateCompanyInfo table " + table);
        }
        context.getContentResolver().update(table, values, where, selectionArgs);
    }

    public static void insertCompanyInfo(@NonNull Context context, @NonNull Uri table, ContentValues values) {
        boolean isFavorite = false;
        if (!TextUtils.isEmpty(values.getAsString(Constants.Columns.NAME))) {
            context.getContentResolver().insert(table, values);
        }
    }

    /**
     * @param isLoad the.
     * @return WeatherPrefer.
     */
    public static void putLoadInfo(@NonNull Context context, boolean isLoad) {
        context.getSharedPreferences(Constants.PREFER_NAME, Context.MODE_PRIVATE).edit()
                .putBoolean(Constants.COMPANY_IS_LOAD, isLoad).apply();
        Intent updateInfo = new Intent(Constants.UPDATE_SHOW_VIEW);
        context.sendBroadcast(updateInfo);
    }

    /**
     * @return the isLoaded;
     */
    public static boolean isLoaded(@NonNull Context context) {
        return context.getSharedPreferences(Constants.PREFER_NAME, Context.MODE_PRIVATE)
                .getBoolean(Constants.COMPANY_IS_LOAD, false);
    }

    /**
     * @return WeatherPrefer.
     */
    public static void putCurrentIndex(@NonNull Context context, String key, int index) {
        context.getSharedPreferences(Constants.PREFER_NAME, Context.MODE_PRIVATE).edit()
                .putInt(key, index).apply();
    }

    /**
     * @return the isLoaded;
     */
    public static int getCurrentIndex(@NonNull Context context, String key) {
        return context.getSharedPreferences(Constants.PREFER_NAME, Context.MODE_PRIVATE)
                .getInt(key, 0);
    }

}
