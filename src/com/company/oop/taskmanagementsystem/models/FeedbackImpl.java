package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.contracts.Feedback;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import static java.lang.String.format;

public class FeedbackImpl extends TaskImpl implements Feedback {
    private int rating;
    private Status status;

    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description);
        setRating(rating);
    }

    @Override
    public int getRating() {
        return rating;
    }

    private void setRating(int rating) {
        ValidationHelpers.validateIntIsNotNegative(rating, Constants.NEGATIVE_RATING_ERROR);
        this.rating = rating;
    }

    @Override
    protected void setStatus() {
        this.status = Status.NEW;
    }

    @Override
    public Status getStatus() {
        return status;
    }
}
