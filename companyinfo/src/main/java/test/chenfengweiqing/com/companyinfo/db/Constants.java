package test.chenfengweiqing.com.companyinfo.db;

import android.net.Uri;

/**
 * Created by lcz on 18-7-6.
 */

public class Constants {

    public static String PREFER_NAME = "LoadInfo";
    public static String COMPANY_IS_LOAD = "is_load";
    public static String IS_SHOW_TOAST = "is_show_toast";
    public static String UPDATE_SHOW_VIEW = "update_show_view";
    public static String OPEN_CITY_KEY = "open_city_key";
    public static String OPEN_CITY_NAME_KEY = "open_city_name_key";
    public static String QUERY_TYPE_KEY = "query_type_key";
    public static int OFF_SET = 30;
    public static final boolean IS_DEBUG = false;

    public @interface QueryType {
        int ALL = 0;
        int UN_CALL = 1;
        int HOPE = 2;
    }

    public @interface LoadType {
        int PRE = 1;
        int CURRENT = 0;
        int NEXT = 2;
    }

    public @interface TABLE {
        //乌鲁木齐
        String WU_LU_MU_QI = "wu_lu_mu_qi";
        //乐山
        String LE_SHAN = "le_shan";
        //克拉玛依
        String KE_LA_MA_YI = "ke_la_ma_yi";
        //兰州
        String LAN_ZHOU = "lan_zhou";
        //北京
        String BEI_JING = "bei_jing";
        //南充
        String NAN_CONG = "nan_cong";
        //吐鲁番
        String TU_LU_FAN = "tu_lu_fan";
        //哈密
        String HA_MI = "ha_mi";
        //喀什
        String KA_SHI = "ka_shi";
        //宜宾
        String YI_BIN = "yi_bin";
        //广元
        String GUANG_YUAN = "guang_yuan";
        //德阳
        String DE_YANG = "de_yang";
        //昌吉
        String CHANG_JI = "chang_ji";
        //泸州
        String LU_ZHOU = "lu_zhou";
        //石河子
        String SHI_HE_ZI = "shi_he_zi";
        //绵阳
        String MIAN_YANG = "mian_yang";
        //西安
        String XI_AN = "xi_an";
    }

    public @interface Columns {
        String NAME = "company_name";
        String PHONE = "company_phone";
        String LEGAL_PERSON = "legal_person";
        String CONPANY_TAPE = "company_type";
        String IS_CALLED = "is_called";
        String IS_HOPE = "is_hope";
    }

    public @interface CompanyInfoUri {
        //乌鲁木齐
        Uri WU_LU_MU_QI = Uri.parse("content://test.chenfengweiqing.companyinfo/wu_lu_mu_qi");
        //乐山
        Uri LE_SHAN = Uri.parse("content://test.chenfengweiqing.companyinfo/le_shan");
        //克拉玛依
        Uri KE_LA_MA_YI = Uri.parse("content://test.chenfengweiqing.companyinfo/ke_la_ma_yi");
        //兰州
        Uri LAN_ZHOU = Uri.parse("content://test.chenfengweiqing.companyinfo/lan_zhou");
        //北京
        Uri BEI_JING = Uri.parse("content://test.chenfengweiqing.companyinfo/bei_jing");
        //南充
        Uri NAN_CONG = Uri.parse("content://test.chenfengweiqing.companyinfo/nan_cong");
        //吐鲁番
        Uri TU_LU_FAN = Uri.parse("content://test.chenfengweiqing.companyinfo/tu_lu_fan");
        //哈密
        Uri HA_MI = Uri.parse("content://test.chenfengweiqing.companyinfo/ha_mi");
        //喀什
        Uri KA_SHI = Uri.parse("content://test.chenfengweiqing.companyinfo/ka_shi");
        //宜宾
        Uri YI_BIN = Uri.parse("content://test.chenfengweiqing.companyinfo/yi_bin");
        //广元
        Uri GUANG_YUAN = Uri.parse("content://test.chenfengweiqing.companyinfo/guang_yuan");
        //德阳
        Uri DE_YANG = Uri.parse("content://test.chenfengweiqing.companyinfo/de_yang");
        //昌吉
        Uri CHANG_JI = Uri.parse("content://test.chenfengweiqing.companyinfo/chang_ji");
        //泸州
        Uri LU_ZHOU = Uri.parse("content://test.chenfengweiqing.companyinfo/lu_zhou");
        //石河子
        Uri SHI_HE_ZI = Uri.parse("content://test.chenfengweiqing.companyinfo/shi_he_zi");
        //绵阳
        Uri MIAN_YANG = Uri.parse("content://test.chenfengweiqing.companyinfo/mian_yang");
        //西安
        Uri XI_AN = Uri.parse("content://test.chenfengweiqing.companyinfo/xi_an");
    }

    public @interface CompanyType {
        //乌鲁木齐
        int WU_LU_MU_QI = 1;
        //乐山
        int LE_SHAN = 2;
        //克拉玛依
        int KE_LA_MA_YI = 3;
        //兰州
        int LAN_ZHOU = 4;
        //北京
        int BEI_JING = 5;
        //南充
        int NAN_CONG = 6;
        //吐鲁番
        int TU_LU_FAN = 7;
        //哈密
        int HA_MI = 8;
        //喀什
        int KA_SHI = 9;
        //宜宾
        int YI_BIN = 10;
        //广元
        int GUANG_YUAN = 11;
        //德阳
        int DE_YANG = 12;
        //昌吉
        int CHANG_JI = 13;
        //泸州
        int LU_ZHOU = 14;
        //石河子
        int SHI_HE_ZI = 15;
        //绵阳
        int MIAN_YANG = 16;
        //西安
        int XI_AN = 17;
    }
}
