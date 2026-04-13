package com.example.motarjimapp.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import jakarta.enterprise.context.ApplicationScoped;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@ApplicationScoped
public class LLMService {

    private static final String API_KEY = "AIzaSyDzkd5jIkdyIoF8p9xq_g2QU4GMTfJok4Q";
    private static final String URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.1-flash-lite-preview:generateContent?key=" + API_KEY;

    private final HttpClient client = HttpClient.newHttpClient();

    public String translate(String text) {

        try {
            String body = """
            {
              "contents": [
                {
                  "parts": [
                    {
                      "text": "You are a professional translator.
                                                                Translate the following text into Moroccan Darija.
                                                                Preserve correct RTL formatting.
                                                                Return ONLY the translation without explanations: %s"
                    }
                  ]
                }
              ]
            }
            """.formatted(text);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            return extractText(response.body());

        } catch (Exception e) {
            return "Error calling Gemini: " + e.getMessage();
        }
    }

    private String extractText(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);

            return root.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

        } catch (Exception e) {
            return "Parsing error: " + e.getMessage();
        }
    }
}
