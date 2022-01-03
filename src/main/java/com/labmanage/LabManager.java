package com.labmanage;

import java.util.ArrayList;

public class LabManager {
    MyDataBase dataBase=null;
    public LabManager(){
        dataBase = new MyDataBase();
        dataBase.connectSQL();
    }
    protected void finalize(){
        dataBase.disconnectSQL();
    }
    public String queryPersonList(){
        return dataBase.getPersonList();
    }
    public String queryPersonInfo(String personID){
        String result = "";
        ArrayList<String> basicInfo = dataBase.getPersonInfo(personID);
        result += String.format("ID:%s  \tNickname:%s\nAge:%d \tGender:%s\n",
                basicInfo.get(0),basicInfo.get(1),Integer.parseInt(basicInfo.get(2)),basicInfo.get(3));
        ArrayList<String> relationInfo = dataBase.getPersonRelationship(personID);
        result += "Phenotype:\n";
        ArrayList<String> phenoInfo = dataBase.getPersonPersonPhenotype(personID);
        for(int i=0;i<phenoInfo.size();i+=3){
            result+=String.format("%s\n",phenoInfo.get(i+1));
        }
        result += "Relationship:\n";
        for(int i=0;i<relationInfo.size();i+=4){
            result+=String.format("ID:%s\tRelation:%s\n",relationInfo.get(i+0),relationInfo.get(i+2));
        }
        ArrayList<String> fileInfo = dataBase.getPersonFile(personID);
        result += "Files:\n";
        for(int i=0;i<fileInfo.size();i+=5){
            result+=String.format("path:%s\ttag:%s\n",fileInfo.get(i+1),relationInfo.get(i+2));
        }
        return result;
    }
}
