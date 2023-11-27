package com.company.oop.taskmanagementsystem.commands.filter;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterTasksByStatusAndAssignee extends CommandImpl {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String NO_TASKS_WITH_ASSIGNEE_AND_STATUS = "No tasks with assignee %s and status %s have been found";

    public FilterTasksByStatusAndAssignee(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String status = parameters.get(0).replace("_", " ");
        String assignee = parameters.get(1);

        StringBuilder stringBuilder = new StringBuilder();

        List<Task> tasksWithAssigneeAndStatus = new ArrayList<>();
        tasksWithAssigneeAndStatus.addAll(getTaskManagementSystemRepository()
                .filterBugByStatus(status)
                .stream()
                .filter(element -> element.getAssignee().getName().equals(assignee))
                .toList());
        tasksWithAssigneeAndStatus.addAll(getTaskManagementSystemRepository()
                .filterStoryByStatus(status)
                .stream()
                .filter(element -> element.getAssignee().getName().equals(assignee))
                .toList());

        if (tasksWithAssigneeAndStatus.isEmpty()) {
            throw new IllegalArgumentException(String.format(NO_TASKS_WITH_ASSIGNEE_AND_STATUS, assignee, status));
        }

        stringBuilder.append(tasksWithAssigneeAndStatus
                .stream()
                .map(Task::printMainInformation)
                .collect(Collectors.joining(System.lineSeparator())));

        return stringBuilder.toString();

    }
}
