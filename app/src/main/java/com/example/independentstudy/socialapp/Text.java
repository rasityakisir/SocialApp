package com.example.independentstudy.socialapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class Text extends ActionBarActivity{


    MyDBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        final TextView textView = (TextView) findViewById(R.id.textView);
        final EditText editText = (EditText) findViewById(R.id.text_input);
        Button submitBtn = (Button) findViewById(R.id.btn_submit);

        dbHandler = new MyDBHandler(this,null, null, 1);
        printDatabase();


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                //startActivity(intent);

                String inputText = editText.getText().toString();
                dbHandler.deleteProduct(inputText);
                printDatabase();

            }
        });


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {

                    //String messageString=editText.getText().toString();
                   Data messageString = new Data(editText.getText().toString());
                   dbHandler.addata(messageString);
                   printDatabase();
                   handled = true;
                    
                   Intent intent = new Intent (getApplicationContext(), Camera.class);
                   startActivity(intent);
                }
                return handled;
            }
        });
    }

    //Print the database
    public void printDatabase(){

        String dbString = dbHandler.databaseToString();

        final TextView textView = (TextView) findViewById(R.id.textView);
        final EditText editText = (EditText) findViewById(R.id.text_input);

        textView.setText(dbString);
        editText.setText("");
    }

}
