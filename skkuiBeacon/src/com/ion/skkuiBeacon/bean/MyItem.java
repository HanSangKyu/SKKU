package com.ion.skkuiBeacon.bean;

public class MyItem {
	String Key;
	String Image;
	String Map;
	String Name;
	String Explain;
	double Accuracy;
	
	public MyItem(String Key, String Image, String Map, String Name, String Explain, double Accuracy) {
		this.Key = Key;
		this.Image = Image;
		this.Map = Map;
		this.Name = Name;
		this.Explain = Explain;
		this.Accuracy = Accuracy;
	}
	
	public String getKey(){
		return Key;
	}
	
	public String getImage(){
		return Image;
	}
	
	public String getMap(){
		return Map;
	}
	
	public String getName(){
		return Name;
	}
	
	public String getExplain(){
		return Explain;
	}
	
	public double getAccuracy(){
		return Accuracy;
	}
	
	public void setAccuracy(double Accuracy){
		this.Accuracy = Accuracy;
	}
}