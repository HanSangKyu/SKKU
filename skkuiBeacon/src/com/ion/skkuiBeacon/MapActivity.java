package com.ion.skkuiBeacon;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class MapActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		ImageView map = (ImageView) findViewById(R.id.inform_map);
		ImageLoader imageLoader = ImageLoader.getInstance();
		DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.maploading).showImageForEmptyUri(R.drawable.maperror).showImageOnFail(R.drawable.maperror).resetViewBeforeLoading(true).cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).build();
		imageLoader.displayImage(getIntent().getStringExtra("map").toString(), map, options);
	}
	
}
