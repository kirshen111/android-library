package com.example.elika.myapplication999;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddBookActivity extends AppCompatActivity {
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference booksRef = rootRef.child("Books");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
    }

    public void on_book_add(View view) {
        String author = getTextByTextViewId(R.id.add_book_author);
        String bookName= getTextByTextViewId(R.id.add_book_book_name);
        String genere = getTextByTextViewId(R.id.add_book_genre);


        Map<String, Object> books = new HashMap<>();
        books.put(bookName, new Book(
                author,
                bookName,
                genere,
                true));


        booksRef.updateChildren(books);

        Toast toast = Toast.makeText(AddBookActivity.this,
                "A book was added successfully",Toast.LENGTH_SHORT);
        toast.show();
    }

    private String getTextByTextViewId(int viewId)
    {
        EditText textView = (EditText)this.findViewById(viewId);
        String result = textView.getText().toString();
        return result;
    }
}
