package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.TaskImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateNewBugInBoard extends CommandImpl {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;
    //todo unsure if this is needed as logger adds the creation of a task.
    public static final String BUG_CREATED_SUCCESSFULLY = "Board %s added Bug with title %s successfully";

    public CreateNewBugInBoard(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String boardName = parameters.get(0);
        String title = parameters.get(1);
        String description = parameters.get(2);
        String[] stepsToReproduceArr = parameters.get(3).split(",");
        List<String> stepsToReproduce = Arrays.asList(stepsToReproduceArr);
        Priority priority = ParsingHelpers.tryParseEnum(parameters.get(4), Priority.class);
        Severity severity = ParsingHelpers.tryParseEnum(parameters.get(5), Severity.class);
       // return addTask;
        return addTask(boardName,title, description, stepsToReproduce, priority, severity);
    }

    private String addTask(String boardName, String title, String description,
                           List<String> stepsToReproduce, Priority priority, Severity severity){
        Task bug = createTaskBug(title, description, stepsToReproduce, priority, severity);

        getTaskManagementSystemRepository().findBoardByName(boardName).addTask(bug);

        return String.format(BUG_CREATED_SUCCESSFULLY, boardName, title);
    }

    private Task createTaskBug(String title, String description,
                               List<String> stepsToReproduce, Priority priority, Severity severity){

            return getTaskManagementSystemRepository().createBug(
                    title, description, stepsToReproduce, priority, severity);
    }

}
