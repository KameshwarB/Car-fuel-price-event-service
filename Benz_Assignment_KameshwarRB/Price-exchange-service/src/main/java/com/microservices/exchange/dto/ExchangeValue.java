package com.microservices.exchange.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExchangeValue {
	
	@Id
	private Long id;
	
	@Column(name="city")
	private String from;
	
	private Double price;
	
	//default constructor
	public ExchangeValue() {
		
	}

	public ExchangeValue(Long id, String from, Double price) {
		super();
		this.id = id;
		this.from = from;		
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public String getFrom() {
		return from;
	}

	public Double getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return "ExchangeValue [id=" + id + ", from=" + from + ", price=" + price + "]";
	}
	

}
