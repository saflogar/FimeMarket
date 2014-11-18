package com.fime.supermarket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

@SuppressLint("UseSparseArrays")
public class FragmentListas extends ListFragment {
	private static final String TAG = "com.fime.supermarket.FragmentListas";
	private ArrayList<String> list = new ArrayList<String>();
	private ProgressDialog pDialog ;
	private HashMap<Integer, String> listas = new HashMap<Integer, String>();
	private int listIdEliminar;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) 
	{
		super.onCreateView(inflater, container, savedInstanceState);
		list.clear();
		new FetchListas().execute();
		View view = inflater.inflate(R.layout.fragment_listas, container,false);
		getActivity().setTitle("Listas");

		ListView listView = (ListView) view.findViewById(android.R.id.list);
		registerForContextMenu(listView);
	//	ImageButton addButton = (ImageButton) view.findViewById(R.id.imageButton_add);
	/*	addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Fragment fragment = new FragmentListaNueva();
				FragmentTransaction fmTrans = getActivity().getSupportFragmentManager().beginTransaction();
				fmTrans.replace(R.id.activity_main, fragment);
				fmTrans.addToBackStack(null);
				fmTrans.commit();
			}
		});*/
		
		
		
		
		return view;
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		
		
		super.onCreateContextMenu(menu, v, menuInfo);
		getActivity().getMenuInflater().inflate(R.menu.lista_context_menu,menu);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo contextMenuInfo=(AdapterContextMenuInfo)item.getMenuInfo();
		int position = contextMenuInfo.position;
		ArrayAdapter<String> adapter = (ArrayAdapter<String>)getListAdapter();
		int listId=0;
		String itemString = adapter.getItem(position);
		for (Map.Entry<Integer, String> e : listas.entrySet())
		{
			if (e.getValue().equals(itemString))
			{
				 listId = e.getKey();
				 Log.d(TAG, "id a eliminar="+listId);
			}
		}
		adapter.remove(adapter.getItem(position));
		listIdEliminar = listId;
		
		new DeleteLista().execute();
		return super.onContextItemSelected(item);
	}
	@Override
	public void onCreateOptionsMenu(Menu menu,MenuInflater inflater)
	{
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.main_menu, menu);
		
	}
	
	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		getListView().setSelector(R.drawable.s);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		ArrayAdapter<String> adapter = (ArrayAdapter<String>)getListAdapter();
		int listId=0;
		String itemString = adapter.getItem(position);
		for (Map.Entry<Integer, String> e : listas.entrySet())
		{
			if (e.getValue().equals(itemString))
			{
				 listId = e.getKey();
				 Log.d(TAG, "id a enviar a listaMandado="+listId);
			}
		}
		
		Fragment fragment =  FragmentListaMandado.newInstance(listId);
		FragmentTransaction fmTrans = getActivity().getSupportFragmentManager().beginTransaction();
		fmTrans.replace(R.id.activity_main, fragment);
		fmTrans.addToBackStack(null);
		fmTrans.commit();
		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.menu_item_add_list:	
			Fragment fragment = new FragmentListaNueva();
			FragmentTransaction fmTrans = getActivity().getSupportFragmentManager().beginTransaction();
			fmTrans.replace(R.id.activity_main, fragment);
			fmTrans.addToBackStack(null);
			fmTrans.commit();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	
/*	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		 v.setSelected(true);
		 v.setBackgroundColor(getResources().getColor(R.color.pressed_color));
		 if (previusSelection != null)
		 previusSelection.setBackgroundColor(getResources().getColor(R.color.default_color));
		 previusSelection = v;
	}*/
	
	
	
	private class FetchListas extends AsyncTask<Void,Void,Void>
	{
		private JSONParser parser = new JSONParser();
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Loading. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONObject jsonObject = parser.makeHttpRequest("http://fimemarket.net46.net/fimemarket/lista.php", "GET");
			try {
				JSONArray objectArrayListas = jsonObject.getJSONArray("listas");
			
			for (int i = 0; i < objectArrayListas.length();i++)
			{
				try {
					listas.put(objectArrayListas.getJSONObject(i).getInt("lista_id"), objectArrayListas.getJSONObject(i).getString("lista_titulo"));
					list.add(objectArrayListas.getJSONObject(i).getString("lista_titulo"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(ClassCastException e)
				{
					e.printStackTrace();
				}
			}} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			
			super.onPostExecute(result);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,list);
			setListAdapter(adapter);
			pDialog.dismiss();
		}
	}
	
	
	private class DeleteLista extends AsyncTask<Void, Void, Void>
	{
		JSONParser parser = new JSONParser();
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Loading. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("lista_id", String.valueOf(listIdEliminar)));
			JSONObject jsonObject = parser.makeHttpRequest("http://fimemarket.net46.net/fimemarket/lista.php", "POST",parameters);
			Log.d(TAG, jsonObject.toString());
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			
			super.onPostExecute(result);
			

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,list);
			setListAdapter(adapter);
			pDialog.dismiss();
		}
	}	

	

}
