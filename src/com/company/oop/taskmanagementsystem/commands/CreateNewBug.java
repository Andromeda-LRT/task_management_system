package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Board;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.Arrays;
import java.util.List;

public class CreateNewBug extends CommandImpl {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;

    public static final String BUG_CREATED_SUCCESSFULLY = "Board %s in Team %s added Bug with title %s successfully";

    public CreateNewBug(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        String boardName = parameters.get(1);
        String title = parameters.get(2);
        String description = parameters.get(3);
        String[] stepsToReproduceArr = parameters.get(4).split(",");
        List<String> stepsToReproduce = Arrays.asList(stepsToReproduceArr);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(5), Priority.class);
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(6), Severity.class);

        return addTask(teamName, boardName,title, description, stepsToReproduce, priority, severity);
    }

    private String addTask(String teamName, String boardName, String title, String description,
                           List<String> stepsToReproduce, Priority priority, Severity severity){

        for (Board boardLocal : getTaskManagementSystemRepository().findTeamByName(teamName).getBoards()) {
            if (boardLocal.getName().equals(boardName)){
                Task bug = createTaskBug(title, description, stepsToReproduce, priority, severity);
                boardLocal.addTask(bug);
                return String.format(BUG_CREATED_SUCCESSFULLY, boardName, teamName, title);
            }
        }

        throw new IllegalArgumentException(String.format(Constants.BOARD_NOT_FOUND_ERR_MSG, boardName, teamName));
    }

    private Task createTaskBug(String title, String description,
                               List<String> stepsToReproduce, Priority priority, Severity severity){

            return getTaskManagementSystemRepository().createBug(
                    title, description, stepsToReproduce, priority, severity);
    }

}
