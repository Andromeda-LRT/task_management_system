package com.company.oop.taskmanagementsystem.commands;

import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Bug;
import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;

import java.util.List;

public class FilterTaskWithAssigneeByStatusAndOrAssignee extends CommandImpl {
    private static final String NO_TASKS_ERROR = "There are no added tasks.";
    private static final String LISTING_FOR_STATUS_AND_ASSIGNEE = "Tasks with status %s and assignee %s:";
    private static final String LISTING_FOR_STATUS = "Tasks with status %s:";
    private static final String LISTING_FOR_ASSIGNEE = "Tasks with assignee %s:";

    public FilterTaskWithAssigneeByStatusAndOrAssignee(TaskManagementSystemRepository taskManagementSystemRepository) {
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

    private String filterForStatusAndAssignee(List<String> parameters) {
        Status status = ParsingHelpers.tryParseEnum(parameters.get(0), Status.class);
        String assignee = parameters.get(1);
        if (getTaskManagementSystemRepository().getTasks().isEmpty()) {
            throw new IllegalArgumentException(NO_TASKS_ERROR);
        }

        StringBuilder builder = new StringBuilder();
        builder.append(String.format(LISTING_FOR_STATUS_AND_ASSIGNEE, status.toString(), assignee))
                .append(System.lineSeparator());
        List<Bug> bugs = getTaskManagementSystemRepository().getBugs();
        List<Story> stories = getTaskManagementSystemRepository().getStories();
        if (!bugs.isEmpty()) {
            for (Bug bug : bugs) {
                if (bug.getStatus().equals(status) && bug.getAssignee().getName().equals(assignee)) {
                    builder.append(bug.printMainInformation()).append(System.lineSeparator());
                }
            }
        }
        if (!stories.isEmpty()) {
            for (Story story : stories) {
                if (story.getStatus().equals(status) && story.getAssignee().getName().equals(assignee)) {
                    builder.append(story.printMainInformation()).append(System.lineSeparator());
                }
            }
        }
        return builder.toString().trim();
    }

    private String filterForStatusOrAssignee(List<String> parameters) {
        String parameter = parameters.get(0);
        StringBuilder output = new StringBuilder();
        boolean isStatus = false;
        for (Status status : Status.values()) {
            if ((status.toString().replace(" ", "_")).equalsIgnoreCase(parameter)) {
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

    private String printForStatus(String parameter) {
        StringBuilder string = new StringBuilder();
        string.append(String.format(LISTING_FOR_STATUS, parameter))
                .append(System.lineSeparator());
        List<Bug> bugs = getTaskManagementSystemRepository().getBugs();
        List<Story> stories = getTaskManagementSystemRepository().getStories();
        if (!bugs.isEmpty()) {
            for (Bug bug : bugs) {
                if ((bug.getStatus().toString().replace(" ", "_")).equalsIgnoreCase(parameter) &&
                        bug.getAssignee().getName() != "NO ASSIGNEE") {
                    string.append(bug.printMainInformation()).append(System.lineSeparator());
                }
            }
        }
        if (!stories.isEmpty()) {
            for (Story story : stories) {
                if ((story.getStatus().toString().replace(" ", "_")).equalsIgnoreCase(parameter) &&
                        story.getAssignee().getName() != "NO ASSIGNEE") {
                    string.append(story.printMainInformation()).append(System.lineSeparator());
                }
            }
        }

        return string.toString().trim();
    }


    private String printForAssignee(String parameter) {
        StringBuilder string = new StringBuilder();
        string.append(String.format(LISTING_FOR_ASSIGNEE, parameter))
                .append(System.lineSeparator());
        List<Bug> bugs = getTaskManagementSystemRepository().findAllBugsInTasks();
        List<Story> stories = getTaskManagementSystemRepository().findAllStoriesInTasks();
        if (!bugs.isEmpty()) {
            for (Bug bug : bugs) {
                if (bug.getAssignee().getName().equals(parameter)) {
                    string.append(bug.printMainInformation()).append(System.lineSeparator());
                }
            }
        }
        if (!stories.isEmpty()) {
            for (Story story : stories) {
                if (story.getAssignee().getName().equals(parameter)) {
                    string.append(story.printMainInformation()).append(System.lineSeparator());
                }
            }
        }
        return string.toString().trim();
    }
}