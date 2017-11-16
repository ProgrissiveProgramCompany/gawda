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
import java.util.*;
import static java.util.Objects.isNull;
/**
 *
 * @author A
 */
public class StandardDatabase {
    
         int selectedID , axesNum;
    	 String DBpath = Database_path.Database_Path;
         Connection conn;
         Statement s;
      

	/*public void insert (String text) throws Exception {
                Connection conn = DriverManager.getConnection(DBpath);
                Statement s =  conn.createStatement();
		//s.executeUpdate("INSERT INTO ImprovementPoints (StandardID , AxesID, ImpPointID ,ImpText) " + "VALUES ('"+getStandardID+"','"+getAxesID+"','"+count+"','"+text+"')");
		}*/
        public void create()throws Exception{
        try
        {   
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            conn = DriverManager.getConnection(DBpath);
            s = conn.createStatement();
            s.execute("create table Standard ( StandardID number,StandardName varchar(50),AxesNum number)"); 
            conn.close();
        }catch(Exception e){e.printStackTrace();}
        }  
        public ArrayList getNames ()  throws Exception{
            ArrayList array = new ArrayList();
              try{
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                conn = DriverManager.getConnection(DBpath);
                s =  conn.createStatement();
                ResultSet rs = s.executeQuery("SELECT * FROM Standard");
                while ( rs.next() ) {
                array.add(rs.getString("StandardName"));
                conn.close();
                }
                }catch(Exception e){e.printStackTrace();}
                return array;
        }
        public void getAxesInf(String name)throws Exception{
              try{
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                conn = DriverManager.getConnection(DBpath);
                s =  conn.createStatement();
                String sql = "SELECT StandardID,AxesNum FROM Standard where StandardName ='"+name+"'";
	        ResultSet rs = s.executeQuery(sql);
                while(rs.next()){
	        selectedID  = rs.getInt("StandardID");
                axesNum  = rs.getInt("AxesNum");
                }
                conn.close();                                
                }catch(Exception e){e.printStackTrace();}
          }
        public int [] getStandardWeight(){
               int weight [] = new int [7] ;
               try{
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                conn = DriverManager.getConnection(DBpath);
                s =  conn.createStatement();
                String sql = "SELECT StandardWeight FROM Standard ";
	        ResultSet rs = s.executeQuery(sql);
                int i = 0;
                while(rs.next()){
	        weight[i]  = rs.getInt("StandardWeight");
                i++;
                }
                conn.close();                                
                }catch(Exception e){e.printStackTrace();}
               return weight;
          }
        public double [] getStandardGPA(){
               double gpa [] = new double [7] ;
               try{
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                conn = DriverManager.getConnection(DBpath);
                s =  conn.createStatement();
                String sql = "SELECT GPA FROM Standard ";
	        ResultSet rs = s.executeQuery(sql);
                int i = 0;
                while(rs.next()){
	        gpa[i]  = rs.getDouble("GPA");
                i++;
                }
                conn.close();                                
                }catch(Exception e){e.printStackTrace();}
               return gpa;
          }        
       
        
        }
    

