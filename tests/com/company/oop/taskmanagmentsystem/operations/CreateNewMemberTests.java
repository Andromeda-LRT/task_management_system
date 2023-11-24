package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.create.CreateNewMember;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateNewMemberTests {
    private List<String> parameters;
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void setUp() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
    }

    @Test
    void createMember_Should_createANewMember_When_ValidParametersAreProvided() {
        List<String> parameters = new ArrayList<>();
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        CreateNewMember newMember = new CreateNewMember(repository);
        String output = newMember.execute(parameters);
        Assertions.assertTrue(repository.memberExist(TestsConstants.VALID_MEMBER_NAME));
        Assertions.assertEquals(String.format("Member %s registered successfully!", TestsConstants.VALID_MEMBER_NAME), output);
    }
}
