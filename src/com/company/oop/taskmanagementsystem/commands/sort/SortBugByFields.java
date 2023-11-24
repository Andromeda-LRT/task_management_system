package com.company.oop.taskmanagementsystem.commands.sort;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.Collections;
import java.util.List;

public class SortBugByFields extends CommandImpl {
    private static final String BUG_SORTED_BY_GIVEN_PARAMETER = "---BUG SORTED BY %S---";
    private static final String NO_BUGS_TO_SORT = "There are no added Bugs";
    private static final String INVALID_SORT_OPERATION = "%s is not a valid sort operation, " +
            "please input either Title, Priority, or Severity";
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public SortBugByFields(TaskManagementSystemRepository taskManagementSystemRepository){
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        List<Bug> bugs = getTaskManagementSystemRepository().getBugs();
        if (bugs.isEmpty()){
            throw new IllegalArgumentException(NO_BUGS_TO_SORT);
        }
        StringBuilder sb = new StringBuilder();

        switch (parameters.get(0).toUpperCase()){
            case "TITLE":
                return sortBugsByTitle(sb, parameters.get(0), bugs);
            case"PRIORITY":
                return sortBugsByPriority(sb, parameters.get(0), bugs);
            case "SEVERITY":
                return sortBugsBySeverity(sb, parameters.get(0), bugs);
        }
        throw new IllegalArgumentException(String.format(INVALID_SORT_OPERATION, parameters.get(0)));
    }

    private String sortBugsByTitle(StringBuilder sb, String sortOperation, List<Bug> bugs){

        sb.append(String.format(BUG_SORTED_BY_GIVEN_PARAMETER, sortOperation))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        Collections.sort(bugs);

        for (Bug bug : bugs) {
            sb.append(bug.printMainInformation()).append(System.lineSeparator());
        }
            sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return sb.toString();
    }

    private String sortBugsByPriority(StringBuilder sb, String sortOperation, List<Bug> bugs){

        sb.append(String.format(BUG_SORTED_BY_GIVEN_PARAMETER, sortOperation))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());


        bugs.sort((b1, b2) -> b2.getPriority().compareTo(b1.getPriority()));

        for (Bug bug : bugs) {
            sb.append(bug.printMainInformation()).append(System.lineSeparator());
        }
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return sb.toString();
    }
    private String sortBugsBySeverity(StringBuilder sb, String sortOperation, List<Bug> bugs){


        sb.append(String.format(BUG_SORTED_BY_GIVEN_PARAMETER, sortOperation))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());


        bugs.sort((b1, b2) -> b1.getSeverity().compareTo(b2.getSeverity()));

        for (Bug bug : bugs) {
            sb.append(bug.printMainInformation()).append(System.lineSeparator());
        }
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return sb.toString();
    }
}
