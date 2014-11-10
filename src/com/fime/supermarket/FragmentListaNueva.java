package com.fime.supermarket;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentListaNueva extends ListFragment{
	
	private String[] titles;
	private Integer[] images;
	private JSONParser parser = new JSONParser();
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		JSONObject jsonObject = parser.makeHttpRequest("http://fimemarket.net46.net/fimemarket/categoria.php", "GET");
		titles = new String[jsonObject.length()];
		images = new Integer[jsonObject.length()];
		int resourceID;
		for (int i = 0; i <jsonObject.length();i++)
		{
			try {
				titles[i] = jsonObject.getString(""+i);
				resourceID = getResources().getIdentifier(jsonObject.getString(""+i), "drawable", "com.fime.supermarket");
				images[i] = resourceID;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
		getActivity().setTitle("Secciones:");
		CustomAdapter adapter = new CustomAdapter(getActivity(), titles, images);
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		switch (position)
		{
		case 1:
			
			FragmentListaSeleccion fragment = FragmentListaSeleccion.newInstance("Frutas");
			FragmentTransaction fmTrans = getActivity().getSupportFragmentManager().beginTransaction();
			fmTrans.replace(R.id.activity_main, fragment);
			fmTrans.addToBackStack(null);
			fmTrans.commit();
			break;
		
		}
	}

}
