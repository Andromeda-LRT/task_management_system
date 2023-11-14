package com.company.oop.taskmanagmentsystem.models;

import com.company.oop.taskmanagementsystem.models.StoryImpl;
import com.company.oop.taskmanagementsystem.models.TaskImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StoryImplTests {
    // TODO The tests should be implemented

    private StoryImpl story;
    @BeforeEach
    public void initStory(){
        story = new StoryImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.TEST_PRIORITY,
                TestsConstants.TEST_SIZE);
    }

    @Test
    public void storyImpl_Should_Implement_StoryInterface(){
        Assertions.assertTrue(story instanceof Story);
    }
    @Test
    public void storyImpl_Should_Implement_TaskImplInterface(){
        Assertions.assertTrue(story instanceof Task);
    }
    @Test
    public void constructor_Should_InitializeTitle_When_ArgumentsAreValid(){
        Assertions.assertEquals(TestsConstants.VALID_TITLE, story.getTitle());
    }
    @Test
    public void constructor_Should_InitializeDescription_When_ArgumentsAreValid(){
        Assertions.assertEquals(TestsConstants.VALID_DESCRIPTION, story.getDescription());
    }
    @Test
    public void constructor_Should_InitializePriority_When_ArgumentsAreValid(){
        Assertions.assertEquals(TestsConstants.TEST_PRIORITY, story.getPriority());
    }
    @Test
    public void constructor_Should_InitializeSize_When_ArgumentsAreValid(){
        Assertions.assertEquals(TestsConstants.TEST_SIZE, story.getSize());
    }
    @Test
    public void constructor_Should_ThrownAnException_When_TitleLengthIsOutOfBounds(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new StoryImpl(TestsConstants.VALID_ID,
                        TestsConstants.INVALID_TITLE,
                        TestsConstants.VALID_DESCRIPTION,
                        TestsConstants.TEST_PRIORITY,
                        TestsConstants.TEST_SIZE));
    }
    @Test
    public void constructor_Should_ThrownAnException_When_DescriptionLengthIsOutOfBounds(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new StoryImpl(TestsConstants.VALID_ID,
                        TestsConstants.VALID_TITLE,
                        TestsConstants.INVALID_DESCRIPTION,
                        TestsConstants.TEST_PRIORITY,
                        TestsConstants.TEST_SIZE));
    }
    //todo tests with advance and revert of status, priority and size
    // todo tests with comments and logger
    @Test
    public void advanceStatus_ShouldAdvance_When_StatusIsValid(){
       // Assertions.assertEquals(Status.IN_PROGRESS, story.);
    }

}
