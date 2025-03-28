package com.marsrover.app;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloader {
    public static void downloadImage(String imageUrl) {
        try {
            File directory = new File("images");
            if (!directory.exists()) {
                directory.mkdir();
            }

            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(true); // Allow redirection

            connection.setRequestMethod("GET");

            // Manually follow redirects if needed
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
                String newUrl = connection.getHeaderField("Location");
                System.out.println("🔄 Redirecting to: " + newUrl);
                url = new URL(newUrl);
                connection = (HttpURLConnection) url.openConnection();
            }

            // Check final response code
            responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("❌ Failed to fetch image. HTTP Response: " + responseCode);
                return;
            }

            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
            File outputFile = new File(directory, fileName);

            try (BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
                 BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream)) {

                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            System.out.println("✅ Image saved successfully: " + outputFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Failed to download image: " + imageUrl);
        }
    }
}
