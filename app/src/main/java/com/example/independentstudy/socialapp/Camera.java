package com.example.independentstudy.socialapp;

import java.util.*;
import android.annotation.TargetApi;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.io.ByteArrayOutputStream;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.ListView;
import android.content.ContentValues;


public class Camera extends Activity {


    private static final int ACTION_TAKE_VIDEO = 3;
    private static final int SELECTED_PICTURE = 1;
    static final int REQUEST_VIDEO_CAPTURE = 1;

    ArrayList<Picture> imageArry = new ArrayList<Picture>();
    ContactImageAdapter adapter;


    VideoView myvideoView;
    ImageView iv;
    MyDBHandler dbHandler;
    MyPictureDB dbPicture;
    MyVideoDB   dbVideo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        iv = (ImageView) findViewById(R.id.imageView);

        Button btnImage = (Button)findViewById(R.id.btn_gallery);
        Button btnText = (Button)findViewById(R.id.btn_text);
        Button vidBtn = (Button) findViewById(R.id.btn_record);
        EditText editText = (EditText) findViewById(R.id.text_input);


        dbHandler = new MyDBHandler(this,null, null, 1);
        dbPicture = new MyPictureDB(this);

        printDatabase();

        List<Picture> pictures = dbPicture.getAllPictures();
        //Toast.makeText(this, "num pics " + pictures.size(), Toast.LENGTH_SHORT).show();


        ImageView dataList = (ImageView) findViewById(R.id.imageView);
        dataList.setImageURI(Uri.parse(String.valueOf(pictures)));

        //ByteArrayOutputStream stream = new ByteArrayOutputStream();
        //byte imageInByte[] = stream.toByteArray();
        //dbPicture.deletePicture(new Picture("Picture", imageInByte));


        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, SELECTED_PICTURE);
            }
        });

        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), Text.class);
                startActivity(intent);
            }
        });

        vidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(takeVideoIntent, ACTION_TAKE_VIDEO );
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case SELECTED_PICTURE:
                if(resultCode==RESULT_OK){
                    Uri uri=data.getData();
                    String[]projection={MediaStore.Images.Media.DATA};

                    Cursor cursor=getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex=cursor.getColumnIndex(projection[0]);
                    String filePath=cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap yourSelectedImage=BitmapFactory.decodeFile(filePath);
                    Drawable d=new BitmapDrawable(yourSelectedImage);

                    iv.setBackground(d);

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    yourSelectedImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte imageInByte[] = stream.toByteArray();

                    dbPicture.addPicture(new Picture("Picture", imageInByte));
                    Toast.makeText(this, "inserted successfully", Toast.LENGTH_SHORT).show();

                    List<Picture> pictures = dbPicture.getAllPictures();
                    //Toast.makeText(this, "num pics" + pictures.size(), Toast.LENGTH_SHORT).show();
                    for (Picture cn : pictures) {
                        String log = "ID:" + cn.getID() + " Name: " + cn.getName()
                                + " ,Image: " + cn.getImage();

                        // Writing Contacts to log
                        Log.d("Result: ", log);
                        //add contacts data in arrayList
                        imageArry.add(cn);

                    }
                    //adapter = new ContactImageAdapter(this, R.layout.camera, imageArry);
                    ImageView dataList = (ImageView) findViewById(R.id.imageView);
                    dataList.setImageURI(Uri.parse(String.valueOf(imageArry)));
                }
                break;

            default:
                break;
        }
    }

    //Print the database
    public void printDatabase(){

        String dbString = dbHandler.databaseToString();
        //String dvRecord = dbVideo.databaseToString();

        final TextView textView   = (TextView) findViewById(R.id.textView);
        //final ImageView imageView = (ImageView)findViewById(R.id.imageView);
        //final VideoView videoView = (VideoView)findViewById(R.id.myVideoView);


        textView.setText(dbString);
        //videoView.setVideoURI(Uri.parse(dvRecord));


    }

}
