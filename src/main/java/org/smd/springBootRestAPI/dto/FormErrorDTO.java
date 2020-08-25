package org.smd.springBootRestAPI.dto;

public class FormErrorDTO { // translates kind of error that are sent
	
	private String field;
	private String error;
	public FormErrorDTO(String field, String error) {
		super();
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}

}
