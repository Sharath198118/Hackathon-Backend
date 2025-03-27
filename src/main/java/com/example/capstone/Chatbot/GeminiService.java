package com.example.capstone.Chatbot;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {
	@Value("${custom.feature.geminiAPI}")
    private String geminiAPI;

//	@Async
    public String getResponse(String text) throws URISyntaxException, IOException, InterruptedException, ExecutionException, TimeoutException {
        String response =null;
        String requestBody = "{\n" +
                "    \"contents\": [\n" +
                "        {\n" +
                "            \"parts\": [\n" +
                "                {\n" +
                "                    \"text\": \""+ text +"\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
//        System.out.println(HttpRequest.BodyPublishers.ofString(requestBody));
        
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(new URI("https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key="+geminiAPI))
//                .version(HttpClient.Version.HTTP_2)
//                .header("Content-Type", "application/json")
//                .timeout(Duration.of(15, ChronoUnit.SECONDS))
//                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
//                .build();
//        
//        HttpClient client =HttpClient.newBuilder().build();
//        HttpResponse<String> httpResponse = client.send(request,
//                HttpResponse.BodyHandlers.ofString());
//          response=  httpResponse.body();
//        System.out.println(httpResponse.statusCode());
        
        String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key="+geminiAPI;
     // Create HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();

        // Define the request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        
        CompletableFuture<HttpResponse<String>> responseCF = null;
        responseCF = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        
        response = responseCF.thenApplyAsync((resp) -> {
            int status = resp.statusCode();
            if (status != 200) {
            	return "";
            } else {
            	return resp.body();
            }
        }).get(20, TimeUnit.SECONDS);
        
        
        
        
//        response = responseCF.thenApply(HttpResponse::body).get(20, TimeUnit.SECONDS);
//        System.out.println(response);
        
        return response;
    }
}
