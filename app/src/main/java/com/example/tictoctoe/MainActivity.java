package com.example.tictoctoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
*  Author: Chun-Wei Chiang
*  Create on: March 19 2019
*  Mail: Chiang@chunwei.org
* */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /* Declare the variable */
    Button[] board_button;
    Button reset;
    Boolean is_player_X;
    Boolean game_over;
    int TURN_COUNT;
    int[][] board_status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initialize the variable */
        board_button = new Button[9];
        board_button[0] = (Button) findViewById(R.id.button1);
        board_button[1] = (Button) findViewById(R.id.button2);
        board_button[2] = (Button) findViewById(R.id.button3);
        board_button[3] = (Button) findViewById(R.id.button4);
        board_button[4] = (Button) findViewById(R.id.button5);
        board_button[5] = (Button) findViewById(R.id.button6);
        board_button[6] = (Button) findViewById(R.id.button7);
        board_button[7] = (Button) findViewById(R.id.button8);
        board_button[8] = (Button) findViewById(R.id.button9);

        if (savedInstanceState != null){
            board_status = (int[][]) savedInstanceState.getSerializable("board");
            TURN_COUNT = (int) savedInstanceState.getInt("TURN_COUNT");
            is_player_X = (Boolean) savedInstanceState.getBoolean("is_player_X");
            game_over = (Boolean)savedInstanceState.getBoolean("game_over");

            for(int i = 0; i < 3; i++){
                for(int j=0; j < 3; j++){
                    if(board_status[i][j] == 1){
                        board_button[ 3*i + j].setText("X");
                        board_button[ 3*i + j].setEnabled(false);
                    }else if(board_status[i][j] == 2){
                        board_button[ 3*i + j].setText("O");
                        board_button[ 3*i + j].setEnabled(false);
                    }
                }
            }

            if (game_over){
                for(int i = 0; i < 3; i++){
                    for(int j=0; j < 3; j++){
                        board_button[ 3*i + j].setEnabled(false);
                    }
                }
            }
        }

        if (board_status == null){
            board_status = new int[3][3];
        }
        if (is_player_X == null){
            is_player_X = true;
        }
        if (game_over == null){
            game_over = false;
        }

        reset = (Button) findViewById(R.id.resetButton);

        for(int i = 0; i < 9; i++){
            board_button[i].setOnClickListener(this);
        }
        reset.setOnClickListener(this);

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("board", board_status);
        savedInstanceState.putInt("TURN_COUNT", TURN_COUNT);
        savedInstanceState.putBoolean("is_player_X", is_player_X);
        savedInstanceState.putBoolean("game_over",game_over);
    }

    @Override
    public void onClick(View view){

        boolean resetButtonPressed = false;

        switch (view.getId()){
            case R.id.button1:
                if(is_player_X){
                    board_button[0].setText("X");
                    board_status[0][0] = 1;
                }else{
                    board_button[0].setText("O");
                    board_status[0][0] = 2;
                }
                board_button[0].setEnabled(false);
                break;
            case R.id.button2:
                if(is_player_X){
                    board_button[1].setText("X");
                    board_status[0][1] = 1;
                }else{
                    board_button[1].setText("O");
                    board_status[0][1] = 2;
                }
                board_button[1].setEnabled(false);
                break;
            case R.id.button3:
                if(is_player_X){
                    board_button[2].setText("X");
                    board_status[0][2] = 1;
                }else{
                    board_button[2].setText("O");
                    board_status[0][2] = 2;
                }
                board_button[2].setEnabled(false);
                break;
            case R.id.button4:
                if(is_player_X){
                    board_button[3].setText("X");
                    board_status[1][0] = 1;
                }else{
                    board_button[3].setText("O");
                    board_status[1][0] = 2;
                }
                board_button[3].setEnabled(false);
                break;
            case R.id.button5:
                if(is_player_X){
                    board_button[4].setText("X");
                    board_status[1][1] = 1;
                }else{
                    board_button[4].setText("O");
                    board_status[1][1] = 2;
                }
                board_button[4].setEnabled(false);
                break;
            case R.id.button6:
                if(is_player_X){
                    board_button[5].setText("X");
                    board_status[1][2] = 1;
                }else{
                    board_button[5].setText("O");
                    board_status[1][2] = 2;
                }
                board_button[5].setEnabled(false);
                break;
            case R.id.button7:
                if(is_player_X){
                    board_button[6].setText("X");
                    board_status[2][0] = 1;
                }else{
                    board_button[6].setText("O");
                    board_status[2][0] = 2;
                }
                board_button[6].setEnabled(false);
                break;
            case R.id.button8:
                if(is_player_X){
                    board_button[7].setText("X");
                    board_status[2][1] = 1;
                }else{
                    board_button[7].setText("O");
                    board_status[2][1] = 2;
                }
                board_button[7].setEnabled(false);
                break;
            case R.id.button9:
                if(is_player_X){
                    board_button[8].setText("X");
                    board_status[2][2] = 1;
                }else{
                    board_button[8].setText("O");
                    board_status[2][2] = 2;
                }
                board_button[8].setEnabled(false);
                break;
            case R.id.resetButton:
                resetButtonPressed = true;
        }

        if(resetButtonPressed){
            resetGame();
        }else{
            TURN_COUNT++;
            game_over = checkWinner();
            if (game_over){
                gameStop();
            }else{
                if(TURN_COUNT == 9){
                    Toast.makeText(getApplicationContext(), "Game Draw", Toast.LENGTH_LONG).show();
                }else{
                    is_player_X = !is_player_X;
                    if(is_player_X){
                        Toast.makeText(getApplicationContext(), "Player X Turn", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Player O Turn", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private boolean checkWinner(){
        // Horizontal
        for(int i = 0; i < 3; i++){
            if(board_status[i][0] == board_status[i][1] && board_status[i][1] == board_status[i][2]){
                if(board_status[i][0] == 1){
                    Toast.makeText(getApplicationContext(), "Player X win", Toast.LENGTH_LONG).show();
                    return true;
                }else if(board_status[i][0] == 2){
                    Toast.makeText(getApplicationContext(), "Player O win", Toast.LENGTH_LONG).show();
                    return true;
                }
            }
        }

        // Vertical
        for(int i = 0; i < 3; i++){
            if(board_status[0][i] == board_status[1][i] && board_status[1]
                    [i] == board_status[2][i]){
                if(board_status[0][i] == 1){
                    Toast.makeText(getApplicationContext(), "Player X win", Toast.LENGTH_LONG).show();
                    return true;
                }else if(board_status[0][i] == 2){
                    Toast.makeText(getApplicationContext(), "Player O win", Toast.LENGTH_LONG).show();
                    return true;
                }
            }
        }

        // First diagonal
        if(board_status[0][0] == board_status[1][1] && board_status[1][1] == board_status[2][2]){
            if(board_status[0][0] == 1){
                Toast.makeText(getApplicationContext(), "Player X win", Toast.LENGTH_LONG).show();
                return true;
            }else if(board_status[0][0] == 2){
                Toast.makeText(getApplicationContext(), "Player O win", Toast.LENGTH_LONG).show();
                return true;
            }
        }

        // Second diagonal
        if(board_status[0][2] == board_status[1][1] && board_status[1][1] == board_status[2][0]){
            if(board_status[0][2] == 1){
                Toast.makeText(getApplicationContext(), "Player X win", Toast.LENGTH_LONG).show();
                return true;
            }else if(board_status[0][2] == 2){
                Toast.makeText(getApplicationContext(), "Player O win", Toast.LENGTH_LONG).show();
                return true;
            }

        }

        return false;
    }

    private void gameStop(){
        for(int i=0; i<9; i++) {
            board_button[i].setEnabled(false);
        }
    }

    private void resetGame(){
        for(int i=0; i<9; i++) {
            board_button[i].setText("");
            board_button[i].setEnabled(true);
        }

        is_player_X = true;
        TURN_COUNT = 0;
        board_status = new int[3][3];
        game_over = false;
        Toast.makeText(getApplicationContext(), "Board Restart", Toast.LENGTH_SHORT).show();
    }

}
