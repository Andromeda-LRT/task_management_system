package com.company.oop.taskmanagmentsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.*;
import com.company.oop.taskmanagementsystem.models.contracts.Board;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.models.contracts.Team;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardImplTests {

    BoardImpl board;
    Team team;
    Member member;

    @BeforeEach
    public void initBoardImpl(){
        board = new BoardImpl(TestsConstants.VALID_BOARD_NAME);
        team = new TeamImpl(TestsConstants.VALID_TEAM_NAME);
        member = new MemberImpl(TestsConstants.VALID_MEMBER_NAME);
    }

    @Test
    public void boardImpl_Should_ImplementBoardInterface(){
        Assertions.assertTrue(board instanceof Board);
    }

    @Test
    public void constructor_Should_InitializeName_When_ArgumentsAreValid(){
        Assertions.assertEquals(TestsConstants.VALID_BOARD_NAME, board.getName());
    }
    @Test
    public void constructor_Should_Throw_An_Exception_When_ArgumentsAreInvalid(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BoardImpl(TestsConstants.INVALID_BOARD_NAME));
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
        Task testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
        board.addTask(testTask);
        Assertions.assertEquals(1, board.getTaskList().size());
    }
    @Test
    public void removeTask_Should_RemoveTaskFromList_When_ArgumentsValid(){
        Task testTask = new StoryImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.TEST_PRIORITY, TestsConstants.TEST_SIZE);
        board.addTask(testTask);
        board.removeTask(testTask);
        Assertions.assertEquals(0, board.getTaskList().size());
    }
    @Test
    public void removeTask_Should_AlsoUnassignTaskFromMember_When_ArgumentsValid(){
        Task testTask = new StoryImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.TEST_PRIORITY, TestsConstants.TEST_SIZE);
        board.addTeam(team);
        team.addMember(member);
        board.addTask(testTask);
        board.assignTask(testTask, member);
        board.removeTask(testTask);
        Assertions.assertEquals(0, member.getListOfTasks().size());
    }
    @Test
    public void assignTask_Should_AssignTaskToList_When_ArgumentsValid(){
        Team team1 = new TeamImpl(TestsConstants.VALID_TEAM_NAME);
        Member member1 = new MemberImpl(TestsConstants.VALID_MEMBER_NAME);
        Task testTask = new BugImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.STEPS_TO_REPRODUCE
                , TestsConstants.TEST_PRIORITY, TestsConstants.VALID_SEVERITY);
        team1.addMember(member1);
        board.addTeam(team1);
        board.assignTask(testTask, member1);

        Assertions.assertEquals(1, member1.getListOfTasks().size());

    }
    @Test
    public void unassignTask_Should_UnassignTaskFromList_When_ArgumentsValid(){
        Team team1 = new TeamImpl(TestsConstants.VALID_TEAM_NAME);
        Member member1 = new MemberImpl(TestsConstants.VALID_MEMBER_NAME);
        Task testTask = new BugImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.STEPS_TO_REPRODUCE
                , TestsConstants.TEST_PRIORITY, TestsConstants.VALID_SEVERITY);
        Task testTask1 = new StoryImpl(2, TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.TEST_PRIORITY,
                TestsConstants.TEST_SIZE);
        team1.addMember(member1);
        board.addTeam(team1);
        board.assignTask(testTask, member1);
        board.assignTask(testTask1, member1);
        board.unassignTask(testTask, member1);

        Assertions.assertEquals(1, member1.getListOfTasks().size());

    }
    @Test
    public void assignTask_Should_ThrowAnException_When_TeamsListIsEmpty(){
        Member member1 = new MemberImpl(TestsConstants.VALID_MEMBER_NAME);
        Task testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);

                Assertions.assertThrows(IllegalArgumentException.class, () ->
         board.assignTask(testTask, member1));

    }
    @Test
    public void unassignTask_Should_ThrowAnException_When_TeamsListIsEmpty(){
        Member member1 = new MemberImpl(TestsConstants.VALID_MEMBER_NAME);
        Task testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);

                Assertions.assertThrows(IllegalArgumentException.class, () ->
         board.unassignTask(testTask, member1));

    }
    @Test
    public void assignTask_Should_ThrowAnException_When_MemberIsNotFound(){
        Team team1 = new TeamImpl(TestsConstants.VALID_TEAM_NAME);
        Member member1 = new MemberImpl(TestsConstants.VALID_MEMBER_NAME);
        Task testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
        board.addTeam(team1);

                Assertions.assertThrows(IllegalArgumentException.class, () ->
         board.assignTask(testTask, member1));

    }
    @Test
    public void unassignTask_Should_ThrowAnException_When_MemberIsNotFound(){
        Team team1 = new TeamImpl(TestsConstants.VALID_TEAM_NAME);
        Member member1 = new MemberImpl(TestsConstants.VALID_MEMBER_NAME);
        Task testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
        board.addTeam(team1);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                board.unassignTask(testTask, member1));
    }
    @Test
    public void addTeam_Should_AddTeamToList_When_ArgumentsValid(){
        Team team1 = new TeamImpl(TestsConstants.VALID_TEAM_NAME);
        board.addTeam(team1);
        Assertions.assertEquals(1,board.getTeams().size());
        Assertions.assertEquals(team1.getName(), board.getTeams().get(0).getName());
    }
    @Test
    public void addTeam_Should_ThrowAnException_When_TeamAlreadyExistsInBoard(){
        board.addTeam(team);
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                board.addTeam(team));
    }
    @Test
    public void removeTeam_Should_RemoveTeamFromList_When_ArgumentsAreValid(){
        Team team1 = new TeamImpl(TestsConstants.VALID_TEAM_NAME);
        board.addTeam(team1);
        board.removeTeam(team1);
        Assertions.assertEquals(0, board.getTeams().size());
    }
    @Test
    public void removeTeam_Should_ThrowAnException_When_TeamListIsEmpty(){
        Team team1 = new TeamImpl(TestsConstants.VALID_TEAM_NAME);
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                board.removeTeam(team1));
    }
    @Test
    public void removeTeam_Should_ThrowAnException_When_TeamDoesNotExistInBoard(){
        Team team1 = new TeamImpl(TestsConstants.VALID_TEAM_NAME_2);
        board.addTeam(team);
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                board.removeTeam(team1));
    }
    @Test
    public void showBoardActivity_Should_ReturnBoardActivity_When_ArgumentsValid(){
        Task testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
        board.addTask(testTask);
        StringBuilder loggedEvents = new StringBuilder();
        for (LoggerImpl logger : board.getActivityHistory()) {
            loggedEvents.append(logger.getDescription());
            loggedEvents.append(System.lineSeparator());
        }
        loggedEvents.append(Constants.LINE_DIVISOR);

        Assertions.assertEquals(String.format("Board: %s%n" +
                Constants.ACTIVITY + "%n" + Constants.LINE_DIVISOR + "%n" +
                loggedEvents, board.getName(), board.getName(),
                board.getName()), board.showBoardActivity());
    }
}
