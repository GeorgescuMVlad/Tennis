package layer_business;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import layer_data_access.repo.GenericRepo;
import model.TennisGame;
import model.TennisMatch;
import model.TennisSet;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TennisService {

    public static TennisMatch createNewMatch(User player1, User player2) {

        TennisMatch tennisMatch = TennisMatch.builder().player1(player1).player2(player2).build();
        TennisSet tennisSet = TennisSet.builder().tennisMatch(tennisMatch).build();
        TennisGame games = TennisGame.builder().p1Score("0").p2Score("0").tennisSet(tennisSet).build();

        tennisSet.setGames(Collections.singletonList(games));
        List<TennisSet> sets = new ArrayList<>();
        sets.add(tennisSet);
        tennisMatch.setSets(sets);

        GenericRepo.save(tennisMatch);
        GenericRepo.save(tennisSet);
        GenericRepo.save(games);

        return tennisMatch;
    }

    public static void deleteMatch(TennisMatch match) {

        List<TennisSet> sets = match.getSets();
        for(int i=0; i<sets.size(); i++){
            List<TennisGame> games = sets.get(i).getGames();
            for(int j=0; j<games.size(); j++){
                GenericRepo.delete(games.get(j));
            }
            GenericRepo.delete(sets.get(i));
        }
        GenericRepo.delete(match);
    }

    public static TennisSet createNewSet(TennisMatch match) {

        TennisSet tennisSet = TennisSet.builder().tennisMatch(match).build();
        TennisGame games = TennisGame.builder().p1Score("0").p2Score("0").tennisSet(tennisSet).build();

        tennisSet.setGames(Collections.singletonList(games));
        List<TennisSet> sets = match.getSets();

        sets.add(tennisSet);
        match.setSets(sets);

        GenericRepo.save(tennisSet);
        GenericRepo.save(games);

        return tennisSet;
    }

    /*
    private static TennisGame getEmptyTennisGame() {
        TennisGame tennisGame = new TennisGame();
        tennisGame.setP1Score("0");
        tennisGame.setP2Score("0");
        return tennisGame;
    }*/

    public static int updateScore(TennisMatch m, int p1score, int p2score, int setNumber){
        int ok =0;
        List<TennisSet>sets = m.getSets();
        for(int i=0; i<sets.size(); i++){

            //partea pt update score si set end detection
            if(i+1==setNumber) {
                List<TennisGame> games = sets.get(i).getGames();
                for (int j = 0; j < games.size(); j++) {
                    int player1Score = Integer.parseInt(games.get(j).getP1Score());
                    int player2Score = Integer.parseInt(games.get(j).getP2Score());

                    if(player1Score==6 && player2Score <5){
                        return 2;
                    }

                    if(player2Score==6 && player1Score <5){
                        return 2;
                    }

                    if( ((player1Score>=5 && player2Score>=5) &&
                            ( Math.abs(p1score-p2score) >2) &&  Math.abs(player1Score-player2Score) ==2)  && (player1Score == 6 || player2Score ==6) )
                        {return 2;}

                    if ( (player1Score <= 6 && player2Score <= 6 && (p1score<=6 && p2score<=6))  ||
                            (Math.abs(player1Score-player2Score) <=2  && (player1Score>=5 && player1Score>=5)) &&
                                    (Math.abs(p1score-p2score) <=2 && (p1score>=5 && p2score>=5))
                         ) {
                        player1Score = p1score;
                        player2Score = p2score;

                        games.get(j).setP1Score(Integer.toString(player1Score));
                        games.get(j).setP2Score(Integer.toString(player2Score));
                        GenericRepo.update(games.get(j));

                    }
                    else{return 1;}
                }
            }
        }

        //partea pt match end detection
        int player1SetsScore=0;
        int player2SetsScore=0;
        for(int i=0; i<sets.size(); i++) {
            List<TennisGame> games = sets.get(i).getGames();
            for (int j = 0; j < games.size(); j++) {
                int p1games=Integer.parseInt(games.get(j).getP1Score());
                int p2games=Integer.parseInt(games.get(j).getP2Score());
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
                    return 3;
                else if (player2SetsScore == 3)
                    return 4;
            }
        }
        return ok;
    }

}
