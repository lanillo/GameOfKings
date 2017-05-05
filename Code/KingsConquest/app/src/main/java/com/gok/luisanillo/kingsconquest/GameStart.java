package com.gok.luisanillo.kingsconquest;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class GameStart extends AppCompatActivity {

    int status = 0;
    final Context context = this;
    final int selectedCity = 0;

    private int finalSelectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);


        // Set history text and make it scrollable
        TextView historyText = (TextView) findViewById(R.id.history_text);
        historyText.setText(R.string.HistoryBeginning);
        historyText.setMovementMethod(new ScrollingMovementMethod());


    }

    public void next(View view) {

        if (status == 0) {

            TextView historyText = (TextView) findViewById(R.id.history_text);
            historyText.setText(R.string.HistoryBeginning1);
            historyText.setMovementMethod(new ScrollingMovementMethod());

            status = 1;

        } else if (status == 1) {

            TextView historyText = (TextView) findViewById(R.id.history_text);
            historyText.setText(R.string.HistoryBeginning2);
            historyText.setTextColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
            historyText.setMovementMethod(new ScrollingMovementMethod());

            ConstraintLayout mConstraintLayout = (ConstraintLayout) findViewById(R.id.background);
            mConstraintLayout.setBackgroundResource(R.drawable.caravan);

            view.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.white, null));

            status = 2;


        } else if (status == 2) {

            TextView historyText = (TextView) findViewById(R.id.history_text);
            historyText.setText(R.string.HistoryBeginning3);
            historyText.setMovementMethod(new ScrollingMovementMethod());

            status = 3;

        } else if (status == 3) {

            // Create dialog and title
            final Dialog dialog = new Dialog(context);

            dialog.setContentView(R.layout.choose_starting_city);
            dialog.setTitle(R.string.ChooseStartingCity);

            // Set attributes text
            final TextView title = (TextView) dialog.findViewById(R.id.choose_your_city);
            title.setText(R.string.ChooseStartingCity);

            final RadioButton city1 = (RadioButton) dialog.findViewById(R.id.city1);
            final RadioButton city2 = (RadioButton) dialog.findViewById(R.id.city2);
            final RadioButton city3 = (RadioButton) dialog.findViewById(R.id.city3);
            final RadioButton city4 = (RadioButton) dialog.findViewById(R.id.city4);

            final RadioGroup radioCitiesGroup = (RadioGroup) dialog.findViewById(R.id.city_group);
            final ImageView checkMark = (ImageView) dialog.findViewById(R.id.check_mark);

            checkMark.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    // get selected radio button in group
                    int selectedCity = radioCitiesGroup.getCheckedRadioButtonId();

                    if (selectedCity == city1.getId()) {
                        selectedCity = 1;
                        Log.i("SELECTED_CITY", ""+selectedCity);
                    } else if (selectedCity == city2.getId()) {
                        selectedCity = 2;
                        Log.i("SELECTED_CITY", ""+selectedCity);
                    } else if (selectedCity == city3.getId()) {
                        selectedCity = 3;
                        Log.i("SELECTED_CITY", ""+selectedCity);
                    } else if (selectedCity == city4.getId()) {
                        selectedCity = 4;
                        Log.i("SELECTED_CITY", ""+selectedCity);
                    }

                    setFinalSelectedCity(selectedCity);

                    dialog.dismiss();
                    status = 4;
                    Log.i("STATUS", ""+status);
                    Log.i("SELECTED_CITY", ""+selectedCity);
                }
            });

            dialog.show();

        } else if (status == 4) {

            Log.i("STATUS", "IM AM IN STATUS == 4");
            Log.i("SELECTED_CITY", ""+getFinalSelectedCity());

            view.setBackgroundColor(Color.TRANSPARENT);

            if (getFinalSelectedCity() == 1) {

                // Frostford
                ConstraintLayout mConstraintLayout = (ConstraintLayout) findViewById(R.id.background);
                mConstraintLayout.setBackgroundResource(R.drawable.frostford);


            } else if (getFinalSelectedCity() == 2) {

                // Bredon
                ConstraintLayout mConstraintLayout = (ConstraintLayout) findViewById(R.id.background);
                mConstraintLayout.setBackgroundResource(R.drawable.bredon);


            } else if (getFinalSelectedCity() == 3) {

                // Worcester
                ConstraintLayout mConstraintLayout = (ConstraintLayout) findViewById(R.id.background);
                mConstraintLayout.setBackgroundResource(R.drawable.worcester);


            } else if (getFinalSelectedCity() == 4) {

                // Old Ashton
                ConstraintLayout mConstraintLayout = (ConstraintLayout) findViewById(R.id.background);
                mConstraintLayout.setBackgroundResource(R.drawable.city);

            }

            TextView historyText = (TextView) findViewById(R.id.history_text);
            historyText.setText(getHistoryAfterCityChoose(getFinalSelectedCity()));
            historyText.setTextColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
            historyText.setMovementMethod(new ScrollingMovementMethod());

            if (getFinalSelectedCity() == 4 || getFinalSelectedCity() == 2) {

                historyText.setTextColor(ResourcesCompat.getColor(getResources(), R.color.black, null));

            }

            status = 5;

        } else if (status == 5) {

            ConstraintLayout mConstraintLayout = (ConstraintLayout) findViewById(R.id.background);
            mConstraintLayout.setBackgroundResource(R.drawable.stranger);

            TextView historyText = (TextView) findViewById(R.id.history_text);
            historyText.setText(getVillageToSave(getFinalSelectedCity()));
            historyText.setTextColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
            historyText.setMovementMethod(new ScrollingMovementMethod());

            status = 6;

        } else if (status == 6) {

            ConstraintLayout mConstraintLayout = (ConstraintLayout) findViewById(R.id.background);
            mConstraintLayout.setBackgroundResource(R.drawable.caravan);

            TextView historyText = (TextView) findViewById(R.id.history_text);
            historyText.setText(R.string.HistoryStranger2);
            historyText.setTextColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
            historyText.setMovementMethod(new ScrollingMovementMethod());

        }
    }

    public int getFinalSelectedCity() {

        return finalSelectedCity;

    }

    public void setFinalSelectedCity(int selectedCity) {

        finalSelectedCity = selectedCity;

    }

    public String getHistoryAfterCityChoose(int selectedCity) {

        if (selectedCity == 1) {
            String selectedCityName = getResources().getString(R.string.City1);
            return getResources().getString(R.string.CityRemember) + selectedCityName + ". "
                    + getResources().getString(R.string.DescCity1);

        } else if (selectedCity == 2) {
            String selectedCityName = getResources().getString(R.string.City2);
            return getResources().getString(R.string.CityRemember) + selectedCityName + ". "
                    + getResources().getString(R.string.DescCity2);

        } else if (selectedCity == 3) {
            String selectedCityName = getResources().getString(R.string.City3);
            return getResources().getString(R.string.CityRemember) + selectedCityName + ". "
                    + getResources().getString(R.string.DescCity3);

        } else if (selectedCity == 4) {
            String selectedCityName = getResources().getString(R.string.City4);
            return getResources().getString(R.string.CityRemember) + selectedCityName + ". "
                    + getResources().getString(R.string.DescCity4);

        } else {

            return "rip";
        }

    }

    public String getVillageToSave(int selectedCity) {

        if (selectedCity == 1) {
            String selectedVillageToSaveName = getResources().getString(R.string.Village1);
            return getResources().getString(R.string.historyStranger) + selectedVillageToSaveName + ". "
                    + getResources().getString(R.string.HistoryStranger1);

        } else if (selectedCity == 2) {
            String selectedVillageToSaveName = getResources().getString(R.string.Village1);
            return getResources().getString(R.string.historyStranger) + selectedVillageToSaveName + ". "
                    + getResources().getString(R.string.HistoryStranger1);

        } else if (selectedCity == 3) {
            String selectedVillageToSaveName = getResources().getString(R.string.Village1);
            return getResources().getString(R.string.historyStranger) + selectedVillageToSaveName + ". "
                    + getResources().getString(R.string.HistoryStranger1);

        } else if (selectedCity == 4) {
            String selectedVillageToSaveName = getResources().getString(R.string.Village1);
            return getResources().getString(R.string.historyStranger) + selectedVillageToSaveName + ". "
                    + getResources().getString(R.string.HistoryStranger1);

        } else {

            return "rip";
        }

    }

}
