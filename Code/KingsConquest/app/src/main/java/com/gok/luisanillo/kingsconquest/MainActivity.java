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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the @string/NewGame Button
     * Function : Start a new game */
    public void newGame(View view) {

        // Dialog to ask for hero's name
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

    /** Called when the user taps the @string/loadGame Button
     * Function : Load a previous game */
    public void loadGame(View view) {

        // To be implemented...

    }

    /** Called when the user taps the @string/help Button
     * Function : Show help menu */
    public void help(View view) {

        // Open activity that shows the help activity
        Intent intent = new Intent(this, HelpMenu.class);
        startActivity(intent);

    }

    /** Called when the user taps the @string/credits Button
     * Function : Show credits */
    public void credits(View view) {

        // Open activity that shows the credits activity
        Intent intent = new Intent(this, CreditsMenu.class);
        startActivity(intent);
    }

    /** Called when the user taps the @string/quit Button
     *  Function : Quit the application */
    public void quit(View view) {


        // Create a dialog for the option to quit
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
