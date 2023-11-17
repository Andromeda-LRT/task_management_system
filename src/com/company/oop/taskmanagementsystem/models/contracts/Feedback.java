package com.company.oop.taskmanagementsystem.models.contracts;

public interface Feedback extends Task {
    int getRating();

    void changeRating(int newRating);
}
