package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.change.ChangeStatusOfBug;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ChangeStatusOfBugTests {
    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    private ChangeStatusOfBug changeStatusOfBug;
    private Bug bug;

    @BeforeEach
    public void setUp() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        changeStatusOfBug = new ChangeStatusOfBug(repository);
        bug = repository.createBug(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.VALID_PRIORITY,
                TestsConstants.VALID_SEVERITY);
    }

    @Test
    void changeStatusOfBug_Should_AdvanceBugStatus_When_ParametersAreValid() {
        parameters.add("advance");
        parameters.add(String.valueOf(bug.getId()));
        changeStatusOfBug.execute(parameters);
        Assertions.assertEquals(Status.DONE, bug.getStatus());
    }

    @Test
    void changeStatusOfBug_Should_RevertBugStatus_When_ParametersAreValid() {
        bug.advanceStatus();
        parameters.add("revert");
        parameters.add(String.valueOf(bug.getId()));
        changeStatusOfBug.execute(parameters);
        Assertions.assertEquals(Status.ACTIVE, bug.getStatus());
    }

    @Test
    void changeStatusOfBug_Should_ThrowAnException_When_IdIsNotValid() {
        parameters.add("Lower");
        parameters.add("five");
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeStatusOfBug.execute(parameters));
    }

    @Test
    void changeStatusOfBug_Should_ThrowAnException_When_StatusIsNotNotValid() {
        parameters.add("Lowers");
        parameters.add(String.valueOf(bug.getId()));
        Assertions.assertThrows(IllegalArgumentException.class, () -> changeStatusOfBug.execute(parameters));
    }

}
