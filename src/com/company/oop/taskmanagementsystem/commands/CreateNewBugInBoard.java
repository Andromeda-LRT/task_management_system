package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class CreateNewBugInBoard implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;
    //todo unsure if this is needed as logger adds the creation of a task.
    //public static final String BUG_CREATED_SUCCESSFULLY = "Bug created successfully";

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public CreateNewBugInBoard(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        return null;
    }
}
