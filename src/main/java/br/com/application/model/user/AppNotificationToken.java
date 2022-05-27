package br.com.application.model.user;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class AppNotificationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@ManyToOne
	AppNotification appNotification;
	
	String token;
	
	public AppNotificationToken() {}
	
	public AppNotificationToken(Integer id, AppNotification appNotification, String token, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.appNotification = appNotification;
		this.token = token;
		this.updatedAt = updatedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AppNotification getAppNotification() {
		return appNotification;
	}

	public void setAppNotification(AppNotification appNotification) {
		this.appNotification = appNotification;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	LocalDateTime updatedAt;
}
