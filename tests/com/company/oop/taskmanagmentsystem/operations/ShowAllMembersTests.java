package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.show.ShowAllMembers;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowAllMembersTests {
    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    private static final String EXPECTED_RESULT = "Members%n---------------------%n%s%n---------------------";

    @BeforeEach
    public void setUp() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
    }

    @Test
    void showAllMembers_Should_ReturnAllMembers_When_ThereAreAddedMembers() {
        ShowAllMembers showAllMembers = new ShowAllMembers(repository);
        repository.createMember(TestsConstants.VALID_MEMBER_NAME);
        String output = showAllMembers.execute(parameters);
        Assertions.assertEquals(String.format(EXPECTED_RESULT, TestsConstants.VALID_MEMBER_NAME), output);
    }

    @Test
    void showAllMembers_ThrowAnException_When_ThereAreNoAddedMembers() {
        ShowAllMembers showAllMembers = new ShowAllMembers(repository);
        Assertions.assertThrows(IllegalArgumentException.class, () -> showAllMembers.execute(parameters));
    }

}
