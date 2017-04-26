package com.gok.luisanillo.kingsconquest;

/**
 * Created by LuisFelipe on 2017-04-25.
 */

public class Hero {

    //Initialisation
    private static final Hero ourInstance = new Hero();
    public static Hero getInstance() {

        return ourInstance;
    }



    private Hero() {

    }
}
