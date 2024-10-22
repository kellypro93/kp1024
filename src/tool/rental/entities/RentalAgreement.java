package tool.rental.entities;

import java.util.Date;

public class RentalAgreement {
	
	public RentalAgreement() {
		// a blank constructor
	}
	
	private Tool tool;
	private int rentalDayCount;
	private Date checkOutDate;
	private Date dueDate;
	private int chargeDays;
	private Double preDiscountCharge;
	private int discountPercent;
	private Double discountAmount;
	private Double finalCharge;

	public Tool getTool() {
		return tool;
	}
	
	public void setTool(Tool tool) {
		this.tool = tool;
	}
	
	public int getRentalDayCount() {
		return rentalDayCount;
	}
	
	public void setRentalDayCount(int rentalDayCount) {
		this.rentalDayCount = rentalDayCount;
	}
	
	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public int getChargeDays() {
		return chargeDays;
	}
	
	public void setChargeDays(int chargeDays) {
		this.chargeDays = chargeDays;
	}
	
	public Double getPreDiscountCharge() {
		return preDiscountCharge;
	}
	
	public void setPreDiscountCharge(Double preDiscountCharge) {
		this.preDiscountCharge = preDiscountCharge;
	}
	
	public int getDiscountPercent() {
		return discountPercent;
	}
	
	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}
	
	public Double getDiscountAmount() {
		return discountAmount;
	}
	
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	
	public Double getFinalCharge() {
		return finalCharge;
	}
	
	public void setFinalCharge(Double finalCharge) {
		this.finalCharge = finalCharge;
	}
}
