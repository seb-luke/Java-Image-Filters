package com.warptronic.imgfilters.gui.view;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.warptronic.imgfilters.core.ImageExtensions;
import com.warptronic.imgfilters.filter.FilterType;
import com.warptronic.imgfilters.filter.ImageFilter;
import com.warptronic.imgfilters.gui.GuiBaseApplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ApplyFiltersController {
	
	private Stage primaryStage;
	private RootLayoutController rootController;
	
	@FXML
	ScrollPane imgListScrollPane;
	
	@FXML
	private ImageView mainImage;
	private Image originalImage;
	
	@FXML
	private Button exportButton;
	
	public ApplyFiltersController() {
		// empty constructor
	}
	
	public void setInitialData(Stage primaryStage, Image image, RootLayoutController rootController) {
		this.mainImage.setImage(image);
		this.originalImage = image;
		this.primaryStage = primaryStage;
		this.rootController = rootController;

		setScrollPaneContents();
	}

	private void setScrollPaneContents() {
		
		ImageFilter filter = new ImageFilter(mainImage.getImage(), true);
		
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		
		ObservableList<Node> images = FXCollections.observableArrayList();
		
		for (FilterType filterType : FilterType.values()) {
			ImageView imageView = getFilteredView(filterType, filter);
			imageView.setOnMouseClicked(event -> handleFilterClicked(event, filterType));
			
			Label label = new Label(filterType.getName());
			
			images.add(imageView);
			images.add(label);
		}
		
		vbox.getChildren().addAll(images);
		imgListScrollPane.setContent(vbox);
	}
	
	private ImageView getFilteredView(FilterType filterType, ImageFilter filter) {
		
		ImageView imageView = new ImageView(filter.getFilteredImage(filterType));
		imageView.fitWidthProperty().bind(imgListScrollPane.widthProperty());
		imageView.setPreserveRatio(true);
		imageView.setSmooth(false);
		imageView.setUserData(filterType);
		
		return imageView;
	}
	
	private void handleFilterClicked(MouseEvent event, FilterType filterType) {
		
		ImageFilter filter = new ImageFilter(this.originalImage);
		this.mainImage.setImage(filter.getFilteredImage(filterType));
	}
	
	@FXML
	private void handleBackClicked() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GuiBaseApplication.class.getResource("view/LoadImage.fxml"));
		
		try {
			AnchorPane loadImageOverview = (AnchorPane) loader.load();
			BorderPane rootLayout = (BorderPane) primaryStage.getScene().getRoot();
			rootLayout.setCenter(loadImageOverview);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LoadImageController controller = loader.getController();
		controller.setControllerData(this.primaryStage, this.rootController);
		controller.setDefaultImage(originalImage);
	}
	
	@FXML
	private void handleExportClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().addAll(ImageExtensions.getSaveImageExtensionFilters());
        
        File file = fileChooser.showSaveDialog(this.primaryStage);
        
        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(this.mainImage.getImage(), null), "png", file);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
	}

}




