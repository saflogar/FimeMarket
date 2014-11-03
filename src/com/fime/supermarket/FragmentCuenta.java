package com.fime.supermarket;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FragmentCuenta extends Fragment{
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) 
	{
		View view = inflater.inflate(R.layout.fragment_cuenta, container,false);
		return view;
	}

}
