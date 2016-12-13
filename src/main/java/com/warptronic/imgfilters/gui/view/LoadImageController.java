package com.warptronic.imgfilters.gui.view;

import java.io.File;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoadImageController {
	
	private Stage primaryStage;
	
	@FXML
	private Button okButton;
	
	@FXML
	private ImageView imageView;

	public LoadImageController() {
		// empty constructor
	}
	
	@FXML
	private void initialize() {
		okButton.setDisable(true);
		this.imageView.setPreserveRatio(true);
	}
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
//		imageView.fitWidthProperty().bind(primaryStage.widthProperty());
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
			
			okButton.setDisable(false);
		}
	}
	
	@FXML
	private void okPushed() {
		//change BorderPane center to a new Layout
	}

}
