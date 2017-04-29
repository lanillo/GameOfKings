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
import android.widget.Toast;

import java.util.ResourceBundle;


public class CharacterCreationActivity extends AppCompatActivity {

    // Objects
    ViewPager viewPager;
    CustomSwipeAdapter adapter;

    boolean isHeroAlreadyCreated = false;
    boolean isHeroAttributesDistributed = false;

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
                        Controller.getInstance().setHero(Controller.getInstance().getHero());
                        finish();
                    }

                }).create().show();

    }

    /** Called when front_arrow is clicked
     *
     *  Function: Modifies the hero's attributes
     */
    public void createHero(View view) {

        int heroType = getHeroType();
        String heroName = getHeroName();
        Log.i("NAME", "NAME: " + heroName + ".");

        if(heroName.isEmpty() || heroName.length() == 0 || heroName.equals("") || heroName == null) {

            showToastName();

        }

        else {

            if (!isHeroAlreadyCreated) {

                // Set hero's name, type and distribute attributes
                Controller.getInstance().getHero().setName(heroName);
                Controller.getInstance().getHero().setType(heroType);
                Controller.getInstance().getHero().setStats(Controller.getInstance().getHero().getType());
                isHeroAlreadyCreated = true;

            }

            /** Select attributes
             * We proceed by creating a new dialog
             *
             * Function: Lets user distribute his initial 10 attributes points
             */

            // If front_arrow is pressed

            if (!isHeroAttributesDistributed) {

                setAttributesDialog(view, Controller.getInstance().getHero(), isHeroAlreadyCreated);
                isHeroAttributesDistributed = true;

            } else {

                showToastHeroCreated();

            }

        }

    }

    /** Select attributes
     * We proceed by creating a new dialog
     *
     * Function: Brings up the setAttributes dialog
     */
    public void setAttributesDialog(View view, final Hero hero, boolean isHeroAlreadyCreated) {

        if (isHeroAlreadyCreated) {

            // temp initial attributes
            final int tempAttack, tempDefence, tempSpeed, tempAttributes;

            tempAttack = Controller.getInstance().getHero().getAttack();
            tempDefence = Controller.getInstance().getHero().getDefence();
            tempSpeed = Controller.getInstance().getHero().getSpeed();
            tempAttributes = Controller.getInstance().getHero().getAttributePoints();

            Log.i("GET_ATTRIBUTES", "" + tempAttributes);
            Log.i("ATT", "" + tempAttack);
            Log.i("DEF", "" + tempDefence);
            Log.i("SPEED", "" + tempSpeed);

            // Create dialog and title
            final Dialog dialog = new Dialog(context);

            dialog.setContentView(R.layout.distribute_attributes);
            dialog.setTitle(R.string.DistributeAttributesTitle);

            // Set attributes text
            final TextView attack = (TextView) dialog.findViewById(R.id.attack);
            attack.setText(R.string.Attack);
            final TextView defence = (TextView) dialog.findViewById(R.id.defence);
            defence.setText(R.string.Defence);
            final TextView speed = (TextView) dialog.findViewById(R.id.speed);
            speed.setText(R.string.Speed);

            // Set remaining attributes text
            final TextView attributesLeft = (TextView) dialog.findViewById(R.id.attributes_left);
            attributesLeft.setText("Your hero " + Controller.getInstance().getHero().getName() + " has "
                    + Controller.getInstance().getHero().getAttributePoints() + " attributes points left.");

            // Set current stats text
            final TextView currentAttack = (TextView) dialog.findViewById(R.id.current_attack);
            currentAttack.setText(String.valueOf(Controller.getInstance().getHero().getAttack()));
            final TextView currentDefence = (TextView) dialog.findViewById(R.id.current_defence);
            currentDefence.setText(String.valueOf(Controller.getInstance().getHero().getDefence()));
            final TextView currentSpeed = (TextView) dialog.findViewById(R.id.current_speed);
            currentSpeed.setText(String.valueOf(Controller.getInstance().getHero().getSpeed()));

            // Imageviews of plus and minus marks
            final ImageView attackPlus = (ImageView) dialog.findViewById(R.id.attack_plus);
            final ImageView attackMinus = (ImageView) dialog.findViewById(R.id.attack_minus);
            final ImageView defencePlus = (ImageView) dialog.findViewById(R.id.defence_plus);
            final ImageView defenceMinus = (ImageView) dialog.findViewById(R.id.defence_minus);
            final ImageView speedPlus = (ImageView) dialog.findViewById(R.id.speed_plus);
            final ImageView speedMinus = (ImageView) dialog.findViewById(R.id.speed_minus);

            attackMinus.setVisibility(View.INVISIBLE);
            defenceMinus.setVisibility(View.INVISIBLE);
            speedMinus.setVisibility(View.INVISIBLE);

            if (Controller.getInstance().getHero().getAttributePoints() <= 0 && isHeroAlreadyCreated) {

                attackPlus.setVisibility(View.INVISIBLE);
                defencePlus.setVisibility(View.INVISIBLE);
                speedPlus.setVisibility(View.INVISIBLE);

            }

            if (isHeroAlreadyCreated && isHeroAlreadyCreated) {

                if (Controller.getInstance().getHero().getAttributePoints() < 5 && isHeroAlreadyCreated) {

                    attackMinus.setVisibility(View.VISIBLE);
                    defenceMinus.setVisibility(View.VISIBLE);
                    speedMinus.setVisibility(View.VISIBLE);

                }
            }

            // OnClick fonctions for plus and minus

            /** Attack Plus */
            attackPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Controller.getInstance().getHero().getAttributePoints() <= 0) {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "cant distribute points");

                    } else {

                        Controller.getInstance().getHero().setAttributePoints(Controller.getInstance().getHero().getAttributePoints() - 1);
                        Log.i("ATTRIBUTES_DISTRIBUTION", "Attributes point after click: " + Controller.getInstance().getHero().getAttributePoints());

                        Controller.getInstance().getHero().setAttack(Controller.getInstance().getHero().getAttack() + 1);
                        Log.i("ATTRIBUTES_DISTRIBUTION", "att+");

                        currentAttack.setText(String.valueOf(Controller.getInstance().getHero().getAttack()));

                        attributesLeft.setText("Your hero " + Controller.getInstance().getHero().getName() + " has "
                                + Controller.getInstance().getHero().getAttributePoints() + " attributes points left.");

                        Controller.getInstance().setHero(Controller.getInstance().getHero());

                    }

                    // If there is no more attributes point, remove plus
                    if (Controller.getInstance().getHero().getAttributePoints() <= 0) {

                        attackPlus.setVisibility(View.INVISIBLE);
                        defencePlus.setVisibility(View.INVISIBLE);
                        speedPlus.setVisibility(View.INVISIBLE);

                    } else if (Controller.getInstance().getHero().getAttributePoints() > 0) {

                        attackPlus.setVisibility(View.VISIBLE);
                        defencePlus.setVisibility(View.VISIBLE);
                        speedPlus.setVisibility(View.VISIBLE);

                    } else {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "ERROR REMOVING PLUS!");

                    }

                    // If attack is at the same value as the start
                    if (Controller.getInstance().getHero().getAttack() <= tempAttack) {

                        attackMinus.setVisibility(View.INVISIBLE);

                    } else if (Controller.getInstance().getHero().getAttack() > tempAttack) {

                        attackMinus.setVisibility(View.VISIBLE);

                    } else {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "ERROR REMOVING MINUS !");

                    }

                }
            });

            /** Attack Minus */
            attackMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Controller.getInstance().getHero().getAttributePoints() >= 5) {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "cant distribute points");

                    } else {

                        Controller.getInstance().getHero().setAttributePoints(Controller.getInstance().getHero().getAttributePoints() + 1);
                        Log.i("ATTRIBUTES_DISTRIBUTION", "Attributes point after click: " + Controller.getInstance().getHero().getAttributePoints());

                        Controller.getInstance().getHero().setAttack(Controller.getInstance().getHero().getAttack() - 1);
                        Log.i("ATTRIBUTES_DISTRIBUTION", "att-");

                        currentAttack.setText(String.valueOf(Controller.getInstance().getHero().getAttack()));

                        attributesLeft.setText("Your hero " + Controller.getInstance().getHero().getName() + " has "
                                + Controller.getInstance().getHero().getAttributePoints() + " attributes points left.");

                        Controller.getInstance().setHero(Controller.getInstance().getHero());


                    }

                    // If there is no more attributes point, remove plus
                    if (Controller.getInstance().getHero().getAttributePoints() <= 0) {

                        attackPlus.setVisibility(View.INVISIBLE);
                        defencePlus.setVisibility(View.INVISIBLE);
                        speedPlus.setVisibility(View.INVISIBLE);

                    } else if (Controller.getInstance().getHero().getAttributePoints() > 0) {

                        attackPlus.setVisibility(View.VISIBLE);
                        defencePlus.setVisibility(View.VISIBLE);
                        speedPlus.setVisibility(View.VISIBLE);

                    } else {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "ERROR REMOVING PLUS!");

                    }

                    // If attack is at the same value as the start
                    if (Controller.getInstance().getHero().getAttack() <= tempAttack) {

                        attackMinus.setVisibility(View.INVISIBLE);

                    } else if (Controller.getInstance().getHero().getAttack() > tempAttack) {

                        attackMinus.setVisibility(View.VISIBLE);

                    } else {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "ERROR REMOVING MINUS !");

                    }

                }
            });

            /** Defence Plus */
            defencePlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Controller.getInstance().getHero().getAttributePoints() <= 0) {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "cant distribute points");

                    } else {

                        Controller.getInstance().getHero().setAttributePoints(Controller.getInstance().getHero().getAttributePoints() - 1);
                        Log.i("ATTRIBUTES_DISTRIBUTION", "Attributes point after click: " + Controller.getInstance().getHero().getAttributePoints());

                        Controller.getInstance().getHero().setDefence(Controller.getInstance().getHero().getDefence() + 1);
                        Log.i("ATTRIBUTES_DISTRIBUTION", "def+");

                        currentDefence.setText(String.valueOf(Controller.getInstance().getHero().getDefence()));

                        attributesLeft.setText("Your hero " + Controller.getInstance().getHero().getName() + " has "
                                + Controller.getInstance().getHero().getAttributePoints() + " attributes points left.");

                        Controller.getInstance().setHero(Controller.getInstance().getHero());

                    }


                    // If there is no more attributes point, remove plus
                    if (Controller.getInstance().getHero().getAttributePoints() <= 0) {

                        attackPlus.setVisibility(View.INVISIBLE);
                        defencePlus.setVisibility(View.INVISIBLE);
                        speedPlus.setVisibility(View.INVISIBLE);

                    } else if (Controller.getInstance().getHero().getAttributePoints() > 0) {

                        attackPlus.setVisibility(View.VISIBLE);
                        defencePlus.setVisibility(View.VISIBLE);
                        speedPlus.setVisibility(View.VISIBLE);

                    } else {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "ERROR REMOVING PLUS!");

                    }

                    // If attack is at the same value as the start
                    if (Controller.getInstance().getHero().getDefence() <= tempDefence) {

                        defenceMinus.setVisibility(View.INVISIBLE);

                    } else if (Controller.getInstance().getHero().getDefence() > tempDefence) {

                        defenceMinus.setVisibility(View.VISIBLE);

                    } else {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "ERROR REMOVING MINUS !");

                    }


                }
            });

            /** Defence Minus */
            defenceMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Controller.getInstance().getHero().getAttributePoints() >= 5) {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "cant distribute points");

                    } else {

                        Controller.getInstance().getHero().setAttributePoints(Controller.getInstance().getHero().getAttributePoints() + 1);
                        Log.i("ATTRIBUTES_DISTRIBUTION", "Attributes point after click: " + Controller.getInstance().getHero().getAttributePoints());

                        Controller.getInstance().getHero().setDefence(Controller.getInstance().getHero().getDefence() - 1);
                        Log.i("ATTRIBUTES_DISTRIBUTION", "def-");

                        currentDefence.setText(String.valueOf(Controller.getInstance().getHero().getDefence()));

                        attributesLeft.setText("Your hero " + Controller.getInstance().getHero().getName() + " has "
                                + Controller.getInstance().getHero().getAttributePoints() + " attributes points left.");

                        Controller.getInstance().setHero(Controller.getInstance().getHero());


                    }


                    // If there is no more attributes point, remove plus
                    if (Controller.getInstance().getHero().getAttributePoints() <= 0) {

                        attackPlus.setVisibility(View.INVISIBLE);
                        defencePlus.setVisibility(View.INVISIBLE);
                        speedPlus.setVisibility(View.INVISIBLE);

                    } else if (Controller.getInstance().getHero().getAttributePoints() > 0) {

                        attackPlus.setVisibility(View.VISIBLE);
                        defencePlus.setVisibility(View.VISIBLE);
                        speedPlus.setVisibility(View.VISIBLE);

                    } else {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "ERROR REMOVING PLUS!");

                    }

                    // If attack is at the same value as the start
                    if (Controller.getInstance().getHero().getDefence() <= tempDefence) {

                        defenceMinus.setVisibility(View.INVISIBLE);

                    } else if (Controller.getInstance().getHero().getDefence() > tempDefence) {

                        defenceMinus.setVisibility(View.VISIBLE);

                    } else {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "ERROR REMOVING MINUS !");

                    }


                }
            });

            /** Speed Plus */
            speedPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Controller.getInstance().getHero().getAttributePoints() <= 0) {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "cant distribute points");

                    } else {

                        Controller.getInstance().getHero().setAttributePoints(Controller.getInstance().getHero().getAttributePoints() - 1);
                        Log.i("ATTRIBUTES_DISTRIBUTION", "Attributes point after click: " + Controller.getInstance().getHero().getAttributePoints());

                        Controller.getInstance().getHero().setSpeed(Controller.getInstance().getHero().getSpeed() + 1);
                        Log.i("ATTRIBUTES_DISTRIBUTION", "speed+");

                        currentSpeed.setText(String.valueOf(Controller.getInstance().getHero().getSpeed()));

                        attributesLeft.setText("Your hero " + Controller.getInstance().getHero().getName() + " has "
                                + Controller.getInstance().getHero().getAttributePoints() + " attributes points left.");

                        Controller.getInstance().setHero(Controller.getInstance().getHero());


                    }


                    // If there is no more attributes point, remove plus
                    if (Controller.getInstance().getHero().getAttributePoints() <= 0) {

                        attackPlus.setVisibility(View.INVISIBLE);
                        defencePlus.setVisibility(View.INVISIBLE);
                        speedPlus.setVisibility(View.INVISIBLE);

                    } else if (Controller.getInstance().getHero().getAttributePoints() > 0) {

                        attackPlus.setVisibility(View.VISIBLE);
                        defencePlus.setVisibility(View.VISIBLE);
                        speedPlus.setVisibility(View.VISIBLE);

                    } else {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "ERROR REMOVING PLUS!");

                    }

                    // If attack is at the same value as the start
                    if (Controller.getInstance().getHero().getSpeed() <= tempSpeed) {

                        speedMinus.setVisibility(View.INVISIBLE);

                    } else if (Controller.getInstance().getHero().getSpeed() > tempSpeed) {

                        speedMinus.setVisibility(View.VISIBLE);

                    } else {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "ERROR REMOVING MINUS !");

                    }

                }
            });

            /** Speed Minus */
            speedMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Controller.getInstance().getHero().getAttributePoints() >= 5) {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "cant distribute points");

                    } else {

                        Controller.getInstance().getHero().setAttributePoints(Controller.getInstance().getHero().getAttributePoints() + 1);
                        Log.i("ATTRIBUTES_DISTRIBUTION", "Attributes point after click: " + Controller.getInstance().getHero().getAttributePoints());

                        Controller.getInstance().getHero().setSpeed(Controller.getInstance().getHero().getSpeed() - 1);
                        Log.i("ATTRIBUTES_DISTRIBUTION", "speed-");

                        currentSpeed.setText(String.valueOf(Controller.getInstance().getHero().getSpeed()));

                        attributesLeft.setText("Your hero " + Controller.getInstance().getHero().getName() + " has "
                                + Controller.getInstance().getHero().getAttributePoints() + " attributes points left.");

                        Controller.getInstance().setHero(Controller.getInstance().getHero());


                    }

                    // If there is no more attributes point, remove plus
                    if (Controller.getInstance().getHero().getAttributePoints() <= 0) {

                        attackPlus.setVisibility(View.INVISIBLE);
                        defencePlus.setVisibility(View.INVISIBLE);
                        speedPlus.setVisibility(View.INVISIBLE);

                    } else if (Controller.getInstance().getHero().getAttributePoints() > 0) {

                        attackPlus.setVisibility(View.VISIBLE);
                        defencePlus.setVisibility(View.VISIBLE);
                        speedPlus.setVisibility(View.VISIBLE);

                    } else {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "ERROR REMOVING PLUS!");

                    }

                    // If attack is at the same value as the start
                    if (Controller.getInstance().getHero().getSpeed() <= tempSpeed) {

                        speedMinus.setVisibility(View.INVISIBLE);

                    } else if (Controller.getInstance().getHero().getSpeed() > tempSpeed) {

                        speedMinus.setVisibility(View.VISIBLE);

                    } else {

                        Log.i("ATTRIBUTES_DISTRIBUTION", "ERROR REMOVING MINUS !");

                    }

                }
            });

            // ImageView of saving changes
            ImageView checkMark = (ImageView) dialog.findViewById(R.id.check_mark);

            // If checkMark is clicked, save attributes
            checkMark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Controller.getInstance().setHero((Controller.getInstance().getHero()));

                    if (Controller.getInstance().getHero().getAttributePoints() <= 0) {

                        // Hero created
                        Controller.getInstance().setHero(Controller.getInstance().getHero());
                        dialog.dismiss();

                    } else {

                        // Refuse to create hero until attributes are spent
                        showToastAttributes();

                    }
                }
            });

            dialog.show();

        }

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

    public void showToastAttributes(){
        Toast.makeText(this, R.string.EnterAllAttributes, Toast.LENGTH_SHORT).show();
    }

    public void showToastName(){
        Toast.makeText(this, R.string.EnterNameHero, Toast.LENGTH_SHORT).show();
    }

    public void showToastHeroCreated(){
        Toast.makeText(this, R.string.HeroCreated, Toast.LENGTH_SHORT).show();
    }

    public int _getScreenOrientation(){
        return getResources().getConfiguration().orientation;
    }
}
