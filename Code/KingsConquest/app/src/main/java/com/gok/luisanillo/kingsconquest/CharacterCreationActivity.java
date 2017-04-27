package com.gok.luisanillo.kingsconquest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class CharacterCreationActivity extends AppCompatActivity {

    // Objects
    ViewPager viewPager;
    CustomSwipeAdapter adapter;

    final Context context = this;
    private ImageView view;

    /** Here, we instantiate the ViewPager and SwipeAdapter modules for swipping heroes */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creation);

        // Initialize pager
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        adapter = new CustomSwipeAdapter(this);
        viewPager.setAdapter(adapter);

        view = (ImageView) findViewById(R.id.front_arrow);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create dialog and title
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.distribute_attributes);
                dialog.setTitle(R.string.DistributeAttributesTitle);

                // set dialog components - text, image and button
                TextView attack = (TextView) dialog.findViewById(R.id.attack);
                attack.setText(R.string.Attack);
                TextView defence = (TextView) dialog.findViewById(R.id.defence);
                defence.setText(R.string.Defence);
                TextView speed = (TextView) dialog.findViewById(R.id.speed);
                speed.setText(R.string.Speed);

                //ImageView image = (ImageView) dialog.findViewById(R.id.image);
                //image.setImageResource(R.drawable.ic_launcher);

                ImageView checkMark = (ImageView) dialog.findViewById(R.id.check_mark);
                // If checkMark is clicked, save attributes
                checkMark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });



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

    /** Called when front_arrow is clicked
     *
     *  Function: Modifies the hero's attributes
     */
    public void createHero(View view) {

        boolean isPlayerHero = true;

        Hero myHero = new Hero(isPlayerHero);
        Controller.getInstance().setHero(myHero);

        int heroType = getHeroType();
        String heroName = getHeroName();

        // Set hero's name, type and distribute attributes
        Controller.getInstance().getHero().setName(heroName);
        Controller.getInstance().getHero().setType(heroType);
        Controller.getInstance().getHero().setStats(Controller.getInstance().getHero().getType());

        /** Select attributes
         * We proceed by creating a new dialog
         *
         * Function: Lets user distribute his initial 10 attributes points
         */

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

}
