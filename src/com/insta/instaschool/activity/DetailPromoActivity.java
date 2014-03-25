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
import android.widget.TextView;

import com.example.instaschool.R;
import com.insta.instaschool.adapter.AdapterEleves;
import com.insta.instaschool.modele.Eleves;
import com.insta.instaschool.modele.Promo;
import com.insta.instaschool.modele.Utilisateur;

public class DetailPromoActivity extends Activity {

	Utilisateur user;
	private TextView nom;
	private Intent intent;
	private int id_promo1;
	
	private   ListView lclasse;
	private  Context context;
	ListView lc;
	List<Eleves> listEleve;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_promo);
		
		user = (Utilisateur) getIntent().getSerializableExtra("Utilisateur");
		
		//PARTIE PROMO--------------------------------------
		intent = getIntent();
		Bundle extra = intent.getExtras();

		if(extra!= null)
		{
			nom = (TextView) findViewById(R.id.textViewNom);
			
			Promo p = (Promo) extra.get("promo");

			nom.setText(p.getPromo());
			id_promo1 = p.getId();
		}
		//-------------------------------------------------
		
		//PARTIE ELEVES-----------------------------------
		context = getApplicationContext();
		lclasse = (ListView) findViewById(R.id.listView1); 
		
		Mytask task = new Mytask();
		task.execute();
		

	    lc = (ListView) findViewById(R.id.listView1);
	}
	
	public class Mytask extends AsyncTask<String, Void, StringBuffer>{

		List<Eleves> listEleves;
		
		@Override
		protected StringBuffer doInBackground(String... params) {
			// TODO Auto-generated method stub
			StringBuffer chaine = new StringBuffer("");
			AndroidHttpClient client = AndroidHttpClient.newInstance("user-Agent");
			
			try{
			URI uri = new URI("http://sebastienthibaud.com/android_school/traitements/listeeleves.php"); 
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
			
			listEleves = new ArrayList<Eleves>();
			
			
				try{ //Traitement du JSON 
					
				JSONObject jsonObject = new JSONObject(result.toString()); 
				JSONObject classement = jsonObject.getJSONObject("INSTA");
				JSONArray datas = new JSONArray(classement.getString("Eleves"));
				
				
				for(int i = 0; i< datas.length(); i++){ 
				JSONObject data = datas.getJSONObject(i); 
				int idEleve = data.getInt("id");
				String noms = data.getString("nom"); 
				String prenoms = data.getString("prenom"); 
				String date = data.getString("date_naissance"); 
				String photo = data.getString("photo"); 
				int id_promo = data.getInt("id_promo");
				if (id_promo == id_promo1){
				Eleves e= new Eleves(idEleve, noms, prenoms, date,photo, id_promo);
				listEleves.add(e); 
				}
				}	
				
				AdapterEleves adapter = new AdapterEleves(context, listEleves);
				lclasse.setAdapter(adapter);
				
				}catch(Exception e){ 
					Log.e("Erreur",e.getMessage());
				}finally { 
					
				} 
				

				lc.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
						// TODO Auto-generated method stub
						intent = new Intent(DetailPromoActivity.this,DetailEleveActivity.class);
						intent.putExtra("Eleves", listEleves.get(position));
						startActivity(intent);

					}
				});
				
				
			}
}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_promo, menu);
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
			intent = new Intent(DetailPromoActivity.this,FormateurActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 2: 
			intent = new Intent(DetailPromoActivity.this,NewsActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 3: 
			intent = new Intent(DetailPromoActivity.this,ReseauSocActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 4: 
			intent = new Intent(DetailPromoActivity.this,ProfilActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		default: return true; 
		} 
		} 
	}
