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
    }

    public void removeTask(TaskImpl task){
        taskList.remove(task);
    }
}
