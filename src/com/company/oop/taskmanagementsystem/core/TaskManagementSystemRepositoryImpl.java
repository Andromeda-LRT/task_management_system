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
import java.util.stream.Collectors;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {
    private static final String TEAM_DOES_NOT_EXIST = "Team %s does not exist!";
    private static final String MEMBER_DOES_NOT_EXIST = "Member %s does not exist!";
    private static final String BOARD_DOES_NOT_EXIST = "Board %s does not exist!";
    private static final String TASK_DOES_NOT_EXIST = "Task with id %d does not exist!";

    private int nextId;
    private final List<Member> members = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();
    private final List<Task> tasks = new ArrayList<>();

    private final List<Bug> bugsList = new ArrayList<>();
    private final List<Story> storiesList = new ArrayList<>();
    private final List<Feedback> feedbackList = new ArrayList<>();

    public TaskManagementSystemRepositoryImpl() {
        nextId = 0;
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Bug> getBugs() {
        return new ArrayList<>(bugsList);
    }

    @Override
    public List<Story> getStories() {
        return new ArrayList<>(storiesList);
    }

    @Override
    public List<Feedback> getFeedback() {
        return new ArrayList<>(feedbackList);
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating) {
        Feedback feedback = new FeedbackImpl(++nextId, title, description, rating);
        feedbackList.add(feedback);
        tasks.add(feedback);
        return feedback;
    }

    @Override
    public Bug createBug(String title, String description,
                         List<String> stepsToReproduce, Priority priority, Severity severity) {
        Bug bug = new BugImpl(++nextId, title, description, stepsToReproduce, priority, severity);
        bugsList.add(bug);
        tasks.add(bug);
        return bug;
    }

    @Override
    public Story createStory(String title, String description, Priority priority, Size size) {
        Story story = new StoryImpl(++nextId, title, description, priority, size);
        storiesList.add(story);
        tasks.add(story);
        return story;
    }

    @Override
    public Member createMember(String name) {
        ValidationHelpers.validateNameIsUniqueInMemberTeamBoard(name, members, teams, boards);
        Member member = new MemberImpl(name);
        members.add(member);
        return member;
    }

    @Override
    public Board createBoard(String name) {
        Board board = new BoardImpl(name);
        boards.add(board);
        return board;
    }

    @Override
    public Team createTeam(String name) {
        ValidationHelpers.validateNameIsUniqueInMemberTeamBoard(name, members, teams, boards);
        Team team = new TeamImpl(name);
        teams.add(team);
        return team;
    }

    @Override
    public boolean teamExist(String teamName) {
        for (Team team : getTeams()) {
            if (team.getName().equalsIgnoreCase(teamName)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean memberExist(String memberName) {
        for (Member member : getMembers()) {
            if (member.getName().equalsIgnoreCase(memberName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Team findTeamByName(String teamName) {
        for (Team team : getTeams()) {
            if (team.getName().equalsIgnoreCase(teamName)) {
                return team;
            }
        }

        throw new IllegalArgumentException(String.format(TEAM_DOES_NOT_EXIST, teamName));
    }

    @Override
    public Member findMemberByName(String memberName) {
        for (Member member : getMembers()) {
            if (member.getName().equalsIgnoreCase(memberName)) {
                return member;
            }
        }

        throw new IllegalArgumentException(String.format(MEMBER_DOES_NOT_EXIST, memberName));
    }

    @Override
    public Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        throw new IllegalArgumentException(String.format(TASK_DOES_NOT_EXIST, id));

    }

    @Override
    public Board findBoardByName(String boardName) {
        for (Board board : getBoards()) {
            if (board.getName().equalsIgnoreCase(boardName)) {
                return board;
            }
        }

        throw new IllegalArgumentException(String.format(BOARD_DOES_NOT_EXIST, boardName));
    }

    @Override
    public List<Bug> findAllBugsInTasks() {
        List<Bug> bugs = getTasks().stream()
                .filter(task -> task instanceof Bug)
                .map(task -> (Bug) task)
                .collect(Collectors.toList());

        return bugs;
    }

    @Override
    public List<Story> findAllStoriesInTasks() {
        List<Story> stories = tasks.stream()
                .filter(task -> task instanceof Story)
                .map(task -> (Story) task)
                .collect(Collectors.toList());

        return stories;
    }

    @Override
    public List<Feedback> findAllFeedbackInTasks() {
        List<Feedback> feedbackList = tasks.stream()
                .filter(task -> task instanceof Feedback)
                .map(task -> (Feedback) task)
                .collect(Collectors.toList());

        return feedbackList;
    }

    @Override
    public Feedback findFeedbackById(int id) {
        for (Feedback feedbackLocal : feedbackList) {
            if (feedbackLocal.getId() == id) {
                return feedbackLocal;
            }
        }
        return null;
    }

    @Override
    public String listTasksWithAssignee() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Member member : getMembers()) {
            if (!member.getListOfTasks().isEmpty()) {
                for (Task task : member.getListOfTasks()) {
                    stringBuilder.append(task.printMainInformation())
                            .append(System.lineSeparator());
                }
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public List<Task> listTasksWithAssigneeSortedByTitle() {
        List<Task> tasks = new ArrayList<>();

        for (Member member : getMembers()) {
            if (!member.getListOfTasks().isEmpty()) {
                for (Task task : member.getListOfTasks()) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    @Override
    public List<Task> filterTaskByTitle(String target) {

        return tasks
                .stream()
                .filter(element -> element.getTitle().contains(target))
                .collect(Collectors.toList());
    }


}