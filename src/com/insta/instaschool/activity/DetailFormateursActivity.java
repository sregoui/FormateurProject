package com.insta.instaschool.activity;

import com.example.instaschool.R;
import com.insta.instaschool.modele.Eleves;
import com.insta.instaschool.modele.Formateurs;
import com.insta.instaschool.modele.Utilisateur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailFormateursActivity extends Activity{

	Utilisateur user;
	private TextView id_formateur;
	private TextView nom;
	private TextView prenom;
	private TextView date_n;
	private TextView specialite;
	private TextView photo;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_formateurs);
		
		Bundle extra = getIntent().getExtras();
		if(extra!= null)
		{
			user = (Utilisateur) getIntent().getSerializableExtra("Utilisateur");
			nom = (TextView) findViewById(R.id.nom);
			prenom = (TextView) findViewById(R.id.prenom);
			date_n = (TextView) findViewById(R.id.date_n);
			specialite = (TextView) findViewById(R.id.specialite);
			//photo = (TextView) findViewById(R.id.photo);
			
			Formateurs f = (Formateurs) extra.get("Formateurs");
			//t.setText(e.toString());
			
		    nom.setText(f.getNom());
		    prenom.setText(f.getPrenom());
		    date_n.setText(f.getDate_naissance());
		    specialite.setText(f.getSpecialite());
		    //photo.setText(e.getPhoto());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_eleve, menu);
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
			intent = new Intent(DetailFormateursActivity.this,FormateurActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 2: 
			intent = new Intent(DetailFormateursActivity.this,NewsActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 3: 
			intent = new Intent(DetailFormateursActivity.this,ReseauSocActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 4: 
			intent = new Intent(DetailFormateursActivity.this,ProfilActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		default: return true; 
		} 
		} 
	}
