package com.example.liugaopodemojson3.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;

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

		NetworkInfo nf = cmg.getActiveNetworkInfo();
		if(nf == null || !nf.isAvailable() ){
			return false;
		}

		return true;
	}


	public static String getLoadJson(String strUrl){
		String result = "";
		HttpURLConnection conn = null;
		BufferedReader br = null;
		try {
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5*1000);
			conn.setReadTimeout(5*1000);
			if(conn.getResponseCode() == 200){
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
				String str = "";
				StringBuilder stb = new StringBuilder();
				while((str = br.readLine())!= null){
					stb.append(str);
				}
				result = stb.toString();
			}else{
				Log.d(TAG, "z t m :"+conn.getResponseCode());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(conn != null){
					conn.connect();
					conn = null;
				}
				if(br != null){
					br.close();
					br = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;

	}
	
	public static Bitmap getLoadImage(String strUrl){
		Bitmap result = null;
		HttpURLConnection conn = null;
		try {
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5*1000);
			conn.setReadTimeout(5*1000);
			if(conn.getResponseCode() == 200){
				result = BitmapFactory.decodeStream(conn.getInputStream());
			}else{
				Log.d(TAG, "z t m :"+conn.getResponseCode());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(conn != null){
					conn.connect();
					conn = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;

	}
}
