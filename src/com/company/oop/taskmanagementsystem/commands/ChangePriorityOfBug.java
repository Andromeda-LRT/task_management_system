package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ChangePriorityOfBug extends CommandImpl {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String ACTION_ERROR_MESSAGE = "The second argument can be: increase or lower";

    public ChangePriorityOfBug(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String action = parameters.get(0);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), Constants.ID_ERROR_MESSAGE);
        return printAction(id, action);
    }

    private String printAction(int id, String action) {
        StringBuilder output = new StringBuilder();
        Bug bug;
        switch (action.toLowerCase()) {
            case "increase":
                bug = (Bug) getTaskManagementSystemRepository().findTaskById(id);
                bug.increasePriority();
                output.append(bug.getHistoryOfChanges()
                                .get(bug.getHistoryOfChanges().size() - 1).getDescription())
                        .append(System.lineSeparator());
                break;
            case "lower":
                bug = (Bug) getTaskManagementSystemRepository().findTaskById(id);
                bug.lowerPriority();
                output.append(bug.getHistoryOfChanges()
                        .get(bug.getHistoryOfChanges().size() - 1).getDescription())
                        .append(System.lineSeparator());
                break;
            default:
                throw new IllegalArgumentException(ACTION_ERROR_MESSAGE);
        }
        return output.toString();
    }
}
