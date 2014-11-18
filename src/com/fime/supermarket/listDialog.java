package com.fime.supermarket;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class listDialog extends DialogFragment {
	String[] items ;
	ArrayList<Integer>   mSelectedItems = new ArrayList<Integer>();  // Where we track the selected items
	private static final String LISTA_ID = "com.fime.supermarket.FragmentListaMandado.LISTA_ID";
	private static final String CAT_ID = "com.fime.supermarket.FragmentListaMandado.CAT_ID";
	private static final String PRODUCTOS_NOMBRES = "com.fime.supermarket.FragmentListaMandado.PRODUCTOS_NOMBRES";
	private int listaId;
	private int catId;
	private AlertDialog.Builder builder;
	
	public static listDialog newInstance(int mListaId,int mCatId,String[] productosNombres)
	{
		Bundle args = new Bundle();
		args.putSerializable(PRODUCTOS_NOMBRES, productosNombres);
		args.putSerializable(LISTA_ID, mListaId);
		args.putSerializable(CAT_ID, mCatId);
		listDialog fragment = new listDialog();
		fragment.setArguments(args);
		return fragment;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		listaId=(Integer)getArguments().getSerializable(LISTA_ID);
		catId=(Integer)getArguments().getSerializable(CAT_ID);
		items = (String[])getArguments().getSerializable(PRODUCTOS_NOMBRES);
	}
@Override
@NonNull
public Dialog onCreateDialog(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	builder = new AlertDialog.Builder(getActivity());
	
    builder.setTitle("")
.setMultiChoiceItems(items, null,
             new DialogInterface.OnMultiChoiceClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which,
              boolean isChecked) {
          if (isChecked) {
              // If the user checked the item, add it to the selected items
              mSelectedItems.add(which);
          } else if (mSelectedItems.contains(which)) {
              // Else, if the item is already in the array, remove it 
              mSelectedItems.remove(Integer.valueOf(which));
          }
      }
  })
// Set the action buttons
  .setPositiveButton("", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int id) {
          // User clicked OK, so save the mSelectedItems results somewhere
          // or return them to the component that opened the dialog
          
      }
  })
  .setNegativeButton("", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int id) {
      }
  });
    return builder.create();

}
	
	
	}
