package com.gok.luisanillo.kingsconquest;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
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

    private RadioButton radioCitiesButton;

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



            final RadioGroup radioCitiesGroup = (RadioGroup) dialog.findViewById(R.id.city_group);
            final ImageView checkMark = (ImageView) dialog.findViewById(R.id.check_mark);

            checkMark.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    // get selected radio button in group
                    int selectedCity = radioCitiesGroup.getCheckedRadioButtonId();

                    // find the radio button returned by ID
                    radioCitiesButton = (RadioButton) findViewById(selectedCity);

                    Toast.makeText(GameStart.this,
                            radioCitiesButton.getText(), Toast.LENGTH_SHORT).show();

                }
            });

            dialog.show();

        }

    }

}
