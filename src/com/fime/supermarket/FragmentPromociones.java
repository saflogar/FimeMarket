package com.fime.supermarket;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentPromociones extends Fragment{
	
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) 
	{
		View view = inflater.inflate(R.layout.fragment_promociones, container,false);
		return view;
	}

}
