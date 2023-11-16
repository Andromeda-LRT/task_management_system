package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class CreateNewTeam extends CommandImpl {

    public CreateNewTeam(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        return null;
    }

    private String createTeam(String teamName) {
        if (getTaskManagementSystemRepository().teamExist(teamName)) {
            throw new IllegalArgumentException(String.format(TEAM_ALREADY_EXISTS, teamName));
        }

        getTaskManagementSystemRepository().createTeam(teamName);

        return String.format(TEAM_CREATED, teamName);
    }
}
