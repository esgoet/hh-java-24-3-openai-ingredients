package com.example.chatgptbasedcookingingredients.model;

import java.util.List;

public record Recipe(
        String title,
        String description,
        List<String> instructions,
        List<String> ingredients
) {
}
