package com.example.liugaopodemojson3.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class NetImageLoad {
	private static Map<String, SoftReference<Bitmap>> mImageInfo;
	private Context mContext;
	public NetImageLoad(Context mContext) {
		super();
		this.mContext = mContext;
		mImageInfo = new HashMap<String, SoftReference<Bitmap>>();
	}


	public Bitmap loadimage(final String url,final ImageLoadInfo imageLoadInfo){
		Bitmap image = null;
		if(mImageInfo.containsKey(url)){
			image = mImageInfo.get(url).get();
			if(image != null){
				return image;
			}
		}

		final Handler handler = new Handler(){
			public void handleMessage(android.os.Message msg) {
				switch (msg.what) {
				case 1:
					imageLoadInfo.onGetImage(url, (Bitmap)msg.obj);
					break;

				case 2:
					Toast.makeText(mContext, "w l w l j 2", Toast.LENGTH_LONG).show();
					break;
				}
			};
		};

		new Thread(){
			public void run() {
				if(NetTools.isNetLink(mContext)){
					Bitmap bit = NetTools.getLoadImage(url);
					mImageInfo.put(url, new SoftReference<Bitmap>(bit));
					Message msg = handler.obtainMessage(1, bit);
					handler.sendMessage(msg);
				}else{
					handler.sendEmptyMessage(2);
				}
			};
		}.start();
		return image;
	}

	public interface ImageLoadInfo{
		void onGetImage(String url,Bitmap bit);
	}

}
