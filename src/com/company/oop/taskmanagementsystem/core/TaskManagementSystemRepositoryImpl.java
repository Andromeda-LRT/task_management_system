package com.company.oop.taskmanagementsystem.core;

import com.company.oop.taskmanagementsystem.constants.Constants;
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

       return getTeams()
                .stream()
                .anyMatch(team -> team.getName().equalsIgnoreCase(teamName));
    }

    @Override
    public boolean memberExist(String memberName) {

       return  getMembers()
                 .stream()
                 .anyMatch(member -> member.getName().equalsIgnoreCase(memberName));
    }

    @Override
    public Team findTeamByName(String teamName) {

      return getTeams()
                .stream()
                .filter(team -> team.getName().equalsIgnoreCase(teamName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format(TEAM_DOES_NOT_EXIST, teamName)));

    }

    @Override
    public Member findMemberByName(String memberName) {

      return getMembers()
                .stream()
                .filter(member -> member.getName().equalsIgnoreCase(memberName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format(MEMBER_DOES_NOT_EXIST, memberName)));
    }

    @Override
    public Task findTaskById(int id) {

        return tasks
                .stream()
                .filter(task -> task.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format(TASK_DOES_NOT_EXIST, id)));
    }

    @Override
    public Board findBoardByName(String boardName) {

        return getBoards()
                .stream()
                .filter(board -> board.getName().equalsIgnoreCase(boardName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format(BOARD_DOES_NOT_EXIST, boardName)));
    }

    @Override
    public Feedback findFeedbackById(int id) {

      return feedbackList
                .stream()
                .filter(feedback -> feedback.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(Constants.ID_DOES_NOT_BELONG_TO_FEEDBACK));
    }

    @Override
    public Story findStoryById(int id) {

       return storiesList
               .stream()
               .filter(storyLocal -> storyLocal.getId() == id)
               .findAny()
               .orElseThrow(() -> new IllegalArgumentException(Constants.ID_DOES_NOT_BELONG_TO_STORY));

    }

    @Override
    public Bug findBugByID(int id) {

       return bugsList
                .stream()
                .filter(bugLocal -> bugLocal.getId() == id)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(Constants.ID_DOES_NOT_BELONG_TO_BUG));
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

    @Override
    public List<Bug> filterBugByAssignee(String target) {

        return bugsList
                .stream()
                .filter(element -> element.getAssignee().getName().equals(target))
                .collect(Collectors.toList());
    }

    @Override
    public List<Story> filterStoryByAssignee(String target) {

        return storiesList
                .stream()
                .filter(element -> element.getAssignee().getName().equals(target))
                .collect(Collectors.toList());
    }

    @Override
    public List<Bug> filterBugByStatus(String target) {

        return bugsList
                .stream()
                .filter(element -> element.getStatus().toString().equalsIgnoreCase(target))
                .collect(Collectors.toList());
    }

    @Override
    public List<Story> filterStoryByStatus(String target) {

        return storiesList
                .stream()
                .filter(element -> element.getStatus().toString().equalsIgnoreCase(target))
                .collect(Collectors.toList());
    }


}