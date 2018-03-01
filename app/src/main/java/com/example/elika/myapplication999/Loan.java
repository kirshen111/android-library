package com.example.elika.myapplication999;

/**
 * Created by elika on 3/1/2018.
 */

public class Loan {
    String BookName;
    String UserName;
    boolean IsBookReturned;

    public Loan()
    {

    }

    public Loan(String bookName,
            String userName,
            boolean isBookReturned)
    {
        BookName = bookName;
        UserName = userName;
        IsBookReturned = isBookReturned;
    }
}
