package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.sort.SortFeedbackByFields;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Feedback;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortFeedbackByFieldsTests {

    private TaskManagementSystemRepository repository;
    private Command command;
    private List<String> parameters;
    private StringBuilder sb;
    private List<Feedback> feedbacksForTest;

    @BeforeEach
    public void initSortStoryByTitlePrioritySeverityCommand(){

        repository = new TaskManagementSystemRepositoryImpl();
        command = new SortFeedbackByFields(repository);
        parameters = new ArrayList<>();
        sb = new StringBuilder();

        repository.createFeedback("eeeeeeeeee", TestsConstants.VALID_DESCRIPTION,
                10);
        repository.createFeedback("dddddddddd", TestsConstants.VALID_DESCRIPTION,
                5);
        repository.createFeedback("cccccccccc", TestsConstants.VALID_DESCRIPTION,
                2);
        repository.createFeedback("lllllllllll", TestsConstants.VALID_DESCRIPTION,
                50);
        repository.createFeedback("xxxxxxxxxx", TestsConstants.VALID_DESCRIPTION,
                20);
        repository.createFeedback("aaaaaaaaaa", TestsConstants.VALID_DESCRIPTION,
                10);
        repository.createFeedback("gggggggggg", TestsConstants.VALID_DESCRIPTION,
                77);
        repository.createFeedback("bbbbbbbbbb", TestsConstants.VALID_DESCRIPTION,
                4);
        feedbacksForTest = repository.getFeedback();
    }

    @Test
    public void SortFeedbackByTitleRating_Should_SortByTitle_When_ArgumentsValid(){
        parameters.add("Title");

        sb.append(String.format(TestsConstants.FEEDBACK_SORTED_BY_GIVEN_PARAMETER, parameters.get(0)))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        Collections.sort(feedbacksForTest);

        for (Feedback feedback : feedbacksForTest) {
            sb.append(feedback.printMainInformation()).append(System.lineSeparator());
        }
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        Assertions.assertEquals(sb.toString(), command.execute(parameters));
        //correct title order should be as follows:
        // a, b, c, d, e, g, l, x
    }

    @Test
    public void SortFeedbackByTitleRating_Should_SortByRating_When_ArgumentsValid(){
        parameters.add("Rating");
        Comparator<Feedback> compareByRating = new Comparator<>() {
            @Override
            public int compare(Feedback o1, Feedback o2) {
                return Integer.compare(o2.getRating(), o1.getRating());
            }
        };
        feedbacksForTest.sort(compareByRating);
        sb.append(String.format(TestsConstants.FEEDBACK_SORTED_BY_GIVEN_PARAMETER, parameters.get(0)))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        for (Feedback feedback : feedbacksForTest) {
            sb.append(feedback.printMainInformation()).append(System.lineSeparator());
        }
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        Assertions.assertEquals(sb.toString(), command.execute(parameters));
    }
    @Test
    public void SortFeedbackByTitleRating_Should_Throw_AnException_When_MissingParameters(){

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }

    @Test
    public void SortFeedbackByTitleRating_Should_Throw_AnException_When_ThereAreNoFeedbacks(){
        parameters.add("Title");
        repository = new TaskManagementSystemRepositoryImpl();
        command = new SortFeedbackByFields(repository);
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
    @Test
    public void SortFeedbackByTitleRating_Should_Throw_AnException_When_InvalidOperation(){
        parameters.add("Size");

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
}
