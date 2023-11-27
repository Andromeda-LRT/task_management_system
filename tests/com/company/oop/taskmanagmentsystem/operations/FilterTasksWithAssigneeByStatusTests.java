package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.filter.FilterTasksWithAssigneeByStatus;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FilterTasksWithAssigneeByStatusTests {
    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    FilterTasksWithAssigneeByStatus filterTasksWithAssigneeByStatus;

    @BeforeEach
    public void setUp() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        filterTasksWithAssigneeByStatus = new FilterTasksWithAssigneeByStatus(repository);
    }

    @Test
    void FilterTasksWithAssigneeByStatus_Should_ThrowAnException_When_ThereAreIsNoSuchParameters(){
        parameters.add("Done");
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->filterTasksWithAssigneeByStatus.execute(parameters));
    }

    @Test
    void FilterTasksWithAssigneeByStatus_Should_ThrowAnException_When_NumberOfParametersAreInvalid(){
        parameters.add("Done");
        parameters.add("Done");
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->filterTasksWithAssigneeByStatus.execute(parameters));
    }
}
