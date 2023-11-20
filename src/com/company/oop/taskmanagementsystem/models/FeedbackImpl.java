package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.contracts.Comment;
import com.company.oop.taskmanagementsystem.models.contracts.Feedback;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;


public class FeedbackImpl extends TaskImpl implements Feedback {

    private int rating;

    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description, Status.NEW);
        setRating(rating);

        logChange(String.format(Constants.TASK_CREATED, Constants.FEEDBACK, getTitle()));
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public void changeRating(int newRating) {
        logChange(String.format(Constants.CHANGED_RATING, getRating(), newRating));
        setRating(newRating);
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
    public void advanceStatus() {
        switch (super.getStatus()){
            case NEW:
                super.setStatus(Status.UNSCHEDULED);
               logChange(String.format(Constants.STATUS_CHANGED, Constants.FEEDBACK, Status.NEW, Status.UNSCHEDULED));
                break;
            case UNSCHEDULED:
                super.setStatus(Status.SCHEDULED);
              logChange(String.format(Constants.STATUS_CHANGED, Constants.FEEDBACK, Status.UNSCHEDULED, Status.SCHEDULED));
                break;
            case SCHEDULED:
                super.setStatus(Status.DONE);
              logChange(String.format(Constants.STATUS_CHANGED, Constants.FEEDBACK, Status.SCHEDULED, Status.DONE));
                break;
            case DONE:
             logChange(String.format(Constants.STATUS_IS_ALREADY_SET_TO_DONE, Constants.FEEDBACK));
                break;
        }
    }

    @Override
    public void revertStatus() {
        switch (super.getStatus()){
            case DONE:
                super.setStatus(Status.SCHEDULED);
             logChange(String.format(Constants.STATUS_CHANGED, Constants.FEEDBACK, Status.DONE, Status.SCHEDULED));
                break;
            case SCHEDULED:
                super.setStatus(Status.UNSCHEDULED);
            logChange(String.format(Constants.STATUS_CHANGED, Constants.FEEDBACK, Status.SCHEDULED, Status.UNSCHEDULED));
                break;
            case UNSCHEDULED:
                super.setStatus(Status.NEW);
              logChange(String.format(Constants.STATUS_CHANGED, Constants.FEEDBACK, Status.UNSCHEDULED, Status.NEW));
                break;
            case NEW:
             logChange(String.format(Constants.STATUS_IS_ALREADY_SET_TO_NEW, Constants.FEEDBACK));
                break;
        }
    }

    @Override
    public String printMainInformation() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(super.printMainInformation());
        stringBuilder.append("[" + getRating() + " RATING]");

        return stringBuilder.toString();
    }
}
