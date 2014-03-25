package com.insta.instaschool.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseOpenHelper extends SQLiteOpenHelper {

	//------------------------------------------------------
	private static final String DATABASE_NAME = "android_school.db";
	private static int VERSION = 1;
			
	public BaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
