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
/**
 *
 * @author A
 */
public class AxesDatabase {
    
         public int selectedID , axesID;
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
            s.execute("create table Axes ( AxisID number,AxisName varchar(50),StandardID number)"); 
            conn.close();
        }catch(Exception e){e.printStackTrace();}
        }
        public ArrayList getNames (int id)  throws Exception{
               ArrayList array = new ArrayList();
              try{
                 System.out.print("can reach");
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                conn = DriverManager.getConnection(DBpath);
                s =  conn.createStatement();
                ResultSet rs = s.executeQuery("SELECT AxisName FROM Axes where StandardID ='"+id+"' " );
                while ( rs.next() ) {
                //all = rs.getString("StandardName");
                array.add(rs.getString("AxisName"));
                conn.close();
                }
                }catch(Exception e){e.printStackTrace();}
                return array;
        }
        public int getAxesID(String name,int stand_id)throws Exception{
            int aid = 0;
              try{
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                conn = DriverManager.getConnection(DBpath);
                s =  conn.createStatement();
                String sql = "SELECT AxisID FROM Axes where AxisName ='"+name+"' and StandardID = '"+stand_id+"'";
	        ResultSet rs = s.executeQuery(sql);
                while(rs.next()){
	        aid  = rs.getInt("AxisID");
                
                }
                conn.close();                                
                }catch(Exception e){e.printStackTrace();}
              return aid;
          }
        public int[] getAxesWeight (){
        int array [] = new int [33];
              try{
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                conn = DriverManager.getConnection(DBpath);
                s =  conn.createStatement();
                String sql = "SELECT AxesWeight FROM Axes where ";
	        ResultSet rs = s.executeQuery(sql);
                int i = 0;
                while(rs.next()){
	        array[i]  = rs.getInt("AxesWeight");
                i++;
                }
                conn.close();                                
                }catch(Exception e){e.printStackTrace();}
              return array;
          }        
        
        } 

