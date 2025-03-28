package com.marsrover.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MarsRoverImageFetcher {

    public static void main(String[] args) {
        // Step 1: Read the dates from the file
        List<String> dates = readDatesFromFile("dates.txt");

        // Step 2: Convert dates from 'dates.txt' to YYYY-MM-DD format
        Set<String> formattedDates = new HashSet<>();
        for (String date : dates) {
            String formattedDate = formatDate(date);
            if (!formattedDate.isEmpty()) {
                formattedDates.add(formattedDate);
            } else {
                System.out.println("❌ Invalid date format: " + date); // Log invalid date
            }
        }

        // Step 3: For each valid date, process the images from NASA API
        Map<String, Integer> dateImageCount = new HashMap<>();  // Track image numbering for each date

        for (String date : formattedDates) {
            // Step 4: Fetch images from NASA API based on the date
            String apiUrl = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=" + date + "&api_key=IYmPGwgwWHtHoOSIBbRuTmC7rVgiux1UAvqI5Uhr";

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int status = connection.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // Step 5: Parse JSON response
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode rootNode = mapper.readTree(response.toString());
                    JsonNode photos = rootNode.path("photos");

                    if (photos.isEmpty()) {
                        System.out.println("No photos found for date: " + date);
                    }

                    // Step 6: Process each photo
                    for (JsonNode photo : photos) {
                        String imageUrl = photo.path("img_src").asText();
                        String earthDate = photo.path("earth_date").asText();
                        String newFileName = generateFileName(date, dateImageCount);

                        // Step 7: Download the image
                        System.out.println("Downloading image: " + newFileName);
                        ImageDownloader.downloadImage(imageUrl, newFileName);
                    }
                } else {
                    System.out.println("Failed to fetch data. HTTP code: " + status);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Step 1: Read dates from the text file
    private static List<String> readDatesFromFile(String filename) {
        List<String> dates = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                dates.add(line.trim());  // Add each date from the file
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dates;
    }

    // Step 2: Format the date to YYYY-MM-DD
    public static String formatDate(String date) {
        SimpleDateFormat inputFormat = null;
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Match different formats in the input file
        if (date.matches("\\d{2}/\\d{2}/\\d{2}")) { // MM/DD/YY
            inputFormat = new SimpleDateFormat("MM/dd/yy");
        } else if (date.matches("[A-Za-z]+ \\d{1,2}, \\d{4}")) { // Month DD, YYYY
            inputFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        } else if (date.matches("[A-Za-z]{3}-\\d{2}-\\d{4}")) { // Mon-DD-YYYY
            inputFormat = new SimpleDateFormat("MMM-dd-yyyy", Locale.ENGLISH);
        }

        try {
            if (inputFormat != null) {
                return outputFormat.format(inputFormat.parse(date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";  // Return empty string for unrecognized formats
    }

    // Step 3: Generate filenames with an index for each image
    public static String generateFileName(String date, Map<String, Integer> dateImageCount) {
        int count = dateImageCount.getOrDefault(date, 0) + 1;
        dateImageCount.put(date, count);

        return "MarsRover_" + date + "_" + count + ".jpg";
    }
}

