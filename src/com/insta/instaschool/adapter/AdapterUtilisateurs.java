package com.insta.instaschool.adapter;

import java.util.List;

import com.example.instaschool.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterUtilisateurs extends BaseAdapter {

	private LayoutInflater inflater;
	
	
	public AdapterUtilisateurs(Context context) {
		
		inflater = LayoutInflater.from(context); 

	}
	
	static class ViewHolder{
		TextView Email;
		TextView Password;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub

		return convertView;
	}

}
