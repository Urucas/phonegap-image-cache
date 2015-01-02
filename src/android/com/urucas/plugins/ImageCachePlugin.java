package com.urucas.plugins;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaChromeClient;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
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

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

		if(action.equals("init")) {
			return true;
		}else if(action.equals("clear")) {

			return true;
		}
		return false;
	}

	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView){
		super.initialize(cordova, webView);

		final CordovaInterface _cordova = cordova;
		final CordovaWebView _webView = webView;
		try {
			Handler mainHandler = new Handler(cordova.getActivity().getApplicationContext().getMainLooper());
			mainHandler.post(new Runnable() {
				@Override
				public void run() {
					CordovaWebViewCached wbCached = new CordovaWebViewCached(_cordova, _webView);

					try {
						Class ca = Class.forName("org.apache.cordova.CordovaWebView");
						Field[] fs = ca.getDeclaredFields();
						for(int i=0; i<fs.length; i++){
							if(fs[i].getName().equals("viewClient")) {
								fs[i].setAccessible(true);
								fs[i].set(_webView, wbCached);
								Log.i("webView client changed", "i think so");
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
			Log.i(TAG, "Error reimplementing CordovaActivity");
			Log.i(TAG, "Android Platform not supported");
		}

	}
}

