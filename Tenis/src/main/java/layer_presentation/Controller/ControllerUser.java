package layer_presentation.Controller;

import javafx.collections.ObservableList;
import javafx.css.Match;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import layer_business.TennisService;
import layer_business.validators.Validators;
import layer_data_access.repo.GenericRepo;
import layer_presentation.util.AlertBox;
import model.TennisGame;
import model.TennisMatch;
import model.TennisSet;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class ControllerUser {


    @FXML
    public TextArea matchAreaUser;

    @FXML
    public TextField upPlayer1Games;

    @FXML
    public TextField upPlayer2Games;

    @FXML
    public TextField upMatchNr;

    @FXML
    public TextField upSetNr;

    ObservableList<TennisMatch> matches;

    public void showMatches(){
        matches = GenericRepo.findEverything("from model.TennisMatch");
        matchAreaUser.clear();
        matchAreaUser.appendText("Players \t\t\t");
        matchAreaUser.appendText("Set1 \t\t");
        matchAreaUser.appendText("Set2 \t\t");
        matchAreaUser.appendText("Set3 \t\t");
        matchAreaUser.appendText("Set4 \t\t");
        matchAreaUser.appendText("Set5 \n\n");

        for(int i=0; i<matches.size(); i++){
            String player = matches.get(i).getPlayer1().getName();
            matchAreaUser.appendText(player + "\t\t\t");

            List<TennisSet> sets = matches.get(i).getSets();
            for(int j=0; j<sets.size(); j++){
                List<TennisGame> games = sets.get(j).getGames();
                for(int k=0; k<games.size(); k++){
                    matchAreaUser.appendText(games.get(k).getP1Score()+ "\t\t\t");
                }
            }
            matchAreaUser.appendText("\n");
            player = matches.get(i).getPlayer2().getName();
            matchAreaUser.appendText(player + "\t\t\t");
            for(int j=0; j<sets.size(); j++){
                List<TennisGame> games = sets.get(j).getGames();
                for(int k=0; k<games.size(); k++){
                    matchAreaUser.appendText(games.get(k).getP2Score()+ "\t\t\t");
                }
            }
            matchAreaUser.appendText("\n\n");
        }
    }

    public void updateGames(){

        int matchNumber=Integer.parseInt(upMatchNr.getText());
        int setNumber=Integer.parseInt(upSetNr.getText());
        if(Validators.validateNumberBox(upPlayer1Games.getText())==0){
            AlertBox.display("Wrong score", "Insert a valid score for Player1");
            upPlayer1Games.clear();
            upPlayer2Games.clear();
            upSetNr.clear();
            upMatchNr.clear();
        }
        else if(Validators.validateNumberBox(upPlayer2Games.getText())==0){
            AlertBox.display("Wrong score", "Insert a valid score for Player2");
            upPlayer1Games.clear();
            upPlayer2Games.clear();
            upSetNr.clear();
            upMatchNr.clear();
        }
        else if(Validators.validateNumberBox(upSetNr.getText())==0){
            AlertBox.display("Wrong set", "Insert a valid set number");
            upPlayer1Games.clear();
            upPlayer2Games.clear();
            upSetNr.clear();
            upMatchNr.clear();
        }
        else if(Validators.validateNumberBox(upMatchNr.getText())==0){
            AlertBox.display("Wrong match number", "Insert a valid match number");
            upPlayer1Games.clear();
            upPlayer2Games.clear();
            upSetNr.clear();
            upMatchNr.clear();
        }
        else {

            if(matchNumber>matches.size()){
                AlertBox.display("Not existing match", "Please insert a valid match number");
            }
            else {
                int player1ScoreToUpdate = Integer.parseInt(upPlayer1Games.getText());
                int player2ScoreToUpdate = Integer.parseInt(upPlayer2Games.getText());
                for (int i = 0; i < matches.size(); i++) {
                    if (i + 1 == matchNumber) {
                        List<TennisSet>sets = matches.get(i).getSets();
                        if(sets.size()<setNumber){
                            AlertBox.display("Not existing set", "Please insert a valid set number");
                        }
                        else {
                            int ok = TennisService.updateScore(matches.get(i), player1ScoreToUpdate, player2ScoreToUpdate, setNumber);
                            if (ok == 1) {
                                AlertBox.display("Wrong score", "Insert a valid score for the current set");
                            } else if (ok == 2) {
                                AlertBox.display("Wrong set", "This set is already finished");
                            } else if (ok == 3) {
                                AlertBox.display("Match ended", "Player1 won");
                            } else if (ok == 4) {
                                AlertBox.display("Match ended", "Player2 won");
                            }
                        }
                    }
                }
            }
            upPlayer1Games.clear();
            upPlayer2Games.clear();
            upSetNr.clear();
            upMatchNr.clear();
            showMatches();
        }

    }





}
