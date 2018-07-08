package test.chenfengweiqing.com.companyinfo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import test.chenfengweiqing.com.companyinfo.adapter.CompanyAdapter;
import test.chenfengweiqing.com.companyinfo.adapter.CompanyInfo;
import test.chenfengweiqing.com.companyinfo.db.Constants;
import test.chenfengweiqing.com.companyinfo.utils.CompanyInfoUtils;

import static test.chenfengweiqing.com.companyinfo.db.Constants.IS_DEBUG;
import static test.chenfengweiqing.com.companyinfo.db.Constants.OFF_SET;

/**
 * Created by lcz on 18-7-6.
 */

public class ShowCityCompanyActivity extends AppCompatActivity implements CompanyAdapter.OnItemClickListener, View.OnClickListener {
    private RecyclerView mCompanyInfo;
    private TextView mCurrentCity;
    private TextView mPre;
    private TextView mNext;
    private TextView mPrompt;
    private List<CompanyInfo> mPreDatas = new ArrayList<>();
    private List<CompanyInfo> mCurDatas = new ArrayList<>();
    private List<CompanyInfo> mLastDatas = new ArrayList<>();
    private static final int UPDATE_DATE = 1;
    private int mType;
    private int mQueryType;
    private int mCurrentIndex;
    private String mCurrentKey;
    private CompanyAdapter mCompanyAdapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_DATE:
                    updateData();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_layout);
        mType = getIntent().getIntExtra(Constants.OPEN_CITY_KEY, 0);
        mQueryType = getIntent().getIntExtra(Constants.QUERY_TYPE_KEY, 0);
        String cityName = getIntent().getStringExtra(Constants.OPEN_CITY_NAME_KEY);
        mCurrentCity = findViewById(R.id.current_city);
        mPre = findViewById(R.id.pre);
        mNext = findViewById(R.id.next);
        mPrompt = findViewById(R.id.prompt);
        StringBuffer sb = new StringBuffer(cityName);
        sb.append("  +  ");
        switch (mQueryType) {
            case Constants.QueryType.HOPE:
                sb.append("有希望");
                break;
            case Constants.QueryType.UN_CALL:
                sb.append("未拨打");
                break;
            case Constants.QueryType.ALL:
            default:
                sb.append("全部");
                break;
        }
        mCurrentKey = sb.toString();
        mCurrentIndex = CompanyInfoUtils.getCurrentIndex(this, mCurrentKey);
        mCurrentCity.setText(sb.toString());
        mPre.setOnClickListener(this);
        mCurrentCity.setOnClickListener(this);
        mNext.setOnClickListener(this);
        mCompanyInfo = findViewById(R.id.company_info);
        mCompanyAdapter = new CompanyAdapter(this, mCurDatas, R.layout.company_info_item);
        mCompanyInfo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mCompanyInfo.setAdapter(mCompanyAdapter);
        mCompanyAdapter.setOnItemClickListener(this);
        if (IS_DEBUG) {
            Log.d("liao", "onCreate: mCurrentIndex " + mCurrentIndex);
        }
        if (mCurrentIndex < 0) {
            mCurrentIndex = 0;
            CompanyInfoUtils.putCurrentIndex(this, mCurrentKey, mCurrentIndex);
        }
        initPreload(mCurrentIndex - OFF_SET, mCurrentIndex + OFF_SET);
    }

    private void updateData() {
        if (IS_DEBUG) {
            Log.d("liao", "updateData: mCurDatas size " + mCurDatas.size());
        }
        if (mCurDatas.size() > 0) {
            mCompanyAdapter.setInfos(mCurDatas);
            mPrompt.setVisibility(View.GONE);
            mCompanyInfo.setVisibility(View.VISIBLE);
        } else {
            mPrompt.setVisibility(View.VISIBLE);
            mCompanyInfo.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initCompany(mCurrentIndex, Constants.LoadType.CURRENT);
    }

    private void initPreload(int preIndex, int lastIndex) {
        Log.d("liao", "initCompany: mCurrentIndex " + mCurrentIndex + " preIndex " + preIndex + " lastIndex " + lastIndex);
        initCompany(preIndex, Constants.LoadType.PRE);
        initCompany(lastIndex, Constants.LoadType.NEXT);
    }

    private void initCompany(final int index, final int type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = null;
                Uri uri = null;
                if (IS_DEBUG) {
                    Log.d("liao", "initCompany: index " + index + " type " + type);
                }
                switch (mType) {
                    case Constants.CompanyType.WU_LU_MU_QI:
                        cursor = CompanyInfoUtils.queryCompany(getApplicationContext(), Constants.CompanyInfoUri.WU_LU_MU_QI,
                                null, null, null, null, mQueryType, index);
                        uri = Constants.CompanyInfoUri.WU_LU_MU_QI;
                        break;
                    case Constants.CompanyType.LE_SHAN:
                        cursor = CompanyInfoUtils.queryCompany(getApplicationContext(), Constants.CompanyInfoUri.LE_SHAN,
                                null, null, null, null, mQueryType, index);
                        uri = Constants.CompanyInfoUri.LE_SHAN;
                        break;
                    case Constants.CompanyType.KE_LA_MA_YI:
                        cursor = CompanyInfoUtils.queryCompany(getApplicationContext(), Constants.CompanyInfoUri.KE_LA_MA_YI,
                                null, null, null, null, mQueryType, index);
                        uri = Constants.CompanyInfoUri.KE_LA_MA_YI;
                        break;
                    case Constants.CompanyType.LAN_ZHOU:
                        cursor = CompanyInfoUtils.queryCompany(getApplicationContext(), Constants.CompanyInfoUri.LAN_ZHOU,
                                null, null, null, null, mQueryType, index);
                        uri = Constants.CompanyInfoUri.LAN_ZHOU;
                        break;
                    case Constants.CompanyType.BEI_JING:
                        cursor = CompanyInfoUtils.queryCompany(getApplicationContext(), Constants.CompanyInfoUri.BEI_JING,
                                null, null, null, null, mQueryType, index);
                        uri = Constants.CompanyInfoUri.BEI_JING;
                        break;
                    case Constants.CompanyType.NAN_CONG:
                        cursor = CompanyInfoUtils.queryCompany(getApplicationContext(), Constants.CompanyInfoUri.NAN_CONG,
                                null, null, null, null, mQueryType, index);
                        uri = Constants.CompanyInfoUri.NAN_CONG;
                        break;
                    case Constants.CompanyType.TU_LU_FAN:
                        cursor = CompanyInfoUtils.queryCompany(getApplicationContext(), Constants.CompanyInfoUri.TU_LU_FAN,
                                null, null, null, null, mQueryType, index);
                        uri = Constants.CompanyInfoUri.TU_LU_FAN;
                        break;
                    case Constants.CompanyType.HA_MI:
                        cursor = CompanyInfoUtils.queryCompany(getApplicationContext(), Constants.CompanyInfoUri.HA_MI,
                                null, null, null, null, mQueryType, index);
                        uri = Constants.CompanyInfoUri.HA_MI;
                        break;
                    case Constants.CompanyType.KA_SHI:
                        cursor = CompanyInfoUtils.queryCompany(getApplicationContext(), Constants.CompanyInfoUri.KA_SHI,
                                null, null, null, null, mQueryType, index);
                        uri = Constants.CompanyInfoUri.KA_SHI;
                        break;
                    case Constants.CompanyType.YI_BIN:
                        cursor = CompanyInfoUtils.queryCompany(getApplicationContext(), Constants.CompanyInfoUri.YI_BIN,
                                null, null, null, null, mQueryType, index);
                        uri = Constants.CompanyInfoUri.YI_BIN;
                        break;
                    case Constants.CompanyType.GUANG_YUAN:
                        cursor = CompanyInfoUtils.queryCompany(getApplicationContext(), Constants.CompanyInfoUri.GUANG_YUAN,
                                null, null, null, null, mQueryType, index);
                        uri = Constants.CompanyInfoUri.GUANG_YUAN;
                        break;
                    case Constants.CompanyType.DE_YANG:
                        cursor = CompanyInfoUtils.queryCompany(getApplicationContext(), Constants.CompanyInfoUri.DE_YANG,
                                null, null, null, null, mQueryType, index);
                        uri = Constants.CompanyInfoUri.DE_YANG;
                        break;
                    case Constants.CompanyType.CHANG_JI:
                        cursor = CompanyInfoUtils.queryCompany(getApplicationContext(), Constants.CompanyInfoUri.CHANG_JI,
                                null, null, null, null, mQueryType, index);
                        uri = Constants.CompanyInfoUri.CHANG_JI;
                        break;
                    case Constants.CompanyType.LU_ZHOU:
                        cursor = CompanyInfoUtils.queryCompany(getApplicationContext(), Constants.CompanyInfoUri.LU_ZHOU,
                                null, null, null, null, mQueryType, index);
                        uri = Constants.CompanyInfoUri.LU_ZHOU;
                        break;
                    case Constants.CompanyType.SHI_HE_ZI:
                        cursor = CompanyInfoUtils.queryCompany(getApplicationContext(), Constants.CompanyInfoUri.SHI_HE_ZI,
                                null, null, null, null, mQueryType, index);
                        uri = Constants.CompanyInfoUri.SHI_HE_ZI;
                        break;
                    case Constants.CompanyType.MIAN_YANG:
                        cursor = CompanyInfoUtils.queryCompany(getApplicationContext(), Constants.CompanyInfoUri.MIAN_YANG,
                                null, null, null, null, mQueryType, index);
                        uri = Constants.CompanyInfoUri.MIAN_YANG;
                        break;
                    case Constants.CompanyType.XI_AN:
                        cursor = CompanyInfoUtils.queryCompany(getApplicationContext(), Constants.CompanyInfoUri.XI_AN,
                                null, null, null, null, mQueryType, index);
                        uri = Constants.CompanyInfoUri.XI_AN;
                        break;
                    default:
                }

                if (cursor != null && uri != null) {
                    int cursorSize = cursor.getCount();
                    if (IS_DEBUG) {
                        Log.d("liao", "initCompany: cursor size " + cursorSize);
                    }
                    switch (type) {
                        case Constants.LoadType.CURRENT:
                            mCurDatas.clear();
                            if (index < 0) {
                                mCurrentIndex = 0;
                                CompanyInfoUtils.putCurrentIndex(getApplicationContext(), mCurrentKey, mCurrentIndex);
                                if (cursorSize > Constants.OFF_SET) {
                                    for (int i = 0; i < Constants.OFF_SET; i++) {
                                        cursor.moveToNext();
                                        String name = cursor.getString(cursor.getColumnIndex(Constants.Columns.NAME));
                                        String legalPerson = cursor.getString(cursor.getColumnIndex(Constants.Columns.LEGAL_PERSON));
                                        String info = cursor.getString(cursor.getColumnIndex(Constants.Columns.CONPANY_TAPE));
                                        String phone = cursor.getString(cursor.getColumnIndex(Constants.Columns.PHONE));
                                        boolean isCalled = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_CALLED)) == 1;
                                        boolean isHope = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_HOPE)) == 1;
                                        CompanyInfo addressEntity = new CompanyInfo(name, legalPerson, info, phone, isCalled, isHope, uri);
                                        mCurDatas.add(addressEntity);
                                    }
                                } else {
                                    while (cursor.moveToNext()) {
                                        String name = cursor.getString(cursor.getColumnIndex(Constants.Columns.NAME));
                                        String legalPerson = cursor.getString(cursor.getColumnIndex(Constants.Columns.LEGAL_PERSON));
                                        String info = cursor.getString(cursor.getColumnIndex(Constants.Columns.CONPANY_TAPE));
                                        String phone = cursor.getString(cursor.getColumnIndex(Constants.Columns.PHONE));
                                        boolean isCalled = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_CALLED)) == 1;
                                        boolean isHope = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_HOPE)) == 1;
                                        CompanyInfo addressEntity = new CompanyInfo(name, legalPerson, info, phone, isCalled, isHope, uri);
                                        mCurDatas.add(addressEntity);
                                    }
                                }
                            } else {
                                if (cursorSize < index) {
                                    if (cursor.getCount() > OFF_SET) {
                                        mCurrentIndex = cursor.getCount() - OFF_SET;
                                        CompanyInfoUtils.putCurrentIndex(getApplicationContext(), mCurrentKey, mCurrentIndex);
                                    }
                                    cursor.move(mCurrentIndex);
                                    while (cursor.moveToNext()) {
                                        String name = cursor.getString(cursor.getColumnIndex(Constants.Columns.NAME));
                                        String legalPerson = cursor.getString(cursor.getColumnIndex(Constants.Columns.LEGAL_PERSON));
                                        String info = cursor.getString(cursor.getColumnIndex(Constants.Columns.CONPANY_TAPE));
                                        String phone = cursor.getString(cursor.getColumnIndex(Constants.Columns.PHONE));
                                        boolean isCalled = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_CALLED)) == 1;
                                        boolean isHope = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_HOPE)) == 1;
                                        CompanyInfo addressEntity = new CompanyInfo(name, legalPerson, info, phone, isCalled, isHope, uri);
                                        mCurDatas.add(addressEntity);
                                    }
                                } else {
                                    cursor.move(index);
                                    if (cursorSize > Constants.OFF_SET + index) {
                                        for (int i = 0; i < Constants.OFF_SET; i++) {
                                            cursor.moveToNext();
                                            String name = cursor.getString(cursor.getColumnIndex(Constants.Columns.NAME));
                                            String legalPerson = cursor.getString(cursor.getColumnIndex(Constants.Columns.LEGAL_PERSON));
                                            String info = cursor.getString(cursor.getColumnIndex(Constants.Columns.CONPANY_TAPE));
                                            String phone = cursor.getString(cursor.getColumnIndex(Constants.Columns.PHONE));
                                            boolean isCalled = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_CALLED)) == 1;
                                            boolean isHope = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_HOPE)) == 1;
                                            CompanyInfo addressEntity = new CompanyInfo(name, legalPerson, info, phone, isCalled, isHope, uri);
                                            mCurDatas.add(addressEntity);
                                        }
                                    } else {
                                        while (cursor.moveToNext()) {
                                            String name = cursor.getString(cursor.getColumnIndex(Constants.Columns.NAME));
                                            String legalPerson = cursor.getString(cursor.getColumnIndex(Constants.Columns.LEGAL_PERSON));
                                            String info = cursor.getString(cursor.getColumnIndex(Constants.Columns.CONPANY_TAPE));
                                            String phone = cursor.getString(cursor.getColumnIndex(Constants.Columns.PHONE));
                                            boolean isCalled = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_CALLED)) == 1;
                                            boolean isHope = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_HOPE)) == 1;
                                            CompanyInfo addressEntity = new CompanyInfo(name, legalPerson, info, phone, isCalled, isHope, uri);
                                            mCurDatas.add(addressEntity);
                                        }
                                    }
                                }
                            }
                            cursor.close();
                            mHandler.sendEmptyMessage(UPDATE_DATE);
                            break;
                        case Constants.LoadType.PRE:
                            mPreDatas.clear();
                            if (index >= 0 && cursorSize > index) {
                                int offset = mCurrentIndex - index;
                                cursor.move(index);
                                if (cursorSize > mCurrentIndex) {
                                    for (int i = 0; i < offset; i++) {
                                        cursor.moveToNext();
                                        String name = cursor.getString(cursor.getColumnIndex(Constants.Columns.NAME));
                                        String legalPerson = cursor.getString(cursor.getColumnIndex(Constants.Columns.LEGAL_PERSON));
                                        String info = cursor.getString(cursor.getColumnIndex(Constants.Columns.CONPANY_TAPE));
                                        String phone = cursor.getString(cursor.getColumnIndex(Constants.Columns.PHONE));
                                        boolean isCalled = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_CALLED)) == 1;
                                        boolean isHope = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_HOPE)) == 1;
                                        CompanyInfo addressEntity = new CompanyInfo(name, legalPerson, info, phone, isCalled, isHope, uri);
                                        mPreDatas.add(addressEntity);
                                    }
                                } else {
                                    while (cursor.moveToNext()) {
                                        String name = cursor.getString(cursor.getColumnIndex(Constants.Columns.NAME));
                                        String legalPerson = cursor.getString(cursor.getColumnIndex(Constants.Columns.LEGAL_PERSON));
                                        String info = cursor.getString(cursor.getColumnIndex(Constants.Columns.CONPANY_TAPE));
                                        String phone = cursor.getString(cursor.getColumnIndex(Constants.Columns.PHONE));
                                        boolean isCalled = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_CALLED)) == 1;
                                        boolean isHope = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_HOPE)) == 1;
                                        CompanyInfo addressEntity = new CompanyInfo(name, legalPerson, info, phone, isCalled, isHope, uri);
                                        mLastDatas.add(addressEntity);
                                    }
                                }
                            }
                            cursor.close();
                            break;
                        case Constants.LoadType.NEXT:
                            mLastDatas.clear();
                            if (index >= 0 && index < cursorSize) {
                                cursor.move(index);
                                if (cursorSize > Constants.OFF_SET + index) {
                                    for (int i = 0; i < Constants.OFF_SET; i++) {
                                        cursor.moveToNext();
                                        String name = cursor.getString(cursor.getColumnIndex(Constants.Columns.NAME));
                                        String legalPerson = cursor.getString(cursor.getColumnIndex(Constants.Columns.LEGAL_PERSON));
                                        String info = cursor.getString(cursor.getColumnIndex(Constants.Columns.CONPANY_TAPE));
                                        String phone = cursor.getString(cursor.getColumnIndex(Constants.Columns.PHONE));
                                        boolean isCalled = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_CALLED)) == 1;
                                        boolean isHope = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_HOPE)) == 1;
                                        CompanyInfo addressEntity = new CompanyInfo(name, legalPerson, info, phone, isCalled, isHope, uri);
                                        mLastDatas.add(addressEntity);
                                    }
                                } else {
                                    while (cursor.moveToNext()) {
                                        String name = cursor.getString(cursor.getColumnIndex(Constants.Columns.NAME));
                                        String legalPerson = cursor.getString(cursor.getColumnIndex(Constants.Columns.LEGAL_PERSON));
                                        String info = cursor.getString(cursor.getColumnIndex(Constants.Columns.CONPANY_TAPE));
                                        String phone = cursor.getString(cursor.getColumnIndex(Constants.Columns.PHONE));
                                        boolean isCalled = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_CALLED)) == 1;
                                        boolean isHope = cursor.getInt(cursor.getColumnIndex(Constants.Columns.IS_HOPE)) == 1;
                                        CompanyInfo addressEntity = new CompanyInfo(name, legalPerson, info, phone, isCalled, isHope, uri);
                                        mLastDatas.add(addressEntity);
                                    }
                                }
                            }
                            if (IS_DEBUG) {
                                Log.d("liao", "initCompany  mPreDatas: size  " + mPreDatas.size() + " mCurDatas size " + mCurDatas.size()
                                        + " mLastDatas size " + mLastDatas.size());
                            }
                            cursor.close();
                            break;
                        default:
                            break;
                    }

                }
            }
        }).start();
    }

    @Override
    public void onCallClick(CompanyInfo companyInfo, boolean isCalled) {
        ContentValues values = new ContentValues();
        values.put(Constants.Columns.NAME, companyInfo.getName());
        values.put(Constants.Columns.LEGAL_PERSON, companyInfo.getLegalPerson());
        values.put(Constants.Columns.PHONE, companyInfo.getPhone());
        values.put(Constants.Columns.CONPANY_TAPE, companyInfo.getInfo());
        if (companyInfo.isHope()) {
            values.put(Constants.Columns.IS_HOPE, 1);
        } else {
            values.put(Constants.Columns.IS_HOPE, 0);
        }

        if (isCalled) {
            values.put(Constants.Columns.IS_CALLED, 1);
        } else {
            values.put(Constants.Columns.IS_CALLED, 0);
        }
        StringBuffer sb = new StringBuffer();
        sb.append(Constants.Columns.NAME);
        sb.append("=");
        sb.append("\'" + companyInfo.getName() + "\'");
        if (IS_DEBUG) {
            Log.d("liao", "handleClickFavoriteItem update history name " + companyInfo.getName() + "  , isHope " + isCalled);
        }
        CompanyInfoUtils.updateCompanyInfo(getApplicationContext(), companyInfo.getUri(), values, sb.toString(), null);
    }

    @Override
    public void onHopeClick(CompanyInfo companyInfo, boolean isHope) {
        if (companyInfo != null) {
            ContentValues values = new ContentValues();
            values.put(Constants.Columns.NAME, companyInfo.getName());
            values.put(Constants.Columns.LEGAL_PERSON, companyInfo.getLegalPerson());
            values.put(Constants.Columns.PHONE, companyInfo.getPhone());
            values.put(Constants.Columns.CONPANY_TAPE, companyInfo.getInfo());
            if (isHope) {
                values.put(Constants.Columns.IS_HOPE, 1);
            } else {
                values.put(Constants.Columns.IS_HOPE, 0);
            }

            if (companyInfo.isCalled()) {
                values.put(Constants.Columns.IS_CALLED, 1);
            } else {
                values.put(Constants.Columns.IS_CALLED, 0);
            }
            StringBuffer sb = new StringBuffer();
            sb.append(Constants.Columns.NAME);
            sb.append("=");
            sb.append("\'" + companyInfo.getName() + "\'");
            if (IS_DEBUG) {
                Log.d("liao", "handleClickFavoriteItem update history name " + companyInfo.getName() + "  , isHope " + isHope);
            }
            CompanyInfoUtils.updateCompanyInfo(getApplicationContext(), companyInfo.getUri(), values, sb.toString(), null);
        }
    }

    @Override
    public void onDialerClick(CompanyInfo companyInfo) {
        onCallClick(companyInfo, true);
        Uri uri = Uri.parse("tel:" + companyInfo.getPhone());
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.current_city:
                break;
            case R.id.pre:
                if (IS_DEBUG) {
                    Log.d("liao", "onClick pre mCurrentIndex " + mCurrentIndex + " mPreDatas " + mPreDatas.size());
                }
                if (mPreDatas.size() > 0) {
                    mCurDatas.clear();
                    mCurDatas.addAll(mPreDatas);
                    updateData();
                    int nextIndex = mCurrentIndex;
                    mCurrentIndex = mCurrentIndex - OFF_SET;
                    CompanyInfoUtils.putCurrentIndex(this, mCurrentKey, mCurrentIndex);
                    int preIndex = mCurrentIndex - OFF_SET;
                    if (preIndex < 0) {
                        preIndex = 0;
                    }
                    initPreload(preIndex, nextIndex);
                } else {
                    Toast.makeText(this, "已经是第一页", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.next:
                if (IS_DEBUG) {
                    Log.d("liao", "onClick pre mCurrentIndex " + mCurrentIndex + " mLastDatas " + mLastDatas.size());
                }
                if (mLastDatas.size() > 0) {
                    mCurDatas.clear();
                    mCurDatas.addAll(mLastDatas);
                    updateData();
                    int preIndex = mCurrentIndex;
                    mCurrentIndex = mCurrentIndex + OFF_SET;
                    CompanyInfoUtils.putCurrentIndex(this, mCurrentKey, mCurrentIndex);
                    int nextIndex = mCurrentIndex + OFF_SET;
                    initPreload(preIndex, nextIndex);
                } else {
                    Toast.makeText(this, "已经是最后一页", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }
}
