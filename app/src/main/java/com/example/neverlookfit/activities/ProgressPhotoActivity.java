package com.example.neverlookfit.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.neverlookfit.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ProgressPhotoActivity extends AppCompatActivity {
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int SEARCH_ACTIVITY_REQUEST_CODE = 0;
    ImageView display;
    String getImgName;
    ImageView leftBtn, rightBtn;
    boolean searchKey = false;
    String returnKeyword, returnLocation;
    private String currentPath;
    private int currentIndex;
    private ArrayList<String> photoGallery;
    private int returnEndDate, returnStartDate;
    private int date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_photo);
        photoGallery = populateGallery();
        Log.d("onCreate, size", Integer.toString(photoGallery.size()));
        currentPath = photoGallery.get(currentIndex);
        displayPhoto(currentPath);

        leftBtn = (ImageView) findViewById(R.id.IVleft);
        rightBtn = (ImageView) findViewById(R.id.IVright);

        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoGallery = populateGallery();
                currentIndex--;
                if (currentIndex < 0)
                    currentIndex = photoGallery.size() - 1;
                updateCurrentPhoto();
            }
        });

        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoGallery = populateGallery();
                currentIndex++;
                if (currentIndex > photoGallery.size() - 1)
                    currentIndex = 0;
                updateCurrentPhoto();
            }
        });
    }

    private void updateCurrentPhoto() {
        currentPath = photoGallery.get(currentIndex);
        displayPhoto(currentPath);
    }

    private ArrayList<String> populateGallery() {
        File directory = new File(Environment.getExternalStorageDirectory().
                getAbsoluteFile(), "/Android/data/com.example.neverlookfit/files/Pictures");
        photoGallery = new ArrayList<String>();
        for (File f : directory.listFiles()) {
            photoGallery.add(f.getPath());
            if (searchKey) {
                String name = f.getName();
                Pattern p = Pattern.compile("_([0-9]*?)_");
                Matcher m = p.matcher(name);

                if ((name.toLowerCase().contains(returnKeyword.toLowerCase())) && !(returnKeyword.isEmpty())) {
                    displayPhoto(f.getPath());
                } else if (m.find()) {
                    date = Integer.parseInt(m.group(1));
                    if ((date >= returnStartDate) && (date <= returnEndDate) && returnEndDate != 0 && returnStartDate != 0) {
                        displayPhoto(f.getPath());
                    }
                }
            }
        }
        return photoGallery;
    }

    private void displayPhoto(String path) {
        display = (ImageView) findViewById(R.id.imageView);
        if (path != null) {
            Bitmap myBitmap = BitmapFactory.decodeFile(path);
            display.setImageBitmap(myBitmap);
        }
    }

    /**
     * Called when the user taps the Camera button
     */
    public void dispatchTakePictureIntent(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.d("FileCreation", "Failed");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.neverlookfit.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
        setResult(RESULT_OK, takePictureIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
                Bitmap myBitmap = BitmapFactory.decodeFile(currentPath);
                display.setImageBitmap(myBitmap);
            }
        }
        if (requestCode == SEARCH_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                searchKey = true;
                if (!(data.getStringExtra("STARTDATE").isEmpty()) && !(data.getStringExtra("ENDDATE").isEmpty())) {
                    returnStartDate = Integer.parseInt(data.getStringExtra("STARTDATE"));
                    returnEndDate = Integer.parseInt(data.getStringExtra("ENDDATE"));
                } else {
                    returnStartDate = 0;
                    returnEndDate = 0;
                }
                returnKeyword = data.getStringExtra("KEYWORD");
                returnLocation = data.getStringExtra("LOCATION");
                photoGallery = populateGallery();
            }
        }

    }

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
        currentPath = image.getAbsolutePath();
        return image;
    }

    public void search(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivityForResult(intent, SEARCH_ACTIVITY_REQUEST_CODE);
    }

}
