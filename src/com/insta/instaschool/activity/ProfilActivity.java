package com.insta.instaschool.activity;

import com.example.instaschool.R;
import com.insta.instaschool.modele.Utilisateur;

import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfilActivity extends Activity {

	private Utilisateur user;
	private TextView nomtext;
	private TextView logintext;
	private TextView emailtext;
	private TextView roletext;
	private Intent intent;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profil);
		
		user = (Utilisateur) getIntent().getSerializableExtra("Utilisateur");

		nomtext = (TextView) findViewById(R.id.textViewNom);
		logintext = (TextView) findViewById(R.id.textViewLogin);
		emailtext = (TextView) findViewById(R.id.textViewEmail);
		roletext = (TextView) findViewById(R.id.textViewRole);
		
		nomtext.append(user.getPrenom()+" "+user.getNom());
		logintext.append(user.getLogin());
		emailtext.append(user.getEmail());
		
		if(user.getIdrole() == 1)
		{
			roletext.append("Utilisateur");
		}
		else if(user.getIdrole() == 2)
		{
		roletext.append("Administration");
		}
		else if(user.getIdrole() == 3)
		{
			roletext.append("Formateur");
		}
		else
		{
			roletext.append("Administrateur");
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
			intent = new Intent(ProfilActivity.this,FormateurActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
			return true; 
		case 2: 
			intent = new Intent(ProfilActivity.this,NewsActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
			return true; 
		case 3: 
			intent = new Intent(ProfilActivity.this,ReseauSocActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
			return true; 
		case 4: 
			intent = new Intent(ProfilActivity.this,ProfilActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
			return true; 
		default: return false; 
		} 
	} 
}
