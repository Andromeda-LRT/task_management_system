package com.company.oop.taskmanagmentsystem.models;

import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.FeedbackImpl;
import com.company.oop.taskmanagementsystem.models.MemberImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemberImplTests {

    private MemberImpl member;
    private Bug bug;
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void setUp() {
        member = new MemberImpl(TestsConstants.VALID_MEMBER_NAME);
        repository = new TaskManagementSystemRepositoryImpl();
        bug = repository.createBug(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.VALID_PRIORITY,
                TestsConstants.VALID_SEVERITY);
    }

    @Test
    public void memberImpl_Should_ImplementMemberInterface() {
        Assertions.assertTrue(member instanceof Member);
    }

    @Test
    public void constructor_Should_CreateNewMember_When_ParametersAreCorrect() {
        Assertions.assertEquals(TestsConstants.VALID_MEMBER_NAME, member.getName());
    }

    @Test
    public void constructor_Should_ThrowAnException_When_NameIsOutOfBounds() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new MemberImpl(TestsConstants.INVALID_MEMBER_NAME));
    }

    @Test
    public void addTask_Should_AddATaskToTheCollection() {
        member.assignTask(bug);
        Assertions.assertEquals(1, member.getListOfTasks().size());
    }

    @Test
    public void removeTask_Should_RemoveATaskFromTheCollection() {
        member.assignTask(bug);
        member.unAssignTask(bug);
        Assertions.assertEquals(0, member.getListOfTasks().size());
    }

    @Test
    public void getListOfTasks_Should_ReturnCopyOfList() {

        List<Task> listOfTasks = member.getListOfTasks();

        member.assignTask(bug);

        Assertions.assertFalse(listOfTasks.contains(bug));
    }
}
