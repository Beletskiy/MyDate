package Practic1.Date;

public class Date {

	private Year year;
	private Month month;
	private Day day;
	private static final DayOfWeek DAY_OF_WEEK_01_01_1970 = DayOfWeek.THURSDAY;

	public enum DayOfWeek {
		MONDAY(0), TUESDAY(1), WEDNESDAY(2), THURSDAY(3), FRIDAY(4), SATURDAY(5), SUNDAY(
				6);

		private int index;

		private DayOfWeek(int index) {
			this.index = index;
		}

		public static DayOfWeek valueOf(int index) {
			DayOfWeek result = null;
			if (index < 0 || index > 6) {
				throw new IllegalArgumentException("Illegal day-of-week index "
						+ index + ". " + "It's value must be between 0 and 6.");
			}
			for (DayOfWeek dow : DayOfWeek.values()) {
				if (dow.getIndex() == index) {
					result = dow;
				}
			}
			return result;
		}

		public int getIndex() {
			return index;
		}
	}

	public Date(int day, int month, int year) {
		this.year = new Year(year);
		this.month = new Month(month);
		this.day = new Day(day);
	}

	public Year getYear() {
		return year;
	}

	public Month getMonth() {
		return month;
	}

	public Day getDay() {
		return day;
	}

	public DayOfWeek getDayOfWeek() {
		Date beginDate = new Date(1, 1, 1970);
		int differenceInDays = daysBetween(beginDate);
		int indexOfDayOfWeek = (DAY_OF_WEEK_01_01_1970.getIndex() + differenceInDays) % 7;
		return DayOfWeek.valueOf(indexOfDayOfWeek);
	}

	@Override
	public String toString() {
		return "Date: " + year.getYear() + "-" + month.getMonth() + "-"
				+ day.getDay() + " --- " + getDayOfWeek();
	}

	public int daysBetween(Date date) {
		int daysToThis = getDaysFrom_01011970(this);
		int daysToThat = getDaysFrom_01011970(date);
		int difference = daysToThat - daysToThis;
		return (difference >= 0) ? difference : -difference;
	}

	private int getDaysFrom_01011970(Date date) {
		int year = date.getYear().getYear();
		int month = date.getMonth().getMonth();
		int day = date.getDay().getDay();
		boolean leapYear = date.getYear().isLeapYear();
		int result = 0;
		
		// calculate days in years
		for (int iYears = 0; iYears < year - 1970; iYears++) {
			result += new Year(1970 + iYears).getDays();
		}
		//calculate days in months
		for (int iMonths = 0; iMonths < month - 1; iMonths++) {
			result += new Month(1 + iMonths).getDays(leapYear);
		}
		// calculate days
		result += day - 1;
		return result;
	}

	private class Year {
		private int year;
		private boolean leapYear; 

		Year(int year) {
			if (year < 1970 || year > 2099) {
				throw new IllegalArgumentException("Illegal value of year: "
						+ year + ". " + "Must be between 1970 and 2099.");
			}
			this.year = year;
			if (year % 4 == 0) {
				leapYear = true;
			} else {
				leapYear = false;
			}
		}

		public int getYear() {
			return year;
		}

		public int getDays() {
			return leapYear ? 366 : 365;
		}

		public boolean isLeapYear() {
			return leapYear;
		}
	}

	private class Month {
		private int month;

		Month(int month) {
			if (month < 1 || month > 12) {
				throw new IllegalArgumentException("Illegal value of month: "
						+ month + ". " + "Must be between 1 and 12.");
			}
			this.month = month;
		}

		public int getMonth() {
			return month;
		}

		public int getDays(boolean leapYear) {
			int result = 31;
			switch (month) {
			case 2:
				if (leapYear) {
					result = 29;
				} else {
					result = 28;
				}
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				result = 30;
				break;
			}
			return result;
		}
	}

	private class Day {
		private int day;

		Day(int day) {
			if (day < 1 || day > 31) {
				throw new IllegalArgumentException("Illegal value of day: "
						+ day + ". " + "Must be between 1 and 31.");
			}
			this.day = day;
		}

		public int getDay() {
			return day;
		}
	}
}
