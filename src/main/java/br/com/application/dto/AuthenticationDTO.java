package br.com.application.dto;

import br.com.application.model.DeviceAppDto;

public class AuthenticationDTO {

	private String email;
	private String password;
	private DeviceAppDto device;
	
	public String getEmail() {
		return email;
	}
	
	public void seteEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public DeviceAppDto getDevice() {
		return device;
	}

	public void setDevice(DeviceAppDto device) {
		this.device = device;
	}
	
}
