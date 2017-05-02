package com.gok.luisanillo.kingsconquest;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.w3c.dom.Text;


public class GameStart extends AppCompatActivity {

    int status = 0;

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
            historyText.setMovementMethod(new ScrollingMovementMethod());

        }

    }

}
