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

public class ReturnBookActivity extends AppCompatActivity {
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference loansRef = rootRef.child("Loans");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_book);
    }

    public void on_return_book(View view) {
        final String bookName = getTextByTextViewId(R.id.return_book_book_name);
        final String loanerName = getTextByTextViewId(R.id.return_book_user_name);

        loansRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
                boolean result = false;
                for (DataSnapshot snapshot :dataSnapshots)
                {
                    Loan l = snapshot.getValue(Loan.class);
                    if(l != null && l.BookName.equals(bookName) && l.UserName.equals(loanerName)&& l.IsBookReturned == false)
                    {
                        String query = "Loans/"+ snapshot.getKey() + "/"+"IsBookReturned";
                        rootRef.child(query).setValue(true);
                        Toast toast = Toast.makeText(ReturnBookActivity.this,
                                "Book was returned successfully",Toast.LENGTH_SHORT);
                        toast.show();
                        result = true;
                        break;
                    }
                }
                if(!result)
                {

                    Toast toast = Toast.makeText(ReturnBookActivity.this,
                            "We couldn't found matching loan with the details you provided",Toast.LENGTH_SHORT);
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
