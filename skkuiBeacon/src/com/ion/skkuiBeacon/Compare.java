package com.ion.skkuiBeacon;

import java.util.Comparator;

import com.ion.skkuiBeacon.bean.MyItem;

public class Compare implements Comparator<MyItem> {
	
	@Override
	public int compare(MyItem arg0, MyItem arg1){
		return Double.compare(arg0.getAccuracy(), arg1.getAccuracy());
	}
	
}