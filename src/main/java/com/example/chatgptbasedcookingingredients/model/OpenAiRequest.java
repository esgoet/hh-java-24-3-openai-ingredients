package com.example.chatgptbasedcookingingredients.model;

import java.util.List;

public record OpenAiRequest(
        String model,
        List<OpenAiMessage> messages,
        Double temperature,
        OpenAiFormat response_format
) {
    public OpenAiRequest(String message, boolean jsonAnswer) {
        this("gpt-4o-mini", List.of(new OpenAiMessage("user", message)), 0.2, jsonAnswer ? new OpenAiFormat("json_object") : null);
    }
}
