package com.gok.luisanillo.kingsconquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        EditText editText = (EditText) findViewById(R.id.helptext);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

    /** Called when the user taps the @string/credits Button */
    public void credits(View view) {
        // Show credits

    }

    /** Called when the user taps the @string/quit Button */
    public void quit(View view) {
        // Quit the application

    }
}
