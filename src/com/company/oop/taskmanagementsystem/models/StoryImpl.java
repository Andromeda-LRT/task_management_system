package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.contracts.Comment;
import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Size;
import com.company.oop.taskmanagementsystem.models.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class StoryImpl extends TaskImpl implements Story {
    public static final String STATUS_CHANGED_FROM_NOT_DONE_TO_IN_PROGRESS =
            "Story status changed from Not Done to In Progress";
    public static final String STATUS_CHANGED_FROM_IN_PROGRESS_TO_DONE =
            "Story status changed from In Progress to Done";
    public static final String STATUS_IS_ALREADY_SET_TO_DONE =
            "Cannot advance status, because Story status is already set to Done";
    public static final String STATUS_CHANGED_FROM_DONE_TO_IN_PROGRESS =
            "Story status changed from Done to In Progress";
    public static final String STATUS_CHANGED_FROM_IN_PROGRESS_TO_NOT_DONE =
            "Story status changed from In Progress to Not Done";
    public static final String STATUS_IS_ALREADY_SET_TO_NOT_DONE =
            "Cannot revert status, because Story status is already set to Not Done";
    public static final String STORY_FOR_CONSTANTS = "Story";

    private Priority priority;
    private Size size;
    public StoryImpl(int id, String title, String description, Status status, Priority priority, Size size){
        super(id, title, description, status);
        setPriority(priority);
        setSize(size);
    }
    @Override
    public Size getSize() {
        return this.size;
    }

    @Override
    public Priority getPriority(){
        return this.priority;
    }

    private void setSize(Size size){
        this.size = size;
    }

    private void setPriority(Priority priority){
        this.priority = priority;
    }

    @Override
    public List<Comment> getComments(){
        return super.getComments();
    }

    @Override
    protected void advanceStatus() {
        switch (super.getStatus()) {
            case NOT_DONE:
                super.setStatus(Status.IN_PROGRESS);
                logChange(STATUS_CHANGED_FROM_NOT_DONE_TO_IN_PROGRESS);
                break;
            case IN_PROGRESS:
                super.setStatus(Status.DONE);
                logChange(STATUS_CHANGED_FROM_IN_PROGRESS_TO_DONE);
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
                super.setStatus(Status.IN_PROGRESS);
               logChange(STATUS_CHANGED_FROM_DONE_TO_IN_PROGRESS);
                break;
            case IN_PROGRESS:
                super.setStatus(Status.NOT_DONE);
              logChange(STATUS_CHANGED_FROM_IN_PROGRESS_TO_NOT_DONE);
                break;
            case NOT_DONE:
             logChange(STATUS_IS_ALREADY_SET_TO_NOT_DONE);
                break;
        }
    }

    @Override
    public void increasePriority() {
        switch (getPriority()){
            case LOW:
                setPriority(Priority.MEDIUM);
                logChange(String.format(Constants.PRIORITY_INCREASED_FROM_LOW_TO_MEDIUM, STORY_FOR_CONSTANTS));
            case MEDIUM:
                setPriority(Priority.HIGH);
                logChange(String.format(Constants.PRIORITY_INCREASED_FROM_MEDIUM_TO_HIGH, STORY_FOR_CONSTANTS));
            case HIGH:
                logChange(String.format(Constants.PRIORITY_IS_ALREADY_SET_TO_HIGH, STORY_FOR_CONSTANTS));
        }
    }

    @Override
    public void lowerPriority() {
        switch (getPriority()){
            case HIGH:
                setPriority(Priority.MEDIUM);
                logChange(String.format(Constants.PRIORITY_LOWERED_FROM_HIGH_TO_MEDIUM, STORY_FOR_CONSTANTS));
            case MEDIUM:
                setPriority(Priority.HIGH);
                logChange(String.format(Constants.PRIORITY_LOWERED_FROM_MEDIUM_TO_LOW, STORY_FOR_CONSTANTS));
            case LOW:
                logChange(String.format(Constants.PRIORITY_IS_ALREADY_SET_TO_LOW, STORY_FOR_CONSTANTS));
        }
    }
    @Override
    public void increaseSize() {
        switch (getSize()){
            case SMALL:
                setSize(Size.MEDIUM);
                logChange(Constants.SIZE_INCREASED_FROM_SMALL_TO_MEDIUM);
            case MEDIUM:
                setSize(Size.LARGE);
                logChange(Constants.SIZE_INCREASED_FROM_MEDIUM_TO_LARGE);
            case LARGE:
                logChange(Constants.SIZE_ALREADY_SET_TO_LARGE);
        }
    }

    @Override
    public void decreaseSize() {
        switch (getSize()){
            case LARGE:
                setSize(Size.MEDIUM);
                logChange(Constants.SIZE_DECREASED_FROM_LARGE_TO_MEDIUM);
            case MEDIUM:
                setSize(Size.SMALL);
                logChange(Constants.SIZE_DECREASED_FROM_MEDIUM_TO_SMALL);
            case SMALL:
                logChange(Constants.SIZE_ALREADY_SET_TO_SMALL);
        }
    }
    @Override
    public String print() {
        return null;
    }
}
