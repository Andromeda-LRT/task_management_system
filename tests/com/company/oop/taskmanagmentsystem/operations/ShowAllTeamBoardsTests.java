package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.ShowAllTeamBoards;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowAllTeamBoardsTests {

    private final TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();

    @Test
    void findTeamByName_Should_ThrowIllegalArgumentException_When_TeamDoesNotExist() {
        repository.createTeam("Team12");

        String nonExistentTeamName = "NonExistent";


        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            repository.findTeamByName(nonExistentTeamName);
        });
    }

    @Test
    void showAllTeamBoards_Should_ThrowIllegalArgumentException_When_NoBoardsAddedToTeam() {
        ShowAllTeamBoards showAllTeamBoards = new ShowAllTeamBoards(repository);

        repository.createTeam("EmptyTeam");
        List<String> parameters = new ArrayList<>();
        parameters.add("EmptyTeam");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            showAllTeamBoards.execute(parameters);
        });
    }
}
