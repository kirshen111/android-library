package com.example.elika.myapplication999;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Intent.EXTRA_MIME_TYPES;

public class RegistrationActivity extends AppCompatActivity {
    private Uri imageUri = null;
    private Uri photoURI = null;
    private Bitmap bitMap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void on_preview_click(View view) {
        Intent registrationIntent = new Intent(RegistrationActivity.this,
                PreviewRegistrationActivity.class);
        boolean isFieldsValid = putExtraData(registrationIntent,"user_name", R.id.registration_user_name) &&
        putExtraData(registrationIntent,"password", R.id.registration_password) &&
        putExtraData(registrationIntent,"first_name", R.id.registration_first_name) &&
        putExtraData(registrationIntent,"last_name", R.id.registration_last_name) &&
        putExtraData(registrationIntent,"city", R.id.registration_city) &&
        putExtraData(registrationIntent,"address", R.id.registration_address) &&
        putExtraData(registrationIntent,"email", R.id.registration_email);
        CheckBox isloanerCheckBox = (CheckBox) this.findViewById( R.id.registration_preview_checkBox_type);
        boolean isLoaner = isloanerCheckBox.isChecked();
        registrationIntent.putExtra("isLoaner", isLoaner);
        if(isFieldsValid == false)
        {
            Toast toast = Toast.makeText(RegistrationActivity.this,
                    "Please make sure you filled the form",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if(imageUri == null) {
            registrationIntent.putExtra("isBitmap", "true");
            registrationIntent.putExtra("image_uri", bitMap);
        }
        else {
            registrationIntent.putExtra("isBitmap", "false");
            registrationIntent.putExtra("image_uri", imageUri.toString());
        }
            //

      //  }
       // else
        //{
       //     registrationIntent.putExtra("image_uri", imageUri.toString());
       // }
        startActivity(registrationIntent);
    }

    private boolean putExtraData(Intent intent, String key, int viewId)
    {
        Boolean isFieldValid = true;
        EditText editText = (EditText)this.findViewById(viewId);
        String text = editText.getText().toString();
        if(text == null || text.equals(""))
        {
            isFieldValid = false;
        }
        intent.putExtra(key, text);
        return isFieldValid;
    }
   // public void on_upload_image(View view) {
    //    dispatchTakePictureIntent();


        //Bitmap myBitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);

        //ImageView myImage = (ImageView) findViewById(R.id.registration_image);

       //myImage.setImageBitmap(myBitmap);
    //}
       private static final String[] ACCEPT_MIME_TYPES = {
               "image/png"
       };
    public void on_upload_image(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        intent.addCategory(Intent.CATEGORY_OPENABLE);

        intent.setType("image/jpeg");
        intent.putExtra(EXTRA_MIME_TYPES, ACCEPT_MIME_TYPES);

        startActivityForResult(intent, 7);

    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {


        if (resultCode == Activity.RESULT_OK) {
            if (resultData != null) {
                imageUri = resultData.getData();
                try {
                    Bitmap bitMap = getBitmapFromUri(imageUri);
                    ImageView imageView = (ImageView)this.findViewById(R.id.registration_image);
                    imageView.setImageBitmap(bitMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            }
        }
    }*/

    private Bitmap getBitmapFromUri(Uri uri) throws Exception{
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }
    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            //...
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                /*Uri*/ photoURI = Uri.fromFile(photoFile);
                //Uri photoURI = FileProvider.getUriForFile(this,
                 //       "com.example.elika.myapplication999",
                   //     photoFile);

                //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

            }
        }
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent1() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //photoURI = data.getData();
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView myImage = (ImageView) findViewById(R.id.registration_image);

            myImage.setImageBitmap(imageBitmap);
            bitMap = imageBitmap;

            //photoURI = Uri.fromFile();
            imageUri = null;
        }

        else if (resultCode == RESULT_OK) {
            imageUri = data.getData();
            Bitmap imageBitmap = null;
            try {
                imageBitmap = getBitmapFromUri(imageUri);
                Toast toast = Toast.makeText(RegistrationActivity.this,
                        "An image was added successfully",Toast.LENGTH_SHORT);
                toast.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ImageView myImage = (ImageView) findViewById(R.id.registration_image);

            myImage.setImageBitmap(imageBitmap);
            //bitMap = imageBitmap;
            photoURI = null;
        }
    }

    public void on_taking_picture(View view) {
        dispatchTakePictureIntent();
    }

    private Bitmap compressImage(Bitmap bitmap)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG,50,stream);

        byte[] byteArray = stream.toByteArray();
        Bitmap compressedBitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        return compressedBitmap;
    }

    /*private void ShareImage(Intent intent) {
        try {
            URL url = new URL(mImageUrl);
            Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            intent.putExtra(Intent.EXTRA_STREAM, getImageUri(mActivity, image));
        } catch (Exception e) {
            e.printStackTrace();
        }
        startActivityForResult(Intent.createChooser(intent, 1001));
    }*/

    private Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
