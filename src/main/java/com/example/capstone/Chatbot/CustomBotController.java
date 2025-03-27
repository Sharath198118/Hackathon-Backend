package com.example.capstone.Chatbot;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

//import com.javatechie.dto.ChatGPTRequest;
//import com.javatechie.dto.ChatGptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.capstone.DTO.MessageResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Bot")
public class CustomBotController {

//    @Value("${openai.model}")
//    private String model;

//    @Value("${openai.api.url}")
//    private String apiURL;

    @Autowired
    GeminiService geminiService;

//    @GetMapping("/chat")
//    public String chat(@RequestParam("prompt") String prompt) {
//        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
//        ChatGptResponse chatGptResponse = template.postForObject(apiURL, request, ChatGptResponse.class);
//        return chatGptResponse.getChoices().get(0).getMessage().getContent();
//    }
    @PostMapping("/recommend")
    public ResponseEntity<MessageResponse> recommend(@RequestBody(required=false) @Valid DataDTO data) throws URISyntaxException, IOException, InterruptedException, ExecutionException, TimeoutException {
    	String response =geminiService.getResponse(data.getData()+"\nfor the above above idea return only the best tools required for building an app in format of an array of json object containing field name, description and link where name and description are self explantory and link contains the link for the documentation of the tool and no other additional text, Dont even separate into different uses. Don't repeat frameworks which serve the same purpose..Dont put into code block.");
    	if(response.isEmpty() || response.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Error occured while accessing Gemini API."));
    	} else {
    		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(response));
    	}
    }
    
    @PostMapping("/rewrite")
    public ResponseEntity<MessageResponse> rewrite(@RequestBody(required=false) @Valid DataDTO data) throws URISyntaxException, IOException, InterruptedException, ExecutionException, TimeoutException {
    	String response =geminiService.getResponse(data.getData()+"\nfor the above idea rewrite with correct grammar and expand on it but keep the character count below 2500 character[mandatory]. Write in a tone that represents your team proposing a solution and no other additional text, Dont even separate into different uses. Return basic text paragraph form without any formatting like bold italic etc or points wise. Dont add extra text at the start or end. Just return the idea. [mandatory]");
    	if(response.isEmpty() || response.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Error occured while accessing Gemini API."));
    	} else {
    		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(response));
    	}
    }
}
