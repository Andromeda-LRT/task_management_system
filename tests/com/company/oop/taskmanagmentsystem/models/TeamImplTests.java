package com.company.oop.taskmanagmentsystem.models;

import com.company.oop.taskmanagementsystem.models.BoardImpl;
import com.company.oop.taskmanagementsystem.models.LoggerImpl;
import com.company.oop.taskmanagementsystem.models.MemberImpl;
import com.company.oop.taskmanagementsystem.models.TeamImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Board;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TeamImplTests {
    private TeamImpl team;
    private MemberImpl member;
    private BoardImpl board;

    @BeforeEach
    void setUp() {
        team = new TeamImpl("TestTeam");
        member = new MemberImpl("First_LastName");
        board = new BoardImpl("TestBoard");
    }

    @Test
    void constructor_ShouldCreateNewTeam_When_ValidArguments() {
        Assertions.assertEquals("TestTeam", team.getName());
    }

    @Test
    void addMember_Should_AddExistingMemberToTeam_WhenValidArguments() {
        team.addMember(member);
        Assertions.assertTrue(team.getMembers().contains(member));

        Assertions.assertEquals(2, team.getActivityHistory().size());
        Assertions.assertEquals("Member First_LastName was added to Team TestTeam successfully.",
                team.getActivityHistory().get(1).getDescription());
    }

    @Test
    void addBoard_Should_AddExistingBoardToTeam_WhenValidArguments() {
        team.addBoard(board);
        Assertions.assertTrue(team.getBoards().contains(board));

        Assertions.assertEquals(2, team.getActivityHistory().size());
        Assertions.assertEquals("Board TestBoard was added to Team TestTeam successfully.",
                team.getActivityHistory().get(1).getDescription());
    }

    @Test
    void addBoard_Should_ThrowIllegalArgumentException_When_BoardAlreadyInList() {
        Board board = new BoardImpl("Board 1");
        team.addBoard(board);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            team.addBoard(board);
        });
    }

    @Test
    void addBoard_Should_ThrowIllegalArgumentException_When_BoardNameAlreadyExists() {
        Board board1 = new BoardImpl("Board 1");
        Board board2 = new BoardImpl("Board 1");
        team.addBoard(board1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            team.addBoard(board2);
        });
    }
    @Test
    void getMembers_ShouldReturnsCopy() {
        team.addMember(member);

        List<Member> membersCopy = team.getMembers();

        Assertions.assertNotSame(team.getMembers(), membersCopy);

        Assertions.assertTrue(membersCopy.contains(member));

        membersCopy.clear();
        Assertions.assertFalse(team.getMembers().isEmpty());
    }

    @Test
    void getBoards_ShouldReturnsCopy() {
        team.addBoard(board);

        List<Board> boardsCopy = team.getBoards();

        Assertions.assertNotSame(team.getBoards(), boardsCopy);

        Assertions.assertTrue(boardsCopy.contains(board));

        boardsCopy.clear();
        Assertions.assertFalse(team.getBoards().isEmpty());
    }

    @Test
    void getActivityHistory_ShouldReturnsCopy() {
        List<LoggerImpl> activityHistoryCopy = team.getActivityHistory();
        LoggerImpl log = new LoggerImpl("New Log");
        activityHistoryCopy.add(log);

        Assertions.assertNotSame(team.getActivityHistory(), activityHistoryCopy);

        Assertions.assertTrue(activityHistoryCopy.contains(log));

        activityHistoryCopy.clear();
        Assertions.assertFalse(team.getActivityHistory().isEmpty());
    }

    @Test
    void addMember_Should_AddMemberToTeamAndLogActivity_When_ValidMemberAdded() {
        team.addMember(member);

        Assertions.assertTrue(team.getMembers().contains(member));

        Assertions.assertEquals(2, team.getActivityHistory().size());
        Assertions.assertEquals(
                "Member First_LastName was added to Team TestTeam successfully.",
                team.getActivityHistory().get(1).getDescription()
        );
    }

    @Test
    void addBoard_Should_AddBoardToTeamAndLogActivity_When_ValidBoardAdded() {
        team.addBoard(board);

        Assertions.assertTrue(team.getBoards().contains(board));

        Assertions.assertEquals(2, team.getActivityHistory().size());
        Assertions.assertEquals(
                "Board TestBoard was added to Team TestTeam successfully.",
                team.getActivityHistory().get(1).getDescription()
        );
    }

    @Test
    void removeBoard_Should_RemoveBoardFromTeamAndLogActivity_When_ExistingBoardRemoved() {
        team.addBoard(board);

        Assertions.assertEquals(1, team.getBoards().size());

        team.removeBoard(board);

        Assertions.assertFalse(team.getBoards().contains(board));

        Assertions.assertFalse(board.getTeams().contains(team));

        Assertions.assertEquals(3, team.getActivityHistory().size());
        Assertions.assertEquals(
                "Board TestBoard was removed from team TestTeam.",
                team.getActivityHistory().get(2).getDescription());
    }
    @Test
    void removeBoard_Should_ThrowIllegalArgumentException_When_NoBoardsInList() {
        Board board = new BoardImpl("Board 1");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            team.removeBoard(board);
        });
    }

    @Test
    void removeBoard_Should_ThrowIllegalArgumentException_When_BoardNotInList() {
        Board board1 = new BoardImpl("Board 1");
        Board board2 = new BoardImpl("Board 2");
        team.addBoard(board1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            team.removeBoard(board2);
        });
    }
}
