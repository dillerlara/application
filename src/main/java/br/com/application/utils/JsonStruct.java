package br.com.application.utils;

import java.util.HashMap;
import java.util.Map;

public class JsonStruct {
	public final static String STATUS = "status";
	public final static String DATA = "data";
	public final static String MESSAGE = "message";
	public final static String CODE = "code";
	private JsonStatusEnum status = null;
	private String message = null;
	private String code = null;
	private Map<String, Object> data = new HashMap<String, Object>();

	public Map<String, Object> getData() {
		return data;
	}

	public void put(String key, Object value) {
		data.put(key, value);
	}

	public void setStatusToSuccess() {
		this.setStatus(JsonStatusEnum.SUCCESS);
	}

	public void setStatusToWarning() {
		this.setStatus(JsonStatusEnum.WARNING);
	}

	public void setStatusToError() {
		this.setStatus(JsonStatusEnum.ERROR);
	}

	public JsonStatusEnum getStatus() {
		return this.status;
	}

	private void setStatus(JsonStatusEnum status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return this.status == JsonStatusEnum.SUCCESS;
	}

	public boolean isFail() {
		return this.status == JsonStatusEnum.WARNING;
	}

	public boolean isError() {
		return this.status == JsonStatusEnum.ERROR;
	}

}
