package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.models.contracts.Comment;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

public class CommentImpl implements Comment {
    private static final int CONTENT_MIN_LENGTH = 1;
    private static final int CONTENT_MAX_LENGTH = 500;
    private static final String CONTENT_LENGTH_ERROR_MESSAGE = "Comment needs to be between 1 and 500 symbols";
    public static final String NEW_LINE_DASH = "----------";
    private String author;
    private String content;
    // todo unsure of parameters order
    public CommentImpl(String author, String content){
        this.author = author;
        setContent(content);
    }
    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public String getContent() {
        return this.content;
    }

    private void setContent(String content){
        ValidationHelpers.validateIntRange(content.length(), CONTENT_MIN_LENGTH,
                CONTENT_MAX_LENGTH, CONTENT_LENGTH_ERROR_MESSAGE);
    }

    @Override
    public String print() {
        StringBuilder output = new StringBuilder(NEW_LINE_DASH).append(System.lineSeparator());
        output.append(String.format("%s", getContent())).append(System.lineSeparator())
                .append(String.format("User: %s", getAuthor())).append(System.lineSeparator())
                .append(NEW_LINE_DASH);
        return output.toString();
    }
}
