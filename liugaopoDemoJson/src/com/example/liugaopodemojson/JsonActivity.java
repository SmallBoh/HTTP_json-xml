package com.example.liugaopodemojson;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.liugaopodemojson.adapter.JsonAdapter;
import com.example.liugaopodemojson.bean.Score;
import com.example.liugaopodemojson.bean.Student;
import com.example.liugaopodemojson.util.NetTools;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.app.Activity;
import android.content.Context;

public class JsonActivity extends Activity {
	private static final String TAG = JsonActivity.class.getSimpleName();
	private final String LOAD_ADDRESS = "http://192.168.1.245:8080/serversocket/sub/data02.json";
	private  Context mContext;
	private ListView mLv;
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
        	Log.d(TAG, "网络为链接 mGetAsyncTask");
        }
    }
    
    private List<Student> getJson(String json){
    	List<Student> list = new ArrayList<Student>();
    	JSONObject jsonObj;
    	Student stu = null;
    	Score score = null;
    	try {
			jsonObj = new JSONObject(json);
			JSONArray array = jsonObj.getJSONArray("student");
			int len = array.length();
			for (int i = 0; i < len; i++) { 
				JSONObject jsonObj2 = array.getJSONObject(i);
				stu = new Student();
				stu.setId(jsonObj2.optInt("id"));
				stu.setName(jsonObj2.optString("name"));
				stu.setAge(jsonObj2.optInt("age"));
				stu.setImgUrl(jsonObj2.optString("imageurl"));
				
				JSONObject jsonObj3 =jsonObj2.getJSONObject("score"); 
				
				score = new Score();
				score.setMath(jsonObj3.optInt("math"));
				score.setEnglish(jsonObj3.getInt("english"));
				score.setChinese(jsonObj3.optInt("chinese"));
				stu.setScore(score);
				
				JSONArray array2 = jsonObj2.getJSONArray("telephone");
				
				int len2 = array2.length();
				String[] tes = new String[len2];
				
				for (int j = 0; j < len2; j++) {
					tes[j] = array2.optString(j);
				}
				stu.setTelephone(tes);
				
				list.add(stu);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }
    
    class GetAsyncTask extends AsyncTask<String, Integer, List<Student>>{

		@Override
		protected List<Student> doInBackground(String... params) {
			// TODO Auto-generated method stub
			if(NetTools.isNetLink(mContext)){
				String str = NetTools.getNetJSON(params[0]);
				Log.d(TAG, str);
				return getJson(str);
			}
			Log.d(TAG, "缃戠粶鏈摼鎺�");
			return null;
		}
		
		@Override
		protected void onPostExecute(List<Student> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result != null){
				JsonAdapter adapter = new JsonAdapter(result, mContext);
				mLv.setAdapter(adapter);
			}else{
				Log.d(TAG, "result id NuLL");
			}
		}
    	
    }
    
    
    
}
