package com.warptronic.imgfilters.gui.view;

import java.io.File;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoadImageController {
	
	Stage primaryStage;

	public LoadImageController() {
		// empty constructor
	}
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@FXML
	private void handleBrowseImage() {
		FileChooser fileChooser = new FileChooser();
		
		fileChooser.getExtensionFilters().addAll(Arrays.asList(
//				new FileChooser.ExtensionFilter("PNG Image", "*.png"),
//				new FileChooser.ExtensionFilter("JPEG Image", "*.jpeg"),
				new FileChooser.ExtensionFilter("JPG Image", "*.jpg")
				));
		
		File imgFile = fileChooser.showOpenDialog(primaryStage);
		
		if (imgFile != null) {
			
		}

	}

}
