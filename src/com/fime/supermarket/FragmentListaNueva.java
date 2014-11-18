package com.fime.supermarket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentListaNueva extends ListFragment{
	private ProgressDialog pDialog;
	private String listName;
	private String[] titles;
	private Integer[] images;
	private static final String TAG ="com.fime.fimemarket.FragmentListaNueva";
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		new FetchCategories().execute();
	
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
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getActivity().setTitle("Secciones:");

		View v = inflater.inflate(R.layout.fragment_lista_nueva, container,false);
		Button createButton = (Button)v.findViewById(R.id.fragment_lista_nueva_Button_Crear);
		createButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

				alert.setTitle("Title");
				alert.setMessage("Ingrese el nombre de la lista");

				// Set an EditText view to get user input 
				final EditText input = new EditText(getActivity());
				alert.setView(input);

				alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
				  listName = input.getText().toString();
				  CustomAdapterProducts.checkedBoxesTotal.clear();
				  new CreateNewList().execute();

				  // Do something with value!
				  }
				});

				alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				  public void onClick(DialogInterface dialog, int whichButton) {
				    // Canceled.
					  CustomAdapterProducts.checkedBoxesTotal.clear();
				  }
				});

				alert.show();
				
				
			}
		});
		return v;
	}
	
	private class FetchCategories extends AsyncTask<Void,Void,Void>
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
			JSONObject jsonObject = parser.makeHttpRequest("http://fimemarket.net46.net/fimemarket/categoria.php", "GET");
			titles = new String[jsonObject.length()-1];
			images = new Integer[jsonObject.length()-1];
			int resourceID;
			for (int i = 0; i <=jsonObject.length()-2;i++)
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
			pDialog.dismiss();
		}
	}
	
	private class CreateNewList extends AsyncTask<Void, Void, Void>
	{
	

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			//we gate the date
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
			Calendar calendar = Calendar.getInstance();
			String date = dateFormat.format(calendar.getTime());
			
			JSONParser parser = new JSONParser();
			JSONArray objectProductos = new JSONArray();
			JSONObject objectParameters = new JSONObject();
			JSONObject objectLista = new JSONObject();
			for (int i = 0; i < CustomAdapterProducts.checkedBoxesTotal.size();i++)
			{
				JSONObject objectProduct = new JSONObject();
				try {
					objectProduct.put("pro_id", CustomAdapterProducts.checkedBoxesTotal.get(i));
					objectProductos.put(objectProduct);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			try {
				objectLista.put("lista_titulo", listName);
				objectLista.put("lista_fech_cr", date);
				objectParameters.put("productos", objectProductos);
				objectParameters.put("lista", objectLista);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.d(TAG, "Message Sended="+objectParameters.toString());
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("json", objectParameters.toString()));
			JSONObject jsonResult = parser.makeHttpRequest("http://fimemarket.net46.net/fimemarket/lista.php",
					"POST", parameters);
			Log.d(TAG, "Response="+jsonResult.toString());
			
			return null;
		}
		
	
		
	}

}
