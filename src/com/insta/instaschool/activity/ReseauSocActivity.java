package com.insta.instaschool.activity;

import java.util.Locale;

import com.example.instaschool.R;
import com.insta.instaschool.modele.Utilisateur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

public class ReseauSocActivity extends FragmentActivity {
	
	private Utilisateur user;
	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;
	private Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reseau_soc);
		
		user = (Utilisateur) getIntent().getSerializableExtra("Utilisateur");
		
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
	}	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_eleve, menu);
		menu.add(0, 1, 0, "Formateur"); 
		menu.add(0, 2, 0, "News");
		menu.add(0, 3, 0, "Promo");
		menu.add(0, 4, 0, "Mon compte");
		//menu.add(0, 4, 0, "Twitter");
		return true;
	}
	
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		
		//List<?> list;

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
			//this.list = list;
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			//return list.size();
			return 2;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			}
			
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
			WebView dummyTextView = (WebView) rootView.findViewById(R.id.section_label);
			//dummyTextView.setTag(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
			int id = getArguments().getInt(ARG_SECTION_NUMBER);
			if(id == 1){
			dummyTextView.loadUrl("https://m.facebook.com/pages/CFA-INSTA/116052425081254");
			}else{
			dummyTextView.loadUrl("https://mobile.twitter.com/signup");	
			}
			return rootView;
		}
	}
		

	public boolean onMenuItemSelected(int featureId, MenuItem item) { 
		switch (item.getItemId()) { 
		case 1: 
			intent = new Intent(ReseauSocActivity.this,FormateurActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 2: 
			intent = new Intent(ReseauSocActivity.this,NewsActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 3: 
			intent = new Intent(ReseauSocActivity.this,ListPromoActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		case 4: 
			intent = new Intent(ReseauSocActivity.this,ProfilActivity.class);
			intent.putExtra("Utilisateur", user);
			startActivity(intent);
		return true; 
		default: return true; 
		} 
		} 
	}
