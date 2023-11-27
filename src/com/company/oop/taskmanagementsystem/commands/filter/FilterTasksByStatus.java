package com.company.oop.taskmanagementsystem.commands.filter;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterTasksByStatus extends CommandImpl {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String NO_TASKS_WITH_STATUS = "No tasks with status %s have been found";


    public FilterTasksByStatus(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        StringBuilder string = new StringBuilder();
        String status = parameters.get(0).replace("_", " ");

        List<Task> tasksWithStatus = new ArrayList<>();
        tasksWithStatus.addAll(getTaskManagementSystemRepository().filterBugByStatus(status));
        tasksWithStatus.addAll(getTaskManagementSystemRepository().filterStoryByStatus(status));

        if (tasksWithStatus.isEmpty()) {
            throw new IllegalArgumentException(String.format(NO_TASKS_WITH_STATUS, parameters.get(0)));
        }

        string.append(tasksWithStatus
                .stream()
                .map(Task::printMainInformation)
                .collect(Collectors.joining(System.lineSeparator())));

        return string.toString();

    }
}
