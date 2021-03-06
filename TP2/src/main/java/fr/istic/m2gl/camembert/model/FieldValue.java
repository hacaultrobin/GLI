package fr.istic.m2gl.camembert.model;

public class FieldValue {

	private String desc;
	private float value;
	
	public FieldValue(String desc, float value) {
		this.desc = desc;
		this.value = value;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
}
