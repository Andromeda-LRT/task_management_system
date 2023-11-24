package com.company.oop.taskmanagmentsystem.operations;

import com.company.oop.taskmanagementsystem.commands.sort.SortStoryByFields;
import com.company.oop.taskmanagementsystem.commands.contracts.Command;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.TaskManagementSystemRepositoryImpl;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.contracts.Story;
import com.company.oop.taskmanagementsystem.models.enums.Priority;
import com.company.oop.taskmanagementsystem.models.enums.Size;
import com.company.oop.taskmanagmentsystem.constants.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortStoryByFieldsTests {

    private TaskManagementSystemRepository repository;
    private Command command;
    private List<String> parameters;
    private StringBuilder sb;
    private List<Story> storiesForTest;

    @BeforeEach
    public void initSortStoryByTitlePrioritySeverityCommand(){

        repository = new TaskManagementSystemRepositoryImpl();
        command = new SortStoryByFields(repository);
        parameters = new ArrayList<>();
        sb = new StringBuilder();

        repository.createStory("eeeeeeeeee", TestsConstants.VALID_DESCRIPTION,
                Priority.LOW, Size.LARGE);
        repository.createStory("dddddddddd", TestsConstants.VALID_DESCRIPTION,
                Priority.HIGH, Size.SMALL);
        repository.createStory("cccccccccc", TestsConstants.VALID_DESCRIPTION,
                Priority.MEDIUM, Size.SMALL);
        repository.createStory("lllllllllll", TestsConstants.VALID_DESCRIPTION,
                Priority.LOW, Size.MEDIUM);
        repository.createStory("xxxxxxxxxx", TestsConstants.VALID_DESCRIPTION,
                Priority.MEDIUM, Size.LARGE);
        repository.createStory("aaaaaaaaaa", TestsConstants.VALID_DESCRIPTION,
                Priority.HIGH, Size.LARGE);
        repository.createStory("gggggggggg", TestsConstants.VALID_DESCRIPTION,
                Priority.MEDIUM, Size.SMALL);
        repository.createStory("bbbbbbbbbb", TestsConstants.VALID_DESCRIPTION,
                Priority.HIGH, Size.MEDIUM);

        storiesForTest = repository.getStories();
    }

    @Test
    public void SortStoryByTitlePrioritySize_Should_SortByTitle_When_ArgumentsValid(){
        parameters.add("Title");

        sb.append(String.format(TestsConstants.STORY_SORTED_BY_GIVEN_PARAMETER, parameters.get(0)))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        Collections.sort(storiesForTest);

        for (Story story : storiesForTest) {
            sb.append(story.printMainInformation()).append(System.lineSeparator());
        }
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        Assertions.assertEquals(sb.toString(), command.execute(parameters));
        //correct title order should be as follows:
        // a, b, c, d, e, g, l, x
    }

    @Test
    public void SortStoryByTitlePrioritySize_Should_SortByPriority_When_ArgumentsValid(){
        parameters.add("Priority");

        sb.append(String.format(TestsConstants.STORY_SORTED_BY_GIVEN_PARAMETER, parameters.get(0)))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

//        Comparator<Story> compareByPrio = new Comparator<>() {
//            @Override
//            public int compare(Story o1, Story o2) {
//                return o1.getPriority().compareTo(o2.getPriority());
//            }
//        };

        storiesForTest.sort((s1, s2) -> s2.getPriority().compareTo(s1.getPriority()));

        for (Story story : storiesForTest) {
            sb.append(story.printMainInformation()).append(System.lineSeparator());
        }
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        Assertions.assertEquals(sb.toString(), command.execute(parameters));
        // correct prio sort order should be as follows:
        // id 2 high, id 6 high, id 8 high, id 3 med, id 5 med, id 7 med
        // id 1 low, id 4 low
    }

    @Test
    public void SortStoryByTitlePrioritySize_Should_SortBySize_When_ArgumentsValid(){
        parameters.add("Size");

        sb.append(String.format(TestsConstants.STORY_SORTED_BY_GIVEN_PARAMETER, parameters.get(0)))
                .append(System.lineSeparator());
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

//        Comparator<Story> compareBySize = new Comparator<>() {
//            @Override
//            public int compare(Story o1, Story o2) {
//                return o1.getSize().compareTo(o2.getSize());
//            }
//        };

        storiesForTest.sort((s1, s2) -> s2.getSize().compareTo(s1.getSize()));

        for (Story story : storiesForTest) {
            sb.append(story.printMainInformation()).append(System.lineSeparator());
        }
        sb.append(Constants.LINE_DIVISOR).append(System.lineSeparator());

        Assertions.assertEquals(sb.toString(), command.execute(parameters));
        // correct size sort order should be as follows:
        // id 1 large, id 5 large, id 6 large, id 4 med, id 8 med, id 2 small
        // id 3 small, id 4 small
    }

    @Test
    public void SortStoryByTitlePrioritySize_Should_Throw_AnException_When_MissingParameters(){

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }

    @Test
    public void SortStoryByTitlePrioritySize_Should_Throw_AnException_When_ThereAreNoStories(){
        parameters.add("Priority");
        repository = new TaskManagementSystemRepositoryImpl();
        command = new SortStoryByFields(repository);
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
    @Test
    public void SortStoryByTitlePrioritySize_Should_Throw_AnException_When_InvalidOperation(){
        parameters.add("Rating");

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                command.execute(parameters));
    }
}
