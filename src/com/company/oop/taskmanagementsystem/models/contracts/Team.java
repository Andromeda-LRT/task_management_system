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

    List<MemberImpl> getMembers();

    List<BoardImpl> getBoards();

    List<LoggerImpl> getActivityHistory();

    void addMember(MemberImpl member);

    void addBoard(BoardImpl board);

    String showAllTeamMembers();

    String showAllTeamBoards();

    String showTeamActivity();

    void removeBoard(BoardImpl board);
}
