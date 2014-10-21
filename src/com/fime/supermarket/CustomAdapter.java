package com.fime.supermarket;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {
	
	private final String[] titles;
	private final Integer[] imageId;
	private final Activity context;
	
	public CustomAdapter(Activity context,String[] titles,Integer[] imageId)
	{
		super(context, R.layout.custom_view, titles);
		this.titles = titles;
		this.imageId= imageId;
		this.context= context;
	}
	
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.custom_view, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.TextView_custom_view);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.ImageView_custom_view);
		txtTitle.setText(titles[position]);
		imageView.setImageResource(imageId[position]);
		return rowView;
		}
}
