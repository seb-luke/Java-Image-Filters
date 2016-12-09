package com.warptronic.imgfilters.gui.view;

import java.io.File;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoadImageController {
	
	private Stage primaryStage;
	
	@FXML
	private ImageView imageView;

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
				new FileChooser.ExtensionFilter("All images", "*.bmp", "*.png", "*.jpg", "*.jpeg"),
				new FileChooser.ExtensionFilter("BMP Image", "*.bmp"),
				new FileChooser.ExtensionFilter("PNG Image", "*.png"),
				new FileChooser.ExtensionFilter("JPEG Image", "*.jpeg", ".jpg")
				));
		
		File imgFile = fileChooser.showOpenDialog(primaryStage);

		if (imgFile != null) {
			Image image = new Image("file:" + imgFile.getAbsolutePath(), true);
			this.imageView.setImage(image);
		}
	}

}
