package com.lifesum.assignment.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lifesum.assignment.data.db.dao.FavouriteDAO;
import com.lifesum.assignment.util.Utils;

/**
 * 
 * @author c.luchessa
 *
 */
public class DbHelper extends SQLiteOpenHelper {
	public static final String TAG = DbHelper.class.getSimpleName();
    
	// If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "lifesum.db";

    private FavouriteDAO favoriteDAO;
    
    
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    
    public void onCreate(SQLiteDatabase db) {
        execSQLAndLogQuery(db,FavouriteDAO.SQL_CREATE_ENTRIES);
    }
    
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    
    
    /*
     * Utilities
     */
    
    public static void execSQLAndLogQuery(SQLiteDatabase db, String q) {
    	Log.d(TAG, "query executed: "+q);
    	db.execSQL(q);
    }
    
    
    /**
     * DAO access methods
     */
    public FavouriteDAO getFavoriteDao() {
    	if ( favoriteDAO == null) {
    		favoriteDAO = new FavouriteDAO();
    	}
    	return favoriteDAO;
    }
    
}