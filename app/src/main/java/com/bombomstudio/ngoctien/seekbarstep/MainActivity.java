package com.bombomstudio.ngoctien.seekbarstep;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    SeekBarStep mSeekBarStep;
    TextView mTextView;

    EditText mEDMax;
    EditText mEDMin;
    EditText mEDStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSeekBarStep = (SeekBarStep) findViewById(R.id.mySeekBarStep);
        mTextView = (TextView)findViewById(R.id.myValue);
        mEDMax = (EditText)findViewById(R.id.editTextMax);
        mEDMin = (EditText)findViewById(R.id.editTextMin);
        mEDStep = (EditText)findViewById(R.id.editTextStep);

        try {
            mSeekBarStep.setMaxMin(100, 35, 1);
        }catch (SeekBarStepException e){

        }

        mSeekBarStep.setOnSeekBarStepChangeListener(new SeekBarStep.OnSeekBarStepChangeListener() {
            @Override
            public void onProgressChanged(float progress) {
                int value = (int) progress;
                mTextView.setText("" + value);
            }

            @Override
            public void onStartTrackingTouch(float progress) {

            }

            @Override
            public void onStopTrackingTouch(float progress) {

            }
        });
    }

    public void pressButonSet(View v){
        int max, min, step;
        String input;
        input = mEDMax.getText().toString().trim();
        
        if(input.length() <= 0){
            return;
        }

        max = Integer.parseInt(input);

        input = mEDMin.getText().toString().trim();

        if(input.length() <= 0){
            return;
        }
        min = Integer.parseInt(input);


        input = mEDStep.getText().toString().trim();
        if(input.length() <= 0){
            return;
        }
        step = Integer.parseInt(input);

        try {
            mSeekBarStep.setMaxMin(max, min, step);
            mSeekBarStep.setCurrentProgress(min);
        }catch (SeekBarStepException e){

        }
        Toast.makeText(this, "Set OK", Toast.LENGTH_SHORT).show();


    }
}
