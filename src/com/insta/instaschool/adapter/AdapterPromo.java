package com.insta.instaschool.adapter;

import java.util.List;

import com.insta.instaschool.modele.Promo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterPromo extends BaseAdapter {

	List<Promo> p;
	LayoutInflater layoutInflater;
	
public AdapterPromo(Context context,List<Promo> p) {
		this.p = p;
		layoutInflater = layoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return p.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	
	static class ViewHolder {
		TextView nom;
		TextView ad;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(com.example.instaschool.R.layout.line, null);
			holder = new ViewHolder();
			holder.nom = (TextView) convertView.findViewById(com.example.instaschool.R.id.nom);
			holder.ad = (TextView) convertView.findViewById(com.example.instaschool.R.id.ad);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
			
		}
		holder.nom.setText(p.get(position).getPromo());
		holder.ad.setText(p.get(position).getAnnee_debut());
		return convertView;
	}

}
