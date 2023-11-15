package com.company.oop.taskmanagmentsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.BugImpl;
import com.company.oop.taskmanagementsystem.models.CommentImpl;
import com.company.oop.taskmanagementsystem.models.FeedbackImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Comment;
import com.company.oop.taskmanagementsystem.models.contracts.Feedback;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FeedbackImplTests {
    FeedbackImpl feedback;

    @BeforeEach
    public void setUp() {
        feedback = new FeedbackImpl(TestsConstants.VALID_ID,
                TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.VALID_RATING);
    }

    @Test
    public void feedbackImpl_Should_ImplementFeedbackInterface() {
        Assertions.assertTrue(feedback instanceof Feedback);
    }

    @Test
    public void feedbackImpl_Should_ImplementTaskInterface() {
        Assertions.assertTrue(feedback instanceof Task);
    }

    @Test
    public void constructor_Should_ThrowAnException_When_TitleLengthIsOutOfBounds() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new FeedbackImpl(TestsConstants.VALID_ID,
                        TestsConstants.INVALID_TITLE,
                        TestsConstants.VALID_DESCRIPTION,
                        TestsConstants.VALID_RATING));
    }

    @Test
    public void constructor_Should_ThrowAnException_When_DescriptionLengthIsOutOfBounds() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new FeedbackImpl(TestsConstants.VALID_ID,
                        TestsConstants.VALID_TITLE,
                        TestsConstants.INVALID_DESCRIPTION,
                        TestsConstants.VALID_RATING));
    }

    @Test
    public void constructor_Should_CreateNewFeedback_When_ParametersAreCorrect() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(TestsConstants.VALID_TITLE, feedback.getTitle()),
                () -> Assertions.assertEquals(TestsConstants.VALID_DESCRIPTION, feedback.getDescription()),
                () -> Assertions.assertEquals(Status.NEW, feedback.getStatus()),
                () -> Assertions.assertEquals(TestsConstants.VALID_RATING, feedback.getRating()));
    }

    @Test
    public void addComment_Should_AddCommentToList() {

        Comment comment = new CommentImpl("User1", "This is a test comment.");
        feedback.addComment(comment);
        List<Comment> comments = feedback.getComments();

        Assertions.assertTrue(comments.contains(comment));
    }

    @Test
    public void advanceStatus_Should_AdvanceStatus_WhenValid() {
        feedback.advanceStatus();
        Assertions.assertEquals(Status.UNSCHEDULED, feedback.getStatus());
    }

    @Test
    public void revertStatus_Should_RevertStatus_WhenValid() {
        feedback.advanceStatus();
        feedback.revertStatus();
        Assertions.assertEquals(Status.NEW, feedback.getStatus());
    }

    @Test
    public void advanceStatus_Should_GiveAnErrorMessage_When_StatusCannotAdvanceFurther() {
        feedback.advanceStatus();
        feedback.advanceStatus();
        feedback.advanceStatus();
        feedback.advanceStatus();
        Assertions.assertEquals(String.format(Constants.STATUS_IS_ALREADY_SET_TO_DONE, Constants.FEEDBACK),
                feedback.getHistoryOfChanges().get(4).getDescription());
    }

    @Test
    public void revertStatus_Should_GiveAnErrorMessage_When_StatusCannotRevertFurther() {
        feedback.revertStatus();
        Assertions.assertEquals(String.format(Constants.STATUS_IS_ALREADY_SET_TO_NEW, Constants.FEEDBACK),
                feedback.getHistoryOfChanges().get(1).getDescription());
        //TODO add tests regarding history
    }
}
