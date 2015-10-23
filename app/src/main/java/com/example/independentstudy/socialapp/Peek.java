package com.example.independentstudy.socialapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by rasityakisir on 3/31/15.
 */
public class Peek  extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peek);

        Button btnHome = (Button)findViewById(R.id.btn_home);

        Button btnPeek = (Button)findViewById(R.id.btn_peek);
        ImageButton btnCamera = (ImageButton)findViewById(R.id.img_Camera);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), Camera.class);
                startActivity(intent);
            }
        });


    }

}
