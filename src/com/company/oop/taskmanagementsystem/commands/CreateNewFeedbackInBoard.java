package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Board;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class CreateNewFeedbackInBoard extends CommandImpl {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;
    public static final String FEEDBACK_CREATED_SUCCESSFULLY = "Board %s in Team %s added Feedback with title %s successfully";

    public CreateNewFeedbackInBoard(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String teamName = parameters.get(0);
        String boardName = parameters.get(1);
        String title = parameters.get(2);
        String description = parameters.get(3);
        //todo implement the parsingHelper for ints later
        int rating = Integer.parseInt(parameters.get(4));

        return addTask(teamName, boardName, title, description, rating);
    }

    private String addTask(String teamName, String boardName, String title, String description, int rating){

        Task feedback = createTaskFeedback(title, description, rating);
        for (Board boardLocal : getTaskManagementSystemRepository().findTeamByName(teamName).getBoards()) {
            if (boardLocal.getName().equals(boardName)){
                boardLocal.addTask(feedback);
                return String.format(FEEDBACK_CREATED_SUCCESSFULLY,boardName, teamName, title);
            }
        }
        throw new IllegalArgumentException(String.format(Constants.BOARD_NOT_FOUND_ERR_MSG, boardName, teamName));

    }

    private Task createTaskFeedback(String title, String description, int rating){

        return getTaskManagementSystemRepository().createFeedback(title, description, rating);
    }
}
