package test.chenfengweiqing.com.companyinfo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author lcz
 * @date 18-7-6
 */

public class LoadDataFragment extends Fragment {
    private TextView mLoadButton;
    private TextView mPrompt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.load_data_layout, container, false);
        mLoadButton = view.findViewById(R.id.load_button);
        mPrompt = view.findViewById(R.id.show_wait);
        mLoadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUpdateWeatherServiceIntent(getActivity());
                mLoadButton.setVisibility(View.GONE);
                mPrompt.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }


    /**
     * @return intent for start update weather services.
     */
    private void getUpdateWeatherServiceIntent(@NonNull final Context context) {
        Intent intent = new Intent("test.chenfengweiqing.com.companyinfo.LOAD_SERVICE");
        intent.setComponent(new ComponentName("test.chenfengweiqing.com.companyinfo",
                "test.chenfengweiqing.com.companyinfo.LoadDataService"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }
}
