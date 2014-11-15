package com.yahoo.akv.instagramviewer;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.instagramviewer.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yahoo.akv.instagramviewer.adapters.InstagramPhotosAdapter;
import com.yahoo.akv.instagramviewer.models.InstagramData;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class InstagramViewerActivity extends Activity {

	private static String CLIENT_ID = "7e2c3c5a2ca04a0d94f1eed8123fd011";
	ListView photoList;
	InstagramPhotosAdapter photosAdapter;
	
	private ArrayList<InstagramData> instagramData = new ArrayList<InstagramData>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instagram_viewer);
		photoList = (ListView) findViewById(R.id.lvPhotos);
		photosAdapter = new InstagramPhotosAdapter(this, instagramData);
		photoList.setAdapter(photosAdapter);
		fetchPopularPhotos();
		
	}
	private void fetchPopularPhotos() {
		// TODO Auto-generated method stub
		String url = "https://api.instagram.com/v1/media/popular?client_id="+CLIENT_ID; 
		makeApiCall(url);
	}
	public void makeApiCall(String url){
		instagramData.clear();
    	AsyncHttpClient client = new AsyncHttpClient();
    	client.get(url , new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				// TODO Auto-generated method stub
				Log.d("Debug", response.toString());
				try {
					JSONArray imageresultsarray = response.getJSONArray("data");
					instagramData.addAll(InstagramData.getInstagramData(imageresultsarray));
					photosAdapter.notifyDataSetChanged();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    }
}
