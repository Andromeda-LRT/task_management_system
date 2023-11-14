package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import com.company.oop.taskmanagementsystem.models.enums.Status;


import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {

    private static final MemberImpl NOBODY = new MemberImpl("NO ASSIGNEE");


    private List<String> stepsToReproduce;

    private Priority priority;

    private Severity severity;

    private MemberImpl assignee;

    public BugImpl(int id, String title, String description,
                   List<String> stepsToReproduce, Priority priority, Severity severity) {
        super(id, title, description, Status.ACTIVE);
        setStepsToReproduce(stepsToReproduce);
        this.priority = priority;
        this.severity = severity;
        this.assignee = NOBODY;

        logChange(String.format(Constants.TASK_CREATED, Constants.BUG, getTitle()));
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    private void setStepsToReproduce(List<String> stepsToReproduce) {
        if(stepsToReproduce==null || stepsToReproduce.isEmpty()){
            throw  new IllegalArgumentException(Constants.EMPTY_STEPS_TO_REPRODUCE);
        }
        this.stepsToReproduce = stepsToReproduce;
    }

    public void setAssignee(MemberImpl assignee) {
        if (assignee.getName().equalsIgnoreCase(NOBODY.getName())) {
            logChange(String.format(Constants.NEW_ASSIGNEE,Constants.BUG, assignee.getName()));
        } else {
            logChange(String.format(Constants.CHANGED_ASSIGNEE, Constants.BUG, assignee.getName()));
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
                logChange(String.format(Constants.PRIORITY_INCREASED, Constants.BUG, Priority.LOW, Priority.MEDIUM));
                break;
            case MEDIUM:
                setPriority(Priority.HIGH);
                logChange(String.format(Constants.PRIORITY_INCREASED, Constants.BUG, Priority.MEDIUM, Priority.HIGH));
                break;
            case HIGH:
                logChange(String.format(Constants.PRIORITY_IS_ALREADY_SET_TO_HIGH, Constants.BUG));
                break;
        }
    }

    @Override
    public void lowerPriority() {
        switch (getPriority()){
            case HIGH:
                setPriority(Priority.MEDIUM);
                logChange(String.format(Constants.PRIORITY_LOWERED, Constants.BUG, Priority.HIGH, Priority.MEDIUM));
                break;
            case MEDIUM:
                setPriority(Priority.LOW);
                logChange(String.format(Constants.PRIORITY_LOWERED, Constants.BUG, Priority.MEDIUM, Priority.LOW));
                break;
            case LOW:
                logChange(String.format(Constants.PRIORITY_IS_ALREADY_SET_TO_LOW, Constants.BUG));
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
                logChange(String.format(Constants.SEVERITY_INCREASED, Severity.MINOR, Severity.MAJOR));
                break;
            case MAJOR:
                setSeverity(Severity.CRITICAL);
                logChange(String.format(Constants.SEVERITY_INCREASED, Severity.MAJOR, Severity.CRITICAL));
                break;
            case CRITICAL:
                logChange(String.format(Constants.SEVERITY_ALREADY_AT_CRITICAL));
                break;
        }
    }

    @Override
    public void lowerSeverity() {
        switch (getSeverity()){
            case CRITICAL:
                setSeverity(Severity.MAJOR);
                logChange(String.format(Constants.SEVERITY_DECREASED, Severity.CRITICAL, Severity.MAJOR));
                break;
            case MAJOR:
                setSeverity(Severity.MINOR);
                logChange(String.format(Constants.SEVERITY_DECREASED, Severity.MAJOR, Severity.MINOR));
                break;
            case MINOR:
                logChange(String.format(Constants.SEVERITY_ALREADY_AT_MINOR));
                break;
        }
    }

    @Override
    public MemberImpl getAssignee() {
        return assignee;
    }

        @Override
    protected void advanceStatus(){
        switch (super.getStatus()){
            case ACTIVE:
                super.setStatus(Status.DONE);
              logChange(String.format(Constants.STATUS_CHANGED,Constants.BUG, Status.ACTIVE, Status.DONE));
                break;
            case DONE:
             logChange(String.format(Constants.CANNOT_ADVANCE_STATUS, Constants.BUG, Status.DONE));
                break;
        }
    }
    @Override
    protected void revertStatus(){
        switch (super.getStatus()){
            case DONE:
                super.setStatus(Status.ACTIVE);
              logChange(String.format(Constants.STATUS_CHANGED,Constants.BUG, Status.DONE, Status.ACTIVE));
                break;
            case ACTIVE:
             logChange(String.format(Constants.CANNOT_REVERT_STATUS, Constants.BUG, Status.ACTIVE));
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
