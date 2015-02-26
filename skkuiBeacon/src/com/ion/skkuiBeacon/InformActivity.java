package com.ion.skkuiBeacon;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InformActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inform);
		
		ImageView image = (ImageView) findViewById(R.id.inform_image);
		ImageLoader imageLoader = ImageLoader.getInstance();
		DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.loading).showImageForEmptyUri(R.drawable.error).showImageOnFail(R.drawable.error).resetViewBeforeLoading(true).cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).build();
		
		imageLoader.displayImage(getIntent().getStringExtra("image").toString(), image, options);
		
		TextView name = (TextView) findViewById(R.id.inform_name);
		name.setText(getIntent().getStringExtra("name").toString());
		TextView explain = (TextView) findViewById(R.id.inform_explain);
		explain.setText(getIntent().getStringExtra("explain").toString());
	}
	
	public void onMapButton(View v) {
		Intent newintent = new Intent(InformActivity.this, MapActivity.class);
		newintent.putExtra("map", getIntent().getStringExtra("map").toString());
		startActivity(newintent);
	}
}
