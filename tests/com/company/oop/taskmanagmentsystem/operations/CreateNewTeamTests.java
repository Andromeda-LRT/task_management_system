package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.create.CreateNewTeam;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateNewTeamTests {
    private final TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();

    @Test
    void execute_Should_CreateNewTeamAndReturnSuccessMessage_When_ValidTeamNameProvided() {
        CreateNewTeam createNewTeamCommand = new CreateNewTeam(repository);
        List<String> parameters = new ArrayList<>();
        parameters.add("TestTeam");

        String result = createNewTeamCommand.execute(parameters);

        Assertions.assertTrue(repository.teamExist("TestTeam"));
        Assertions.assertEquals("Team with name TestTeam was created!", result);
    }

    @Test
    void execute_Should_ThrowIllegalArgumentException_When_TeamAlreadyExists() {
        CreateNewTeam createNewTeamCommand = new CreateNewTeam(repository);
        List<String> parameters = new ArrayList<>();
        parameters.add("TestTeam");

        repository.createTeam("TestTeam");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            createNewTeamCommand.execute(parameters);
        });
    }

    @Test
    void execute_Should_ThrowIllegalArgumentException_When_TeamNameIsInvalid() {
        CreateNewTeam createNewTeamCommand = new CreateNewTeam(repository);
        List<String> parameters = new ArrayList<>();
        parameters.add("Test");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            createNewTeamCommand.execute(parameters);
        });
    }
}
