package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.contracts.Board;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.models.contracts.Team;
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
            "Task %s with description:%n%s has been assigned to %s";
    private static final String TASK_UNASSIGNED =
            "Task %s with description:%n%s has been unassigned from %s";
    private static final String NO_TEAMS_IN_BOARD_ERR_MSG =
            "There are no teams added to board";
    private static final String MEMBER_DOES_NOT_EXIST_ERR_MSG =
            "The member you want to assign a task to does not exist or does not belong to a team";
    private static final String BOARD_CREATED = "Board %s has been created";
    private static final String TEAM_NOT_FOUND_ERR_MSG = "Team %s does not exist in this board.";
    private static final String TEAM_ADDED_TO_BOARD = "Team has been added to board %s";
    private static final String TEAM_ALREADY_EXIST_IN_BOARD = "This team already exist in board %s";
    private static final String TEAM_REMOVED_MSG = "Team %s has been removed removed.";

    private String name;
    private List<LoggerImpl> activityHistory;
    private List<Task> taskList;
    private List<Team> teamsList;

    public BoardImpl(String name){
        setName(name);
        this.activityHistory = new ArrayList<>();
        this.taskList = new ArrayList<>();
        this.teamsList = new ArrayList<>();
        logChange(String.format(BOARD_CREATED, getName()));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Task> getTaskList() {
        return new ArrayList<>(taskList);
    }
    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teamsList);
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
    public void addTask(Task task){
        taskList.add(task);
        logChange(String.format(TASK_ADDED, task.getTitle(), getName()));
    }
    @Override

    public void removeTask(Task task){
        for (Task taskLocal : taskList) {
            if (taskLocal == task) {
                taskList.remove(task);
                break;
            }
        }
        if (!teamsList.isEmpty()) {
            for (Team teamLocal : teamsList) {
                for (Member memberLocal : teamLocal.getMembers()) {
                    if (memberLocal.getListOfTasks().contains(task)) {
                        memberLocal.unAssignTask(task);
                        break;
                    }
                }
            }
        }
        logChange(String.format(TASK_REMOVED, task.getTitle(), getName()));
    }
    private void logChange(String change){
        activityHistory.add(new LoggerImpl(change));
    }
    @Override
    public String showBoardActivity(){
        StringBuilder output = new StringBuilder();
        if (activityHistory.isEmpty()){
            output.append(toString());
            output.append(Constants.NO_BOARD_ACTIVITY);
            output.append(System.lineSeparator());
        } else {
            output.append(String.format(Constants.ACTIVITY, getName())).append(System.lineSeparator());
            output.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

            for (LoggerImpl logger : activityHistory) {
                output.append(logger.getDescription());
                output.append(System.lineSeparator());
            }
        }
        output.append(Constants.LINE_DIVISOR);
        return output.toString();
    }

    @Override
    public void assignTask(Task task, Member memberToAssignTaskTo) {
        if (teamsList.isEmpty()){
        throw new IllegalArgumentException(NO_TEAMS_IN_BOARD_ERR_MSG);
        } else {
        for (Team teamsLocal : teamsList){
        if ( teamsLocal.getMembers().contains(memberToAssignTaskTo)){
            memberToAssignTaskTo.assignTask(task);
            logChange(String.format(TASK_ASSIGNED, task.getTitle(), task.getDescription(),
             memberToAssignTaskTo.getName()));
            break;
        } else {
        throw new IllegalArgumentException(MEMBER_DOES_NOT_EXIST_ERR_MSG);
        }
       }
      }
    }

    @Override
    public void unassignTask(Task task, Member memberToUnassignTask) {

        if (teamsList.isEmpty()){
        throw new IllegalArgumentException(NO_TEAMS_IN_BOARD_ERR_MSG);
        } else {
        for (Team teamsLocal : teamsList){
        if( teamsLocal.getMembers().contains(memberToUnassignTask)){
            memberToUnassignTask.unAssignTask(task);
            logChange(String.format(TASK_UNASSIGNED, task.getTitle(), task.getDescription(),
                    memberToUnassignTask.getName()));
            break;
        } else {
        throw new IllegalArgumentException(MEMBER_DOES_NOT_EXIST_ERR_MSG);
        }
       }
      }
    }
    @Override
    public void addTeam(Team teamToAdd){
        if(!teamsList.contains(teamToAdd)){
            teamsList.add(teamToAdd);
            logChange(String.format(TEAM_ADDED_TO_BOARD, getName()));
        }else {
            throw new IllegalArgumentException(String.format(TEAM_ALREADY_EXIST_IN_BOARD, getName()));
        }
    }

    @Override
    public void removeTeam(Team team) {
        if (teamsList.isEmpty()){
            throw new IllegalArgumentException(NO_TEAMS_IN_BOARD_ERR_MSG);
        } else {
            for (Team teamLocal : teamsList) {
                if (teamLocal.getName().equals(team.getName())) {
                    teamsList.remove(team);
                    logChange(String.format(TEAM_REMOVED_MSG, team.getName()));
                    break;
                } else {
                    throw new IllegalArgumentException(String.format(TEAM_NOT_FOUND_ERR_MSG, team.getName()));
                }
            }
        }
    }

    @Override
    public String toString(){
        return String.format("Board: %s%n", getName());
    }
}
