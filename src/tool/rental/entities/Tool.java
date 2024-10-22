package tool.rental.entities;

public class Tool {
	
	enum ToolTypes {
		CHAINSAW("Chainsaw"),
		LADDER("Ladder"),
		JACKHAMMER("Jackhammer");
		private String value;

		ToolTypes(String value) {
			this.value = value;
		}
	}
	
	enum Brands {
		STIHL("Stihl"),
		WERNER("Werner"),
		DEWALT("DeWalt"),
		RIDGID("Ridgid");
		private String value;

		Brands(String value) {
			this.value = value;
		}
	}
	
	enum DailyCharges {
		LADDER(1.99),
		CHAINSAW(1.49),
		JACKHAMMER(2.99);
		private double value;
		
		DailyCharges(double value) {
			this.value = value;
		}
	}

	// selects a tool based on the provided toolCode
	public Tool(String toolCode) {
		this.toolCode = toolCode;
		this.weekdayCharge = true;
		
		switch (toolCode) {
		case "CHNS": 
			this.toolType = ToolTypes.CHAINSAW.value;
			this.toolBrand = Brands.STIHL.value;
			this.dailyCharge = DailyCharges.CHAINSAW.value;
			this.weekendCharge = false;
			this.holidayCharge = true;
			break;
		case "LADW":
			this.toolType = ToolTypes.LADDER.value;
			this.toolBrand = Brands.STIHL.value;
			this.dailyCharge = DailyCharges.LADDER.value;
			this.weekendCharge = true;
			this.holidayCharge = false;
			break;
		case "JAKD":
			this.toolType = ToolTypes.JACKHAMMER.value;
			this.toolBrand = Brands.DEWALT.value;
			this.dailyCharge = DailyCharges.JACKHAMMER.value;
			this.weekendCharge = false;
			this.holidayCharge = false;
			break;
		case "JAKR":
			this.toolType = ToolTypes.JACKHAMMER.value;
			this.toolBrand = Brands.RIDGID.value;
			this.dailyCharge = DailyCharges.JACKHAMMER.value;
			this.weekendCharge = false;
			this.holidayCharge = false;
		}
			
	}
	
	private String toolCode;
	private String toolType;
	private String toolBrand;
	private Double dailyCharge;
	private boolean weekdayCharge;
	private boolean weekendCharge;
	private boolean holidayCharge;
	
	public String getToolCode() {
		return toolCode;
	}
	
	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}
	
	public String getToolType() {
		return toolType;
	}
	
	public void setToolType(String toolType) {
		this.toolType = toolType;
	}
	
	public String getToolBrand() {
		return toolBrand;
	}
	
	public void setToolBrand(String toolBrand) {
		this.toolBrand = toolBrand;
	}
	
	public Double getDailyCharge() {
		return dailyCharge;
	}
	
	public void setDailyCharge(Double dailyCharge) {
		this.dailyCharge = dailyCharge;
	}
	
	public boolean isWeekdayCharge() {
		return weekdayCharge;
	}
	
	public void setWeekdayCharge(boolean weekdayCharge) {
		this.weekdayCharge = weekdayCharge;
	}
	
	public boolean isWeekendCharge() {
		return weekendCharge;
	}
	
	public void setWeekendCharge(boolean weekendCharge) {
		this.weekendCharge = weekendCharge;
	}
	
	public boolean isHolidayCharge() {
		return holidayCharge;
	}
	
	public void setHolidayCharge(boolean holidayCharge) {
		this.holidayCharge = holidayCharge;
	}
}
