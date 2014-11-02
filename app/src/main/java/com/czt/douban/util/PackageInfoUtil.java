package com.czt.douban.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by GavinCT on 2014/10/28.
 */
public class PackageInfoUtil {
    public static Integer getVersionCode(Context context){
        PackageInfo info = getPackageInfo(context);
        if(info == null){
            return null;
        }
        return info.versionCode;
    }
    public static String getVersionName(Context context){
        PackageInfo info = getPackageInfo(context);
        if(info == null){
            return null;
        }
        return info.versionName;
    }

    private static PackageInfo getPackageInfo(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info;
        } catch (PackageManager.NameNotFoundException e) {
            //can't reach
            e.printStackTrace();
        }
        //can't reach
        return null;
    }
}
