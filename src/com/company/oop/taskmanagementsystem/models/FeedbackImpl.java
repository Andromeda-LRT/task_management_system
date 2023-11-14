package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.contracts.Comment;
import com.company.oop.taskmanagementsystem.models.contracts.Feedback;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

import static java.lang.String.format;

public class FeedbackImpl extends TaskImpl implements Feedback {
    public static final String STATUS_CHANGED_FROM_NEW_TO_UNSCHEDULED =
            "Feedback status changed from New to Unscheduled";
    public static final String STATUS_CHANGED_FROM_UNSCHEDULED_TO_SCHEDULED =
            "Feedback status changed from Unscheduled to Scheduled";
    public static final String STATUS_CHANGED_FROM_SCHEDULED_TO_DONE =
            "Feedback status changed from Scheduled to Done";
    public static final String STATUS_IS_ALREADY_SET_TO_DONE =
            "Cannot advance status, because Feedback status is already set to Done";
    public static final String STATUS_CHANGED_FROM_DONE_TO_SCHEDULED =
            "Feedback status changed from Done to Scheduled";
    public static final String STATUS_CHANGED_FROM_SCHEDULED_TO_UNSCHEDULED =
            "Feedback status changed from Scheduled to Unscheduled";
    public static final String STATUS_CHANGED_FROM_UNSCHEDULED_TO_NEW =
            "Feedback status changed from Unscheduled to New";
    public static final String STATUS_IS_ALREADY_SET_TO_NEW =
            "Cannot revert status, because Feedback status is already set to New";
    private int rating;

    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description, Status.NEW);
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
    public List<Comment> getComments(){
        return super.getComments();
    }

    @Override
    protected void advanceStatus() {
        switch (super.getStatus()){
            case NEW:
                super.setStatus(Status.UNSCHEDULED);
               logChange(STATUS_CHANGED_FROM_NEW_TO_UNSCHEDULED);
                break;
            case UNSCHEDULED:
                super.setStatus(Status.SCHEDULED);
              logChange(STATUS_CHANGED_FROM_UNSCHEDULED_TO_SCHEDULED);
                break;
            case SCHEDULED:
                super.setStatus(Status.DONE);
              logChange(STATUS_CHANGED_FROM_SCHEDULED_TO_DONE);
                break;
            case DONE:
             logChange(STATUS_IS_ALREADY_SET_TO_DONE);
                break;
        }
    }

    @Override
    protected void revertStatus() {
        switch (super.getStatus()){
            case DONE:
                super.setStatus(Status.SCHEDULED);
             logChange(STATUS_CHANGED_FROM_DONE_TO_SCHEDULED);
                break;
            case SCHEDULED:
                super.setStatus(Status.UNSCHEDULED);
            logChange(STATUS_CHANGED_FROM_SCHEDULED_TO_UNSCHEDULED);
                break;
            case UNSCHEDULED:
                super.setStatus(Status.NEW);
              logChange(STATUS_CHANGED_FROM_UNSCHEDULED_TO_NEW);
                break;
            case NEW:
             logChange(STATUS_IS_ALREADY_SET_TO_NEW);
                break;
        }
    }

}
