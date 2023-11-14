package com.company.oop.taskmanagementsystem.models.contracts;

import com.company.oop.taskmanagementsystem.models.LoggerImpl;

import java.util.List;

public interface Member {
    String getName();

    List<Task> getListOfTasks();

    List<LoggerImpl> getActivityHistory();
}
