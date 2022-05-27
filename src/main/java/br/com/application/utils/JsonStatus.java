package br.com.application.utils;

public class JsonStatus {
	private JsonStatusEnum status;

	public JsonStatusEnum getStatus() {
		return status;
	}

	public void setStatus(JsonStatusEnum status) {
		this.status = status;
	}
	
	public void setStatusToSuccess() {
		this.status = JsonStatusEnum.SUCCESS;
	}
	
	public void setStatusToWarning() {
		this.status = JsonStatusEnum.WARNING;
	}
	
	public void setStatusToError() {
		this.status = JsonStatusEnum.ERROR;
	}
}
