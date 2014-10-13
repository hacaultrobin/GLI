package fr.istic.m2gl.camembert.model;

import java.util.Collection;
import java.util.HashMap;

import fr.istic.m2gl.camembert.command.ICommand;

public class CamembertModel {

	private String title;
	private HashMap<String, FieldValue> fields;
	
	private ICommand camembertChangedCmd;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
		if (this.camembertChangedCmd != null) {
			this.camembertChangedCmd.execute();
		}
	}
	
	public Collection<String> getFieldsNames() {
		return fields.keySet();
	}
	
	public String getDesc(String fieldName) {
		return fields.get(fieldName).getDesc();
	}
	
	public void setDesc(String fieldName, String desc) {
		fields.get(fieldName).setDesc(desc);
		if (this.camembertChangedCmd != null) {
			this.camembertChangedCmd.execute();
		}
	}
		
	public float getValue(String fieldName) {
		return fields.get(fieldName).getValue();
	}
	
	public void setValue(String fieldName, float value) {
		fields.get(fieldName).setValue(value);
		if (this.camembertChangedCmd != null) {
			this.camembertChangedCmd.execute();
		}
	}
	
	public void setName(String oldName, String newName) {
		FieldValue oldValue = this.fields.get(oldName);
		if (oldValue != null && !this.fields.containsKey(newName)) {
			this.fields.remove(oldName);
			this.fields.put(newName, oldValue);
			
			if (this.camembertChangedCmd != null) {
				this.camembertChangedCmd.execute();
			}
		}
	}
	
	public void addField(String name, String desc, float value) {
		this.fields.put(name, new FieldValue(desc, value));
		if (this.camembertChangedCmd != null) {
			this.camembertChangedCmd.execute();
		}
	}
	
	public void removeField(String name) {
		this.fields.remove(name);
		if (this.camembertChangedCmd != null) {
			this.camembertChangedCmd.execute();
		}
	}
	
	
}
