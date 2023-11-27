package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.change.ChangeTaskSeverity;
import com.company.oop.taskmanagementsystem.commands.change.ChangeTaskSize;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import com.company.oop.taskmanagementsystem.models.enums.Size;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChangeTaskSeverityTests {

    private Command command;
    private List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    @BeforeEach
    public void initChangeTaskSizeCommand() {

        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        command = new ChangeTaskSeverity(taskManagementSystemRepository);
        parameters = new ArrayList<>();
        parameters.add(Constants.BUG);
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
    }

    @Test
    public void ChangeTaskSeverity_Should_ChangeSeverity_When_ArgumentsValid(){

        parameters.add("minor");
        taskManagementSystemRepository.createBug(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.TEST_PRIORITY, TestsConstants.VALID_SEVERITY);
        command.execute(parameters);

        Assertions.assertEquals(Severity.MINOR, taskManagementSystemRepository
                .findBugByID(TestsConstants.VALID_ID)
                .getSeverity());
    }

    @Test
    public void ChangeTaskSeverity_Should_ChangeSeverity_When_ArgumentsValidAgain(){

        parameters = new ArrayList<>();
        parameters.add(Constants.BUG);
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
        parameters.add("major");
        taskManagementSystemRepository.createBug(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.TEST_PRIORITY, TestsConstants.VALID_SEVERITY);
        command.execute(parameters);

        Assertions.assertEquals(Severity.MAJOR, taskManagementSystemRepository
                .findBugByID(TestsConstants.VALID_ID)
                .getSeverity());
    }
    @Test
    public void ChangeTaskSize_Should_ThrowException_When_ArgumentsInvalid(){

        parameters = new ArrayList<>();

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
    @Test
    public void ChangeTaskSeverity_Should_ThrowException_When_TaskIdIsNegativeNum(){

        parameters = new ArrayList<>();
        parameters.add(Constants.BUG);
        parameters.add(String.valueOf(TestsConstants.INVALID_ID));
        parameters.add("major");

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));

    }

    @Test
    public void ChangeTaskSeverity_Should_ThrowException_When_SeverityIsInvalidForTask(){

        parameters = new ArrayList<>();
        parameters.add(Constants.BUG);
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
        parameters.add("large");
        taskManagementSystemRepository.createBug(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.TEST_PRIORITY, TestsConstants.VALID_SEVERITY);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));

    }
    @Test
    public void ChangeTaskSeverity_Should_ThrowException_When_SeverityIsAlreadyTheSame(){

        parameters = new ArrayList<>();
        parameters.add(Constants.BUG);
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
        parameters.add("critical");
        taskManagementSystemRepository.createBug(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.TEST_PRIORITY, TestsConstants.VALID_SEVERITY);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));

    }
    @Test
    public void ChangeTaskSeverity_Should_ThrowException_When_TaskTypeIsInvalid(){
        parameters = new ArrayList<>();
        parameters.add(Constants.FEEDBACK);
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
        parameters.add("minor");

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
}
