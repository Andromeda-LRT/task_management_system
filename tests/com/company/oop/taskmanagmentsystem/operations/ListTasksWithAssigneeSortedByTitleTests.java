package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.list.ListTasksWithAssigneeSortedByTitle;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTasksWithAssigneeSortedByTitleTests {
    TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();

    @Test
    void execute_Should_ListTasksWithAssigneeSortedByTitle_When_AssigneesExist() {
       ListTasksWithAssigneeSortedByTitle listTasksCommand = new ListTasksWithAssigneeSortedByTitle(repository);
        List<String> parameters = new ArrayList<>();

        repository.createMember("Assignee1");
        repository.createMember("Assignee2");
        repository.createStory(
                "xxxxxxxxxx",
                "Valid descritpion of story",
                Priority.LOW,
                Size.MEDIUM
        );

        repository.createStory(
                "NextStory123",
                "Valid descritpion of story",
                Priority.LOW,
                Size.MEDIUM
        );

        repository.getMembers().get(0).assignTask(repository.getTasks().get(0));
        repository.getMembers().get(1).assignTask(repository.getTasks().get(1));

        String result = listTasksCommand.execute(parameters);

        Assertions.assertTrue(result.contains("Tasks with assignee sorted by title:"));
        Assertions.assertTrue(result.indexOf("NextStory123") < result.indexOf("xxxxxxxxxx"));
    }

    @Test
    void execute_Should_ThrowIllegalArgumentException_When_NoAssigneesExist() {
        ListTasksWithAssigneeSortedByTitle listTasksCommand = new ListTasksWithAssigneeSortedByTitle(repository);
        List<String> parameters = new ArrayList<>();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            listTasksCommand.execute(parameters);
        });
    }
}
