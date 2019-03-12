package edu.purdue.bartleyt.complex_counter;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.*;

import java.util.ArrayList;

public class Counter extends AppCompatActivity {
    private int counterValue;
    Button countBtn, doneBtn;
    EditText counterText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        /**
         * Get extra from mainactivity if it exists
         * If not, set counter value to 0
         */
        try {
            counterValue = getIntent().getIntExtra("COUNTER_VALUE", 0);
        }catch (NullPointerException e){
            counterValue = 0;
        }

        /**
         * Initialize buttons
         */
        countBtn = (Button) findViewById(R.id.countBtn);
        doneBtn = (Button) findViewById(R.id.doneBtn);
        counterText = (EditText) findViewById(R.id.counterText);
        counterText.setText(""+counterValue);


        countBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++counterValue;
                counterText.setText(""+counterValue);
            }
        });

        counterText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction()!=KeyEvent.ACTION_DOWN)
                    return false;
                if(keyCode == KeyEvent.KEYCODE_ENTER ){
                    String newVal = counterText.getText().toString();
                    counterValue = Integer.parseInt(newVal);
                    return true;
                }
                return false;
            }
        });


        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent done = new Intent(getApplicationContext(), MainActivity.class);
                counterValue = Integer.parseInt(counterText.getText().toString());
                done.setData(Uri.parse(getCounterValue()+""));
                setResult(RESULT_OK, done);
                finish();
            }
        });


    }

    public int getCounterValue() {
        return counterValue;
    }
}
