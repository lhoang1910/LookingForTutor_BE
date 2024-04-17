package com.ltf.adminservice.dto.response;

public class ClassFee {
	private int admissionFee;
	private long tution;
	private double totalPrice;

	public ClassFee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClassFee(int admissionFee, long tution, double totalPrice) {
		super();
		this.admissionFee = admissionFee;
		this.tution = tution;
		this.totalPrice = totalPrice;
	}

	public int getAdmissionFee() {
		return admissionFee;
	}

	public void setAdmissionFee(int admissionFee) {
		this.admissionFee = admissionFee;
	}

	public long getTution() {
		return tution;
	}

	public void setTution(long tution) {
		this.tution = tution;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
