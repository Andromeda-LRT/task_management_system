package com.company.oop.taskmanagementsystem.utils;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.contracts.Board;
import com.company.oop.taskmanagementsystem.models.contracts.Member;
import com.company.oop.taskmanagementsystem.models.contracts.Team;

import java.util.List;

public class ValidationHelpers {

    public static final String INVALID_NUM_OF_ARGS_ERR_MSG = "Invalid number of arguments. Expected %d received %d.";

    public static void validateIntRange(int value, int min, int max, String message) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateEmptyString(String value, String message) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateIntIsNotNegative(int value, String message) {
        if (value < 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void validateNameIsUniqueInMemberTeamBoard(String name,
                                                             List<Member> members,
                                                             List<Team> teams,
                                                             List<Board> boards){
        for (Member member:members) {
            if (member.getName().equals(name)){
                throw new IllegalArgumentException(String.format(Constants.NOT_UNIQUE, name));
            }
        }
        for (Team team: teams) {
            if (team.getName().equals(name)){
                throw new IllegalArgumentException(String.format(Constants.NOT_UNIQUE, name));
            }
        }
        for (Board board: boards) {
            if (board.getName().equals(name)){
                throw new IllegalArgumentException(String.format(Constants.NOT_UNIQUE, name));
            }
        }
    }
    public static void validateArgumentsCount(List<String> parameters, int expectedNumberOfParameters){
        if (parameters.size() != expectedNumberOfParameters){
            throw new IllegalArgumentException(
                    String.format(INVALID_NUM_OF_ARGS_ERR_MSG, expectedNumberOfParameters, parameters.size())
            );
        }
    }
}
