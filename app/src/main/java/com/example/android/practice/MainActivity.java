package com.example.android.practice;

import android.animation.ArgbEvaluator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View blueView = findViewById(R.id.blue_triangle);
        final View redView = findViewById(R.id.red_triangle);


        // create a color changer seekbar and set change listener to change color based on progress
        final SeekBar colorChangeSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        colorChangeSeekBar.setMax(255);
        colorChangeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                // change the blue triangle background color according to seekbar progress
                blueView.setBackgroundColor(Color.argb(255, progress, progress, 200));

                // change the red triangle background color according to seekbar progress
                redView.setBackgroundColor(Color.argb(255, 200, progress, progress));
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // this method is triggered when the "more information" is clicked
    public void showInfo(MenuItem item) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // inflating a custom layout for the alert dialog
        builder.setView(getLayoutInflater().inflate(R.layout.alert_dialog, null));


        // sets up the VISIT FORUMS button
        builder.setPositiveButton("VISIT FORUMS", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // the button is clicked
                String url = "https://macdiscussions.udacity.com/t/topic/99751";
                Intent forumPage = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                // checking if the user has a web browser
                if (forumPage.resolveActivity(getPackageManager()) != null) {
                    // the user has a web browser
                    startActivity(forumPage);
                }
                // the user doesn't have a web browser
                else {
                    Toast.makeText(MainActivity.this, "You need a Web browser app to view the forum", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // sets up NOT NOW button
        builder.setNegativeButton("NOT NOW", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

