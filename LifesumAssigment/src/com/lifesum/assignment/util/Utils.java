package com.lifesum.assignment.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.lifesum.assignment.data.db.bean.DatabaseBean;
import com.lifesum.assignment.data.db.bean.FavouriteBean;
import com.lifesum.assignment.data.dto.BasePageBean;
import com.lifesum.assignment.data.network.model.SearchResponse.WSItem;

public class Utils {
	
	private static final int BUFFER_SIZE = 1024 * 16;
	
	/**
	 * Reads the given InputStream as a byte[] and then closes it.
	 */
	public static byte[] toByteArray(InputStream input) throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		int read;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			while ((read = input.read(buffer)) != -1)
				baos.write(buffer, 0, read);
			return baos.toByteArray();
		} finally {
			if (baos != null) baos.close();
			input.close();
		}
	}
	
	public static boolean isNotNullOrEmpty(String s) {
		if (s != null && !("").equals(s.trim())) {
			return true;
		} else
			return false;

	}

	public static boolean isNullOrEmpty(String s) {
		return !isNotNullOrEmpty(s);

	}
	
	public static BasePageBean adaptWSItems2PageBean(WSItem item) {
		BasePageBean bean = new BasePageBean();
		bean = new BasePageBean();
		bean.setId(item.getId());
		bean.setCalories(item.getCalories());
		bean.setCategory(item.getCategory());
		bean.setFormat(item.getPcstext());
		bean.setTitle(item.getTitle());
		return bean;
	}

	public static BasePageBean adaptDatabaseBean2PageBean(DatabaseBean dbBean) {
		BasePageBean pageBean = new BasePageBean();
		if(dbBean instanceof FavouriteBean)
		{
			FavouriteBean favBean = (FavouriteBean) dbBean;
			pageBean.setId(favBean.getId());
			pageBean.setTitle(favBean.getTitle());
			pageBean.setCategory(favBean.getCategory());
			pageBean.setFormat(favBean.getPcstext());
			pageBean.setCalories(favBean.getCalories());
		}
		return pageBean;
	}
	
	public static List<BasePageBean> adaptDatabaseBean2PageBean(List<? extends DatabaseBean> dbBeans) {
		List<BasePageBean> pageBeans = new ArrayList<BasePageBean>(dbBeans.size());
		BasePageBean pageBean = null;
		for(DatabaseBean dbBean: dbBeans)
		{
			pageBean = adaptDatabaseBean2PageBean(dbBean);
			pageBeans.add(pageBean);
		}
		return pageBeans;
	} 

	public static List<BasePageBean> adaptWSItems2PageBean(List<WSItem> items) {
		List<BasePageBean> beans = new ArrayList<BasePageBean>(items.size());
		BasePageBean bean = null;
		for(WSItem item: items)
		{
			bean = adaptWSItems2PageBean(item);
			beans.add(bean);
		}
		return beans;
	}

	public static FavouriteBean adaptWSItem2FavouriteBean(WSItem item) {
		// for simplicity add only the elements in common with BasePageBean
		FavouriteBean favBean = new FavouriteBean();
		favBean.setId(item.getId());
		favBean.setCalories(item.getCalories());
		favBean.setCategory(item.getCategory());
		favBean.setPcstext(item.getPcstext());
		favBean.setTitle(item.getTitle());
		return favBean;
	} 
	
	public static List<FavouriteBean> adaptWSItem2FavouriteBean(List<WSItem> items) {
		List<FavouriteBean> beans = new ArrayList<FavouriteBean>(items.size());
		FavouriteBean bean = null;
		for(WSItem item: items)
		{
			bean = adaptWSItem2FavouriteBean(item);
			beans.add(bean);
		}
		return beans;
	} 

}
