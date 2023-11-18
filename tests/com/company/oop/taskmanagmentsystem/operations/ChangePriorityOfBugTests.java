package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.ChangePriorityOfBug;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChangePriorityOfBugTests {
    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    private ChangePriorityOfBug changePriorityOfBug;
    private Bug bug;

    @BeforeEach
    public void setUp() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        changePriorityOfBug = new ChangePriorityOfBug(repository);
        bug = repository.createBug(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.VALID_PRIORITY,
                TestsConstants.VALID_SEVERITY);
    }

    @Test
    void changePriorityOfBug_Should_IncreaseBugPriority_When_ParametersAreValid() {
        parameters.add("increase");
        parameters.add(String.valueOf(bug.getId()));
        changePriorityOfBug.execute(parameters);
        Assertions.assertEquals(Priority.MEDIUM, bug.getPriority());

    }

    @Test
    void changePriorityOfBug_Should_LowerBugPriority_When_ParametersAreValid() {
        bug.increasePriority();
        parameters.add("Lower");
        parameters.add(String.valueOf(bug.getId()));
        changePriorityOfBug.execute(parameters);
        Assertions.assertEquals(Priority.LOW, bug.getPriority());
    }

    @Test
    void changePriorityOfBug_Should_ThrowAnException_When_IdIsNotValid() {
        parameters.add("Lower");
        parameters.add("five");
        Assertions.assertThrows(IllegalArgumentException.class, () -> changePriorityOfBug.execute(parameters));
    }

    @Test
    void changePriorityOfBug_Should_ThrowAnException_When_PriorityIsNotNotValid() {
        parameters.add("Lowers");
        parameters.add(String.valueOf(bug.getId()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> changePriorityOfBug.execute(parameters));
    }
}
