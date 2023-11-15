package com.company.oop.taskmanagmentsystem.models;

import com.company.oop.taskmanagementsystem.models.FeedbackImpl;
import com.company.oop.taskmanagementsystem.models.MemberImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemberImplTests {

    MemberImpl member;

    @BeforeEach
    public void setUp() {
        member = new MemberImpl(TestsConstants.VALID_MEMBER_NAME);
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
        member.assignTask(new FeedbackImpl(TestsConstants.VALID_ID,
                TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.VALID_RATING));
        Assertions.assertEquals(1, member.getListOfTasks().size());
    }

    @Test
    public void removeTask_Should_RemoveATaskFromTheCollection() {
        FeedbackImpl feedback = new FeedbackImpl(TestsConstants.VALID_ID,
                TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.VALID_RATING);
        member.assignTask(feedback);
        member.unAssignTask(feedback);
        Assertions.assertEquals(0, member.getListOfTasks().size());
    }

    @Test
    public void getListOfTasks_Should_ReturnCopyOfList() {
        FeedbackImpl feedback = new FeedbackImpl(TestsConstants.VALID_ID,
                TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.VALID_RATING);

        List<Task> listOfTasks = member.getListOfTasks();

        member.assignTask(feedback);

        Assertions.assertFalse(listOfTasks.contains(feedback));
    }
}
