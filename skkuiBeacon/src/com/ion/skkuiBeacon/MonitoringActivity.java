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
import android.widget.ListView;

import com.ion.skkuiBeacon.adapter.MyAdapter;
import com.ion.skkuiBeacon.bean.MyItem;
import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconConsumer;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.MonitorNotifier;
import com.radiusnetworks.ibeacon.RangeNotifier;
import com.radiusnetworks.ibeacon.Region;

public class MonitoringActivity extends Activity implements IBeaconConsumer {
	protected static final String TAG = "MonitoringActivity";

	private ListView list = null;
	private MyAdapter adapter = null;
	private ArrayList<IBeacon> arrayL = new ArrayList<IBeacon>();
	private ArrayList<MyItem> array = new ArrayList<MyItem>();
	private LayoutInflater inflater;

	private BeaconServiceUtility beaconUtill = null;
	private IBeaconManager iBeaconManager = IBeaconManager.getInstanceForApplication(this);
	JSONArray beacon;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_monitor);
		
		try{
			JSONObject jsonObject = new JSONObject("{beacon:[{key:\"1-2\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/61.JPG\", name:\"제1공학관\",explain:\"어쩌고저쩌고\"}," +
					"{key:\"1-4\",image:\"http://www.skku.ac.kr/new_home/upload/images/campusmap/71.JPG\", name:\"제2공학관\",explain:\"건물번호 25\"}]}");
			beacon = jsonObject.getJSONArray("beacon");
			
			for(int i=0;i<2;i++)
			{
				array.add(new MyItem(beacon.getJSONObject(i).getString("key"), beacon.getJSONObject(i).getString("image"), beacon.getJSONObject(i).getString("name"), beacon.getJSONObject(i).getString("explain"), 0));
			}
		} catch(JSONException e){
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		
		beaconUtill = new BeaconServiceUtility(this);
		list = (ListView) findViewById(R.id.list);
		adapter = new MyAdapter(this, R.layout.tupple_monitoring, array);
		list.setAdapter(adapter);
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		list.setOnItemClickListener(new OnItemClickListener() {	 
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent newintent = new Intent(MonitoringActivity.this, InformActivity.class);
				newintent.putExtra("image", array.get(position).getImage());
				newintent.putExtra("name", array.get(position).getName());
				newintent.putExtra("explain", array.get(position).getExplain());
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
				
				String key;
				
				for(int j=0;j<array.size();j++){
					array.get(j).setAccuracy(0);
				}
				
				for(int i=0;i<arrayL.size();i++){
					key = arrayL.get(i).getMajor() + "-" + arrayL.get(i).getMinor();
					for(int j=0;j<array.size();j++){
						if(array.get(j).getKey().equals(key)){
							array.get(j).setAccuracy(arrayL.get(i).getAccuracy());
						}
					}
				}
				
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

}