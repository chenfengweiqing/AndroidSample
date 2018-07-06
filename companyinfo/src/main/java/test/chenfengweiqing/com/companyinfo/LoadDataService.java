package test.chenfengweiqing.com.companyinfo;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import test.chenfengweiqing.com.companyinfo.db.Constants;
import test.chenfengweiqing.com.companyinfo.utils.CompanyInfoUtils;

/**
 * Created by lcz on 18-7-6.
 */

public class LoadDataService extends IntentService {
    private Context mContext;
    private final String CHANNEL_ID = "Load_company_info";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public LoadDataService() {
        super("WeatherService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        mContext = getApplicationContext();
        insertDB("乌鲁木齐.xls", Constants.CompanyInfoUri.WU_LU_MU_QI);
        insertDB("乐山.xls", Constants.CompanyInfoUri.LE_SHAN);
        insertDB("克拉玛依.xls", Constants.CompanyInfoUri.KE_LA_MA_YI);
        insertDB("兰州.xls", Constants.CompanyInfoUri.LAN_ZHOU);
        insertDB("北京.xls", Constants.CompanyInfoUri.BEI_JING);
        insertDB("南充.xls", Constants.CompanyInfoUri.NAN_CONG);
        insertDB("吐鲁番.xls", Constants.CompanyInfoUri.TU_LU_FAN);
        insertDB("哈密.xls", Constants.CompanyInfoUri.HA_MI);
        insertDB("喀什.xls", Constants.CompanyInfoUri.KA_SHI);
        insertDB("宜宾.xls", Constants.CompanyInfoUri.YI_BIN);
        insertDB("广元.xls", Constants.CompanyInfoUri.GUANG_YUAN);
        insertDB("德阳.xls", Constants.CompanyInfoUri.DE_YANG);
        insertDB("昌吉.xls", Constants.CompanyInfoUri.CHANG_JI);
        insertDB("泸州.xls", Constants.CompanyInfoUri.LU_ZHOU);
        insertDB("石河子.xls", Constants.CompanyInfoUri.SHI_HE_ZI);
        insertDB("绵阳.xls", Constants.CompanyInfoUri.MIAN_YANG);
        insertDB("西安.xls", Constants.CompanyInfoUri.XI_AN);
        CompanyInfoUtils.putLoadInfo(mContext,true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        int NOTIFICATION_ID = (int) (System.currentTimeMillis() % 10000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_LOW);
            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle(null)
                    .setContentText(null).build();
            startForeground(NOTIFICATION_ID, notification);
        }
    }


    private void insertDB(String excel, Uri table) {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        Workbook book = null;
        try {
            inputStream = mContext.getAssets().open(excel);
            File tempFile = new File(mContext.getCacheDir(), "text.xls");
            outputStream = new FileOutputStream(tempFile);
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
            //用读取到的表格文件来实例化工作簿对象（符合常理，我们所希望操作的就是Excel工作簿文件）
            book = Workbook.getWorkbook(tempFile);
            Sheet[] sheets = book.getSheets();
            for (int m = 0; m < sheets.length; m++) {
                Sheet sheet = book.getSheet(m);
                int Rows = sheet.getRows();
                for (int i = 0; i < Rows; i++) {
                    // 注意：这里是按行读取的！！！
                    String name = sheet.getCell(0, i).getContents();
                    String legalPerson = sheet.getCell(1, i).getContents();
                    String phone = sheet.getCell(2, i).getContents();
                    String info = sheet.getCell(3, i).getContents();
                    boolean isAdd = false;
                    if (TextUtils.isEmpty(phone)) {
                        isAdd = false;
                    } else if (phone.length() < 11) {
                        isAdd = false;
                    } else {
                        String[] arr = phone.split("\\s+");
                        for (String ss : arr) {
                            if (ss.length() == 11) {
                                isAdd = true;
                                phone = ss;
                                break;
                            }
                        }
                    }
                    if (isAdd) {
                        ContentValues values = new ContentValues();
                        values.put(Constants.Columns.NAME, name);
                        values.put(Constants.Columns.LEGAL_PERSON, legalPerson);
                        values.put(Constants.Columns.PHONE, phone);
                        String company_type;
                        if (TextUtils.isEmpty(info) || info.length() < 20) {
                            company_type = info;
                        } else {
                            company_type = info.substring(0, 20);
                        }
                        values.put(Constants.Columns.CONPANY_TAPE, company_type);
                        values.put(Constants.Columns.IS_CALLED, 0);
                        values.put(Constants.Columns.IS_HOPE, 0);
                        CompanyInfoUtils.insertCompanyInfo(mContext, table, values);
                    } else {
                        Log.d("liao ", " content  name " + name + " legalPerson " + legalPerson + " phone " + phone);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }

    private void createExcel(String excel) {
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        Workbook book = null;
        try {
            inputStream = mContext.getAssets().open(excel);
            File tempFile = new File(mContext.getCacheDir(), "text.xls");
            outputStream = new FileOutputStream(tempFile);
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
            book = Workbook.getWorkbook(tempFile);
            Sheet[] sheets = book.getSheets();
            File dirFile = new File(mContext.getCacheDir(), excel);
            WritableWorkbook writeBook = Workbook.createWorkbook(dirFile);
            // 生成名为“sheet1”的工作表，参数0表示这是第一页
            WritableSheet writeSheet = writeBook.createSheet("sheet1", 0);
            int row = 0;
            for (int m = 0; m < sheets.length; m++) {
                Sheet sheet = book.getSheet(m);
                int Rows = sheet.getRows();
                for (int i = 0; i < Rows; i++) {
                    String name = sheet.getCell(0, i).getContents();
                    String legalPerson = sheet.getCell(1, i).getContents();
                    String phone = sheet.getCell(2, i).getContents();
                    String info = sheet.getCell(3, i).getContents();
                    boolean isAdd = false;
                    if (TextUtils.isEmpty(phone)) {
                        isAdd = false;
                    } else if (phone.length() < 11) {
                        isAdd = false;
                    } else {
                        String[] arr = phone.split("\\s+");
                        for (String ss : arr) {
                            if (ss.length() == 11) {
                                isAdd = true;
                                phone = ss;
                                break;
                            }
                        }
                    }
                    if (isAdd) {
                        Label newName = new Label(0, row, name);
                        writeSheet.addCell(newName);
                        Label newLegalPerson = new Label(1, row, legalPerson);
                        writeSheet.addCell(newLegalPerson);
                        Label newPhone = new Label(2, row, phone);
                        writeSheet.addCell(newPhone);
                        Label newInfo = new Label(3, row, info);
                        writeSheet.addCell(newInfo);
                        row++;
                    } else {
                        Log.d("liao ", " content  name " + name + " legalPerson " + legalPerson + " phone " + phone);
                    }
                }
            }
            writeBook.write();
            writeBook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}
