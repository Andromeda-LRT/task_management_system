package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.show.ShowBoardActivity;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.FeedbackImpl;
import com.company.oop.taskmanagementsystem.models.LoggerImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Board;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowBoardsActivityTests {

    private Command command;
    private List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    @BeforeEach
    public void initBoardCommand(){

        parameters = new ArrayList<>();
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        command = new ShowBoardActivity(taskManagementSystemRepository);
        parameters.add(TestsConstants.VALID_TEAM_NAME);
        parameters.add(TestsConstants.VALID_BOARD_NAME);
        Board board = taskManagementSystemRepository.createBoard(parameters.get(1));
        taskManagementSystemRepository.createTeam(parameters.get(0));
        taskManagementSystemRepository.findTeamByName(parameters.get(0)).addBoard(board);
    }

    @Test
    public void execute_Should_showBoardActivity_When_ArgumentsValid(){
        Task testTask = new FeedbackImpl(TestsConstants.VALID_ID, TestsConstants.VALID_TITLE,
                TestsConstants.VALID_DESCRIPTION, TestsConstants.VALID_RATING);
        taskManagementSystemRepository.findBoardByName(parameters.get(1)).addTask(testTask);
        StringBuilder loggedEvents = new StringBuilder();
        for (LoggerImpl logger : taskManagementSystemRepository.findBoardByName(parameters.get(1))
                .getActivityHistory()) {
            loggedEvents.append(logger.getDescription());
            loggedEvents.append(System.lineSeparator());
        }
        loggedEvents.append(Constants.LINE_DIVISOR);

        Assertions.assertEquals(String.format("Board: %s%n" +
                        Constants.ACTIVITY + "%n" + Constants.LINE_DIVISOR + "%n" + loggedEvents,
                taskManagementSystemRepository.findBoardByName(parameters.get(1)).getName(),
                taskManagementSystemRepository.findBoardByName(parameters.get(1)).getName(),
                taskManagementSystemRepository.findBoardByName(parameters.get(1)).getName()),
                command.execute(parameters));
    }
    @Test
    public void execute_Should_ThrowAnException_When_ArgumentsInvalid(){
        parameters = new ArrayList<>();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
    @Test
    public void execute_Should_ThrowAnException_When_TeamDoesNotExist(){
        parameters = new ArrayList<>();
        parameters.add("TestTeam1");
        parameters.add(TestsConstants.VALID_BOARD_NAME);
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
    @Test
    public void execute_Should_ThrowAnException_When_BoardDoesNotExist(){
        parameters = new ArrayList<>();
        parameters.add(TestsConstants.VALID_TEAM_NAME);
        parameters.add("testBoard");
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
}
