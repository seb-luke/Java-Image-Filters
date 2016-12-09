package com.warptronic.imgfilters.gui;

import java.io.IOException;

import com.warptronic.imgfilters.gui.view.LoadImageController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GuiBaseApplication extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;

	public GuiBaseApplication() {
		// empty for now
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Java Image Filters");
		
		initRootLayout();
		showGetImageOverview();
	}
	
	private void initRootLayout() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GuiBaseApplication.class.getResource("view/RootLayout.fxml"));
		
		try {
			rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void showGetImageOverview() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GuiBaseApplication.class.getResource("view/LoadImage.fxml"));
		
		try {
			AnchorPane loadImageOverview = (AnchorPane) loader.load();
			rootLayout.setCenter(loadImageOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LoadImageController controller = loader.getController();
		controller.setPrimaryStage(this.primaryStage);
	}

}








