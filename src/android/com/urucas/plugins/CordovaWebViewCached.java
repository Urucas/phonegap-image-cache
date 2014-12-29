package com.urucas.plugins;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.cordova.Config;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.IceCreamCordovaWebViewClient;
import org.apache.cordova.LOG;
import org.apache.cordova.CordovaResourceApi.OpenForReadResult;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
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

		ImageCache _cache = ImageCachePlugin.getCache();
		
		if(url.endsWith(".jpg") || url.endsWith(".jpeg")){
			String cacheFileName = ImageCache.md5(url);
			_cache.register(url, cacheFileName, "image/jpg", "UTF-8", 5*ImageCache.ONE_DAY);
			return _cache.load(url);
			
		}else if(url.endsWith(".png")){
			String cacheFileName = ImageCache.md5(url);
			_cache.register(url, cacheFileName, "image/png", "UTF-8", 5*ImageCache.ONE_DAY);
			return _cache.load(url);
		}else if(url.endsWith(".gif")){
			String cacheFileName = ImageCache.md5(url);
			_cache.register(url, cacheFileName, "image/gif", "UTF-8", 5*ImageCache.ONE_DAY);
			return _cache.load(url);
		}
		return super.shouldInterceptRequest(view, url);
	}

}
