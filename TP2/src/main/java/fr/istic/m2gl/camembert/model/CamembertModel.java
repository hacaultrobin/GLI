package fr.istic.m2gl.camembert.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.istic.m2gl.camembert.command.ICommand;

public class CamembertModel implements Model {

	private String title;
	private Map<String, FieldValue> fields;
	
	private ICommand camembertChangedCmd;
	
	public CamembertModel(String title) {
		this.title = title;
		this.fields = new HashMap<String, FieldValue>();
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
		notifyChange();
	}
	
	public Collection<String> getFieldsNames() {
		return fields.keySet();
	}
	
	public String getDesc(String fieldName) {
		return fields.get(fieldName).getDesc();
	}
	
	public void setDesc(String fieldName, String desc) {
		fields.get(fieldName).setDesc(desc);
		notifyChange();
	}
		
	public float getValue(String fieldName) {
		return fields.get(fieldName).getValue();
	}
	
	public void setValue(String fieldName, float value) {
		fields.get(fieldName).setValue(value);
		notifyChange();
	}
	
	public float getTotalValues() {
		float sum = 0;
		for (Entry<String, FieldValue> e : fields.entrySet()) {
			sum += e.getValue().getValue();
		}
		return sum;
	}
	
	public void setName(String oldName, String newName) {
		FieldValue oldValue = this.fields.get(oldName);
		if (oldValue != null && !this.fields.containsKey(newName)) {
			this.fields.remove(oldName);
			this.fields.put(newName, oldValue);			
			notifyChange();
		}
	}
	
	public void addField(String name, String desc, float value) {
		this.fields.put(name, new FieldValue(desc, value));
		notifyChange();
	}
	
	public void removeField(String name) {
		this.fields.remove(name);
		notifyChange();
	}
		
	private void notifyChange() {
		if (this.camembertChangedCmd != null) {
			this.camembertChangedCmd.execute();
		}
	}
	
	public void setCamembertChangedCmd(ICommand cmd) {
		camembertChangedCmd = cmd;
	}
	
}
