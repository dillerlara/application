package br.com.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.application.enums.Application;
import br.com.application.enums.Platform;

@Entity
public class AppVersion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false, unique = true)
	private Integer id;

	private String version;
	private String releaseDate;

	@Enumerated(EnumType.STRING)
	private Application application;

	@Enumerated(EnumType.STRING)
	private Platform platform;

	public AppVersion() {
	}

	public AppVersion(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Override
	public String toString() {
		return "AppVersion [id=" + id + ", platform=" + platform + ", version=" + version + ", releaseDate="
				+ releaseDate + ", application=" + application + "]";
	}
}
