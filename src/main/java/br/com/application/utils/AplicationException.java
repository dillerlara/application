package br.com.application.utils;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AplicationException extends RuntimeException {

    private static final long serialVersionUID = 5517434192564487786L;
	private String type;
	private String title;
	private String message;

    public AplicationException(String type, String title, String message) {
		this.type = type;
		this.title = title;
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message == null ? "MSG.UNKNOWN-ERROR" : message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static ResponseEntity<JsonStruct> responseException(JsonStruct struct, Exception exception) {

		if (exception instanceof DataIntegrityViolationException) {
			exception.printStackTrace();
			struct.put("exception", new AplicationException(JsonStatusEnum.ERROR.getStatus(), "SUMMARY.ERROR",
					"Este registro possui dados relacionados."));
			return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);

		}

		if (!(exception instanceof AplicationException)) {
			exception.printStackTrace();
			struct.put("exception",
					new AplicationException(JsonStatusEnum.ERROR.getStatus(), "SUMMARY.ERROR", exception.getMessage()));
			return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);
		}

		exception.printStackTrace();
		struct.put("exception", exception);
		return new ResponseEntity<JsonStruct>(struct, HttpStatus.NOT_FOUND);
	}

    
}
