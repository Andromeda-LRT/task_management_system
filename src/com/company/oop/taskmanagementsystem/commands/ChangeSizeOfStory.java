package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Size;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ChangeSizeOfStory extends CommandImpl {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeSizeOfStory(TaskManagementSystemRepository taskManagementSystemRepository) {
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
                return storyIncreaseSize(storyID);
            case Constants.OPERATION_DECREASE:
                return storyDecreaseSize(storyID);
        }
        throw new IllegalArgumentException(Constants.NO_SUCH_CHANGE_PRIORITY_OPERATION_ERR_MSG);
    }

    private String storyIncreaseSize(int storyID){
        Story story = (Story) getTaskManagementSystemRepository().findTaskById(storyID);
        Size latestSize = story.getSize();
        story.increaseSize();
        if (latestSize == Size.LARGE){
            return String.format(Constants.SIZE_ALREADY_SET_TO_LARGE_WITH_ID, storyID);
        }

        return String.format(Constants.STORY_SIZE_CHANGED_MSG, storyID, latestSize,
                story.getSize());
    }

    private String storyDecreaseSize(int storyID){
        Story story = (Story) getTaskManagementSystemRepository().findTaskById(storyID);
        Size latestSize = story.getSize();
        story.decreaseSize();
        if (latestSize == Size.SMALL){
            return String.format(Constants.SIZE_ALREADY_SET_TO_SMALL_WITH_ID, storyID);
        }

        return String.format(Constants.STORY_SIZE_CHANGED_MSG, storyID, latestSize,
                story.getSize());
    }
}
