package com.fime.supermarket;

import java.util.ArrayList;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class FragmentListas extends ListFragment {
	
	private ArrayList<String> list = new ArrayList<String>();
	private View previusSelection;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setHasOptionsMenu(true);
		list.add("Lista Num 1");
		list.add("Lista Num 2");
		list.add("lista Num 3");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,list);
		
		setListAdapter(adapter);
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) 
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_listas, container,false);
		ImageButton addButton = (ImageButton) view.findViewById(R.id.imageButton_add);
		addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Fragment fragment = new FragmentListaNueva();
				FragmentTransaction fmTrans = getActivity().getSupportFragmentManager().beginTransaction();
				fmTrans.replace(R.id.activity_main, fragment);
				fmTrans.addToBackStack(null);
				fmTrans.commit();
			}
		});
		
		
		
		
		return view;
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
		 v.setSelected(true);
		 v.setBackgroundColor(getResources().getColor(R.color.pressed_color));
		 if (previusSelection != null)
		 previusSelection.setBackgroundColor(getResources().getColor(R.color.default_color));
		 previusSelection = v;
	}
	
	
	private class FetchFragmentListas extends AsyncTask<Void, Void, Void>
	{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			
			return null;
		}
		
		
	}
	
	
	

	

}
