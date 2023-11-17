package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.ShowMemberActivity;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowMemberActivityTests {
    private static final String EXPECTED_RESULT = "%s ACTIVITY%n---------------------%nA member with the name %s was created%nTask Example of a valid title was assigned to %s%n---------------------";
    private List<String> parameters;
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void setUp() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
    }

    @Test
    void ShowMemberActivity_ShouldReturnAllActivityOfThatMember() {
        ShowMemberActivity showMemberActivity = new ShowMemberActivity(repository);
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        Bug bug = repository.createBug(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.VALID_PRIORITY,
                TestsConstants.VALID_SEVERITY);
        Member member = repository.createMember(TestsConstants.VALID_MEMBER_NAME);
        member.assignTask(bug);
        Assertions.assertEquals(String.format(EXPECTED_RESULT, TestsConstants.VALID_MEMBER_NAME,
                TestsConstants.VALID_MEMBER_NAME, TestsConstants.VALID_MEMBER_NAME),
                showMemberActivity.execute(parameters));

    }
}
