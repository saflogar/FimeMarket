package com.fime.supermarket;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentListaSeleccion extends ListFragment{
	private String[] titles = {};
	private Integer[] images={};
	public static final String TITLE = "com.fime.supermarket.fragmentlistaseleccion.title";
	
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
		CustomAdapter adapter = new CustomAdapter(getActivity(), titles, images);
		
		setListAdapter(adapter);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		
		View view = inflater.inflate(R.layout.fragment_lista_seleccion, container,false);
		TextView textView =(TextView) view.findViewById(R.id.fragment_lista_seleccion_TextView_Titulo);
		textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.fruits, 0, 0, 0);
		textView.setText((String)getArguments().getSerializable(TITLE));
		
		return view;
	}
	


}
