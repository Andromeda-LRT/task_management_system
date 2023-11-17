package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.ChangeRatingOfFeedback;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Feedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ChangetRatingOfFeedbackTests {

    private final TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();

    @Test
    void execute_Should_ChangeRatingOfFeedback_When_ValidArguments() {
        ChangeRatingOfFeedback changeRatingCommand = new ChangeRatingOfFeedback(repository);
        List<String> parameters = List.of("1", "5");


        repository.createFeedback(
                "Feedback Task",
                "DescriptionDescriptionDescription",
                10);

        String result = changeRatingCommand.execute(parameters);

        Assertions.assertTrue(result.contains("Rating of feedback was changed from 10 to 5."));
    }

    @Test
    void execute_Should_ThrowIllegalArgumentException_When_InvalidId() {
        ChangeRatingOfFeedback changeRatingCommand = new ChangeRatingOfFeedback(repository);
        List<String> parameters = List.of("invalidId", "5");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            changeRatingCommand.execute(parameters);
        });
    }

    @Test
    void execute_Should_ThrowIllegalArgumentException_When_InvalidRating() {
        ChangeRatingOfFeedback changeRatingCommand = new ChangeRatingOfFeedback(repository);
        List<String> parameters = List.of("1", "invalidRating");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            changeRatingCommand.execute(parameters);
        });
    }

    @Test
    void execute_Should_ThrowIllegalArgumentException_When_InvalidTaskId() {
        ChangeRatingOfFeedback changeRatingCommand = new ChangeRatingOfFeedback(repository);
        List<String> parameters = List.of("2", "5");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            changeRatingCommand.execute(parameters);
        });
    }
}
