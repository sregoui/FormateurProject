package com.insta.instaschool.activity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;

import com.example.instaschool.R;
import com.example.instaschool.R.layout;
import com.example.instaschool.R.menu;
import com.insta.instaschool.activity.DetailPromoActivity.Mytask;
import com.insta.instaschool.adapter.AdapterEleves;
import com.insta.instaschool.adapter.AdapterNews;
import com.insta.instaschool.modele.Eleves;
import com.insta.instaschool.modele.News;
import com.insta.instaschool.modele.Utilisateur;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.TabSpec;

public class NewsActivity extends Activity {
	
	private Utilisateur user;
	private TextView nom;
	private int id_promo1;
	private ListView lclasse;
	private Context context;
	private ListView lc;
	private List<News> listNews;
	
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		
		user = (Utilisateur) getIntent().getSerializableExtra("Utilisateur");
	
		TabHost tabs = (TabHost) this.findViewById(R.id.tabhost); 
		tabs.setup();
		
		Resources res = getResources();
		
		TabSpec tspec1 = tabs.newTabSpec("Tab1"); 
		tspec1.setIndicator("Ecole",res.getDrawable(R.drawable.ic_launcher)); 
		tspec1.setContent(R.id.tab1); 
		tabs.addTab(tspec1); 
		TabSpec tspec2 = tabs.newTabSpec("Tab2"); 
		tspec2.setIndicator("Cours"); 
		tspec2.setContent(R.id.tab2); 
		tabs.addTab(tspec2); 
		TabSpec tspec3 = tabs.newTabSpec("Tab3"); 
		tspec3.setIndicator("Projets"); 
		tspec3.setContent(R.id.tab3); 
		tabs.addTab(tspec3);
		
		tabs.setCurrentTab(2);
		
		
		//----------------Affichage des news----------------------------- 
		context = getApplicationContext();
		lclasse = (ListView) findViewById(R.id.listView1); 
		
		Mytask task = new Mytask();
		task.execute();
		

	    lc = (ListView) findViewById(R.id.listView1);

	}
	
	
	public class Mytask extends AsyncTask<String, Void, StringBuffer>{

		List<News> listNews;
		
		@Override
		protected StringBuffer doInBackground(String... params) {
			// TODO Auto-generated method stub
			StringBuffer chaine = new StringBuffer("");
			AndroidHttpClient client = AndroidHttpClient.newInstance("user-Agent");
			
			try{
			URI uri = new URI("http://sebastienthibaud.com/android_school/traitements/listenews.php"); 
			HttpGet request = new HttpGet(); 
			request.setURI(uri); 
			HttpResponse response = client.execute(request); 
			
			client.close(); 
			// Get the response 
			InputStream inputStream = response.getEntity().getContent(); 
			BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream)); 
			String line = ""; 
			
			while ((line = rd.readLine()) != null) { 
				chaine.append(line); // 2 
				}
			}catch(Exception e){ 
				e.printStackTrace(); // 3 
			}
			finally { 
					
			}
			return chaine;
	}
		
		protected void onPostExecute(StringBuffer result) { 
			
			listNews = new ArrayList<News>();
			
			
				try{ //Traitement du JSON 
					
				JSONObject jsonObject = new JSONObject(result.toString()); 
				JSONObject classement = jsonObject.getJSONObject("INSTA");
				JSONArray datas = new JSONArray(classement.getString("News"));
				
				
				for(int i = 0; i< datas.length(); i++){ 
				JSONObject data = datas.getJSONObject(i); 
				int idNews = data.getInt("type");
				String titre = data.getString("titre"); 
				String sous_titre = data.getString("sous_titre");
				String news = data.getString("news"); 
       			String date = data.getString("date"); 
				News n = new News(titre, sous_titre, news, date, idNews);
				listNews.add(n); 
				}	
				
				AdapterNews adapter = new AdapterNews(context, listNews);
				lclasse.setAdapter(adapter);
				
				}catch(Exception e){ 
					Log.e("Erreur",e.getMessage());
				}finally { 
					
				} 
				
				lc.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
						// TODO Auto-generated method stub
//						intent = new Intent(NewsActivity.this,DetailEleveActivity.class);
//						intent.putExtra("Eleves", listEleves.get(position));
//						startActivity(intent);

					}
				});
				
				
			}
}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_promo, menu);
		menu.add(0, 1, 0, "Formateur"); 
		menu.add(0, 2, 0, "Promo");
		menu.add(0, 3, 0, "Réseau");
		menu.add(0, 4, 0, "Mon compte");
		//menu.add(0, 4, 0, "Twitter");
		return true;
	}
	
	public boolean onMenuItemSelected(int featureId, MenuItem item) { 
		switch (item.getItemId()) { 
		case 1: 
			intent = new Intent(NewsActivity.this,FormateurActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 2: 
			intent = new Intent(NewsActivity.this,ListPromoActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 3: 
			intent = new Intent(NewsActivity.this,ReseauSocActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 4: 
			intent = new Intent(NewsActivity.this,ProfilActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		default: return true; 
		} 
		} 
	}
