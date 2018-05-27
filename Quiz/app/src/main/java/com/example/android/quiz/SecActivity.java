package com.example.android.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SecActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
    }

    public void Check_answer(View view) {

        EditText et =(EditText)findViewById(R.id.answer);

        if(et.getText().toString().equals("Asoka Tano")){

            Toast toast = Toast.makeText(getApplicationContext(), "CORRECT", 5);
            toast.show();

            finalScore fs1 = new finalScore();
            fs1.calfinal(1);

            Intent intent = new Intent(this, thr_activity.class);
            startActivity(intent);


        }

        else{
            Toast toast = Toast.makeText(getApplicationContext(), "WRONG", 5);
            toast.show();

            Intent in = new Intent(this, thr_activity.class);
            startActivity(in);
        }
    }
}