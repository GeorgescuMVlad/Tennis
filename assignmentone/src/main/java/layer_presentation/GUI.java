package layer_presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import layer_data_access.repo.GenericRepo;
import model.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class GUI extends Application //implements EventHandler<ActionEvent>
{
	private Stage window;

    public static void main( String[] args )
    {
    	//PT POPULAREA BAZEI DE DATE TREBUIE DECOMENTATE LINIILE SI RULAT CODUL O DATA!!
		//IMPORTANT!!!!!!!!!!!!!!!!!!IMPORTANT!!!!!!!!!!!!IMPORTANT!!!!!!!!
		/*DACA ALEGI VARIANTA ASTA, DECOMENTEZI LINIILE ASTEA, RULEZI SI INCHIZI!! APOI COMENTEZI INAPOI LINIILE ASTEA
		SI RULEZI DIN NOU APLICATIA SI TE LOGHEZI CU CE VREI SI TESTEZI APLICATIA. CA DACA NU FACI ASA O SA DEA O
		EXCEPTIE CAND DAI SHOW MATCHES, NU AR TREBUI DAR O DA.. DAR DACA FACI ASA CUM AM ZIS, NU MAI E NICIUN BAI LA
		NICIO RULARE ULTERIOARA. PS: WEIRD THINGS HAPPEN SOMETIMES :/ */
		/*DACA ALEGI VARIANTA 2: SA DAI IMPORT LA SCRIPTUL .SQL, ATUNCI FACI A NEW SCHEMA IN MYSQL NUMITA tenis si
		APOI DAI IMPORT IN EA LA tenis.sql FILE PE CARE AM ATASAT-O CU PROIECTUL, IAR LINIILE ASTEA DE MAI JOS VOR
		RAMANE MEREU COMENTATE. IN CAZUL ASTA NU MAI TREBUIE INCHISA APLICATIA LA PRIMA RULARE CI TE POTI LOGA CA
		USER SAU ADMIN SI SA O TESTEZI. */
		/* PENTRU CA SA ITI VINA USOR SA O FOLOSESTI, AM ATASAT UN FISIER TXT CARE SE NUMESTE INSTRUCTIUNIapp CU
		PROIECTUL CARE CONTINE INSTRUCTIUNILE DE FOLOSIRE. BETTER SAFE THAN SORRY! :) */
			/*
			User federer = User.builder().mail("mail@federer").password("federer").name("Federer").role(FALSE).build();
			GenericRepo.save(federer);
			User nadal = User.builder().mail("mail@nadal").password("nadal").name("Nadal").role(FALSE).build();
			GenericRepo.save(nadal);
			User murray = User.builder().mail("mail@murray").password("murray").name("Murray").role(FALSE).build();
			GenericRepo.save(murray);
			User djokovic = User.builder().mail("mail@djokovic").password("djokovic").name("Djokovic").role(FALSE).build();
			GenericRepo.save(djokovic);
			User thiem = User.builder().mail("mail@thiem").password("thiem").name("Thiem").role(FALSE).build();
			GenericRepo.save(thiem);
			User halep = User.builder().mail("mail@halep").password("halep").name("Halep").role(FALSE).build();
			GenericRepo.save(halep);
			Admin vlad = Admin.builder().mail("mail@vlad").password("vlad").name("Vlad").role(TRUE).build();
			GenericRepo.save(vlad);
			Admin admin = Admin.builder().mail("mail@admin").password("admin").name("Admin").role(TRUE).build();
			GenericRepo.save(admin);
			Admin andreea = Admin.builder().mail("mail@andreea").password("andreea").name("Andreea").role(TRUE).build();
			GenericRepo.save(andreea);

			TennisMatch match1 = TennisMatch.builder().player1(federer).player2(halep).build();
			GenericRepo.save(match1);
			TennisMatch match2 = TennisMatch.builder().player1(nadal).player2(thiem).build();
			GenericRepo.save(match2);
			TennisMatch match3 = TennisMatch.builder().player1(djokovic).player2(murray).build();
			GenericRepo.save(match3);

			TennisSet set1m1 = TennisSet.builder().tennisMatch(match1).build();
			GenericRepo.save(set1m1);
			TennisSet set2m1 = TennisSet.builder().tennisMatch(match1).build();
			GenericRepo.save(set2m1);
			TennisSet set3m1 = TennisSet.builder().tennisMatch(match1).build();
			GenericRepo.save(set3m1);
			TennisGame g1 = TennisGame.builder().p1Score("6").p2Score("2").tennisSet(set1m1) .build();
			GenericRepo.save(g1);
			TennisGame g2 = TennisGame.builder().p1Score("6").p2Score("4").tennisSet(set2m1) .build();
			GenericRepo.save(g2);
			TennisGame g3 = TennisGame.builder().p1Score("1").p2Score("5").tennisSet(set3m1) .build();
			GenericRepo.save(g3);

			TennisSet set1m2 = TennisSet.builder().tennisMatch(match2).build();
			GenericRepo.save(set1m2);
			TennisSet set2m2 = TennisSet.builder().tennisMatch(match2).build();
			GenericRepo.save(set2m2);
			TennisSet set3m2 = TennisSet.builder().tennisMatch(match2).build();
			GenericRepo.save(set3m2);
			TennisSet set4m2 = TennisSet.builder().tennisMatch(match2).build();
			GenericRepo.save(set4m2);
			TennisGame g1m2 = TennisGame.builder().p1Score("3").p2Score("6").tennisSet(set1m2) .build();
			GenericRepo.save(g1m2);
			TennisGame g2m2 = TennisGame.builder().p1Score("7").p2Score("5").tennisSet(set2m2) .build();
			GenericRepo.save(g2m2);
			TennisGame g3m2 = TennisGame.builder().p1Score("6").p2Score("0").tennisSet(set3m2) .build();
			GenericRepo.save(g3m2);
			TennisGame g4m2 = TennisGame.builder().p1Score("4").p2Score("4").tennisSet(set4m2) .build();
			GenericRepo.save(g4m2);

			TennisSet set1m3 = TennisSet.builder().tennisMatch(match3).build();
			GenericRepo.save(set1m3);
			TennisSet set2m3 = TennisSet.builder().tennisMatch(match3).build();
			GenericRepo.save(set2m3);
			TennisSet set3m3 = TennisSet.builder().tennisMatch(match3).build();
			GenericRepo.save(set3m3);
			TennisGame g1m3 = TennisGame.builder().p1Score("1").p2Score("6").tennisSet(set1m3) .build();
			GenericRepo.save(g1m3);
			TennisGame g2m3 = TennisGame.builder().p1Score("0").p2Score("6").tennisSet(set2m3) .build();
			GenericRepo.save(g2m3);
			TennisGame g3m3 = TennisGame.builder().p1Score("4").p2Score("5").tennisSet(set3m3) .build();
			GenericRepo.save(g3m3);
			*/

		launch(args);
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
    	ClassLoader classLoader = GUI.class.getClassLoader();
		Parent root = FXMLLoader.load(classLoader.getResource("login.fxml"));
		window=primaryStage;

		Scene mainScene = new Scene(root);
		mainScene.getStylesheets().add("Style.css");
		window.setScene(mainScene);
		window.setTitle("Login Tennis Club App");
		window.show();
	}
		

	public static Scene changeScene(String fxml) throws IOException{
		ClassLoader classLoader = GUI.class.getClassLoader();
		Parent pane = FXMLLoader.load(classLoader.getResource(fxml));
		
		Stage stage = new Stage();
        Scene newScene = new Scene(pane);
        newScene.getStylesheets().add("Style.css");
		stage.setScene(newScene);
		stage.show();
		
		return newScene;
	}
}
