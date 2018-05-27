package com.example.android.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class four extends AppCompatActivity {

    int q=0,k=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
    }

    public void color_radio(View view) {

        RadioButton rdbtn = (RadioButton) findViewById(R.id.option_A);


        if (rdbtn.isChecked()) {

            rdbtn.setBackground(getDrawable(R.drawable.selected));
        } else {
            rdbtn.setBackground(getDrawable(R.drawable.rounded_textview));
        }
    }

    public void color_radio1(View view) {

        RadioButton rdbtn = (RadioButton) findViewById(R.id.option_B);


        if (rdbtn.isChecked()) {

            rdbtn.setBackground(getDrawable(R.drawable.selected));
        } else {
            rdbtn.setBackground(getDrawable(R.drawable.rounded_textview));
        }
    }


    public void color_radio2(View view) {

        RadioButton rdbtn = (RadioButton) findViewById(R.id.option_C);


        if (rdbtn.isChecked()) {

            rdbtn.setBackground(getDrawable(R.drawable.selected));
        } else {
            rdbtn.setBackground(getDrawable(R.drawable.rounded_textview));
        }
    }

    public void color_radio3(View view) {

        RadioButton rdbtn = (RadioButton) findViewById(R.id.option_D);

        if (rdbtn.isChecked()) {

            rdbtn.setBackground(getDrawable(R.drawable.selected));
        } else {
            rdbtn.setBackground(getDrawable(R.drawable.rounded_textview));
        }
    }

    public void ifCorrect(View view) {

        TextView textView = (TextView)findViewById(R.id.question);
        TextView qno = (TextView)findViewById(R.id.q_no);
        ImageView iv = (ImageView)findViewById(R.id.back_cover);
        RadioButton rdbtn1 = (RadioButton) findViewById(R.id.option_A);
        RadioButton rdbtn2 = (RadioButton) findViewById(R.id.option_B);
        RadioButton rdbtn3 = (RadioButton) findViewById(R.id.option_C);
        RadioButton rdbtn4 = (RadioButton) findViewById(R.id.option_D);
        finalScore fs1 = new finalScore();

        if (q==0 && !rdbtn1.isChecked() && rdbtn2.isChecked() && !rdbtn3.isChecked() && !rdbtn4.isChecked()) {

            Toast toast = Toast.makeText(getApplicationContext(), "CORRECT", 5);
            toast.show();

            textView.setText("What was the Hutt family's home planet?");
            qno.setText("6/8");
            rdbtn1.setText("Tatooine");
            rdbtn2.setText("Geonosis");
            rdbtn3.setText("Nar Shadda");
            rdbtn4.setText("Nal Hutta");
            iv.setImageResource(R.drawable.jaba);

            fs1.calfinal(1);

            q++;
            k++;
        }

        else if(q==0 && k==0) {
            Toast toast = Toast.makeText(getApplicationContext(), "WRONG", 5);
            toast.show();

            textView.setText("What was the Hutt family's home planet?");
            qno.setText("6/8");
            rdbtn1.setText("Tatooine");
            rdbtn2.setText("Geonosis");
            rdbtn3.setText("Nar Shadda");
            rdbtn4.setText("Nal Hutta");
            iv.setImageResource(R.drawable.jaba);

            q++;
        }


        else if(q==1 && rdbtn1.isChecked() && !rdbtn2.isChecked() && !rdbtn3.isChecked() && !rdbtn4.isChecked()){

            Toast toast = Toast.makeText(getApplicationContext(), "CORRECT", 2);
            toast.show();

            Intent in = new Intent(this, five.class);
            startActivity(in);

            fs1.calfinal(1);
        }

            else {
            Toast toast = Toast.makeText(getApplicationContext(), "WRONG", 5);
            toast.show();

            Intent in = new Intent(this, five.class);
            startActivity(in);
        }

    }
}