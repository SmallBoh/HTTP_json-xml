package com.example.liugaopodemojson.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
		NetworkInfo info = cmg.getActiveNetworkInfo();
		if(info == null || !info.isAvailable()){
			return false;
		}

		return true;
	}

	public static String getNetJSON(String Jsonurl){
		String result = "";
		HttpURLConnection conn = null;
		BufferedReader br = null;
		try {
			URL url = new URL(Jsonurl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(5*1000);
			conn.setConnectTimeout(5*1000);
			if(conn.getResponseCode() == 200){
				/*
				 * HttpClient
				 * HttpEntity entity = (HttpEntity) conn.getContent();
				result  = EntityUtils.toString(entity, "gbk");*/
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"gbk"));
				String str = "";
				StringBuilder stb = new StringBuilder();
				while((str = br.readLine())!= null){
					stb.append(str);
				}
				result = stb.toString();
				
			}else{
				Log.d(TAG, "服务器错误 状态码 ："+conn.getResponseCode());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, e.getMessage());

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
				Log.d(TAG, e.getMessage());
				e.printStackTrace();
			}
		}
		return result;
	}

	@SuppressWarnings("unused")
	public static Bitmap getNetImage(String Jsonurl){
		Bitmap result = null;
		HttpURLConnection conn = null;
		BufferedReader br = null;
		try {
			URL url = new URL(Jsonurl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(5*1000);
			conn.setConnectTimeout(5*1000);
			if(conn.getResponseCode() == 200){
				
				result = BitmapFactory.decodeStream(conn.getInputStream());
			}else{
				Log.d(TAG, "服务器错误 状态码 ："+conn.getResponseCode());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, e.getMessage());

			e.printStackTrace();
		}finally{
			try {
				if(conn != null){
					conn.connect();
					conn = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d(TAG, e.getMessage());
				e.printStackTrace();
			}
		}
		return result;
	}

}
