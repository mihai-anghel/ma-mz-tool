package ro.anghel.mz.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;

public abstract class AbstractTShirtIcon extends ImageIcon {

	private static final long serialVersionUID = 1L;

	private Color priColor = null;

	private Color secColor = null;

	private Color numberColor = null;

	private static final int DEF_PRI_RGB = new Color(0x80, 0x00, 0x00).getRGB();

	private static final int DEF_SEC_RGB = new Color(0xFF, 0xFF, 0xFF).getRGB();

	public AbstractTShirtIcon(final String aImageFileName,
			final Color aPriColor, final Color aSecColor,
			final Color aNumberColor) {
		super(AbstractTShirtIcon.class.getClassLoader()
				.getResource(aImageFileName));

		priColor = aPriColor;
		secColor = aSecColor;
		numberColor = aNumberColor;

		BufferedImage buffImage = getBufferedImage();
		if (buffImage == null) {
			buffImage = toBufferedImage(getImage());
			final int priRGB = aPriColor.getRGB();
			final int secRGB = aSecColor.getRGB();
			final int h = getIconHeight();
			final int w = getIconWidth();
			for (int y = 0; y < h; ++y) {
				for (int x = 0; x < w; ++x) {
					final int rgb = buffImage.getRGB(x, y);
					if (rgb == DEF_PRI_RGB) {
						buffImage.setRGB(x, y, priRGB);
					} else if (rgb == DEF_SEC_RGB) {
						buffImage.setRGB(x, y, secRGB);
					}
				}
			}
			setBufferedImage(buffImage);
		}

		setImage(buffImage);
	}

	protected abstract BufferedImage getBufferedImage();

	protected abstract void setBufferedImage(BufferedImage aBufferedImage);

	public static boolean hasAlpha(final Image aImage) {
		// If buffered image, the color model is readily available
		if (aImage instanceof final BufferedImage bimage) {
			return bimage.getColorModel().hasAlpha();
		}

		// Use a pixel grabber to retrieve the image's color model;
		// grabbing a single pixel is usually sufficient
		final PixelGrabber pg = new PixelGrabber(aImage, 0, 0, 1, 1, false);
		try {
			pg.grabPixels();
		} catch (final InterruptedException e) {
		}

		// Get the image's color model
		final ColorModel cm = pg.getColorModel();
		return cm.hasAlpha();
	}

	public static BufferedImage toBufferedImage(Image aImage) {
		if (aImage instanceof BufferedImage) {
			return (BufferedImage) aImage;
		}

		// This code ensures that all the pixels in the image are loaded
		aImage = new ImageIcon(aImage).getImage();

		// Determine if the image has transparent pixels
		final boolean hasAlpha = hasAlpha(aImage);

		// Create a buffered image with a format that's compatible with the
		// screen
		BufferedImage bimage = null;
		final GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		try {
			// Determine the type of transparency of the new buffered image
			int transparency = Transparency.OPAQUE;
			if (hasAlpha) {
				transparency = Transparency.BITMASK;
			}

			// Create the buffered image
			final GraphicsDevice gs = ge.getDefaultScreenDevice();
			final GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(aImage.getWidth(null),
					aImage.getHeight(null), transparency);
		} catch (final HeadlessException e) {
			// The system does not have a screen
		}

		if (bimage == null) {
			// Create a buffered image using the default color model
			int type = BufferedImage.TYPE_INT_RGB;
			if (hasAlpha) {
				type = BufferedImage.TYPE_INT_ARGB;
			}
			bimage = new BufferedImage(aImage.getWidth(null),
					aImage.getHeight(null), type);
		}

		// Copy image to buffered image
		final Graphics g = bimage.createGraphics();

		// Paint the image onto the buffered image
		g.drawImage(aImage, 0, 0, null);
		g.dispose();

		return bimage;
	}

	public Color getPriColor() {
		return priColor;
	}

	public Color getSecColor() {
		return secColor;
	}

	public Color getNumberColor() {
		return numberColor;
	}

}
