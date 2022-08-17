package com.mc2022.template;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
//import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private TextView testRequirement;
    private boolean testButtonClicked;
    int positiveSymptoms;
    boolean stopToCreate=false;
    boolean restartToStart=false;
    @SuppressLint({"SetTextI18n", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(stopToCreate){
            Toast.makeText(SecondActivity.this,"State of Second activity changed from Stop to Create", Toast.LENGTH_SHORT).show();
            Log.i("lifecycle","State of Second activity changed from Stop to Create");
            stopToCreate=false;
        }else{
            Toast.makeText(SecondActivity.this,"State of Second activity is Created", Toast.LENGTH_SHORT).show();
            Log.i("lifecycle","State of Second activity is Created");
        }
        restartToStart=false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView userName = findViewById(R.id.enterUserNameTextField2);
        TextView feverField = findViewById(R.id.feverAnswerTextField);
        TextView coughField = findViewById(R.id.coughAnswerTextField);
        TextView bodyPainField = findViewById(R.id.bodyPainAnswerTextField);
        TextView congestionField = findViewById(R.id.congestionAnswerTextField);
        TextView headacheField = findViewById(R.id.headacheAnswerTextField);
        Button checkTestButton = findViewById(R.id.checkCovidTestButton);
        testRequirement=findViewById(R.id.testRequirementTextField);

        Bundle extras = getIntent().getExtras();
        user u1 = new user(extras.getString("userName"),extras.getInt("positiveSymptoms"));

        userName.setText(u1.getUserName());
        positiveSymptoms=u1.getPositiveSymptomsCount();

        if(savedInstanceState!=null){
            testButtonClicked=savedInstanceState.getBoolean("testButtonClicked");
            if(testButtonClicked){
                setTestResult(positiveSymptoms);
            }
        }
        String [] symptomStatus=extras.getStringArray("symptomStatus");
        TextView[] temp=new TextView[]{feverField, coughField, bodyPainField, congestionField, headacheField};
        for(int i=0;i<5;i++){
            temp[i].setText(symptomStatus[i]);
            if(symptomStatus[i].matches("Yes")){
                temp[i].setTextColor(Color.rgb(206,15,62));
            }else if(symptomStatus[i].matches("No")){
                temp[i].setTextColor(Color.rgb(5,155,78));
            }
        }
        checkTestButton.setOnClickListener(view -> {
            testButtonClicked=true;
            setTestResult(positiveSymptoms);
        });

        Toast.makeText(SecondActivity.this,"State of Second activity changed from Create to Start", Toast.LENGTH_SHORT).show();
        Log.i("lifecycle","State of Second activity changed from Create to Start");
    }

    public void onSaveInstanceState(@NonNull Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("positiveSymptomsSaved",positiveSymptoms);
        savedInstanceState.putBoolean("testButtonClicked",testButtonClicked);
    }

    @SuppressLint("SetTextI18n")
    public void setTestResult(int positiveSymptoms){
        if(positiveSymptoms > 3){
            testRequirement.setText("Required");
            testRequirement.setTextColor(Color.rgb(206,15,62));
        }else{
            testRequirement.setText("Not Required");
            testRequirement.setTextColor(Color.rgb(5,155,78));
        }
    }
    @Override
    protected void onStart() {
        if(restartToStart) {
            Toast.makeText(SecondActivity.this, "State of Second activity changed from Restart to Start", Toast.LENGTH_SHORT).show();
            Log.i("lifecycle", "State of Second activity changed from Restart to Start");
        }
        super.onStart();
        Toast.makeText(SecondActivity.this,"State of Second activity changed from Start to Resume", Toast.LENGTH_SHORT).show();
        Log.i("lifecycle","State of Second activity changed from Start to Resume");

    }
    @Override
    protected void onResume() {
        super.onResume();

    }
    @Override
    protected void onPause() {
        Toast.makeText(SecondActivity.this,"State of Second activity changed from Resume to Pause", Toast.LENGTH_SHORT).show();
        Log.i("lifecycle","State of Second activity changed from Resume to Pause");
        super.onPause();
    }
    @Override
    protected void onStop() {
        Toast.makeText(SecondActivity.this,"State of Second activity changed from Pause to Stop", Toast.LENGTH_SHORT).show();
        Log.i("lifecycle","State of Second activity changed from Pause to Stop");
        super.onStop();
        stopToCreate=true;
    }
    @Override
    protected void onRestart() {
        Toast.makeText(SecondActivity.this,"State of Second activity changed from Stop to Restart", Toast.LENGTH_SHORT).show();
        Log.i("lifecycle","State of Second activity changed from Stop to Restart");
        super.onRestart();
        restartToStart=true;
    }
    @Override
    protected void onDestroy() {
        Toast.makeText(SecondActivity.this,"State of Second activity changed from Stop to Destroy", Toast.LENGTH_SHORT).show();
        Log.i("lifecycle","State of Second activity changed from Stop to Destroy");
        stopToCreate=false;
        super.onDestroy();
    }
}