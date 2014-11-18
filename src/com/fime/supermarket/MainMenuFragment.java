package com.fime.supermarket;

import java.util.ArrayList;
import java.util.zip.Inflater;

 
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainMenuFragment extends Fragment{
//	ArrayList<String> list = new ArrayList<String>();
	
	String[] titles = {"Promociones","Mis Listas","Productos","Cuenta"};
	Integer[] imageId ={R.drawable.offer,R.drawable.icon_android,R.drawable.ic_productos,R.drawable.ic_action_person};
	Fragment fragment;
	@Override
	public void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//crearLista();

	}
	@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
		getActivity().setTitle("Menu");
		View v =inflater.inflate(R.layout.fragment_main_menu, container,false);
		Button buttonListas = (Button) v.findViewById(R.id.button_listas);
		buttonListas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 fragment = new FragmentListas();
				 android.support.v4.app.FragmentTransaction fmTrans = getActivity().getSupportFragmentManager().beginTransaction();
				 fmTrans.replace(R.id.activity_main, fragment);
				 fmTrans.addToBackStack(null);
				 fmTrans.commit();	
				
			}
		});
		Button buttonPromos = (Button) v.findViewById(R.id.button_ofertas);
		buttonPromos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 fragment = new FragmentPromociones();
				 android.support.v4.app.FragmentTransaction fmTrans = getActivity().getSupportFragmentManager().beginTransaction();
				 fmTrans.replace(R.id.activity_main, fragment);
				 fmTrans.addToBackStack(null);
				 fmTrans.commit();	
				
			}
		});
		
		Button buttonProductos = (Button) v.findViewById(R.id.button_productos);
		buttonProductos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 fragment = new FragmentProductos();
				 android.support.v4.app.FragmentTransaction fmTrans = getActivity().getSupportFragmentManager().beginTransaction();
					fmTrans.replace(R.id.activity_main, fragment);
					fmTrans.addToBackStack(null);
					fmTrans.commit();
				
			}
		});
		
		Button buttonCuenta = (Button) v.findViewById(R.id.button_cuenta);
		buttonCuenta.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 fragment = new FragmentCuenta();
				 android.support.v4.app.FragmentTransaction fmTrans = getActivity().getSupportFragmentManager().beginTransaction();
				 fmTrans.replace(R.id.activity_main, fragment);
				 fmTrans.addToBackStack(null);
				 fmTrans.commit();	
			}
		});
			return v;
		}
	
	/*
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
		
		
		
		
	}*/

	

}
