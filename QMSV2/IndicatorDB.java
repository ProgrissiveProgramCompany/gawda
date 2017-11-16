/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmsv2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import static java.util.Objects.isNull;


/**
 *
 * @author Isra
 */
public class IndicatorDB implements Serializable {

    
         public int selectedID , axesNum;
    	 public static String DBpath = Database_path.Database_Path;
         public Connection conn;
         public static Statement statement;
         static int ExistanceTypeNum  ;
         static int ScaleTypeNum  ;
         static int TextTypeNum  ;
         private int Ind_id ;
	 private int Stand_id ;
	 private int Axes_id ;
         private int Sub_id ;
         private int rate ;


	 private String Questin ;
	 private int type ;
         String textAnswer ;
         int scaleAnswer ;
         boolean exitanceAnswer ;
         static ArrayList<IndicatorDB> arr ;
         ArrayList outer ;
         ArrayList inner ;
       
         static IndicatorDB indicator ;
    IndicatorDB(){}
         
         
 /* @param : standard id , axis id
    @return : array list of indicator      
    method to get all indicator in arraylist object   */  
    public int getStand_id() {
        return Stand_id;
    }

    public void setStand_id(int Stand_id) {
        this.Stand_id = Stand_id;
    }

    public int getAxes_id() {
        return Axes_id;
    }

    public void setAxes_id(int Axes_id) {
        this.Axes_id = Axes_id;
    }         
    public int getInd_id() {
        return Ind_id;
    }

    public void setInd_id(int Ind_id) {
        this.Ind_id = Ind_id;
    }
    public int getSub_id() {
        return Sub_id;
    }

    public void setSub_id(int Sub_id) {
        this.Sub_id = Sub_id;
    }


    

    public String getQuestin() {
        return Questin;
    }

    public void setQuestin(String Questin) {
        this.Questin = Questin;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    public int getScaleAnswer() {
        System.out.println(scaleAnswer);
        return this.scaleAnswer;
    }

    public void setScaleAnswer(int scaleAnswer) {
                System.out.println(scaleAnswer+"inset");
        this.scaleAnswer = scaleAnswer;
    }

    public boolean getExitanceAnswer() {
        return exitanceAnswer;
    }

    public void setExitanceAnswer(boolean exitanceAnswer) {
        this.exitanceAnswer = exitanceAnswer;
    }


    public int getRate() {
        return rate;
    }

    public void setRate(String stringRate) {
       int numRate = 0 ;
       switch(stringRate){
          case "ممتاز": numRate = 4 ;break;
          case "جيد جدا":numRate = 3 ; break;
          case "جيد":numRate = 2 ;break;
          case "ضعيف":numRate = 1 ;break;
          case"قيم اجابتك":numRate=-1;
}    
        this.rate = numRate;
    }    
    
         

static ArrayList<IndicatorDB> getIndicators(int Stand_id, int Axis_id) throws SQLException {
        System.out.println("image that  is not true but a fugment of your >> "+Stand_id+"    "+Axis_id);
    
	Connection conn=DriverManager.getConnection(DBpath);
        statement = conn.createStatement();
        ResultSet result = statement.executeQuery("select * from Question where STAND_ID= "
                +Stand_id+" and AXES_ID = "+Axis_id+";");
           arr = new ArrayList<IndicatorDB>();
          indicator = new IndicatorDB();
          while(result.next()){
          indicator.setStand_id(Stand_id);
          indicator.setAxes_id(Axis_id);
          int indID = result.getInt("Int_id");
          indicator.setInd_id(indID);
          int subID = result.getInt("SUB_ID");
          indicator.setSub_id(subID);    
          String s = result.getString("question");
          indicator.setQuestin(s);        
          int type = result.getInt("type");
          //System.out.println("from another "+type);
          if(type!= 0){
          indicator.setType(type);
          increaseType(type);
             }
          Boolean b ;
          
         
         /*indicator.setHasSub(b);
          b = result.getBoolean("answered");
          indicator.setAnswered(b);*/
         // System.out.println("hhhhhhhhhhh"+s);
          arr.add(indicator);
          indicator = new IndicatorDB();
        }
      //  System.out.println("ssssssssssssssssssssize  "+arr.size());
 return arr;
 }  
 
 public ArrayList saveAnswer(){
     outer  = new ArrayList();
     
    
     for(int i = 0 ; i < arr.size() ; i++){
        inner = new ArrayList();
        inner.add(arr.get(i).getStand_id()); 
        inner.add(arr.get(i).getAxes_id());
        inner.add(arr.get(i).getInd_id());
        inner.add(arr.get(i).getSub_id());
        inner.add(arr.get(i).getType());
        switch(arr.get(i).getType()){
            case 1:
                inner.add(arr.get(i).getExitanceAnswer());
                break;
            case 2:
                inner.add(arr.get(i).getTextAnswer());
                break;
            case 3:
                inner.add(arr.get(i).getScaleAnswer());
                break;
        }
        outer.add(i,inner);
        System.out.println(((ArrayList)outer.get(i)).get(0)+"   "+((ArrayList)outer.get(i)).get(1)+((ArrayList)outer.get(i)).get(5));

     } 
      // Message m = new Message();
      return outer  ;
      
 }
 static void increaseType(int Type){
    switch(Type){
        case 1:IndicatorDB.ExistanceTypeNum++;
                            break ;
         case 2:IndicatorDB.TextTypeNum++;
                            break ;    
         case 3:IndicatorDB.ScaleTypeNum++;
         break;   
    }
        }

}
