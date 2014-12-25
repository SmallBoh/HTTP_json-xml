package com.example.liugaopodemojson.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class NetLoadImageInfo {


	private  static Map<String, SoftReference<Bitmap>> mapImageInfo;
	private static Context mContext;
	@SuppressWarnings("static-access")
	public NetLoadImageInfo(Context mContext){
		this.mContext = mContext;
		mapImageInfo = new HashMap<String, SoftReference<Bitmap>>();
	}

	public Bitmap loadImageInfo(final String url,final ImageLoadInfo imageLoadInfo){
		Bitmap bit = null;

		if(mapImageInfo.containsKey(url)){
			bit = mapImageInfo.get(url).get();
			if(bit != null){
				return bit;
			}
		}

		final Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				
				switch (msg.what) {
				case 1:
					imageLoadInfo.onImageInfo(url, (Bitmap)msg.obj);;
					break;

				case 2:
					Toast.makeText(mContext, "网络为链接", Toast.LENGTH_LONG).show();
					break;
				}
			}
		};
		new Thread(){
			public void run() {
				if(NetTools.isNetLink(mContext)){
					Bitmap image = NetTools.getNetImage(url);
					mapImageInfo.put(url, new SoftReference<Bitmap>(image));
					Message msg = handler.obtainMessage(1, image);
					handler.sendMessage(msg);
				}else{
					handler.sendEmptyMessage(2);
				}
			};
		}.start();

		return bit;
	}

	public interface ImageLoadInfo{
		void onImageInfo(String url,Bitmap bit);
	}
}
