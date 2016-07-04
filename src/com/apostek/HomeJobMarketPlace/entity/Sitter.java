package com.apostek.HomeJobMarketPlace.entity;

public class Sitter extends Member {
	private int id;
	private int experience;
	private int expectedPay;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public int getExpectedPay() {
		return expectedPay;
	}
	public void setExpectedPay(int expectedPay) {
		this.expectedPay = expectedPay;
	}

}
