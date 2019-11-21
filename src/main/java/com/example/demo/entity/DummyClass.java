package com.example.demo.entity;

public class DummyClass {
	
	private int id ;
	
	private String description;
	
	private double amount;
	
	private boolean active;
	
	public DummyClass() {
		
	}
	

	public DummyClass(int id, String description, double amount, boolean active) {
		super();
		this.id = id;
		this.description = description;
		this.amount = amount;
		this.active = active;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	

}
