package layer_business;

import javafx.collections.ObservableList;
import layer_data_access.repo.GenericRepo;
import model.TennisGame;
import model.TennisMatch;
import model.TennisSet;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class ReportTxt implements Reports {
    @Override
    public void createReport() {

        try {
            PrintWriter out = new PrintWriter("MatchReport.txt");
            out.print("Players       ");
            out.print("Set1       ");
            out.print("Set2       ");
            out.print("Set3       ");
            out.print("Set4       ");
            out.print("Set5 \n\n");
            ObservableList<TennisMatch> matches = GenericRepo.findEverything("from model.TennisMatch");
            for(int i=0; i<matches.size(); i++){
                String player = null;

                player = matches.get(i).getPlayer1().getName();
                player = String.format("%-15s", player);

                List<TennisSet> sets = matches.get(i).getSets();
                for(int j=0; j<sets.size(); j++){
                    List<TennisGame> games = sets.get(j).getGames();
                    for(int k=0; k<games.size(); k++){
                        String gamesP1 = games.get(k).getP1Score() + "          ";
                        player = player + gamesP1;
                    }
                }
                out.print(player);
                out.print("\n");

                player = matches.get(i).getPlayer2().getName();
                player = String.format("%-15s", player);

                for(int j=0; j<sets.size(); j++){
                    List<TennisGame> games = sets.get(j).getGames();
                    for(int k=0; k<games.size(); k++){
                        String gamesP2 = games.get(k).getP2Score() + "          ";
                        player = player + gamesP2;
                    }
                }
                player = player + " \n\n";
                out.print(player);
            }

            out.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
