package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class MemberImpl implements Member {
    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 15;
    private static final String NAME_LENGTH_ERROR = format(
            "The name must be between %s and %s characters long!",
            NAME_MIN_LENGTH,
            NAME_MAX_LENGTH);
    private static final String TASK_ASSIGNED = "Task %s was assigned to %s";
    private static final String TASK_UNASSIGNED = "Task %s was unassigned from %s";
    private String name;
    private List<Task> taskList;
    private List<LoggerImpl> activityHistory;

    public MemberImpl(String name) {
        setName(name);
        this.taskList = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
        logChange(String.format(Constants.MEMBER_WAS_CREATED, this.name));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Task> getListOfTasks() {
        return new ArrayList<>(taskList);
    }

    @Override
    public List<LoggerImpl> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    private void setName(String name) {
        ValidationHelpers.validateIntRange(name.length(),
                NAME_MIN_LENGTH,
                NAME_MAX_LENGTH,
                NAME_LENGTH_ERROR);
        this.name = name;
    }

    public void assignTask(Task task) {
        if(!taskList.contains(task)) {
            taskList.add(task);
            if (task instanceof Bug) {
                ((Bug) task).setAssignee(this);
            } else {
                ((Story) task).setAssignee(this);
            }
            logChange(String.format(TASK_ASSIGNED, task.getTitle(), getName()));
        }else {
            throw new IllegalArgumentException("Task is already assigned to " + getName());
        }
    }

    public void unAssignTask(Task task) {
        if(taskList.contains(task)) {
            taskList.remove(task);
            logChange(String.format(TASK_UNASSIGNED, task.getTitle(), getName()));
        }else {
            throw new IllegalArgumentException("There is no such task for " + getName());
        }

    }

    private void logChange(String change) {
        activityHistory.add(new LoggerImpl(change));
    }

    public String printActivity() {
        StringBuilder output = new StringBuilder();
        output.append(String.format(Constants.ACTIVITY, getName())).append(System.lineSeparator());
        output.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        for (LoggerImpl log : activityHistory) {
            output.append(log.print()).append(System.lineSeparator());
        }

        output.append(Constants.LINE_DIVISOR);
        return output.toString();
    }
}
