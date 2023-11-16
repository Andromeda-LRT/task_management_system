package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.contracts.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {

    private String name;
    private List<MemberImpl> members;
    private List<BoardImpl> boards;
    private List<LoggerImpl> activityHistory;

    public TeamImpl(String name) {
        this.name = name;
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
        this.activityHistory = new ArrayList<>();

        logChange(String.format("Team %s was created.", getName()));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<MemberImpl> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<BoardImpl> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public List<LoggerImpl> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public void addMember(MemberImpl member) {
        members.add(member);

        logChange(String.format(Constants.TEAM_ADD_METHOD,"Member", member.getName(), getName()));
    }

    @Override
    public void addBoard(BoardImpl board) {
        if(!boards.contains(board)) {
            boards.add(board);
            int index = boards.indexOf(board);
            boards.get(index).addTeam(this);
            logChange(String.format(Constants.TEAM_ADD_METHOD, "Board", board.getName(), getName()));
        }else {
            throw new IllegalArgumentException("This team already exist in board" +
                    getName());
        }
    }

    private void logChange(String change) {
        activityHistory.add(new LoggerImpl(change));
    }

    @Override
    public String showAllTeamMembers() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getName());
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());
        stringBuilder.append(Constants.MEMBERS);
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        for (MemberImpl member : members) {
            stringBuilder.append(member.getName()).append(System.lineSeparator());
        }
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return stringBuilder.toString();
    }

    @Override
    public void removeBoard(BoardImpl board) {
        if (boards.isEmpty()){
            throw new IllegalArgumentException("There are no boards in this team");
        } else {
            for (BoardImpl boardLocal : boards) {
                if (boardLocal.getName().equals(board.getName())) {
                    boards.remove(board);
                    boardLocal.removeTeam(this);
                    logChange("Team board %s was removed.");
                    break;
                }
            }
        }
    }

    @Override
    public String showAllTeamBoards() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getName());
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());
        stringBuilder.append(Constants.BOARDS);
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        for (BoardImpl board : boards) {
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

    //something

}
