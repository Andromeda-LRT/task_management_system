package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.contracts.Board;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.models.contracts.Team;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class TeamImpl implements Team {

    private static final int NAME_MIN_LENGTH = 5;
    private static final int NAME_MAX_LENGTH = 15;
    private static final String NAME_LENGTH_ERROR = format(
            "The name must be between %s and %s characters long!",
            NAME_MIN_LENGTH,
            NAME_MAX_LENGTH);
    private static final String BOARD_EXISTS = "Board with name %s already exist in team %s";
    private static final String BOARD_REMOVED = "Board %s was removed from team %s.";
    private static final String NO_BOARDS_IN_TEAM = "There are no boards in this team %s";
    private static final String NO_SUCH_BOARD_IN_TEAM = "Board with name %s does not exist in team %s";
    private String name;
    private List<Member> members;
    private List<Board> boards;
    private List<LoggerImpl> activityHistory;

    public TeamImpl(String name) {
        setName(name);
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.activityHistory = new ArrayList<>();

        logChange(String.format("Team %s was created.", getName()));
    }

    private void setName(String name) {
        ValidationHelpers.validateIntRange(name.length(),
                NAME_MIN_LENGTH,
                NAME_MAX_LENGTH,
                NAME_LENGTH_ERROR);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public List<LoggerImpl> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public void addMember(Member member) {
        members.add(member);

        logChange(String.format(Constants.TEAM_ADD_METHOD, "Member", member.getName(), getName()));
    }

    @Override
    public void addBoard(Board board) {
        if (!boards.contains(board) && !isBoardNameExist(board.getName())) {
            boards.add(board);
            int index = boards.indexOf(board);
            boards.get(index).addTeam(this);
            logChange(String.format(Constants.TEAM_ADD_METHOD, "Board", board.getName(), getName()));
        } else {
            throw new IllegalArgumentException(
                    String.format(
                            BOARD_EXISTS,
                            board.getName(),
                            getName())
            );
        }
    }

    private boolean isBoardNameExist(String name) {
        for (Board board : getBoards()) {
            if (board.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private void logChange(String change) {
        activityHistory.add(new LoggerImpl(change));
    }

    @Override
    public String showAllTeamMembers() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(
                 getName())
                .append(" ")
                .append(Constants.MEMBERS)
                .append(System.lineSeparator())
                .append(Constants.LINE_DIVISOR)
                .append(System.lineSeparator());

        for (Member member : members) {
            stringBuilder.append(member.getName()).append(System.lineSeparator());
        }
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return stringBuilder.toString();
    }

    @Override
    public void removeBoard(Board board) {
        boolean flag = false;

        if (boards.isEmpty()) {
            throw new IllegalArgumentException(String.format(NO_BOARDS_IN_TEAM, getBoards()));
        } else {
            for (Board boardLocal : boards) {
                if (boardLocal.getName().equals(board.getName())) {
                    boards.remove(board);
                    boardLocal.removeTeam(this);
                    logChange(String.format(BOARD_REMOVED, board.getName(), getName()));
                    flag = true;
                    break;
                }
            }
        }
        if(!flag){
            throw new IllegalArgumentException(
                    String.format(NO_SUCH_BOARD_IN_TEAM, board.getName(), getName())
            );
        }
    }

    @Override
    public String showAllTeamBoards() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(getName())
                .append(" ")
                .append(Constants.BOARDS)
                .append(System.lineSeparator());
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        for (Board board : boards) {
            stringBuilder.append(board.getName()).append(System.lineSeparator());
        }
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return stringBuilder.toString();
    }

    @Override
    public String showTeamActivity() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getName());
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());
        stringBuilder.append(String.format(Constants.ACTIVITY, getName()));
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        for (LoggerImpl logger : getActivityHistory()) {
            stringBuilder.append(logger.getDescription()).append(System.lineSeparator());
        }
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return stringBuilder.toString();
    }
}
