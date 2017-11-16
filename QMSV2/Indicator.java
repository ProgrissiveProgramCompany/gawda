/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qmsv2;

import java.util.ArrayList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Objects.isNull;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;



/**
 *
 * @author Isra
 */
public class Indicator {
        public static int ExistanceTypeNum  ;
        public static int ScaleTypeNum  ;
        public static int TextTypeNum  ;
        //private int rate ; 
        private boolean answered ;
        ArrayList<IndicatorDB> indicators ;
        private int TotalIndicatorNum ;
        private int TotalRadioGrouNum ;
        private int TotalRadiosNum ;
        private int TotalTextAreaNum ;
        private int TotalSliderNum ;
        private Label[] questionLabel ; 
        private ComboBox[] rate ;
        private RadioButton[] answerradios ;
       // private ToggleButton [] buttonsgroup ;
        ToggleGroup group[] ;
        private TextArea[] answerBox ;
        private Slider[] answerSliders ;
        private Button save ; 
// integer number associte each answer with its indicator 
        private int[] assINdOfButt ;
        private int[] assINdOfArea ;
        private int[] assINdOfSlider ;
        
        private String[] rateOptions = {"ممتاز","جيد جدا","جيد","ضعيف"};
        final ObservableList option = FXCollections.observableArrayList();
        private Button back , mainmenu;
        Stage window ;
        Scene s1 ;
        ObjectOutputStream os;
        
 public Indicator() throws SQLException {
        
 }
 public Indicator(int Stand_id , int Axis_id) throws SQLException {

     
      // int x = 1 ;
        try {
            indicators = IndicatorDB.getIndicators(Stand_id,Axis_id);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Indicator.class.getName()).log(Level.SEVERE, null, ex);
        }
        //initialize sizes 
        //initialize sizes 
        //initialize sizes 
        //initialize sizes 
        

        TotalIndicatorNum = indicators.size();
        TotalRadioGrouNum = IndicatorDB.ExistanceTypeNum;
        TotalRadiosNum = IndicatorDB.ExistanceTypeNum*2;//yes and no for each question
        TotalSliderNum = IndicatorDB.ScaleTypeNum;
        TotalTextAreaNum = IndicatorDB.TextTypeNum;

        //initaialize arrays 
        questionLabel = new Label[TotalIndicatorNum] ;
        answerBox = new TextArea[TotalTextAreaNum];
        answerSliders = new Slider[TotalSliderNum];
        answerradios = new RadioButton[TotalRadiosNum];
        group = new ToggleGroup[TotalRadioGrouNum];
        assINdOfButt = new int[TotalRadiosNum];
        assINdOfArea = new int[TotalTextAreaNum];
        assINdOfSlider = new int[TotalSliderNum];
        rate = new ComboBox[TotalIndicatorNum];
        for(int i = 0 ; i< rateOptions.length ;i++){
           option.add(rateOptions[i]);
        }
        
       /* contanier = new JPanel();
        contanier.setSize(900,900);
        all  = new JScrollPane(contanier);
        all.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        all.add(all.createHorizontalScrollBar());
        all.add(all.createVerticalScrollBar());
        all.setBounds(0, 0, 1000, 1000);
        all.setVisible(true);
        getContentPane().add(all);*/
        viewContent();
        
        
    }

 
 
 
   
    void viewContent(){
       
        AnchorPane layout = new AnchorPane();
        window = new Stage();
        back = new Button("رجوع");
        back.setPrefSize(85,29);
        back.setLayoutX(14);
        back.setLayoutY(16);
        back.setFont(new Font("Arabic Typesetting",15));
        layout.getChildren().add(back);          
        back.setOnAction((ActionEvent e) -> {
            buttonsAction(e);
        });        
        mainmenu = new Button("الرئيسية");
        mainmenu.setPrefSize(85,29);
        mainmenu.setLayoutX(109);
        mainmenu.setLayoutY(16);
        mainmenu.setOnAction((ActionEvent e) -> {
            buttonsAction(e);
        });        
        layout.getChildren().add(mainmenu); 
        int xPosition = 100 ;
        int yPosition = 72 ;
   
        String labelText ; 
        int SliderIndex=0 ;
        int RadiosIndex=0;
        int RGroupIndex=0;
        int indicatorIndex =0;
        int TextAreaIndex=0;
        
        for(int index = 0 ; index < TotalIndicatorNum ; index++){
           
            String s = indicators.get(index).getQuestin();
          //  int shift = 900 - s.length();
            labelText = (index+1)+"."+s;
            questionLabel[indicatorIndex] = new Label(labelText); 
            questionLabel[indicatorIndex].setFont(new Font("Arabic Typesetting",22));
            questionLabel[indicatorIndex].setPrefSize(550, 30);
            questionLabel[indicatorIndex].setLayoutX(xPosition);
            questionLabel[indicatorIndex].setLayoutY(yPosition);
            questionLabel[indicatorIndex].nodeOrientationProperty().setValue(NodeOrientation.RIGHT_TO_LEFT);
            questionLabel[indicatorIndex].setWrapText(true);
            //questionLabel[indicatorIndex].setTextAlignment(TextAlignment.JUSTIFY);

            layout.getChildren().add(questionLabel[indicatorIndex]); 
            
            /*if(indicators.get(index).getPrevRates()!= -1)
            r = resetArrOption(indicators.get(index).getPrevRates());
            else */
            
            
            
            rate[index] = new ComboBox();
            rate[index].setPromptText("قيم إجابتك");
            rate[index].setItems(option);
            rate[index].setMaxHeight(5);
            rate[index].setLayoutX(14);
            rate[index].setLayoutY(yPosition);
            layout.getChildren().add(rate[index]); 
            indicatorIndex++;
            yPosition+=72;
           
       

        switch(indicators.get(index).getType()){     
        case 1:
            
            group[RGroupIndex] = new ToggleGroup();
            answerradios[RadiosIndex] = new RadioButton("لا");
            answerradios[RadiosIndex].setToggleGroup(group[RGroupIndex]);       
            answerradios[RadiosIndex].setFont(new Font("Arabic Typesetting",15));
            answerradios[RadiosIndex].setLayoutX(404);
            answerradios[RadiosIndex].setLayoutY(yPosition+10);
            layout.getChildren().add(answerradios[RadiosIndex]);
            assINdOfButt[RadiosIndex] = indicators.get(index).getInd_id();
            RadiosIndex++;
            answerradios[RadiosIndex] = new RadioButton("نعم");
            answerradios[RadiosIndex].setFont(new Font("Arabic Typesetting",15));
            answerradios[RadiosIndex].setLayoutX(580);
            answerradios[RadiosIndex].setLayoutY(yPosition+10);            
            answerradios[RadiosIndex].setToggleGroup(group[RGroupIndex]);
            layout.getChildren().add(answerradios[RadiosIndex]);
            assINdOfButt[RadiosIndex] = indicators.get(index).getInd_id();
           /*if(indicators.get(index).getAnswered()){
               boolean PrevAns =indicators.get(index).getExistanceAns();
           /* System.out.println(PrevAns+"ppp");
            if(PrevAns)
                answerradios[RadiosIndex-1].setSelected(true);
            else
                answerradios[RadiosIndex].setSelected(true);
           //answerBox[TextAreaIndex].setText(PrevAns);
             }*/
            RadiosIndex++;
            RGroupIndex++;
            
            break ;
        case 2:
            answerBox[TextAreaIndex] = new TextArea();
            answerBox[TextAreaIndex].setPrefSize(490, 66);
            answerBox[TextAreaIndex].setLayoutX(160);
            answerBox[TextAreaIndex].setLayoutY(yPosition+10);
            //answerBox[TextAreaIndex].setBounds((xPosition),yPosition,700,200);
             /*if(indicators.get(index).getAnswered()){
               String PrevAns =indicators.get(index).getTextAns();
          //  System.out.println(PrevAns+"ppp");
            answerBox[TextAreaIndex].setText(PrevAns);
             }*/
            ScrollPane sp=new ScrollPane(answerBox[TextAreaIndex]);
            sp.setPrefSize(497, 66);
            sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            layout.getChildren().add(answerBox[TextAreaIndex]);
            assINdOfArea[TextAreaIndex] = indicators.get(index).getInd_id();
            TextAreaIndex++;
                         
            break ;    
        
        case 3:
           /* if(indicators.get(index).getAnswered()){
               int PrevAns =indicators.get(index).getScaleAns();
        //    System.out.println(PrevAns+"ppp");
            answerSliders[SliderIndex] = new JSlider(JSlider.HORIZONTAL,0,100,PrevAns);
            }else*/
            answerSliders[SliderIndex] = new Slider(0, 100, 0);
            answerSliders[SliderIndex].setPrefSize(500, 15);
            answerSliders[SliderIndex].setLayoutX(156);
            answerSliders[SliderIndex].setLayoutY(yPosition+10);
            answerSliders[SliderIndex].setShowTickMarks(true);
            answerSliders[SliderIndex].setShowTickLabels(true); 
            layout.getChildren().add(answerSliders[SliderIndex]);
            assINdOfSlider[SliderIndex] = indicators.get(index).getInd_id();  
            SliderIndex++; 
            break; 

            }
            yPosition+=80;
         /*   
            if(indicators.get(index).getHasSub()){
                int x = 1 ;
            subIndicators=IndicatorDB.getSubIndicator(indicators.get(index).getStand_id(),indicators.get(index).getStand_id(),indicators.get(index).getStand_id());
            }
 */       }
        save = new Button("حفظ");           
        save.setPrefSize(85,29);
        save.setLayoutX(324);
        save.setLayoutY(yPosition);
        save.setFont(new Font("Arabic Typesetting",15));
        layout.getChildren().add(save);          
        save.setOnAction((ActionEvent e) -> {
            buttonsAction(e);
        });
        ScrollPane scroller = new ScrollPane(layout);
        scroller.setFitToWidth(true);
        s1 = new Scene(scroller,700,400);
        s1.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        window.setScene(s1);
        window.show();
    }      
        
        


    public static int getExistanceTypeNum() {
        return ExistanceTypeNum;
    }

    public static void setExistanceTypeNum(int ExistanceTypeNum) {
        Indicator.ExistanceTypeNum = ExistanceTypeNum;
    }

    public static int getScaleTypeNum() {
        return ScaleTypeNum;
    }

    public static void setScaleTypeNum(int ScaleTypeNum) {
        Indicator.ScaleTypeNum = ScaleTypeNum;
    }

    public static int getTextTypeNum() {
        return TextTypeNum;
    }

    public static void setTextTypeNum(int TextTypeNum) {
        Indicator.TextTypeNum = TextTypeNum;
    }

   
    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }
       
    public void buttonsAction(ActionEvent e) {
        Button btn = (Button)e.getSource();
        if(btn == back){
          window.close();
          Axes a = new Axes();
          a.viewContent();
        }
        else if (btn == mainmenu){
          window.close();
          loadTo("TeamMenu.fxml");
        }
        else{
        for(int i = 0 ; i < TotalRadiosNum ; i++){
           if(answerradios[i].isSelected()==true)
           if("نعم".equals(answerradios[i].getText()))    
           IndicatorDB.arr.get(assINdOfButt[i]-1).setExitanceAnswer(true);
           else 
           IndicatorDB.arr.get(assINdOfButt[i]-1).setExitanceAnswer(false);
           }
        
        
        for(int i = 0 ; i < TotalSliderNum ; i++){
           if((int)answerSliders[i].getValue()!=0){
           IndicatorDB.arr.get(assINdOfSlider[i]-1).setScaleAnswer((int) answerSliders[i].getValue()); 
           System.out.println("woooooooooooooooooooooooooooooooooooooooob"+(int)answerSliders[i].getValue());
           }
        }

        
        for(int i = 0 ; i < TotalTextAreaNum ; i++){
            if(answerBox[i].getText().equals(""))
                continue ;
            else{
            IndicatorDB.arr.get(assINdOfArea[i]-1).setTextAnswer(answerBox[i].getText()); 
                }
            }
        
        
        for(int i = 0 ; i < TotalIndicatorNum ; i++){
            if( rate[i].getSelectionModel().getSelectedIndex()!= 0  )
            IndicatorDB.arr.get(i).setRate(rateOptions[rate[i].getSelectionModel().getSelectedIndex()]);
        }
      // new AutoCompute();
         Message m = new Message();
         m.service = 3 ;
         IndicatorDB indDB = new IndicatorDB();
         m.array = indDB.saveAnswer();
         
            try {
               // StaticIO.setIO(os, is);
                System.out.println(isNull(StaticIO.os)+"messsssssssssage "+(int)((ArrayList)m.array.get(0)).get(0));
                System.out.println(isNull(m));
                System.out.println(isNull(StaticIO.os));
                
                StaticIO.os.writeObject(m);
            
            } catch (IOException ex) {
                Logger.getLogger(Indicator.class.getName()).log(Level.SEVERE, null, ex);
            }
         System.out.println("lllllllastTest  "+isNull(indDB.saveAnswer()));
           /* try {
                os.writeObject(m);
                //hidethis();
            } catch (IOException ex) {
                Logger.getLogger(Indicator.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        Alert.display(null,  "have been successfuly saved ");     
        }
    }     
        public void loadTo(String className){
        Stage window = new Stage();
        Parent root = null;
        try {
          
            root = FXMLLoader.load(getClass().getResource(className));   
        } catch (IOException ex) {
            Logger.getLogger(AdminMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        window.setScene(scene);
        window.show();
    }    
}
