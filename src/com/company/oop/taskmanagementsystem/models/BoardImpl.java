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
    private static final String TASK_ASSIGNED =
            "Task %s with description:%n has been assigned to %s";
    private static final String TASK_UNASSIGNED =
            "Task %s with description:%n has been unassigned from %s";
    private String name;
    private List<LoggerImpl> activityHistory;
    private List<TaskImpl> taskList;
    // teamsImpl teamsList List<TeamsImpl> teamsList;

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
    @Override
    public void addTask(TaskImpl task){
        taskList.add(task);
        logChange(String.format(TASK_ADDED, task.getTitle(), getName()));
    }
    @Override
    public void removeTask(TaskImpl task){
        for (TaskImpl taskLocal : taskList) {
            if (taskLocal == task){
                taskList.remove(task);
            }
        }
        //taskList.remove(task);
        logChange(String.format(TASK_REMOVED, task.getTitle(), getName()));
    }
    private void logChange(String change){
        activityHistory.add(new LoggerImpl(change));
    }
    @Override
    public String showBoardActivity(){
        StringBuilder output = new StringBuilder(toString());
        for (LoggerImpl logger : activityHistory) {
            output.append(logger.getDescription());
            output.append(System.lineSeparator());
        }
        return output.toString().trim();
    }

    @Override
    public void assignTask(TaskImpl task, MemberImpl memberToAssignTaskTo) {
        /*
        if (teamsList.isEmpty){
        throw new IllegalArgumentException("Please create a team prior to assigning a task
        to a member");
        } else {
        for (TeamsImpl teamsLocal : teamsList){
        if( teamsLocal.getMembers.contains(memberToAssignTaskTo){
            memberToAssignTaskTo.addTask(task);
            logChange(String.format(TASK_ASSIGNED, task.getTitle(), task.getDescription,
             memberToAssignTaskTo.getName()))
            break;
        } else {
        throw new IllegalArgumentException("The member you want to assign a task to
        does not exist/does not belong to a team");
        }
       }
      }
         */
        // to have a for each loop for teamImpl list and to find the concrete member
        // then to have it assigned
        // add memberimpl as paramter to use teams list to look for the member
        // in question.
        // could throw an exception if teams list is empty
        // throw exception if member does not exist in teams's list
    }

    @Override
    public void unassignTask(TaskImpl task, MemberImpl memberToUnassignTask) {
         /*
        if (teamsList.isEmpty){
        throw new IllegalArgumentException("Please create a team prior to assigning a task
        to a member");
        } else {
        for (TeamsImpl teamsLocal : teamsList){
        if( teamsLocal.getMembers.contains(memberToAssignTaskTo){
            memberToAssignTaskTo.removeTask(task);
            logChange(String.format(TASK_UNASSIGNED, task.getTitle(), task.getDescription,
             memberToAssignTaskTo.getName()))
            break;
        } else {
        throw new IllegalArgumentException("The member you want to assign a task to
        does not exist/does not belong to a team");
        }
       }
      }
         */
    }

    @Override
    public String toString(){
        return String.format("Board: %s%n", getName());
    }
}
