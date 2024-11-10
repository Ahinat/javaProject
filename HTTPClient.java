package com.example.realestatemanagementsystem;

import javafx.scene.control.Alert;
import javafx.util.Pair;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class HTTPClient {
    public static Pair<Integer, String> sendPostRequest(String targetUrl, String jsonInputString) throws IOException {
        URL url = new URL(targetUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        connection.setConnectTimeout(5000);  // 5 seconds
        connection.setReadTimeout(5000);     // 5 seconds

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        Integer responseCode = connection.getResponseCode();
        BufferedReader reader;

        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
        }

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        return new Pair<>(responseCode, response.toString());
    }

    public static JSONObject sendGetRequest(String targetUrl, String email) throws IOException {
        URL url = new URL(targetUrl + "/" + email);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException(("Failed : HTTP error code : " + connection.getResponseCode()));
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        connection.disconnect();

        JSONObject jsonObject = new JSONObject(response.toString());
        return jsonObject;
    }

    public static Pair<Integer, String> sendPutRequestForEditAccountDetails(String targetUrl, String jsonInputString, String email) throws IOException {
        URL url = new URL(targetUrl + "/" + email + "/accountdetails");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        Integer responseCode = connection.getResponseCode();

        BufferedReader reader;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        return new Pair<>(responseCode, response.toString());
    }

    public static Pair<Integer, String> sendPutRequestForEditLoginDetails(String targetUrl, String jsonInputString, String email) throws IOException {
        URL url = new URL(targetUrl + "/" + email + "/logindetails");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        Integer responseCode = connection.getResponseCode();

        BufferedReader reader;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        return new Pair<>(responseCode, response.toString());
    }

//    public static Pair<Integer, String> sendPutRequestForEditProfileDetails(String targetUrl, String jsonInputString, String email, File profilePicture) throws IOException {
//        String boundary = "===Boundary===";  // Custom boundary for multipart form data
//        URL url = new URL(targetUrl + "/" + email + "/profiledetails");
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("PUT");
//        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
//        connection.setDoOutput(true);
//
//        try (OutputStream os = connection.getOutputStream();
//             PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8), true)) {
//
//            // JSON part
//            writer.append("--").append(boundary).append("\r\n");
//            writer.append("Content-Disposition: form-data; name=\"userDetails\"\r\n");
//            writer.append("Content-Type: application/json; charset=UTF-8\r\n\r\n");
//            writer.append(jsonInputString).append("\r\n");
//
//            // File part if profile picture is provided
//            if (profilePicture != null && profilePicture.exists()) {
//                writer.append("--").append(boundary).append("\r\n");
//                writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"").append(profilePicture.getName()).append("\"\r\n");
//                writer.append("Content-Type: ").append(Files.probeContentType(profilePicture.toPath())).append("\r\n\r\n");
//                writer.flush();
//
//                // Write the binary file data
//                Files.copy(profilePicture.toPath(), os);
//                os.flush();
//                writer.append("\r\n");
//            }
//
//            writer.append("--").append(boundary).append("--").append("\r\n");
//            writer.flush();
//        }
//
//        // Get response code
//        Integer responseCode = connection.getResponseCode();
//
//        // Handle the response
//        BufferedReader reader;
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        } else {
//            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
//        }
//
//        StringBuilder response = new StringBuilder();
//        String responseLine;
//        while ((responseLine = reader.readLine()) != null) {
//            response.append(responseLine);
//        }
//
//        return new Pair<>(responseCode, response.toString());
//    }

    public static Pair<Integer, String> sendPutRequestForEditProfileDetails(String targetUrl, String jsonInputString, String email) throws IOException {
        URL url = new URL(targetUrl + "/" + email + "/profiledetails");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        Integer responseCode = connection.getResponseCode();

        BufferedReader reader;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = reader.readLine()) != null) {
            response.append(responseLine);
        }

        return new Pair<>(responseCode, response.toString());
    }

    public static void deleteUserAccount(Long userId) {
        try {
            String url = "http://localhost:8080/api/yourpropertyuser/delete/" + userId;
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("DELETE");
            connection.setDoOutput(true);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                Alert infoAlert = new Alert(Alert.AlertType.INFORMATION, "Account deleted successfully.");
                infoAlert.showAndWait();
                // Redirect or close the application as needed
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Failed to delete the account.");
                errorAlert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}