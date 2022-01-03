package com.labmanage;

import java.sql.*;
import java.util.ArrayList;

import com.labmanage.constant.DBConstant;

public class MyDataBase {
    private Connection conn;
    private String url;
    private String user;
    private String password;
    public MyDataBase(){
        url = DBConstant.DATABASE_LINK;
        user = DBConstant.USER_ID;
        password = DBConstant.USER_PW;
    }
    public void connectSQL(){
        try{
            Class.forName(DBConstant.DRIVER_NAME);
        } catch (ClassNotFoundException e1){
            e1.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("connected");
        } catch (SQLException e2){
            e2.printStackTrace();
        }
        return;
    }
    public void disconnectSQL(){
        try{
            if(conn!=null){
                conn.close();
                conn = null;
                System.out.println("disconnected");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return;
    }
    protected Statement createStatement(){
        Statement stmt = null;
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }
    public ResultSet executeSQL(String _statement){
        Statement stmt = this.createStatement();
        ResultSet res = null;
        try{
            boolean flag = stmt.execute(_statement);
            if(flag){
                res =stmt.getResultSet();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
    //
    public String getPersonList(){
        String result = "";
        String command = "Select * From Inheritance.Person;";
        ResultSet res = this.executeSQL(command);
        try{
            ResultSetMetaData tableHead = res.getMetaData();
            res.beforeFirst();
            int cnt = 0;
            while(res.next() && cnt<10){
                result += res.getString("ID") + "\n";
                ++cnt;
            }
            if(cnt==10){
                result += "...\n";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public ArrayList<String> getPersonInfo(String personID){
        ArrayList<String> result = new ArrayList<String>();
        String command = "SELECT * FROM Inheritance.Person Where `ID` = '"+personID+"';";
        ResultSet res = this.executeSQL(command);
        try{
            ResultSetMetaData tableHead = res.getMetaData();
            res.beforeFirst();
            while(res.next()){
                int col = tableHead.getColumnCount();
                for ( int i = 1 ; i <= col ; i++ ){
                    result.add(res.getString(i));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public ArrayList<String> getPersonRelationship(String personID){
        ArrayList<String> result = new ArrayList<String>();
        String command = "SELECT * FROM Inheritance.PersonRelation Where `id2` = '"+personID+"';";
        ResultSet res = this.executeSQL(command);
        try{
            ResultSetMetaData tableHead = res.getMetaData();
            res.beforeFirst();
            while(res.next()){
                int col = tableHead.getColumnCount();
                for ( int i = 1 ; i <= col ; i++ ){
                    result.add(res.getString(i));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public ArrayList<String> getPersonPersonPhenotype(String personID){
        ArrayList<String> result = new ArrayList<String>();
        String command = "SELECT * FROM Inheritance.PersonPhenotype Where `person_id` = '"+personID+"';";
        ResultSet res = this.executeSQL(command);
        try{
            ResultSetMetaData tableHead = res.getMetaData();
            res.beforeFirst();
            while(res.next()){
                int col = tableHead.getColumnCount();
                for ( int i = 1 ; i <= col ; i++ ){
                    result.add(res.getString(i));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public ArrayList<String> getPersonFile(String personID){
        ArrayList<String> result = new ArrayList<String>();
        String command = "SELECT * FROM Inheritance.PersonFile Where `person_id` = '"+personID+"';";
        ResultSet res = this.executeSQL(command);
        try{
            ResultSetMetaData tableHead = res.getMetaData();
            res.beforeFirst();
            while(res.next()){
                int col = tableHead.getColumnCount();
                for ( int i = 1 ; i <= col ; i++ ){
                    result.add(res.getString(i));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
