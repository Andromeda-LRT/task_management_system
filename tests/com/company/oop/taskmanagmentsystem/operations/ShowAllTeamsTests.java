package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.ShowAllTeams;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowAllTeamsTests {

    private final TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();

    @Test
    void execute_Should_ThrowIllegalArgumentException_When_NoTeamsInRepository() {
        ShowAllTeams showAllTeamsCommand = new ShowAllTeams(repository);
        List<String> parameters = new ArrayList<>();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            showAllTeamsCommand.execute(parameters);
        });
    }
}
