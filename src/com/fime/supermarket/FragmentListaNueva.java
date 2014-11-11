package com.fime.supermarket;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentListaNueva extends ListFragment{
	
	private String[] titles;
	private Integer[] images;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		new FetchCategories().execute();
		getActivity().setTitle("Secciones:");
	
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		FragmentListaSeleccion fragment = FragmentListaSeleccion.newInstance(getListAdapter().getItem(position).toString());
		FragmentTransaction fmTrans = getActivity().getSupportFragmentManager().beginTransaction();
		fmTrans.replace(R.id.activity_main, fragment);
		fmTrans.addToBackStack(null);
		fmTrans.commit();
	
	
	}
	
	private class FetchCategories extends AsyncTask<Void,Void,Void>
	{
		private JSONParser parser = new JSONParser();

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONObject jsonObject = parser.makeHttpRequest("http://fimemarket.net46.net/fimemarket/categoria.php", "GET");
			titles = new String[jsonObject.length()-1];
			images = new Integer[jsonObject.length()-1];
			int resourceID;
			for (int i = 0; i <=jsonObject.length()-1;i++)
			{
				try {
					titles[i] = jsonObject.getJSONObject(""+i).getString("cat_nombre");
					resourceID = getResources().getIdentifier(jsonObject.getJSONObject(""+i).getString("cat_imagen"), "drawable", "com.fime.supermarket");
					images[i] = resourceID;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(ClassCastException e)
				{
					e.printStackTrace();
				}
				
				
				
				
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			CustomAdapter adapter = new CustomAdapter(getActivity(), titles, images);
			setListAdapter(adapter);
		}
		
		
		
		
	}

}
