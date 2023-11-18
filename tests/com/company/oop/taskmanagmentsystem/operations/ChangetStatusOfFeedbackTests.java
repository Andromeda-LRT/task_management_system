package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.ChangeStatusOfFeedback;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ChangetStatusOfFeedbackTests {
    private final TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();

    @Test
    void execute_Should_AdvanceStatusOfFeedback_When_ActionIsAdvance() {
        ChangeStatusOfFeedback changeStatusCommand = new ChangeStatusOfFeedback(repository);
        List<String> parameters = List.of("ADVANCE", "1");

        repository.createFeedback(
                "Feedback Task",
                "DescriptionDescriptionDescription",
                10);

        String result = changeStatusCommand.execute(parameters);

        Assertions.assertTrue(result.contains("Feedback status changed from New to Unscheduled."));
    }

    @Test
    void execute_Should_RevertStatusOfFeedback_When_ActionIsRevert() {
        ChangeStatusOfFeedback changeStatusCommand = new ChangeStatusOfFeedback(repository);

        repository.createFeedback(
                "Feedback Task",
                "DescriptionDescriptionDescription",
                10);

        List<String> parameters_advance = List.of("ADVANCE", "1");
        changeStatusCommand.execute(parameters_advance);

        List<String> parameters_revert = List.of("REVERT", "1");

        String result = changeStatusCommand.execute(parameters_revert);

        Assertions.assertTrue(result.contains("Feedback status changed from Unscheduled to New."));
    }

    @Test
    void execute_Should_ThrowIllegalArgumentException_When_InvalidAction() {
        ChangeStatusOfFeedback changeStatusCommand = new ChangeStatusOfFeedback(repository);
        List<String> parameters = List.of("INVALID_ACTION", "1");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            changeStatusCommand.execute(parameters);
        });
    }
}
