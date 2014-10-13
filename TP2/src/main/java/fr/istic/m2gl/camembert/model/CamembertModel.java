package fr.istic.m2gl.camembert.model;

import java.util.HashMap;

public class CamembertModel {

	private String title;
	private HashMap<String, FieldValue> fields;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
