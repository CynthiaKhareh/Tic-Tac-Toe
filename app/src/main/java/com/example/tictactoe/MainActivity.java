package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private TextView  playerX_Score;
private TextView  playerY_Score;

private Button [][] buttons = new Button[3][3];
private Button resetGame;

private int playerX_ScoreCount = 0;
private int playerY_ScoreCount = 0;
private int roundCount = 0;

private boolean activePlayer = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerX_Score=(TextView)findViewById(R.id.tvPlayerX_Score);
        playerY_Score=(TextView)findViewById(R.id.tvPlayerY_Score);

        for(int i =0; i <3; i++){
            for(int j =0; j<3; j++){
                String ButtonID = "button_" + i + j;
                int resID = getResources().getIdentifier(ButtonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        resetGame=(Button)findViewById(R.id.button_Reset);
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                playerX_ScoreCount = 0;
                playerY_ScoreCount = 0;
                updatePlayerScore();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }
        if(activePlayer){
            ((Button) v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#FFC34A"));
        }
        else {
            ((Button) v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#70FFEA"));
        }
        roundCount++;

        if(checkWinner()){
            if (activePlayer) {
                playerX_ScoreCount++;
                updatePlayerScore();
                Toast.makeText(this,"Player X Won!!",Toast.LENGTH_SHORT).show();
            }
            else {
                playerY_ScoreCount++;
                updatePlayerScore();
                Toast.makeText(this,"Player Y Won!!",Toast.LENGTH_SHORT).show();
            }
            playAgain();
        }
        else if(roundCount == 9) {
            Toast.makeText(this,"Draw ...No winner!!",Toast.LENGTH_SHORT).show();
            playAgain();
        }
        else{
            activePlayer = !activePlayer;
        }
    }
    private boolean checkWinner(){
        String[][] field = new String[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for(int i=0; i<3; i++){
            if(field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for(int i=0; i<3; i++){
            if(field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        if(field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if(field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }
    public void updatePlayerScore(){
        playerX_Score.setText("PLAYER X:" + playerX_ScoreCount);
        playerY_Score.setText("PLAYER Y:" +playerY_ScoreCount);
    }
    public void playAgain(){
        roundCount=0;
        activePlayer=true;

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                buttons [i][j].setText("");
            }
        }
    }
}

