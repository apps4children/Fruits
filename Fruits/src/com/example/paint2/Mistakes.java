package com.example.paint2;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Mistakes {
	// instead of HotOrNot
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "students_name";
	public static final String KEY_LEVEL = "game_level";
	public static final String KEY_MISTAKE = "mistake_made";
	public static final String KEY_DATEnTIME = "date_time";
	public static final String KEY_LANG = "language";
	
	private static final String DATABASE_NAME = "MistakesDb";
	private static final String DATABASE_TABLE = "logTable";
	private static final int DATABASE_VERSION = 3;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private String student_name;
	private String game_level;
	private String game_lang;
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_NAME + " TEXT NOT NULL, " +
					KEY_LEVEL + " TEXT NOT NULL, " +
					KEY_MISTAKE + " TEXT NOT NULL, " +
					KEY_LANG + " TEXT NOT NULL, " +
					KEY_DATEnTIME + " TEXT NOT NULL);"
			);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public Mistakes(Context c){
		ourContext = c;
	}
	
	public void setname(String sname)
	{
		student_name = sname;
		Log.d("sname","sname->"+student_name);
	}

	public void setlevel(Integer level)
	{
		game_level =level.toString();
		Log.d("game_level","game_level->"+game_level);
	}
	
	public void setlang(Integer clang) 
	{
		game_lang = clang.toString();
		
	}
	public Mistakes open(){
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
	
	public long createEntry(String name, String level, String mistake,String clang, String date_time){
		
		
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_LEVEL, level);
		cv.put(KEY_MISTAKE, mistake);
		cv.put(KEY_DATEnTIME,date_time);
		cv.put(KEY_LANG, clang);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public ArrayList<String> getData() {
		// TODO Auto-generated method stub
		
		String countsame = "count("+KEY_MISTAKE+")";
		String[] columns = new String[]{KEY_MISTAKE, KEY_DATEnTIME,countsame};
		String where_clause = KEY_NAME + "= ?" +" AND "+ KEY_LEVEL + "= ?" + " AND " + KEY_LANG + "= ?"; 
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, where_clause , new String[]{student_name,game_level,game_lang}, KEY_MISTAKE, null, null, null);
		
		int nrows=c.getCount();
		ArrayList<String> re = new ArrayList<String>();
	//	String[] result = new String[nrows];
	//	int iRow = c.getColumnIndex(KEY_ROWID);
	//	int iName = c.getColumnIndex(KEY_NAME);
	//	int iLevel = c.getColumnIndex(KEY_LEVEL);
		int iMistake = c.getColumnIndex(KEY_MISTAKE);
		int iDatenTime = c.getColumnIndex(KEY_DATEnTIME);
		int iCount = c.getColumnIndex(countsame);
	//	int l=0;
		String temp="a";
		String ttemp = "b";
		
		for(c.moveToFirst(); !c.isAfterLast();c.moveToNext()){
	//		result[l] = c.getString(iRow) + " " + c.getString(iName) + " " + c.getString(iLevel) ;
			ttemp = c.getString(iDatenTime);
			if(!temp.equals(ttemp)) 
			{
				temp = ttemp;
				String g = "\t\t\t\t\t" + temp;
				re.add(g);
			}
			String g2 = c.getString(iMistake) + " --> " + c.getString(iCount);
			re.add(g2);
		}
		
		return re;
	}
	
	public int clearLog()
	{
		String where_clause = KEY_NAME + "= ?" +" AND "+ KEY_LEVEL + "= ?";
		return ourDatabase.delete(DATABASE_TABLE,where_clause,new String[]{student_name,game_level});
	}

}