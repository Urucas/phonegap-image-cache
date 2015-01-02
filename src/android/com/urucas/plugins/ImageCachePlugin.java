package com.urucas.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.util.Log;

public class ImageCachePlugin extends CordovaPlugin {

	private static final String TAG = "ImageCachePlugin";
	private static ImageCache _cache;
	private static CordovaWebView _webView;
	
	public static long CACHE_TIME = 5*ImageCache.ONE_DAY;
	
	public static ImageCache getCache(){
		return _cache;
	}
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if(action.equals("config")) {
			int duration = 5;
			try {
				JSONObject params = args.getJSONObject(0);
				duration = params.getInt("duration");
				duration = duration < 1 ? 1: duration;
				duration = duration > 7 ? 7: duration;
				
			}catch(Exception e){}
			
			CACHE_TIME = duration*ImageCache.ONE_DAY;
			return true;
			
		}else if(action.equals("clear")) {
			
			return true;
		}
		return false;
	}

	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView){

		try {
			final CordovaWebViewCached wbCached = new CordovaWebViewCached(cordova, webView);
			_cache = new ImageCache(cordova.getActivity());
			_webView = webView;
			
			Handler handler = new Handler(cordova.getActivity().getApplicationContext().getMainLooper());
			handler.post(new Runnable() {
				@Override
				public void run() {
					_webView.setWebViewClient(wbCached);
				}
			});
	
		} catch (Exception e) {
			e.printStackTrace();
			Log.i(TAG, "Error reimplementing CordovaActivity");
			Log.i(TAG, "Android Platform not supported");
		}

	}
}

