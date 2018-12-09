package com.example.annajin.bookrental;

public class Book {

    String author;
    String title;
    String condition;
    String Borrower;

    // right click, generate, constructor, choose both
    public Book(String author, String title, String condition, String borrower) {
        this.author = author;
        this.title = title;
        this.condition = condition;
        this.Borrower = borrower;
    }

    public Book () {
    }
}
