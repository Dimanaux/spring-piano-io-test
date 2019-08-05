package com.example.stackexchange.questions;

public interface Questions extends Iterable<Question> {
    Questions containing(String string);
}
