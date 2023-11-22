package com.company.oop.taskmanagmentsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.BugImpl;
import com.company.oop.taskmanagementsystem.models.CommentImpl;
import com.company.oop.taskmanagementsystem.models.MemberImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.contracts.Comment;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BugImplTests {
    private BugImpl bug;

    @BeforeEach
    public void initBugImpl(){
        bug = new BugImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.STEPS_TO_REPRODUCE, TestsConstants.VALID_PRIORITY,
                TestsConstants.VALID_SEVERITY);
    }

    @Test
    public void constructor_Should_Initialize_All_Fields_When_ValidArguments() {
        Bug bug = new BugImpl(
                TestsConstants.VALID_ID,
                TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.VALID_PRIORITY,
                TestsConstants.VALID_SEVERITY);

        Assertions.assertEquals(1, bug.getId());
        Assertions.assertEquals("Example of a valid title", bug.getTitle());
        Assertions.assertEquals("Example of a valid description", bug.getDescription());
        Assertions.assertEquals(Status.ACTIVE, bug.getStatus());
        Assertions.assertEquals(TestsConstants.STEPS_TO_REPRODUCE, bug.getStepsToReproduce());
        Assertions.assertEquals(Priority.LOW, bug.getPriority());
        Assertions.assertEquals(Severity.CRITICAL, bug.getSeverity());
        Assertions.assertEquals("NO ASSIGNEE", bug.getAssignee().getName());
    }

    @Test
    public void constructor_Should_ThrownException_When_InValidTitle() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
           new BugImpl(
                TestsConstants.VALID_ID,
                TestsConstants.INVALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                   TestsConstants.STEPS_TO_REPRODUCE,
                   TestsConstants.VALID_PRIORITY,
                   TestsConstants.VALID_SEVERITY));
    }
    @Test
    public void constructor_Should_ThrownException_When_InValidDescription() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BugImpl(
                        TestsConstants.VALID_ID,
                        TestsConstants.VALID_TITLE,
                        TestsConstants.INVALID_DESCRIPTION,
                        TestsConstants.STEPS_TO_REPRODUCE,
                        TestsConstants.VALID_PRIORITY,
                        TestsConstants.VALID_SEVERITY));
    }
    @Test
    public void constructor_Should_ThrownException_When_StepsToReproduceAreEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BugImpl(
                        TestsConstants.VALID_ID,
                        TestsConstants.VALID_TITLE,
                        TestsConstants.INVALID_DESCRIPTION,
                        TestsConstants.EMPTY_STEPS_TO_REPRODUCE,
                        TestsConstants.VALID_PRIORITY,
                        TestsConstants.VALID_SEVERITY));
    }

    @Test
    public void constructor_Should_ThrownException_When_StepsToReproduceAreNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BugImpl(
                        TestsConstants.VALID_ID,
                        TestsConstants.VALID_TITLE,
                        TestsConstants.INVALID_DESCRIPTION,
                        TestsConstants.NULL_STEPS_TO_REPRODUCE,
                        TestsConstants.VALID_PRIORITY,
                        TestsConstants.VALID_SEVERITY));
    }

    @Test
    public void setAssignee_Should_SetNewAssignee_When_AssigneeIsNobody() {
        BugImpl bug = new BugImpl(
                TestsConstants.VALID_ID,
                TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.VALID_PRIORITY,
                TestsConstants.VALID_SEVERITY);

        MemberImpl petar = new MemberImpl("Petar Petrov");
        bug.setAssignee(petar);

        Assertions.assertEquals(petar.getName(), bug.getAssignee().getName());
    }

    @Test
    public void getStepsToReproduce_Should_ReturnCopyOfList() {
        BugImpl bug = new BugImpl(
                TestsConstants.VALID_ID,
                TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.VALID_PRIORITY,
                TestsConstants.VALID_SEVERITY);

        List<String> stepsToReproduce = bug.getStepsToReproduce();

        bug.getStepsToReproduce().add("Step 3");

        Assertions.assertFalse(stepsToReproduce.contains("Step 3"));
    }

    @Test
    public void setPriority_Should_SetPriorityCorrectly() {
        BugImpl bug = new BugImpl(
                TestsConstants.VALID_ID,
                TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.VALID_PRIORITY,
                TestsConstants.VALID_SEVERITY);

        bug.setPriority(Priority.MEDIUM);

        Assertions.assertEquals(Priority.MEDIUM, bug.getPriority());
    }

    @Test
    public void setSeverity_Should_SetSeverityCorrectly() {

        BugImpl bug = new BugImpl(
                TestsConstants.VALID_ID,
                TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.VALID_PRIORITY,
                TestsConstants.VALID_SEVERITY);

         bug.setSeverity(Severity.MAJOR);

         Assertions.assertEquals(Severity.MAJOR, bug.getSeverity());
    }

    @Test
    public void addComment_Should_AddCommentToList() {
        BugImpl bug = new BugImpl(
                TestsConstants.VALID_ID,
                TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.VALID_PRIORITY,
                TestsConstants.VALID_SEVERITY);

        Comment comment = new CommentImpl("User1", "This is a test comment.");
        bug.addComment(comment);
        List<Comment> comments = bug.getComments();

        Assertions.assertTrue(comments.contains(comment));
    }
    @Test
    public void advanceStatus_Should_AdvanceStatus_WhenValid(){
        bug.advanceStatus();
        Assertions.assertEquals(Status.DONE, bug.getStatus());
    }

    @Test
    public void revertStatus_Should_RevertStatus_WhenValid(){
        bug.advanceStatus();
        bug.revertStatus();
        Assertions.assertEquals(Status.ACTIVE, bug.getStatus());
    }
    @Test
    public void advanceStatus_Should_GiveAnErrorMessage_When_StatusCannotAdvanceFurther(){
        bug.advanceStatus();
        bug.advanceStatus();
        Assertions.assertEquals(String.format(Constants.STATUS_IS_ALREADY_SET_TO_DONE, Constants.BUG),
                bug.getHistoryOfChanges().get(2).getDescription());
    }
    @Test
    public void revertStatus_Should_GiveAnErrorMessage_When_StatusCannotRevertFurther(){
        bug.revertStatus();
        Assertions.assertEquals(String.format(Constants.CANNOT_REVERT_STATUS, Constants.BUG, Status.ACTIVE),
                bug.getHistoryOfChanges().get(1).getDescription());
    }
    @Test
    public void increasePriority_Should_IncreasePriority_When_PriorityIsValid(){
        bug.increasePriority();
        Assertions.assertEquals(Priority.MEDIUM, bug.getPriority());
    }
    @Test
    public void lowerPriority_Should_LowerPriority_When_PriorityIsValid(){
        bug.increasePriority();
        bug.lowerPriority();
        Assertions.assertEquals(Priority.LOW, bug.getPriority());
    }
    @Test
    public void increasePriority_Should_GiveAnErrorMessage_When_PriorityCannotIncreaseFurther(){
        bug.increasePriority();
        bug.increasePriority();
        bug.increasePriority();
        Assertions.assertEquals(String.format(Constants.PRIORITY_IS_ALREADY_SET_TO_HIGH, Constants.BUG),
                bug.getHistoryOfChanges().get(3).getDescription());
    }
    @Test
    public void lowerPriority_Should_GiveAnErrorMessage_When_PriorityCannotLowerFurther(){
        bug.lowerPriority();
        Assertions.assertEquals(String.format(Constants.PRIORITY_IS_ALREADY_SET_TO_LOW, Constants.BUG),
                bug.getHistoryOfChanges().get(1).getDescription());
    }

}
