package layer_presentation.Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import layer_business.ReportPdf;
import layer_business.ReportTxt;
import layer_business.Reports;
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

import static java.lang.Boolean.FALSE;

public class ControllerAdmin {

    @FXML
    public TextArea playersArea;

    @FXML
    public TextArea matchArea;

    @FXML
    public Button showPlayersPButton;

    @FXML
    public Button deletePButton;

    @FXML
    public Button editPButton;

    @FXML
    public TextField insPlayerName;

    @FXML
    public TextField insPlayerMail;

    @FXML
    public TextField insPlayerPassword;

    @FXML
    public TextField upPlayer1GamesA;

    @FXML
    public TextField upPlayer2GamesA;

    @FXML
    public TextField upMatchNrA;

    @FXML
    public TextField upSetNrA;

    @FXML
    public TextField insPlayer1NameA;

    @FXML
    public TextField insPlayer2NameA;

    @FXML
    public TextField repo;

    ObservableList<User> users;
    public void showPlayers(){
        users= GenericRepo.findUsers();
        playersArea.clear();
        playersArea.appendText("Player name");
        playersArea.appendText("\t\t");
        playersArea.appendText("Player password");
        playersArea.appendText("\t");
        playersArea.appendText("Player email\n\n");

        for(int i=0; i<users.size(); i++){

            playersArea.appendText(users.get(i).getName());
            if(users.get(i).getName().length()<5)
                playersArea.appendText("\t\t\t\t");
            else if(users.get(i).getName().length()>9)
                playersArea.appendText("  ");  //
                else  playersArea.appendText("\t\t\t");

            playersArea.appendText(users.get(i).getPassword());
            if(users.get(i).getPassword().length()<5)
                playersArea.appendText("\t\t\t\t");
            else if(users.get(i).getPassword().length()>9)
                playersArea.appendText("\t");
               else playersArea.appendText("\t\t\t");

            playersArea.appendText(users.get(i).getMail());
            playersArea.appendText("\t\t\t");
            playersArea.appendText(" \n");
        }
    }

    public void addPlayer(){
        String name = insPlayerName.getText();
        String pass = insPlayerPassword.getText();
        String mail = insPlayerMail.getText();
        if(Validators.validateName(name)==0){
            AlertBox.display("Wrong name", "Please insert a valid name. Must have at least 3 characters.");
        }
        else if(Validators.validatePass(pass)==0){
            AlertBox.display("Wrong pass", "Pass must be at least 3 characters and at most 15");
        }
        else if(Validators.validateEmail(mail)==0){
            AlertBox.display("Wrong email", "Email must contain exactly 1 @ which is not first or last and must have at least 3 characters");
        }
            else {
            User usr = User.builder().mail(mail).password(pass).name(name).role(FALSE).build();
            GenericRepo.save(usr);
            showPlayers();
            insPlayerName.clear();
            insPlayerPassword.clear();
            insPlayerMail.clear();
        }
        insPlayerName.clear();
        insPlayerPassword.clear();
        insPlayerMail.clear();
    }

    public void updatePlayer(){
        String name = insPlayerName.getText();
        String pass = insPlayerPassword.getText();
        String mail = insPlayerMail.getText();
        if(Validators.validateName(name)==0){
            AlertBox.display("Wrong name", "Please insert a valid name");
        }
        else if(Validators.validatePass(pass)==0){
            AlertBox.display("Wrong pass", "Pass must be at least 3 characters and at most 15");
        }
        else if(Validators.validateEmail(mail)==0){
            AlertBox.display("Wrong email", "Email must contain exactly 1 @ which is not first or last and must have at least 3 characters");
        }
        else {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getName().equals(name)) {
                    users.get(i).setMail(mail);
                    users.get(i).setPassword(pass);
                    GenericRepo.update(users.get(i));
                }
            }
            showPlayers();
            insPlayerName.clear();
            insPlayerPassword.clear();
            insPlayerMail.clear();
        }
        insPlayerName.clear();
        insPlayerPassword.clear();
        insPlayerMail.clear();
    }

    //sterg intai games, apoi sets, apoi meciul, apoi playerul ca altfel arunca exceptii din cauza cheilor straine din DB..
    public void deletePlayer(){
        String name = insPlayerName.getText();
        if(Validators.validateName(name)==0){
            AlertBox.display("Wrong name", "Please insert a valid name");
        }
        else {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getName().equals(name)) {
                    int idPlayer = users.get(i).getId();
                    ObservableList<TennisMatch> matches = GenericRepo.findEverything("from model.TennisMatch");
                    for (int j = 0; j < matches.size(); j++) {
                        if (matches.get(j).getPlayer1().equals(users.get(i)) || matches.get(j).getPlayer2().equals(users.get(i))) {
                            List<TennisSet> sets = matches.get(j).getSets();
                            for (int k = 0; k < sets.size(); k++) {
                                List<TennisGame> games = sets.get(k).getGames();
                                for (int l = 0; l < games.size(); l++) {
                                    GenericRepo.delete(games.get(l));
                                }
                                GenericRepo.delete(sets.get(k));
                            }
                            GenericRepo.delete(matches.get(j));
                        }
                    }
                    GenericRepo.delete(users.get(i));
                }
            }
            showPlayers();
            showMatches();
            insPlayerName.clear();
        }
        insPlayerName.clear();
    }

    //Trecem la partea de meciuri aici --------------------------------------------------------------------------------
    ObservableList<TennisMatch> matches;
    public void showMatches(){
        matches = GenericRepo.findEverything("from model.TennisMatch");
        matchArea.clear();
        matchArea.appendText("Players \t\t\t");
        matchArea.appendText("Set1 \t\t");
        matchArea.appendText("Set2 \t\t");
        matchArea.appendText("Set3 \t\t");
        matchArea.appendText("Set4 \t\t");
        matchArea.appendText("Set5 \n\n");


        for(int i=0; i<matches.size(); i++){
            String player = matches.get(i).getPlayer1().getName();
            matchArea.appendText(player + "\t\t\t");
            List<TennisSet> sets = matches.get(i).getSets();

            for(int j=0; j<sets.size(); j++){
                List<TennisGame> games = sets.get(j).getGames();
                for(int k=0; k<games.size(); k++){
                    matchArea.appendText(games.get(k).getP1Score()+ "\t\t\t");
                }
            }
            matchArea.appendText("\n");
            player = matches.get(i).getPlayer2().getName();
            matchArea.appendText(player + "\t\t\t");
            for(int j=0; j<sets.size(); j++){
                List<TennisGame> games = sets.get(j).getGames();
                for(int k=0; k<games.size(); k++){
                    matchArea.appendText(games.get(k).getP2Score()+ "\t\t\t");
                }
            }
            matchArea.appendText("\n\n");
        }
    }

    public void updateGames(){

        if(Validators.validateNumberBox(upPlayer1GamesA.getText())==0){
            AlertBox.display("Wrong score", "Insert a valid score for Player1");
            upPlayer1GamesA.clear();
            upPlayer2GamesA.clear();
            upSetNrA.clear();
            upMatchNrA.clear();
        }
        else if(Validators.validateNumberBox(upPlayer2GamesA.getText())==0){
            AlertBox.display("Wrong score", "Insert a valid score for Player2");
            upPlayer1GamesA.clear();
            upPlayer2GamesA.clear();
            upSetNrA.clear();
            upMatchNrA.clear();
        }
        else if(Validators.validateNumberBox(upSetNrA.getText())==0){
            AlertBox.display("Wrong set", "Insert a valid set number");
            upPlayer1GamesA.clear();
            upPlayer2GamesA.clear();
            upSetNrA.clear();
            upMatchNrA.clear();
        }
        else if(Validators.validateNumberBox(upMatchNrA.getText())==0){
            AlertBox.display("Wrong match number", "Insert a valid match number");
            upPlayer1GamesA.clear();
            upPlayer2GamesA.clear();
            upSetNrA.clear();
            upMatchNrA.clear();
        }
        else {
            int player1ScoreToUpdate=Integer.parseInt(upPlayer1GamesA.getText());
            int player2ScoreToUpdate=Integer.parseInt(upPlayer2GamesA.getText());
            int matchNumber=Integer.parseInt(upMatchNrA.getText());
            int setNumber=Integer.parseInt(upSetNrA.getText());
            if(matchNumber>matches.size()){
                AlertBox.display("Not existing match", "Please insert a valid match number");
            }
            else {
                for (int i = 0; i < matches.size(); i++) {
                    if (i + 1 == matchNumber) {
                        List<TennisSet> sets = matches.get(i).getSets();
                        if (sets.size() < setNumber) {
                            AlertBox.display("Not existing set", "Please insert a valid set number");
                        } else {
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

                upPlayer1GamesA.clear();
                upPlayer2GamesA.clear();
                upSetNrA.clear();
                upMatchNrA.clear();
                showMatches();
            }

        }
    }

    public void createMatch(){
        String p1Name = insPlayer1NameA.getText();
        String p2Name = insPlayer2NameA.getText();

        if(Validators.validateName(p1Name)==0){
            AlertBox.display("Wrong name", "Please insert a valid name for Player 1");
        }
        else if(Validators.validateName(p2Name)==0){
            AlertBox.display("Wrong name", "Please insert a valid name for Player 2");
        }
        else {
            User player1 = new User();
            User player2 = new User();
            int ok1 = 0;
            int ok2=0;
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getName().equals(p1Name)) {
                    player1 = users.get(i);
                    ok1=1;
                }
                if (users.get(i).getName().equals(p2Name)) {
                    player2 = users.get(i);
                    ok2=1;
                }
            }
            if(ok1==1 && ok2==1){
                TennisMatch matchToCreate = TennisService.createNewMatch(player1, player2);
                matches.add(matchToCreate);
            }
            else {AlertBox.display("Invalid player", "Please insert a player available from the list of players");}

            insPlayer1NameA.clear();
            insPlayer2NameA.clear();
            showMatches();
        }
    }

    public void createSet() {

        if (Validators.validateNumberBox(upMatchNrA.getText()) == 0) {
            AlertBox.display("Wrong input", "Please insert a valid match number");
        } else {
            int matchNumber = Integer.parseInt(upMatchNrA.getText());
            if(matchNumber>matches.size()){
                AlertBox.display("Not existing match", "Please insert a valid match number");
            }
            else {
                for (int i = 0; i < matches.size(); i++) {
                    if (i + 1 == matchNumber) {

                        int player1SetsScore = 0;
                        int player2SetsScore = 0;
                        int ok = 0;
                        List<TennisSet> sets = matches.get(i).getSets();
                        for (int k = 0; k < sets.size(); k++) {
                            List<TennisGame> games = sets.get(k).getGames();
                            for (int j = 0; j < games.size(); j++) {
                                int p1games = Integer.parseInt(games.get(j).getP1Score());
                                int p2games = Integer.parseInt(games.get(j).getP2Score());
                                if (p1games >= 5 && p2games >= 5) {
                                    if (p1games - p2games == 2)
                                        player1SetsScore++;
                                    else if (p2games - p1games == 2)
                                        player2SetsScore++;
                                } else {
                                    if (p1games == 6)
                                        player1SetsScore++;
                                    else if (p2games == 6)
                                        player2SetsScore++;
                                }
                                if (player1SetsScore == 3)
                                    ok = 1;
                                else if (player2SetsScore == 3)
                                    ok = 1;
                            }
                        }
                        if (ok == 0) {
                            TennisSet set = TennisService.createNewSet(matches.get(i));
                        } else AlertBox.display("Match ended", "Match ended, cannot create a new set!");
                    }
                }
            }

        upMatchNrA.clear();
        showMatches();
        }
    }

    public void deleteMatch(){
        if(Validators.validateNumberBox(upMatchNrA.getText())==0){
            AlertBox.display("Wrong input", "Please insert a valid match number");
        }
        else {
            int matchNumber = Integer.parseInt(upMatchNrA.getText());
            if(matchNumber>matches.size()){
                AlertBox.display("Not existing match", "Please insert a valid match number");
            }
            else {
                for (int i = 0; i < matches.size(); i++) {
                    if (i + 1 == matchNumber) {
                        TennisService.deleteMatch(matches.get(i));
                    }
                }
            }
                upMatchNrA.clear();
                showMatches();
            }
    }

    public void generateReport(){
        Reports report = null;
        String rep = repo.getText();
        if(rep.equals("pdf")){
            report = new ReportPdf();
            report.createReport();
            AlertBox.display("Pdf", "Pdf generated successfully, check for it in the directories");
        }
        else if(rep.equals("txt")) {
            report = new ReportTxt();
            report.createReport();
            AlertBox.display("Txt", "Txt generated successfully, check for it in the directories");
            }
        else AlertBox.display("Reports", "Invalid format inserted. Try again!");
        repo.clear();
    }


}
