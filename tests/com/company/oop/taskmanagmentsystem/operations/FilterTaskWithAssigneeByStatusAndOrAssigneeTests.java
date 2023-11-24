package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.assignAndUnassign.AssignTaskToMember;
import com.company.oop.taskmanagementsystem.commands.filter.FilterTaskWithAssigneeByStatusAndOrAssignee;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.models.enums.Size;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FilterTaskWithAssigneeByStatusAndOrAssigneeTests {
    private static final String EXPECTED_RESULT_STATUS = "Tasks with status Done:%nID:1 'Example of a valid title', [Done | Example of a valid description][Low PRIORITY][Critical SEVERITY] assigned to Ivancho";
    private static final String EXPECTED_RESULT_STATUS_AND_ASSIGNEE = "Tasks with status Done and assignee Ivancho:%nID:1 'Example of a valid title', [Done | Example of a valid description][Low PRIORITY][Critical SEVERITY] assigned to Ivancho";
    private static final String EXPECTED_RESULT_ASSIGNEE = "Tasks with assignee Ivancho:%nID:1 'Example of a valid title', [Done | Example of a valid description][Low PRIORITY][Critical SEVERITY] assigned to Ivancho";

    private List<String> parameters;
    private TaskManagementSystemRepository repository;
    FilterTaskWithAssigneeByStatusAndOrAssignee filterTaskWithAssigneeByStatusAndOrAssignee;
    private Bug bug;
    private Story story;

    @BeforeEach
    public void setUp() {
        parameters = new ArrayList<>();
        repository = new TaskManagementSystemRepositoryImpl();
        filterTaskWithAssigneeByStatusAndOrAssignee = new FilterTaskWithAssigneeByStatusAndOrAssignee(repository);
        bug = repository.createBug(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.VALID_PRIORITY,
                TestsConstants.VALID_SEVERITY);
        story = repository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.VALID_PRIORITY,
                Size.SMALL);
    }
    @Test
    void FilterTaskWithAssigneeByStatusOrAndAssignee_Should_ListAllTasksWithThatStatus_When_ParametersAreValid() {
        repository.createMember(TestsConstants.VALID_MEMBER_NAME);
        bug.advanceStatus();
        story.advanceStatus();
        story.advanceStatus();

        AssignTaskToMember assignTaskToMember = new AssignTaskToMember(repository);
        List<String> assignTaskParameters = new ArrayList<>();
        assignTaskParameters.add(String.valueOf(bug.getId()));
        assignTaskParameters.add(TestsConstants.VALID_MEMBER_NAME);
        assignTaskToMember.execute(assignTaskParameters);

        parameters.add("Done");
        String output = filterTaskWithAssigneeByStatusAndOrAssignee.execute(parameters);
        Assertions.assertEquals(String.format(EXPECTED_RESULT_STATUS), output);
    }

    @Test
    void FilterTaskWithAssigneeByStatusOrAndAssignee_Should_ListAllTasksWithThatStatusAndAssignee_When_ParametersAreValid() {
        repository.createMember(TestsConstants.VALID_MEMBER_NAME);
        bug.advanceStatus();
        story.advanceStatus();
        story.advanceStatus();

        AssignTaskToMember assignTaskToMember = new AssignTaskToMember(repository);
        List<String> assignTaskParameters = new ArrayList<>();
        assignTaskParameters.add(String.valueOf(bug.getId()));
        assignTaskParameters.add(TestsConstants.VALID_MEMBER_NAME);
        assignTaskToMember.execute(assignTaskParameters);

        parameters.add("Done");
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        String output = filterTaskWithAssigneeByStatusAndOrAssignee.execute(parameters);
        Assertions.assertEquals(String.format(EXPECTED_RESULT_STATUS_AND_ASSIGNEE), output);
    }

    @Test
    void FilterTaskWithAssigneeByStatusOrAndAssignee_Should_ListAllTasksWithThatAssignee_When_ParametersAreValid() {
        repository.createMember(TestsConstants.VALID_MEMBER_NAME);
        bug.advanceStatus();

        AssignTaskToMember assignTaskToMember = new AssignTaskToMember(repository);
        List<String> assignTaskParameters = new ArrayList<>();
        assignTaskParameters.add(String.valueOf(bug.getId()));
        assignTaskParameters.add(TestsConstants.VALID_MEMBER_NAME);
        assignTaskToMember.execute(assignTaskParameters);

        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        String output = filterTaskWithAssigneeByStatusAndOrAssignee.execute(parameters);
        Assertions.assertEquals(String.format(EXPECTED_RESULT_ASSIGNEE), output);
    }
}
