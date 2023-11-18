package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.models.contracts.Comment;
import com.company.oop.taskmanagementsystem.utils.ValidationHelpers;

public class CommentImpl implements Comment {
    private static final int CONTENT_MIN_LENGTH = 1;
    private static final int CONTENT_MAX_LENGTH = 500;
    private static final String CONTENT_LENGTH_ERROR_MESSAGE = "Comment needs to be between 1 and 500 symbols";
    private static final String BLANK_AUTHOR_ERROR_MESSAGE = "Comment must have an author";
    public static final String NEW_LINE_DASH = "----------";
    private String author;
    private String content;
    public CommentImpl(String author, String content){
        setAuthor(author);
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

    private void setAuthor(String author){
        ValidationHelpers.validateEmptyString(author, BLANK_AUTHOR_ERROR_MESSAGE);
        this.author = author;
    }
    private void setContent(String content){
        ValidationHelpers.validateIntRange(content.length(), CONTENT_MIN_LENGTH,
                CONTENT_MAX_LENGTH, CONTENT_LENGTH_ERROR_MESSAGE);
    }

    @Override
    public String print() {
        StringBuilder output = new StringBuilder(NEW_LINE_DASH);
        output.append(System.lineSeparator());
        output.append(String.format("%s", getContent()));
        output.append(System.lineSeparator());
        output.append(String.format("User: %s", getAuthor()));
        output.append(System.lineSeparator());
        output.append(NEW_LINE_DASH);
        return output.toString();

    }
}
