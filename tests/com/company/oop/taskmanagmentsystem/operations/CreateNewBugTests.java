package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.create.CreateNewBug;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.BoardImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Board;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateNewBugTests {

    private Command command;
    private List<String> parameters;
    private TaskManagementSystemRepository taskManagementSystemRepository;

    @BeforeEach
    public void initCreateBugInBoardCommand(){
        taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
        command = new CreateNewBug(taskManagementSystemRepository);
        parameters = new ArrayList<>();
        parameters.add(TestsConstants.VALID_TEAM_NAME);
        parameters.add(TestsConstants.VALID_BOARD_NAME);
        parameters.add(TestsConstants.VALID_TITLE);
        parameters.add(TestsConstants.VALID_DESCRIPTION);
        parameters.add(TestsConstants.STEPS_TO_REPRODUCE.toString());
        parameters.add(String.valueOf(TestsConstants.TEST_PRIORITY));
        parameters.add(String.valueOf(TestsConstants.VALID_SEVERITY));
    }

    @Test
    public void execute_Should_CreateBugInBoardCommand_When_ValidParameters(){
        taskManagementSystemRepository.createTeam(parameters.get(0));
        Board board = new BoardImpl(parameters.get(1));

        taskManagementSystemRepository.findTeamByName(parameters.get(0)).addBoard(board);

        Assertions.assertEquals(String.format(CreateNewBug.BUG_CREATED_SUCCESSFULLY,
                TestsConstants.VALID_BOARD_NAME, TestsConstants.VALID_TEAM_NAME, TestsConstants.VALID_TITLE), command.execute(parameters));
    }
    @Test
    public void execute_Should_ThrowException_When_BoardDoesNotExist(){
        taskManagementSystemRepository.createTeam(parameters.get(0));


        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
    @Test
    public void execute_Should_ThrowException_When_MissingParameters(){
        parameters = new ArrayList<>();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }

}
