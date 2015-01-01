package com.ion.skkuiBeacon;

import com.radiusnetworks.ibeacon.IBeaconManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startActivity(new Intent(this, SplashActivity.class));
		setContentView(R.layout.activity_main);
		
		try {
			if (IBeaconManager.getInstanceForApplication(this).checkAvailability()) {
				startActivity(new Intent(this, MonitoringActivity.class));
				finish();
			}
		} catch (RuntimeException e) {
			final AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("지원되지 않는 기기");
			builder.setMessage("Bluetooth 4.0이 지원되지 않는 기기입니다.");
			builder.setPositiveButton(android.R.string.ok, null);
			builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
				@Override
				public void onDismiss(DialogInterface dialog) {
					finish();
					System.exit(0);
				}
			});
			builder.show();
		}
		
		
	}
	
/*	public void scan(View view) {
		this.startActivity(new Intent(this, MonitoringActivity.class));
	} */

/*	
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
	*/
	
}
