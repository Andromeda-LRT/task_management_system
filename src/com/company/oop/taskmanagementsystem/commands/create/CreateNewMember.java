package com.company.oop.taskmanagementsystem.commands.create;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.MemberImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class CreateNewMember extends CommandImpl {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final static String MEMBER_REGISTERED = "Member %s registered successfully!";

    public CreateNewMember(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);

        return createMember(name);
    }

    private String createMember(String name) {
        ValidationHelpers.validateNameIsUniqueInMemberTeamBoard(
                name,
                getTaskManagementSystemRepository().getMembers(),
                getTaskManagementSystemRepository().getTeams(),
                getTaskManagementSystemRepository().getBoards()
        );

        Member member = getTaskManagementSystemRepository().createMember(name);

        return String.format(MEMBER_REGISTERED, member.getName());
    }
}
