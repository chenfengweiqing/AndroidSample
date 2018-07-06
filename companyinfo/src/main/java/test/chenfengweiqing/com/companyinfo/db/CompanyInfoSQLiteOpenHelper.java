package test.chenfengweiqing.com.companyinfo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lcz on 17-8-28.
 */

public class CompanyInfoSQLiteOpenHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "company_info_db";
    private final static String CREATE_WU_LU_MU_QI_TABLE = " CREATE TABLE IF NOT EXISTS " + Constants.TABLE.WU_LU_MU_QI + "(" +
            Constants.Columns.NAME + "  TEXT PRIMARY KEY ,"
            + Constants.Columns.LEGAL_PERSON + "  TEXT ,"
            + Constants.Columns.PHONE + "  TEXT ,"
            + Constants.Columns.CONPANY_TAPE + "  TEXT ,"
            + Constants.Columns.IS_CALLED + "  INTEGER ,"
            + Constants.Columns.IS_HOPE + " INTEGER )";
    private final static String CREATE_LE_SHAN_TABLE = " CREATE TABLE IF NOT EXISTS " + Constants.TABLE.LE_SHAN + "(" +
            Constants.Columns.NAME + "  TEXT PRIMARY KEY ,"
            + Constants.Columns.LEGAL_PERSON + "  TEXT ,"
            + Constants.Columns.PHONE + "  TEXT ,"
            + Constants.Columns.CONPANY_TAPE + "  TEXT ,"
            + Constants.Columns.IS_CALLED + "  INTEGER ,"
            + Constants.Columns.IS_HOPE + " INTEGER )";
    private final static String CREATE_KE_LA_MA_YI_TABLE = " CREATE TABLE IF NOT EXISTS " + Constants.TABLE.KE_LA_MA_YI + "(" +
            Constants.Columns.NAME + "  TEXT PRIMARY KEY ,"
            + Constants.Columns.LEGAL_PERSON + "  TEXT ,"
            + Constants.Columns.PHONE + "  TEXT ,"
            + Constants.Columns.CONPANY_TAPE + "  TEXT ,"
            + Constants.Columns.IS_CALLED + "  INTEGER ,"
            + Constants.Columns.IS_HOPE + " INTEGER )";
    private final static String CREATE_LAN_ZHOU_TABLE = " CREATE TABLE IF NOT EXISTS " + Constants.TABLE.LAN_ZHOU + "(" +
            Constants.Columns.NAME + "  TEXT PRIMARY KEY ,"
            + Constants.Columns.LEGAL_PERSON + "  TEXT ,"
            + Constants.Columns.PHONE + "  TEXT ,"
            + Constants.Columns.CONPANY_TAPE + "  TEXT ,"
            + Constants.Columns.IS_CALLED + "  INTEGER ,"
            + Constants.Columns.IS_HOPE + " INTEGER )";
    private final static String CREATE_BEI_JING_TABLE = " CREATE TABLE IF NOT EXISTS " + Constants.TABLE.BEI_JING + "(" +
            Constants.Columns.NAME + "  TEXT PRIMARY KEY ,"
            + Constants.Columns.LEGAL_PERSON + "  TEXT ,"
            + Constants.Columns.PHONE + "  TEXT ,"
            + Constants.Columns.CONPANY_TAPE + "  TEXT ,"
            + Constants.Columns.IS_CALLED + "  INTEGER ,"
            + Constants.Columns.IS_HOPE + " INTEGER )";
    private final static String CREATE_NAN_CONG_TABLE = " CREATE TABLE IF NOT EXISTS " + Constants.TABLE.NAN_CONG + "(" +
            Constants.Columns.NAME + "  TEXT PRIMARY KEY ,"
            + Constants.Columns.LEGAL_PERSON + "  TEXT ,"
            + Constants.Columns.PHONE + "  TEXT ,"
            + Constants.Columns.CONPANY_TAPE + "  TEXT ,"
            + Constants.Columns.IS_CALLED + "  INTEGER ,"
            + Constants.Columns.IS_HOPE + " INTEGER )";
    private final static String CREATE_TU_LU_FAN_TABLE = " CREATE TABLE IF NOT EXISTS " + Constants.TABLE.TU_LU_FAN + "(" +
            Constants.Columns.NAME + "  TEXT PRIMARY KEY ,"
            + Constants.Columns.LEGAL_PERSON + "  TEXT ,"
            + Constants.Columns.PHONE + "  TEXT ,"
            + Constants.Columns.CONPANY_TAPE + "  TEXT ,"
            + Constants.Columns.IS_CALLED + "  INTEGER ,"
            + Constants.Columns.IS_HOPE + " INTEGER )";
    private final static String CREATE_HA_MI_TABLE = " CREATE TABLE IF NOT EXISTS " + Constants.TABLE.HA_MI + "(" +
            Constants.Columns.NAME + "  TEXT PRIMARY KEY ,"
            + Constants.Columns.LEGAL_PERSON + "  TEXT ,"
            + Constants.Columns.PHONE + "  TEXT ,"
            + Constants.Columns.CONPANY_TAPE + "  TEXT ,"
            + Constants.Columns.IS_CALLED + "  INTEGER ,"
            + Constants.Columns.IS_HOPE + " INTEGER )";
    private final static String CREATE_KA_SHI_TABLE = " CREATE TABLE IF NOT EXISTS " + Constants.TABLE.KA_SHI + "(" +
            Constants.Columns.NAME + "  TEXT PRIMARY KEY ,"
            + Constants.Columns.LEGAL_PERSON + "  TEXT ,"
            + Constants.Columns.PHONE + "  TEXT ,"
            + Constants.Columns.CONPANY_TAPE + "  TEXT ,"
            + Constants.Columns.IS_CALLED + "  INTEGER ,"
            + Constants.Columns.IS_HOPE + " INTEGER )";
    private final static String CREATE_YI_BIN_TABLE = " CREATE TABLE IF NOT EXISTS " + Constants.TABLE.YI_BIN + "(" +
            Constants.Columns.NAME + "  TEXT PRIMARY KEY ,"
            + Constants.Columns.LEGAL_PERSON + "  TEXT ,"
            + Constants.Columns.PHONE + "  TEXT ,"
            + Constants.Columns.CONPANY_TAPE + "  TEXT ,"
            + Constants.Columns.IS_CALLED + "  INTEGER ,"
            + Constants.Columns.IS_HOPE + " INTEGER )";

    private final static String CREATE_GUANG_YUAN_TABLE = " CREATE TABLE IF NOT EXISTS " + Constants.TABLE.GUANG_YUAN + "(" +
            Constants.Columns.NAME + "  TEXT PRIMARY KEY ,"
            + Constants.Columns.LEGAL_PERSON + "  TEXT ,"
            + Constants.Columns.PHONE + "  TEXT ,"
            + Constants.Columns.CONPANY_TAPE + "  TEXT ,"
            + Constants.Columns.IS_CALLED + "  INTEGER ,"
            + Constants.Columns.IS_HOPE + " INTEGER )";
    private final static String CREATE_DE_YANG_TABLE = " CREATE TABLE IF NOT EXISTS " + Constants.TABLE.DE_YANG + "(" +
            Constants.Columns.NAME + "  TEXT PRIMARY KEY ,"
            + Constants.Columns.LEGAL_PERSON + "  TEXT ,"
            + Constants.Columns.PHONE + "  TEXT ,"
            + Constants.Columns.CONPANY_TAPE + "  TEXT ,"
            + Constants.Columns.IS_CALLED + "  INTEGER ,"
            + Constants.Columns.IS_HOPE + " INTEGER )";
    private final static String CREATE_CHANG_JI_TABLE = " CREATE TABLE IF NOT EXISTS " + Constants.TABLE.CHANG_JI + "(" +
            Constants.Columns.NAME + "  TEXT PRIMARY KEY ,"
            + Constants.Columns.LEGAL_PERSON + "  TEXT ,"
            + Constants.Columns.PHONE + "  TEXT ,"
            + Constants.Columns.CONPANY_TAPE + "  TEXT ,"
            + Constants.Columns.IS_CALLED + "  INTEGER ,"
            + Constants.Columns.IS_HOPE + " INTEGER )";
    private final static String CREATE_LU_ZHOU_TABLE = " CREATE TABLE IF NOT EXISTS " + Constants.TABLE.LU_ZHOU + "(" +
            Constants.Columns.NAME + "  TEXT PRIMARY KEY ,"
            + Constants.Columns.LEGAL_PERSON + "  TEXT ,"
            + Constants.Columns.PHONE + "  TEXT ,"
            + Constants.Columns.CONPANY_TAPE + "  TEXT ,"
            + Constants.Columns.IS_CALLED + "  INTEGER ,"
            + Constants.Columns.IS_HOPE + " INTEGER )";
    private final static String CREATE_SHI_HE_ZI_TABLE = " CREATE TABLE IF NOT EXISTS " + Constants.TABLE.SHI_HE_ZI + "(" +
            Constants.Columns.NAME + "  TEXT PRIMARY KEY ,"
            + Constants.Columns.LEGAL_PERSON + "  TEXT ,"
            + Constants.Columns.PHONE + "  TEXT ,"
            + Constants.Columns.CONPANY_TAPE + "  TEXT ,"
            + Constants.Columns.IS_CALLED + "  INTEGER ,"
            + Constants.Columns.IS_HOPE + " INTEGER )";
    private final static String CREATE_MIAN_YANG_TABLE = " CREATE TABLE IF NOT EXISTS " + Constants.TABLE.MIAN_YANG + "(" +
            Constants.Columns.NAME + "  TEXT PRIMARY KEY ,"
            + Constants.Columns.LEGAL_PERSON + "  TEXT ,"
            + Constants.Columns.PHONE + "  TEXT ,"
            + Constants.Columns.CONPANY_TAPE + "  TEXT ,"
            + Constants.Columns.IS_CALLED + "  INTEGER ,"
            + Constants.Columns.IS_HOPE + " INTEGER )";
    private final static String CREATE_XI_AN_TABLE = " CREATE TABLE IF NOT EXISTS " + Constants.TABLE.XI_AN + "(" +
            Constants.Columns.NAME + "  TEXT PRIMARY KEY ,"
            + Constants.Columns.LEGAL_PERSON + "  TEXT ,"
            + Constants.Columns.PHONE + "  TEXT ,"
            + Constants.Columns.CONPANY_TAPE + "  TEXT ,"
            + Constants.Columns.IS_CALLED + "  INTEGER ,"
            + Constants.Columns.IS_HOPE + " INTEGER )";
    private static CompanyInfoSQLiteOpenHelper sInstance = null;
    private static final int DATABASE_VERSION = 1;

    private CompanyInfoSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    public static synchronized CompanyInfoSQLiteOpenHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CompanyInfoSQLiteOpenHelper(context);
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
    }

    private void createTable(SQLiteDatabase db) {
        db.execSQL(CREATE_WU_LU_MU_QI_TABLE);
        db.execSQL(CREATE_LE_SHAN_TABLE);
        db.execSQL(CREATE_KE_LA_MA_YI_TABLE);
        db.execSQL(CREATE_LAN_ZHOU_TABLE);
        db.execSQL(CREATE_BEI_JING_TABLE);
        db.execSQL(CREATE_NAN_CONG_TABLE);
        db.execSQL(CREATE_TU_LU_FAN_TABLE);
        db.execSQL(CREATE_HA_MI_TABLE);
        db.execSQL(CREATE_KA_SHI_TABLE);
        db.execSQL(CREATE_YI_BIN_TABLE);
        db.execSQL(CREATE_GUANG_YUAN_TABLE);
        db.execSQL(CREATE_DE_YANG_TABLE);
        db.execSQL(CREATE_CHANG_JI_TABLE);
        db.execSQL(CREATE_LU_ZHOU_TABLE);
        db.execSQL(CREATE_SHI_HE_ZI_TABLE);
        db.execSQL(CREATE_MIAN_YANG_TABLE);
        db.execSQL(CREATE_XI_AN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
