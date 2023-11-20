package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.contracts.Prioritizeable;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortBugByTitlePrioritySeverity extends CommandImpl {
    private static final String BUG_SORTED_BY_GIVEN_PARAMETER = "---BUG SORTED BY %S---";
    private static final String NO_BUGS_TO_SORT = "There are no added Bugs";
    private static final String INVALID_SORT_OPERATION = "%s is not a valid sort operation, " +
            "please input either Title, Priority, or Severity";
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public SortBugByTitlePrioritySeverity(TaskManagementSystemRepository taskManagementSystemRepository){
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        List<Bug> bugs = getTaskManagementSystemRepository().findAllBugsInTasks();
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
//        Comparator<Bug> compareByPrio = new Comparator<>() {
//            @Override
//            public int compare(Bug o1, Bug o2) {
//                return o1.getPriority().compareTo(o2.getPriority());
//            }
//        };
        sb.append(String.format(BUG_SORTED_BY_GIVEN_PARAMETER, sortOperation))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        //bugs.sort(compareByPrio.reversed());
        bugs.sort((b1, b2) -> b2.getPriority().compareTo(b1.getPriority()));

        for (Bug bug : bugs) {
            sb.append(bug.printMainInformation()).append(System.lineSeparator());
        }
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return sb.toString();
    }
    private String sortBugsBySeverity(StringBuilder sb, String sortOperation, List<Bug> bugs){
        // the idea behind anonymous comparator is to use it based on the concrete interface or class
        // then to implement the compare logic that we want for this class as a local class within method
//        Comparator<Bug> compareBySeverity = new Comparator<>() {
//            @Override
//            public int compare(Bug o1, Bug o2) {
//                return o1.getSeverity().compareTo(o2.getSeverity());
//            }
//        };

        sb.append(String.format(BUG_SORTED_BY_GIVEN_PARAMETER, sortOperation))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

       // bugs.sort(compareBySeverity);
        bugs.sort((b1, b2) -> b1.getSeverity().compareTo(b2.getSeverity()));

        for (Bug bug : bugs) {
            sb.append(bug.printMainInformation()).append(System.lineSeparator());
        }
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return sb.toString();
    }
}
