package test.chenfengweiqing.com.companyinfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import test.chenfengweiqing.com.companyinfo.db.Constants;
import test.chenfengweiqing.com.companyinfo.utils.CompanyInfoUtils;

public class MainActivity extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.UPDATE_SHOW_VIEW);
        registerReceiver(mReceiver, intentFilter);
        updateFragment();
    }

    private void updateFragment() {
        boolean isLoad = CompanyInfoUtils.isLoaded(mContext);
        if (isLoad) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new ShowMessageFragment()).commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new LoadDataFragment()).commit();
        }
    }


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Constants.UPDATE_SHOW_VIEW.equals(intent.getAction())) {
                updateFragment();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
