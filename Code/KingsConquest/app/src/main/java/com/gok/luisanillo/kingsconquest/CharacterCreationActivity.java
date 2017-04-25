package com.gok.luisanillo.kingsconquest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class CharacterCreationActivity extends AppCompatActivity {

    // Objects
    ViewPager viewPager;
    CustomSwipeAdapter adapter;

    /** Here, we instantiate the ViewPager and SwipeAdapter modules for swipping heroes */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creation);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        adapter = new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);

    }

    /** Called when back button is pressed
     * Function : Closes the activity */
    @Override
    public void onBackPressed() {

        // Create dialog to tell user if he is sure he wants to go back
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Do you really want to quit character creation?")
                .setNegativeButton("No",null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Quit the CharacterCreationActivity and go back to MainActivity
                        finish();
                    }

                }).create().show();

    }

    /** Called to see what hero type we have (see Constant Class)
     *
     * @return Hero's type position
     */
    public int getHeroType() {

        return viewPager.getCurrentItem();

    }

    /** Called to see what hero name we have (see Constant Class)
     *
     * @return Hero's name
     */
    public String getHeroName() {

        EditText heroName = (EditText) findViewById(R.id.hero_name);
        return heroName.getText().toString();

    }


    /** Called when front_arrow is clicked
     *
     */

    public void movingForward(View view) {

        int test;
        String test1;
        test = getHeroType();
        test1 = getHeroName();

        Log.i("DEBUG_TAG", "Hero Type:" + test);
        Log.i("DEBUG_TAG1", "Hero Name:" + test1);

    }

}
