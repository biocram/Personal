package com.lifesum.assignment.data.db.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.lifesum.assignment.data.db.bean.DatabaseBean;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public abstract class AbstractDAO<T extends DatabaseBean> {

	private static final String TAG = AbstractDAO.class.getSimpleName();

	// --------------------------------
	//
	// INSERT STATEMENT
	//
	// --------------------------------

	/**
	 * 
	 * Insert an item into db
	 * 
	 * @param db
	 * @param item
	 * @return
	 * @throws SQLException
	 */
	public long insert(SQLiteDatabase db, T item) throws SQLException {
		long id = -1;
		try {
			id = this.insertOrThrow(db, item);
		} catch (SQLException e) {
			throw e;
		}

		return id;
	}

	/**
	 * 
	 * Insert a list of items in a transaction into db
	 * 
	 * @param db
	 * @param itemList
	 * @return
	 * @throws SQLException
	 */
	public List<Long> insert(SQLiteDatabase db, List<T> itemList)
			throws SQLException {
		List<Long> itemRowIdList = new LinkedList<Long>();

		try {
			db.beginTransaction();
			for (T item : itemList) {
				itemRowIdList.add(this.insertOrThrow(db, item));
			}
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			throw e;
		} finally {
			db.endTransaction();
		}

		return itemRowIdList;
	}

	private long insertOrThrow(SQLiteDatabase db, T item) throws SQLException {
		long itemRowId = -1;
		try {
			itemRowId = db.insertOrThrow(this.getTableName(), null,
					this.objectToContentValues(item));
		} catch (SQLException e) {
			Log.e(TAG, "An error occured during the insert operation on: "
					+ this.getTableName());
			throw e;
		}

		return itemRowId;
	}

	// --------------------------------
	//
	// DELETE STATEMENT
	//
	// --------------------------------

	/**
	 * 
	 * Delete an item from db
	 * 
	 * @param db
	 * @param columnName
	 * @param columnValue
	 * @return
	 */
	public int removeBy(SQLiteDatabase db, String columnName, String columnValue) {
		return delete(db, columnName, columnValue);
	}

	/**
	 * 
	 * Delete list of items from db
	 * 
	 * @param db
	 * @param columnName
	 * @param columnValues
	 * @return
	 */
	public int removeBy(SQLiteDatabase db, String columnName,
			String[] columnValues) {

		int deletedRows = 0;
		
		for (String columnValue : columnValues) {
			deletedRows += delete(db, columnName, columnValue);
		}

		return deletedRows;
	}

	private int delete(SQLiteDatabase db, String columnName, String columnValue) {
		return db.delete(this.getTableName(), columnName + " = ? ",
				new String[] { columnValue });
	}

	// --------------------------------
	//
	// SELECT STATEMENT
	//
	// --------------------------------

	/**
	 * 
	 * Search all items of the table.
	 * 
	 * @param db
	 * @return
	 */
	public List<T> findAll(SQLiteDatabase db) {

		Cursor c = db.query(this.getTableName(),
				this.getColumnsForSelectStatement(), null, null, null, null,
				null);

		List<T> resultList = new ArrayList<T>(c.getCount());

		while (c.moveToNext()) {
			resultList.add(this.rowToObject(c));
		}

		c.close(); // release cursor
		return resultList;
	}

	// --------------------------------
	//
	// ABSTRACT METHODS
	//
	// --------------------------------

	protected abstract ContentValues objectToContentValues(T item);

	protected abstract T rowToObject(Cursor c);

	protected abstract String getTableName();

	protected abstract String[] getColumnsForSelectStatement();

}
