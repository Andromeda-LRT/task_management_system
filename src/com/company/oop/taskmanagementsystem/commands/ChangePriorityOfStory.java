package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.StoryImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ChangePriorityOfStory extends CommandImpl {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangePriorityOfStory(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int storyID = Integer.parseInt(parameters.get(0));
        String changePriorityOperation = parameters.get(1);
        Task task = getTaskManagementSystemRepository().findTaskById(storyID);
        if (!(task instanceof Story)){
            throw new IllegalArgumentException(Constants.ID_DOES_NOT_BELONG_TO_STORY);
        }

        switch (changePriorityOperation.toUpperCase()){
            case Constants.OPERATION_INCREASE:
                return storyIncreasePriority(storyID);
            case Constants.OPERATION_LOWER:
                return storyLowerPriority(storyID);
        }
        throw new IllegalArgumentException(Constants.NO_SUCH_CHANGE_PRIORITY_OPERATION_ERR_MSG);
    }

    private String storyIncreasePriority(int storyID){
        Story story = (Story) getTaskManagementSystemRepository().findTaskById(storyID);
        Priority latestPriority = story.getPriority();
        story.increasePriority();
        if (latestPriority == Priority.HIGH){
            return String.format(Constants.PRIORITY_IS_ALREADY_SET_TO_HIGH_WITH_ID,
                    Constants.STORY, storyID);
        }

        return String.format(Constants.STORY_PRIORITY_CHANGED_MSG, storyID, latestPriority,
                story.getPriority());
    }

    private String storyLowerPriority(int storyID){
        Story story = (Story) getTaskManagementSystemRepository().findTaskById(storyID);
        Priority latestPriority = story.getPriority();
        story.lowerPriority();
        if (latestPriority == Priority.LOW){
            return String.format(Constants.PRIORITY_IS_ALREADY_SET_TO_LOW_WITH_ID,
                    Constants.STORY, storyID);
        }

        return String.format(Constants.STORY_PRIORITY_CHANGED_MSG, storyID, latestPriority,
                story.getPriority());
    }
}
