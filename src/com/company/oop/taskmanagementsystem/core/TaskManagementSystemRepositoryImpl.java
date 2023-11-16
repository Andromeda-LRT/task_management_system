package com.company.oop.taskmanagementsystem.core;

import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.*;
import com.company.oop.taskmanagementsystem.models.contracts.*;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import com.company.oop.taskmanagementsystem.models.enums.Size;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
    
    private int nextId;
    private final List<Member> members = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();

    public TaskManagementSystemRepositoryImpl() {
        nextId = 0;
    }

    public Feedback createFeedback(String title, String description, int rating) {
        return new FeedbackImpl(++nextId, title, description, rating);
    }

    public Bug createBug(String title, String description,
                         ArrayList<String> stepsToReproduce, Priority priority, Severity severity) {
        return new BugImpl(++nextId, title, description, stepsToReproduce, priority, severity);
    }

    public Story createStory(String title, String description, Priority priority, Size size) {
        return new StoryImpl(++nextId, title, description, priority, size);
    }

    public Member createMember(String name) {
        ValidationHelpers.validateNameIsUniqueInMemberTeamBoard(name, members, teams, boards);
        Member member = new MemberImpl(name);
        members.add(member);
        return member;
    }

    public Board createBoard(String name) {
        Board board = new BoardImpl(name);
        boards.add(board);
        return board;
    }

    public Team createTeam(String name) {
        ValidationHelpers.validateNameIsUniqueInMemberTeamBoard(name, members, teams, boards);
        Team team = new TeamImpl(name);
        teams.add(team);
        return team;
    }

}