package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import com.company.oop.taskmanagementsystem.models.enums.Status;


import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {

    private static final Member NOBODY = new Member("NO ASSIGNEE");
    private static final String NEW_ASSIGNEE = "Bug was assigned to: %s";
    private static final String CHANGED_ASSIGNEE = "Bug assignee was changed to: %s";
    private static final String NEW_COMMENT =
            "Invalid status of bug! Status should be Active or Done!";
    public static final String STATUS_CHANGED_FROM_ACTIVE_TO_DONE =
            "Bug Status changed from Active to Done";
    public static final String STATUS_IS_ALREADY_SET_TO_DONE =
            "Cannot advance status, because Bug status is already set to Done";
    public static final String STATUS_CHANGED_FROM_DONE_TO_ACTIVE =
            "Bug status changed from Done to Active";
    public static final String STATUS_IS_ALREADY_SET_TO_ACTIVE =
            "Cannot revert status, because Bug status is already set to Active";
    public static final String BUG_FOR_CONSTANTS = "Bug";


    private final List<String> stepsToReproduce;

    private Priority priority;

    private Severity severity;

    private Member assignee;

    public BugImpl(int id, String title, String description,
                   List<String> stepsToReproduce, Priority priority, Severity severity) {
        super(id, title, description, Status.ACTIVE);
        this.stepsToReproduce = stepsToReproduce;
        this.priority = priority;
        this.severity = severity;
        this.assignee = NOBODY;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public void setAssignee(Member assignee) {
        if (assignee.getName().equalsIgnoreCase(NOBODY.getName())) {
            logChange(String.format(NEW_ASSIGNEE, assignee.getName()));
        } else {
            logChange(String.format(CHANGED_ASSIGNEE, assignee.getName()));
        }
        this.assignee = assignee;
    }

    @Override
    public List<String> getStepsToReproduce() {
        return new ArrayList<>(stepsToReproduce);
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public void increasePriority() {
        switch (getPriority()){
            case LOW:
                setPriority(Priority.MEDIUM);
                logChange(String.format(Constants.PRIORITY_INCREASED_FROM_LOW_TO_MEDIUM, BUG_FOR_CONSTANTS));
                break;
            case MEDIUM:
                setPriority(Priority.HIGH);
                logChange(String.format(Constants.PRIORITY_INCREASED_FROM_MEDIUM_TO_HIGH, BUG_FOR_CONSTANTS));
                break;
            case HIGH:
                logChange(String.format(Constants.PRIORITY_IS_ALREADY_SET_TO_HIGH, BUG_FOR_CONSTANTS));
                break;
        }
    }

    @Override
    public void lowerPriority() {
        switch (getPriority()){
            case HIGH:
                setPriority(Priority.MEDIUM);
                logChange(String.format(Constants.PRIORITY_LOWERED_FROM_HIGH_TO_MEDIUM, BUG_FOR_CONSTANTS));
                break;
            case MEDIUM:
                setPriority(Priority.HIGH);
                logChange(String.format(Constants.PRIORITY_LOWERED_FROM_MEDIUM_TO_LOW, BUG_FOR_CONSTANTS));
                break;
            case LOW:
                logChange(String.format(Constants.PRIORITY_IS_ALREADY_SET_TO_LOW, BUG_FOR_CONSTANTS));
                break;
        }
    }

    @Override
    public Severity getSeverity() {
        return severity;
    }

    @Override
    public void increaseSeverity() {
        switch (getSeverity()){
            case MINOR:
                setSeverity(Severity.MAJOR);
                logChange(String.format(Constants.SEVERITY_INCREASED_FROM_MINOR_TO_MAJOR, BUG_FOR_CONSTANTS));
                break;
            case MAJOR:
                setSeverity(Severity.CRITICAL);
                logChange(String.format(Constants.SEVERITY_INCREASED_FROM_MAJOR_TO_CRITICAL, BUG_FOR_CONSTANTS));
                break;
            case CRITICAL:
                logChange(String.format(Constants.SEVERITY_ALREADY_AT_CRITICAL, BUG_FOR_CONSTANTS));
                break;
        }
    }

    @Override
    public void lowerSeverity() {
        switch (getSeverity()){
            case CRITICAL:
                setSeverity(Severity.MAJOR);
                logChange(String.format(Constants.SEVERITY_DECREASED_FROM_CRITICAL_TO_MAJOR, BUG_FOR_CONSTANTS));
                break;
            case MAJOR:
                setSeverity(Severity.MINOR);
                logChange(String.format(Constants.SEVERITY_DECREASED_FROM_MAJOR_TO_MINOR, BUG_FOR_CONSTANTS));
                break;
            case MINOR:
                logChange(String.format(Constants.SEVERITY_ALREADY_AT_MINOR, BUG_FOR_CONSTANTS));
                break;
        }
    }

    @Override
    public Member getAssignee() {
        return assignee;
    }

        @Override
    protected void advanceStatus(){
        switch (super.getStatus()){
            case ACTIVE:
                super.setStatus(Status.DONE);
              logChange(STATUS_CHANGED_FROM_ACTIVE_TO_DONE);
                break;
            case DONE:
             logChange(STATUS_IS_ALREADY_SET_TO_DONE);
                break;
        }
    }
    @Override
    protected void revertStatus(){
        switch (super.getStatus()){
            case DONE:
                super.setStatus(Status.ACTIVE);
              logChange(STATUS_CHANGED_FROM_DONE_TO_ACTIVE);
                break;
            case ACTIVE:
             logChange(STATUS_IS_ALREADY_SET_TO_ACTIVE);
                break;
        }
    }

    @Override
    public String print() {
        StringBuilder stringBuilder = new StringBuilder();

       // stringBuilder.append(super.print());
        stringBuilder.append("Assigned to: ");
        stringBuilder.append(getAssignee().getName());
        stringBuilder.append(System.lineSeparator());

        return stringBuilder.toString();
    }



}
