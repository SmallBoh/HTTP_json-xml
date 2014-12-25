package com.example.liugaopodemojson2.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class NetImageLoadInfo {
	private static Map<String, SoftReference<Bitmap>> mImageInfo = null;
	private Context mContext;
	public NetImageLoadInfo(Context context){
		mContext = context;
		mImageInfo = new HashMap<String, SoftReference<Bitmap>>();
	}

	public Bitmap LoadImage(final String url , final ImageLoadFace imageLoadFace){
		Bitmap image = null;
		if(mImageInfo.containsKey(url)){
			image = mImageInfo.get(url).get();
			if(image != null){
				return image;
			}
		}
		final Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case 1:
					imageLoadFace.onLoadInmage(url, (Bitmap)msg.obj);
					break;

				case 2:
					Toast.makeText(mContext, "ÍøÂçÎ´Á´½Ó", Toast.LENGTH_LONG).show();
					break;
				}
			}
		};

		new Thread(){
			public void run() {
				if(NetTools.isNetLink(mContext)){
					Bitmap bitmap = NetTools.getNetClientImage(url);
					mImageInfo.put(url,new SoftReference<Bitmap>(bitmap));
					Message msg = handler.obtainMessage(1, bitmap);
					handler.sendMessage(msg);
				}else{
					handler.sendEmptyMessage(2);
				}

			};
		}.start();

		return image;
	}


	public interface ImageLoadFace{
		void onLoadInmage(String url,Bitmap bit);
	}
}