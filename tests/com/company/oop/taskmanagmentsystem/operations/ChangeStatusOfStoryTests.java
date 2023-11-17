package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.ChangeStatusOfStory;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChangeStatusOfStoryTests {
    // TODO The tests should be implemented
    private Command command;
    private List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    @BeforeEach
    public void initChangeSizeOfStoryCommand() {
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        command = new ChangeStatusOfStory(taskManagementSystemRepository);
        parameters = new ArrayList<>();
        parameters.add(String.valueOf(TestsConstants.VALID_ID));

    }

    @Test
    public void Execute_Should_AdvanceStatus_When_ArgumentsValid(){
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.TEST_PRIORITY,
                TestsConstants.TEST_SIZE);
        parameters.add(Constants.OPERATION_ADVANCE);

        Assertions.assertEquals(String.format(Constants.STORY_STATUS_CHANGED_MSG, TestsConstants.VALID_ID,
                Status.NOT_DONE, Status.IN_PROGRESS), command.execute(parameters));
    }
    @Test
    public void Execute_Should_RevertStatus_When_ArgumentsValid(){
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, Priority.MEDIUM,
                TestsConstants.TEST_SIZE);
        parameters.add(Constants.OPERATION_REVERT);
        taskManagementSystemRepository.findTaskById(TestsConstants.VALID_ID).advanceStatus();

        Assertions.assertEquals(String.format(Constants.STORY_STATUS_CHANGED_MSG, TestsConstants.VALID_ID,
                Status.IN_PROGRESS, Status.NOT_DONE), command.execute(parameters));

    }
    @Test
    public void Execute_Should_GiveWarningMessage_When_StatusCannotAdvanceFurther(){
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, Priority.MEDIUM,
                TestsConstants.TEST_SIZE);
        parameters.add(Constants.OPERATION_ADVANCE);
        taskManagementSystemRepository.findTaskById(TestsConstants.VALID_ID).advanceStatus();
        taskManagementSystemRepository.findTaskById(TestsConstants.VALID_ID).advanceStatus();
        Assertions.assertEquals(String.format(Constants.STATUS_IS_ALREADY_SET_TO_DONE_WITH_ID,
                Constants.STORY, TestsConstants.VALID_ID), command.execute(parameters));
    }
    @Test
    public void Execute_Should_GiveWarningMessage_When_StatusCannotRevertFurther(){
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, Priority.MEDIUM,
                TestsConstants.TEST_SIZE);
        parameters.add(Constants.OPERATION_REVERT);

        Assertions.assertEquals(String.format(Constants.STATUS_IS_ALREADY_SET_TO_NOT_DONE_WITH_ID,
                Constants.STORY, TestsConstants.VALID_ID), command.execute(parameters));
    }

    @Test
    public void Execute_Should_ThrownAnException_When_IdDoesNotBelongToStory(){
        taskManagementSystemRepository.createFeedback(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, Priority.MEDIUM,
                TestsConstants.TEST_SIZE);
        parameters.add(Constants.OPERATION_REVERT);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
    @Test
    public void Execute_Should_ThrowAnException_When_OperationArgumentIsInvalid(){
        parameters = new ArrayList<>();
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
        parameters.add("test");

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }

    @Test
    public void Execute_Should_ThrowAnException_When_MissingArguments(){
        parameters = new ArrayList<>();

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
    @Test
    public void Execute_Should_ThrowAnException_When_TaskDoesNotExist(){
        parameters = new ArrayList<>();
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
        parameters.add(Constants.OPERATION_INCREASE);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
}
