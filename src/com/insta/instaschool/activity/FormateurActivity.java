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
import com.insta.instaschool.activity.ListPromoActivity.Mytask;
import com.insta.instaschool.adapter.AdapterFormateurs;
import com.insta.instaschool.adapter.AdapterPromo;
import com.insta.instaschool.modele.Formateurs;
import com.insta.instaschool.modele.Promo;
import com.insta.instaschool.modele.Utilisateur;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.AdapterView.OnItemClickListener;

public class FormateurActivity extends Activity {
	
	private Utilisateur user;
	private ListView lclasse;
	private Context context;
	private ListView lc;
	private Intent intent;
	private List<Formateurs> listFormateurs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formateur);
		
		user = (Utilisateur) getIntent().getSerializableExtra("Utilisateur");
		
		context = getApplicationContext();
		lclasse = (ListView) findViewById(R.id.listView1); 
		
		Mytask task = new Mytask();
		task.execute();
		

	    lc = (ListView) findViewById(R.id.listView1);


	}

	public class Mytask extends AsyncTask<String, Void, StringBuffer>{

		List<Formateurs> listFormateur;
		
		@Override
		protected StringBuffer doInBackground(String... params) {
			// TODO Auto-generated method stub
			StringBuffer chaine = new StringBuffer("");
			AndroidHttpClient client = AndroidHttpClient.newInstance("user-Agent");
			
			try{
			URI uri = new URI("http://sebastienthibaud.com/android_school/traitements/listeformateurs.php"); 
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
			
			listFormateurs = new ArrayList<Formateurs>();
			
			ProgressBar progressbar = (ProgressBar)findViewById(R.id.progressBar);
			
			progressbar.setVisibility(View.VISIBLE);
			
				try{ //Traitement du JSON 
					
				JSONObject jsonObject = new JSONObject(result.toString()); 
				JSONObject classement = jsonObject.getJSONObject("INSTA");
				JSONArray datas = new JSONArray(classement.getString("Formateurs"));
				
				for(int i = 0; i< datas.length(); i++){ 
				JSONObject data = datas.getJSONObject(i); 
				String id = data.getString("id"); 
				String noms = data.getString("nom"); 
				String prenoms = data.getString("prenom"); 
				String date_naissance = data.getString("date_naissance"); 
				String specialite = data.getString("specialite");
				String photo = data.getString("photo");
				Formateurs f= new Formateurs(id, noms, prenoms, date_naissance,specialite, photo);
				listFormateurs.add(f); 
				}	
				
				AdapterFormateurs adapter = new AdapterFormateurs(context, listFormateurs);
				lclasse.setAdapter(adapter);
				
				}catch(Exception e){ 
					Log.e("Erreur",e.getMessage());
				}finally { 
					
				} 
				
				lc.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
						// TODO Auto-generated method stub
						intent = new Intent(FormateurActivity.this,DetailFormateursActivity.class);
						intent.putExtra("Formateurs", listFormateurs.get(position));
						startActivity(intent);

					}
				});
				
				progressbar.setVisibility(View.INVISIBLE);
			}
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_promo, menu);
		menu.add(0, 1, 0, "Promo"); 
		menu.add(0, 2, 0, "News");
		menu.add(0, 3, 0, "Réseau");
		menu.add(0, 4, 0, "Mon compte");
		//menu.add(0, 4, 0, "Twitter");
		return true;
	}
	
	public boolean onMenuItemSelected(int featureId, MenuItem item) { 
		switch (item.getItemId()) { 
		case 1: 
			intent = new Intent(FormateurActivity.this,ListPromoActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 2: 
			intent = new Intent(FormateurActivity.this,NewsActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 3: 
			intent = new Intent(FormateurActivity.this,ReseauSocActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 4: 
			intent = new Intent(FormateurActivity.this,ProfilActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		default: return true; 
		} 
		} 
	}
