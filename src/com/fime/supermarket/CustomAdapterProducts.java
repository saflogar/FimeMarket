package com.fime.supermarket;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class CustomAdapterProducts extends ArrayAdapter<String>{
	private final String[] titles;
	private final Integer[] imageId;
	private final Integer[] puntuactions;
	private final Activity context;
	public static ArrayList<Integer> checkedBoxesTotal = new ArrayList<Integer>();
	private ArrayList<Integer> checkedBoxes;
	public CustomAdapterProducts(Activity context,String[] titles,Integer[] imageId,Integer[] puntuactions)
	{
		super(context, R.layout.custom_view_products, titles);
		this.titles = titles;
		this.imageId= imageId;
		this.context= context;
		this.puntuactions = puntuactions;
		checkedBoxes = new ArrayList<Integer>();
		
	}
	
	public ArrayList<Integer> getCheckedBoxes()
	{
		return checkedBoxes;
		
	}
	
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.custom_view_products, null, true);
		final TextView txtTitle = (TextView) rowView.findViewById(R.id.custom_view_products_TextView_description);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.custom_view_products_ImageView_image);
		RatingBar rating = (RatingBar) rowView.findViewById(R.id.custom_view_products_RatingBar_puntuation);
		CheckBox cb = (CheckBox)rowView.findViewById(R.id.custom_view_products_CheckBox);
		final int pos = position;
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					checkedBoxes.add(pos);
				else
					checkedBoxes.remove(pos);
			}
		});
		txtTitle.setText(titles[position]);
		imageView.setImageResource(imageId[position]);
		rating.setEnabled(false);
		rating.setRating(puntuactions[position]);
		return rowView;
		}

}
