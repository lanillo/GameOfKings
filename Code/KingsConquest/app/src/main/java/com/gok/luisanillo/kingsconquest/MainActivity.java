package com.gok.luisanillo.kingsconquest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    /** Constants */
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the @string/NewGame Button */
    public void newGame(View view) {
        // Start a new game

    }

    /** Called when the user taps the @string/loadGame Button */
    public void loadGame(View view) {
        // Load a previous game

    }

    /** Called when the user taps the @string/help Button */
    public void help(View view) {
        // Show help menu
        Intent intent = new Intent(this, HelpMenu.class);
        startActivity(intent);

    }

    /** Called when the user taps the @string/credits Button */
    public void credits(View view) {
        // Show credits
        Intent intent = new Intent(this, CreditsMenu.class);
        startActivity(intent);
    }

    /** Called when the user taps the @string/quit Button */
    public void quit(View view) {
        // Quit the application
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Do you really want to exit?")
                .setNegativeButton("No",null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Quit the application
                        System.exit(0);
                        //Log.i("DEBUG_TAG", "User pressed yes.");
                    }

                }).create().show();

    }
}
