package tool.rental.checkout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import tool.rental.entities.RentalAgreement;
import tool.rental.entities.Tool;

public class Checkout {
	
	public static final String LINE_BREAK = "\n";
	
	public static final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
	
	public static RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, Date checkoutDate) throws Exception {
		
		// check right away for invalid rentalDayCount and discountPercent so we don't waste resources
		if (rentalDayCount < 1) {
			throw new Exception("Rental day count must be 1 or greater.");
		} else if (!(discountPercent >= 0 && discountPercent <= 100)) {
			throw new Exception("Discount percent needs to be between 0 and 100.");
		}
		
		RentalAgreement agreement = new RentalAgreement();
		Tool tool = new Tool(toolCode);
		
		agreement.setTool(tool);
		agreement.setRentalDayCount(rentalDayCount);
		agreement.setDiscountPercent(discountPercent);
		agreement.setCheckOutDate(checkoutDate);
		
		// adding the rental days to get the due date
		Calendar cal = Calendar.getInstance();
		cal.setTime(checkoutDate);
		
		Calendar dueDate = Calendar.getInstance();
		dueDate.setTime(checkoutDate);
		dueDate.add(Calendar.DATE, rentalDayCount);
		agreement.setDueDate(dueDate.getTime());
		
		int chargeDays = 0;
		
		for (int i = 1; i <= rentalDayCount; i++) {
			cal.add(Calendar.DATE, 1);
			
			Calendar floatHoliday = Calendar.getInstance();
			floatHoliday.setTime(cal.getTime());
			
			// Fourth of July Check
			if (!tool.isHolidayCharge() && ((cal.get(Calendar.DAY_OF_MONTH) == 4 && cal.get(Calendar.MONTH) == Calendar.JULY))) {
				if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
					floatHoliday.add(Calendar.DATE, -1);
					
					if (floatHoliday.getTime().getTime() > checkoutDate.getTime()) {
						if (tool.isWeekendCharge()) {
							continue;
						} else {
							chargeDays--;
						}
					}
				} else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
					floatHoliday.add(Calendar.DATE, 1);
					if (floatHoliday.getTime().getTime() <= checkoutDate.getTime()) {
						if (tool.isWeekendCharge()) {
							continue;
						} else {
							chargeDays--;
						}
					}
				} else {
					continue;
				}
			// Labor Day Check
			} else if (!tool.isHolidayCharge() && (cal.get(Calendar.MONTH) == Calendar.SEPTEMBER && cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY && cal.get(Calendar.DAY_OF_MONTH) <= 7)) {
				continue;
			} else if (!tool.isWeekendCharge() && 
					(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
				continue;
			} else {
				chargeDays++;
			}
		}
		
		agreement.setChargeDays(chargeDays);
		
		BigDecimal preDiscountCharge = new BigDecimal(tool.getDailyCharge() * chargeDays);
		preDiscountCharge = preDiscountCharge.setScale(2, RoundingMode.HALF_UP);
		agreement.setPreDiscountCharge(preDiscountCharge.doubleValue());
		
		BigDecimal discountAmount = new BigDecimal((discountPercent * 0.01) * agreement.getPreDiscountCharge());
		discountAmount = discountAmount.setScale(2, RoundingMode.HALF_UP);
		agreement.setDiscountAmount(discountAmount.doubleValue());
		
		BigDecimal finalCharge = new BigDecimal(agreement.getPreDiscountCharge() - agreement.getDiscountAmount());
		finalCharge = finalCharge.setScale(2, RoundingMode.HALF_UP);
		agreement.setFinalCharge(finalCharge.doubleValue());
		
		System.out.println("Tool code: " + agreement.getTool().getToolCode() + LINE_BREAK
				+ "Tool type: " + agreement.getTool().getToolType() + LINE_BREAK
				+ "Tool brand: " + agreement.getTool().getToolBrand() + LINE_BREAK
				+ "Rental days: " + agreement.getRentalDayCount() + LINE_BREAK
				+ "Check out date: " + formatter.format(agreement.getCheckOutDate()) + LINE_BREAK
				+ "Due date: " + formatter.format(agreement.getDueDate()) + LINE_BREAK
				+ "Daily rental charge: $" + agreement.getTool().getDailyCharge() + LINE_BREAK
				+ "Charge days: " + agreement.getChargeDays() + LINE_BREAK
				+ "Pre-discount charge: $" + agreement.getPreDiscountCharge() + LINE_BREAK
				+ "Discount percent: " + agreement.getDiscountPercent() + "%" + LINE_BREAK
				+ "Discount amount: $" + String.format("%.2f", agreement.getDiscountAmount()) + LINE_BREAK
				+ "Final charge: $" + agreement.getFinalCharge());
		
		return agreement;
	}

}
