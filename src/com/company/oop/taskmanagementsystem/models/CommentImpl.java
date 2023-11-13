package com.company.oop.taskmanagementsystem.models;

import com.company.oop.taskmanagementsystem.models.contracts.Comment;

public class CommentImpl implements Comment {

    private String author;
    private String content;
    // todo unsure of parameters order
    public CommentImpl(String author, String content){

    }
    @Override
    public String getAuthor() {
        return null;
    }

    @Override
    public String getContent() {
        return null;
    }
}
