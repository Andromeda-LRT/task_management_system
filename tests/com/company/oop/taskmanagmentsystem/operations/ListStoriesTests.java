package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.list.ListAllStories;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ListStoriesTests {
    private final TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();

    @Test
    void execute_Should_ListAllStories_When_NoParametersProvided() {
        ListAllStories listAllStoriesCommand = new ListAllStories(repository);
        List<String> parameters = new ArrayList<>();

        repository.createStory(
                "Title 11111",
                "Valid descritpion of story",
                Priority.LOW,
                Size.MEDIUM
        );
        repository.createStory(
                "Title 22222",
                "Valid descritpion of story",
                Priority.LOW,
                Size.MEDIUM
        );

        String result = listAllStoriesCommand.execute(parameters);

        Assertions.assertTrue(result.contains("---ALL STORIES---"));
        Assertions.assertTrue(result.contains("Title 11111"));
        Assertions.assertTrue(result.contains("Title 22222"));
    }

    @Test
    void execute_Should_ReturnNoStoriesMessage_When_NoStoriesInRepository() {
        ListAllStories listAllStoriesCommand = new ListAllStories(repository);
        List<String> parameters = new ArrayList<>();

        String result = listAllStoriesCommand.execute(parameters);

        Assertions.assertTrue(result.contains("No stories were found!"));
    }
}
