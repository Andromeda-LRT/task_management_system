package com.company.oop.taskmanagementsystem.commands.list;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Board;
import com.company.oop.taskmanagementsystem.models.contracts.Comment;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ListAllTasks extends CommandImpl {
    private static final String ALL_TASKS = "---ALL TASKS---";
    private static final String NO_TASKS = "No tasks were found!";

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    public ListAllTasks(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        return listAllTasks();
    }

    private String listAllTasks() {
        StringBuilder stringBuilder = new StringBuilder();

        if(getTaskManagementSystemRepository().getTasks().isEmpty()){
            stringBuilder.append(NO_TASKS).append(System.lineSeparator());
            return stringBuilder.toString();
        }

        stringBuilder.append(ALL_TASKS).append(System.lineSeparator());
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        for (Task task : getTaskManagementSystemRepository().getTasks()) {
            stringBuilder.append(task.printMainInformation()).append(System.lineSeparator());
        }
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return stringBuilder.toString();
    }
}
