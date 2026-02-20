package ro.anghel.mz.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public abstract class AbstractTShirtSmallIcon extends AbstractTShirtIcon {

	private static final long serialVersionUID = 1L;

	private static final Color[] TRAIN_CAMP_COLORS = { new Color(0, 101, 0),
			new Color(62, 134, 0), new Color(86, 186, 0),
			new Color(119, 253, 0), new Color(182, 255, 51),
			new Color(242, 245, 0) };

	private String injurySeverity = "";

	private boolean atTrainingCamp = false;

	protected AbstractTShirtSmallIcon(final String aImageFileName,
			final Color aPriColor, final Color aSecColor, final Color aNrColor,
			final String aInjurySeverity, final boolean aAtTrainingCamp) {
		super(aImageFileName, aPriColor, aSecColor, aNrColor);
		injurySeverity = aInjurySeverity;
		atTrainingCamp = aAtTrainingCamp;
	}

	@Override
	public synchronized void paintIcon(final Component aComponent,
			final Graphics aGraphics, final int aX, final int aY) {
		super.paintIcon(aComponent, aGraphics, aX, aY);
		final Graphics g = aGraphics.create(aX, aY, getIconWidth(),
				getIconHeight());
		drawInjuredOrTC(g);
		g.dispose();
	}

	private void drawInjuredOrTC(final Graphics aGraphics) {
		if (!"".equals(injurySeverity)) {
			aGraphics.setColor(Color.WHITE);
			final int x = getIconWidth() - 10;
			final int y = getIconHeight() - 8;
			aGraphics.fillRect(x, y, 8, 8);
			aGraphics.setColor(
					"Recover".equals(injurySeverity) ? new Color(255, 153, 0)
							: Color.RED);
			aGraphics.fillRect(x + 1, y + 3, 6, 2);
			aGraphics.fillRect(x + 3, y + 1, 2, 6);
		} else if (atTrainingCamp) {
			aGraphics.setColor(Color.WHITE);
			final int x = getIconWidth() - 10;
			final int y = getIconHeight() - 8;
			aGraphics.drawRect(x, y, 7, 7);

			for (int i = 0; i < TRAIN_CAMP_COLORS.length; ++i) {
				aGraphics.setColor(TRAIN_CAMP_COLORS[i]);
				aGraphics.drawLine(x + 1, y + 1 + i, x + 6, y + 1 + i);
			}
		}
	}

	public String getInjurySeverity() {
		return injurySeverity;
	}

	public boolean isAtTrainingCamp() {
		return atTrainingCamp;
	}

}
