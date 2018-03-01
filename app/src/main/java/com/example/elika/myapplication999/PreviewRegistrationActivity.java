package com.example.elika.myapplication999;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PreviewRegistrationActivity extends AppCompatActivity {

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference usersRef = rootRef.child("Users");

    public void on_register_click(View view) {
        final int userTypeId = ((CheckBox)this.findViewById(R.id.registration_preview_checkBox_type)).isChecked() ?
        3 : 2;
        final String userName = getTextBtTextViewId(R.id.preview_registration_user_name);
        final String password = getTextBtTextViewId(R.id.preview_registration_password);
        final String firstName = getTextBtTextViewId(R.id.preview_registration_first_name);
        final String lastName = getTextBtTextViewId(R.id.preview_registration_last_name);
        final String email = getTextBtTextViewId(R.id.preview_registration_email);
        final String city = getTextBtTextViewId(R.id.preview_registration_city);
        final String address = getTextBtTextViewId(R.id.preview_registration_address);

        String query = "Users/" + userName;
        final DatabaseReference usersQuery = rootRef.child(query);

        usersQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if(user != null)
                {
                    Toast toast = Toast.makeText(PreviewRegistrationActivity.this,
                            "User Name Already Exists. Please Choose Other User Name",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                else {

                    Map<String, Object> users = new HashMap<>();
                    users.put(userName, new User(userTypeId, userName,
                            password,
                            lastName,
                            firstName,
                            email,
                            city,
                            address));


                    usersRef.updateChildren(users);

                    Toast toast = Toast.makeText(PreviewRegistrationActivity.this,
                            "Registration succeeded",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private String getTextBtTextViewId(int viewId)
    {
        TextView textView = (TextView)this.findViewById(viewId);
        String result = textView.getText().toString();
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_registration);

        Intent intent = PreviewRegistrationActivity.this.getIntent();
        setViewText(intent.getStringExtra("user_name"),R.id.preview_registration_user_name);
        setViewText(intent.getStringExtra("password"),R.id.preview_registration_password);
        setViewText(intent.getStringExtra("first_name"),R.id.preview_registration_first_name);
        setViewText(intent.getStringExtra("last_name"),R.id.preview_registration_last_name);
        setViewText(intent.getStringExtra("city"),R.id.preview_registration_city);
        setViewText(intent.getStringExtra("address"),R.id.preview_registration_address);
        setViewText(intent.getStringExtra("email"),R.id.preview_registration_email);
        CheckBox isLoanerCheckBox = (CheckBox)this.findViewById(R.id.registration_preview_checkBox_type);
        isLoanerCheckBox.setChecked(intent.getBooleanExtra("isLoaner", true));
        try {
              Bitmap bitMap = null;
              String isBitmap = intent.getStringExtra("isBitmap");
              Boolean b = isBitmap.equals("true");
              if(b)
              {
                  bitMap = (Bitmap) intent.getParcelableExtra("image_uri");
              }
              else {
                  Uri myUri = Uri.parse(intent.getStringExtra("image_uri"));
                  bitMap = getBitmapFromUri(myUri);

              }



            //Bitmap

            ImageView imageView = (ImageView)this.findViewById(R.id.preview_registration_image);
            imageView.setImageBitmap(bitMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setViewText(String value, int fieldId)
    {
        TextView textView = (TextView)this.findViewById(fieldId);
        textView.setText(value);
    }

    private Bitmap getBitmapFromUri(Uri uri) throws Exception{
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }
}
