package com.example.liugaopodemojson.adapter;

import java.util.List;

import com.example.liugaopodemojson.R;
import com.example.liugaopodemojson.bean.Student;
import com.example.liugaopodemojson.util.NetLoadImageInfo;
import com.example.liugaopodemojson.util.NetLoadImageInfo.ImageLoadInfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class JsonAdapter extends BaseAdapter{
	private List<Student> mData;
	private Context mContext;
	private NetLoadImageInfo mNetLoadImageInfo;
	
	public JsonAdapter(List<Student> mData, Context mContext) {
		super();
		this.mData = mData;
		this.mContext = mContext;
		mNetLoadImageInfo = new NetLoadImageInfo(mContext);
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
			vh.age = (TextView) v.findViewById(R.id.item_age);
			vh.math = (TextView) v.findViewById(R.id.item_math);
			vh.english = (TextView) v.findViewById(R.id.item_english);
			vh.chinese = (TextView) v.findViewById(R.id.item_chinese);
			vh.phone1 = (TextView) v.findViewById(R.id.item_phone1);
			vh.phone2 = (TextView) v.findViewById(R.id.item_phone2);
			v.setTag(vh);
		}else{
			vh = (ViewHold) v.getTag();
		}
			Student stu = mData.get(position);
			vh.name.setText(stu.getName());
			vh.age.setText(stu.getAge()+"");
			vh.math.setText(stu.getScore().getMath()+"");
			vh.english.setText(stu.getScore().getEnglish()+"");
			vh.chinese.setText(stu.getScore().getChinese()+"");
			vh.phone1.setText(stu.getTelephone()[0]);
			vh.phone2.setText(stu.getTelephone()[1]);
			
			String url = stu.getImgUrl();
			Log.d("==", url);
			vh.img.setTag(url);
			Bitmap image = mNetLoadImageInfo.loadImageInfo(url, new ImageLoadInfo() {
				
				@Override
				public void onImageInfo(String url, Bitmap bit) {
					// TODO Auto-generated method stub
					ImageView pic = (ImageView) parent.findViewWithTag(url);
					if(pic != null && bit != null){
						pic.setImageBitmap(bit);
					}
				}
			});
			if (image != null) {
				vh.img.setImageBitmap(image);
			}else{
				vh.img.setImageResource(R.drawable.ic_launcher);
			}
		return v;
	}
	class ViewHold{
		ImageView img;
		TextView name;
		TextView age;
		TextView math;
		TextView english;
		TextView chinese;
		TextView phone1;
		TextView phone2;
	}
}
