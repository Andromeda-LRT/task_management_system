package com.company.oop.taskmanagementsystem.commands.sort;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortStoryByFields extends CommandImpl {
    private static final String STORY_SORTED_BY_GIVEN_PARAMETER = "---STORY SORTED BY %S---";
    private static final String NO_STORIES_TO_SORT = "There are no added Stories";
    private static final String INVALID_SORT_OPERATION = "%s is not a valid sort operation, " +
            "please input either Title, Priority, or Size";
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    public SortStoryByFields(TaskManagementSystemRepository taskManagementSystemRepository){
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        List<Story> stories = getTaskManagementSystemRepository().getStories();
        if (stories.isEmpty()){
            throw new IllegalArgumentException(NO_STORIES_TO_SORT);
        }
        StringBuilder sb = new StringBuilder();

        switch (parameters.get(0).toUpperCase()){
            case "TITLE":
                return sortStoriesByTitle(sb, parameters.get(0), stories);
            case"PRIORITY":
                return sortStoriesByPriority(sb, parameters.get(0), stories);
            case "SIZE":
                return sortStoriesBySize(sb, parameters.get(0), stories);
        }
        throw new IllegalArgumentException(String.format(INVALID_SORT_OPERATION, parameters.get(0)));
    }

    private String sortStoriesByTitle(StringBuilder sb, String sortOperation, List<Story> stories){

        sb.append(String.format(STORY_SORTED_BY_GIVEN_PARAMETER, sortOperation))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        Collections.sort(stories);

        for (Story story : stories) {
            sb.append(story.printMainInformation()).append(System.lineSeparator());
        }
            sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return sb.toString();
    }

    private String sortStoriesByPriority(StringBuilder sb, String sortOperation, List<Story> stories){

        sb.append(String.format(STORY_SORTED_BY_GIVEN_PARAMETER, sortOperation))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        stories.sort((s1, s2) -> s2.getPriority().compareTo(s1.getPriority()));

        for (Story story : stories) {
            sb.append(story.printMainInformation()).append(System.lineSeparator());
        }
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return sb.toString();
    }
    private String sortStoriesBySize(StringBuilder sb, String sortOperation, List<Story> stories){

        sb.append(String.format(STORY_SORTED_BY_GIVEN_PARAMETER, sortOperation))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        stories.sort((s1, s2) -> s2.getSize().compareTo(s1.getSize()));

        for (Story story : stories) {
            sb.append(story.printMainInformation()).append(System.lineSeparator());
        }
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        return sb.toString();
    }
}
