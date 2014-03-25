package com.insta.instaschool.bdd;

import java.util.ArrayList;
import java.util.List;

import com.insta.instaschool.modele.Promo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PromoDataSource {
	private BaseOpenHelper helper;
	private SQLiteDatabase bdd;
	private Promo promo;

	public PromoDataSource(Promo promo, Context context) {

		this.helper = new BaseOpenHelper(context);
		this.promo = promo;
	}

	public PromoDataSource(Context context) {

		this.helper = new BaseOpenHelper(context);

	}

	public void open() throws SQLException {
		bdd = helper.getWritableDatabase();
	}

	public String insert() {
	String msg = "";
//
//		ContentValues v = new ContentValues();
//		v.put("nom", this.promo.getPromo());
//		long re = bdd.insert(BaseOpenHelper.TABLE_NAME, null, v);
//		if (re != 0) {
//			msg = "insertion ok!!!";
//		} else {
//			msg = "insertion ko!!";
//		}
	return msg;
	}

	public List<Promo> selectAll() {
		List<Promo> l = new ArrayList<Promo>();

		String selectQuery = "SELECT promo, annee_debut, annee_fin FROM promo ";
		Cursor cursor = bdd.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			
				try {
					this.promo= new Promo();
					this.promo.setPromo(cursor.getString(1));
					l.add(this.promo);
					cursor.moveToNext();
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
			
		}
		cursor.close();
		

		return l;
	}

	public void close() {
		helper.close();
	}

	public Promo getPromo() {
		return promo;
	}

	public void setPromo(Promo promo) {
		this.promo = promo;
	}

}