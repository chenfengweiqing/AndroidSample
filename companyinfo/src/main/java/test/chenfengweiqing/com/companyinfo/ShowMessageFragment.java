package test.chenfengweiqing.com.companyinfo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import test.chenfengweiqing.com.companyinfo.db.Constants;

/**
 * Created by lcz on 18-7-6.
 */

public class ShowMessageFragment extends Fragment {
    private ListView mCity;
    private List<CityInfo> mDatas = new ArrayList<>();

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
        mCity.setAdapter(new CityAdapter(this, mDatas, R.layout.city_item));
        mCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private void initData(){
        mDatas.clear();
        mDatas.add(new CityInfo(CityInfo);
        mDatas.add(new CityInfo(Constants.CompanyType.WU_LU_MU_QI);
    }

    class CityAdapter extends BaseAdapter {

        private List<CityInfo> infos;
        private LayoutInflater inflater;
        private @LayoutRes
        int layout;
        private Context context;

        public CityAdapter(Context context, List<CityInfo> stuList,
                           @NonNull @LayoutRes int layout) {
            this.infos = stuList;
            this.inflater = LayoutInflater.from(context);
            this.layout = layout;
            this.context = context;
        }

        @Override
        public int getCount() {
            return infos == null ? 0 : infos.size();
        }

        @Override
        public CityInfo getItem(int position) {
            return infos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CityAdapter.ViewHolder viewHolder;
            if (convertView == null) {
                convertView = inflater.inflate(layout, null);
                viewHolder = new CityAdapter.ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (CityAdapter.ViewHolder) convertView.getTag();
            }
            CityInfo info = getItem(position);
            viewHolder.name.setText(info.getName());
            return convertView;
        }

        class ViewHolder {
            TextView name;

            ViewHolder(View view) {
                name = view.findViewById(R.id.name);
            }
        }
    }

    class CityInfo {
        private String name;
        private int key;

        public CityInfo(String name, int key) {
            this.name = name;
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }
    }
}
