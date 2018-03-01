package com.example.elika.myapplication999;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class LoanBookActivity extends AppCompatActivity {
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference loansRef = rootRef.child("Loans");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_book);
    }

    public void on_loan_book(View view) {
        final String bookName = getTextByTextViewId(R.id.loan_book_book_name);
        final String loanerName = getTextByTextViewId(R.id.loan_book_user_name);


        loansRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
                boolean result = true;
                for (DataSnapshot snapshot :dataSnapshots)
                {
                    Loan l = snapshot.getValue(Loan.class);
                    if(l != null && l.BookName.equals(bookName) && l.IsBookReturned == false)
                    {
                        Toast toast = Toast.makeText(LoanBookActivity.this,
                                "Book is unavailable, please choose other book",Toast.LENGTH_SHORT);
                        toast.show();
                        result = false;
                        break;
                    }
                    else if(l != null && l.UserName.equals(loanerName) && l.IsBookReturned == false)
                    {
                        Toast toast = Toast.makeText(LoanBookActivity.this,
                                "Username has loaned a book alrecady. Please tell loaner to return the book before taking another book",
                                Toast.LENGTH_SHORT);
                        toast.show();
                        result = false;
                        break;
                    }
                }
                if(result == true)
                {
                    loansRef.push().setValue(new Loan(bookName, loanerName, false));
                    Toast toast = Toast.makeText(LoanBookActivity.this,
                            "Loaned was finished successfully.",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private String getTextByTextViewId(int viewId)
    {
        EditText textView = (EditText)this.findViewById(viewId);
        String result = textView.getText().toString();
        return result;
    }
}
