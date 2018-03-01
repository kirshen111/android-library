package com.example.elika.myapplication999;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void on_login_click(View view) {
        EditText passwordEditText = (EditText)this.findViewById(R.id.login_password);
        EditText userNameEditText = (EditText)this.findViewById(R.id.login_user_name);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        final String enteredPassword = passwordEditText.getText().toString();
        String userName = userNameEditText.getText().toString();
        if(userName.equals(""))
        {
            userName = "errorNoText";
        }
        String query = "Users/" + userName;
        final DatabaseReference usersQuery = rootRef.child(query);

        usersQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if(user != null && user.Password.equals(enteredPassword))
                {
                    Toast toast = Toast.makeText(LoginActivity.this,
                            "Login Succeeded",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    Toast toast = Toast.makeText(LoginActivity.this,
                            "Login Failed",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
