package com.example.elika.myapplication999;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EditUserActivity extends AppCompatActivity {

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
    }

    public void on_search_click(View view) {
        final EditText userNameEditText = (EditText)this.findViewById(R.id.edit_user_user_name);
        //String query = "Users/" + c;
        String query = "Users";
        final DatabaseReference usersQuery = rootRef.child(query);
        final EditText addressEditText = (EditText)this.findViewById(R.id.edit_user_address);
        final EditText cityEditText = (EditText)this.findViewById(R.id.edit_user_city);
        final EditText emailEditText = (EditText)this.findViewById(R.id.edit_user_email);
        final EditText firstNameEditText = (EditText)this.findViewById(R.id.edit_user_first_name);
        final EditText lastNameEditText = (EditText)this.findViewById(R.id.edit_user_last_name);
        final EditText passwordEditText = (EditText)this.findViewById(R.id.edit_user_password);
        final CheckBox isLoanerCheckBox = (CheckBox)this.findViewById(R.id.edit_user_checkBox_type);

        usersQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean result = false;
                Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
                String userName = userNameEditText.getText().toString();
                for (DataSnapshot snapshot : dataSnapshots)
                {
                    User user = snapshot.getValue(User.class);
                    if(user != null && user.UserName.equals(userName))
                    {
                        addressEditText.setText(user.Address);
                        cityEditText.setText(user.City);
                        emailEditText.setText(user.Email);
                        firstNameEditText.setText(user.FirstName);
                        lastNameEditText.setText(user.LastName);
                        passwordEditText.setText(user.Password);
                        isLoanerCheckBox.setChecked(user.UserTypeId == 3);
                        Toast toast = Toast.makeText(EditUserActivity.this,
                                "User was found",Toast.LENGTH_SHORT);
                        toast.show();
                        result = true;
                        break;
                    }
                }
                if(!result)
                {
                    Toast toast = Toast.makeText(EditUserActivity.this,
                            "user not found",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void on_edit_click(View view) {
        final EditText userNameEditText = (EditText)this.findViewById(R.id.edit_user_user_name);
        //String query = "Users/" + c;
        String query = "Users";
        final DatabaseReference usersQuery = rootRef.child(query);
        final EditText addressEditText = (EditText)this.findViewById(R.id.edit_user_address);
        final EditText cityEditText = (EditText)this.findViewById(R.id.edit_user_city);
        final EditText emailEditText = (EditText)this.findViewById(R.id.edit_user_email);
        final EditText firstNameEditText = (EditText)this.findViewById(R.id.edit_user_first_name);
        final EditText lastNameEditText = (EditText)this.findViewById(R.id.edit_user_last_name);
        final EditText passwordEditText = (EditText)this.findViewById(R.id.edit_user_password);
        final CheckBox isLoanerCheckBox = (CheckBox)this.findViewById(R.id.edit_user_checkBox_type);

        usersQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean result = false;
                Iterable<DataSnapshot> dataSnapshots = dataSnapshot.getChildren();
                String userName = userNameEditText.getText().toString();
                for (DataSnapshot snapshot : dataSnapshots)
                {
                    User user = snapshot.getValue(User.class);
                    if(user != null && user.UserName.equals(userName))
                    {
                        user.Address = addressEditText.getText().toString();
                        user.City = cityEditText.getText().toString();
                        user.Email = emailEditText.getText().toString();
                        user.FirstName = firstNameEditText.getText().toString();
                        user.LastName = lastNameEditText.getText().toString();
                        user.Password = passwordEditText.getText().toString();

                        String query = "Users/"+ snapshot.getKey();
                        rootRef.child(query).setValue(user);
                        Toast toast = Toast.makeText(EditUserActivity.this,
                                "User was updated",Toast.LENGTH_SHORT);
                        toast.show();
                        result = true;
                        break;
                    }
                }
                if(!result)
                {
                    Toast toast = Toast.makeText(EditUserActivity.this,
                            "user not found",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
