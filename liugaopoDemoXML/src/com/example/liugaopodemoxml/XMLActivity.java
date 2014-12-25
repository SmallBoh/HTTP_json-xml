package com.example.liugaopodemoxml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.example.liugaopodemoxml.adapter.XmlAdapter;
import com.example.liugaopodemoxml.bean.ArticleItem;
import com.example.liugaopodemoxml.util.NetTools;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Xml;
import android.widget.GridView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;

public class XMLActivity extends Activity {
	private static final String LOAD_ADDRESS = "http://app.cnautoshows.com/Html/Temp//GetArticleList/04091368292954847cb5f1f8fb4a9512.Xml";
	public static final String TAG = XMLActivity.class.getSimpleName();
	private Context mContext;
	private GridView mGrid;

	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				XmlAdapter adapter = new XmlAdapter((List<ArticleItem>)msg.obj, mContext);
				mGrid.setAdapter(adapter);
				break;

			case 2:
				Toast.makeText(mContext, "����δ���� ", Toast.LENGTH_LONG).show();
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_layout);
		mGrid = (GridView) findViewById(R.id.gridView);
		mContext = this;

		new Thread(){

			public void run() {
				if(NetTools.isNetLink(mContext)){
					InputStream is = NetTools.getNetXml(LOAD_ADDRESS);

					//对InputStream判空
					if(is != null){

						List<ArticleItem> list = getXml(is);

						Message msg = mHandler.obtainMessage(1, list);
						mHandler.sendMessage(msg);
					}else{
						Log.d(TAG, "获取数据为空");
					}

				}else{
					mHandler.sendEmptyMessage(2);
				}
			};
		}.start();
	}
	public List<ArticleItem> getXml(InputStream is){
		List<ArticleItem> list = new ArrayList<ArticleItem>();
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(is, "utf-8");
			int i = parser.getEventType();
			ArticleItem art = null;
			String tagName = "";
			while(i != XmlPullParser.END_DOCUMENT){
				switch (i) {
				case XmlPullParser.START_TAG:
					tagName = parser.getName();
					if("ArticleItem".equals(tagName)){
						art = new ArticleItem();
						art.setThumbnail(parser.getAttributeValue(0));

					}else{
						Log.d(TAG, tagName);
					}

					break;

				case XmlPullParser.TEXT:
					String test = parser.getText();
					if(art != null){
						if("title".equals(tagName)){
							art.setTitle(test);
						}else if("pubtime".equals(tagName)){
							art.setPubtime(test);
						}else if("source".equals(tagName)){
							art.setSource(test);
						}
					}else{
						Log.d(TAG, tagName);
					}
					break;
				case XmlPullParser.END_TAG:
					tagName = parser.getName();
					if("ArticleItem".equals(tagName)){
						list.add(art);
						art = null;
					}else{
						Log.d(TAG, tagName);
					}
					tagName = "";
					break;
				}
				i = parser.next();
			}
			Log.d(TAG, list.toString());

		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				if(is != null){
					is.close();
					is = null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d(TAG, e.getMessage());
				e.printStackTrace();
			}
		}
		return list;
	}

}
