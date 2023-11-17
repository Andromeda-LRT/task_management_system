package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.AssignTaskToMember;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.contracts.Feedback;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AssignTaskToMemberTests {
    private static final String VALID_ID = "1";
    private static final String INVALID_ID = "string";
    private List<String> parameters;
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void setUp() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
    }

    @Test
    void addTaskToMember_Should_AddNewTaskToMember_When_ValidParametersAreProvided(){
        parameters.add(VALID_ID);
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        Bug bug = repository.createBug(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.VALID_PRIORITY,
                TestsConstants.VALID_SEVERITY);
        repository.createMember(TestsConstants.VALID_MEMBER_NAME);
        AssignTaskToMember assignTaskToMember = new AssignTaskToMember(repository);
        assignTaskToMember.execute(parameters);
        Assertions.assertTrue(repository.findMemberByName(TestsConstants.VALID_MEMBER_NAME).getListOfTasks().contains(bug));
    }

    @Test
    void addTaskToMember_Should_ThrowAnException_When_FeedbackIsProvided(){
        parameters.add(VALID_ID);
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        Feedback feedback = repository.createFeedback(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.VALID_RATING);
        repository.createMember(TestsConstants.VALID_MEMBER_NAME);
        AssignTaskToMember assignTaskToMember = new AssignTaskToMember(repository);
        Assertions.assertThrows(IllegalArgumentException.class, () -> assignTaskToMember.execute(parameters));
    }

    @Test
    void addTaskToMember_Should_ThrowAnException_When_NoSuchIdIsFound(){
        parameters.add(VALID_ID);
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        repository.createMember(TestsConstants.VALID_MEMBER_NAME);
        AssignTaskToMember assignTaskToMember = new AssignTaskToMember(repository);
        Assertions.assertThrows(IllegalArgumentException.class, () -> assignTaskToMember.execute(parameters));
    }

    @Test
    void addTaskToMember_Should_ThrowAnException_When_InvalidParametersAreAdded(){
        parameters.add(INVALID_ID);
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        repository.createMember(TestsConstants.VALID_MEMBER_NAME);
        AssignTaskToMember assignTaskToMember = new AssignTaskToMember(repository);
        Assertions.assertThrows(IllegalArgumentException.class, () -> assignTaskToMember.execute(parameters));
    }

}
