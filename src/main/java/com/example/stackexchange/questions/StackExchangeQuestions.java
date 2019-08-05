package com.example.stackexchange.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class StackExchangeQuestions implements Questions {
    private static final String baseUrl = "http://api.stackexchange.com/2.2/search?order=desc&site=stackoverflow&intitle={intitle}";
    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    private List<Question> questions;

    public StackExchangeQuestions() {
        questions = new LinkedList<>();
    }

    private StackExchangeQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public StackExchangeQuestions containing(String string) {
        LinkedHashMap items = restTemplate.getForObject(baseUrl, LinkedHashMap.class, string);
        if (items == null) {
            return new StackExchangeQuestions();
        } else {
            this.questions = questions(items);
            return this;
        }
    }

    private List<Question> questions(LinkedHashMap<String, ?> map) {
        LinkedList<Question> questions = new LinkedList<>();
        ArrayList<LinkedHashMap> items = (ArrayList<LinkedHashMap>) map.get("items");
        for (LinkedHashMap item : items) {
            questions.add(toQuestion(item));
        }
        return questions;
    }

    private Question toQuestion(LinkedHashMap map) {
        return new Question(
                (String) map.get("title"),
                (String) map.get("link"),
                new User(
                        ((String) ((LinkedHashMap) map.get("owner")).get("display_name"))
                ),
                (Boolean) map.get("is_answered"),
                LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(1000L * (Integer) map.get("creation_date")),
                        ZoneId.systemDefault()
                )
        );
    }

    @Override
    public Iterator<Question> iterator() {
        return questions.iterator();
    }
}
