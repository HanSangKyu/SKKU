package com.ion.skkuiBeacon;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconConsumer;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.MonitorNotifier;
import com.radiusnetworks.ibeacon.RangeNotifier;
import com.radiusnetworks.ibeacon.Region;

public class MonitoringActivity extends Activity implements IBeaconConsumer {
	protected static final String TAG = "MonitoringActivity";

	private ListView list = null;
	private BeaconAdapter adapter = null;
	private ArrayList<IBeacon> arrayL = new ArrayList<IBeacon>();
	private LayoutInflater inflater;

	private BeaconServiceUtility beaconUtill = null;
	private IBeaconManager iBeaconManager = IBeaconManager.getInstanceForApplication(this);
	JSONArray beacon;
	private String buildingName = null;
	private String imageURL = null;
	private String explain = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_monitor);
		try{
			JSONObject jsonObject = new JSONObject("{beacon:[{key:\"1-2\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/61.JPG\", name:\"제1공학관\",explain:\"어쩌고저쩌고\"}," +
					"{key:\"1-4\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/71.JPG\", name:\"제2공학관\",explain:\"건물번호 25\"}]}");
			beacon = jsonObject.getJSONArray("beacon");
		} catch(JSONException e){
			
		}
		
		beaconUtill = new BeaconServiceUtility(this);
		list = (ListView) findViewById(R.id.list);
		adapter = new BeaconAdapter();
		list.setAdapter(adapter);
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		list.setOnItemClickListener(new OnItemClickListener() {	 
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent newintent = new Intent(MonitoringActivity.this, InformActivity.class);
				//newintent.putExtra("key", arrayL.get(position).getMajor() + "-" + arrayL.get(position).getMinor());
				newintent.putExtra("buildingName", buildingName);
				newintent.putExtra("explain", explain);
				newintent.putExtra("imageURL", imageURL);
				startActivity(newintent);
			}
		} );
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onStart() {
		super.onStart();
		beaconUtill.onStart(iBeaconManager, this);
	}

	@Override
	protected void onStop() {
		beaconUtill.onStop(iBeaconManager, this);
		super.onStop();
	}

	@Override
	public void onIBeaconServiceConnect() {

		iBeaconManager.setRangeNotifier(new RangeNotifier() {
			@Override
			public void didRangeBeaconsInRegion(Collection<IBeacon> iBeacons, Region region) {

				arrayL.clear();
				arrayL.addAll((ArrayList<IBeacon>) iBeacons);
				adapter.notifyDataSetChanged();
			}

		});

		iBeaconManager.setMonitorNotifier(new MonitorNotifier() {
			@Override
			public void didEnterRegion(Region region) {
				Log.e("BeaconDetactorService", "didEnterRegion");
				// logStatus("I just saw an iBeacon for the first time!");
			}

			@Override
			public void didExitRegion(Region region) {
				Log.e("BeaconDetactorService", "didExitRegion");
				// logStatus("I no longer see an iBeacon");
			}

			@Override
			public void didDetermineStateForRegion(int state, Region region) {
				Log.e("BeaconDetactorService", "didDetermineStateForRegion");
				// logStatus("I have just switched from seeing/not seeing iBeacons: " + state);
			}

		});

		try {
			iBeaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		try {
			iBeaconManager.startMonitoringBeaconsInRegion(new Region("myMonitoringUniqueId", null, null, null));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private class BeaconAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			if (arrayL != null && arrayL.size() > 0)
				return arrayL.size();
			else
				return 0;
		}

		@Override
		public IBeacon getItem(int arg0) {
			return arrayL.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			try {
				ViewHolder holder;
				
				String beaconKey = arrayL.get(position).getMajor() + "-" + arrayL.get(position).getMinor();
				buildingName = null;
				imageURL = null;
				for(int i=0;i<2;i++)
				{					
					if(beaconKey.equals(beacon.getJSONObject(i).getString("key")))
					{
						buildingName=beacon.getJSONObject(i).getString("name");
						imageURL=beacon.getJSONObject(i).getString("image");
						explain=beacon.getJSONObject(i).getString("explain");
					}
				}
				if (convertView != null) {
					holder = (ViewHolder) convertView.getTag();
				} else {
					holder = new ViewHolder(convertView = inflater.inflate(R.layout.tupple_monitoring, null));
				}
				if (arrayL.get(position).getProximityUuid() != null)
		//		holder.beacon_uuid.setText("UUID: " + arrayL.get(position).getProximityUuid());
		//		holder.beacon_minor.setText("Key : " + arrayL.get(position).getMajor() + "-" + arrayL.get(position).getMinor());
			
				holder.beacon_name.setText(buildingName);
				holder.beacon_range.setText("거리 : " + Double.parseDouble(String.format("%.3f", arrayL.get(position).getAccuracy())) + " m");

			} catch (Exception e) {
				e.printStackTrace();
			}

			return convertView;
		}

		private class ViewHolder {
	//		private TextView beacon_uuid;
	//		private TextView beacon_major;
			//private TextView beacon_minor;
			private TextView beacon_range;
			private TextView beacon_name;

			public ViewHolder(View view) {
	//			beacon_uuid = (TextView) view.findViewById(R.id.BEACON_uuid);
	//			beacon_major = (TextView) view.findViewById(R.id.BEACON_major);
				beacon_name = (TextView) view.findViewById(R.id.building_name);
				//beacon_minor = (TextView) view.findViewById(R.id.BEACON_minor);
				beacon_range = (TextView) view.findViewById(R.id.BEACON_range);

				view.setTag(this);
			}
		}

	}

}