package ro.anghel.mz.ui;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class TShirtLargeIcon extends AbstractTShirtIcon {

	private static final long serialVersionUID = 1L;

	private static BufferedImage bufferedImage = null;

	public TShirtLargeIcon(final int aShirtPattern, final Color aPriColor,
			final Color aSecColor, final Color aNrColor) {
		super("gui/t" + aShirtPattern + ".gif", aPriColor, aSecColor, aNrColor);
	}

	@Override
	protected BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	@Override
	protected void setBufferedImage(final BufferedImage aBufferedImage) {
		bufferedImage = aBufferedImage;
	}

}
