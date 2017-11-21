package qmsv2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static java.util.Objects.isNull;

public class Database {

    private final String path = "jdbc:ucanaccess://D:\\Prgramming\\Eclipse workSpace\\SSL\\src\\javafxapplication1\\Server_Database.accdb";
    private Connection conn;

    public Database() {

    }

    public ArrayList getDeparments() {

        ArrayList departments = new ArrayList();
        try {

            System.out.println(" am here");
            conn = DriverManager.getConnection(this.path);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("select department from Departments;");

            while (result.next()) {

                departments.add((result.getString("department")));

            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return departments;

    }


    public ArrayList getAuthorization(String UserName, String password) {

        ArrayList authorization = new ArrayList();
        int authrized = 3;
        try {
            conn = DriverManager.getConnection(this.path);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("select name , password ,TYPE from Accounts;");


            while (result.next()) {
                if (UserName.equals(result.getString("name"))
                        &&
                        result.getString("password").equals((password))) {
                    System.out.println(result.getString("name"));
                    System.out.println(result.getString("password"));
                    System.out.println(result.getString("TYPE"));
                    authrized = Integer.parseInt(result.getString("TYPE"));


                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        authorization.add(authrized);
        return authorization;
    }


    public ArrayList sendAllAccounts() {

        ArrayList arrayList = new ArrayList();
        try {
            conn = DriverManager.getConnection(this.path);
            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery("select  name , accounts.department  from accounts ORDER BY department ;");

            int count = 0;

            String name = null;
            boolean firstTIme = true;
            while (result.next()) {
                if (firstTIme) {
                    name = result.getString("department");
                    arrayList.add(new ArrayList());
                    ((ArrayList) arrayList.get(count)).add(name);
                    firstTIme = false;
                }

                String newName = result.getString("department");

                if (!newName.equals(name)) {

                    name = result.getString("department");
                    arrayList.add(new ArrayList());
                    count++;
                    ((ArrayList) arrayList.get(count)).add(name);
                    String accountName = result.getString("name");
                    ((ArrayList) arrayList.get(count)).add(accountName);

                } else {
                    String accountName = result.getString("name");
                    ((ArrayList) arrayList.get(count)).add(accountName);

                }

            }


        } catch (Exception e) {
            System.out.print(e);
        }
        return arrayList;
    }















    /*
  standardID
   AxisID
   Idn id
   sub id
   type
   2/String - 1/boolean - 3/int


   */

    public boolean saveAnswers(ArrayList answers ){

        boolean status = true;
        try {

            conn = DriverManager.getConnection(this.path);
            Statement statement = conn.createStatement();


            for(int i=0;i<answers.size();i++){

                ArrayList indicator = (ArrayList) answers.get(i);
                int StdID =(int) indicator.get(0);
                int AxisID = (int )indicator.get(1);
                int IndicatorID = (int )indicator.get(2);
                int SubID = (int)indicator.get(3);
                int Type = (int)indicator.get(4);
                String sqlStatement = "INSERT INTO Answers VALUES ("+StdID+" , "+AxisID+" , "+IndicatorID+" , "+SubID+" , "+Type+" ,";
                switch(Type){
                    case 1:
                        boolean boolean_answer = (boolean)indicator.get(5);
                        sqlStatement+= boolean_answer +",null,null);";
                        break;
                    case 2:
                        String String_answer = (String)indicator.get(5);
                        sqlStatement+= "null,'" + String_answer +"',null);";
                        break;
                    case 3:
                        int Int_answer = (int)indicator.get(5);
                        sqlStatement += Int_answer +",null,null);";
                        break;
                }
                System.out.println(sqlStatement);
                statement.execute(sqlStatement);

            }




        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }





    public void     addImprovementSuggestion(ArrayList improvements){

      try{
            conn = DriverManager.getConnection(this.path);
            Statement statement = conn.createStatement();

            statement.execute("INSERT INTO  ImprovementPoints VALUES ("+null+","+(int)improvements.get(0)+"," +(int)improvements.get(1)+","+0+",'"+(String) improvements.get(2)+"')");


        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println(improvements.get(0));
        System.out.println(improvements.get(1));
        System.out.println((String)improvements.get(2));


    }

    public void deleteImprovementSuggestion(ArrayList improvements ){
        try{

            conn = DriverManager.getConnection(this.path);
            Statement statement = conn.createStatement();

            System.out.println((int)(improvements.get(0)));
            System.out.println((int)(improvements.get(01)));


            ArrayList im = (ArrayList) improvements.get(2);
            for (int i=0;i<im.size();i++) {
                System.out.println("STDID id   "+(int) improvements.get(0) +"   axis id is  "+ (int) improvements.get(1) +"   text is  :  "+(String) im.get(i));
              int r=  statement.executeUpdate("delete from ImprovementPoints where   ImpText='"+(String)im.get(i)+"';") ;
                //StandardID =" + (int) improvements.get(0) + " AND AxesID =" + (int) improvements.get(1) + " AND
            System.out.println("the result is "+r);
            }
            }catch(Exception e){e.printStackTrace();}

        System.out.println();

    }

    public ArrayList getImprovementSuggetions(){
        ArrayList arrayList = new ArrayList();
        boolean first = true;
        try{

            conn = DriverManager.getConnection(this.path);
            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery("select * from ImprovementPoints");

            ArrayList arrayList1 = new ArrayList();
            while(result.next()){
                if (first){
                    arrayList.add(result.getString("StandardID"));
                    arrayList.add(result.getString("AxesID"));
                    first = false;
                }



                arrayList1.add(result.getString("ImpText"));


            }
            if(arrayList.size()<2) {
                arrayList.add(-1);
                arrayList.add(-1);
                arrayList.add(2, arrayList1);

            }
        }catch(Exception e){e.printStackTrace();}
return arrayList;

    }











/*

    public String hashFunction(String string) {

        String hashedString = null;

        try {

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(string.getBytes(StandardCharsets.UTF_8));
            hashedString = new String(hash);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hashedString;
    }
*/

}
