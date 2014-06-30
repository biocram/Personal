package com.lifesum.assignment.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.lifesum.assignment.data.db.bean.FavouriteBean;

/**
 * Contains Favorite info
 * 
 * 
 * 
 */
public class FavouriteDAO extends AbstractDAO<FavouriteBean> {

	/* Inner class that defines the table contents */
	public static abstract class FavouriteEntry {
		public static final String TABLE_NAME = "Favourites";

		/**
	     * The unique ID for a row.
	     * <P>Type: INTEGER (long)</P>
	     */
		public static final String FAVOURITE_ID = "id";
		public static final String CATEGORYID = "categoryid";
		public static final String FIBER = "fiber";
		public static final String HEADIMAGE = "headimage";
		public static final String PCSINGRAM = "pcsingram";
		public static final String BRAND = "brand";
		public static final String UNSATURATEDFAT = "unsaturatedfat";
		public static final String FAT = "fat";
		public static final String SERVINGCATEGORY = "servingcategory";
		public static final String TYPEOFMEASUREMENT = "typeofmeasurement";
		public static final String PROTEIN = "protein";
		public static final String DEFAULTSERVING = "defaultserving";
		public static final String MLINGRAM = "mlingram";
		public static final String SATURATEDFAT = "saturatedfat";
		public static final String CATEGORY = "category";
		public static final String VERIFIED = "verified";
		public static final String TITLE = "title";
		public static final String PCSTEXT = "pcstext";
		public static final String SODIUM = "sodium";
		public static final String CARBOHYDRATES = "carbohydrates";
		public static final String SHOWONLYSAMETYPE = "showonlysametype";
		public static final String CALORIES = "calories";
		public static final String SERVING_VERSION = "serving_version";
		public static final String SUGAR = "sugar";
		public static final String MEASUREMENTID = "measurementid";
		public static final String CHOLESTEROL = "cholesterol";
		public static final String GRAMSPERSERVING = "gramsperserving";
		public static final String SHOWMEASUREMENT = "showmeasurement";
		public static final String POTASSIUM = "potassium";
	}

	public static final String SQL_CREATE_ENTRIES = "" + "CREATE TABLE " + "'"
			+ FavouriteEntry.TABLE_NAME + "' (" + "'"
			+ FavouriteEntry.FAVOURITE_ID	+ "' TEXT PRIMARY KEY NOT NULL" + ", '"
			+ FavouriteEntry.CATEGORYID + "' REAL " + ", '" 
			+ FavouriteEntry.FIBER+ "' REAL " + ", '"
			+ FavouriteEntry.HEADIMAGE + "' TEXT " + ", '"
			+ FavouriteEntry.PCSINGRAM + "' REAL " + ", '"
			+ FavouriteEntry.BRAND + "' TEXT " + ", '" 
			+ FavouriteEntry.UNSATURATEDFAT + "' REAL " + ", '"
			+ FavouriteEntry.FAT + "' REAL " + ", '"
			+ FavouriteEntry.SERVINGCATEGORY + "' REAL " + ", '"
			+ FavouriteEntry.TYPEOFMEASUREMENT + "' REAL " + ", '"
			+ FavouriteEntry.PROTEIN + "' REAL " + ", '"
			+ FavouriteEntry.DEFAULTSERVING + "' REAL " + ", '"
			+ FavouriteEntry.MLINGRAM + "' REAL " + ", '"
			+ FavouriteEntry.SATURATEDFAT + "' REAL " + ", '"
			+ FavouriteEntry.CATEGORY + "' TEXT " + ", '"
			+ FavouriteEntry.VERIFIED + "' REAL " + ", '"
			+ FavouriteEntry.TITLE + "' TEXT " + ", '"
			+ FavouriteEntry.PCSTEXT + "' TEXT " + ", '"
			+ FavouriteEntry.SODIUM + "' REAL " + ", '"
			+ FavouriteEntry.CARBOHYDRATES + "' REAL " + ", '"
			+ FavouriteEntry.SHOWONLYSAMETYPE + "' REAL " + ", '"
			+ FavouriteEntry.CALORIES + "' REAL " + ", '"
			+ FavouriteEntry.SERVING_VERSION + "' REAL " + ", '"
			+ FavouriteEntry.SUGAR + "' REAL " + ", '"
			+ FavouriteEntry.MEASUREMENTID + "' REAL " + ", '"
			+ FavouriteEntry.CHOLESTEROL + "' REAL " + ", '"
			+ FavouriteEntry.GRAMSPERSERVING + "' REAL " + ", '"
			+ FavouriteEntry.SHOWMEASUREMENT + "' REAL " + ", '"
			+ FavouriteEntry.POTASSIUM + "' REAL)";

	@Override
	protected ContentValues objectToContentValues(FavouriteBean item) {

		ContentValues values = new ContentValues();

		values.put(FavouriteEntry.FAVOURITE_ID, item.getId());
		values.put(FavouriteEntry.CATEGORYID, item.getCategoryid());
		values.put(FavouriteEntry.FIBER, item.getFiber());
		values.put(FavouriteEntry.HEADIMAGE, item.getHeadimage());
		values.put(FavouriteEntry.PCSINGRAM, item.getPcsingram());
		values.put(FavouriteEntry.BRAND, item.getBrand());
		values.put(FavouriteEntry.UNSATURATEDFAT, item.getUnsaturatedfat());
		values.put(FavouriteEntry.FAT, item.getFat());
		values.put(FavouriteEntry.SERVINGCATEGORY, item.getServingcategory());
		values.put(FavouriteEntry.TYPEOFMEASUREMENT, item.getTypeofmeasurement());
		values.put(FavouriteEntry.PROTEIN, item.getProtein());
		values.put(FavouriteEntry.DEFAULTSERVING, item.getDefaultserving());
		values.put(FavouriteEntry.MLINGRAM, item.getMlingram());
		values.put(FavouriteEntry.SATURATEDFAT, item.getSaturatedfat());
		values.put(FavouriteEntry.CATEGORY, item.getCategory());
		values.put(FavouriteEntry.VERIFIED, item.isVerified());
		values.put(FavouriteEntry.TITLE, item.getTitle());
		values.put(FavouriteEntry.PCSTEXT, item.getPcstext());
		values.put(FavouriteEntry.SODIUM, item.getSodium());
		values.put(FavouriteEntry.CARBOHYDRATES, item.getCarbohydrates());
		values.put(FavouriteEntry.SHOWONLYSAMETYPE, item.getShowonlysametype());
		values.put(FavouriteEntry.CALORIES, item.getCalories());
		values.put(FavouriteEntry.SERVING_VERSION, item.getServing_version());
		values.put(FavouriteEntry.SUGAR, item.getSugar());
		values.put(FavouriteEntry.MEASUREMENTID, item.getMeasurementid());
		values.put(FavouriteEntry.CHOLESTEROL, item.getCholesterol());
		values.put(FavouriteEntry.GRAMSPERSERVING, item.getGramsperserving());
		values.put(FavouriteEntry.SHOWMEASUREMENT, item.getShowmeasurement());
		values.put(FavouriteEntry.POTASSIUM, item.getPotassium());

		return values;
	}

	@Override
	protected FavouriteBean rowToObject(Cursor c) {

		FavouriteBean item = new FavouriteBean();

		int i = 0;

		item.setId(c.getString(i++));
		item.setCategory(c.getString(i++));
		item.setCalories(c.getDouble(i++));
		item.setTitle(c.getString(i++));
		item.setPcstext(c.getString(i++));

		return item;
	}

	@Override
	protected String getTableName() {
		return FavouriteEntry.TABLE_NAME;
	}

	@Override
	protected String[] getColumnsForSelectStatement() {
		return new String[]{FavouriteEntry.FAVOURITE_ID,FavouriteEntry.CATEGORY,FavouriteEntry.CALORIES, FavouriteEntry.TITLE,FavouriteEntry.PCSTEXT};
	}

}
