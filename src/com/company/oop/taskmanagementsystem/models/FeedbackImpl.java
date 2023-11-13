package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.models.contracts.Feedback;
import com.company.oop.taskmanagementsystem.models.enums.Status;

public class FeedbackImpl extends TaskImpl implements Feedback {
    private int rating;
    private Status status;

    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description);
        setRating(rating);
        setStatus();
    }

    @Override
    public int getRating() {
        return rating;
    }

    private void setRating(int rating) {
        this.rating = rating;
    }
    private void setStatus(){
        this.status = Status.NEW;
    }

    @Override
    public Status getStatus() {
        return status;
    }
}
