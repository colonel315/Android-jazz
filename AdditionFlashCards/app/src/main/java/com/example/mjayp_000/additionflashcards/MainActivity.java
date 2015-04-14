/*
 * Author: Michael-James Parsons
 * Course: CSC 309
 * Due Date: 2-24-15
 * Description: A basic flash card app that adds two numbers.
 */
package com.example.mjayp_000.additionflashcards;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get the text field that contains the first number
        TextView top_number = (TextView)findViewById(R.id.top_number);
        //get the text field that contains the last number
        TextView bottom_number = (TextView)findViewById(R.id.bottom_number);
        //get the text field that contains the submitted answer
        TextView answer_field = (TextView)findViewById(R.id.answer);
        //get the submit button field
        Button submit_button = (Button)findViewById(R.id.submit_button);
        //generate a new flash card and store its answer in this variable
        int answer = this.resetFlashCard(top_number, bottom_number, answer_field);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Generate a new flash card, and return the sum of the two new numbers.
     *
     * @param top_number - the text field that contains the first number to add
     * @param bottom_number - the text field that contains the second number to add
     * @param answer_field - the text field that contains the submitted answer
     * @return int - the sum of top_number and bottom_number
     */
    public int resetFlashCard(TextView top_number, TextView bottom_number, TextView answer_field) {
        //generate two numbers between 1 and 20 inclusive
        Random rand = new Random();
        int new_top_number = rand.nextInt(20) + 1;
        int new_bottom_number = rand.nextInt(20) + 1;

        //set the value of each text value to the appropriate number (generated above)
        top_number.setText(new_top_number + "");
        bottom_number.setText(new_bottom_number + "");
        //wipe the answer field
        answer_field.setText("");

        //return the sum of the two numbers
        return new_top_number + new_bottom_number;
    }


    /**
     *
     * @param v - android View object
     */
    public void processAnswer(View v) {
        //get all of the text fields from the view
        TextView top_number = (TextView)findViewById(R.id.top_number);
        TextView bottom_number = (TextView)findViewById(R.id.bottom_number);
        TextView answer_field = (TextView)findViewById(R.id.answer);
        //Lets make Toast!
        Toast notice = new Toast(this);

        try {
            //was the submitted answer correct
            if (isAnswerCorrect(
                    Integer.parseInt(answer_field.getText().toString()),
                    (Integer.parseInt(top_number.getText().toString()) + Integer.parseInt(bottom_number.getText().toString()))
                )) {
                //Toast the user for their good work!
                notice.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                this.resetFlashCard(top_number, bottom_number, answer_field);
            } else {
                //The user inputted the wrong answer. Shun them with burnt toast!
                notice.makeText(getApplicationContext(), "Incorrect: Try again!", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            //Either the user didn't type anything in the answer field,
            //or they are magic and found a way to insert non-numeric characters into a 'number field'.
            notice.makeText(getApplicationContext(), "Please enter a number.", Toast.LENGTH_LONG).show();
        }
    }


    /**
     * Check if the two given numbers are equivalent.
     * @param answer_field - the number submitted as the answer
     * @param answer - the actual answer
     * @return boolean - True if the answers match
     */
    public boolean isAnswerCorrect(int answer_field, int answer) {
        if(answer_field == answer) {
            return true;
        } else {
            return false;
        }
    }
}
