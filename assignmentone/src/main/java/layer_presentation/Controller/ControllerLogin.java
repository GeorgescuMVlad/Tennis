package layer_presentation.Controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import layer_business.Functions;
import layer_data_access.repo.GenericRepo;
import layer_presentation.GUI;
import layer_presentation.util.AlertBox;
import model.User;

import java.io.IOException;

public class ControllerLogin {
	
	@FXML
	public Button loginButton;
	
	@FXML
	public TextField mail;
	
	@FXML
	public TextField password;

	@FXML
	public TextField role;


	public void login() throws IOException {
		Functions f = new Functions();

		if (mail.getText().equals("") || password.getText().equals("") || role.getText().equals(""))
			AlertBox.display("No input", "You forgot to write your mail/password/role");
		else if (!role.getText().equals("admin") && !role.getText().equals("user")) {
			AlertBox.display("Wrong role", "Insert admin for admin role or user for user/player role");
		} else {
			if (role.getText().equals("user")) {
				System.out.println(f.loginPlayer(mail.getText(), password.getText()));
				Scene scene = GUI.changeScene("user.fxml");

			} else {
				System.out.println(f.loginAdmin(mail.getText(), password.getText()));
				Scene scene = GUI.changeScene("admin.fxml");

			}

		}
	}







}
