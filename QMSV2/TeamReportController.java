/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmsv2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import static jdk.nashorn.internal.objects.NativeRegExp.source;

/**
 * FXML Controller class
 *
 * @author Isra
 */
public class TeamReportController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML Button btn1;
    @FXML Button btn2;
    @FXML Button btn3;
    Stage window ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    //back button event
    public void handleActionB1(){
        window = (Stage) btn1.getScene().getWindow();
        window.close();
    }
    //contnuie button event
    public void handleActionB2(){}
    //new report button event
    public void handleActionB3(){
       
       Standard standard = new Standard();
       standard.veiwContent();
       window = (Stage) btn3.getScene().getWindow();
       window.close();
       
    }
    
    
    
    
    
}
