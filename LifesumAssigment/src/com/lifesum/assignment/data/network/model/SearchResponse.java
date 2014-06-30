package com.lifesum.assignment.data.network.model;

import java.util.Arrays;
import java.util.List;



public class SearchResponse {
	
	private Meta meta;
	private Response response;
	
	public List<WSItem> getItems()
	{
		return Arrays.asList(response.list);
	}
	
	static class Meta
	{
		private double code;
	}
	
	static class Response
	{
		private WSItem[] list;
		
	}
	
	public static class WSItem
	{
		private String id;
		
		private double categoryid;
		private double fiber;
		private String headimage;
		private double pcsingram;
		private String brand;
		private double unsaturatedfat;
		private double fat;
		private double servingcategory;
		private double typeofmeasurement;
		private double protein;
		private double defaultserving;
		private double mlingram;
		private double saturatedfat;
		private String category;
		private boolean verified;
		private String title;
		private String pcstext;
		private double sodium;
		private double carbohydrates;
		private double showonlysametype;
		private double calories;
		private double serving_version;
		private double sugar;
		private double measurementid;
		private double cholesterol;
		private double gramsperserving;
		private double showmeasurement;
		private double potassium;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public double getCategoryid() {
			return categoryid;
		}
		public void setCategoryid(double categoryid) {
			this.categoryid = categoryid;
		}
		public double getFiber() {
			return fiber;
		}
		public void setFiber(double fiber) {
			this.fiber = fiber;
		}
		public String getHeadimage() {
			return headimage;
		}
		public void setHeadimage(String headimage) {
			this.headimage = headimage;
		}
		public double getPcsingram() {
			return pcsingram;
		}
		public void setPcsingram(double pcsingram) {
			this.pcsingram = pcsingram;
		}
		public String getBrand() {
			return brand;
		}
		public void setBrand(String brand) {
			this.brand = brand;
		}
		public double getUnsaturatedfat() {
			return unsaturatedfat;
		}
		public void setUnsaturatedfat(double unsaturatedfat) {
			this.unsaturatedfat = unsaturatedfat;
		}
		public double getFat() {
			return fat;
		}
		public void setFat(double fat) {
			this.fat = fat;
		}
		public double getServingcategory() {
			return servingcategory;
		}
		public void setServingcategory(double servingcategory) {
			this.servingcategory = servingcategory;
		}
		public double getTypeofmeasurement() {
			return typeofmeasurement;
		}
		public void setTypeofmeasurement(double typeofmeasurement) {
			this.typeofmeasurement = typeofmeasurement;
		}
		public double getProtein() {
			return protein;
		}
		public void setProtein(double protein) {
			this.protein = protein;
		}
		public double getDefaultserving() {
			return defaultserving;
		}
		public void setDefaultserving(double defaultserving) {
			this.defaultserving = defaultserving;
		}
		public double getMlingram() {
			return mlingram;
		}
		public void setMlingram(double mlingram) {
			this.mlingram = mlingram;
		}
		public double getSaturatedfat() {
			return saturatedfat;
		}
		public void setSaturatedfat(double saturatedfat) {
			this.saturatedfat = saturatedfat;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public boolean isVerified() {
			return verified;
		}
		public void setVerified(boolean verified) {
			this.verified = verified;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getPcstext() {
			return pcstext;
		}
		public void setPcstext(String pcstext) {
			this.pcstext = pcstext;
		}
		public double getSodium() {
			return sodium;
		}
		public void setSodium(double sodium) {
			this.sodium = sodium;
		}
		public double getCarbohydrates() {
			return carbohydrates;
		}
		public void setCarbohydrates(double carbohydrates) {
			this.carbohydrates = carbohydrates;
		}
		public double getShowonlysametype() {
			return showonlysametype;
		}
		public void setShowonlysametype(double showonlysametype) {
			this.showonlysametype = showonlysametype;
		}
		public double getCalories() {
			return calories;
		}
		public void setCalories(double calories) {
			this.calories = calories;
		}
		public double getServing_version() {
			return serving_version;
		}
		public void setServing_version(double serving_version) {
			this.serving_version = serving_version;
		}
		public double getSugar() {
			return sugar;
		}
		public void setSugar(double sugar) {
			this.sugar = sugar;
		}
		public double getMeasurementid() {
			return measurementid;
		}
		public void setMeasurementid(double measurementid) {
			this.measurementid = measurementid;
		}
		public double getCholesterol() {
			return cholesterol;
		}
		public void setCholesterol(double cholesterol) {
			this.cholesterol = cholesterol;
		}
		public double getGramsperserving() {
			return gramsperserving;
		}
		public void setGramsperserving(double gramsperserving) {
			this.gramsperserving = gramsperserving;
		}
		public double getShowmeasurement() {
			return showmeasurement;
		}
		public void setShowmeasurement(double showmeasurement) {
			this.showmeasurement = showmeasurement;
		}
		public double getPotassium() {
			return potassium;
		}
		public void setPotassium(double potassium) {
			this.potassium = potassium;
		}
	}
	
}
