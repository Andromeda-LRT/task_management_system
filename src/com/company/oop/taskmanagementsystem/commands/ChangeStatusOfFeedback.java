package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.contracts.Feedback;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ChangeStatusOfFeedback extends CommandImpl {
    private static final String ACTION_ERROR_MESSAGE = "Status of feedback can be advanced or reverted!";
    private static final String NO_FEEDBACK_INSTANCE = "The provided id should be of a feedback.";
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeStatusOfFeedback(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String action = parameters.get(0);
        int id = ParsingHelpers.tryParseInt(parameters.get(1), Constants.ID_ERROR_MESSAGE);
        return changeStatusOfFeedback(id, action);
    }

    private String changeStatusOfFeedback(int id, String action) {
        StringBuilder stringBuilder = new StringBuilder();

        Task task = getTaskManagementSystemRepository().findTaskById(id);

        if (!(task instanceof Feedback)) {
            throw new IllegalArgumentException(NO_FEEDBACK_INSTANCE);
        }
        Feedback feedback = (Feedback) getTaskManagementSystemRepository().findTaskById(id);

        switch (action.toUpperCase()) {
            case "ADVANCE":
                feedback.advanceStatus();
                stringBuilder.append(feedback.getHistoryOfChanges()
                             .get(feedback.getHistoryOfChanges().size() - 1)
                             .getDescription())
                             .append(System.lineSeparator());
                break;
            case "REVERT":
                feedback.revertStatus();
                stringBuilder.append(feedback.getHistoryOfChanges()
                              .get(feedback.getHistoryOfChanges().size() - 1)
                              .getDescription())
                              .append(System.lineSeparator());
                break;
            default:
                throw new IllegalArgumentException(ACTION_ERROR_MESSAGE);
        }

        return stringBuilder.toString();
    }
}
