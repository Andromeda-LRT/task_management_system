package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Feedback;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class AssignTaskToMember extends CommandImpl {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String ID_ERROR = "The first parameter should be a number.";
    private static final String TASK_ASSIGNED = "Task with id %d was assigned to %s";

    public AssignTaskToMember(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), ID_ERROR);
        String name = parameters.get(1);
        assignTaskToMember(id, name);
        return String.format(TASK_ASSIGNED, id, name);
    }

    private void assignTaskToMember(int id, String name) {
        Member member = getTaskManagementSystemRepository().findMemberByName(name);
        Task task = getTaskManagementSystemRepository().findTaskById(id);

        if (getTaskManagementSystemRepository().getBugs().contains(task)) {
            member.assignTask(getTaskManagementSystemRepository().findTaskById(id));
            getTaskManagementSystemRepository().findBugById(id).changeAssignee(member);
        } else if (getTaskManagementSystemRepository().getStories().contains(task)) {
            member.assignTask(getTaskManagementSystemRepository().findTaskById(id));
            getTaskManagementSystemRepository().findStoryById(id).changeAssignee(member);
        } else if (getTaskManagementSystemRepository().getFeedback().contains(task)){
            throw new IllegalArgumentException("A Feedback cannot be assigned to a member.");
        }
        else {
            throw new IllegalArgumentException("There is no task with the provided ID.");
        }
    }
}
