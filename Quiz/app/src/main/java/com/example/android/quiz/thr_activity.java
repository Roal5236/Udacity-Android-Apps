package com.example.android.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class thr_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thr_activity);
    }

    public void set_img1(View view) {

        ImageView ib = (ImageView)findViewById(R.id.initial_back);
        ib.setImageResource(R.drawable.x1);

    }

    public void set_img2(View view) {

        ImageView ib = (ImageView)findViewById(R.id.initial_back);
        ib.setImageResource(R.drawable.x2);
    }


    public void set_img3(View view) {

        ImageView ib = (ImageView)findViewById(R.id.initial_back);
        ib.setImageResource(R.drawable.x3);
    }


    public void set_img4(View view) {

        ImageView ib = (ImageView)findViewById(R.id.initial_back);
        ib.setImageResource(R.drawable.x4);
    }

    public void set_default(View view) {

        ImageView ib = (ImageView)findViewById(R.id.initial_back);
        ib.setImageResource(R.drawable.back8);
    }

    public void check(View view){

        EditText et =(EditText)findViewById(R.id.answer);
        if(et.getText().toString().equals("X-Wing")){

            Toast toast = Toast.makeText(getApplicationContext(), "CORRECT", 5);
            toast.show();

            finalScore fs1 = new finalScore();
            fs1.calfinal(1);

            Intent intent = new Intent(this, four.class);
            startActivity(intent);

        }

        else {

            Toast toast = Toast.makeText(getApplicationContext(), "WRONG", 5);
            toast.show();

            Intent intent = new Intent(this, four.class);
            startActivity(intent);
        }
    }
}
