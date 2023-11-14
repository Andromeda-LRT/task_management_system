package com.company.oop.taskmanagmentsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.BoardImpl;
import com.company.oop.taskmanagementsystem.models.FeedbackImpl;
import com.company.oop.taskmanagementsystem.models.TaskImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Board;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardImplTests {
    private static final String INVALID_BOARD_NAME = "xxx";
    private static final String VALID_BOARD_NAME = "xxxxxx";
    // TODO The tests should be implemented

    BoardImpl board;

    @BeforeEach
    public void initBoardImpl(){
        board = new BoardImpl(VALID_BOARD_NAME);
    }

    @Test
    public void boardImpl_Should_ImplementBoardInterface(){
        Assertions.assertTrue(board instanceof Board);
    }

    @Test
    public void constructor_Should_InitializeName_When_ArgumentsAreValid(){
        Assertions.assertEquals(VALID_BOARD_NAME, board.getName());
    }
    @Test
    public void constructor_Should_Throw_An_Exception_When_ArgumentsAreInvalid(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BoardImpl(INVALID_BOARD_NAME));
    }
    @Test
    public void getTaskList_Should_ReturnACopyOfTaskList(){
        Assertions.assertNotNull(board.getTaskList());
    }
    @Test
    public void getTaskList_Should_ReturnACopyOfActivityHistory(){
        Assertions.assertNotNull(board.getActivityHistory());
    }
    @Test
    public void addTask_Should_AddTaskToList_When_ArgumentsValid(){
        TaskImpl testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
        board.addTask(testTask);
        Assertions.assertEquals(1, board.getTaskList().size());
    }
    @Test
    public void removeTask_Should_RemoveTaskFromList_When_ArgumentsValid(){
        TaskImpl testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
        board.addTask(testTask);
        board.removeTask(testTask);
        Assertions.assertEquals(0, board.getTaskList().size());
    }
    @Test
    public void showBoardActivity_Should_ReturnBoardActivity_When_ArgumentsValid(){
        TaskImpl testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
        board.addTask(testTask);
        Assertions.assertEquals(String.format("Board: %s%n" +
                "Task %s has been added to board with name %s", board.getName(),
                TestsConstants.VALID_TITLE, board.getName()), board.showBoardActivity());
    }
}
