package com.urucas.plugins;

import java.lang.reflect.Method;

import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaChromeClient;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewClient;
import android.util.Log;

public class ImageCachePlugin extends CordovaPlugin {

	private static final String TAG = "ImageCachePlugin";
	private static ImageCache _cache;
	private static ImageCachePlugin _instance;
	
	public ImageCachePlugin(){
		_instance = this;
	}
	
	public static ImageCache getCache() {
		if(_cache == null){
			_cache = new ImageCache(_instance.cordova.getActivity()); 
		}
		return _cache;
	}
	
	public void initialize(CordovaInterface cordova, CordovaWebView webView){
		super.initialize(cordova, webView);
		
		if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB ||
                android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            	Log.i(TAG, "Android Platform not supported");
        }
        else {
        	try {
    			CordovaWebViewCached wbCached = new CordovaWebViewCached(this.cordova, webView);
            		CordovaActivity _activity = (CordovaActivity) this.cordova.getActivity();
            		_activity.init(webView, wbCached, webView.getWebChromeClient());
    			
    		} catch (Exception e) {
    			Log.i("TAG", "Error reimplementing CordovaActivity");
    		}
        }
	}
}
