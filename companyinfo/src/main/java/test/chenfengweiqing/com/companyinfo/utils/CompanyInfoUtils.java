package test.chenfengweiqing.com.companyinfo.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import test.chenfengweiqing.com.companyinfo.db.Constants;

/**
 * Created by lcz on 17-8-28.
 */

public class CompanyInfoUtils {
    private static final String TAG = "CompanyInfoUtils";

    public static Cursor queryCompany(@NonNull Context context, @NonNull Uri table, @Nullable String[] projection,
                                      @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(table, projection, selection, selectionArgs, sortOrder);
        } catch (Exception e) {
            Log.d(TAG, "queryNaviHistory error   " + e.toString());
        }
        return cursor;
    }

    public static void deleteCompany(@NonNull Context context, @NonNull Uri table, String where, String[] selectionArgs) {
        Log.d(TAG, "deleteCompany table " + table);
        context.getContentResolver().delete(table, where, selectionArgs);
    }

    public static void updateCompanyInfo(@NonNull Context context, @NonNull Uri table, ContentValues values, String where, String[] selectionArgs) {
        Log.d(TAG, "updateCompanyInfo table " + table);
        context.getContentResolver().update(table, values, where, selectionArgs);
    }

    public static void insertCompanyInfo(@NonNull Context context, @NonNull Uri table, ContentValues values) {
        boolean isFavorite = false;
        if (!TextUtils.isEmpty(values.getAsString(Constants.Columns.NAME))) {
            context.getContentResolver().insert(table, values);
        }
    }
}
