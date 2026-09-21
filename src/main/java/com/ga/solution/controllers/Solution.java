package com.ga.solution.controllers;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class Solution {

    private final Environment env;
    private final List<String> interests;

    public Solution(Environment env) {
        this.env = env;
        String interestsValue = env.getProperty("app.profile.learning");
        assert interestsValue != null;
        interests = new ArrayList<>(Arrays.asList(interestsValue.split(",")));
    }

    @GetMapping("/welcome")
    public ResponseEntity<Map<String, Object>> welcome() {
        String appName = env.getProperty("spring.application.name");
        String myName = env.getProperty("app.profile.name");
        String summary = env.getProperty("app.profile.introduction");
        String chosenTheme = env.getProperty("app.profile.theme");

        assert appName != null;
        assert myName != null;
        assert summary != null;
        assert chosenTheme != null;

        return ResponseEntity.ok(Map.of("Application Name", appName, "My Name", myName, "Summary", summary, "Chosen Theme", chosenTheme));
    }

    @GetMapping("/items")
    public ResponseEntity<Map<String, Object>> getItems() {
        String items = env.getProperty("app.profile.languages");

        assert items != null;

        return ResponseEntity.ok(Map.of("Favorite Programming Languages", items));
    }

    @GetMapping("/item/{index}/")
    public ResponseEntity<Map<String, Object>> getItem(@PathVariable String index) {
        String items = env.getProperty("app.profile.languages");

        assert items != null;
        ArrayList<String> itemsList = new ArrayList<>(List.of(items.split(",")));

        return ResponseEntity.ok(Map.of("Requested Item", itemsList.get(Integer.parseInt(index))));
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchLanguages(@RequestParam String value) {
        List<String> results = new ArrayList<>();

        String items = env.getProperty("app.profile.languages");

        assert items != null;
        ArrayList<String> itemsList = new ArrayList<>(List.of(items.split(",")));

        for (String language : itemsList) {
            if (language.toLowerCase().contains(value.toLowerCase())) {
                results.add(language);
            }
        }

        return ResponseEntity.ok(Map.of("Languages Search Result", results));
    }

    @GetMapping("/filter")
    public List<String> filter(@RequestParam(value = "category") String value) {

        String languages = env.getProperty("app.profile.languages");
        String sports = env.getProperty("app.profile.sports");

        assert sports != null;
        assert languages != null;

        if (value.equalsIgnoreCase("programming")) {
            return List.of(languages.split(","));
        }

        if (value.equalsIgnoreCase("sports")) {
            return List.of(sports.split(","));
        }

        return List.of();
    }

    @GetMapping("/add-interest")
    public ResponseEntity<Map<String, Object>> addInterest(@RequestParam String value) {
        this.interests.add(value);

        return ResponseEntity.ok(Map.of("Current Interests", this.interests));
    }


    @GetMapping("/update-interest")
    public ResponseEntity<Map<String, Object>> updateInterest(@RequestParam String value, @RequestParam String updatedValue) {
        this.interests.remove(value);
        this.interests.add(updatedValue);
        return ResponseEntity.ok(Map.of("Current Interests", this.interests));
    }

    @GetMapping("/delete-interest")
    public ResponseEntity<Map<String, Object>> deleteInterest(@RequestParam String value) {
        this.interests.remove(value);

        return ResponseEntity.ok(Map.of("Current Interests", this.interests));
    }

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> statistics() {
        String languages = env.getProperty("app.profile.languages");
        String sports = env.getProperty("app.profile.sports");

        assert languages != null;
        assert sports != null;

        List<String> languagesList = List.of(languages.split(","));
        List<String> sportsList = List.of(sports.split(","));

        return ResponseEntity.ok(Map.of(
                "Total Programming Languages", languagesList.size(),
                "Total Sports", sportsList.size(),
                "Total Current Interests", interests.size(),
                "Total Items", languagesList.size()
                        + sportsList.size()
                        + interests.size()
        ));
    }

    @GetMapping("/challenge")
    public ResponseEntity<Map<String, Object>> challenge() {
        String coding = env.getProperty("app.profile.coding-challenges");
        String fitness = env.getProperty("app.profile.fitness-challenges");

        assert coding != null;
        assert fitness != null;

        List<String> codingChallenges = List.of(coding.split(","));
        List<String> fitnessChallenges = List.of(fitness.split(","));

        int codingIndex = (int) (Math.random() * codingChallenges.size());
        int fitnessIndex = (int) (Math.random() * fitnessChallenges.size());

        return ResponseEntity.ok(Map.of(
                "Programming Challenge", codingChallenges.get(codingIndex),
                "Fitness Challenge", fitnessChallenges.get(fitnessIndex)
        ));
    }
}

