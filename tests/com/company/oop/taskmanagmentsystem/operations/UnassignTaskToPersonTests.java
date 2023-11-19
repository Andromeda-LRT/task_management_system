package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.AssignTaskToMember;
import com.company.oop.taskmanagementsystem.commands.UnassignTaskToMember;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.MemberImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class UnassignTaskToPersonTests {
    private static final String VALID_ID = "1";
    private TaskManagementSystemRepository repository;
    private UnassignTaskToMember unassignTaskToMember;
    private List<String> parameters;
    private Bug bug;
    @BeforeEach
    public void setUp() {
        repository = new TaskManagementSystemRepositoryImpl();
        unassignTaskToMember = new UnassignTaskToMember(repository);
        bug = repository.createBug(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.VALID_PRIORITY,
                TestsConstants.VALID_SEVERITY);
        parameters = new ArrayList<>();
    }

    @Test
    void unassignTaskToMember_Should_ThrowAnException_When_NoSuchIdIsFound(){
        parameters.add(VALID_ID);
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        repository.createMember(TestsConstants.VALID_MEMBER_NAME);
        Assertions.assertThrows(IllegalArgumentException.class, () -> unassignTaskToMember.execute(parameters));
    }

    @Test
    void unassignTaskToMember_Should_AddNewTaskToMember_When_ValidParametersAreProvided(){
        repository.createMember(TestsConstants.VALID_MEMBER_NAME);
        parameters.add(VALID_ID);
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        AssignTaskToMember assignTaskToMember = new AssignTaskToMember(repository);
        assignTaskToMember.execute(parameters);
        unassignTaskToMember.execute(parameters);
        Assertions.assertFalse(repository.findMemberByName(TestsConstants.VALID_MEMBER_NAME).getListOfTasks().contains(bug));
    }

    @Test
    void unassignTaskToMember_Should_ThrowAnException_When_NoSuchMemberIsFound(){
        parameters.add(String.valueOf(bug.getId()));
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        repository.createMember(TestsConstants.VALID_MEMBER_NAME);
        AssignTaskToMember assignTaskToMember = new AssignTaskToMember(repository);
        assignTaskToMember.execute(parameters);
        parameters.remove(1);
        parameters.add("Maria");
        Assertions.assertThrows(IllegalArgumentException.class, () -> unassignTaskToMember.execute(parameters));
    }
}
