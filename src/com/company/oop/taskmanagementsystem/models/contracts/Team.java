package com.company.oop.taskmanagementsystem.models.contracts;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.BoardImpl;
import com.company.oop.taskmanagementsystem.models.LoggerImpl;
import com.company.oop.taskmanagementsystem.models.MemberImpl;
import com.company.oop.taskmanagementsystem.models.TeamImpl;

import java.util.ArrayList;
import java.util.List;

public interface Team {

    String getName();

    List<Member> getMembers();

    List<Board> getBoards();

    List<LoggerImpl> getActivityHistory();

    void addMember(Member member);

    void addBoard(Board board);

    String showAllTeamMembers();

    String showAllTeamBoards();

    String showTeamActivity();

    void removeBoard(Board board);
}
