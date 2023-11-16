package com.company.oop.taskmanagementsystem.models.contracts;

import com.company.oop.taskmanagementsystem.models.LoggerImpl;
import com.company.oop.taskmanagementsystem.models.MemberImpl;
import com.company.oop.taskmanagementsystem.models.TaskImpl;
import com.company.oop.taskmanagementsystem.models.TeamImpl;

import java.util.List;

public interface Board {
    String getName();
    List<Task> getTaskList();
    List<LoggerImpl> getActivityHistory();
    String showBoardActivity();
    List<Team> getTeams();
    void assignTask(Task task, Member memberToAssignTaskTo);
    void unassignTask(Task task, Member memberToUnassignTask);
    void addTask(Task task);
    void removeTask(Task task);
    void addTeam(Team team);
    void removeTeam(Team team);
}
