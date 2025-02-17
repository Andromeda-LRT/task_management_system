package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.constants.Constants;
import com.company.oop.taskmanagementsystem.models.contracts.Comment;
import com.company.oop.taskmanagementsystem.models.contracts.Task;
import com.company.oop.taskmanagementsystem.models.enums.Status;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public abstract class TaskImpl implements Task {
    private static final int TITLE_MIN_LENGTH = 10;
    private static final int TITLE_MAX_LENGTH = 100;
    private final static String COMMENT_HEADER = "--COMMENTS--";
    public static final String NO_COMMENTS_MESSAGE = "--NO COMMENTS--";
    private static final String TITLE_LENGTH_ERR = format(
            "Title must be between %s and %s characters long!",
            TITLE_MIN_LENGTH,
            TITLE_MAX_LENGTH);
    private static final int DESCRIPTION_MIN_LENGTH = 10;
    private static final int DESCRIPTION_MAX_LENGTH = 500;
    private static final String DESCRIPTION_LENGTH_ERR = format(
            "Description must be between %s and %s characters long!",
            DESCRIPTION_MIN_LENGTH,
            DESCRIPTION_MAX_LENGTH);

    private int id;
    private String title;
    private String description;

    private final List<Comment> comments;

    private Status status;


    private final List<LoggerImpl> historyOfChanges = new ArrayList<>();

    public TaskImpl(int id, String title, String description, Status status) {
        setId(id);
        setTitle(title);
        setDescription(description);
        setStatus(status);
        comments = new ArrayList<>();
    }

    protected void logChange(String change) {
        historyOfChanges.add(new LoggerImpl(change));
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public List<LoggerImpl> getHistoryOfChanges() {
        return new ArrayList<>(historyOfChanges);
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setTitle(String title) {
        ValidationHelpers.validateIntRange(title.length(),
                TITLE_MIN_LENGTH,
                TITLE_MAX_LENGTH,
                TITLE_LENGTH_ERR);
        this.title = title;
    }

    private void setDescription(String description) {
        ValidationHelpers.validateIntRange(description.length(),
                DESCRIPTION_MIN_LENGTH,
                DESCRIPTION_MAX_LENGTH,
                DESCRIPTION_LENGTH_ERR);
        this.description = description;
    }

    protected void setStatus(Status status) {
        this.status = status;
    }

//    protected abstract void advanceStatus();
//    protected abstract void revertStatus();

    public void addComment(Comment comment) {
        comments.add(comment);
        logChange(Constants.COMMENT_ADDED + comment.getAuthor());
    }

    @Override
    public String print() {
        StringBuilder output = new StringBuilder();

        if (comments.isEmpty()) {
            output.append(NO_COMMENTS_MESSAGE).append(System.lineSeparator());
        } else {
            output.append(COMMENT_HEADER).append(System.lineSeparator());
            for (Comment comment : comments) {
                output.append(comment.print())
                        .append(System.lineSeparator());
            }
        }
        return output.toString();
    }

    @Override
    public String printMainInformation() {
        return String.format(
                "ID:%d '%s', [%s | %s]",
                getId(),
                getTitle(),
                getStatus(),
                getDescription());
    }

    @Override
    public int compareTo(Task nextTask) {
        return this.title.compareTo(nextTask.getTitle());
    }
}
