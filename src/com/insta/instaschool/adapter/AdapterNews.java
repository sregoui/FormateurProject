package com.insta.instaschool.adapter;

import java.util.List;

import com.insta.instaschool.adapter.AdapterEleves.ViewHolder;
import com.insta.instaschool.modele.News;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterNews extends BaseAdapter {
	
	List<News> n;
	LayoutInflater layoutInflater;
	
	

	public AdapterNews(Context context,List<News> n) {

		this.n = n;
		layoutInflater = layoutInflater.from(context);
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return n.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	static class ViewHolder {
		TextView titre;
		TextView news;
		TextView date;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(com.example.instaschool.R.layout.line3, null);
			holder = new ViewHolder();
			holder.titre = (TextView) convertView.findViewById(com.example.instaschool.R.id.titre);
			holder.news = (TextView) convertView.findViewById(com.example.instaschool.R.id.news);
			holder.date = (TextView) convertView.findViewById(com.example.instaschool.R.id.date);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
			
		}
		holder.titre.setText(n.get(position).getTitre());
		holder.news.setText(n.get(position).getNews());
		holder.date.setText(n.get(position).getDate());
		return convertView;
	}

}
