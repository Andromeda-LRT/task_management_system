package com.company.oop.taskmanagmentsystem.models;

import com.company.oop.taskmanagementsystem.models.BugImpl;
import com.company.oop.taskmanagementsystem.models.CommentImpl;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.StoryImpl;
import com.company.oop.taskmanagementsystem.models.TaskImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Comment;
import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Size;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
   @Test
   public void getComments_Should_ReturnACopyOfCommentsList(){
        Assertions.assertNotNull(story.getComments());
   }
    @Test
    public void advanceStatus_Should_AdvanceStatus_WhenValid(){
        story.advanceStatus();
        Assertions.assertEquals(Status.IN_PROGRESS, story.getStatus());
    }

    @Test
    public void revertStatus_Should_RevertStatus_WhenValid(){
        story.advanceStatus();
        story.revertStatus();
        Assertions.assertEquals(Status.NOT_DONE, story.getStatus());
    }
    @Test
    public void advanceStatus_Should_GiveAnErrorMessage_When_StatusCannotAdvanceFurther(){
        story.advanceStatus();
        story.advanceStatus();
        story.advanceStatus();
        Assertions.assertEquals(String.format(Constants.STATUS_IS_ALREADY_SET_TO_DONE, Constants.STORY),
                story.getHistoryOfChanges().get(3).getDescription());
    }
    @Test
    public void revertStatus_Should_GiveAnErrorMessage_When_StatusCannotRevertFurther(){
        story.revertStatus();
        Assertions.assertEquals(String.format(Constants.STATUS_IS_ALREADY_SET_TO_NOT_DONE, Constants.STORY),
                story.getHistoryOfChanges().get(1).getDescription());
    }
    @Test
    public void increasePriority_Should_IncreasePriority_When_PriorityIsValid(){
        story.increasePriority();
        Assertions.assertEquals(Priority.MEDIUM, story.getPriority());
    }
    @Test
    public void lowerPriority_Should_LowerPriority_When_PriorityIsValid(){
        story.increasePriority();
        story.lowerPriority();
        Assertions.assertEquals(Priority.LOW, story.getPriority());
    }
    @Test
    public void increasePriority_Should_GiveAnErrorMessage_When_PriorityCannotIncreaseFurther(){
        story.increasePriority();
        story.increasePriority();
        story.increasePriority();
        Assertions.assertEquals(String.format(Constants.PRIORITY_IS_ALREADY_SET_TO_HIGH, Constants.STORY),
                story.getHistoryOfChanges().get(3).getDescription());
    }
    @Test
    public void lowerPriority_Should_GiveAnErrorMessage_When_PriorityCannotLowerFurther(){
        story.lowerPriority();
        Assertions.assertEquals(String.format(Constants.PRIORITY_IS_ALREADY_SET_TO_LOW, Constants.STORY),
                story.getHistoryOfChanges().get(1).getDescription());
    }
    @Test
    public void increaseSize_Should_IncreaseSize_When_SizeIsValid(){
        story.increaseSize();
        Assertions.assertEquals(Size.LARGE, story.getSize());
    }
    @Test
    public void decreaseSize_Should_DecreaseSize_When_SizeIsValid(){
        story.decreaseSize();
        Assertions.assertEquals(Size.SMALL, story.getSize());
    }
    @Test
    public void increaseSize_Should_GiveAnErrorMessage_When_SizeCannotIncreaseFurther(){
        story.increaseSize();
        story.increaseSize();
        Assertions.assertEquals(Constants.SIZE_ALREADY_SET_TO_LARGE,
                story.getHistoryOfChanges().get(2).getDescription());
    }
    @Test void decreaseSize_Should_GiveAnErrorMessage_When_SizeCannotDecreaseFurther(){
        story.decreaseSize();
        story.decreaseSize();
               Assertions.assertEquals(Constants.SIZE_ALREADY_SET_TO_SMALL,
                story.getHistoryOfChanges().get(2).getDescription());
    }

    // todo tests with logger

    @Test
    public void addComment_Should_AddCommentToList() {
        Comment comment = new CommentImpl("User1", "This is a test comment.");
        story.addComment(comment);
        List<Comment> comments = story.getComments();

        Assertions.assertTrue(comments.contains(comment));
    }
}
