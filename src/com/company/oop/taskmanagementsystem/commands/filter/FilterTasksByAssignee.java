package com.company.oop.taskmanagementsystem.commands.filter;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterTasksByAssignee extends CommandImpl {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String NO_TASKS_WITH_ASSIGNEE = "No tasks with assignee %s have been found";


    public FilterTasksByAssignee(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        StringBuilder string = new StringBuilder();

        List<Task> tasksWithAssignee = new ArrayList<>();
        tasksWithAssignee.addAll(getTaskManagementSystemRepository().filterBugByAssignee(parameters.get(0)));
        tasksWithAssignee.addAll(getTaskManagementSystemRepository().filterStoryByAssignee(parameters.get(0)));

        if (tasksWithAssignee.isEmpty()) {
            throw new IllegalArgumentException(String.format(NO_TASKS_WITH_ASSIGNEE, parameters.get(0)));
        }

        string.append(tasksWithAssignee
                .stream()
                .map(Task::printMainInformation)
                .collect(Collectors.joining(System.lineSeparator())));

        return string.toString();
    }
}
