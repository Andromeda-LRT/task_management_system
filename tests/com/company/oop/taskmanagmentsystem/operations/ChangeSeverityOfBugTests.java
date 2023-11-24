package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.change.ChangeSeverityOfBug;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChangeSeverityOfBugTests {
    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    private ChangeSeverityOfBug changeSeverityOfBug;
    private Bug bug;

    @BeforeEach
    public void setUp() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        changeSeverityOfBug = new ChangeSeverityOfBug(repository);
        bug = repository.createBug(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.VALID_PRIORITY,
                TestsConstants.VALID_SEVERITY);
    }

    @Test
    void changeSeverityOfBug_Should_IncreaseBugSeverity_When_ParametersAreValid() {
        bug.lowerSeverity();
        bug.lowerSeverity();
        parameters.add("increase");
        parameters.add(String.valueOf(bug.getId()));
        changeSeverityOfBug.execute(parameters);
        Assertions.assertEquals(Severity.MAJOR, bug.getSeverity());

    }

    @Test
    void changeSeverityOfBug_Should_LowerBugSeverity_When_ParametersAreValid() {
        bug.lowerSeverity();
        parameters.add("lower");
        parameters.add(String.valueOf(bug.getId()));
        changeSeverityOfBug.execute(parameters);
        Assertions.assertEquals(Severity.MINOR, bug.getSeverity());

    }

    @Test
    void changeSeverityOfBug_Should_ThrowAnException_When_IdIsNotValid() {
        parameters.add("Lower");
        parameters.add("five");
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeSeverityOfBug.execute(parameters));
    }

    @Test
    void changeSeverityOfBug_Should_ThrowAnException_When_PriorityIsNotNotValid() {
        parameters.add("Lowers");
        parameters.add(String.valueOf(bug.getId()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeSeverityOfBug.execute(parameters));
    }
}
