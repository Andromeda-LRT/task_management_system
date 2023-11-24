package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.add.AddMemberToTeam;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.models.contracts.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddMemberToTeamTests {

    private final TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();

    @Test
    void addMemberToTeam_Should_AddMemberToTeam_When_ValidParametersProvided() {
        List<String> parameters = new ArrayList<>();
        parameters.add("TestTeam");
        parameters.add("TestMember");

        repository.createTeam(parameters.get(0));
        repository.createMember(parameters.get(1));

        AddMemberToTeam addMemberToTeam = new AddMemberToTeam(repository);
        Team team = repository.findTeamByName("TestTeam");
        Member member = repository.findMemberByName("TestMember");

        String result = addMemberToTeam.execute(parameters);

        Assertions.assertTrue(team.getMembers().contains(member));
        Assertions.assertEquals("Member TestMember was added to team TestTeam successfully!", result);
    }

    @Test
    void findMemberByName_Should_ThrowIllegalArgumentException_When_MemberDoesNotExist() {
        List<String> parameters = new ArrayList<>();
        parameters.add("TestTeam");
        parameters.add("NonMember");

        repository.createTeam(parameters.get(0));
        repository.createMember("ValidMem");

        AddMemberToTeam addMemberToTeam = new AddMemberToTeam(repository);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            addMemberToTeam.execute(parameters);
        });
    }
}
