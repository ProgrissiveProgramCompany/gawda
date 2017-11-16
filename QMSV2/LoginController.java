/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmsv2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Isra
 */
public class LoginController implements Initializable {
    
    @FXML
    private TextField userText;
    @FXML 
    private PasswordField passText ;
    Message m ;
    ArrayList array;
    Socket clientSocket = null;
    static ObjectOutputStream os = null;
    static ObjectInputStream is = null;
    
    @FXML
    private void handleActionEvent(ActionEvent event) {
        array = new ArrayList();
        array.add(userText.getText());
        array.add(passText.getText());
        try {
           StaticIO.os.writeObject(array);
           checkAuthority();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
     
   
    }
    public static void setIO(){
        try {
            StaticIO.setIO(os,is);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    private  void checkAuthority(){
        m = new Message(); 
        try {
            m = (Message)is.readObject();        
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if((int)m.array.get(0)==1){
            loadTo("AdminMenu.fxml");
        }else if ((int)m.array.get(0)==2){
            loadTo("TeamMenu.fxml");
        }else{
           JOptionPane.showMessageDialog(null,"wrong username or password");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
       try {
           clientSocket = new Socket("172.27.130.54", 5000);
           os = new ObjectOutputStream(clientSocket.getOutputStream());
           is = new ObjectInputStream(clientSocket.getInputStream());
           setIO();
       } catch (Exception e) {
           e.printStackTrace();
           System.err.println("Don't know about host " ); 
       }   
    }    
    public void loadTo(String className){
        Stage window = new Stage();
         Parent root = null;
         try {
             try {
                 setIO();    
             } catch (Exception ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
             root = FXMLLoader.load(getClass().getResource(className));

         } catch (IOException ex) {
             Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
         }
         Scene scene = new Scene(root);
         scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
         window.setScene(scene);
         window.show();    
    }
    
}
