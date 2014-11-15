package com.yahoo.akv.instagramviewer.adapters;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.instagramviewer.R;
import com.squareup.picasso.Picasso;

import com.yahoo.akv.instagramviewer.models.InstagramData;

public class InstagramPhotosAdapter extends ArrayAdapter<InstagramData> {

	public InstagramPhotosAdapter(Context context, List<InstagramData> objects) {
		super(context,R.layout.instagramphoto, objects);
		// TODO Auto-generated constructor stub
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		InstagramData photo = getItem(position);
		if(convertView == null){
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.instagramphoto, parent, false);
		}
			TextView username = (TextView) convertView.findViewById(R.id.tvUsername);
			ImageView imgView = (ImageView) convertView.findViewById(R.id.ivImage);
			TextView caption  =  (TextView) convertView.findViewById(R.id.tvcaption);
			TextView likes  =  (TextView) convertView.findViewById(R.id.tvlikesCount);
			imgView.setImageResource(0);
			imgView.getLayoutParams().height = photo.imageHeight;
			username.setText(photo.username);
			likes.setText(photo.likesCount + " Likes");
			caption.setText(Html.fromHtml(photo.caption));
			Picasso.with(getContext()).load(photo.imageUrl).into(imgView);	
		return convertView;
	}
}
