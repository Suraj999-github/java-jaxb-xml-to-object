package crud.demo.common.resources;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	public static Timestamp GET_PRESENT_TIME() {
		long currentTimeMillis = System.currentTimeMillis();
		// Create a Timestamp using the current time
		Timestamp timestamp = new Timestamp(currentTimeMillis);
		return timestamp;
	}

	public static Timestamp GET_UTC_PRESENT_TIME() throws ParseException {

		// Get the current ZonedDateTime
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
		// Define the desired pattern
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");

		// Format the ZonedDateTime using the defined pattern
		String formattedDateTime = zonedDateTime.format(formatter);

		// Define the format
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");

		// Parse the string into a Date object
		Date parsedDate = dateFormat.parse(formattedDateTime);

		// Convert the Date object to a Timestamp
		Timestamp timestamp = new Timestamp(parsedDate.getTime());

		return timestamp;

	}

	public static boolean IS_INTEGER(String value) {
		// Regular expression to match integers
		String integerRegex = "-?\\d+";

		return Pattern.matches(integerRegex, value);
	}

	public static boolean is_Null_Empty_Blank(String str) {
		return str == null || str.trim().isEmpty();

		// String testString1 = null;
		// String testString2 = "";
		// String testString3 = " "; // Whitespace string
		// String testString4 = "Hello, World!";
		// System.out.println(isNullOrEmptyOrBlank(testString1)); // true
		// System.out.println(isNullOrEmptyOrBlank(testString2)); // true
		// System.out.println(isNullOrEmptyOrBlank(testString3)); // true
		// System.out.println(isNullOrEmptyOrBlank(testString4)); // false
	}

	public static boolean IS_VALID_EMAIL(String email) {
		String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean IS_VALID_PHONE_NO(String val) {
		String regex = "^[7-9][0-9]{9}$";
        return val.matches(regex);
	}
	
	public static boolean IS_NAME(String val) {
		String name = "^[A-Za-z ]*$";
        return val.matches(name);
	}

	public static String DATE_YYYYMMDD(Date value) {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy/MM/dd");
		return (sm.format(value));

	}

	public static Boolean ISNULL_OR_LESS_THAN_EQUAL_TO_ZERO(Long id) {
		return id == null || id <= 0;
	}

	public static String TO_CAMEL_CASE(String letter) {
		// Replace non-alphanumeric characters (except spaces) with spaces
		String cleanedName = letter.replaceAll("[^a-zA-Z0-9\\s]", " ");

		// Split the string into words
		String[] words = cleanedName.split("\\s+");

		StringBuilder camelCaseBuilder = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			String word = words[i].toLowerCase();
			if (i > 0) {
				// Capitalize the first letter of each word (except for the first word)
				word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
			}
			camelCaseBuilder.append(word);
		}

		return camelCaseBuilder.toString();
	}

	public static final <T> String FORMAT_DATE_TO_YYYYMMDD(T date) {
		if (date instanceof LocalDateTime) {
			return ((LocalDateTime) date).toLocalDate().toString();
		} else if (date instanceof LocalDate) {
			return date.toString();
		} else if (date instanceof Timestamp) {
			return ((Timestamp) date).toLocalDateTime().toLocalDate().toString();
		} else if (date instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format((Date) date);
		} else {
			return null;
		}
	}

	public static final <T> String FORMAT_DATE_TO_YYYYMMDDHHMM(T date) {
		if (date instanceof LocalDateTime) {
			return ((LocalDateTime) date).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		} else if (date instanceof Timestamp) {
			return ((Timestamp) date).toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		} else {
			return null;
		}
	}

	public static final <T> String FORMAT_DATE_TO_YYYYMMDDHHMMSS(T date) {
		if (date instanceof LocalDateTime) {
			return ((LocalDateTime) date).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		} else if (date instanceof Timestamp) {
			return ((Timestamp) date).toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		} else {
			return null;
		}
	}	

	// Amount Formatter

	public static String FORMAT_AMOUNT(String currencyValue) {
		if (currencyValue == null || currencyValue.trim().isEmpty()) {
			return "0";
		}

		try {
			String numericValue = currencyValue.replaceAll("[^\\d.]", "");
			BigDecimal amount = new BigDecimal(numericValue);
			DecimalFormat formatter = new DecimalFormat("#,###");
			return formatter.format(amount.setScale(0, RoundingMode.DOWN));
		} catch (Exception e) {
			return "0";
		}
	}

	public static String FORMAT_AMOUNT_WITH_DECIMAL_PLACE(String currencyValue) {
		if (currencyValue == null || currencyValue.trim().isEmpty()) {
			return "0";
		}

		try {
			//boolean hasDecimal = currencyValue.contains(".");
			String numericValue = currencyValue.replaceAll("[^\\d.]", "");
			BigDecimal amount = new BigDecimal(numericValue);
			DecimalFormat formatter = new DecimalFormat("#,###.##");
			String formattedAmount = formatter.format(amount.setScale(2, RoundingMode.DOWN));			
			return formattedAmount;
		} catch (Exception e) {
			return currencyValue;
		}
	}

	public static String FORMAT_AMOUNT(BigDecimal currencyValue) {
		if (currencyValue == null) {
			return "0";
		}

		try {
			DecimalFormat formatter = new DecimalFormat("#,###");
			return formatter.format(currencyValue.setScale(0, RoundingMode.DOWN));
		} catch (Exception e) {
			return currencyValue.toString();
		}
	}

	public static String FORMAT_AMOUNT_WITH_DECIMAL_PLACE(BigDecimal currencyValue) {
		if (currencyValue == null) {
			return "0";
		}

		try {
			DecimalFormat formatter = new DecimalFormat("#,###.00");
			return formatter.format(currencyValue.setScale(2, RoundingMode.DOWN));
		} catch (Exception e) {
			return currencyValue.toString();
		}
	}

	public static Date WEEKLY_START_DAY() {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

			return sdf.parse(sdf.format(calendar.getTime()));
		} catch (Exception e) {
			return null;
		}
	}
	public static Date MONTHLY_START_DAY() {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			return sdf.parse(sdf.format(calendar.getTime()));
		}catch (Exception e){
			return null;
		}
	}

	public static Date TODAY_DATE() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(sdf.format(new Date()));
		}catch (Exception e){
			return null;
		}
	}

	public static Date YEARLY_START_DAY() {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			calendar.set(Calendar.DAY_OF_YEAR, 1);
			return sdf.parse(sdf.format(calendar.getTime()));
		}catch (Exception e){
			return null;
		}
	}


	public static <T extends Enum<T>> boolean IS_VALID_ENUM_VALUE(String value, Class<T> enumClass) {
		try {
			Enum.valueOf(enumClass, value);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	public static int COUNT_LOWER_CASE_CHARACTERS(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isLowerCase(ch)) {
                count++;
            }
        }
        return count;
    }
	public static int COUNT_UPPER_CASE_CHARACTERS(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                count++;
            }
        }
        return count;
    }
	public static int COUNT_CHARACTERS(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {                   
                count++;            
        }
        return count;
    }
	public static int COUNT_DIGIT(String str) {
		 int count = 0;
	        for (int i = 0; i < str.length(); i++) {
	            char ch = str.charAt(i);
	            if (Character.isDigit(ch)) {
	                count++;
	            }
	        }
	        return count;
    }
	public static int COUNT_SPECIAL_CHARACTERS(String str) {
		 int count = 0;
	        // Define a set of special characters
	        String specialCharacters = "!@#$%^&*()_+{}:<>?|[]\\;'/,.";
	        
	        // Loop through each character in the string
	        for (int i = 0; i < str.length(); i++) {
	            char ch = str.charAt(i);
	            // Check if the character is a special character
	            if (specialCharacters.contains(String.valueOf(ch))) {
	                count++;
	            }
	        }
	        return count;
   }

	public static Timestamp RETURN_TIMESTAMP(String data) throws ParseException {
		
		// Get the first 26 characters from the original string
        String first26Characters = data.substring(0, Math.min(data.length(), 26));  
		
		// Define the date-time format pattern
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		// Parse the string to a Date object
		Date parsedDate = dateFormat.parse(first26Characters);
		// Convert the Date object to a Timestamp object
		Timestamp timestamp = new Timestamp(parsedDate.getTime());
		return timestamp;
	}
}
