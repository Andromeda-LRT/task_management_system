package com.company.oop.taskmanagmentsystem.models;

import com.company.oop.taskmanagementsystem.models.FeedbackImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Feedback;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    //TODO add tests regarding comments and history
}
