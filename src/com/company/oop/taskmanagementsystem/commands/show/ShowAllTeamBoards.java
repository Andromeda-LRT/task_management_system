package com.company.oop.taskmanagementsystem.commands.show;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Team;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamBoards extends CommandImpl {

    private static final String NO_ADDED_BOARDS = "There are no boards added to team %s!";

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public ShowAllTeamBoards(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        
        return showAllTeamBoards(teamName);
    }

    private String showAllTeamBoards(String teamName) {
        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);

        if(team.getBoards().isEmpty()){
            throw new IllegalArgumentException(String.format(NO_ADDED_BOARDS, teamName));
        }
        return team.showAllTeamBoards();
    }
}
