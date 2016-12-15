package com.warptronic.imgfilters.gui.view;

import java.util.Optional;

import com.warptronic.imgfilters.gui.GuiBaseApplication;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;

public class RootLayoutController {
	
	private GuiBaseApplication guiBaseApp;
	
	@FXML
	private MenuItem removeButton;

	public RootLayoutController() {
		// empty constructor
	}
	
	@FXML
	private void initialize() {
		removeButton.setDisable(true);
	}
	
	@FXML
	private void handleClose() {
		System.exit(0);
	}
	
	@FXML
	private void handleRemove() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setTitle("Remove Image Prompt");
		alert.setContentText("Are you sure you want to remove the image? All changes will be lost!");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK){
			guiBaseApp.initializeLoadImageView();
			this.removeButton.setDisable(true);
		} else {
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Java Image Filters");
			alert.setHeaderText(null);
			alert.setContentText("Nothing changed!");

			alert.showAndWait();
		}
	}
	
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Java Image Filters");
		alert.setHeaderText("Thank you for using our small project!");
		alert.setContentText("This software was written, built and distributed "
				+ "as a part of the GPS project for the Software Engineering Master Program "
				+ "\n\n\t\t\t\tby Sebastian Luke");
		

		alert.showAndWait();
	}
	
	public void setGuiBaseApp(GuiBaseApplication baseApp) {
		this.guiBaseApp = baseApp;
	}
	
	public void enableRemoveImage() {
		this.removeButton.setDisable(false);
	}

}
