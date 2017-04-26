package com.gok.luisanillo.kingsconquest;

import android.util.Log;

/**
 * Created by LuisFelipe on 2017-04-25.
 */

public class Hero {

    /** List of attributes */
    private String name;
    private int type;

    private int attack;
    private int defence;
    private int speed;

    private int hitpoints;
    private int experience;


    // Initialisation of the hero
    private static final Hero ourInstance = new Hero();
    public static Hero getInstance() {

        return ourInstance;
    }


    /** Called after type is set stats when type is chosen
     *
     * Function: set stats when type is chosen
     */

    public void setStats(int type) {

        switch(type) {

            // Warrior type
            case Constants.WARRIOR:
                setAttack(Constants.INITIAL_ATTACK_W);
                setDefence(Constants.INITIAL_DEFENCE_W);
                setSpeed(Constants.INITIAL_SPEED_W);
                break;

            // Archer type
            case Constants.ARCHER:
                setAttack(Constants.INITIAL_ATTACK_A);
                setDefence(Constants.INITIAL_DEFENCE_A);
                setSpeed(Constants.INITIAL_SPEED_A);
                break;

            // Speed type
            case Constants.KNIGHT:
                setAttack(Constants.INITIAL_ATTACK_K);
                setDefence(Constants.INITIAL_DEFENCE_K);
                setSpeed(Constants.INITIAL_SPEED_K);
                break;

            case Constants.NO_TYPE:
                // Debug message indicating the type has not yet been chosen
                Log.i("NO_TYPE_setStats", "type is equal to -1, therefore no type chosen. ERROR");
                break;

            default:
                //Log.i("DEBUG_TAG", "User pressed yes.");
                Log.i("NO_TYPE_setStats", "type is equal to something weird, ERROR");
                break;

        }

    }




    // Constructor
    private Hero() {

        this.name = Constants.NAME;
        this.type = Constants.NO_TYPE;
        this.attack = Constants.INITIAL_ATTACK;
        this.defence = Constants.INITIAL_DEFENCE;
        this.speed = Constants.INITIAL_SPEED;
        this.hitpoints = Constants.INITIAL_HITPOINTS;
        this.experience = Constants.INITIAL_EXPERIENCE;

    }


     /** List of modifiers
      *
      * Function: Modify each of the attributes of the hero */
     public void setName(String Name) {

        this.name = Name;
    }

    public void setType(int Type) {

        this.type = Type;
    }

    public void setAttack(int Attack) {

        this.attack = Attack;
    }

    public void setDefence(int Defence) {

        this.defence = Defence;
    }

    public void setSpeed(int Speed) {

        this.speed = Speed;
    }

    public void setHitpoints(int Hitpoints) {

        this.hitpoints = Hitpoints;
    }

    public void setExperience(int Experience) {

        this.experience = Experience;
    }

    /** List of accessers
     *
     * Function: Read each attribute of the hero */
    public String getName() {

        return this.name;
    }

    public int getType() {

        return this.type;
    }

    public int getAttack() {

        return this.attack;
    }

    public int getDefence() {

        return this.defence;
    }

    public int getSpeed() {

        return this.speed;
    }

    public int getHitpoints() {

        return this.hitpoints;
    }

    public int getExperience() {

        return this.experience;
    }


}
