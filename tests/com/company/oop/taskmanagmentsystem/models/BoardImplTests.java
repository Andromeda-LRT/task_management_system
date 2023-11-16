package com.company.oop.taskmanagmentsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.*;
import com.company.oop.taskmanagementsystem.models.contracts.Board;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//
//public class BoardImplTests {
//
//
//    // TODO The tests should be implemented
//
//    BoardImpl board;
//
//    @BeforeEach
//    public void initBoardImpl(){
//        board = new BoardImpl(TestsConstants.VALID_BOARD_NAME);
//    }
//
//    @Test
//    public void boardImpl_Should_ImplementBoardInterface(){
//        Assertions.assertTrue(board instanceof Board);
//    }
//
//    @Test
//    public void constructor_Should_InitializeName_When_ArgumentsAreValid(){
//        Assertions.assertEquals(TestsConstants.VALID_BOARD_NAME, board.getName());
//    }
//    @Test
//    public void constructor_Should_Throw_An_Exception_When_ArgumentsAreInvalid(){
//        Assertions.assertThrows(IllegalArgumentException.class, () ->
//                new BoardImpl(TestsConstants.INVALID_BOARD_NAME));
//    }
//    @Test
//    public void getTaskList_Should_ReturnACopyOfTaskList(){
//        Assertions.assertNotNull(board.getTaskList());
//    }
//    @Test
//    public void getTaskList_Should_ReturnACopyOfActivityHistory(){
//        Assertions.assertNotNull(board.getActivityHistory());
//    }
//    @Test
//    public void addTask_Should_AddTaskToList_When_ArgumentsValid(){
//        TaskImpl testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
//                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
//        board.addTask(testTask);
//        Assertions.assertEquals(1, board.getTaskList().size());
//    }
//    @Test
//    public void removeTask_Should_RemoveTaskFromList_When_ArgumentsValid(){
//        TaskImpl testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
//                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
//        board.addTask(testTask);
//        board.removeTask(testTask);
//        Assertions.assertEquals(0, board.getTaskList().size());
//    }
//    @Test
//    public void assignTask_Should_AssignTaskToList_When_ArgumentsValid(){
//        TeamImpl team1 = new TeamImpl(TestsConstants.VALID_TEAM_NAME);
//        MemberImpl member1 = new MemberImpl(TestsConstants.VALID_MEMBER_NAME);
//        TaskImpl testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
//                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
//        team1.addMember(member1);
//        board.addTeam(team1);
//        board.assignTask(testTask, member1);
//
//        Assertions.assertEquals(1, member1.getListOfTasks().size());
//
//    }
//    @Test
//    public void unassignTask_Should_UnassignTaskFromList_When_ArgumentsValid(){
//        TeamImpl team1 = new TeamImpl(TestsConstants.VALID_TEAM_NAME);
//        MemberImpl member1 = new MemberImpl(TestsConstants.VALID_MEMBER_NAME);
//        TaskImpl testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
//                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
//        TaskImpl testTask1 = new StoryImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
//                TestsConstants.VALID_DESCRIPTION, TestsConstants.TEST_PRIORITY,
//                TestsConstants.TEST_SIZE);
//        team1.addMember(member1);
//        board.addTeam(team1);
//        board.assignTask(testTask, member1);
//        board.assignTask(testTask1, member1);
//        board.unassignTask(testTask, member1);
//
//        Assertions.assertEquals(1, member1.getListOfTasks().size());
//
//    }
//    @Test
//    public void assignTask_Should_ThrowAnException_When_TeamsListIsEmpty(){
//        MemberImpl member1 = new MemberImpl(TestsConstants.VALID_MEMBER_NAME);
//        TaskImpl testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
//                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
//
//                Assertions.assertThrows(IllegalArgumentException.class, () ->
//         board.assignTask(testTask, member1));
//
//    }
//    @Test
//    public void unassignTask_Should_ThrowAnException_When_TeamsListIsEmpty(){
//        MemberImpl member1 = new MemberImpl(TestsConstants.VALID_MEMBER_NAME);
//        TaskImpl testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
//                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
//
//                Assertions.assertThrows(IllegalArgumentException.class, () ->
//         board.unassignTask(testTask, member1));
//
//    }
//    @Test
//    public void assignTask_Should_ThrowAnException_When_MemberIsNotFound(){
//        TeamImpl team1 = new TeamImpl(TestsConstants.VALID_TEAM_NAME);
//        MemberImpl member1 = new MemberImpl(TestsConstants.VALID_MEMBER_NAME);
//        TaskImpl testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
//                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
//        board.addTeam(team1);
//
//                Assertions.assertThrows(IllegalArgumentException.class, () ->
//         board.assignTask(testTask, member1));
//
//    }
//    @Test
//    public void unassignTask_Should_ThrowAnException_When_MemberIsNotFound(){
//        TeamImpl team1 = new TeamImpl(TestsConstants.VALID_TEAM_NAME);
//        MemberImpl member1 = new MemberImpl(TestsConstants.VALID_MEMBER_NAME);
//        TaskImpl testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
//                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
//        board.addTeam(team1);
//
//        Assertions.assertThrows(IllegalArgumentException.class, () ->
//                board.unassignTask(testTask, member1));
//    }
//    @Test
//    public void addTeam_Should_AddTeamToList_When_ArgumentsValid(){
//        TeamImpl team1 = new TeamImpl(TestsConstants.VALID_TEAM_NAME);
//        board.addTeam(team1);
//        Assertions.assertEquals(1,board.getTeams().size());
//        Assertions.assertEquals(team1.getName(), board.getTeams().get(0).getName());
//    }
//    @Test
//    public void removeTeam_Should_RemoveTeamFromList_When_ArgumentsAreValid(){
//        TeamImpl team1 = new TeamImpl(TestsConstants.VALID_TEAM_NAME);
//        board.addTeam(team1);
//        board.removeTeam(team1);
//        Assertions.assertEquals(0, board.getTeams().size());
//    }
//    @Test
//    public void removeTeam_Should_ThrowAnException_When_TeamListIsEmpty(){
//        TeamImpl team1 = new TeamImpl(TestsConstants.VALID_TEAM_NAME);
//        Assertions.assertThrows(IllegalArgumentException.class, () ->
//                board.removeTeam(team1));
//    }
//    @Test
//    public void showBoardActivity_Should_ReturnBoardActivity_When_ArgumentsValid(){
//        TaskImpl testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
//                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
//        board.addTask(testTask);
//        Assertions.assertEquals(String.format("Board: %s%n" +
//                "Task %s has been added to board with name %s", board.getName(),
//                TestsConstants.VALID_TITLE, board.getName()), board.showBoardActivity());
//    }
//}
