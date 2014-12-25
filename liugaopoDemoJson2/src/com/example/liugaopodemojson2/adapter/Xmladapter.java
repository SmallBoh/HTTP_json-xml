package com.example.liugaopodemojson2.adapter;

import java.util.List;

import com.example.liugaopodemojson2.R;
import com.example.liugaopodemojson2.bean.Contact;
import com.example.liugaopodemojson2.util.NetImageLoadInfo;
import com.example.liugaopodemojson2.util.NetImageLoadInfo.ImageLoadFace;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Xmladapter extends BaseAdapter{
	private List<Contact> mData;
	private Context mContext;
	private NetImageLoadInfo mNetImageLoadInfo;
	private static final String HOST = "http://192.168.1.245:8080/serversocket/";	
	public Xmladapter(List<Contact> mData, Context mContext) {
		super();
		this.mData = mData;
		this.mContext = mContext;
		mNetImageLoadInfo = new NetImageLoadInfo(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View v, final ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHold vh;
		if(v == null){
			v = LayoutInflater.from(mContext).inflate(R.layout.item_layout, null);
			vh = new ViewHold();
			vh.img = (ImageView) v.findViewById(R.id.item_img);
			vh.name = (TextView) v.findViewById(R.id.item_name);
			vh.content = (TextView) v.findViewById(R.id.item_content);
			vh.gender = (TextView) v.findViewById(R.id.item_gender);
			v.setTag(vh);
		}else{
			vh = (ViewHold) v.getTag();
		}
		Contact cta = mData.get(position);
		vh.name.setText(cta.getName());
		vh.content.setText(cta.getContent());
		vh.gender.setText(cta.getGender());
		
		String url = HOST+cta.getIcon();
		vh.img.setTag(cta);
		Bitmap image = mNetImageLoadInfo.LoadImage(url, new ImageLoadFace() {
			
			@Override
			public void onLoadInmage(String url, Bitmap bit) {
				// TODO Auto-generated method stub
				ImageView img = (ImageView) parent.findViewWithTag(url);
				if(img != null && bit != null){
					img.setImageBitmap(bit);
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
		TextView content;
		TextView gender;
	}
}
