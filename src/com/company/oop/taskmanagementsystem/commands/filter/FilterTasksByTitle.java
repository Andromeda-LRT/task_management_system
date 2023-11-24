package com.company.oop.taskmanagementsystem.commands.filter;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class FilterTasksByTitle extends CommandImpl {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String TITLE_NOT_FOUND = "No tasks with title %s have been found";
    private static final String FILTERED_TITLES = "---Titles filtered by %s ---";

    public FilterTasksByTitle(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        StringBuilder sb = new StringBuilder();
        List<Task> tasksFilteredByTitle = getTaskManagementSystemRepository().
                filterTaskByTitle(parameters.get(0));

        if (tasksFilteredByTitle.isEmpty()){
            throw new IllegalArgumentException(String.format(TITLE_NOT_FOUND, parameters.get(0)));
        }

        sb.append(String.format(FILTERED_TITLES, parameters.get(0))).append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        for (Task filteredTask : tasksFilteredByTitle) {
            sb.append(filteredTask.printMainInformation()).append(System.lineSeparator());
        }
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return sb.toString();
    }
}
