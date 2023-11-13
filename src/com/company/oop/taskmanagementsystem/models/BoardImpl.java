package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.models.contracts.Board;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    private static final int BOARD_NAME_MIN_LENGTH = 5;
    private static final int BOARD_NAME_MAX_LENGTH = 10;
    private static final String INVALID_BOARD_NAME_MESSAGE =
            "The Board's name must be between 5 and 10 symbols";
    private String name;
    private List<LoggerImpl> activityHistory;
    private List<TaskImpl> taskList;

    public BoardImpl(String name){
        setName(name);
        this.activityHistory = new ArrayList<>();
        this.taskList = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<TaskImpl> getTaskList() {
        return new ArrayList<>(taskList);
    }

    @Override
    public List<LoggerImpl> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    private void setName(String name){
        ValidationHelpers.validateIntRange(name.length(), BOARD_NAME_MIN_LENGTH,
                BOARD_NAME_MAX_LENGTH, INVALID_BOARD_NAME_MESSAGE);
        this.name = name;
    }

}
