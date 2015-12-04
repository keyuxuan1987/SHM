package com.mypro.factory;

public class Attribute {

	private String field;

	private String type;

	private String length;

	private String description;

	public Attribute(String field, String type, String length, String description) {
		super();
		this.field = field;
		this.type = type;
		this.length = length;
		this.description = description;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
