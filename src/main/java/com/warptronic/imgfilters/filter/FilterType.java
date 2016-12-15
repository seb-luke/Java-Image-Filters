package com.warptronic.imgfilters.filter;

public enum FilterType {
	
	ORIGINAL("Original Image"),
	GRAYSCALE("Grayscale"),
	SEPIA("Sepia"),
	OLD("Oldies"),
	BLUR("Gaussian Blur"),
	SHARPEN("Sharpened Image");
	
	private final String filterName;
	
	private FilterType(String filterName) {
		this.filterName = filterName;
	}
	
	public String getName() {
		return this.filterName;
	}

}
