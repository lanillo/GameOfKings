package com.gok.luisanillo.kingsconquest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class CreditsMenu extends AppCompatActivity {

    /** Called when Credits on MainActivity is pressed *
     * Function : Creates the activity */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_menu);
    }

    /** Called when back button is pressed
     * Function : Closes the activity */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    /** Called when the back arrow is pressed
     * Function : Returns to main menu and closes activity */
    public void back(View view) {
        this.finish();
    }


}
