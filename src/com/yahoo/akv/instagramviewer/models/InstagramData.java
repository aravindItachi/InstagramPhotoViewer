package com.yahoo.akv.instagramviewer.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InstagramData {

	public String username;
	public int imageHeight;
	public String caption;
	public String imageUrl;
	public int likesCount;

	public InstagramData(String username, int imageHeight, String caption,
			String imageUrl, int likesCount) {
		this.username = username;
		this.imageHeight = imageHeight;
		this.caption = caption;
		this.imageUrl = imageUrl;
		this.likesCount = likesCount;
	}

	public static ArrayList<InstagramData> getInstagramData(
			JSONArray resultArray) {
		ArrayList<InstagramData> instagramData = new ArrayList<InstagramData>();
		for (int i = 0; i < resultArray.length(); i++) {
			try {
				JSONObject imageobj = resultArray.getJSONObject(i);
				String username = imageobj.getJSONObject("user").getString(
						"username");
				String caption = null;
				if (imageobj.getJSONObject("caption") != null) {
					caption = imageobj.getJSONObject("caption").getString(
							"text");
				} else {
					caption = "No caption";
				}
				int imageHeight = imageobj.getJSONObject("images")
						.getJSONObject("standard_resolution").getInt("height");
				String imageUrl = imageobj.getJSONObject("images")
						.getJSONObject("standard_resolution").getString("url");
				int likesCount = 0;
				if (imageobj.getJSONObject("likes") != null) {
					likesCount = imageobj.getJSONObject("likes")
							.getInt("count");
				}
				instagramData.add(new InstagramData(username, imageHeight,
						caption, imageUrl, likesCount));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instagramData;
	}

}
