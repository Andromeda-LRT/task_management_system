package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Feedback;
import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortFeedbackByTitleRating extends CommandImpl {
    private static final String FEEDBACK_SORTED_BY_GIVEN_PARAMETER = "---FEEDBACK SORTED BY %S---";
    private static final String NO_FEEDBACKS_TO_SORT = "There are no added Feedbacks";
    private static final String INVALID_SORT_OPERATION = "%s is not a valid sort operation, " +
            "please input either Title or Rating";
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public SortFeedbackByTitleRating(TaskManagementSystemRepository taskManagementSystemRepository){
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        List<Feedback> feedbacks = getTaskManagementSystemRepository().findAllFeedbackInTasks();
        if (feedbacks.isEmpty()){
            throw new IllegalArgumentException(NO_FEEDBACKS_TO_SORT);
        }
        StringBuilder sb = new StringBuilder();

        switch (parameters.get(0).toUpperCase()){
            case "TITLE":
                return sortFeedbacksByTitle(sb, parameters.get(0), feedbacks);
            case"RATING":
                return sortFeedbacksByRating(sb, parameters.get(0), feedbacks);
        }
        throw new IllegalArgumentException(String.format(INVALID_SORT_OPERATION, parameters.get(0)));
    }

    private String sortFeedbacksByTitle(StringBuilder sb, String sortOperation, List<Feedback> feedbacks){

        sb.append(String.format(FEEDBACK_SORTED_BY_GIVEN_PARAMETER, sortOperation))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        Collections.sort(feedbacks);

        for (Feedback feedback : feedbacks) {
            sb.append(feedback.printMainInformation()).append(System.lineSeparator());
        }
            sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return sb.toString();
    }

    private String sortFeedbacksByRating(StringBuilder sb, String sortOperation, List<Feedback> feedbacks){
        Comparator<Feedback> compareByRating = new Comparator<>() {
            @Override
            public int compare(Feedback o1, Feedback o2) {
                return Integer.compare(o1.getRating(), o2.getRating());
            }
        };
        sb.append(String.format(FEEDBACK_SORTED_BY_GIVEN_PARAMETER, sortOperation))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        feedbacks.sort(compareByRating);

        for (Feedback feedback : feedbacks) {
            sb.append(feedback.printMainInformation()).append(System.lineSeparator());
            sb.append(String.format("%s: ", sortOperation.toUpperCase())).append(feedback.getRating()).append(System.lineSeparator());
        }
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return sb.toString();
    }
}
