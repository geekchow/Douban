package com.czt.douban.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
	public static boolean isOnline(Context context) {
	    ConnectivityManager connMgr = getConnectivityManager(context);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    return (networkInfo != null && networkInfo.isConnected());
	}
	public static boolean isWifiConnected(Context context){
		ConnectivityManager connMgr = getConnectivityManager(context);
		NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI); 
		return networkInfo.isConnected();
	}
	public static boolean isMobileConnected(Context context){
		ConnectivityManager connMgr = getConnectivityManager(context);
		NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		return networkInfo.isConnected();
	}
	private static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	}
}
