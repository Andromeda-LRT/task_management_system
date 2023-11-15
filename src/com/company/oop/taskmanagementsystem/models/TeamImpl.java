package com.company.oop.taskmanagementsystem.models;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl {
    private static final String ADDED_MEMBER = "%s %s was added to Team %s successfully.";
    private static final String PRINT_HEADER =
            "%s\n----------------------------\n%s:\n----------------------------\n";

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

    public String getName() {
        return name;
    }

    public List<MemberImpl> getMembers() {
        return new ArrayList<>(members);
    }

    public List<BoardImpl> getBoards() {
        return new ArrayList<>(boards);
    }

    public List<LoggerImpl> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    public void addMember(MemberImpl member) {
        members.add(member);

        logChange(String.format(ADDED_MEMBER, "Member", member.getName(), getName()));
    }

    public void addBoard(BoardImpl board) {
        boards.add(board);

        logChange(String.format(ADDED_MEMBER, "Board", board.getName(), getName()));
    }

    private void logChange(String change) {
        activityHistory.add(new LoggerImpl(change));
    }

    public String showAllTeamMembers() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(PRINT_HEADER, getName(), "Members"));

        for (MemberImpl member : members) {
            stringBuilder.append(member.getName()).append(System.lineSeparator());
        }
        stringBuilder.append("----------------------------\n");

        return stringBuilder.toString();
    }

    public String showAllTeamBoards() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(PRINT_HEADER, getName(), "Boards"));

        for (BoardImpl board : boards) {
            stringBuilder.append(board.getName()).append(System.lineSeparator());
        }
        stringBuilder.append("----------------------------");

        return stringBuilder.toString();
    }

//    public String showTeamActivity() {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(String.format(PRINT_HEAER, getName(), "Boards"));
//
//        for (BoardImpl board : boards) {
//            stringBuilder.append(board.getName()).append(System.lineSeparator());
//        }
//        stringBuilder.append("----------------------------");
//
//        return stringBuilder.toString();
//    }
}
