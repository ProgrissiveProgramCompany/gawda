

package qmsv2;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor
/**
 *
 * @author A
 */
public class Axes  {
   

    /**
     * Creates new form Axis
     */
    static int StandardID ,AxesID;
    AxesDatabase adb = new AxesDatabase();
    ArrayList rate = new ArrayList();
    ArrayList x = new ArrayList();
    Button btn1 ;
    Scene s1 ;
    public Axes() {
     
    }
    public Axes(int StandardID){
        this.StandardID = StandardID;   
    }  
    public void viewContent() {
        Stage window = new Stage() ;
        AnchorPane layout = new AnchorPane();        
        btn1 = new Button();
        btn1.setText("رجوع");     
        btn1.setLayoutX(14);
        btn1.setLayoutY(16);
        btn1.setPrefSize(85,29);
        btn1.setFont(new Font("Arabic Typesetting",15));
        layout.getChildren().add(btn1); 
        btn1.setOnAction(e -> window.close());
        
        label1 = new Label();
        label1.setText(" اختر المحور :");     
        label1.setLayoutX(504);
        label1.setLayoutY(28);
        label1.setFont(new Font("Arabic Typesetting",25));
        layout.getChildren().add(label1);      
        int count = 61 ;
              try {
               x = adb.getNames(StandardID);
               array = new Button [x.size()];
              
            } catch (Exception ex) {
                ex.printStackTrace();
            }             
      
        for(int i = 0 ; i < x.size() ; i++){
            array[i]=new Button();  
            array[i].setText((String) x.get(i));
            array[i].setPrefSize(250, 43);
            array[i].setLayoutX(231);
            array[i].setLayoutY(count);
            array[i].setFont(new Font("Arabic Typesetting",22));
            layout.getChildren().add(array[i]);
            array[i].setOnAction((ActionEvent e) -> {
                Button button = (Button)e.getSource();
                try {
                    AxesID = adb.getAxesID(button.getText(), StandardID);
                } catch (Exception ex) {
                    Logger.getLogger(Axes.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    
                    //Message m = (Message)is.readObject();
                    //JOptionPane.showMessageDialog(null,m.array.get(0));
                    Indicator ind = new Indicator(getStandardID(),getAxesID());
                    ind.viewContent();
                    
                } catch (Exception ex) {
                    Logger.getLogger(Axes.class.getName()).log(Level.SEVERE, null, ex);
                }
            });             
            count += 45 ;
         } 
 
         s1 = new Scene(layout,700,400);
         s1.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
         window.setScene(s1);
         window.show();       
    }// </editor-fold>                        
                     
   /*  @Override
    public void actionPerformed(ActionEvent e){
          JButton button = (JButton)e.getSource();
          if(button == jButton1){
             this.setVisible(false);
             /*Standard s = new Standard();
             s.setSize(500, 600);
             s.setVisible(true);
            // this.setVisible(true);
          }else{
          try { 
                AxesID = adb.getAxesID(button.getText(),StandardID);
          } catch (Exception ex) {
               ex.printStackTrace();
                //Logger.getLogger(StandardInterface.class.getName()).log(Level.SEVERE, null, ex);
          }
          //int choice = menu.selected;
          int choice = 2 ;
          switch(choice){
            case 2:
          {
              try {

                  Indicator ind = new Indicator(getStandardID(),getAxesID());
                  ind.setSize(1000,1000);
                  ind.setVisible(true);
                  //IndicatorDB idb = new IndicatorDB();
                
              } catch (Exception ex) {
                  ex.printStackTrace();
              }
          }
                break;
            /*case 3:
                ImprovementPoint imp = new ImprovementPoint();
                this.setVisible(false);
                imp.setSize(400,500);
                imp.setVisible(true);
                break;
            case 4:
              try {
                  this.setVisible(false);
                  Evidance_Menu evd = new Evidance_Menu (getStandardID(),getAxesID());   
                 
              } catch (Exception ex) {
                  Logger.getLogger(Axes.class.getName()).log(Level.SEVERE, null, ex);
              }               
                break;
           }
          }
   
    }  */
    static int getStandardID(){
    return StandardID;
    }
    static int getAxesID(){
    return AxesID;
    }
    void setRate(ArrayList rate){
    this.rate =rate;
    }

    /**
     * @param args the command line arguments
     */
  /*  public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Axis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Axis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Axis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Axis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
       /* java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Axis().setVisible(true);
            }
        });
    }
*/
    // Variables declaration - do not modify                     
     Button array [];
     Label label1;
     Label label2;
     Button button1;
    // End of variables declaration                   

  
}
