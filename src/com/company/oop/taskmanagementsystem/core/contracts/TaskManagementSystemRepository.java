package com.company.oop.taskmanagementsystem.core.contracts;

import com.company.oop.taskmanagementsystem.models.*;
import com.company.oop.taskmanagementsystem.models.contracts.*;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Severity;
import com.company.oop.taskmanagementsystem.models.enums.Size;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface TaskManagementSystemRepository {
    Member createMember(String name);

    Story createStory(String title, String description, Priority priority, Size size);

    Bug createBug(String title, String description,
                  List<String> stepsToReproduce, Priority priority, Severity severity);

    Feedback createFeedback(String title, String description, int rating);

    Board createBoard(String name);

    List<Member> getMembers();

    List<Team> getTeams();

    List<Board> getBoards();

    List<Task> getTasks();

    Team createTeam(String name);

    boolean teamExist(String teamName);

    boolean memberExist(String memberName);

    Team findTeamByName(String teamName);

    Member findMemberByName(String memberName);

    Board findBoardByName(String boardName);

    Task findTaskById(int id);

    String listTasksWithAssignee();

    List<Task> listTasksWithAssigneeSortedByTitle();

    List<Task> filterTaskByTitle(String target);
    List<Bug> filterBugByAssignee(String target);
    List<Story> filterStoryByAssignee(String target);
    List<Story> filterStoryByStatus(String target);
    List<Bug> filterBugByStatus(String target);
    List<Bug> getBugs();

    List<Story> getStories();

    List<Feedback> getFeedback();

    Feedback findFeedbackById(int id);
    Story findStoryById(int id);
    Bug findBugByID(int id);


}
