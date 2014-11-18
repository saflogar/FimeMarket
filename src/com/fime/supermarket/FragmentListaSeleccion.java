package com.fime.supermarket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentListaSeleccion extends ListFragment{
	private String[] titles ;
	private Integer[] images;
	private Integer[] puntos;
	private String mTitle;
	private ProgressDialog pDialog;

	private static final String TAG = "FragmentListasSeleccion";
	public static final String TITLE = "com.fime.supermarket.fragmentlistaseleccion.title";
	
	private JSONObject jsonObjecto;
	
	public static FragmentListaSeleccion newInstance(String mtitle)
	{
		Bundle args = new Bundle();
		args.putSerializable(TITLE, mtitle);
		FragmentListaSeleccion fragment = new FragmentListaSeleccion();
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mTitle = (String)getArguments().getSerializable(TITLE);
		setRetainInstance(true);
		new FetchProducts().execute();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_lista_seleccion, container,false);
		getActivity().setTitle(mTitle);
		//TextView textView =(TextView) view.findViewById(R.id.fragment_lista_seleccion_TextView_Titulo);
		//textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fruits, 0, 0, 0);
		//textView.setText(mTitle);
		Button buttonCrear = (Button) view.findViewById(R.id.fragment_lista_seleccion_Button_Crear);
		
		buttonCrear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CustomAdapterProducts adapter = (CustomAdapterProducts)getListAdapter();
				ArrayList <Integer> checkedBoxes = adapter.getCheckedBoxes();
				for (int i = 0 ; i < checkedBoxes.size(); i++)
				{
					try {
						
						CustomAdapterProducts.checkedBoxesTotal.add(jsonObjecto.getJSONObject(""+checkedBoxes.get(i)).getInt("pro_id"));
					    Log.d(TAG, "Articulo"+jsonObjecto.getJSONObject(""+checkedBoxes.get(i)).getInt("pro_id")+" Agregado a la lista");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				//CustomAdapterProducts.checkedBoxesTotal = adapter.getCheckedBoxes();
				getFragmentManager().popBackStack();
			}
		});
		return view;
	}
	
	private class FetchProducts extends AsyncTask<Void,Void,Void>
	{
		private JSONParser parser = new JSONParser();
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Loading products. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("categoria",mTitle));
			JSONObject jsonObject = parser.makeHttpRequest("http://fimemarket.net46.net/fimemarket/productos.php", "GET",parameters);
			jsonObjecto = jsonObject;
			titles = new String[jsonObject.length()-1];
			images = new Integer[jsonObject.length()-1];
			puntos = new Integer[jsonObject.length()-1];
			int resourceID;
			//String puntuacion;
			Log.d(TAG, jsonObject.toString());
			
			try {
				if (jsonObject.getInt("success") == 1)
				{
					for (int i = 0; i <= jsonObject.length()-2;i++)
					{
						try {
							titles[i] = jsonObject.getJSONObject(""+i).getString("pro_descripcion");
							resourceID = getResources().getIdentifier(jsonObject.getJSONObject(""+i).getString("pro_imagen"), "drawable", "com.fime.supermarket");
							images[i] = resourceID;
							Log.d(TAG, jsonObject.getJSONObject(""+i).getString("pro_puntos"));
						//	puntuacion = jsonObject.getJSONObject(""+i).getString("pro_puntos");
							puntos[i] =  jsonObject.getJSONObject(""+i).getInt("pro_puntos");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						
						
					}
					
					
				}
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();
			CustomAdapterProducts adapter = new CustomAdapterProducts(getActivity(), titles, images,puntos);
			setListAdapter(adapter);
			
		}
		
		
		
		
	}
	
	



}
