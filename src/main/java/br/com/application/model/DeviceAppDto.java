package br.com.application.model;

import br.com.application.enums.Platform;

public class DeviceAppDto {

	private String manufacturer;
	private String model;
	private Platform platform;
	private String version;
	private String easyVersionNumber;

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEasyVersionNumber() {
		return easyVersionNumber;
	}

	public void setEasyVersionNumber(String easyVersionNumber) {
		this.easyVersionNumber = easyVersionNumber;
	}

}
