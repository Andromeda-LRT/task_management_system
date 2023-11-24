package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.add.AddCommentToTask;
import com.company.oop.taskmanagementsystem.commands.create.CreateNewMember;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddCommentToTaskTests {
    private static final String COMMENT_ADDED_TO_TASK = "Comment was added successfully to task with id %d.";
    private TaskManagementSystemRepository repository;
    private List<String> parameters;
    private Bug bug;
    private AddCommentToTask addCommentToTask;

    @BeforeEach
    public void setUp() {
        repository = new TaskManagementSystemRepositoryImpl();
        addCommentToTask = new AddCommentToTask(repository);
        bug = repository.createBug(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.VALID_PRIORITY,
                TestsConstants.VALID_SEVERITY);
        parameters = new ArrayList<>();
    }

    @Test
    void addCommentToTask_Should_AddACommentToTheTask_When_AllParametersAreValid(){
        CreateNewMember createNewMember = new CreateNewMember(repository);
        List<String> createMemberParameters = new ArrayList<>();
        createMemberParameters.add(TestsConstants.VALID_MEMBER_NAME);
        createNewMember.execute(createMemberParameters  );
        parameters.add(TestsConstants.VALID_DESCRIPTION);
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        parameters.add(String.valueOf(bug.getId()));
        String output = addCommentToTask.execute(parameters);
        Assertions.assertEquals(String.format(COMMENT_ADDED_TO_TASK, bug.getId()), output);
    }

    @Test
    void addCommentToTask_Should_ThrowAnException_When_TheAuthorIsNotAMember(){
        parameters.add(TestsConstants.VALID_DESCRIPTION);
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        parameters.add(String.valueOf(bug.getId()));
        Assertions.assertThrows(IllegalArgumentException.class, ()->addCommentToTask.execute(parameters));
    }
}
