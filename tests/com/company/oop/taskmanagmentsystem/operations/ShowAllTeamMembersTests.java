package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.show.ShowAllTeamMembers;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowAllTeamMembersTests {

    private final TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();

    @Test
    void execute_Should_ThrowIllegalArgumentException_When_NoMembersAddedToTeam() {
        ShowAllTeamMembers showAllTeamMembersCommand = new ShowAllTeamMembers(repository);
        List<String> parameters = new ArrayList<>();
        parameters.add("EmptyTeam");

        repository.createTeam("EmptyTeam");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            showAllTeamMembersCommand.execute(parameters);
        });
    }
}
