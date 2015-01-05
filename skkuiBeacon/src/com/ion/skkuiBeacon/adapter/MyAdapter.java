package com.ion.skkuiBeacon.adapter;

import java.util.ArrayList;

import com.ion.skkuiBeacon.R;
import com.ion.skkuiBeacon.bean.MyItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	Context context;
	int layoutRes;
	ArrayList<MyItem> list;
	LayoutInflater inflater;
	ImageLoader imageLoader;
	private DisplayImageOptions options;

	public MyAdapter(Context context, int layoutRes, ArrayList<MyItem> list) {
		this.context = context;
		this.layoutRes = layoutRes;
		this.list = list;
		inflater = LayoutInflater.from(context);
		imageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.loading).showImageForEmptyUri(R.drawable.error).showImageOnFail(R.drawable.error).resetViewBeforeLoading(true).cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).build();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		
		if (convertView != null) {
			holder = (ViewHolder) convertView.getTag();
		} else {
			holder = new ViewHolder(convertView = inflater.inflate(R.layout.tupple_monitoring, null));
		}

		imageLoader.displayImage(list.get(position).getImage(), holder.beacon_image, options);
		holder.beacon_name.setText(list.get(position).getName());
		
		Double accuracy = list.get(position).getAccuracy();
		if(accuracy == 100){
			holder.beacon_range.setText("");
		}
		else{
			holder.beacon_range.setText("거리 : " + Double.parseDouble(String.format("%.3f", list.get(position).getAccuracy())) + " m");
		}
		
		return convertView;
	}
	
	private class ViewHolder {
		private ImageView beacon_image;
		private TextView beacon_range;
		private TextView beacon_name;

		public ViewHolder(View view) {
			beacon_image = (ImageView) view.findViewById(R.id.building_image);
			beacon_name = (TextView) view.findViewById(R.id.building_name);
			beacon_range = (TextView) view.findViewById(R.id.BEACON_range);

			view.setTag(this);
		}
	}
}