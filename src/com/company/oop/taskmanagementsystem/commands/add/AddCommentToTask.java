package com.company.oop.taskmanagementsystem.commands.add;

import com.company.oop.taskmanagementsystem.commands.CommandImpl;
import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.core.contracts.TaskManagementSystemRepository;
import com.company.oop.taskmanagementsystem.models.CommentImpl;
import com.company.oop.taskmanagementsystem.models.contracts.Comment;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.utils.ParsingHelpers;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.List;

public class AddCommentToTask extends CommandImpl {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final String COMMENT_ADDED_TO_TASK = "Comment was added successfully to task with id %d.";
    public static final String EXISTING_MEMBER_ERROR = "The author of the comment should be an existing member.";

    public AddCommentToTask(TaskManagementSystemRepository taskManagementSystemRepository) {
        super(taskManagementSystemRepository);
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String description = parameters.get(0);
        String author = parameters.get(1);
        int id = ParsingHelpers.tryParseInt(parameters.get(2), Constants.ID_ERROR_MESSAGE);
        return addComment(description, author, id);
    }

    public String addComment(String description, String author, int id){
        if (!getTaskManagementSystemRepository().memberExist(author)){
            throw new IllegalArgumentException(EXISTING_MEMBER_ERROR);
        }
        Task task = getTaskManagementSystemRepository().findTaskById(id);
        Comment comment = new CommentImpl(author, description);
        task.addComment(comment);
        return String.format(COMMENT_ADDED_TO_TASK, id);
    }
}
