package com.urucas.plugins;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.IceCreamCordovaWebViewClient;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CordovaWebViewCached extends IceCreamCordovaWebViewClient{

	private static final String TAG = "CordovaWebViewCached";

	public CordovaWebViewCached(CordovaInterface cordova, CordovaWebView view) {
		super(cordova, view);
	}

	@Override
	public WebResourceResponse shouldInterceptRequest(WebView view, String url) {

		if(url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".png") || url.endsWith(".gif")){
			try {
				Log.i(TAG, "Overriding image url load");
				Log.i(TAG, url);

				ImageCache _cache = ImageCachePlugin.getCache();
				String cacheFileName = ImageCache.md5(url);
				if(url.endsWith(".jpg") || url.endsWith(".jpeg")){
					_cache.register(url, cacheFileName, "image/jpg", "UTF-8", ImageCachePlugin.CACHE_TIME);
				}else if(url.endsWith(".png")){
					_cache.register(url, cacheFileName, "image/png", "UTF-8", ImageCachePlugin.CACHE_TIME);
				}else if(url.endsWith(".gif")){
					_cache.register(url, cacheFileName, "image/gif", "UTF-8", ImageCachePlugin.CACHE_TIME);
				}
				return _cache.load(url);
				
			}catch(Exception e){}
		}
		return super.shouldInterceptRequest(view, url);
	}

}

