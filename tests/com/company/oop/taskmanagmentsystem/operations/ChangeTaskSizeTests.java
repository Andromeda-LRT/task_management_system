package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.change.ChangeTaskPriority;
import com.company.oop.taskmanagementsystem.commands.change.ChangeTaskSize;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Size;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChangeTaskSizeTests {

    private Command command;
    private List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    @BeforeEach
    public void initChangeTaskSizeCommand() {

        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        command = new ChangeTaskSize(taskManagementSystemRepository);
        parameters = new ArrayList<>();
        parameters.add(Constants.STORY);
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
    }

    @Test
    public void ChangeTaskSize_Should_ChangeSize_When_ArgumentsValid(){

        parameters.add("small");
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.TEST_PRIORITY,
                TestsConstants.TEST_SIZE);
        command.execute(parameters);

        Assertions.assertEquals(Size.SMALL, taskManagementSystemRepository
                .findStoryById(TestsConstants.VALID_ID)
                .getSize());
    }

    @Test
    public void ChangeTaskSize_Should_ChangeSize_When_ArgumentsValidAgain(){

        parameters = new ArrayList<>();
        parameters.add(Constants.STORY);
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
        parameters.add("large");
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.TEST_PRIORITY,
                TestsConstants.TEST_SIZE);
        command.execute(parameters);

        Assertions.assertEquals(Size.LARGE, taskManagementSystemRepository
                .findStoryById(TestsConstants.VALID_ID)
                .getSize());
    }
    @Test
    public void ChangeTaskSize_Should_ThrowException_When_ArgumentsInvalid(){

        parameters = new ArrayList<>();

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
    @Test
    public void ChangeTaskSize_Should_ThrowException_When_TaskIdIsNegativeNum(){

        parameters = new ArrayList<>();
        parameters.add(Constants.STORY);
        parameters.add(String.valueOf(TestsConstants.INVALID_ID));
        parameters.add("medium");

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));

    }

    @Test
    public void ChangeTaskSize_Should_ThrowException_When_SizeIsInvalidForTask(){

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
    public void ChangeTaskSize_Should_ThrowException_When_SizeIsAlreadyTheSame(){

        parameters = new ArrayList<>();
        parameters.add(Constants.STORY);
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
        parameters.add("medium");
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.TEST_PRIORITY,
                TestsConstants.TEST_SIZE);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));

    }
    @Test
    public void ChangeTaskSize_Should_ThrowException_When_TaskTypeIsInvalid(){
        parameters = new ArrayList<>();
        parameters.add(Constants.FEEDBACK);
        parameters.add(String.valueOf(TestsConstants.VALID_ID));
        parameters.add("medium");

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
}
