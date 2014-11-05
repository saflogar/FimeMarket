package com.fime.supermarket;

import java.util.ArrayList;
import java.util.zip.Inflater;

 
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainMenuFragment extends ListFragment{
//	ArrayList<String> list = new ArrayList<String>();
	
	String[] titles = {"Promociones","Mis Listas","Productos","Cuenta"};
	Integer[] imageId ={R.drawable.offer,R.drawable.icon_android,R.drawable.ic_productos,R.drawable.ic_action_person};
	Fragment fragment;
	@Override
	public void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getActivity().setTitle("Menu");
		//crearLista();
		CustomAdapter adapter = new CustomAdapter(getActivity(), titles, imageId);
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,list);
		setListAdapter(adapter);

	}
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id)
	{
		
		if (getListAdapter().getItem(position).equals("Productos"))
		{
		 fragment = new FragmentProductos();
		 android.support.v4.app.FragmentTransaction fmTrans = getActivity().getSupportFragmentManager().beginTransaction();
			fmTrans.replace(R.id.activity_main, fragment);
			fmTrans.addToBackStack(null);
			fmTrans.commit();
			
		} 
		if (getListAdapter().getItem(position).equals("Mis Listas"))
		{
			 
		 fragment = new FragmentListas();
		 android.support.v4.app.FragmentTransaction fmTrans = getActivity().getSupportFragmentManager().beginTransaction();
		 fmTrans.replace(R.id.activity_main, fragment);
		 fmTrans.addToBackStack(null);
		 fmTrans.commit();	
		}
		
		if (getListAdapter().getItem(position).equals("Promociones"))
		{
			 
		 fragment = new FragmentPromociones();
		 android.support.v4.app.FragmentTransaction fmTrans = getActivity().getSupportFragmentManager().beginTransaction();
		 fmTrans.replace(R.id.activity_main, fragment);
		 fmTrans.addToBackStack(null);
		 fmTrans.commit();	
		}
		
		if (getListAdapter().getItem(position).equals("Cuenta"))
		{
			 
		 fragment = new FragmentCuenta();
		 android.support.v4.app.FragmentTransaction fmTrans = getActivity().getSupportFragmentManager().beginTransaction();
		 fmTrans.replace(R.id.activity_main, fragment);
		 fmTrans.addToBackStack(null);
		 fmTrans.commit();	
		}
		
		
		
		
	}

	

}
