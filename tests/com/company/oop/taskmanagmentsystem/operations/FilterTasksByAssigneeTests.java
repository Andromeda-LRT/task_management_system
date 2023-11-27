package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.filter.FilterTasksByAssignee;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FilterTasksByAssigneeTests {
    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    FilterTasksByAssignee filterTasksByAssignee;

    @BeforeEach
    public void setUp() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        filterTasksByAssignee = new FilterTasksByAssignee(repository);
    }

    @Test
    void FilterTasksByAssignee_Should_ThrowAnException_When_ThereAreIsNoSuchAssignee(){
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->filterTasksByAssignee.execute(parameters));
    }

    @Test
    void FilterTasksByAssignee_Should_ThrowAnException_When_NumberOfParametersAreInvalid(){
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->filterTasksByAssignee.execute(parameters));
    }
}
