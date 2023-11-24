package com.company.oop.taskmanagementsystem.commands.create;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class CreateNewTeam extends CommandImpl {
    private static final String TEAM_CREATED = "Team with name %s was created!";
    private static final String TEAM_ALREADY_EXISTS = "Team with name %s already exists!";


    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public CreateNewTeam(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
       ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
       String teamName = parameters.get(0);
       return createTeam(teamName);
    }

    private String createTeam(String teamName) {
        if (getTaskManagementSystemRepository().teamExist(teamName)) {
            throw new IllegalArgumentException(String.format(TEAM_ALREADY_EXISTS, teamName));
        }

        getTaskManagementSystemRepository().createTeam(teamName);

        return String.format(TEAM_CREATED, teamName);
    }
}
