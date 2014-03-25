package com.insta.instaschool.activity;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.example.instaschool.R;
import com.insta.instaschool.modele.Eleves;
import com.insta.instaschool.modele.Utilisateur;

public class DetailEleveActivity extends Activity {
	
	Utilisateur user;
	private TextView nom;
	private TextView prenom;
	private TextView date_n;
	private QuickContactBadge photo;
	private Intent intent;
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compte_user);
		
		user = (Utilisateur) getIntent().getSerializableExtra("Utilisateur");
		
		Bundle extra = getIntent().getExtras();
		if(extra!= null)
		{
			nom = (TextView) findViewById(R.id.nom);
			prenom = (TextView) findViewById(R.id.prenom);
			date_n = (TextView) findViewById(R.id.date_n);
			photo = (QuickContactBadge) findViewById(R.id.photo);
			
			Eleves e = (Eleves) extra.get("Eleves");
			//t.setText(e.toString());
			
		    nom.setText(e.getNom());
		    prenom.setText(e.getPrenom());
		    date_n.setText(e.getDate_naissance());
		    //photo.setText(e.getPhoto());
		    

		    ImageTask task = new ImageTask();
			task.execute(new String[]{"http://sebastienthibaud.com/android_school/images/"+e.getPhoto()+".jpg"});

		}
		 
	}

	public class ImageTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			return downloadBitmap(params[0]);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			photo.setImageBitmap(result);
		}
	}
	
	private Bitmap downloadBitmap(String url) {
        // initilize the default HTTP client object
        final DefaultHttpClient client = new DefaultHttpClient();

        //forming a HttoGet request 
        final HttpGet getRequest = new HttpGet(url);
        try {
            HttpResponse response = client.execute(getRequest);
            //check 200 OK for success
            final int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = null;
                try {
                    // getting contents from the stream 
                    inputStream = entity.getContent();

                    // decoding stream data back into image Bitmap that android understands
                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    return bitmap;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    entity.consumeContent();
                }
            }
        } catch (Exception e) {
            // You Could provide a more explicit error message for IOException
            getRequest.abort();
        } 
        return null;
    }
	
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

	@Test
	public boolean onMenuItemSelected(int featureId, MenuItem item) { 
		switch (item.getItemId()) { 
		case 1: 
			intent = new Intent(DetailEleveActivity.this,FormateurActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 2: 
			intent = new Intent(DetailEleveActivity.this,NewsActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 3: 
			intent = new Intent(DetailEleveActivity.this,ReseauSocActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 4: 
			intent = new Intent(DetailEleveActivity.this,ProfilActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		default: return true; 
		} 
		} 
	}
