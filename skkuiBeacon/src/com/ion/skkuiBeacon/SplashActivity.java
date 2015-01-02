package com.ion.skkuiBeacon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.radiusnetworks.ibeacon.IBeaconManager;

@SuppressLint("HandlerLeak")
public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		try {
			final Boolean isSuccess = IBeaconManager.getInstanceForApplication(this).checkAvailability();

			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					if (isSuccess) {
						startActivity(new Intent(SplashActivity.this, MonitoringActivity.class));
					} else {
						startActivity(new Intent(SplashActivity.this, MainActivity.class));
					}
					finish();
				}
			}, 3000);
		} catch (Exception e) {
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
}
