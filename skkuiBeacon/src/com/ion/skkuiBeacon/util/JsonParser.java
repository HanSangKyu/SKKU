package com.ion.skkuiBeacon.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ion.skkuiBeacon.bean.MyItem;

public class JsonParser {

	private static final int arraysize = 2;
	ArrayList<MyItem> array = new ArrayList<MyItem>();
	
	public ArrayList<MyItem> getItem(){
		
		try{
			JSONObject jsonObject = new JSONObject("{beacon:[{key:\"1-2\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/63.JPG\", name:\"제1공학관\",explain:\"어쩌고저쩌고\"}," +
					"{key:\"1-4\",image:\"http://www.skku.edu/new_home/upload/images/campusmap/71.JPG\", name:\"제2공학관\",explain:\"건물번호 25\"}]}");
			JSONArray beacon = jsonObject.getJSONArray("beacon");
			
			for(int i=0;i<arraysize;i++)
			{
				array.add(new MyItem(beacon.getJSONObject(i).getString("key"), beacon.getJSONObject(i).getString("image"), beacon.getJSONObject(i).getString("name"), beacon.getJSONObject(i).getString("explain"), 100));
			}
		} catch(JSONException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return array;
	}
}
