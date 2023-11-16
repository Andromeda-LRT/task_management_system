package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Team;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeams extends CommandImpl {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    public ShowAllTeams(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        return showAllTeams();
    }

    private String showAllTeams() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(Constants.ALL_TEAMS).append(System.lineSeparator());
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        for (Team team : getTaskManagementSystemRepository().getTeams()){
            stringBuilder.append(team.getName()).append(System.lineSeparator());
        }

        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return stringBuilder.toString();
    }
}
