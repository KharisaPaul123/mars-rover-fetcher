package com.marsrover.app;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static String formatDate(String inputDate) {
        String[] possibleFormats = {
                "MM/dd/yy", "MMMM d, yyyy", "MMM-dd-yyyy", "yyyy-MM-dd"
        };

        for (String format : possibleFormats) {
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat(format);
                inputFormat.setLenient(false);
                Date date = inputFormat.parse(inputDate);

                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                return outputFormat.format(date);
            } catch (ParseException ignored) {
                // Try next format
            }
        }

        System.out.println("Invalid date format: " + inputDate);
        return null;
    }
}
