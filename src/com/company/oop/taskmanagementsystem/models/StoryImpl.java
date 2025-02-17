package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.contracts.Comment;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Size;
import com.company.oop.taskmanagementsystem.models.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class StoryImpl extends TaskImpl implements Story {

    private Priority priority;
    private Size size;
    private Member assignee;
    private static final Member NOBODY = new MemberImpl("NO ASSIGNEE");

    public StoryImpl(int id, String title, String description, Priority priority, Size size) {
        super(id, title, description, Status.NOT_DONE);
        setPriority(priority);
        setSize(size);
        this.assignee = NOBODY;
        logChange(String.format(Constants.TASK_CREATED, Constants.STORY, getTitle()));
    }

    @Override
    public Size getSize() {
        return this.size;
    }

    @Override
    public Member getAssignee() {
        return this.assignee;
    }

    @Override
    public Priority getPriority() {
        return this.priority;
    }

    private void setSize(Size size) {
        this.size = size;
    }

    private void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void changeAssignee(Member member) {
        setAssignee(member);
    }


    public void setAssignee(Member assignee) {
        if (assignee.getName().equalsIgnoreCase(NOBODY.getName())) {
            logChange(String.format(Constants.NEW_ASSIGNEE, Constants.STORY, assignee.getName()));
        } else {
            logChange(String.format(Constants.CHANGED_ASSIGNEE, Constants.STORY, assignee.getName()));
        }
        this.assignee = assignee;
    }

    @Override
    public List<Comment> getComments() {
        return super.getComments();
    }

    @Override
    public void changeStatus(Status statusToChangeTo) {
        if (statusToChangeTo != Status.NOT_DONE && statusToChangeTo != Status.IN_PROGRESS
        && statusToChangeTo != Status.DONE){
            throw new IllegalArgumentException(Constants.INVALID_STORY_STATUS_ERROR_MSG);
        }
        if (super.getStatus() == statusToChangeTo){
            throw new IllegalArgumentException(String.format(Constants.CANNOT_CHANGE_STATUS_ERR_MSG,
                    Constants.STORY, super.getStatus()));
        }
        logChange(String.format(Constants.STATUS_CHANGED, Constants.STORY, super.getStatus(), statusToChangeTo));
        setStatus(statusToChangeTo);
    }

    @Override
    public void advanceStatus() {
        switch (super.getStatus()) {
            case NOT_DONE:
                super.setStatus(Status.IN_PROGRESS);
                logChange(String.format(Constants.STATUS_CHANGED, Constants.STORY, Status.NOT_DONE, Status.IN_PROGRESS));
                break;
            case IN_PROGRESS:
                super.setStatus(Status.DONE);
                logChange(String.format(Constants.STATUS_CHANGED, Constants.STORY, Status.IN_PROGRESS, Status.DONE));
                break;
            case DONE:
                logChange(String.format(Constants.STATUS_IS_ALREADY_SET_TO_DONE, Constants.STORY));
                break;
        }
    }

    @Override
    public void revertStatus() {
        switch (super.getStatus()) {
            case DONE:
                super.setStatus(Status.IN_PROGRESS);
                logChange(String.format(Constants.STATUS_CHANGED, Constants.STORY, Status.DONE, Status.IN_PROGRESS));
                break;
            case IN_PROGRESS:
                super.setStatus(Status.NOT_DONE);
                logChange(String.format(Constants.STATUS_CHANGED, Constants.STORY, Status.IN_PROGRESS, Status.NOT_DONE));
                break;
            case NOT_DONE:
                logChange(String.format(Constants.STATUS_IS_ALREADY_SET_TO_NOT_DONE, Constants.STORY));
                break;
        }
    }

    @Override
    public void increasePriority() {
        switch (getPriority()) {
            case LOW:
                setPriority(Priority.MEDIUM);
                logChange(String.format(Constants.PRIORITY_INCREASED, Constants.STORY, Priority.LOW, Priority.MEDIUM));
                break;
            case MEDIUM:
                setPriority(Priority.HIGH);
                logChange(String.format(Constants.PRIORITY_INCREASED, Constants.STORY, Priority.MEDIUM, Priority.HIGH));
                break;
            case HIGH:
                logChange(String.format(Constants.PRIORITY_IS_ALREADY_SET_TO_HIGH, Constants.STORY));
                break;
        }
    }

    @Override
    public void lowerPriority() {
        switch (getPriority()) {
            case HIGH:
                setPriority(Priority.MEDIUM);
                logChange(String.format(Constants.PRIORITY_LOWERED, Constants.STORY, Priority.HIGH, Priority.MEDIUM));
                break;
            case MEDIUM:
                setPriority(Priority.LOW);
                logChange(String.format(Constants.PRIORITY_LOWERED, Constants.STORY, Priority.MEDIUM, Priority.LOW));
                break;
            case LOW:
                logChange(String.format(Constants.PRIORITY_IS_ALREADY_SET_TO_LOW, Constants.STORY));
                break;
        }
    }

    @Override
    public void changePriority(Priority priorityToChangeTo) {
        if (getPriority() == priorityToChangeTo){
            throw new IllegalArgumentException(String.format(Constants.CANNOT_CHANGE_PRIORITY_ERR_MSG,
                    Constants.STORY, getPriority()));
        }
        logChange(String.format(Constants.PRIORITY_CHANGED, Constants.STORY, getId(), getPriority(), priorityToChangeTo));
        setPriority(priorityToChangeTo);
    }

    @Override
    public void increaseSize() {
        switch (getSize()) {
            case SMALL:
                setSize(Size.MEDIUM);
                logChange(String.format(Constants.SIZE_INCREASED, Size.SMALL, Size.MEDIUM));
                break;
            case MEDIUM:
                setSize(Size.LARGE);
                logChange(String.format(Constants.SIZE_INCREASED, Size.MEDIUM, Size.LARGE));
                break;
            case LARGE:
                logChange(Constants.SIZE_ALREADY_SET_TO_LARGE);
                break;
        }
    }

    @Override
    public void decreaseSize() {
        switch (getSize()) {
            case LARGE:
                setSize(Size.MEDIUM);
                logChange(String.format(Constants.SIZE_DECREASED, Size.LARGE, Size.MEDIUM));
                break;
            case MEDIUM:
                setSize(Size.SMALL);
                logChange(String.format(Constants.SIZE_DECREASED, Size.MEDIUM, Size.SMALL));
                break;
            case SMALL:
                logChange(Constants.SIZE_ALREADY_SET_TO_SMALL);
                break;
        }
    }
    @Override
    public void changeSize(Size sizeToChangeTo) {
        if (getSize() == sizeToChangeTo){
            throw new IllegalArgumentException(String.format(Constants.CANNOT_CHANGE_SIZE_ERR_MSG,
                    Constants.STORY, getSize()));
        }
        logChange(String.format(Constants.SIZE_CHANGED, Constants.STORY, getId(), getSize(), sizeToChangeTo));
        setSize(sizeToChangeTo);
    }

    @Override
    public String print() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("Story with %s", super.print()));
        output.append(System.lineSeparator());
        output.append(String.format("Priority: %s", getPriority()));
        output.append(System.lineSeparator());
        output.append(String.format("Size: %s", getSize()));
        // the new line could be removed later on
        output.append(System.lineSeparator());
        return output.toString();
    }

    @Override
    public String printMainInformation() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(super.printMainInformation());
        stringBuilder.append("[" + getPriority() + " PRIORITY]");
        stringBuilder.append("[" + getSize() + " SIZE]");
        stringBuilder.append(" assigned to " + getAssignee().getName());

        return stringBuilder.toString();
    }
}
