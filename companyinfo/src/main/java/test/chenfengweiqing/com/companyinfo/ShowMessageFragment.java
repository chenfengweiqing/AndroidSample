package test.chenfengweiqing.com.companyinfo;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import test.chenfengweiqing.com.companyinfo.adapter.CityAdapter;
import test.chenfengweiqing.com.companyinfo.adapter.CityInfo;
import test.chenfengweiqing.com.companyinfo.db.Constants;

/**
 * Created by lcz on 18-7-6.
 */

public class ShowMessageFragment extends Fragment implements CityAdapter.OnItemClickListener{
    private RecyclerView mCity;
    private List<CityInfo> mDatas = new ArrayList<>();
    private CityAdapter mCityAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_layout, container, false);
        mCity = view.findViewById(R.id.city);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        mCityAdapter = new CityAdapter(getContext(), mDatas, R.layout.city_item);
        mCity.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mCityAdapter.setOnItemClickListener(this);
        mCity.setAdapter(mCityAdapter );

    }

    private void initData() {
        mDatas.clear();
        mDatas.add(new CityInfo("乌鲁木齐", Constants.CompanyType.WU_LU_MU_QI));
        mDatas.add(new CityInfo("乐山", Constants.CompanyType.LE_SHAN));
        mDatas.add(new CityInfo("克拉玛依", Constants.CompanyType.KE_LA_MA_YI));
        mDatas.add(new CityInfo("兰州", Constants.CompanyType.LAN_ZHOU));
        mDatas.add(new CityInfo("北京", Constants.CompanyType.BEI_JING));
        mDatas.add(new CityInfo("南充", Constants.CompanyType.NAN_CONG));
        mDatas.add(new CityInfo("吐鲁番", Constants.CompanyType.TU_LU_FAN));
        mDatas.add(new CityInfo("哈密", Constants.CompanyType.HA_MI));
        mDatas.add(new CityInfo("喀什", Constants.CompanyType.KA_SHI));
        mDatas.add(new CityInfo("宜宾", Constants.CompanyType.YI_BIN));
        mDatas.add(new CityInfo("广元", Constants.CompanyType.GUANG_YUAN));
        mDatas.add(new CityInfo("德阳", Constants.CompanyType.DE_YANG));
        mDatas.add(new CityInfo("昌吉", Constants.CompanyType.CHANG_JI));
        mDatas.add(new CityInfo("泸州", Constants.CompanyType.LU_ZHOU));
        mDatas.add(new CityInfo("石河子", Constants.CompanyType.SHI_HE_ZI));
        mDatas.add(new CityInfo("绵阳", Constants.CompanyType.MIAN_YANG));
        mDatas.add(new CityInfo("西安", Constants.CompanyType.XI_AN));
    }

    @Override
    public void onItemClick(CityInfo cityInfo, int type) {
        Intent intent = new Intent(getActivity(), ShowCityCompanyActivity.class);
        intent.putExtra(Constants.OPEN_CITY_KEY, cityInfo.getKey());
        intent.putExtra(Constants.OPEN_CITY_NAME_KEY, cityInfo.getName());
        intent.putExtra(Constants.QUERY_TYPE_KEY, type);
        getActivity().startActivity(intent);
    }

}
