package com.microservices.conversion.dto;

public class ResultValue {
	
	private boolean fuelLid;
	private Long time;
	private Double price;
	private String city;
	
	
	public void setFuelLid(boolean fuelLid) {
		this.fuelLid = fuelLid;
	}


	public void setTime(Long time) {
		this.time = time;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public boolean isFuelLid() {
		return fuelLid;
	}


	public Long getTime() {
		return time;
	}


	public Double getPrice() {
		return price;
	}


	public String getCity() {
		return city;
	}


	public ResultValue(boolean fuelLid, Long time, Double price, String city) {
		super();
		this.fuelLid = fuelLid;
		this.time = time;
		this.price = price;
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "ResultValue [fuelLid=" + fuelLid + ", time=" + time + ", price=" + price + ", city=" + city + "]";
	}

}
