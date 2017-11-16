/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmsv2;

/**
 *
 * @author A 
 */


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javafx.event.*;

import java.util.ArrayList;
import static java.util.Objects.isNull;
import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Standard{

    /**
     * Creates new form axis
     */
    int selectedStandardID ;
    StandardDatabase sdb;
    ArrayList x;
    Stage window;
    Button btn1;
    Scene s1;
    Standard(){
        sdb = new StandardDatabase();
        x = new ArrayList();
        window = new Stage();
    }
    public void veiwContent(){ 
        AnchorPane layout = new AnchorPane(); 
        array = new Button[7];
        btn1 = new Button();
        btn1.setText("رجوع");     
        btn1.setLayoutX(14);
        btn1.setLayoutY(16);
        btn1.setPrefSize(85,29);
        btn1.setFont(new Font("Arabic Typesetting",15));
        layout.getChildren().add(btn1);
        btn1.setOnAction(e -> window.close());
        Label1 = new Label();
        Label1.setText("إختر المعيار ");    
        Label1.setLayoutX(504);
        Label1.setLayoutY(28);
        Label1.setFont(new Font("Arabic Typesetting",25));
        layout.getChildren().add(Label1);                  
        int count = 61;
        for(int i=0; i<array.length ;i++){
            try {
              x = sdb.getNames();
            } catch (Exception ex) {
               ex.printStackTrace();
            }        
            array[i]=new Button();  
            array[i].setText((String) x.get(i));
            array[i].setPrefSize(250, 43);
            array[i].setLayoutX(231);
            array[i].setLayoutY(count);
            array[i].setFont(new Font("Arabic Typesetting",22));
            layout.getChildren().add(array[i]);
            array[i].setOnAction(new EventHandler<ActionEvent>(){
               @Override
               public void handle(ActionEvent e ){
                   Button button = (Button)e.getSource();
                   try { 
                       sdb.getAxesInf(button.getText());
                   } catch (Exception ex) {
                   ex.printStackTrace();
                   }
                   //window.close();
                   Axes axis = new Axes(sdb.selectedID);
                   axis.viewContent();
                 }
            });
            count+=45;
        }         
        s1 = new Scene(layout,700,400);
        s1.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        window.setScene(s1);
        window.show();
    }
     Button array[] ;
     Button back;
     Label Label1;
     Label Label2;
     Label Label3;
     javax.swing.JMenu jMenu1;
     javax.swing.JMenu jMenu2;
     javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration                   
}
