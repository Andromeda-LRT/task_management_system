package com.company.oop.taskmanagementsystem.commands.create;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Board;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Size;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class CreateNewStory extends CommandImpl {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;
    public static final String STORY_CREATED_SUCCESSFULLY = "Board %s in Team %s added Story with title %s successfully";

    public CreateNewStory(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        String boardName = parameters.get(1);
        String title = parameters.get(2);
        String description = parameters.get(3);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(4), Priority.class);
        Size size = ParsingHelpers.tryParseEnum(parameters.get(5), Size.class);


        return addTask(teamName, boardName, title, description, priority, size);
    }

    private String addTask(String teamName, String boardName, String title, String description,
                           Priority priority, Size size){


        for (Board boardLocal : getTaskManagementSystemRepository().findTeamByName(teamName).getBoards()) {
            if (boardLocal.getName().equals(boardName)){
                Task story = createTaskStory(title, description, priority, size);
                boardLocal.addTask(story);
                return String.format(STORY_CREATED_SUCCESSFULLY, boardName, teamName, title);
            }
        }
        throw new IllegalArgumentException(String.format(Constants.BOARD_NOT_FOUND_ERR_MSG, boardName, teamName));
    }

    private Task createTaskStory(String title, String description, Priority priority, Size size){
        return getTaskManagementSystemRepository().createStory(title, description, priority, size);
    }

}
