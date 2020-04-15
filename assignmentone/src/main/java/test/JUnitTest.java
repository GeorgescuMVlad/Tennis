package test;

import javafx.collections.ObservableList;
import junit.framework.TestCase;
import layer_business.TennisService;
import layer_data_access.repo.GenericRepo;
import model.TennisGame;
import model.TennisMatch;
import model.TennisSet;
import model.User;


import java.util.List;

import static java.lang.Boolean.FALSE;
import static layer_business.TennisService.createNewMatch;
import static layer_business.TennisService.createNewSet;


public class JUnitTest extends TestCase{


    public void testCreateNewMatch(){
        User player1 = User.builder().mail("mail@x").password("x").name("X").role(FALSE).build();
        User player2 = User.builder().mail("mail@y").password("y").name("Y").role(FALSE).build();
        TennisMatch tennisMatch = TennisMatch.builder().player1(player1).player2(player2).build();
        GenericRepo.save(player1);
        GenericRepo.save(player2);
        TennisMatch match = createNewMatch(player1, player2);
        assertEquals(tennisMatch.getPlayer1().getName(), match.getPlayer1().getName());
        assertEquals(tennisMatch.getPlayer2().getName(), match.getPlayer2().getName());
    }



    public void testUpdateMatchScore(){
        int matchNumber = 2;  //asa am ales eu, poate fi oricare meci, am ales unu din ele
        int player1Score = 5;
        int player2Score = 4;
        int setNumber = 4;
        for(int i=0; i<matches.size(); i++) {
            if (i + 1 == matchNumber) {
                int ok = TennisService.updateScore(matches.get(i), player1Score, player2Score, setNumber);
                if(ok == 0){  //this means that the match is not over and the current set is not over and the score to update is valid and
                              //ready to be updated
                    ObservableList<TennisMatch> matchesNew = GenericRepo.findEverything("from model.TennisMatch");
                    List<TennisSet>sets = matchesNew.get(i).getSets();
                    for(int j=0; j<sets.size(); j++) {
                        if (j + 1 == setNumber) {
                            List<TennisGame> games = sets.get(j).getGames();
                            for (int k = 0; k < games.size(); k++) {
                                    String pl1Score = games.get(k).getP1Score();
                                    String pl2Score = games.get(k).getP2Score();
                                    assertEquals(Integer.toString(player1Score), pl1Score);
                                    assertEquals(Integer.toString(player2Score), pl2Score);
                            }
                        }
                    }
                }
            }
        }
    }

    ObservableList<TennisMatch> matches = GenericRepo.findEverything("from model.TennisMatch");
    public void testCreateNewSet() {
        int matchNumber = 1;  //asa am ales eu, poate fi oricare meci, am ales unu din ele
        for (int i = 0; i < matches.size(); i++) {
            if (i + 1 == matchNumber) {
                TennisSet set = TennisService.createNewSet(matches.get(i));
                List<TennisGame> games = set.getGames();
                for (int j = 0; j < games.size(); j++) {
                    assertEquals("0", games.get(j).getP1Score());
                    assertEquals("0", games.get(j).getP2Score());
                }
            }
        }
    }

    public void testDeleteMatch(){
        int matchNumber = 3;  //asa am ales eu, poate fi oricare meci, am ales unu din ele
        for(int i=0; i<matches.size(); i++){
            if(i+1==matchNumber){
                TennisService.deleteMatch(matches.get(i));
            }
        }
        ObservableList<TennisMatch> matchesNew = GenericRepo.findEverything("from model.TennisMatch");
        int sizeMatchList = matchesNew.size();
        int testSizeMatchList = matches.size()-1;
        assertEquals(Integer.toString(testSizeMatchList), Integer.toString(sizeMatchList));
    }




}
