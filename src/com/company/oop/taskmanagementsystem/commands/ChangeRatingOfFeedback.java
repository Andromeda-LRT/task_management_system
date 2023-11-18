package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Feedback;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;

import java.util.List;

public class ChangeRatingOfFeedback extends CommandImpl {
    private static final String ID_FEEDBACK_SHOULD_BE_DIGIT = "Id of feedback should be a digit!";
    private static final String ERROR_MESSAGE_NOT_VALID_RATING = "Rating of feedback should be a digit!";
    private static final String NO_FEEDBACK_INSTANCE = "The provided id should be of a feedback.";

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeRatingOfFeedback(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        int idOfFeedback = ParsingHelpers.tryParseInt(parameters.get(0), ID_FEEDBACK_SHOULD_BE_DIGIT);
        int ratingOfFeedback = ParsingHelpers.tryParseInt(parameters.get(1), ERROR_MESSAGE_NOT_VALID_RATING);

        return changeRatingOfFeedback(idOfFeedback, ratingOfFeedback);
    }

    private String changeRatingOfFeedback(int idOfFeedback, int ratingOfFeedback) {
        StringBuilder stringBuilder = new StringBuilder();

        Task task = getTaskManagementSystemRepository().findTaskById(idOfFeedback);
        if (!(task instanceof Feedback)) {
            throw new IllegalArgumentException(NO_FEEDBACK_INSTANCE);
        }

        Feedback feedback = (Feedback) getTaskManagementSystemRepository().findTaskById(idOfFeedback);

        feedback.changeRating(ratingOfFeedback);

        stringBuilder.append(
                feedback.getHistoryOfChanges()
                .get(feedback.getHistoryOfChanges().size()-1)
                .getDescription())
                .append(System.lineSeparator());

        return stringBuilder.toString();
    }
}
