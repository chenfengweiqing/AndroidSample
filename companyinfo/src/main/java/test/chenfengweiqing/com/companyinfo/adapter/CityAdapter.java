package test.chenfengweiqing.com.companyinfo.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.chenfengweiqing.com.companyinfo.R;
import test.chenfengweiqing.com.companyinfo.db.Constants;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private List<CityInfo> infos;
    private Context context;
    private @LayoutRes
    int layout;
    private OnItemClickListener mOnItemClickListener;

    public CityAdapter(Context context, List<CityInfo> stuList, @NonNull @LayoutRes int layout) {
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
        final CityInfo info = infos.get(position);
        holder.name.setText(info.getName());
        if (mOnItemClickListener != null) {
            holder.all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(info, Constants.QueryType.ALL);
                }
            });
            holder.unCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(info, Constants.QueryType.UN_CALL);
                }
            });
            holder.hope.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(info, Constants.QueryType.HOPE);
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
        TextView all;
        TextView unCall;
        TextView hope;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            all = view.findViewById(R.id.all);
            unCall = view.findViewById(R.id.un_call);
            hope = view.findViewById(R.id.hope);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(CityInfo cityInfo, int type);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }
}


