package ro.anghel.mz.ui;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ro.anghel.mz.domain.Position;

public class TShirtSmallIcon extends AbstractTShirtSmallIcon {

	private static final long serialVersionUID = 1L;

	private transient BufferedImage bufferedImage = null;

	public TShirtSmallIcon(final String positionName, final int aShirtPattern,
			final Color aPriColor, final Color aSecColor, final Color aNrColor,
			final String aInjurySeverity, final boolean aAtTrainingCamp) {
		super(Position.GK.name().equalsIgnoreCase(positionName)
				? "gui/ts_gk.gif"
				: "gui/ts" + aShirtPattern + ".gif", //
				aPriColor, aSecColor, aNrColor, aInjurySeverity,
				aAtTrainingCamp);
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
