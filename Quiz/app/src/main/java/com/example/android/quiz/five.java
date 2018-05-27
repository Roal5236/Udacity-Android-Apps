package com.example.android.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class five extends AppCompatActivity {

    int q=0,k=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
    }

    public void color_radio(View view){

        CheckBox chkbx = (CheckBox) findViewById(R.id.option_A);

        if(chkbx.isChecked()) {

            chkbx.setBackground(getDrawable(R.drawable.selected));
        }

        else
        {
            chkbx.setBackground(getDrawable(R.drawable.rounded_textview));
        }
    }

    public void color_radio1(View view){

        CheckBox chkbx = (CheckBox) findViewById(R.id.option_B);

        if(chkbx.isChecked()) {

            chkbx.setBackground(getDrawable(R.drawable.selected));
        }

        else
        {
            chkbx.setBackground(getDrawable(R.drawable.rounded_textview));
        }
    }


    public void color_radio2(View view){

        CheckBox chkbx = (CheckBox) findViewById(R.id.option_C);

        if(chkbx.isChecked()) {

            chkbx.setBackground(getDrawable(R.drawable.selected));
        }

        else
        {
            chkbx.setBackground(getDrawable(R.drawable.rounded_textview));
        }
    }

    public void color_radio3(View view){

        CheckBox chkbx = (CheckBox) findViewById(R.id.option_D);

        if(chkbx.isChecked()) {

            chkbx.setBackground(getDrawable(R.drawable.selected));
        }

        else
        {
            chkbx.setBackground(getDrawable(R.drawable.rounded_textview));
        }
    }

    public void ifCorrect(View view)
    {
        TextView textView = (TextView)findViewById(R.id.question);
        TextView qno = (TextView)findViewById(R.id.q_no);
        ImageView iv = (ImageView)findViewById(R.id.back_cover);
        CheckBox chkbx1 = (CheckBox) findViewById(R.id.option_A);
        CheckBox chkbx2 = (CheckBox) findViewById(R.id.option_B);
        CheckBox chkbx3 = (CheckBox) findViewById(R.id.option_C);
        CheckBox chkbx4 = (CheckBox) findViewById(R.id.option_D);
        finalScore fs1 = new finalScore();


        if(chkbx1.isChecked() && chkbx2.isChecked() && !chkbx3.isChecked() && !chkbx4.isChecked()) {


            Toast toast = Toast.makeText(getApplicationContext(), "CORRECT", 5);
            toast.show();

            textView.setText("Characters in Starwars 7?");
            qno.setText("8/8");
            chkbx1.setText("FN-2187 ");
            chkbx2.setText("R2-D2 ");
            chkbx3.setText("Darth Vader ");
            chkbx4.setText("Rey");
            iv.setImageResource(R.drawable.images2);

            fs1.calfinal(1);

            q++;
            k++;

        }

        else if(q==0 && k==0){
            Toast toast = Toast.makeText(getApplicationContext(), "WRONG", 5);
            toast.show();

            textView.setText("Characters in Starwars 7?");
            qno.setText("8/8");
            chkbx1.setText("FN-2187 ");
            chkbx2.setText("R2-D2 ");
            chkbx3.setText("Darth Vader ");
            chkbx4.setText("Rey");


            q++;
        }


        else if(q==1 && chkbx1.isChecked() && !chkbx2.isChecked() && !chkbx3.isChecked() && chkbx4.isChecked()){

            Toast toast = Toast.makeText(getApplicationContext(), "CORRECT", 2);
            toast.show();

            fs1.calfinal(1);


            Intent in = new Intent(this, finalScore.class);
            startActivity(in);

        }



        else {
            Toast toast = Toast.makeText(getApplicationContext(), "WRONG", 5);
            toast.show();

            Intent in = new Intent(this, finalScore.class);
            startActivity(in);

        }

    }

}
