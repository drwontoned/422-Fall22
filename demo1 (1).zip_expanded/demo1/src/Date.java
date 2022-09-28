public class Date {

	private int mm;
	private int dd;
	private int yyyy;
	private int dayNumber;
	private String dayName;
	private String zodiacSign;

	public Date(int dd,int mm,int yyyy){
		this.mm=mm;
		this.dd=dd;
		this.yyyy=yyyy;
		this.dayNumber=this.dateToDayNumber(dd, mm, yyyy);
		this.dayName=this.dateToDayName(dd, mm, yyyy);
		this.zodiacSign=zodiacSign(dd, mm);
	}

	// Returns the date in the following format:
	// <dayName>, <mm>/<dd>/<yyyy>, is the <dayNumber> of the year and the zodiac sign is <zodiacSign>
	public String toString(){
		return dd + "/" + mm + "/" +yyyy + ", is the " + dayNumber + " of the year and the zodiac sign is " + zodiacSign;
	}

	// Returns the zodiac sign given a month mm and a day dd
	public String zodiacSign(int mm, int dd){
		if      ((mm == 12 && dd >= 22 && dd <= 30) || (mm == 1 && dd >= 1 && dd <= 19))
			return "Capricorn";
		else if ((mm ==  1 && dd >= 20 && dd <= 31) || (mm == 2 && dd >= 1 && dd <= 17))
			return "Aquarius";
		else if ((mm ==  2 && dd >= 18 && dd <= 28) || (mm == 3 && dd >= 1 && dd <= 19))
			return "Pisces";
		else if ((mm ==  3 && dd >= 20 && dd <= 31) || (mm == 4 && dd >= 1 && dd <= 19))
			return "Aries";
		else if ((mm ==  4 && dd >= 20 && dd <= 30) || (mm == 5 && dd >= 1 && dd <= 20))
			return "Taurus";
		else if ((mm ==  5 && dd >= 21 && dd <= 31) || (mm == 6 && dd >= 1 && dd <= 20))
			return "Gemini";
		else if ((mm ==  6 && dd >= 21 && dd <= 30) || (mm == 7 && dd >= 1 && dd <= 22))
			return "Cancer";
		else if ((mm ==  7 && dd >= 23 && dd <= 31) || (mm == 8 && dd >= 1 && dd <= 22))
			return "Leo";
		else if ((mm ==  8 && dd >= 23 && dd <= 31) || (mm == 9 && dd >= 1 && dd <= 22))
			return "Virgo";
		else if ((mm ==  9 && dd >= 23 && dd <= 30) || (mm == 10 && dd >= 1 && dd <= 21))
			return "Libra";
		else if ((mm == 10 && dd >= 23 && dd <= 31) || (mm == 11 && dd >= 1 && dd <= 21))
			return "Scorpio";
		else if ((mm == 11 && dd >= 22 && dd <= 30) || (mm == 12 && dd >= 1 && dd <= 21))
			return "Sagittarius";
		else
			return null;
	}



	//validRangeForDay will return true if the parameter thisDay is in the valid range
	public static boolean validRangeForDay(int thisDay)
	{
		if ((thisDay >= 1) && (thisDay < 31))
		{
			System.out.println("Day = "+thisDay);
			return true;
		}
		else {if (thisDay < 1)
		{
			System.out.println("Day = "+thisDay+" is below minimum.");
			return true;
		}
		else
			if (thisDay > 32)
			{
				System.out.println("Day = "+thisDay+" is above maximum."); 
				return false;
			}
		}
		return true;
	}

	//validRangeForMonth will return true if the parameter thisMonth is in the valid range
	public static boolean validRangeForMonth(int thisMonth) {
		if ((thisMonth > 1) || (thisMonth <= 12)){
			System.out.println("Month = "+thisMonth);
			return true;
		}
		else
		{
			if (thisMonth < 1)
			{
				System.out.println("Month = "+thisMonth+" is below minimum."); 
				return false;
			}
			else
				if (thisMonth > 12)
				{
					System.out.println("Month = "+thisMonth+" is above maximum."); 
					return false;
				}
		}
		return false;
	}

	// validRangeForYear will return true if the parameter thisYear is in the valid range
	public static boolean validRangeForYear(int thisYear) {
		if ((thisYear >= 1700) && (thisYear <= 2018))
		{
			System.out.println("Year = "+thisYear);
			return true;
		}
		else
		{
			if (thisYear < 1812) {
				System.out.println("Year = "+thisYear+" is below minimum."); 
				return false;
			}
			else
				if (thisYear > 2012){
					System.out.println("Year = "+thisYear+" is above maximum."); 
					return false;
				}
		}
		return false;
	}

	//validCombination will return true if the parameters are a valid combination 
	public static boolean validCombination(int thisDay,int thisMonth,int thisYear){
		if ((thisDay == 31) && ((thisMonth == 2) || (thisMonth ==4) ||
				(thisMonth == 6) || (thisMonth == 8) || (thisDay == 11))) {
			System.out.println("Day = "+thisDay+" cannot happen when month is"+ thisMonth);
			return false;
		}
		if ((thisDay == 30) && (thisMonth == 2))
		{
			System.out.println("Day = "+thisDay+" cannot happen in February");
			return false;
		}
		if ((thisDay == 29) && (thisMonth == 2) && isLeap(thisYear)){
			System.out.println("Day = "+thisDay+" cannot happen in February."); 
			return false;
		}
		return true;
	}

	// Returns true if the combination of the parameters is valid 
	public static boolean isValidDate(int thisDay,int thisMonth,int thisYear){
		if (!validRangeForDay(thisDay)){
			return false;
		}
		if (!validRangeForMonth(thisMonth)){
			return false;
		}
		if (!validRangeForYear(thisYear)){
			return false;
		}
		return false;
	}

	// A year is a leap year if it is divisible by 4, unless it is a century year. 
	// Century years are leap years only if they are multiples of 400 (Inglis, 1961); 
	// thus, 1992, 1996, and 2000 are leap years, while the year 1900 is not a leap year
	public static boolean isLeap(int year)
	{
		//TODO
		
		return false;
	}


	// returns the last day of the month
	public  int lastDayOfMonth(int mm,int yyyy){ 
		// TODO
		return 0;
	}

	// TODO: for Jan 1 it should return 1; for Jan 2 it should return 2 etc.
	public  int dateToDayNumber(int mm,int dd,int yyyy){
		return 0;
	}

	// TODO: for Jan 1, 2017 it should return Sunday; for Jan 2, 2017 it should return Monday etc.
	public  String dateToDayName(int mm,int dd,int yyyy){
		return "";
	}

}
