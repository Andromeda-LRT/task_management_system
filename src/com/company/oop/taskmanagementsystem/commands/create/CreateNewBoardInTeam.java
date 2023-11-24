package com.company.oop.taskmanagementsystem.commands.create;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Board;
import com.company.oop.taskmanagementsystem.models.contracts.Team;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class CreateNewBoardInTeam extends CommandImpl {
    private static final String BOARD_ADDED_TO_TEAM = "Board %s was created in team %s.";

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public CreateNewBoardInTeam(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String boardName = parameters.get(0);
        String teamName = parameters.get(1);

        return createNewBoardInTeam(boardName, teamName);
    }

    private String createNewBoardInTeam(String boardName, String teamName) {
        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);

        Board board = getTaskManagementSystemRepository().createBoard(boardName);

        team.addBoard(board);

        return String.format(BOARD_ADDED_TO_TEAM, boardName, teamName);
    }
}