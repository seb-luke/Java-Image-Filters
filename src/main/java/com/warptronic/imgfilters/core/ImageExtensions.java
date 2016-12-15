package com.warptronic.imgfilters.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;;

public class ImageExtensions {
	
	private static final List<ExtensionFilter> imageExtensionFilters = 
			Arrays.asList(
					new FileChooser.ExtensionFilter("JPEG Image", "*.jpg", "*.jpeg"),
				new FileChooser.ExtensionFilter("BMP Image", "*.bmp"),
				new FileChooser.ExtensionFilter("PNG Image", "*.png")
			);

	private ImageExtensions() {
		// empty constructor
	}
	
	public static Collection<ExtensionFilter> getOpenImageExtensioFilters() {
		List<ExtensionFilter> openExtFilters = new ArrayList<>(imageExtensionFilters);
		openExtFilters.add(0, new FileChooser.ExtensionFilter("All images", "*.bmp", "*.png", "*.jpg", "*.jpeg"));
		
		return openExtFilters;
	}
	
	public static Collection<ExtensionFilter> getSaveImageExtensionFilters() {
		return imageExtensionFilters;
	}

}