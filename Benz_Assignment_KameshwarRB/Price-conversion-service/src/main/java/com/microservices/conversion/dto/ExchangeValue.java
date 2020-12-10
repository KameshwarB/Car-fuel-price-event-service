package com.microservices.conversion.dto;

public class ExchangeValue {
	
	
	private Long id;
	private String from;
	private Double price;
	
	
	public Long getId() {
		return id;
	}
	public String getFrom() {
		return from;
	}
	public Double getPrice() {
		return price;
	}
	
	
	public ExchangeValue(Long id, String from, Double price) {
		super();
		this.id = id;
		this.from = from;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "ExchangeValue [id=" + id + ", from=" + from + ", price=" + price + "]";
	}
	

	
	

}
