package br.com.application.utils;

import org.codehaus.jackson.annotate.JsonValue;

public enum JsonStatusEnum {

	SUCCESS("success"), WARNING("warning"), ERROR("error");
	private final String status;

	private JsonStatusEnum(final String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}
	
	@JsonValue
	@Override
	public String toString() {
		return this.status.toLowerCase();
	}
}