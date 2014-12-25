package com.example.liugaopodemoxml.adapter;

import java.util.List;

import com.example.liugaopodemoxml.R;
import com.example.liugaopodemoxml.XMLActivity;
import com.example.liugaopodemoxml.bean.ArticleItem;
import com.example.liugaopodemoxml.util.NetImageLoadInfo;
import com.example.liugaopodemoxml.util.NetImageLoadInfo.ImageInfoLoad;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class XmlAdapter extends BaseAdapter{
	private List<ArticleItem> mData;
	private Context mContext;
	private NetImageLoadInfo mNetImageLoadInfo;
	public static final String TAG = XmlAdapter.class.getSimpleName();

	public XmlAdapter(List<ArticleItem> mData, Context mContext) {
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
		return 0;
	}

	@Override
	public View getView(int position, View v, final ViewGroup parent) {
		// TODO Auto-generated method stub
		VeiwHold vg = null;
		if(v == null){
			v = LayoutInflater.from(mContext).inflate(R.layout.item_layout, null);
			vg = new VeiwHold();
			vg.img = (ImageView) v.findViewById(R.id.item_img);
			vg.title = (TextView) v.findViewById(R.id.item_title);
			vg.pubtime = (TextView) v.findViewById(R.id.item_pubtime);
			vg.source = (TextView) v.findViewById(R.id.item_source);
			v.setTag(vg);
		}else{
			vg = (VeiwHold) v.getTag();
		}
		ArticleItem art =mData.get(position);
		
		vg.title.setText(art.getTitle());
		vg.pubtime.setText(art.getPubtime());
		vg.source.setText(art.getSource());
		Log.d(TAG, art.toString());
		
		
		String str = art.getThumbnail();
		Log.d(TAG, str);
		vg.img.setTag(str);
		
		Bitmap bit = mNetImageLoadInfo.loadNetImage(str, new ImageInfoLoad() {
			
			@Override
			public void onImageLoad(String url, Bitmap bit) {
				// TODO Auto-generated method stub
				ImageView img = (ImageView) parent.findViewWithTag(url);
				if(img != null && bit != null){
					img.setImageBitmap(bit);
				}
			}
		});
		vg.img.setImageBitmap(bit);
		
		return v;
	}
	class VeiwHold{
		ImageView img;
		TextView title;
		TextView pubtime;
		TextView source;
		
	}
}
