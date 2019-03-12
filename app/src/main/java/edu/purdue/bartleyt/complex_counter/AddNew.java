package edu.purdue.bartleyt.complex_counter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNew extends AppCompatActivity {
    Button setBtn;
    EditText newCounterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        setBtn = (Button) findViewById(R.id.setBtn);
        newCounterText = (EditText) findViewById(R.id.newCounterText);

        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setData(Uri.parse(newCounterText.getText().toString()));
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }

}
