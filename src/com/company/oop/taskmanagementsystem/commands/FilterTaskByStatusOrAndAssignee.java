package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;

import java.util.ArrayList;
import java.util.List;

public class FilterTaskByStatusOrAndAssignee extends CommandImpl {
    private static final String NO_TASKS_ERROR = "There are no added tasks.";
    private static final String LISTING_FOR_STATUS_AND_ASSIGNEE = "Tasks with status %s and assignee %s:";
    private static final String LISTING_FOR_STATUS = "Tasks with status %s:";
    private static final String LISTING_FOR_ASSIGNEE = "Tasks with assignee %s:";

    public FilterTaskByStatusOrAndAssignee(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        String result;
        switch (parameters.size()) {
            case 1:
                result = filterForStatusOrAssignee(parameters);
                break;
            case 2:
                result = filterForStatusAndAssignee(parameters);
                break;
            default:
                throw new IllegalArgumentException("Invalid number of arguments. Expected 1 or 2 arguments.");
        }
        return result;
    }

    //todo think about notdone status - Reni
    // todo case sensitive status
    private String filterForStatusAndAssignee(List<String> parameters) {
        Status status = ParsingHelpers.tryParseEnum(parameters.get(0), Status.class);
        String assignee = parameters.get(1);
        if (getTaskManagementSystemRepository().getTasks().isEmpty()) {
            throw new IllegalArgumentException(NO_TASKS_ERROR);
        }

        StringBuilder builder = new StringBuilder();
        builder.append(String.format(LISTING_FOR_STATUS_AND_ASSIGNEE, status.toString(), assignee)).append(System.lineSeparator());
        List<Task> tasks = addBugsAndStoriesToAList();

        for (Task task : tasks) {
            if (task.getStatus().equals(status) && (task instanceof Bug ? ((Bug) task).getAssignee().getName().equals(assignee) :
                    ((Story) task).getAssignee().getName().equals(assignee))) {
                builder.append(task.printMainInformation()).append(System.lineSeparator());
            }
        }
        return builder.toString().trim();
    }

    private String filterForStatusOrAssignee(List<String> parameters) {
        String parameter = parameters.get(0);
        StringBuilder output = new StringBuilder();
        boolean isStatus = false;
        for (Status status : Status.values()) {
            if (status.toString().equals(parameter)) {
                isStatus = true;
                break;
            }
        }
        if (isStatus) {
            output.append(printForStatus(parameter));
        } else {
            output.append(printForAssignee(parameter));
        }

        return output.toString().trim();
    }

    private List<Task> addBugsAndStoriesToAList() {
        List<Bug> bugs = getTaskManagementSystemRepository().findAllBugsInTasks();
        List<Story> stories = getTaskManagementSystemRepository().findAllStoriesInTasks();
        List<Task> tasks = new ArrayList<>();
        tasks.addAll(bugs);
        tasks.addAll(stories);
        return tasks;
    }

    private String printForStatus(String parameter) {
        StringBuilder string = new StringBuilder();
        string.append(String.format(LISTING_FOR_STATUS, parameter)).append(System.lineSeparator());
        List<Task> tasks = addBugsAndStoriesToAList();
        for (Task task : tasks) {
            if (task.getStatus().toString().equalsIgnoreCase(parameter)) {
                string.append(task.printMainInformation()).append(System.lineSeparator());
            }
        }
        return string.toString();
    }

    private String printForAssignee(String parameter) {
        StringBuilder string = new StringBuilder();
        string.append(String.format(LISTING_FOR_ASSIGNEE, parameter)).append(System.lineSeparator());
        List<Task> tasks = addBugsAndStoriesToAList();
        for (Task task : tasks) {
            if ((task instanceof Bug ? ((Bug) task).getAssignee().getName().equals(parameter) : ((Story) task).getAssignee().getName().equals(parameter))) {
                string.append(task.printMainInformation()).append(System.lineSeparator());
            }
        }
        return string.toString().trim();
    }

}
