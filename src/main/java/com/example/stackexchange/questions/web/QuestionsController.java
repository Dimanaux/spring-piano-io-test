package com.example.stackexchange.questions.web;

import com.example.stackexchange.questions.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping({"/questions", "/"})
public class QuestionsController {
    private final Questions questions;

    static {
        System.out.println("HELLO");
    }

    @Autowired
    public QuestionsController(Questions questions) {
        this.questions = questions;
    }

    @GetMapping
    public String justPage(ModelMap modelMap) {
        modelMap.put("questions", Collections.EMPTY_LIST);
        return "questions";
    }

    @GetMapping(params = {"query"})
    public String withResults(@RequestParam String query, ModelMap modelMap) {
        Questions byQuery = questions.containing(query);
        modelMap.put("questions", presentAll(byQuery));
        return "questions";
    }

    private List<Map> presentAll(Questions questions) {
        LinkedList<Map> maps = new LinkedList<>();
        questions.forEach(q -> {
            HashMap<String, Object> map = new HashMap<>();
            q.printToMap(map);
            maps.add(map);
        });
        return maps;
    }
}
