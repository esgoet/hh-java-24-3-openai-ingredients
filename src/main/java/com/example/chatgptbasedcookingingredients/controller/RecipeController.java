package com.example.chatgptbasedcookingingredients.controller;

import com.example.chatgptbasedcookingingredients.model.Recipe;
import com.example.chatgptbasedcookingingredients.service.RecipeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService service;

    @PostMapping
    public Recipe getRecipe(@RequestBody String ingredients) throws JsonProcessingException {
        return service.getRecipe(ingredients);
    }



}
