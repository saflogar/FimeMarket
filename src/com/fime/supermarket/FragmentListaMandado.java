package com.fime.supermarket;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.drm.ProcessedData;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentListaMandado extends Fragment{
	
	android.support.v4.app.FragmentManager fm;
	private static final String LISTA_ID = "com.fime.supermarket.FragmentListaMandado";
	private String[] items;
	private int catId;
	private int listaId;
	private ProgressDialog pDialog;
	public static FragmentListaMandado newInstance(int mListaId)
	{
		Bundle args = new Bundle();
		args.putSerializable(LISTA_ID, mListaId);
		FragmentListaMandado fragment = new FragmentListaMandado();
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		listaId = (Integer)getArguments().getSerializable(LISTA_ID);
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 fm = getActivity().getSupportFragmentManager();
		View v = inflater.inflate(R.layout.fragment_lista_mandado, container,false);
		Button button2 = (Button)v.findViewById(R.id.fragment_lista_mandado_button_2);
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				catId = 2;
				/*listDialog lDialog = listDialog.newInstance((Integer) getArguments().getSerializable(LISTA_ID), 2);
				lDialog.show(fm, "");*/
				new fetchProductos().execute();
			}
		});
		
Button button1 = (Button)v.findViewById(R.id.fragment_lista_mandado_button_1);
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				catId = 1;
			/*	listDialog lDialog = listDialog.newInstance((Integer) getArguments().getSerializable(LISTA_ID), 1);
				lDialog.show(fm, "");*/
				new fetchProductos().execute();

			}
		});
Button button3 = (Button)v.findViewById(R.id.fragment_lista_mandado_button_3);
		
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				catId = 3;
			/*	listDialog lDialog = listDialog.newInstance((Integer) getArguments().getSerializable(LISTA_ID), 3);
				lDialog.show(fm, "");*/
				new fetchProductos().execute();

			}
		});
Button button4 = (Button)v.findViewById(R.id.fragment_lista_mandado_button_4);
		
		button4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				catId = 4;
				/*listDialog lDialog = listDialog.newInstance((Integer) getArguments().getSerializable(LISTA_ID), 4);
				lDialog.show(fm, "");*/
				new fetchProductos().execute();

			}
		});
Button button5= (Button)v.findViewById(R.id.fragment_lista_mandado_button_5);
		
		button5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				catId = 5;
		/*		listDialog lDialog = listDialog.newInstance((Integer) getArguments().getSerializable(LISTA_ID), 5);
				lDialog.show(fm, "");*/
				new fetchProductos().execute();

				
			}
		});
		return v;
	}
	
	public class fetchProductos extends AsyncTask<Void, Void, Void>
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
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("lista_id",String.valueOf(listaId) ));
			parameters.add(new BasicNameValuePair("cat_id",String.valueOf(catId) ));

			JSONObject jsonObject = parser.makeHttpRequest("http://fimemarket.net46.net/fimemarket/listaDetalle.php", "GET",parameters);
			try {
				JSONArray objectArrayListas = jsonObject.getJSONArray("productos");
				items = new String[objectArrayListas.length()];
			for (int i = 0; i < objectArrayListas.length();i++)
			{
				try {
					items[i]=objectArrayListas.getJSONObject(i).getString("pro_nombre");
					
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
			pDialog.dismiss();
			listDialog lDialog = listDialog.newInstance(listaId, catId, items);
			lDialog.show(fm, "Seccion:");
		}
		
		
	}
}
