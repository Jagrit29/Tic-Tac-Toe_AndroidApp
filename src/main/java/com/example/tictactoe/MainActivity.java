package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //0 for yellow  , 1 for red , 2 represent that the imageview or box is empty
    int activePlayer=0;

    //Game State;

    int[] gameState={2,2,2,2,2,2,2,2,2};


    //2d array of winning position;

    int[][] winningPositions={{0,1,2} , {3,4,5} , {6,7,8} , {0,3,6} ,{1,4,7}, {2,5,8} , {0,4,8} , {2,4,6} };

    //To stop the game

    boolean gameActive=true;

    public void dropIn(View view)
    {
        //here we are first making a counter variable for imageview which is clicked
        ImageView counter = (ImageView) view;

        int tappedCounter=Integer.parseInt(counter.getTag().toString());  //we are getting the int value of Tag which is

        if(gameState[tappedCounter]==2 && gameActive) {  //checking whether the box is empty on which user click. this will happen only if its empty and game is active

            gameState[tappedCounter] = activePlayer;          //setting the imageview in array either equal to 0 or 1;


            //counter which is storing the current image view is made disappeared original from screen by -1500
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow); //setting the image to the current image view
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red); //setting the image to the current image view
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);//bringing that image to its place


            //checking whether wining Condition is reached or not

            for (int[] winningPosition : winningPositions)   //here winningPosition is array of each element of winningPositions
            {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2)
                //here we put only one != condition because we have already check whether they are all equal and if equal then if just one is !=2 all will be not equal to 2;
                {
                    //Someone Has won!;
                    gameActive=false;
                    //As we have already changed the value of activeplayer in if else above , so it winner will be opposite to the current value;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";

                    } else {
                        winner = "Red";
                    }

                    Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner+" has won!");
                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
            //draw
            int draw=1;
            for(int i=0;i<gameState.length;i++)
            {
                if(gameState[i]==2)
                {
                    draw=0;
                    break;
                }
            }
            if(draw==1 && gameActive )
            {
                Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
                TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);
                winnerTextView.setText("Its a Draw!");
                playAgainButton.setVisibility(View.VISIBLE);

                winnerTextView.setVisibility(View.VISIBLE);
            }


        }

    }

    public void playAgain(View view)
    {
        Button playAgainButton=(Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);


        //removing all the filled boxes

        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);

        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView counter= (ImageView) gridLayout.getChildAt(i); //traversing through all the boxes or imageviews
            counter.setImageDrawable(null);  //removing image src from all
        }

        activePlayer=0;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        gameActive=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

