package com.company.oop.taskmanagementsystem.models;

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
    private static final String NEW_COMMENT = "Invalid status of bug! Status should be Active or Done!";


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
        if(assignee.getName().equalsIgnoreCase(NOBODY.getName())) {
            logChange(String.format(NEW_ASSIGNEE, assignee.getName()));
        }else{
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
    public Severity getSeverity() {
        return severity;
    }

    @Override
    public Member getAssignee() {
        return assignee;
    }

    @Override
    public String print() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(super.print());
        stringBuilder.append("Assigned to: ");
        stringBuilder.append(getAssignee().getName());
        stringBuilder.append(System.lineSeparator());

        return stringBuilder.toString();
    }
}
