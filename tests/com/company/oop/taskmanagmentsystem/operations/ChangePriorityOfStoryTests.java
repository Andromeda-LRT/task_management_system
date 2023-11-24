package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.change.ChangePriorityOfStory;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChangePriorityOfStoryTests {

    private Command command;
    private List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    @BeforeEach
    public void initChangeSizeOfStoryCommand() {
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        command = new ChangePriorityOfStory(taskManagementSystemRepository);
        parameters = new ArrayList<>();
        parameters.add(String.valueOf(TestsConstants.VALID_ID));

    }

    @Test
    public void Execute_Should_IncreasePriority_When_ArgumentsValid(){
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.TEST_PRIORITY,
                TestsConstants.TEST_SIZE);
        parameters.add(Constants.OPERATION_INCREASE);

        Assertions.assertEquals(String.format(Constants.STORY_PRIORITY_CHANGED_MSG, TestsConstants.VALID_ID,
                TestsConstants.TEST_PRIORITY, Priority.MEDIUM), command.execute(parameters));
    }
    @Test
    public void Execute_Should_LowerPriority_When_ArgumentsValid(){
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, Priority.MEDIUM,
                TestsConstants.TEST_SIZE);
        parameters.add(Constants.OPERATION_LOWER);

        Assertions.assertEquals(String.format(Constants.STORY_PRIORITY_CHANGED_MSG, TestsConstants.VALID_ID,
                Priority.MEDIUM, Priority.LOW), command.execute(parameters));
    }
    @Test
    public void Execute_Should_GiveWarningMessage_When_PriorityCannotIncreaseFurther(){
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, Priority.MEDIUM,
                TestsConstants.TEST_SIZE);
        parameters.add(Constants.OPERATION_INCREASE);
        Story story = taskManagementSystemRepository.findStoryById(TestsConstants.VALID_ID);
        story.increasePriority();

        Assertions.assertEquals(String.format(Constants.PRIORITY_IS_ALREADY_SET_TO_HIGH_WITH_ID,
                Constants.STORY, TestsConstants.VALID_ID), command.execute(parameters));
    }
    @Test
    public void Execute_Should_GiveWarningMessage_When_PriorityCannotLowerFurther(){
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, Priority.MEDIUM,
                TestsConstants.TEST_SIZE);
        parameters.add(Constants.OPERATION_LOWER);
        Story story = taskManagementSystemRepository.findStoryById(TestsConstants.VALID_ID);
        story.lowerPriority();

        Assertions.assertEquals(String.format(Constants.PRIORITY_IS_ALREADY_SET_TO_LOW_WITH_ID,
                Constants.STORY, TestsConstants.VALID_ID), command.execute(parameters));
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
    public void Execute_Should_ThrownAnException_When_IdDoesNotBelongToStory(){
        taskManagementSystemRepository.createFeedback(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
        taskManagementSystemRepository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, Priority.MEDIUM,
                TestsConstants.TEST_SIZE);
        parameters.add(Constants.OPERATION_LOWER);

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
