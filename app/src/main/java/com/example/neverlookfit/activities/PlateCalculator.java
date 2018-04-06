package com.example.neverlookfit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.neverlookfit.R;

public class PlateCalculator extends AppCompatActivity {

    TextView textViewvalue, textView1, textView2, textView3, textView4, textView5;
    private Button buttonMinus1, buttonMinus2, buttonMinus3, buttonMinus4, buttonMinus5,
            buttonAdd1, buttonAdd2, buttonAdd3, buttonAdd4, buttonAdd5;
    private int value = 0;
    private int value45, value35, value25, value10, value5 = 0;
    private int counter45, counter35, counter25, counter10, counter5 = 0;
    private int barWeight = 45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plate_calculator);
        initButtons();

    }

    private void initButtons() {
        buttonMinus1 = (Button) findViewById(R.id.buttonMinus1);
        buttonMinus2 = (Button) findViewById(R.id.buttonMinus2);
        buttonMinus3 = (Button) findViewById(R.id.buttonMinus3);
        buttonMinus4 = (Button) findViewById(R.id.buttonMinus4);
        buttonMinus5 = (Button) findViewById(R.id.buttonMinus5);
        buttonAdd1 = (Button) findViewById(R.id.buttonAdd1);
        buttonAdd2 = (Button) findViewById(R.id.buttonAdd2);
        buttonAdd3 = (Button) findViewById(R.id.buttonAdd3);
        buttonAdd4 = (Button) findViewById(R.id.buttonAdd4);
        buttonAdd5 = (Button) findViewById(R.id.buttonAdd5);

        textViewvalue = (TextView) findViewById(R.id.textViewTotalWeight);
        textViewvalue.setText(Integer.toString(barWeight));
        value = barWeight;

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5);

        buttonMinus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter45 > 0) {
                    value = value - (45 * 2);
                    textViewvalue.setText(Integer.toString(value));
                    value45 = value45 - 2;
                    textView1.setText(Integer.toString(value45));
                    counter45--;
                }
            }
        });
        buttonMinus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter35 > 0) {
                    value = value - (35 * 2);
                    textViewvalue.setText(Integer.toString(value));
                    value35 = value35 - 2;
                    textView2.setText(Integer.toString(value35));
                    counter35--;
                }
            }
        });
        buttonMinus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter25 > 0) {
                    value = value - (25 * 2);
                    textViewvalue.setText(Integer.toString(value));
                    value25 = value25 - 2;
                    textView3.setText(Integer.toString(value25));
                    counter25--;
                }
            }
        });
        buttonMinus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter10 > 0) {
                    value = value - (10 * 2);
                    textViewvalue.setText(Integer.toString(value));
                    value10 = value10- 2;
                    textView4.setText(Integer.toString(value10));
                    counter10--;
                }
            }
        });
        buttonMinus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter5 > 0) {
                    value = value - (5 * 2);
                    textViewvalue.setText(Integer.toString(value));
                    value5 = value5 - 2;
                    textView5.setText(Integer.toString(value5));
                    counter5--;
                }
            }
        });

        buttonAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + (45 * 2);
                textViewvalue.setText(Integer.toString(value));
                value45 = value45 + 2;
                textView1.setText(Integer.toString(value45));
                counter45++;
            }
        });
        buttonAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + (35 * 2);
                textViewvalue.setText(Integer.toString(value));
                value35 = value35 + 2;
                textView2.setText(Integer.toString(value35));
                counter35++;
            }
        });
        buttonAdd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + (25 * 2);
                textViewvalue.setText(Integer.toString(value));
                value25 = value25 + 2;
                textView3.setText(Integer.toString(value25));
                counter25++;
            }
        });
        buttonAdd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + (10 * 2);
                textViewvalue.setText(Integer.toString(value));
                value10 = value10 + 2;
                textView4.setText(Integer.toString(value10));
                counter10++;
            }
        });
        buttonAdd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = value + (5 * 2);
                textViewvalue.setText(Integer.toString(value));
                value5 = value5 + 2;
                textView5.setText(Integer.toString(value5));
                counter5++;
            }
        });

    }
}
