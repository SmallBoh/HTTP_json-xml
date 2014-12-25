package com.example.liugaopodemojson3.adapter;

import java.util.List;

import com.example.liugaopodemojson3.JsonActivity;
import com.example.liugaopodemojson3.R;
import com.example.liugaopodemojson3.bean.User;
import com.example.liugaopodemojson3.util.NetImageLoad;
import com.example.liugaopodemojson3.util.NetImageLoad.ImageLoadInfo;
import com.example.liugaopodemojson3.util.NetTools;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class JsonAdapter extends BaseAdapter{
	private List<User> mData;
	private Context mContext;
	private String HOST= "http://121.52.221.85:8888/u/";
	private NetImageLoad mNetImageLoad;
	private static final String TAG = JsonAdapter.class.getSimpleName();

	public JsonAdapter(List<User> mData, Context mContext) {
		super();
		this.mData = mData;
		this.mContext = mContext;
		mNetImageLoad = new NetImageLoad(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View v, final ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHold vh = null;
		if(v == null){
			v = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
			vh = new ViewHold();
			vh.img = (ImageView) v.findViewById(R.id.item_img);
			vh.name = (TextView) v.findViewById(R.id.item_name);
			v.setTag(vh);
		}else{
			vh = (ViewHold) v.getTag();
		}
		User us = mData.get(arg0);

		vh.name.setText(us.getName());
		String url = HOST+us.getId()+"/"+us.getImg();
		vh.img.setTag(url);

		Bitmap image = mNetImageLoad.loadimage(url, new ImageLoadInfo() {

			@Override
			public void onGetImage(String url, Bitmap bit) {
				// TODO Auto-generated method stub
				ImageView pic = (ImageView) arg2.findViewWithTag(url);
				if(pic != null && bit != null){
					pic.setImageBitmap(bit);
				}else{
					Log.d(TAG, "PIC OR BIT IS NULL");
				}
			}
		});
		if(image != null){
			vh.img.setImageBitmap(image);
		}else{
			vh.img.setImageResource(R.drawable.ic_launcher);
		}
		return v;
	}

	class ViewHold{
		ImageView img;
		TextView name;
	}

}
