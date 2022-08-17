package com.mc2022.template;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private RadioButton yesButton,noButton;
    private TextView userNameField, symptomTextField;
    RadioGroup rgg;

    private final covidSymptom[] userSymptoms = new covidSymptom[]{
            new covidSymptom("Fever","NA"),
            new covidSymptom("Cough","NA"),
            new covidSymptom("Body pain","NA"),
            new covidSymptom("Congestion","NA"),
            new covidSymptom("Headache","NA")
    };
    private final user U = new user("",0);

    private  int currentSymptomIndex = -1;
    private String [] symptomStatus= new String[]{"NA","NA","NA","NA","NA"};
    private int positiveSymptoms;

    boolean stopToCreate=false;
    boolean restartToStart=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(stopToCreate){
            Toast.makeText(MainActivity.this,"State of Main activity changed from Stop to Create", Toast.LENGTH_SHORT).show();
            Log.i("lifecycle","State of Main activity changed from Stop to Create");
            stopToCreate=false;
        }else{
            Toast.makeText(MainActivity.this,"State of Main activity is Created", Toast.LENGTH_SHORT).show();
            Log.i("lifecycle","State of Main activity is Created");
        }
        restartToStart=false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yesButton=findViewById(R.id.yesButton);
        noButton= findViewById(R.id.noButton);
        Button nextButton = findViewById(R.id.nextButton2);
        Button clearButton = findViewById(R.id.clearButton);
        Button submitButton = findViewById(R.id.submitButton);
        userNameField =findViewById(R.id.enterUsernameTextField);
        symptomTextField =findViewById(R.id.symtomValueTextField);
        rgg=findViewById(R.id.radioGroup);

        if(savedInstanceState!=null){
            U.setUserName(savedInstanceState.getString("userName"));
            userNameField.setText(U.getUserName());
            currentSymptomIndex =savedInstanceState.getInt("symptomId")-1;
            symptomStatus=savedInstanceState.getStringArray("symptomStatus");
        }
        updateSymptom();

        yesButton.setOnClickListener(view -> {
            if(userNameField.getText().toString().matches("")){
                Toast.makeText(MainActivity.this,"Please enter Username", Toast.LENGTH_SHORT).show();
            }else {
                U.setUserName(userNameField.getText().toString());
            }
            userSymptoms[currentSymptomIndex].setSymptomState("Yes");
            symptomStatus[currentSymptomIndex]=userSymptoms[currentSymptomIndex].getSymptomState();
        });

        noButton.setOnClickListener(view -> {
            if(userNameField.getText().toString().matches("")){
                Toast.makeText(MainActivity.this,"Please enter Username", Toast.LENGTH_SHORT).show();
            }else {
                U.setUserName(userNameField.getText().toString());
            }
            userSymptoms[currentSymptomIndex].setSymptomState("No");
            symptomStatus[currentSymptomIndex]=userSymptoms[currentSymptomIndex].getSymptomState();
        });

        nextButton.setOnClickListener(view -> {
            if(userNameField.getText().toString().matches("")){
                Toast.makeText(MainActivity.this,"Please enter Username", Toast.LENGTH_SHORT).show();
            }else{
                if(!yesButton.isChecked() && !noButton.isChecked()){
                    Toast.makeText(MainActivity.this,"Please select Yes or No", Toast.LENGTH_SHORT).show();
                }else{
                    if(currentSymptomIndex == 4){
                        String allAnswerGivenMessage = "This was the last symptom, please press submit button to proceed.";
                        Toast.makeText(MainActivity.this,allAnswerGivenMessage, Toast.LENGTH_SHORT).show();
                    }else{
                        updateSymptom();
                    }
                }
            }
        });

        clearButton.setOnClickListener(view -> {
            for(int i = 0; i< 5; i++){
                userSymptoms[i].setSymptomState("NA");
                symptomStatus[i]=userSymptoms[i].getSymptomState();
            }
            U.setUserName("");
            userNameField.setText(U.getUserName());
            currentSymptomIndex =-1;
            yesButton.setChecked(false);
            noButton.setChecked(false);
            updateSymptom();
        });

        submitButton.setOnClickListener(view -> {
            if(userNameField.getText().toString().matches("")){
                Toast.makeText(MainActivity.this,"Please enter Username", Toast.LENGTH_SHORT).show();
            }else{
                U.setUserName(userNameField.getText().toString());
                positiveSymptoms=0;
                for(int i=0;i<5;i++){
                    if(symptomStatus[i].matches("Yes")){
                        positiveSymptoms++;
                    }
                }
                U.setPositiveSymptomsCount(positiveSymptoms);
                Intent intentOne = new Intent(getApplicationContext(),SecondActivity.class);
                intentOne.putExtra("positiveSymptoms",U.getPositiveSymptomsCount());
                intentOne.putExtra("userName", U.getUserName());
                intentOne.putExtra("symptomStatus",symptomStatus);
                startActivity(intentOne);
            }
        });

        U.setUserName(userNameField.getText().toString());
        Toast.makeText(MainActivity.this,"State of Main activity changed from Create to Start", Toast.LENGTH_SHORT).show();
        Log.i("lifecycle","State of Main activity changed from Create to Start");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("userName",U.getUserName());
        savedInstanceState.putInt("symptomId", currentSymptomIndex);
        savedInstanceState.putStringArray("symptomStatus",symptomStatus);
    }
    private void updateSymptom(){
        rgg.clearCheck();
        currentSymptomIndex =(currentSymptomIndex +1) % userSymptoms.length;
        symptomTextField.setText(userSymptoms[currentSymptomIndex].getSymptomName());
    }
    @Override
    protected void onStart() {
        if(restartToStart) {
            Toast.makeText(MainActivity.this, "State of Main activity changed from Restart to Start", Toast.LENGTH_SHORT).show();
            Log.i("lifecycle", "State of Main activity changed from Restart to Start");
        }
        super.onStart();
        Toast.makeText(MainActivity.this,"State of Main activity changed from Start to Resume", Toast.LENGTH_SHORT).show();
        Log.i("lifecycle","State of Main activity changed from Start to Resume");
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        Toast.makeText(MainActivity.this,"State of Main activity changed from Resume to Pause", Toast.LENGTH_SHORT).show();
        Log.i("lifecycle","State of Main activity changed from Resume to Pause");
        super.onPause();
    }
    @Override
    protected void onStop() {
        Toast.makeText(MainActivity.this,"State of Main activity changed from Pause to Stop", Toast.LENGTH_SHORT).show();
        Log.i("lifecycle","State of Main activity changed from Pause to Stop");
        super.onStop();
        stopToCreate=true;
    }
    @Override
    protected void onRestart() {
        Toast.makeText(MainActivity.this,"State of Main activity changed from Stop to Restart", Toast.LENGTH_SHORT).show();
        Log.i("lifecycle","State of Main activity changed from Stop to Restart");
        super.onRestart();
        restartToStart=true;
    }
    @Override
    protected void onDestroy() {
        Toast.makeText(MainActivity.this,"State of Main activity changed from Stop to Destroy", Toast.LENGTH_SHORT).show();
        Log.i("lifecycle","State of Main activity changed from Stop to Destroy");
        stopToCreate=false;
        super.onDestroy();
    }
}