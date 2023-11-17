package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.models.contracts.Team;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class AddMemberToTeam extends CommandImpl {
    private static final String MEMBER_ADDED_TO_TEAM = "Member %s was added to team %s successfully!";

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public AddMemberToTeam(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String teamName = parameters.get(0);
        String memberName = parameters.get(1);

        return addMemberToTeam(teamName, memberName);
    }

    private String addMemberToTeam(String teamName, String memberName) {
        Team team = getTaskManagementSystemRepository().findTeamByName(teamName);
        Member member = getTaskManagementSystemRepository().findMemberByName(memberName);

        team.addMember(member);

        return String.format(MEMBER_ADDED_TO_TEAM, memberName, teamName);
    }
}
