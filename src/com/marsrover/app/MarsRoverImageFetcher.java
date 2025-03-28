package com.marsrover.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MarsRoverImageFetcher {
    public static void main(String[] args) {
        // Read the dates from the file
        List<String> dates = DateReader.readDates("dates.txt");

        for (String date : dates) {
            // Convert the Earth date to sol (for now, use a placeholder sol value)
            int sol = convertDateToSol(date);  // You can implement this later with actual conversion logic

            // Format the URL with the sol value
            String apiUrl = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=" + sol + "&api_key=IYmPGwgwWHtHoOSIBbRuTmC7rVgiux1UAvqI5Uhr";

            try {
                // Create a URL object and open a connection
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Set the request method to GET
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);  // Set timeout in milliseconds
                connection.setReadTimeout(5000);     // Set read timeout in milliseconds

                // Get the response code
                int status = connection.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    // Read the response
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // Convert the JSON response to a Java object
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode rootNode = mapper.readTree(response.toString());

                    // Extract and process image URLs
                    JsonNode photos = rootNode.path("photos");
                    for (JsonNode photo : photos) {
                        String imageUrl = photo.path("img_src").asText();
                        System.out.println("Image URL: " + imageUrl);

                        // Call the downloadImage method to save the image locally
                        ImageDownloader.downloadImage(imageUrl);
                    }
                } else {
                    System.out.println("HTTP request failed with status: " + status);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Placeholder method for converting Earth date to Sol (you can implement actual conversion later)
    private static int convertDateToSol(String date) {
        // For now, return a dummy sol value
        return 1000;
    }
}





