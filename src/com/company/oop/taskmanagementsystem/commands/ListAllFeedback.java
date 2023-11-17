package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.contracts.Feedback;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ListAllFeedback extends CommandImpl {
    private static final String ALL_FEEDBACK = "---ALL FEEDBACK---";
    private static final String NO_ADDED_FEEDBACK = "No feedbacks were found!";
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    public ListAllFeedback(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        return listAllFeedback();
    }

    private String listAllFeedback() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Feedback> feedbacks = getTaskManagementSystemRepository().findAllFeedbackInTasks();

        if (feedbacks.isEmpty()) {
            stringBuilder.append(NO_ADDED_FEEDBACK).append(System.lineSeparator());
            stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());
            return stringBuilder.toString();
        }

        stringBuilder.append(ALL_FEEDBACK).append(System.lineSeparator());
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        for (Feedback feedback : feedbacks) {
            stringBuilder.append(feedback.printMainInformation()).append(System.lineSeparator());
        }
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return stringBuilder.toString();
    }
}
