package com.ion.skkuiBeacon;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class InformActivity extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inform);
		/*
		ImageView image = (ImageView) findViewById(R.id.image);
		try {
			URL url = new URL(getIntent().getStringExtra("imageURL").toString());
			URLConnection conn = url.openConnection();
			conn.connect();
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			Bitmap bm = BitmapFactory.decodeStream(bis);
			bis.close();
			image.setImageBitmap(bm);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		
		TextView name = (TextView) findViewById(R.id.buildingName);
		name.setText("건물이름 : " + getIntent().getStringExtra("name").toString());
		TextView explain = (TextView) findViewById(R.id.explain);
		explain.setText("설명 : " + getIntent().getStringExtra("explain").toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
