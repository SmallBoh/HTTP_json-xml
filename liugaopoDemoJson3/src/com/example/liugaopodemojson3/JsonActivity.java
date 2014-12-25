package com.example.liugaopodemojson3;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.liugaopodemojson3.adapter.JsonAdapter;
import com.example.liugaopodemojson3.bean.User;
import com.example.liugaopodemojson3.util.NetTools;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;

public class JsonActivity extends Activity {
	private static final String LOAD_ADDRESS = "http://121.52.221.85:8888/cms/exp/ealExp.dhtml?rp=1&ps=20";

	private static final String TAG = JsonActivity.class.getSimpleName();
	private ListView mLv;
	private Context mContext;
	private GetAsyncTask mGetAsyncTask;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_layout);
		mLv = (ListView) findViewById(R.id.listView);
		mContext = this;
		if(NetTools.isNetLink(mContext)){
			mGetAsyncTask = new GetAsyncTask();
			mGetAsyncTask.execute(LOAD_ADDRESS);
		}else{
			Toast.makeText(mContext, "网络未连接", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(mGetAsyncTask != null){
			mGetAsyncTask.cancel(false);
			mGetAsyncTask = null;
		}
	}

	private List<User> getJson(String chr){
		List<User> list = new ArrayList<User>();
		JSONObject jsonObj;
		User us = null;
		try {
			jsonObj = new JSONObject(chr);
			JSONArray ary = jsonObj.getJSONArray("data");
			int len = ary.length();

			for (int i = 0; i < len; i++) {
				JSONObject jsonObj2 = ary.getJSONObject(i);
				us = new User();
				us.setId(jsonObj2.optString("ID"));
				us.setImg(jsonObj2.optString("E_PIC"));
				us.setName(jsonObj2.optString("E_NAME"));
				list.add(us);
			}
			Log.d(TAG, list.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "jx   "+e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	class GetAsyncTask extends AsyncTask<String, Integer, List<User>>{

		@Override
		protected List<User> doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			String utr = NetTools.getLoadJson(params[0]);
			if(utr != null){
			return getJson(utr);
			}
			Log.d(TAG, "Net 网上获取的数据为空");
			return null;
		}

		@Override
		protected void onPostExecute(List<User> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result != null){
				JsonAdapter adapter = new JsonAdapter(result, mContext);
				mLv.setAdapter(adapter);
			}else{
				Log.d(TAG, "GetAsyncTask onPostExecute result is null");
			}
		}
	}
}
