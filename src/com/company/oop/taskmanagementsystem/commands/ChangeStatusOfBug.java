package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ChangeStatusOfBug extends CommandImpl {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String ACTION_ERROR_MESSAGE = "The second argument can be: advance or revert";


    public ChangeStatusOfBug(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String action = parameters.get(0);
        int id = ParsingHelpers.tryParseInt(parameters.get(1), Constants.ID_ERROR_MESSAGE);
        return printAction(id, action);
    }

    private String printAction(int id, String action) {
        StringBuilder output = new StringBuilder();
        Task task = getTaskManagementSystemRepository().findTaskById(id);
        if (!(task instanceof Bug)){
            throw new IllegalArgumentException("The provided id should be of a bug.");
        }
        Bug bug = (Bug) getTaskManagementSystemRepository().findTaskById(id);
        switch (action.toUpperCase()) {
            case "ADVANCE":
                bug.advanceStatus();
                output.append(bug.getHistoryOfChanges()
                                .get(bug.getHistoryOfChanges().size() - 1).getDescription())
                        .append(System.lineSeparator());
                break;
            case "REVERT":
                bug.revertStatus();
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
