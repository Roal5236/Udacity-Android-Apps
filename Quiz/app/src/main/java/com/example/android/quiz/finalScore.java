package com.example.android.quiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class finalScore extends AppCompatActivity {

    static int q=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);
    }


    public void calfinal(int a){

            q+=1;

    }


    public void smth(View view)
    {

        TextView tx = (TextView) findViewById(R.id.score);
        TextView tx1 = (TextView) findViewById(R.id.character);
        ImageView iv = (ImageView)findViewById(R.id.img_score);
        tx.setText(String.valueOf(q)+"/8");

        Toast toast = Toast.makeText(getApplicationContext(),"You Scored "+String.valueOf(q)+"/8", 5);
        toast.show();

        if(q<=2)
        {
            tx1.setText("CLONE");
            iv.setImageResource(R.drawable.clone);
        }

        if(q>2 && q<=4)
        {
            tx1.setText("PADAWON");
            iv.setImageResource(R.drawable.jedi);
        }

        if(q>4 && q<=6)
        {
            tx1.setText("Sith");
            iv.setImageResource(R.drawable.sith);
        }

        if(q>6 && q<=8)
        {
            tx1.setText("Jedi Master");
            iv.setImageResource(R.drawable.master);
        }
    }
}
