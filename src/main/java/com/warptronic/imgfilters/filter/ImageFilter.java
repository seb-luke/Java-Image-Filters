package com.warptronic.imgfilters.filter;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

import javafx.embed.swing.SwingFXUtils;

public class ImageFilter {
	
	private static final int THUMBNAIL_WIDTH = 600;
//	private static final int THUMBNAIL_HEIGHT = 500;
	private final BufferedImage originalImage;

	public ImageFilter(Image image) {
	    this(image, false);
	}
	
	public ImageFilter(Image image, boolean convertToThumbnail) {
	    if (convertToThumbnail) {
	    	this.originalImage = toBufferedImage(image, THUMBNAIL_WIDTH);
	    } else {
	    	if (image instanceof BufferedImage) {
		        this.originalImage = (BufferedImage) image;
		    } else {
		    	this.originalImage = toBufferedImage(image, image.getWidth(null));
		    }
	    }
	}
	
	public ImageFilter(javafx.scene.image.Image image) {
		this(SwingFXUtils.fromFXImage(image, null));
	}
	
	public ImageFilter(javafx.scene.image.Image image, boolean convertToThumbnail) {
		this(SwingFXUtils.fromFXImage(image, null), convertToThumbnail);
	}
	
    public BufferedImage getGrayScale() {
        BufferedImage gray = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        op.filter(originalImage, gray);

        return gray;
    }
    
    public javafx.scene.image.Image getGrayScaleImage() {
    	return SwingFXUtils.toFXImage(getGrayScale(), null);
    }
	
	private static BufferedImage toBufferedImage(Image image, int width) {
		int height = image.getHeight(null) * width / image.getWidth(null);
				
	    BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(image, 0, 0, width, height, null);
	    bGr.dispose();

	    return bimage;
	}
}
