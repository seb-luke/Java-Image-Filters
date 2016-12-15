package com.warptronic.imgfilters.gui.view;

import java.io.File;
import java.io.IOException;

import com.warptronic.imgfilters.core.ImageExtensions;
import com.warptronic.imgfilters.gui.GuiBaseApplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
	}
	
	public void setDefaultImage(Image defaultImage) {
		this.imageView.setImage(defaultImage);
	}
	
	@FXML
	private void handleBrowseImage() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(ImageExtensions.getOpenImageExtensioFilters());
		
		File imgFile = fileChooser.showOpenDialog(primaryStage);

		if (imgFile != null) {
			Image image = new Image("file:" + imgFile.getAbsolutePath(), true);
			this.imageView.setImage(image);
			
			okButton.setDisable(false);
		}
	}
	
	@FXML
	private void okPushed() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GuiBaseApplication.class.getResource("view/ApplyFilters.fxml"));
		
		try {
			AnchorPane applyFiltersView = (AnchorPane) loader.load();
			BorderPane rootLayout = (BorderPane) primaryStage.getScene().getRoot();
			rootLayout.setCenter(applyFiltersView);
			
			ApplyFiltersController controller = loader.getController();
			controller.setInitialData(applyFiltersView, primaryStage, imageView.getImage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
