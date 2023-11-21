package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class ChangeStatusOfStory extends CommandImpl{

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;



    public ChangeStatusOfStory(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        int storyID = Integer.parseInt(parameters.get(0));
        String changeStatusOperation = parameters.get(1);
        Story story = getTaskManagementSystemRepository().findStoryById(storyID);
//        Task task = getTaskManagementSystemRepository().findTaskById(storyID);
//        if (!(task instanceof Story)){
//            throw new IllegalArgumentException(Constants.ID_DOES_NOT_BELONG_TO_STORY);
//        }

        switch (changeStatusOperation.toUpperCase()){
            case Constants.OPERATION_ADVANCE:
                return storyAdvanceStatus(storyID, story);
            case Constants.OPERATION_REVERT:
                return storyRevertStatus(storyID, story);
        }
        throw new IllegalArgumentException(Constants.NO_SUCH_CHANGE_STATUS_OPERATION_ERR_MSG);
    }

    private String storyAdvanceStatus(int storyID, Story story){
        String latestStatus = String.valueOf(story.getStatus());

        story.advanceStatus();
        if (latestStatus.equalsIgnoreCase(String.valueOf(Status.DONE))){
            return String.format(Constants.STATUS_IS_ALREADY_SET_TO_DONE_WITH_ID,
                    Constants.STORY, storyID);
        }

        return String.format(Constants.STORY_STATUS_CHANGED_MSG, storyID, latestStatus,
                story.getStatus());
    }

    private String storyRevertStatus(int storyID, Story story){
        String latestStatus = String.valueOf(story.getStatus());

        story.revertStatus();
        if (latestStatus.equalsIgnoreCase(String.valueOf(Status.NOT_DONE))){
            return String.format(Constants.STATUS_IS_ALREADY_SET_TO_NOT_DONE_WITH_ID,
                    Constants.STORY, storyID);
        }

        return String.format(Constants.STORY_STATUS_CHANGED_MSG, storyID, latestStatus,
                story.getStatus());
    }
}
