package fr.istic.m2gl.camembert.model;

import java.util.Collection;

public interface Model {

	public String getTitle();

	public void setTitle(String title);

	public Collection<String> getFieldsNames();

	public String getDesc(String fieldName);

	public void setDesc(String fieldName, String desc);

	public float getValue(String fieldName);

	public void setValue(String fieldName, float value);

	public float getTotalValues();

	public void setName(String oldName, String newName);

	public void addField(String name, String desc, float value);

	public void removeField(String name);

}