package com.gok.luisanillo.kingsconquest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ResourceBundle;


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

        // If front_arrow is pressed
        setAttributesDialog(view);


    }

    /** Select attributes
     * We proceed by creating a new dialog
     *
     * Function: Brings up the setAttributes dialog
     */
    public void setAttributesDialog(View view) {

        /*// temp variables
        int tempAttack, tempDefence, tempSpeed, tempAttributes;

        tempAttack = Controller.getInstance().getHero().getAttack();
        tempDefence = Controller.getInstance().getHero().getDefence();
        tempSpeed = Controller.getInstance().getHero().getSpeed();
        tempAttributes = Controller.getInstance().getHero().getAttributePoints();
        */

        // Create dialog and title
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.distribute_attributes);
        dialog.setTitle(R.string.DistributeAttributesTitle);

        // Set attributes text
        TextView attack = (TextView) dialog.findViewById(R.id.attack);
        attack.setText(R.string.Attack);
        TextView defence = (TextView) dialog.findViewById(R.id.defence);
        defence.setText(R.string.Defence);
        TextView speed = (TextView) dialog.findViewById(R.id.speed);
        speed.setText(R.string.Speed);

        // Set remaining attributes text
        TextView attributesLeft = (TextView) dialog.findViewById(R.id.attributes_left);
        attributesLeft.setText("Your hero " + Controller.getInstance().getHero().getName() + " has "
                + Controller.getInstance().getHero().getAttributePoints() + " attributes points left.");

        // Set current stats text
        TextView currentAttack = (TextView) dialog.findViewById(R.id.current_attack);
        currentAttack.setText(String.valueOf(Controller.getInstance().getHero().getAttack()));
        TextView currentDefence = (TextView) dialog.findViewById(R.id.current_defence);
        currentDefence.setText(String.valueOf(Controller.getInstance().getHero().getDefence()));
        TextView currentSpeed = (TextView) dialog.findViewById(R.id.current_speed);
        currentSpeed.setText(String.valueOf(Controller.getInstance().getHero().getSpeed()));

        // Imageviews of plus and minus marks
        ImageView attackPlus = (ImageView) dialog.findViewById(R.id.attack_plus);
        attackPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Controller.getInstance().getHero().getAttributePoints() == 0) {

                    Log.i("ATTRIBUTES_DISTRIBUTION", "cant distribute points");

                } else {
                    Controller.getInstance().getHero().setAttributePoints(Controller.getInstance().getHero().getAttributePoints() - 1);
                    Controller.getInstance().getHero().setAttack(Controller.getInstance().getHero().getAttack() + 1);
                    Log.i("ATTRIBUTES_DISTRIBUTION", "att+");

                    TextView currentAttack = (TextView) dialog.findViewById(R.id.current_attack);
                    currentAttack.setText(String.valueOf(Controller.getInstance().getHero().getAttack()));

                    TextView attributesLeft = (TextView) dialog.findViewById(R.id.attributes_left);
                    attributesLeft.setText("Your hero " + Controller.getInstance().getHero().getName() + " has "
                            + Controller.getInstance().getHero().getAttributePoints() + " attributes points left.");
                }
            }
        });
        ImageView attackMinus = (ImageView) dialog.findViewById(R.id.attack_minus);
        attackMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Controller.getInstance().getHero().getAttributePoints() == 0) {

                    Log.i("ATTRIBUTES_DISTRIBUTION", "cant distribute points");

                } else {
                    Controller.getInstance().getHero().setAttributePoints(Controller.getInstance().getHero().getAttributePoints() + 1);
                    Controller.getInstance().getHero().setAttack(Controller.getInstance().getHero().getAttack() - 1);
                    Log.i("ATTRIBUTES_DISTRIBUTION", "att-");

                    TextView currentAttack = (TextView) dialog.findViewById(R.id.current_attack);
                    currentAttack.setText(String.valueOf(Controller.getInstance().getHero().getAttack()));

                    TextView attributesLeft = (TextView) dialog.findViewById(R.id.attributes_left);
                    attributesLeft.setText("Your hero " + Controller.getInstance().getHero().getName() + " has "
                            + Controller.getInstance().getHero().getAttributePoints() + " attributes points left.");

                }
            }
        });
        ImageView defencePlus = (ImageView) dialog.findViewById(R.id.defence_plus);
        defencePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Controller.getInstance().getHero().getAttributePoints() == 0) {

                    Log.i("ATTRIBUTES_DISTRIBUTION", "cant distribute points");

                } else {
                    Controller.getInstance().getHero().setAttributePoints(Controller.getInstance().getHero().getAttributePoints() - 1);
                    Controller.getInstance().getHero().setDefence(Controller.getInstance().getHero().getDefence() + 1);
                    Log.i("ATTRIBUTES_DISTRIBUTION", "def+");
                }
            }
        });
        ImageView defenceMinus = (ImageView) dialog.findViewById(R.id.defence_minus);
        defenceMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Controller.getInstance().getHero().getAttributePoints() == 0) {

                    Log.i("ATTRIBUTES_DISTRIBUTION", "cant distribute points");

                } else {
                    Controller.getInstance().getHero().setAttributePoints(Controller.getInstance().getHero().getAttributePoints() + 1);
                    Controller.getInstance().getHero().setDefence(Controller.getInstance().getHero().getDefence() - 1);
                    Log.i("ATTRIBUTES_DISTRIBUTION", "def-");
                }
            }
        });
        ImageView speedPlus = (ImageView) dialog.findViewById(R.id.speed_plus);
        speedPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Controller.getInstance().getHero().getAttributePoints() == 0) {

                    Log.i("ATTRIBUTES_DISTRIBUTION", "cant distribute points");

                } else {
                    Controller.getInstance().getHero().setAttributePoints(Controller.getInstance().getHero().getAttributePoints() - 1);
                    Controller.getInstance().getHero().setSpeed(Controller.getInstance().getHero().getSpeed() + 1);
                    Log.i("ATTRIBUTES_DISTRIBUTION", "speed+");
                }
            }
        });
        ImageView speedMinus = (ImageView) dialog.findViewById(R.id.speed_minus);
        speedMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Controller.getInstance().getHero().getAttributePoints() == 0) {

                    Log.i("ATTRIBUTES_DISTRIBUTION", "cant distribute points");

                } else {
                    Controller.getInstance().getHero().setAttributePoints(Controller.getInstance().getHero().getAttributePoints() + 1);
                    Controller.getInstance().getHero().setSpeed(Controller.getInstance().getHero().getSpeed() - 1);
                    Log.i("ATTRIBUTES_DISTRIBUTION", "speed-");
                }
            }
        });

        // If there is no more attributes point
        if (Controller.getInstance().getHero().getAttributePoints() == 0) {

            attackPlus.setVisibility(View.INVISIBLE);
            defencePlus.setVisibility(View.INVISIBLE);
            speedPlus.setVisibility(View.INVISIBLE);

            attackMinus.setVisibility(View.VISIBLE);
            defenceMinus.setVisibility(View.VISIBLE);
            speedMinus.setVisibility(View.VISIBLE);

        } else if (Controller.getInstance().getHero().getAttributePoints() == 5) {

            attackPlus.setVisibility(View.VISIBLE);
            defencePlus.setVisibility(View.VISIBLE);
            speedPlus.setVisibility(View.VISIBLE);

            attackMinus.setVisibility(View.INVISIBLE);
            defenceMinus.setVisibility(View.INVISIBLE);
            speedMinus.setVisibility(View.INVISIBLE);

        } else {

            attackPlus.setVisibility(View.VISIBLE);
            defencePlus.setVisibility(View.VISIBLE);
            speedPlus.setVisibility(View.VISIBLE);

            attackMinus.setVisibility(View.VISIBLE);
            defenceMinus.setVisibility(View.VISIBLE);
            speedMinus.setVisibility(View.VISIBLE);

        }

        // ImageView of saving changes
        ImageView checkMark = (ImageView) dialog.findViewById(R.id.check_mark);

        // If checkMark is clicked, save attributes
        checkMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();


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
