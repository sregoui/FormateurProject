package com.insta.instaschool.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

//import fr.insta.android.training1.PromoActivity.myTask;

import com.example.instaschool.R;
import com.insta.instaschool.modele.Promo;
import com.insta.instaschool.modele.Utilisateur;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{
	
	private Intent intent;
	private Button btnConnection;
	private EditText loginfield;
	private EditText passwordfield;
	private ProgressBar progressbar;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		progressbar = (ProgressBar)findViewById(R.id.progressBar);
		
		progressbar.setVisibility(View.INVISIBLE);
		
		intent = new Intent(this, ProfilActivity.class);
		
		loginfield = (EditText) findViewById(R.id.editTextLogin);
		passwordfield = (EditText) findViewById(R.id.editTextPassword);
		
		btnConnection = (Button) findViewById(R.id.btnConnection);
		btnConnection.setOnClickListener(this);
	}

	/*public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onMenuItemSelected(int featureId, MenuItem item)
	{
		switch(item.getItemId())
		{
			case R.id.itempromos:
					Toast.makeText(this, "Promos", Toast.LENGTH_SHORT).show();
					return true;
			case R.id.itemnews:
				Toast.makeText(this, "News", Toast.LENGTH_SHORT).show();
				return true;
				
			default:
				return true;
		}
	}*/
	
	public void onBackPressed()
	{
		showDialog(1);
	}
	
	protected Dialog onCreateDialog(int id){
		Dialog dialog;
		switch(id){
			case 1:
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					
					builder.setMessage("Voulez-vous quitter l'application ?");
					builder.setCancelable(false);
					builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int id) {
							finish();
						}
					});
					builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
					
					dialog = builder.create();
					break;
			default:
				dialog = null;
		}
		return dialog;
	}

	public void onClick(View v) {
	
		if(v == btnConnection)
		{
			String login = loginfield.getText().toString();
			String password = passwordfield.getText().toString();
			
			if(login.length() > 0 && password.length() > 0)
			{
				progressbar.setVisibility(View.VISIBLE);
				
				myTask task = new myTask();
				task.execute(new String[]{
						"http://sebastienthibaud.com/android_school/traitements/identification.php"	
				});
			}
			else 
			{
				Toast.makeText(getBaseContext(), "Saisissez votre nom d'utilisateur et votre mot de passe", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	public class myTask extends AsyncTask<String, Void, StringBuffer>
	{
		protected StringBuffer doInBackground(String... urls) 
		{
			String login = loginfield.getText().toString();
			String password = passwordfield.getText().toString();
				
			StringBuffer chaine = new StringBuffer("");
			
			try
			{
				AndroidHttpClient client = AndroidHttpClient.newInstance("user-agent");
				
				URI uri = new URI("http://sebastienthibaud.com/android_school/traitements/identification.php?login="+login+"&password="+password+"");
				HttpGet request = new HttpGet();
				request.setURI(uri);
				HttpResponse response = client.execute(request);
				
				InputStream inputStream = response.getEntity().getContent();
				BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
				String line = "";
				
				while((line = rd.readLine()) != null)
				{
					chaine.append(line);
				}
			}
			catch(Exception e){
				chaine.append(e.getMessage());
			}
			finally{
				
			}

			return chaine;
		}
		
		protected void onPostExecute(StringBuffer result)
		{
			/*TextView textview = (TextView)findViewById(R.id.textRecu);
			ProgressBar progressbar = (ProgressBar)findViewById(R.id.progressBar);
			
			progressbar.setVisibility(View.VISIBLE);*/
			
			try
			{
				Utilisateur user = new Utilisateur();
				
				JSONObject jsonObject = new JSONObject(result.toString());
				JSONObject resultats = jsonObject.getJSONObject("INSTA");
				JSONArray datas = new JSONArray(resultats.getString("Utilisateur"));
				
				for(int i=0; i < datas.length(); i++)
				{
					JSONObject data = datas.getJSONObject(i);
					user = new Utilisateur(data.getString("nom"), data.getString("prenom"), data.getString("email"), data.getString("login"), data.getString("password"), data.getInt("id_role"));

					//textview.append(datas.getJSONObject(i).getString("promo")+datas.getJSONObject(i).getString("")+"/"+datas.getJSONObject(i).getString("annee_fin"));
				}
				
				/*for(int i=0; i < promos.size(); i++)
				{
					textview.append(promos.get(i).getNom()+" : "+promos.get(i).getAnneedebut()+"/"+promos.get(i).getAnneefin()+"\n");
				}*/
				
				if(user.getLogin() != "")
				{
					intent.putExtra("Utilisateur", user);
					startActivity(intent);
				}
				else 
				{
					Toast.makeText(getBaseContext(), "Nom d'utilisateur ou mot de passe incorrecte", Toast.LENGTH_SHORT).show();
				}
				progressbar.setVisibility(View.INVISIBLE);
			}
			catch(Exception e){
				Log.e("Erreur",e.getMessage());
			}
			finally{
				
			}
			
			//progressbar.setVisibility(View.INVISIBLE);
		}
	}

}
