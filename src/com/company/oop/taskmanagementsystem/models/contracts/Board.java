package com.company.oop.taskmanagementsystem.models.contracts;

import com.company.oop.taskmanagementsystem.models.LoggerImpl;
import com.company.oop.taskmanagementsystem.models.MemberImpl;
import com.company.oop.taskmanagementsystem.models.TaskImpl;
import com.company.oop.taskmanagementsystem.models.TeamImpl;

import java.util.List;

public interface Board {
    String getName();
    List<TaskImpl> getTaskList();
    List<LoggerImpl> getActivityHistory();
    String showBoardActivity();
   List<TeamImpl> getTeams();
    void assignTask(TaskImpl task, MemberImpl memberToAssignTaskTo);
    void unassignTask(TaskImpl task, MemberImpl memberToUnassignTask);
    void addTask(TaskImpl task);
    void removeTask(TaskImpl task);
    void addTeam(TeamImpl team);
    void removeTeam(TeamImpl team);

}
