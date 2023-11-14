package com.company.oop.taskmanagmentsystem.models;

import com.company.oop.taskmanagementsystem.models.BugImpl;
import com.company.oop.taskmanagementsystem.models.CommentImpl;
import com.company.oop.taskmanagementsystem.models.MemberImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.contracts.Comment;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BugImplTests {
    private static final List<String> STEPS_TO_REPRODUCE = Arrays.asList("Step 1", "Step 2");
    private static final List<String> EMPTY_STEPS_TO_REPRODUCE = new ArrayList<>();
    private static final List<String> NULL_STEPS_TO_REPRODUCE = null;

    private static final Priority VALID_PRIORITY = Priority.LOW;
    private static final Severity VALID_SEVERITY = Severity.CRITICAL;


    @Test
    public void constructor_Should_Initialize_All_Fields_When_ValidArguments() {
        Bug bug = new BugImpl(
                TestsConstants.VALID_ID,
                TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                STEPS_TO_REPRODUCE,
                VALID_PRIORITY,
                VALID_SEVERITY);

        Assertions.assertEquals(1, bug.getId());
        Assertions.assertEquals("Example of a valid title", bug.getTitle());
        Assertions.assertEquals("Example of a valid description", bug.getDescription());
        Assertions.assertEquals(Status.ACTIVE, bug.getStatus());
        Assertions.assertEquals(STEPS_TO_REPRODUCE, bug.getStepsToReproduce());
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
                STEPS_TO_REPRODUCE,
                VALID_PRIORITY,
                VALID_SEVERITY));
    }
    @Test
    public void constructor_Should_ThrownException_When_InValidDescription() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BugImpl(
                        TestsConstants.VALID_ID,
                        TestsConstants.VALID_TITLE,
                        TestsConstants.INVALID_DESCRIPTION,
                        STEPS_TO_REPRODUCE,
                        VALID_PRIORITY,
                        VALID_SEVERITY));
    }
    @Test
    public void constructor_Should_ThrownException_When_StepsToReproduceAreEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BugImpl(
                        TestsConstants.VALID_ID,
                        TestsConstants.VALID_TITLE,
                        TestsConstants.INVALID_DESCRIPTION,
                        EMPTY_STEPS_TO_REPRODUCE,
                        VALID_PRIORITY,
                        VALID_SEVERITY));
    }

    @Test
    public void constructor_Should_ThrownException_When_StepsToReproduceAreNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BugImpl(
                        TestsConstants.VALID_ID,
                        TestsConstants.VALID_TITLE,
                        TestsConstants.INVALID_DESCRIPTION,
                        NULL_STEPS_TO_REPRODUCE,
                        VALID_PRIORITY,
                        VALID_SEVERITY));
    }

    @Test
    public void setAssignee_Should_SetNewAssignee_When_AssigneeIsNobody() {
        BugImpl bug = new BugImpl(
                TestsConstants.VALID_ID,
                TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                STEPS_TO_REPRODUCE,
                VALID_PRIORITY,
                VALID_SEVERITY);

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
                STEPS_TO_REPRODUCE,
                VALID_PRIORITY,
                VALID_SEVERITY);

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
                STEPS_TO_REPRODUCE,
                VALID_PRIORITY,
                VALID_SEVERITY);

        bug.setPriority(Priority.MEDIUM);

        Assertions.assertEquals(Priority.MEDIUM, bug.getPriority());
    }

    @Test
    public void setSeverity_Should_SetSeverityCorrectly() {

        BugImpl bug = new BugImpl(
                TestsConstants.VALID_ID,
                TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                STEPS_TO_REPRODUCE,
                VALID_PRIORITY,
                VALID_SEVERITY);

         bug.setSeverity(Severity.MAJOR);

         Assertions.assertEquals(Severity.MAJOR, bug.getSeverity());
    }

    @Test
    public void addComment_Should_AddCommentToList() {
        BugImpl bug = new BugImpl(
                TestsConstants.VALID_ID,
                TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                STEPS_TO_REPRODUCE,
                VALID_PRIORITY,
                VALID_SEVERITY);

        Comment comment = new CommentImpl("User1", "This is a test comment.");
        bug.addComment(comment);
        List<Comment> comments = bug.getComments();

        Assertions.assertTrue(comments.contains(comment));
    }
}
