package test.chenfengweiqing.com.companyinfo.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static test.chenfengweiqing.com.companyinfo.db.Constants.IS_DEBUG;

/**
 * Created by lcz on 17-8-28.
 */

public class CompanyInfoProvider extends ContentProvider {
    private final static String TAG = "NaviInfoProvider";
    private SQLiteOpenHelper mOpenHelper;
    private static final String AUTHORITY = "test.chenfengweiqing.companyinfo";
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, Constants.TABLE.WU_LU_MU_QI, Constants.CompanyType.WU_LU_MU_QI);
        sUriMatcher.addURI(AUTHORITY, Constants.TABLE.LE_SHAN, Constants.CompanyType.LE_SHAN);
        sUriMatcher.addURI(AUTHORITY, Constants.TABLE.KE_LA_MA_YI, Constants.CompanyType.KE_LA_MA_YI);
        sUriMatcher.addURI(AUTHORITY, Constants.TABLE.LAN_ZHOU, Constants.CompanyType.LAN_ZHOU);
        sUriMatcher.addURI(AUTHORITY, Constants.TABLE.BEI_JING, Constants.CompanyType.BEI_JING);
        sUriMatcher.addURI(AUTHORITY, Constants.TABLE.NAN_CONG, Constants.CompanyType.NAN_CONG);
        sUriMatcher.addURI(AUTHORITY, Constants.TABLE.TU_LU_FAN, Constants.CompanyType.TU_LU_FAN);
        sUriMatcher.addURI(AUTHORITY, Constants.TABLE.HA_MI, Constants.CompanyType.HA_MI);
        sUriMatcher.addURI(AUTHORITY, Constants.TABLE.KA_SHI, Constants.CompanyType.KA_SHI);
        sUriMatcher.addURI(AUTHORITY, Constants.TABLE.YI_BIN, Constants.CompanyType.YI_BIN);
        sUriMatcher.addURI(AUTHORITY, Constants.TABLE.GUANG_YUAN, Constants.CompanyType.GUANG_YUAN);
        sUriMatcher.addURI(AUTHORITY, Constants.TABLE.DE_YANG, Constants.CompanyType.DE_YANG);
        sUriMatcher.addURI(AUTHORITY, Constants.TABLE.CHANG_JI, Constants.CompanyType.CHANG_JI);
        sUriMatcher.addURI(AUTHORITY, Constants.TABLE.LU_ZHOU, Constants.CompanyType.LU_ZHOU);
        sUriMatcher.addURI(AUTHORITY, Constants.TABLE.SHI_HE_ZI, Constants.CompanyType.SHI_HE_ZI);
        sUriMatcher.addURI(AUTHORITY, Constants.TABLE.MIAN_YANG, Constants.CompanyType.MIAN_YANG);
        sUriMatcher.addURI(AUTHORITY, Constants.TABLE.XI_AN, Constants.CompanyType.XI_AN);
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = CompanyInfoSQLiteOpenHelper.getInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull android.net.Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor cursor = null;
        if(IS_DEBUG){
            Log.d(TAG, "query match " + match);
        }
        switch (match) {
            case Constants.CompanyType.WU_LU_MU_QI:
                qb.setTables(Constants.TABLE.WU_LU_MU_QI);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            case Constants.CompanyType.LE_SHAN:
                qb.setTables(Constants.TABLE.LE_SHAN);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            case Constants.CompanyType.KE_LA_MA_YI:
                qb.setTables(Constants.TABLE.KE_LA_MA_YI);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            case Constants.CompanyType.LAN_ZHOU:
                qb.setTables(Constants.TABLE.LAN_ZHOU);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            case Constants.CompanyType.BEI_JING:
                qb.setTables(Constants.TABLE.BEI_JING);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            case Constants.CompanyType.NAN_CONG:
                qb.setTables(Constants.TABLE.NAN_CONG);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            case Constants.CompanyType.TU_LU_FAN:
                qb.setTables(Constants.TABLE.TU_LU_FAN);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            case Constants.CompanyType.HA_MI:
                qb.setTables(Constants.TABLE.HA_MI);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            case Constants.CompanyType.KA_SHI:
                qb.setTables(Constants.TABLE.KA_SHI);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            case Constants.CompanyType.YI_BIN:
                qb.setTables(Constants.TABLE.YI_BIN);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            case Constants.CompanyType.GUANG_YUAN:
                qb.setTables(Constants.TABLE.GUANG_YUAN);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            case Constants.CompanyType.DE_YANG:
                qb.setTables(Constants.TABLE.DE_YANG);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            case Constants.CompanyType.CHANG_JI:
                qb.setTables(Constants.TABLE.CHANG_JI);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            case Constants.CompanyType.LU_ZHOU:
                qb.setTables(Constants.TABLE.LU_ZHOU);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            case Constants.CompanyType.SHI_HE_ZI:
                qb.setTables(Constants.TABLE.SHI_HE_ZI);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            case Constants.CompanyType.MIAN_YANG:
                qb.setTables(Constants.TABLE.MIAN_YANG);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            case Constants.CompanyType.XI_AN:
                qb.setTables(Constants.TABLE.XI_AN);
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, null);
                break;
            default:
                throw new UnsupportedOperationException("URI" + uri + "not support");
        }
        if(IS_DEBUG){
            Log.d(TAG, "query cursor " + cursor);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull android.net.Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public android.net.Uri insert(@NonNull android.net.Uri uri, @Nullable ContentValues values) {
        int match = sUriMatcher.match(uri);
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        if(IS_DEBUG){
            Log.d(TAG, "insert match " + match + "  , values " + values);
        }
        long count;
        switch (match) {
            case Constants.CompanyType.WU_LU_MU_QI:
                count = db.insert(Constants.TABLE.WU_LU_MU_QI, Constants.Columns.NAME, values);
                break;
            case Constants.CompanyType.LE_SHAN:
                count = db.insert(Constants.TABLE.LE_SHAN, Constants.Columns.NAME, values);
                break;
            case Constants.CompanyType.KE_LA_MA_YI:
                count = db.insert(Constants.TABLE.KE_LA_MA_YI, Constants.Columns.NAME, values);
                break;
            case Constants.CompanyType.LAN_ZHOU:
                count = db.insert(Constants.TABLE.LAN_ZHOU, Constants.Columns.NAME, values);
                break;
            case Constants.CompanyType.BEI_JING:
                count = db.insert(Constants.TABLE.BEI_JING, Constants.Columns.NAME, values);
                break;
            case Constants.CompanyType.NAN_CONG:
                count = db.insert(Constants.TABLE.NAN_CONG, Constants.Columns.NAME, values);
                break;
            case Constants.CompanyType.TU_LU_FAN:
                count = db.insert(Constants.TABLE.TU_LU_FAN, Constants.Columns.NAME, values);
                break;
            case Constants.CompanyType.HA_MI:
                count = db.insert(Constants.TABLE.HA_MI, Constants.Columns.NAME, values);
                break;
            case Constants.CompanyType.KA_SHI:
                count = db.insert(Constants.TABLE.KA_SHI, Constants.Columns.NAME, values);
                break;
            case Constants.CompanyType.YI_BIN:
                count = db.insert(Constants.TABLE.YI_BIN, Constants.Columns.NAME, values);
                break;
            case Constants.CompanyType.GUANG_YUAN:
                count = db.insert(Constants.TABLE.GUANG_YUAN, Constants.Columns.NAME, values);
                break;
            case Constants.CompanyType.DE_YANG:
                count = db.insert(Constants.TABLE.DE_YANG, Constants.Columns.NAME, values);
                break;
            case Constants.CompanyType.CHANG_JI:
                count = db.insert(Constants.TABLE.CHANG_JI, Constants.Columns.NAME, values);
                break;
            case Constants.CompanyType.LU_ZHOU:
                count = db.insert(Constants.TABLE.LU_ZHOU, Constants.Columns.NAME, values);
                break;
            case Constants.CompanyType.SHI_HE_ZI:
                count = db.insert(Constants.TABLE.SHI_HE_ZI, Constants.Columns.NAME, values);
                break;
            case Constants.CompanyType.MIAN_YANG:
                count = db.insert(Constants.TABLE.MIAN_YANG, Constants.Columns.NAME, values);
                break;
            case Constants.CompanyType.XI_AN:
                count = db.insert(Constants.TABLE.XI_AN, Constants.Columns.NAME, values);
                break;
            default:
                throw new UnsupportedOperationException("URI" + uri + "not support");
        }
        if(IS_DEBUG){
            Log.d(TAG, "insert uri " + uri + " count " + count);
        }
        return uri;
    }

    @Override
    public int delete(@NonNull android.net.Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        if(IS_DEBUG){
            Log.d(TAG, "delete match " + match + "   selection " + selection + " , selectionArgs  " + selectionArgs);
        }
        switch (match) {
            case Constants.CompanyType.WU_LU_MU_QI:
                count = db.delete(Constants.TABLE.WU_LU_MU_QI, selection, selectionArgs);
                break;
            case Constants.CompanyType.LE_SHAN:
                count = db.delete(Constants.TABLE.LE_SHAN, selection, selectionArgs);
                break;
            case Constants.CompanyType.KE_LA_MA_YI:
                count = db.delete(Constants.TABLE.KE_LA_MA_YI, selection, selectionArgs);
                break;
            case Constants.CompanyType.LAN_ZHOU:
                count = db.delete(Constants.TABLE.LAN_ZHOU, selection, selectionArgs);
                break;
            case Constants.CompanyType.BEI_JING:
                count = db.delete(Constants.TABLE.BEI_JING, selection, selectionArgs);
                break;
            case Constants.CompanyType.NAN_CONG:
                count = db.delete(Constants.TABLE.NAN_CONG, selection, selectionArgs);
                break;
            case Constants.CompanyType.TU_LU_FAN:
                count = db.delete(Constants.TABLE.TU_LU_FAN, selection, selectionArgs);
                break;
            case Constants.CompanyType.HA_MI:
                count = db.delete(Constants.TABLE.HA_MI, selection, selectionArgs);
                break;
            case Constants.CompanyType.KA_SHI:
                count = db.delete(Constants.TABLE.KA_SHI, selection, selectionArgs);
                break;
            case Constants.CompanyType.YI_BIN:
                count = db.delete(Constants.TABLE.YI_BIN, selection, selectionArgs);
                break;
            case Constants.CompanyType.GUANG_YUAN:
                count = db.delete(Constants.TABLE.GUANG_YUAN, selection, selectionArgs);
                break;
            case Constants.CompanyType.DE_YANG:
                count = db.delete(Constants.TABLE.DE_YANG, selection, selectionArgs);
                break;
            case Constants.CompanyType.CHANG_JI:
                count = db.delete(Constants.TABLE.CHANG_JI, selection, selectionArgs);
                break;
            case Constants.CompanyType.LU_ZHOU:
                count = db.delete(Constants.TABLE.LU_ZHOU, selection, selectionArgs);
                break;
            case Constants.CompanyType.SHI_HE_ZI:
                count = db.delete(Constants.TABLE.SHI_HE_ZI, selection, selectionArgs);
                break;
            case Constants.CompanyType.MIAN_YANG:
                count = db.delete(Constants.TABLE.MIAN_YANG, selection, selectionArgs);
                break;
            case Constants.CompanyType.XI_AN:
                count = db.delete(Constants.TABLE.XI_AN, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("URI" + uri + "not support");
        }
        if(IS_DEBUG){
            Log.d(TAG, "delete count " + count + "  , selection    " + selection);
        }
        return count;
    }

    @Override
    public int update(@NonNull android.net.Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int count;
        switch (match) {
            case Constants.CompanyType.WU_LU_MU_QI:
                count = db.update(Constants.TABLE.WU_LU_MU_QI, values, selection, selectionArgs);
                break;
            case Constants.CompanyType.LE_SHAN:
                count = db.update(Constants.TABLE.LE_SHAN, values, selection, selectionArgs);
                break;
            case Constants.CompanyType.KE_LA_MA_YI:
                count = db.update(Constants.TABLE.KE_LA_MA_YI, values, selection, selectionArgs);
                break;
            case Constants.CompanyType.LAN_ZHOU:
                count = db.update(Constants.TABLE.LAN_ZHOU, values, selection, selectionArgs);
                break;
            case Constants.CompanyType.BEI_JING:
                count = db.update(Constants.TABLE.BEI_JING, values, selection, selectionArgs);
                break;
            case Constants.CompanyType.NAN_CONG:
                count = db.update(Constants.TABLE.NAN_CONG, values, selection, selectionArgs);
                break;
            case Constants.CompanyType.TU_LU_FAN:
                count = db.update(Constants.TABLE.TU_LU_FAN, values, selection, selectionArgs);
                break;
            case Constants.CompanyType.HA_MI:
                count = db.update(Constants.TABLE.HA_MI, values, selection, selectionArgs);
                break;
            case Constants.CompanyType.KA_SHI:
                count = db.update(Constants.TABLE.KA_SHI, values, selection, selectionArgs);
                break;
            case Constants.CompanyType.YI_BIN:
                count = db.update(Constants.TABLE.YI_BIN, values, selection, selectionArgs);
                break;
            case Constants.CompanyType.GUANG_YUAN:
                count = db.update(Constants.TABLE.GUANG_YUAN, values, selection, selectionArgs);
                break;
            case Constants.CompanyType.DE_YANG:
                count = db.update(Constants.TABLE.DE_YANG, values, selection, selectionArgs);
                break;
            case Constants.CompanyType.CHANG_JI:
                count = db.update(Constants.TABLE.CHANG_JI, values, selection, selectionArgs);
                break;
            case Constants.CompanyType.LU_ZHOU:
                count = db.update(Constants.TABLE.LU_ZHOU, values, selection, selectionArgs);
                break;
            case Constants.CompanyType.SHI_HE_ZI:
                count = db.update(Constants.TABLE.SHI_HE_ZI, values, selection, selectionArgs);
                break;
            case Constants.CompanyType.MIAN_YANG:
                count = db.update(Constants.TABLE.MIAN_YANG, values, selection, selectionArgs);
                break;
            case Constants.CompanyType.XI_AN:
                count = db.update(Constants.TABLE.XI_AN, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("URI" + uri + "not support");
        }
        return count;
    }

}
