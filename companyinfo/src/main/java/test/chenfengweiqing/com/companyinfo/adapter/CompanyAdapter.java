package test.chenfengweiqing.com.companyinfo.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import test.chenfengweiqing.com.companyinfo.R;
import test.chenfengweiqing.com.companyinfo.ShowCityCompanyActivity;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    private List<CompanyInfo> infos;
    private Context context;
    private @LayoutRes
    int layout;
    private OnItemClickListener mOnItemClickListener;

    public void setInfos(List<CompanyInfo> stuList) {
        this.infos = stuList;
        notifyDataSetChanged();
    }

    public CompanyAdapter(Context context, List<CompanyInfo> stuList, @NonNull @LayoutRes int layout) {
        this.infos = stuList;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CompanyInfo info = infos.get(position);
        holder.name.setText(info.getName());
        holder.legalName.setText(info.getLegalPerson());
        holder.phone.setText(info.getPhone());
        holder.info.setText(info.getInfo());
        holder.isCall.setChecked(info.isCalled());
        holder.isHope.setChecked(info.isHope());
        if (mOnItemClickListener != null) {
            holder.phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onDialerClick(info);
                }
            });
            holder.isCall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mOnItemClickListener.onCallClick(info, isChecked);
                }
            });
            holder.isHope.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mOnItemClickListener.onHopeClick(info, isChecked);
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return infos != null ? infos.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView legalName;
        TextView phone;
        TextView info;
        CheckBox isCall;
        CheckBox isHope;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            legalName = view.findViewById(R.id.leagal_person);
            phone = view.findViewById(R.id.phone);
            info = view.findViewById(R.id.info);
            isCall = view.findViewById(R.id.is_called);
            isHope = view.findViewById(R.id.is_hope);
        }
    }

    public interface OnItemClickListener {
        void onCallClick(CompanyInfo companyInfo, boolean isCalled);

        void onHopeClick(CompanyInfo companyInfo, boolean isHope);

        void onDialerClick(CompanyInfo companyInfo);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }
}

