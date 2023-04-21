package com.example;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller implements Initializable {

    @FXML protected Button button0;
    @FXML protected Button button1;
    @FXML protected Button button2;
    @FXML protected Button button3;
    @FXML protected Button button4;
    @FXML protected Button button5;
    @FXML protected Button button6;
    @FXML protected Button button7;
    @FXML protected Button button8;
    @FXML protected Text playerTxt;

    protected List <Button> buttons;
    protected boolean running = false;
    protected String[] myoptions = {"","","","","","","","",""};
    protected String player = "X";

    protected static final int[][] win_combinations = {
        {0,1,2},
        {3,4,5},
        {6,7,8},
        {0,3,6},
        {1,4,7},
        {2,5,8},
        {0,4,8},
        {2,4,6}
    };
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        buttons = new ArrayList<Button>(List.of(button0,button1,button2,button3,button4,button5,button6,button7,button8));
        playerTxt.setText("Player "+player+" `s turn");
        running = true;
    }

    public void cellClicked(javafx.event.ActionEvent event){
        Button clickedButton = (Button) event.getSource();
        int index = buttons.indexOf(clickedButton);
        if(myoptions[index]!= "" || running==false){
            return;
        }
        updateCell(clickedButton, index);
        checkWinner();
    }

    public void updateCell(Button clickedButton, int index){
        clickedButton.setText(player);
        myoptions[index]=player;
    }
    
    public void checkWinner(){
        boolean win = false;
        for(int i=0; i<win_combinations.length; i++){
            String cell1 = myoptions[win_combinations[i][0]];
            String cell2 = myoptions[win_combinations[i][1]];
            String cell3 = myoptions[win_combinations[i][2]];

            if((cell1=="") || (cell2=="" || (cell3==""))){
                continue;
            }else if(cell1==cell2 && cell2==cell3){
                win = true;
                buttons.get(win_combinations[i][0]).setStyle("-fx-background-color: #F1C40F;");
                buttons.get(win_combinations[i][1]).setStyle("-fx-background-color: #F1C40F;");
                buttons.get(win_combinations[i][2]).setStyle("-fx-background-color: #F1C40F;");
                break;
            }
        }

        if(win){
            running = false;
            playerTxt.setText("Player "+player+" wins !");
        }else if(!Arrays.asList(myoptions).contains("")){
            playerTxt.setText("Draw ... try again !");
            running=false;
        }else{
            changePlayer();
        }

    }

    public void changePlayer(){
        player = (player=="X")? "O": "X";
        playerTxt.setText("Player "+player+" `s turn");
    }

    public void reset(){
        running = false;
        Arrays.fill(myoptions, "");
        buttons.forEach(button->{
            button.setText("");
            button.setStyle("");
        });
        initialize(null, null);
    }
}
