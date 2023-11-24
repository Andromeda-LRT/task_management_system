package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.list.ListTasksWithAssignee;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTasksWithAssigneeTests {

    private final TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();

    @Test
    void execute_Should_ListTasksWithAssignee_When_AssigneesExist() {
        ListTasksWithAssignee listTasksWithAssigneeCommand = new ListTasksWithAssignee(repository);
        List<String> parameters = new ArrayList<>();

        repository.createMember("Assignee1");

        repository.createStory(
                "xxxxxxxxxx",
                "Valid descritpion of story",
                Priority.LOW,
                Size.MEDIUM
        );

        repository.getMembers().get(0).assignTask(repository.getTasks().get(0));
        String result = listTasksWithAssigneeCommand.execute(parameters);

        Assertions.assertTrue(result.contains("Tasks with assignee:"));
        Assertions.assertTrue(result.contains("xxxxxxxxxx"));
    }

    @Test
    void execute_Should_ThrowIllegalArgumentException_When_NoAssigneesExist() {
        ListTasksWithAssignee listTasksWithAssigneeCommand = new ListTasksWithAssignee(repository);
        List<String> parameters = new ArrayList<>();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            listTasksWithAssigneeCommand.execute(parameters);
        });
    }
}
