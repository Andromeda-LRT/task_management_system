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
    private static final String TASK_ADDED =
            "Task %s has been added to board with name %s";
    private static final String TASK_REMOVED =
            "Task %s has been removed from board with name %s";
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

    public void addTask(TaskImpl task){
        taskList.add(task);
        logChange(String.format(TASK_ADDED, task.getTitle(), getName()));
    }
    public void removeTask(TaskImpl task){
        taskList.remove(task);
        logChange(String.format(TASK_REMOVED, task.getTitle(), getName()));
    }
    private void logChange(String change){
        activityHistory.add(new LoggerImpl(change));
    }
    
    public String showBoardActivity(){
        StringBuilder output = new StringBuilder(toString());
        for (LoggerImpl logger : activityHistory) {
            output.append(logger.getDescription());
            output.append(System.lineSeparator());
        }
        return output.toString().trim();
    }

    @Override
    public String toString(){
        return String.format("Board: %s%n", getName());
    }
}
