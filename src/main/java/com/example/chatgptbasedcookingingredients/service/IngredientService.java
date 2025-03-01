package com.example.chatgptbasedcookingingredients.service;

import com.example.chatgptbasedcookingingredients.model.OpenAiRequest;
import com.example.chatgptbasedcookingingredients.model.OpenAiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class IngredientService {
    RestClient restClient;

    public IngredientService (@Value("${BASE_URL}") String url, @Value("${AUTH_KEY}") String key) {
        restClient = RestClient.builder()
                .baseUrl(url)
                .defaultHeader("Authorization", "Bearer " + key)
                .build();

    }

    public String categorizeIngredient(String ingredient) {
        String prompt = "Is " + ingredient + " vegan, vegetarian or regular? Answer in one sentence.";
        OpenAiRequest request = new OpenAiRequest(prompt, false);

        OpenAiResponse response = restClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(OpenAiResponse.class);
        return response.getAnswer();
    }
}
