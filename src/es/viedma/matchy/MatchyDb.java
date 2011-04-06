package es.viedma.matchy;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class MatchyDb {
		
	private static final String DATABASE_NAME = "matchy.db";
	private static final int DATABASE_VERSION = 2;

	private static final String CARDS_TABLE = "card";
	public static final String KEY_CARD_ID = "card_id";
	public static final String KEY_KID_ID = "kid_id";
	public static final String KEY_CARD = "card";
	public static final String KEY_ANSWER = "answer";
	public static final String KEY_CREATED_AT = "created_at";
	public static final String KIDS_TABLE = "kids";
	public static final String QUESTIONS_TABLE = "questions";
	public static final String KEY_QUESTION = "question";
	public static final String KEY_QUESTION_ID = "question_id";
	public static MatchyDb instance;

	private SQLiteDatabase db;
	private MatchyDBOpenHelper dbHelper;
	private Integer lastKidId;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public static void setup(Context context) {
		if (instance == null) instance = new MatchyDb(context);
	}
	
	private MatchyDb(Context context) {
		this.dbHelper = new MatchyDBOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public void open() {
		try {
			db = dbHelper.getWritableDatabase();
		} catch (SQLiteException e) {
			db = dbHelper.getReadableDatabase();
		}
	}
	
	public void close() {
		db.close();
	}
	
	public int nextKidId() {
		int lastId;
		this.open();
		Cursor cur = db.query("SQLITE_SEQUENCE", null, "name == 'kids'", null, null, null, null);
		cur.moveToFirst();
		String res = cur.getString(1);
	    lastId = Integer.parseInt(res);
	    this.close();
		return lastId;
	}
	
	public long insertCard (String card, String answer){
		if (lastKidId == null) throw new RuntimeException("There is no kid selected!");
		this.open();	
		ContentValues newCard = new ContentValues();
		
		newCard.put(KEY_KID_ID, lastKidId);
		newCard.put(KEY_CARD, card);
		newCard.put(KEY_ANSWER, answer);
		newCard.put(KEY_CREATED_AT, dateFormat.format(System.currentTimeMillis()));
		long inserts = db.insert(CARDS_TABLE, null, newCard);
		this.close();
		return inserts;
	}
	
	public long insertQuestion (String question, String answer){
		if (lastKidId == null) throw new RuntimeException("There is no kid selected!");
		this.open();
		ContentValues newQuestion = new ContentValues();
		newQuestion.put(KEY_KID_ID, lastKidId);
		newQuestion.put(KEY_QUESTION, question);
		newQuestion.put(KEY_ANSWER, answer);
		newQuestion.put(KEY_CREATED_AT, dateFormat.format(System.currentTimeMillis()));
		long insert = db.insert(QUESTIONS_TABLE, null, newQuestion);
		this.close();
		return insert;
	}
	
	public long createNewKid (){
		this.open();
		ContentValues newKid = new ContentValues();
		newKid.put(KEY_CREATED_AT, dateFormat.format(System.currentTimeMillis()));
		
		long insertedId = db.insert(KIDS_TABLE, null, newKid);
		this.close();
        lastKidId = (Integer) (int) insertedId;
		return insertedId;
	}
	
	private class MatchyDBOpenHelper extends SQLiteOpenHelper {

		private static final String KIDS_CREATE = "create table " + KIDS_TABLE + " (" + 
			KEY_KID_ID + " integer primary key autoincrement, " +
			KEY_CREATED_AT + " text);";
		
		private static final String CARDS_CREATE = "create table " + CARDS_TABLE + " (" + 
			KEY_CARD_ID + " integer primary key autoincrement, " +
			KEY_KID_ID + " integer, " +
			KEY_CARD + " text not null, " + 
			KEY_ANSWER + " text not null, " +
			KEY_CREATED_AT + " text);";
		
		private static final String QUESTIONS_CREATE = "create table " + QUESTIONS_TABLE + " (" + 
			KEY_QUESTION_ID + " integer primary key autoincrement, " +
			KEY_KID_ID + " integer, " +
			KEY_QUESTION + " text not null, " + 
			KEY_ANSWER + " text not null, " +
			KEY_CREATED_AT + " text);";
		
		public MatchyDBOpenHelper (Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(KIDS_CREATE);
			_db.execSQL(CARDS_CREATE);
			_db.execSQL(QUESTIONS_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
			Log.w("MatchyDBAdapter", "Destructive upgrade from version " + _oldVersion + " to " + _newVersion);
			_db.execSQL("DROP TABLE IF EXISTS " + KIDS_TABLE);
			_db.execSQL("DROP TABLE IF EXISTS " + CARDS_TABLE);
			_db.execSQL("DROP TABLE IF EXISTS " + QUESTIONS_TABLE);
			onCreate(_db);			
		}
	}

}
