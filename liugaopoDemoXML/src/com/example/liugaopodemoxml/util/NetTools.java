package com.example.liugaopodemoxml.util;

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
	public static final String TAG = NetTools.class.getSimpleName();
	public static boolean isNetLink(Context context){
		ConnectivityManager cmg = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo nf = cmg.getActiveNetworkInfo();
		if(nf == null || !nf.isAvailable()){
			return false;
		}
		return true;
	}

	public static InputStream getNetXml(String url){
		InputStream is = null;
		HttpGet request = new HttpGet(url);
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setSoTimeout(params, 5*1000);
		HttpConnectionParams.setConnectionTimeout(params, 5*1000);
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(request);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity enty = response.getEntity();
				is = enty.getContent();
			}else{
				Log.d(TAG, "服务器未连接");
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, e.getMessage());
			e.printStackTrace();
		}
		return is;
	}
	public static Bitmap getNetImage(String url){
		Bitmap is = null;
		HttpGet request = new HttpGet(url);
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setSoTimeout(params, 5*1000);
		HttpConnectionParams.setConnectionTimeout(params, 5*1000);
		HttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(request);

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity enty = response.getEntity();
				is = BitmapFactory.decodeStream(enty.getContent());
			}else{
				Log.d(TAG, "����������ʧ��   ͼƬ");
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, e.getMessage());
			e.printStackTrace();
		}
		return is;
	}

}
