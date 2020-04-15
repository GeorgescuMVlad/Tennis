package layer_business;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import javafx.collections.ObservableList;
import layer_data_access.repo.GenericRepo;
import model.TennisGame;
import model.TennisMatch;
import model.TennisSet;

import javax.print.Doc;

public class ReportPdf implements Reports {

    @Override
    public void createReport() {

        try{
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("MatchReport.pdf"));

            document.open();
            document.addAuthor("Vlad Georgescu");
            document.addTitle("Tennis Club Matches Report");
            document.add(new Paragraph("                                                              Tennis Club Matches Report                                              Author: Vlad Georgescu\n\n"));

            document.add(new Paragraph("Players \t\t\t    Set1 \t\t    Set2 \t\t    Set3 \t\t    Set4 \t\t    Set5 \n\n" ));
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
                Paragraph p = new Paragraph(player);
                document.add(p);

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
                p = new Paragraph(player);
                document.add(p);
            }

            document.close();

        }catch(Exception ex) {
        }

    }
}
