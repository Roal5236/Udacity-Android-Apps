package com.example.android.scoccerkeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int noGoalsA=0;
    int noFoulsA=0;
    int noOfPenaltyA=0;
    int noOfShotsA=0;
    int possisionA,possisionB;
    int noOfGoalB=0;
    int noOfFoulsB=0;
    int noOfPenaltyB=0;
    int noOfShotsB=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {//set all the values of the textViews to 0
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayGoalForTeamA(0);
        displayGoalForTeamB(0);
        displayfoulsA(0);
        displayfoulnoOfShotsB(0);
        displayPenaltyA(0);
        displayPenaltyB(0);
        displayShotA(0);
        displayShotB(0);
        displayPossessionA("-");
        displayPossessionB("-");

    }

    public void goalA(View v){//increase the score of Team A by 1
        noGoalsA+=1;
        displayGoalForTeamA(noGoalsA);
        possA();
        posnoOfShotsB();

    }

    public void goalB(View v){//increase the score of Team B by 1
        noOfGoalB+=1;
        displayGoalForTeamB(noOfGoalB);
        possA();
        posnoOfShotsB();

    }

    public void foulA(View v){//increase the fouls of Team A by 1
        noFoulsA+=1;
        displayfoulsA(noFoulsA);
        possA();
        posnoOfShotsB();
    }

    public void foulB(View v){//increase the fouls of Team B by 1
        noOfFoulsB+=1;
        displayfoulnoOfShotsB(noOfFoulsB);
        possA();
        posnoOfShotsB();
    }

    public void penaltyA(View v){//increase the penalties of Team A by 1
        noOfPenaltyA+=1;
        displayPenaltyA(noOfPenaltyA);
        possA();
        posnoOfShotsB();
    }

    public void penaltyB(View v){//increase the penalties of Team B by 1
        noOfPenaltyB+=1;
        displayPenaltyB(noOfPenaltyB);
        possA();
        posnoOfShotsB();
    }

    public void Shot_A(View v){//increase the shots of Team A by 1
        noOfShotsA+=1;
        displayShotA(noOfShotsA);
        possA();
        posnoOfShotsB();

    }

    public void Shot_B(View v){//increase the shots of Team A by 1
        noOfShotsB+=1;
        displayShotB(noOfShotsB);
        possA();
        posnoOfShotsB();

    }

    public void possA(){//Calculate the possession of Team A

        possisionA=100*(noGoalsA+noOfShotsA+noFoulsA+noOfPenaltyA)/(noGoalsA+noOfGoalB+noOfShotsA+noOfShotsB+noFoulsA+noOfPenaltyA+noOfFoulsB+noOfPenaltyB);
        displayPossessionA(possisionA+"%");
    }

    public void posnoOfShotsB(){//Calculate the possession of Team B

        possisionB=100*(noOfGoalB+noOfShotsB+noOfFoulsB+noOfPenaltyB)/(noGoalsA+noOfGoalB+noOfShotsA+noOfShotsB+noFoulsA+noOfPenaltyA+noOfFoulsB+noOfPenaltyB);
        displayPossessionB(possisionB+"%");

    }

    /**
     * Displays the given score for Team A.
     */
    public void displayGoalForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayGoalForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given fouls for Team A.
     */
    public void displayfoulsA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.foulA);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given fouls for Team B.
     */
    public void displayfoulnoOfShotsB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.foulB);
        scoreView.setText(String.valueOf(score));
    }


    /**
     * Displays the given penalties for Team A.
     */
    public void displayPenaltyA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.penaltyA);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given penalties for Team B.
     */
    public void displayPenaltyB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.penaltyB);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given shots for Team A.
     */
    public void displayShotA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.shotsA);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given shots for Team B.
     */
    public void displayShotB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.shotsB);
        scoreView.setText(String.valueOf(score));
    }

    public void displayPossessionA(String score) {//displays the possession rate for Team A
        TextView scoreView = (TextView) findViewById(R.id.posiA);
        scoreView.setText(String.valueOf(score));
    }


    public void displayPossessionB(String score) {//displays the possession rate for Team B
        TextView scoreView = (TextView) findViewById(R.id.posiB);
        scoreView.setText(String.valueOf(score));
    }

    public void resets(View v){//resets all the values of the textViews to 0

        displayGoalForTeamA(0);
        displayGoalForTeamB(0);
        displayfoulsA(0);
        displayfoulnoOfShotsB(0);
        displayPenaltyA(0);
        displayPenaltyB(0);
        displayShotA(0);
        displayShotB(0);
        displayPossessionA("-");
        displayPossessionB("-");

        noGoalsA=0;
        noFoulsA=0;
        noOfPenaltyA=0;
        noOfShotsA=0;
        possisionA=0;
        possisionB=0;
        noOfGoalB=0;
        noOfFoulsB=0;
        noOfPenaltyB=0;
        noOfShotsB=0;
    }

}
