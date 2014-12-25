package com.example.liugaopodemojson2;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.example.liugaopodemojson2.adapter.Xmladapter;
import com.example.liugaopodemojson2.bean.Contact;
import com.example.liugaopodemojson2.util.NetTools;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.Xml;
import android.widget.ListView;
import android.widget.Toast;

public class JosnActivity extends Activity {
	private static final String LOAD_ADDRESS = "http://192.168.1.245:8080/serversocket/sub/pro03_data.xml";
	private static final String TAG = JosnActivity.class.getSimpleName();
	private ListView mLv;
	private Context mContext;
	private final Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
				switch (msg.what) {
				case 1:
					Xmladapter adapter = new Xmladapter((List<Contact>)msg.obj, mContext);
					mLv.setAdapter(adapter);
					break;

				case 2:
					Toast.makeText(mContext, "网络没链接2", Toast.LENGTH_LONG).show();
					break;
				}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_listview);
		mLv = (ListView) findViewById(R.id.listView);
		mContext = this;
		
		new Thread(){
			public void run() {
				if(NetTools.isNetLink(mContext)){
					InputStream is = NetTools.getNetClientXml(LOAD_ADDRESS);
					Log.d(TAG, "获取数据为 ：=="+is);
					List<Contact> list = getXML(is);
					Message msg = mHandler.obtainMessage(1, list);
					mHandler.sendMessage(msg);
				}else{
					mHandler.sendEmptyMessage(2);
				}
			}
		}.start();
		
	}

	public static List<Contact> getXML(InputStream stream){
		Log.d(TAG, "XML sTREAM " +stream);
		List<Contact> list = new ArrayList<Contact>();
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(stream, "gbk");
			int evy = parser.getEventType();
			String tagName = "";
			Contact contact = null;
			while (evy != XmlPullParser.END_DOCUMENT) {
				switch (evy) {
			/*	case XmlPullParser.START_DOCUMENT:
					tagName = parser.getName();
					Log.d(TAG, "解析文档开始 ："+tagName);
					break;*/

				case XmlPullParser.START_TAG:
					tagName = parser.getName();
					
					if("contact".equals(tagName)){
						contact = new Contact();
						contact.setIcon(parser.getAttributeValue(0));
					}else{
						Log.d(TAG, tagName);
					}
					break;
				case XmlPullParser.TEXT:
					String test = parser.getText();
					if(contact != null){
						if("name".equals(tagName)){
							contact.setName(test);
						}else if("content".equals(tagName)){
							contact.setContent(test);
						}else if("gender".equals(tagName)){
							contact.setGender(test);
						}
					}else{
						Log.d(TAG, "contact is NULL");
					}
					break;

				case XmlPullParser.END_DOCUMENT:
					tagName = parser.getName();
					if("contact".equals(tagName)){
						list.add(contact);
						contact = null;
					}
					tagName = "";
					break;
				}
				evy = parser.next();
			}
			//==????????????????????????????????????????????
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "2=="+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "1=="+e.getMessage());
			e.printStackTrace();
		}

		return list;

	}

}
