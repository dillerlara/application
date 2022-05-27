package br.com.application.dto;

public class AppUserMonthDTO {
	
	private Integer totalApp;
	private Integer totalCustomer;
	
	private String month;
	
	public AppUserMonthDTO(String month, Integer totalApp, Integer totalCustomer) {
		this.totalApp = totalApp;
		this.totalCustomer = totalCustomer;
		this.month = month;
	}
	public Integer getTotalApp() {
		return totalApp;
	}
	public void setTotalApp(Integer totalApp) {
		this.totalApp = totalApp;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getTotalCustomer() {
		return totalCustomer;
	}
	public void setTotalCustomer(Integer totalCustomer) {
		this.totalCustomer = totalCustomer;
	}

}
