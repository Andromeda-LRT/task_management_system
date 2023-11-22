package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ListAllBugs extends CommandImpl {
    private static final String ALL_BUGS = "---ALL BUGS---";
    private static final String NO_ADDED_BUGS = "No bugs were found!";
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    public ListAllBugs(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        return listAllBugs();
    }

    private String listAllBugs() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Bug> bugs = getTaskManagementSystemRepository().getBugs();

        if (bugs.isEmpty()) {
            stringBuilder.append(NO_ADDED_BUGS).append(System.lineSeparator());
            stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());
            return stringBuilder.toString();
        }

        stringBuilder.append(ALL_BUGS).append(System.lineSeparator());
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        for (Bug bug : bugs) {
            stringBuilder.append(bug.printMainInformation()).append(System.lineSeparator());
        }
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return stringBuilder.toString();
    }
}
