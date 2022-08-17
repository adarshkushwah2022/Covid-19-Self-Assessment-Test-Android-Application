package com.mc2022.template;

public class user {
    private String userName;
    private int positiveSymptomsCount;

    user(String userName,int positiveSymptomsCount){
        this.userName=userName;
        this.positiveSymptomsCount=positiveSymptomsCount;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getPositiveSymptomsCount() {
        return positiveSymptomsCount;
    }

    public void setPositiveSymptomsCount(int positiveSymptomsCount) {
        this.positiveSymptomsCount = positiveSymptomsCount;
    }




}
