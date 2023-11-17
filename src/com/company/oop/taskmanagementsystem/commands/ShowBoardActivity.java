package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Board;
import com.company.oop.taskmanagementsystem.models.contracts.Team;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ShowBoardActivity extends CommandImpl{

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;


    public ShowBoardActivity(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        String boardName = parameters.get(1);

        return showBoardActivity(teamName, boardName);
    }

    private String showBoardActivity(String teamName, String boardName){

        for (Board boardLocal : getTaskManagementSystemRepository().findTeamByName(teamName).getBoards()) {
            if (boardLocal.getName().equals(boardName)){
                return boardLocal.showBoardActivity();
            }
        }
        throw new IllegalArgumentException(String.format(Constants.BOARD_NOT_FOUND_ERR_MSG, boardName, teamName));
    }
}
