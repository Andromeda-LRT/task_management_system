package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.CreateNewBoardInTeam;
import com.company.oop.taskmanagementsystem.commands.CreateNewTeam;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateNewBoardInTeamTests {
    private final TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();

    @Test
    void execute_Should_CreateNewBoardInTeamAndReturnSuccessMessage_When_ValidParametersProvided() {
        CreateNewTeam newTeam = new CreateNewTeam(repository);
        List<String> teamName = new ArrayList<>();
        teamName.add("TestTeam");
        Team team = repository.createTeam(teamName.get(0));

        CreateNewBoardInTeam createNewBoardInTeamCommand = new CreateNewBoardInTeam(repository);
        List<String> parameters = new ArrayList<>();
        parameters.add("TestBoard");
        parameters.add("TestTeam");


        String result = createNewBoardInTeamCommand.execute(parameters);

        Assertions.assertTrue(repository.findBoardByName("TestBoard") != null);
        Assertions.assertTrue(repository.findTeamByName(team.getName()).getBoards().size() == 1);
        Assertions.assertEquals("Board TestBoard was created in team TestTeam.", result);
    }

    @Test
    void execute_Should_ThrowIllegalArgumentException_When_TeamNotFound() {
        CreateNewBoardInTeam createNewBoardInTeamCommand = new CreateNewBoardInTeam(repository);
        List<String> parameters = new ArrayList<>();
        parameters.add("TestBoard");
        parameters.add("NonExistentTeam");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            createNewBoardInTeamCommand.execute(parameters);
        });
    }
}
