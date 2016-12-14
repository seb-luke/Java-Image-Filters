package com.warptronic.imgfilters.filter;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

import javafx.embed.swing.SwingFXUtils;

public class ImageFilter {
	
	private final BufferedImage originalImage;

	public ImageFilter(Image image) {
	    if (image instanceof BufferedImage) {
	        this.originalImage = (BufferedImage) image;
	    } else {
	    	this.originalImage = toBufferedImage(image);
	    }
	}
	
	public ImageFilter(javafx.scene.image.Image image) {
		this(SwingFXUtils.fromFXImage(image, null));
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
	
	private static BufferedImage toBufferedImage(Image imgage) {

	    BufferedImage bimage = new BufferedImage(imgage.getWidth(null), imgage.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(imgage, 0, 0, null);
	    bGr.dispose();

	    return bimage;
	}
}
