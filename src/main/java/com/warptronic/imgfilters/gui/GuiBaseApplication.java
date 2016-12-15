package com.warptronic.imgfilters.gui;

import java.io.IOException;

import com.warptronic.imgfilters.gui.view.LoadImageController;
import com.warptronic.imgfilters.gui.view.RootLayoutController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GuiBaseApplication extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	private RootLayoutController rootController;

	public GuiBaseApplication() {
		// empty for now
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Java Image Filters");
		
		initRootLayout();
		initializeLoadImageView();
	}
	
	private void initRootLayout() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GuiBaseApplication.class.getResource("view/RootLayout.fxml"));
		
		try {
			rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		rootController = loader.getController();
		rootController.setGuiBaseApp(this);
		
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void initializeLoadImageView() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GuiBaseApplication.class.getResource("view/LoadImage.fxml"));
		
		try {
			AnchorPane loadImageOverview = (AnchorPane) loader.load();
			rootLayout.setCenter(loadImageOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LoadImageController controller = loader.getController();
		controller.setControllerData(this.primaryStage, this.rootController);
	}
}