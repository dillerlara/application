package br.com.application.model;

public class RefreshToken {

	private String refreshToken;
	private DeviceAppDto device;

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public DeviceAppDto getDevice() {
		return device;
	}

	public void setDevice(DeviceAppDto device) {
		this.device = device;
	}
}
