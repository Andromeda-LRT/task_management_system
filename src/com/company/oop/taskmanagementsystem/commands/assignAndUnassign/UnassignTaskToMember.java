package com.company.oop.taskmanagementsystem.commands.assignAndUnassign;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.MemberImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class UnassignTaskToMember extends CommandImpl {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private static final String ID_ERROR = "The first parameter should be a number.";
    private static final String TASK_UNASSIGNED = "Task with id %d was unassigned from %s";

    public UnassignTaskToMember(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(parameters.get(0), ID_ERROR);
        String name = parameters.get(1);
        unAssignTaskToMember(id, name);
        return String.format(TASK_UNASSIGNED, id, name);
    }

    private void unAssignTaskToMember(int id, String name){
        Member member = getTaskManagementSystemRepository().findMemberByName(name);
        Member memberNew = new MemberImpl("NO ASSIGNEE");
        Task task = getTaskManagementSystemRepository().findTaskById(id);
        if (getTaskManagementSystemRepository().getBugs().contains(task)) {
            member.unAssignTask(getTaskManagementSystemRepository().findTaskById(id));
            getTaskManagementSystemRepository().findBugByID(id).changeAssignee(memberNew);
        } else if (getTaskManagementSystemRepository().getStories().contains(task)) {
            member.unAssignTask(getTaskManagementSystemRepository().findTaskById(id));
            getTaskManagementSystemRepository().findStoryById(id).changeAssignee(memberNew);
        } else {
        throw new IllegalArgumentException("There is no such task for " + member.getName());
    }
    }
}
