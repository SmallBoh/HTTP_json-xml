package com.example.liugaopodemojson2.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetTools {
	private static final String TAG = NetTools.class.getSimpleName();
	public static boolean isNetLink(Context context){
		ConnectivityManager cmg = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cmg.getActiveNetworkInfo();
		if(netInfo == null || !netInfo.isAvailable()){
			return false;
		}
		return true;
	}
	
	public static InputStream getNetClientXml(String url){
		InputStream is = null;
		HttpGet request = new HttpGet(url);
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setSocketBufferSize(params, 5*1000);
		HttpConnectionParams.setConnectionTimeout(params, 5*1000);
		HttpClient client = new DefaultHttpClient(params);
		try {
			HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				HttpEntity entity = response.getEntity();
				is = entity.getContent();
			}else{
				Log.d(TAG, "·þÎñÆ÷´íÎó ×´Ì¬Âë £º"+response.getStatusLine().getStatusCode());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
		
		return is;
	}
	public static Bitmap getNetClientImage(String url){
		Bitmap img = null;
		HttpGet request = new HttpGet(url);
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setSocketBufferSize(params, 5*1000);
		HttpConnectionParams.setConnectionTimeout(params, 5*1000);
		HttpClient client = new DefaultHttpClient(params);
		try {
			HttpResponse response = client.execute(request);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				img = BitmapFactory.decodeStream(response.getEntity().getContent());
			}else{
				Log.d(TAG, "·þÎñÆ÷´íÎó ×´Ì¬Âë £º"+response.getStatusLine().getStatusCode());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, "4=="+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, "3=="+e.getMessage());
			e.printStackTrace();
		}
		return img;
	}
}
