package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.filter.FilterTasksByStatusAndAssignee;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FilterTasksByStatusAndAssigneeTests {
    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    FilterTasksByStatusAndAssignee filterTasksByStatusAndAssignee;

    @BeforeEach
    public void setUp() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        filterTasksByStatusAndAssignee = new FilterTasksByStatusAndAssignee(repository);
    }

    @Test
    void FilterTasksByStatusAndAssignee_Should_ThrowAnException_When_ThereAreIsNoSuchParameters(){
        parameters.add("Done");
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->filterTasksByStatusAndAssignee.execute(parameters));
    }

    @Test
    void FilterTasksByStatusAndAssignee_Should_ThrowAnException_When_NumberOfParametersAreInvalid(){
        parameters.add("Done");
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->filterTasksByStatusAndAssignee.execute(parameters));
    }
}
