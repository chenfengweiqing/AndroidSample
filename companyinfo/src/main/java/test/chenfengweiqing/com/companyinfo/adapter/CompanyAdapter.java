package test.chenfengweiqing.com.companyinfo.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import test.chenfengweiqing.com.companyinfo.R;

import static test.chenfengweiqing.com.companyinfo.db.Constants.IS_DEBUG;

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

    public void updatInfo(CompanyInfo info) {
        if (IS_DEBUG) {
            Log.d("liao ", "updatInfo: info " + info);
        }
        if (info != null && !TextUtils.isEmpty(info.getName()) && infos != null && infos.size() > 0) {
            int index = -1;
            for (int i = 0; i < infos.size(); i++) {
                if (info.getName().equals(infos.get(i).getName())) {
                    index = i;
                    break;
                }
            }
            if (IS_DEBUG) {
                Log.d("liao ", "updatInfo: index " + index);
            }
            if (index != -1) {
                infos.remove(index);
                infos.add(index, info);
                notifyItemChanged(index);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CompanyInfo info = infos.get(position);
        if (IS_DEBUG) {
            Log.d("liao ", "onBindViewHolder: " + info.getName() + " is called " + info.isCalled() + " isHope " + info.isHope());
        }
        holder.name.setText(info.getName());
        holder.legalName.setText(info.getLegalPerson());
        holder.phone.setText(info.getPhone());
        holder.info.setText(info.getInfo());
        if (info.isCalled()) {
            holder.isCall.setImageDrawable(context.getResources().getDrawable(R.drawable.favorite));
        } else {
            holder.isCall.setImageDrawable(context.getResources().getDrawable(R.drawable.not_favorite));
        }
        if (info.isHope()) {
            holder.isHope.setImageDrawable(context.getResources().getDrawable(R.drawable.favorite));
        } else {
            holder.isHope.setImageDrawable(context.getResources().getDrawable(R.drawable.not_favorite));
        }
        if (mOnItemClickListener != null) {
            holder.phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onDialerClick(info);
                }
            });
            holder.isHope.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onHopeClick(info, !info.isHope());
                }
            });
            holder.isCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onCallClick(info, !info.isCalled());
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
        ImageButton isCall;
        ImageButton isHope;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            legalName = view.findViewById(R.id.leagal_person);
            phone = view.findViewById(R.id.phone);
            info = view.findViewById(R.id.info);
            isCall = view.findViewById(R.id.is_called_bt);
            isHope = view.findViewById(R.id.is_hope_bt);
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

