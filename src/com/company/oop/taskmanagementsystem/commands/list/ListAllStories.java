package com.company.oop.taskmanagementsystem.commands.list;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Feedback;
import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ListAllStories extends CommandImpl {
    private static final String ALL_STORIES = "---ALL STORIES---";
    private static final String NO_ADDED_STORIES = "No stories were found!";
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    public ListAllStories(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        return listAllStories();
    }

    private String listAllStories() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Story> stories = getTaskManagementSystemRepository().getStories();

        if (stories.isEmpty()) {
            stringBuilder.append(NO_ADDED_STORIES).append(System.lineSeparator());
            stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());
            return stringBuilder.toString();
        }
        stringBuilder.append(ALL_STORIES).append(System.lineSeparator());
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        for (Story story : stories) {
            stringBuilder.append(story.printMainInformation()).append(System.lineSeparator());
        }
        stringBuilder.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return stringBuilder.toString();
    }
}
