package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Team;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamMembers extends CommandImpl {

    private static final String NO_MEMBERS_ADDED_TO_TEAM = "There are no members added to team %s.";
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowAllTeamMembers(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String teamName = parameters.get(0);

        return showAllTeamMembers(teamName);
    }

    private String showAllTeamMembers(String teamName) {
        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);

        if(team.getMembers().isEmpty()){
            throw new IllegalArgumentException(String.format(NO_MEMBERS_ADDED_TO_TEAM, teamName));
        }
        return team.showAllTeamMembers();
    }
}
//TODO print should be fixed - Lyubima
//    SHOWALLTEAMMEMBERS team123
//        team123---------------------
//        Members:---------------------
//        Lyubima
//        Reneta
//        ---------------------
