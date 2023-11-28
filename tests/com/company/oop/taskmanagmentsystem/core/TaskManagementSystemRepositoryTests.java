package com.company.oop.taskmanagmentsystem.core;

import com.company.oop.taskmanagementsystem.commands.assignAndUnassign.AssignTaskToMember;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.*;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryTests {
    private static final String INVALID_NAME = "NotExist";

    private TaskManagementSystemRepository repository;
    private Bug bug;
    private Story story;
    private Feedback feedback;
    private Member member;
    private Board board;
    private Team team;
    private List<String> parameters;

    @BeforeEach
    public void setUp() {
        repository = new TaskManagementSystemRepositoryImpl();
        parameters = new ArrayList<>();
        bug = repository.createBug(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.STEPS_TO_REPRODUCE,
                TestsConstants.VALID_PRIORITY,
                TestsConstants.VALID_SEVERITY);
        story = repository.createStory(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.TEST_PRIORITY,
                TestsConstants.TEST_SIZE);
        feedback = repository.createFeedback(TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION,
                TestsConstants.VALID_RATING);
        member = repository.createMember(TestsConstants.VALID_MEMBER_NAME);
        board = repository.createBoard(TestsConstants.VALID_BOARD_NAME);
        team = repository.createTeam(TestsConstants.VALID_TEAM_NAME);
    }

    @Test
    public void createMember_Should_CreateMember_WhenValidParametersArePassed() {
        Assertions.assertTrue(repository.getMembers().contains(member));
    }

    @Test
    public void createStory_Should_CreateStory_WhenValidParametersArePassed() {
        Assertions.assertTrue(repository.getStories().contains(story));
        Assertions.assertTrue(repository.getTasks().contains(story));
    }

    @Test
    public void createBug_Should_CreateBug_WhenValidParametersArePassed() {
        Assertions.assertTrue(repository.getBugs().contains(bug));
        Assertions.assertTrue(repository.getTasks().contains(bug));
    }

    @Test
    public void createFeedback_Should_CreateFeedback_WhenValidParametersArePassed() {
        Assertions.assertTrue(repository.getFeedback().contains(feedback));
        Assertions.assertTrue(repository.getTasks().contains(feedback));
    }

    @Test
    public void createBoard_Should_CreateBoard_WhenValidParametersArePassed() {
        Assertions.assertTrue(repository.getBoards().contains(board));
    }

    @Test
    public void createTeam_Should_CreateTeam_WhenValidParametersArePassed() {
        Assertions.assertTrue(repository.getTeams().contains(team));
    }

    @Test
    public void teamExist_Should_ReturnTrue_WhenTeamExists() {
        Assertions.assertTrue(repository.teamExist(TestsConstants.VALID_TEAM_NAME));
    }

    @Test
    public void teamExist_Should_ReturnFalse_WhenTeamDoesNotExist() {
        Assertions.assertFalse(repository.teamExist(INVALID_NAME));
    }

    @Test
    void listTaskWithAssignee_Should_ReturnAString() {
        repository.createMember(TestsConstants.VALID_MEMBER_NAME);
        AssignTaskToMember assignTaskToMember = new AssignTaskToMember(repository);
        parameters.add("1");
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        assignTaskToMember.execute(parameters);
        Assertions.assertTrue(repository.listTasksWithAssignee() instanceof String);
    }

    @Test
    void listTaskWithAssigneeSortedByTitle_Should_ReturnAListOfTask() {
        repository.createMember(TestsConstants.VALID_MEMBER_NAME);
        AssignTaskToMember assignTaskToMember = new AssignTaskToMember(repository);
        parameters.add("1");
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        assignTaskToMember.execute(parameters);
        Assertions.assertEquals(1, repository.listTasksWithAssigneeSortedByTitle().size());
        Assertions.assertTrue(repository.listTasksWithAssigneeSortedByTitle() instanceof List<Task>);
    }


    @Test
    void filterTaskByTitle_Should_ReturnAListOfTasks_When_TitleIsValid() {
        List<Task> filteredTasksByTitle = repository.filterTaskByTitle(TestsConstants.VALID_TITLE);
        Assertions.assertEquals(3, filteredTasksByTitle.size());
        Assertions.assertTrue(repository.filterTaskByTitle(TestsConstants.VALID_TITLE) instanceof List<Task>);
    }

    @Test
    void filterBugByAssignee_Should_ReturnAListOfBugs_When_AssigneeIsValid() {
        repository.createMember(TestsConstants.VALID_MEMBER_NAME);
        AssignTaskToMember assignTaskToMember = new AssignTaskToMember(repository);
        parameters.add("1");
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        assignTaskToMember.execute(parameters);
        List<Bug> filteredBugByAssignee = repository.filterBugByAssignee(TestsConstants.VALID_MEMBER_NAME);
        Assertions.assertEquals(1, filteredBugByAssignee.size());
        Assertions.assertTrue(repository.filterBugByAssignee(TestsConstants.VALID_MEMBER_NAME) instanceof List<Bug>);
    }

    @Test
    void filterStoryByAssignee_Should_ReturnAListOfStories_When_AssigneeIsValid() {
        repository.createMember(TestsConstants.VALID_MEMBER_NAME);
        AssignTaskToMember assignTaskToMember = new AssignTaskToMember(repository);
        parameters.add("2");
        parameters.add(TestsConstants.VALID_MEMBER_NAME);
        assignTaskToMember.execute(parameters);
        List<Story> filteredStoryByAssignee = repository.filterStoryByAssignee(TestsConstants.VALID_MEMBER_NAME);
        Assertions.assertEquals(1, filteredStoryByAssignee.size());
        Assertions.assertTrue(repository.filterStoryByAssignee(TestsConstants.VALID_MEMBER_NAME) instanceof List<Story>);
    }

    @Test
    void filterBugByStatus_Should_ReturnAListOfBugs_When_StatusIsValid(){
        List<Bug> filteredBugByStatus = repository.filterBugByStatus("active");
        Assertions.assertEquals(1, filteredBugByStatus.size());
        Assertions.assertTrue(repository.filterBugByStatus("active") instanceof List<Bug>);
    }

    @Test
    void filterStoryByStatus_Should_ReturnAListOfStories_When_StatusIsValid() {
        List<Story> filteredStoryByStatus = repository.filterStoryByStatus("Not Done");
        Assertions.assertEquals(1, filteredStoryByStatus.size());
        Assertions.assertTrue(repository.filterStoryByStatus("Not Done") instanceof List<Story>);
    }
}
