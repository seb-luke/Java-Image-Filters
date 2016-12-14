package com.warptronic.imgfilters.gui.view;

import java.util.Arrays;

import com.warptronic.imgfilters.filter.ImageFilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ApplyFiltersController {
	
	private AnchorPane applyFiltersView;
	private Stage primaryStage;
	
	@FXML
	ScrollPane imgListScrollPane;
	
	@FXML
	private ImageView mainImage;
	
	public ApplyFiltersController() {
		// empty constructor
	}
	
	public void setInitialData(AnchorPane applyFiltersView, Stage primaryStage, Image image) {
		this.mainImage.setImage(image);
		
		this.applyFiltersView = applyFiltersView;
		this.primaryStage = primaryStage;
		
		BorderPane rootLayout = (BorderPane) this.primaryStage.getScene().getRoot();
		rootLayout.prefHeightProperty().bind(this.applyFiltersView.prefHeightProperty());
		rootLayout.prefWidthProperty().bind(this.applyFiltersView.prefWidthProperty());
		
		this.primaryStage.setWidth(this.applyFiltersView.getPrefWidth());
		this.primaryStage.setHeight(this.applyFiltersView.getPrefHeight());

		setScrollPaneContents();
	}

	private void setScrollPaneContents() {
		
		ImageFilter filter = new ImageFilter(mainImage.getImage());
		
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		
		ObservableList<Node> images = FXCollections.observableArrayList();
		for (int i = 0; i < 6; i++) {
			ImageView img = new ImageView(filter.getGrayScaleImage());
			img.fitWidthProperty().bind(imgListScrollPane.widthProperty());
			img.setPreserveRatio(true);
			img.setSmooth(false);
			
			images.add(img);
		}
		
		vbox.getChildren().addAll(images);
		imgListScrollPane.setContent(vbox);
	}

}




