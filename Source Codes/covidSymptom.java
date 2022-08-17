package com.mc2022.template;

public class covidSymptom {
    private final String symptomName;
    private String symptomState;

    public String getSymptomState() {
        return symptomState;
    }

    public void setSymptomState(String symptomState) {
        this.symptomState = symptomState;
    }

    covidSymptom(String symptomName, String symptomState){
        this.symptomName = symptomName;
        this.symptomState = symptomState;
    }

    public String getSymptomName() {
        return symptomName;
    }

}
