package tool.rental.tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tool.rental.checkout.Checkout;

import tool.rental.entities.RentalAgreement;

class ToolRentalTest {
	
	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);

	@Test
	void checkout_testCase1() throws Exception {
		Date checkoutDate = formatter.parse("9/3/15");
		
		assertThrows(Exception.class, 
				() -> Checkout.checkout("JAKR", 5, 101, checkoutDate), 
				"Discount percent needs to be between 0 and 100.");
	}
	
	@Test
	void checkout_testCase2() throws Exception {
		Date checkoutDate = formatter.parse("7/2/20");
		
		RentalAgreement agreement = Checkout.checkout("LADW", 3, 10, checkoutDate);
		
		assertEquals(formatter.parse("7/5/20"), agreement.getDueDate());
		assertEquals(Double.valueOf(1.99), agreement.getTool().getDailyCharge());
		assertEquals(2, agreement.getChargeDays());
		assertEquals(Double.valueOf(3.98), agreement.getPreDiscountCharge());
		assertEquals(Double.valueOf(0.40), agreement.getDiscountAmount());
		assertEquals(Double.valueOf(3.58), agreement.getFinalCharge());
	}
	
	@Test
	void checkout_testCase3() throws Exception {
		Date checkoutDate = formatter.parse("7/2/15");
		
		RentalAgreement agreement = Checkout.checkout("CHNS", 5, 25, checkoutDate);
		
		assertEquals(formatter.parse("7/7/15"), agreement.getDueDate());
		assertEquals(Double.valueOf(1.49), agreement.getTool().getDailyCharge());
		assertEquals(3, agreement.getChargeDays());
		assertEquals(Double.valueOf(4.47), agreement.getPreDiscountCharge());
		assertEquals(Double.valueOf(1.12), agreement.getDiscountAmount());
		assertEquals(Double.valueOf(3.35), agreement.getFinalCharge());
	}

	@Test
	void checkout_testCase4() throws Exception {
		Date checkoutDate = formatter.parse("9/3/15");
		
		RentalAgreement agreement = Checkout.checkout("JAKD", 6, 0, checkoutDate);
		
		assertEquals(formatter.parse("9/9/15"), agreement.getDueDate());
		assertEquals(Double.valueOf(2.99), agreement.getTool().getDailyCharge());
		assertEquals(3, agreement.getChargeDays());
		assertEquals(Double.valueOf(8.97), agreement.getPreDiscountCharge());
		assertEquals(Double.valueOf(0), agreement.getDiscountAmount());
		assertEquals(Double.valueOf(8.97), agreement.getFinalCharge());
	}
	
	@Test
	void checkout_testCase5() throws Exception {
		Date checkoutDate = formatter.parse("7/2/15");
		
		RentalAgreement agreement = Checkout.checkout("JAKR", 9, 0, checkoutDate);
		
		assertEquals(formatter.parse("7/11/15"), agreement.getDueDate());
		assertEquals(Double.valueOf(2.99), agreement.getTool().getDailyCharge());
		assertEquals(5, agreement.getChargeDays());
		assertEquals(Double.valueOf(14.95), agreement.getPreDiscountCharge());
		assertEquals(Double.valueOf(0), agreement.getDiscountAmount());
		assertEquals(Double.valueOf(14.95), agreement.getFinalCharge());
	}
	
	@Test
	void checkout_testCase6() throws Exception {
		Date checkoutDate = formatter.parse("7/2/20");
		
		RentalAgreement agreement = Checkout.checkout("JAKR", 4, 50, checkoutDate);
		
		assertEquals(formatter.parse("7/6/20"), agreement.getDueDate());
		assertEquals(Double.valueOf(2.99), agreement.getTool().getDailyCharge());
		assertEquals(1, agreement.getChargeDays());
		assertEquals(Double.valueOf(2.99), agreement.getPreDiscountCharge());
		assertEquals(Double.valueOf(1.50), agreement.getDiscountAmount());
		assertEquals(Double.valueOf(1.49), agreement.getFinalCharge());
	}
}
