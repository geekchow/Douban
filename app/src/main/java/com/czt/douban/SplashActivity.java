package com.czt.douban;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.czt.douban.util.NetworkUtil;
import com.czt.douban.util.PackageInfoUtil;


public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView versionName = (TextView) findViewById(R.id.splash_version_name);
        versionName.setText(getString(R.string.app_version, PackageInfoUtil.getVersionName(this)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(NetworkUtil.isOnline(this)) {
            //进入主界面
            new Thread(){
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    loadMainUI();
                };
            }.start();
        } else {
            showSetNetworkDialog();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mNetworkSettingDialog != null) {
            mNetworkSettingDialog.dismiss();
        }
    }

    private Dialog mNetworkSettingDialog;
    private void showSetNetworkDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.network_setting));
        builder.setMessage(getString(R.string.tip_network_error));
        builder.setPositiveButton(R.string.network_setting, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //cmp=com.android.settings/.WirelessSettings
                Intent intent = new Intent();
                intent.setClassName("com.android.settings","com.android.settings.WirelessSettings");
                startActivity(intent);

            }
        });
        builder.setNegativeButton(R.string.cancel,  new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        mNetworkSettingDialog = builder.show();
    }
    private void loadMainUI() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
