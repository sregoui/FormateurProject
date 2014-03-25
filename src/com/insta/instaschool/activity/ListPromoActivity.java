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
import com.insta.instaschool.adapter.AdapterPromo;
import com.insta.instaschool.bdd.PromoDataSource;
import com.insta.instaschool.modele.Promo;
import com.insta.instaschool.modele.Utilisateur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ListPromoActivity extends Activity {

	private Utilisateur user;
	private ListView lclasse;
	private Context context;
	private ListView lc;
	private Intent intent;
	private List<Promo> listPromo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_promo);
		
		user = (Utilisateur) getIntent().getSerializableExtra("Utilisateur");
		
		context = getApplicationContext();
		lclasse = (ListView) findViewById(R.id.listView1); 
		
		Mytask task = new Mytask();
		task.execute();
		
		

	    lc = (ListView) findViewById(R.id.listView1);


	}

	public class Mytask extends AsyncTask<String, Void, StringBuffer>{

		List<Promo> listPromo;
		
		@Override
		protected StringBuffer doInBackground(String... params) {
			// TODO Auto-generated method stub
			StringBuffer chaine = new StringBuffer("");
			AndroidHttpClient client = AndroidHttpClient.newInstance("user-Agent");
			
			try{
			URI uri = new URI("http://sebastienthibaud.com/android_school/traitements/listepromos.php"); 
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
			
			listPromo = new ArrayList<Promo>();
			
			ProgressBar progressbar = (ProgressBar)findViewById(R.id.progressBar);
			
			progressbar.setVisibility(View.VISIBLE);
			
				try{ //Traitement du JSON 
					
				JSONObject jsonObject = new JSONObject(result.toString()); 
				JSONObject classement = jsonObject.getJSONObject("INSTA");
				JSONArray datas = new JSONArray(classement.getString("Promos"));
				
				for(int i = 0; i< datas.length(); i++){ 
				JSONObject data = datas.getJSONObject(i); 
				int points = data.getInt("id"); 
				String noms = data.getString("promo"); 
				String ad = data.getString("annee_debut"); 
				String af = data.getString("annee_fin"); 
				Promo e= new Promo(points, noms, ad, af);
				listPromo.add(e); 
				}	
				
				AdapterPromo adapter = new AdapterPromo(context, listPromo);
				lclasse.setAdapter(adapter);
				
				}catch(Exception e){ 
					Log.e("Erreur",e.getMessage());
				}finally { 
					
				} 
				
				lc.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
						// TODO Auto-generated method stub
						intent = new Intent(ListPromoActivity.this,DetailPromoActivity.class);
						intent.putExtra("promo", listPromo.get(position));
						startActivity(intent);

					}
				});
				
				progressbar.setVisibility(View.INVISIBLE);
			}
}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.promo, menu);
		menu.add(0, 1, 0, "Formateur"); 
		menu.add(0, 2, 0, "News");
		menu.add(0, 3, 0, "Réseau");
		menu.add(0, 4, 0, "Mon compte");
		//menu.add(0, 4, 0, "Twitter");
		
		return true;
	}
	
	
	public boolean onMenuItemSelected(int featureId, MenuItem item) { 
		switch (item.getItemId()) { 
		case 1: 
			intent = new Intent(ListPromoActivity.this,FormateurActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 2: 
			intent = new Intent(ListPromoActivity.this,NewsActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 3: 
			intent = new Intent(ListPromoActivity.this,ReseauSocActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 4: 
			intent = new Intent(ListPromoActivity.this,ProfilActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		default: return true; 
		} 
		} 
	}
