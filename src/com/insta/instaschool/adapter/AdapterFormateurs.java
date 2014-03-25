package com.insta.instaschool.adapter;

import java.util.List;

import com.insta.instaschool.adapter.AdapterEleves.ViewHolder;
import com.insta.instaschool.modele.Eleves;
import com.insta.instaschool.modele.Formateurs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterFormateurs extends BaseAdapter {
	
	List<Formateurs> f;
	LayoutInflater layoutInflater;
	
	public AdapterFormateurs(Context context,List<Formateurs> f) {
		this.f = f;
		layoutInflater = layoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return f.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	static class ViewHolder {
		TextView nom;
		TextView prenom;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewHolder holder;
		
		if (convertView == null) {
			convertView = layoutInflater.inflate(com.example.instaschool.R.layout.line2, null);
			holder = new ViewHolder();
			holder.nom = (TextView) convertView.findViewById(com.example.instaschool.R.id.nom);
			holder.prenom = (TextView) convertView.findViewById(com.example.instaschool.R.id.prenom);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
			
		}
		holder.nom.setText(f.get(position).getNom());
		holder.prenom.setText(f.get(position).getPrenom());
		return convertView;
	
	}

}
