package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.change.ChangeTaskPriority;
import com.company.oop.taskmanagementsystem.commands.change.ChangeTaskStatus;
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

public class ChangeTaskPriorityTests {

    private Command command;
    private List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    @BeforeEach
    public void initChangeTaskPriorityCommand() {

        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        command = new ChangeTaskPriority(taskManagementSystemRepository);
        parameters = new ArrayList<>();
        parameters.add(Constants.STORY);
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
    }

    @Test
    public void ChangeTaskPriority_Should_ChangePriority_When_ArgumentsValid(){

        parameters.add("medium");
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.TEST_PRIORITY,
                TestsConstants.TEST_SIZE);
        command.execute(parameters);

        Assertions.assertEquals(Priority.MEDIUM, taskManagementSystemRepository
                .findStoryById(TestsConstants.VALID_ID)
                .getPriority());
    }

    @Test
    public void ChangeTaskPriority_Should_ChangePriority_When_ArgumentsValidAgain(){

        parameters = new ArrayList<>();
        parameters.add(Constants.BUG);
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
        parameters.add("high");
        taskManagementSystemRepository.createBug(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.TEST_PRIORITY, TestsConstants.VALID_SEVERITY);
        command.execute(parameters);

        Assertions.assertEquals(Priority.HIGH, taskManagementSystemRepository
                .findBugByID(TestsConstants.VALID_ID)
                .getPriority());
    }
    @Test
    public void ChangeTaskPriority_Should_ThrowException_When_ArgumentsInvalid(){

        parameters = new ArrayList<>();

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
    @Test
    public void ChangeTaskPriority_Should_ThrowException_When_TaskIdIsNegativeNum(){

        parameters = new ArrayList<>();
        parameters.add(Constants.STORY);
        parameters.add(String.valueOf(TestsConstants.INVALID_ID));
        parameters.add("medium");

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));

    }

    @Test
    public void ChangeTaskPriority_Should_ThrowException_When_PriorityIsInvalidForTask(){

        parameters = new ArrayList<>();
        parameters.add(Constants.STORY);
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
        parameters.add("critical");
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.TEST_PRIORITY,
                TestsConstants.TEST_SIZE);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));

    }
    @Test
    public void ChangeTaskPriority_Should_ThrowException_When_PriorityIsAlreadyTheSame(){

        parameters = new ArrayList<>();
        parameters.add(Constants.STORY);
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
        parameters.add("medium");
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.TEST_PRIORITY,
                TestsConstants.TEST_SIZE);
        command.execute(parameters);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));

    }
    @Test
    public void ChangeTaskPriority_Should_ThrowException_When_TaskTypeIsInvalid(){
        parameters = new ArrayList<>();
        parameters.add(Constants.FEEDBACK);
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
        parameters.add("medium");

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
}
