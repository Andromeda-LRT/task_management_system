package com.company.oop.taskmanagementsystem.models.contracts;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.LoggerImpl;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public interface Member {
    String getName();

    List<Task> getListOfTasks();

    List<LoggerImpl> getActivityHistory();

    void assignTask(Task task);

    void unAssignTask(Task task);

    String printActivity();
}
