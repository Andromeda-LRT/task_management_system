package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.filter.FilterTasksByStatus;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FilterTasksByStatusTests {
    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    FilterTasksByStatus filterTasksByStatus;

    @BeforeEach
    public void setUp() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        filterTasksByStatus = new FilterTasksByStatus(repository);
    }

    @Test
    void FilterTasksByStatus_Should_ThrowAnException_When_ThereAreIsNoSuchStatus() {
        parameters.add("Done");
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterTasksByStatus.execute(parameters));
    }

    @Test
    void FilterTasksByStatus_Should_ThrowAnException_When_NumberOfParametersAreInvalid() {
        parameters.add("Done");
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterTasksByStatus.execute(parameters));
    }
}
