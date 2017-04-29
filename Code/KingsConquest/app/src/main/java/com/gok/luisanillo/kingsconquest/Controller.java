package com.gok.luisanillo.kingsconquest;

/**
 * Created by LuisFelipe on 2017-04-26.
 */

public class Controller {

    private static Controller instance = null;

    public static Controller getInstance()
    {
        if(instance == null)
        {
            instance = new Controller();
        }

        return instance;
    }

    public Controller()
    {

    }

    //================================================================================
    // Parameters
    //================================================================================
    private Hero hero;
    private Hero empty;


    //================================================================================
    // Functions
    //================================================================================
    public Hero getHero()
    {
        return hero;
    }

    public void setHero(Hero hero)
    {
        this.hero = hero;
    }



}
