
package com.marsrover.app;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DateReader {
    public static List<String> readDates(String fileName) {
        List<String> dates = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                dates.add(line.trim());  // Add each date from the file
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dates;
    }
}
