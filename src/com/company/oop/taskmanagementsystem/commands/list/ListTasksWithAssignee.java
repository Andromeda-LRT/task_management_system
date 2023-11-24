package com.company.oop.taskmanagementsystem.commands.list;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.Collections;
import java.util.List;

public class ListTasksWithAssignee extends CommandImpl {
    private static final String NO_ASSIGNEES = "There are no members added!";
    private static final String LIST_TASKS_WITH_ASSIGNEE = "Tasks with assignee:";
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    public ListTasksWithAssignee(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return listTasksWithAssignee();
    }

    private String listTasksWithAssignee() {
        if (getTaskManagementSystemRepository().getMembers().isEmpty()) {
            throw new IllegalArgumentException(NO_ASSIGNEES);
        }
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(LIST_TASKS_WITH_ASSIGNEE).append(System.lineSeparator());
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        stringBuilder.append(getTaskManagementSystemRepository().listTasksWithAssignee());

        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return stringBuilder.toString();
    }
}
