package com.warptronic.imgfilters.filter;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import javafx.embed.swing.SwingFXUtils;

public class ImageFilter {
	
	private static final int THUMBNAIL_WIDTH = 600;
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
	
	/*******/
	
	public javafx.scene.image.Image getOriginalImage() {
		return SwingFXUtils.toFXImage(originalImage, null);
	}
	
	/**** GRAYSCALE IMAGE ****/
	
	public javafx.scene.image.Image getGrayScaleImage() {
		return SwingFXUtils.toFXImage(getGrayScale(), null);
	}
	
    private BufferedImage getGrayScale() {
        BufferedImage gray = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        op.filter(originalImage, gray);

        return gray;
    }
    
    /**** END GRAYSCALE IMAGE ****/
    
    
    /**** SEPIA IMAGE ****/
    
    /**
     * @see {@link http://stackoverflow.com/a/21900125/3356879}
     * @param sepiaIntensity
     * @return
     */
    private BufferedImage getSepia(int sepiaIntensity) {

    	int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int sepiaDepth = 20;

        int[] imagePixels = originalImage.getRGB(0, 0, width, height, null, 0, width);

        for (int i = 0; i < imagePixels.length; i++) {
            int color = imagePixels[i];

            int r = (color >> 16) & 0xff;
            int g = (color >> 8) & 0xff;
            int b = (color) & 0xff;
            int gry = (r + g + b) / 3;

            r = g = b = gry;
            r = r + (sepiaDepth * 2);
            g = g + sepiaDepth;

            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }

            // Darken blue color to increase sepia effect
            b -= sepiaIntensity;

            // normalize if out of bounds
            if (b < 0) {
                b = 0;
            }
            if (b > 255) {
                b = 255;
            }

            imagePixels[i] = (color & 0xff000000) + (r << 16) + (g << 8) + b;
        }

        BufferedImage res = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        res.setRGB(0, 0, width, height, imagePixels, 0, width);
        return res;
    }
    
    public javafx.scene.image.Image getSepiaImage(int sepiaIntensity) {
    	return SwingFXUtils.toFXImage(this.getSepia(sepiaIntensity), null);
    }
    
    /**** END SEPIA IMAGE ****/
    
    /**** BLURED IMAGE ****/
    
    private BufferedImage getBlured() {
    	
        float[] blurMatrix = { 1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f,
        						1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f };
        BufferedImageOp blurFilter = new ConvolveOp(new Kernel(3, 3, blurMatrix), ConvolveOp.EDGE_NO_OP, null);
        
        return blurFilter.filter(originalImage, null);
    }
    
    public javafx.scene.image.Image getBluredImage() {
    	return SwingFXUtils.toFXImage(this.getBlured(), null);
    }
    
    /**** END BLURED IMAGE ****/
    
    /**** SHARPENED IMAGE ****/
    
    private BufferedImage getSharpened() {
    	
        float[] sharpenMatrix = { 0.0f, -1.0f, 0.0f, -1.0f, 5.0f, -1.0f, 0.0f, -1.0f, 0.0f };
        BufferedImageOp sharpenFilter = new ConvolveOp(new Kernel(3, 3, sharpenMatrix), ConvolveOp.EDGE_NO_OP, null);
        
        return sharpenFilter.filter(originalImage, null);
	}
    
    public javafx.scene.image.Image getSharpenedImage() {
    	return SwingFXUtils.toFXImage(this.getSharpened(), null);
    }
    
    /**** END SHARPENED IMAGE ****/
    
	public javafx.scene.image.Image getFilteredImage(FilterType filterType) {
		javafx.scene.image.Image image;
		
		switch (filterType) {
			case GRAYSCALE:
				image = this.getGrayScaleImage();
				break;
			case BLUR:
				image = this.getBluredImage();
				break;
			case SEPIA:
				image = this.getSepiaImage(30);
				break;
			case SHARPEN:
				image = this.getSharpenedImage();
				break;
			case ORIGINAL:
			default:
				image = this.getOriginalImage();
				break;
		}
		
		return image;
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
