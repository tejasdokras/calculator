

package com.projnibbas.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

import java.lang.*;
public class MainActivity extends Activity {

    // Ids of all the number/digit buttons
    int[] numberButtonIds = { R.id.point,R.id.b0, R.id.b1, R.id.b2, R.id.b3,
            R.id.b4, R.id.b5, R.id.b6, R.id.b7,
            R.id.b8 ,R.id.b9 };

    // Ids of all the operators
    int[] opButtonIds = { R.id.b_add, R.id.b_subtract, R.id.b_div, R.id.b_mul, R.id.b_equal,R.id.b_sin,R.id.b_cos,R.id.b_exp, R.id.b_e };

    Button numberButtons[] = new Button[11];    // An array of 11 number buttons
    Button opButtons[] = new Button[9];        // An array of 9 op buttons
    TextView resultBox;                         // The result text box
    Button clearButton;


    // Variables for the functioning of the Calculator
    double prevNum;         // Tells me the previous number
    String prevOp;          // Tells me the previous operator clicked
    boolean opClicked;      // Tells me if an operator was clicked


// This is the onCreate() function we talked about. This is the function called when the
// Activity is first loaded onto the screen.
// !! REMEMBER: you can use findViewById() only once the Activity is loaded, hence, you can
// initialize UI elements (Buttons and stuff) only after this function is called, or when this
// function is called.
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //Initialise the variables
    prevNum = 0;
    prevOp = "=";           // Initially, assume '=' is the operator
    opClicked = false;      // Initially, we assume no operator is clicked
// Initialize the clear
    clearButton = findViewById(R.id.b_clear);

    // Initialize the TextView
    resultBox = findViewById(R.id.result);

    // Initialize all the number buttons
    for(int i = 0; i < 11; i++){
        // Assigning the button referenced by the id to the button variable (in the array)
        numberButtons[i] = findViewById(numberButtonIds[i]);

        // Setting the click listener on each button
        // numberListener is defined on Line 82
        numberButtons[i].setOnClickListener(numberListener);
    }

    //Initialize all the op buttons
    for(int i = 0; i < 9; i++){
        opButtons[i] = findViewById(opButtonIds[i]);
        opButtons[i].setOnClickListener(opListener);
    }

    //Set a click listener for the clear button
    clearButton.setOnClickListener(new View.OnClickListener() {

        // Clearing all the data here
        @Override
        public void onClick(View v) {
            prevNum = 0;
            prevOp = "=";
            resultBox.setText("0");
        }
    });
}
    View.OnClickListener numberListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String buttonText = ((Button) v) // Typecast the view into a Button
                    .getText().toString();

            // Convert the string into a number
            double numberClicked = Double.parseDouble(buttonText);
            // Do the following steps only if a numberButton was clicked earlier
            if (!opClicked) {
                // Get the number in the result box
                double numberInBox = Double.parseDouble(resultBox.getText().toString());
                numberInBox = numberInBox * 10 + numberClicked;
                resultBox.setText(Double.toString(numberInBox));
            }
            else{
                resultBox.setText(Double.toString(numberClicked));
            }

            opClicked = false; // A number was clicked now
        }
    };
    View.OnClickListener opListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Get the number in the result box
            double numberInBox = Double.parseDouble(resultBox.getText().toString());


            // Perform necessary operation depending on the operator clicked
            switch (prevOp){
                case "+":
                    prevNum += numberInBox;
                    break;
                case "-":
                    prevNum -= numberInBox;
                    break;

                case "x":
                    prevNum *= numberInBox;
                    break;

                case "/":
                    prevNum /= numberInBox;
                    break;

                case "=":
                    prevNum = numberInBox;
                    break;
                case "SIN" :
                    prevNum = Math.sin(numberInBox); // numberInBox in radian
                    break;
                case "COS" :
                    prevNum = Math.cos(numberInBox); // numberInBox in radian
                    break;
                case "^":
                    prevNum = Math.pow (prevNum , numberInBox);
                case "e":
                    prevNum  = Math.exp(numberInBox);


            }
            opClicked = true; // An operator was clicked now
            resultBox.setText(Double.toString(prevNum));
            prevOp = ((Button) v).getText().toString();
        }
    };
}


