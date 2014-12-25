package com.example.liugaopodemoxml.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class NetImageLoadInfo {
	private static Map<String, SoftReference<Bitmap>> mImageInfo;
	private Context mContext;
	public NetImageLoadInfo(Context mContext) {
		super();
		this.mContext = mContext;
		mImageInfo = new HashMap<String, SoftReference<Bitmap>>();
	}



	public Bitmap loadNetImage(final String url,final ImageInfoLoad imageInfoLoad){
		Bitmap bit = null;
		if(mImageInfo.containsKey(url)){
			bit = mImageInfo.get(url).get();
			if(bit != null){
				return bit;
			}
		}

		final Handler hd = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case 1:
					imageInfoLoad.onImageLoad(url, (Bitmap)msg.obj);
					break;

				case 2:
					Toast.makeText(mContext, "ÍøÂçÎªÁ´½Ó", 0).show();
					break;
				}
				
			}
		};

		new Thread(){
			public void run() {
				if(NetTools.isNetLink(mContext)){
					Bitmap img= NetTools.getNetImage(url);
					
					//Í¼Æ¬ÅÐ¶ÏÊÇ·ñÎª¿Õ
					mImageInfo.put(url, new SoftReference<Bitmap>(img));
					Message msg = hd.obtainMessage(1, img);
					hd.sendMessage(msg);

				}else{
					hd.sendEmptyMessage(2);
				}
			};
		}.start();

		return bit;

	}
	public interface ImageInfoLoad{
		void onImageLoad(String url,Bitmap bit);
	}

}
