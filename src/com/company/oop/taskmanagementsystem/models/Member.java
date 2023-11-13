package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.models.contracts.Person;
import com.company.oop.taskmanagementsystem.models.contracts.Printable;
import com.company.oop.taskmanagementsystem.models.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public class Member implements Person {

    private String name;
    private List<TaskImpl> taskList;
    private List<LoggerImpl> activityHistory;

    //TODO validate fields
    //TODO implement the rest methods

    public Member(String name) {
        this.name = name;
        this.taskList = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Task> getListOfTasks() {
        return null;
    }

    @Override
    public List<LoggerImpl> getActivityHistory() {
        return null;
    }
}
