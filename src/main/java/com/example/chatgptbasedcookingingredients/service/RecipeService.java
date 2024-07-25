package com.example.chatgptbasedcookingingredients.service;

import com.example.chatgptbasedcookingingredients.model.OpenAiRequest;
import com.example.chatgptbasedcookingingredients.model.OpenAiResponse;
import com.example.chatgptbasedcookingingredients.model.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;



@Service
public class RecipeService {
    RestClient restClient;

    public RecipeService (@Value("${BASE_URL}") String url, @Value("${AUTH_KEY}") String key) {
        restClient = RestClient.builder()
                .baseUrl(url)
                .defaultHeader("Authorization", "Bearer " + key)
                .build();

    }
    public Recipe getRecipe(String ingredients) throws JsonProcessingException {
        OpenAiRequest request = new OpenAiRequest("Give me a recipe for a dish in JSON Format with a title, description, instructions in form of a step-by-step list and a list of ingredients with only their name, containing these ingredients: " + ingredients, true);

        OpenAiResponse response = restClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(OpenAiResponse.class);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.getAnswer(), Recipe.class);
    }
}
