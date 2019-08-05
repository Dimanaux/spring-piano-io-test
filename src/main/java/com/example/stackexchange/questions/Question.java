package com.example.stackexchange.questions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Question {
    private String title;
    private String link;
    private User owner;
    private boolean isAnswered;
    private LocalDateTime creationDate;

    public Question(String title, String link, User owner, boolean isAnswered, LocalDateTime creationDate) {
        this.title = title;
        this.link = link;
        this.owner = owner;
        this.isAnswered = isAnswered;
        this.creationDate = creationDate;
    }

    public void printToMap(Map<String, Object> map) {
        map.put("title", title);
        map.put("link", link);
        map.put("author", owner.name());
        map.put("isAnswered", isAnswered);
        map.put("date", creationDate.format(DateTimeFormatter.ISO_DATE));
    }
}
