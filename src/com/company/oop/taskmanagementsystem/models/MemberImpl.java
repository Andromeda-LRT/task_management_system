package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class MemberImpl implements com.company.oop.taskmanagementsystem.models.contracts.Member {
    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 15;
    private static final String NAME_LENGTH_ERROR = format(
            "The name must be between %s and %s characters long!",
            NAME_MIN_LENGTH,
            NAME_MAX_LENGTH);
    private static final String TASK_ASSIGNED = "Task %s was assigned to %s";
    private static final String TASK_UNASSIGNED = "Task %s was unassigned from %s";
    private String name;
    private List<TaskImpl> taskList;
    private List<LoggerImpl> activityHistory;

    public MemberImpl(String name) {
        setName(name);
        this.taskList = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
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
    private void setName(String name){
        ValidationHelpers.validateIntRange(name.length(),
                NAME_MIN_LENGTH,
                NAME_MAX_LENGTH,
                NAME_LENGTH_ERROR);
        this.name = name;
    }

    public void addTask(TaskImpl task){
        taskList.add(task);
        logChange(String.format(TASK_ASSIGNED, task.getTitle(), getName()));
    }

    public void removeTask(TaskImpl task){
        taskList.remove(task);
        logChange(String.format(TASK_UNASSIGNED, task.getTitle(), getName()));

    }

    private void logChange(String change) {
        activityHistory.add(new LoggerImpl(change));
    }
}
