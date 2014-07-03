package com.urucas.plugins;

/*!
 *  
 * ToastPlugin.java
 * Created By Urucas
 * 
 * The source Code is available under 
 * same licenses as javamail which are:
 * CDDL-1.0, GPL-2.0, BSD
 * 
 */

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.widget.Toast;
import android.util.Log;

public class ImageCachePlugin extends CordovaPlugin {

	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        
			if(action.equals("load")) {
				Log.i("load","yeap");				
		  	return false;
	    }	
			return true;
		}
}
