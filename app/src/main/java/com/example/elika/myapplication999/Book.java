package com.example.elika.myapplication999;

/**
 * Created by elika on 2/28/2018.
 */

public class Book {
    public Book()
    {
    }

    public Book(String author,
             String bookName,
             String genre,
             boolean isAvailable)
    {
        Author = author;
        BookName = bookName;
        Genre = genre;
        IsAvailable = isAvailable;
    }

    public String Author;
    public String BookName;
    public String Genre;
    public boolean IsAvailable;
}
