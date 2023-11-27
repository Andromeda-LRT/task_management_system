package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.change.ChangeTaskStatus;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChangeTaskStatusTests {

    private Command command;
    private List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    @BeforeEach
    public void initChangeSizeOfStoryCommand() {

        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        command = new ChangeTaskStatus(taskManagementSystemRepository);
        parameters = new ArrayList<>();
        parameters.add(Constants.STORY);
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
    }

    @Test
    public void ChangeTaskStatus_Should_ChangeStatus_When_ArgumentsValid(){

        parameters.add("in");
        parameters.add("progress");
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.TEST_PRIORITY,
                TestsConstants.TEST_SIZE);
        command.execute(parameters);

        Assertions.assertEquals(Status.IN_PROGRESS, taskManagementSystemRepository
                .findTaskById(TestsConstants.VALID_ID)
                .getStatus());
    }

    @Test
    public void ChangeTaskStatus_Should_ChangeStatus_When_ArgumentsValidAgain(){

        parameters.add("done");
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.TEST_PRIORITY,
                TestsConstants.TEST_SIZE);
        command.execute(parameters);

        Assertions.assertEquals(Status.DONE, taskManagementSystemRepository
                .findTaskById(TestsConstants.VALID_ID)
                .getStatus());
    }
    @Test
    public void ChangeTaskStatus_Should_ThrowException_When_ArgumentsInvalid(){

        parameters = new ArrayList<>();

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
    @Test
    public void ChangeTaskStatus_Should_ThrowException_When_TaskIdIsNegativeNum(){

        parameters = new ArrayList<>();
        parameters.add(Constants.STORY);
        parameters.add(String.valueOf(TestsConstants.INVALID_ID));
        parameters.add("done");

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));

    }

    @Test
    public void ChangeTaskStatus_Should_ThrowException_When_StatusIsInvalidForTask(){

        parameters = new ArrayList<>();
        parameters.add(Constants.STORY);
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
        parameters.add("unscheduled");
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.TEST_PRIORITY,
                TestsConstants.TEST_SIZE);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));

    }
    @Test
    public void ChangeTaskStatus_Should_ThrowException_When_StatusIsAlreadyTheSame(){

        parameters = new ArrayList<>();
        parameters.add(Constants.STORY);
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
        parameters.add("done");
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.TEST_PRIORITY,
                TestsConstants.TEST_SIZE);
        command.execute(parameters);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));

    }
}
