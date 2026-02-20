package ro.anghel.mz.ui.renderer;

import java.awt.Color;

public class ColorCalculator {

	private static final Color[] CORNER_COLORS = { Color.WHITE, Color.BLACK,
			Color.RED, Color.BLUE, Color.YELLOW, Color.CYAN, Color.GREEN,
			Color.MAGENTA };

	public static String toHexColor(final Color color) {
		if (color == null) {
			return null;
		}

		final int r = color.getRed();
		final int g = color.getGreen();
		final int b = color.getBlue();
		final int a = color.getAlpha();

		return toHexColor(r, g, b, a);
	}

	public static String toHexColor(final int r, final int g, final int b,
			final int a) {
		if (a == 255) {
			return String.format("#%02X%02X%02X", r, g, b);
		} else {
			return String.format("#%02X%02X%02X%02X", r, g, b, a);
		}
	}

	public static String toHexColor(final int r, final int g, final int b) {
		return toHexColor(r, g, b, 255);
	}

	public static Color calculateNrColor(final int aShirtPattern,
			final String aPriColor, final String aSecColor) {
		return calculateNrColor(Color.decode(aSecColor),
				Color.decode(aSecColor));
	}

	public static Color calculateNrColor(final int aShirtPattern,
			final Color aPriColor, final Color aSecColor) {
		if (aShirtPattern == 1 || aShirtPattern == 4 || aShirtPattern == 11
				|| aShirtPattern == 13 || aShirtPattern == 14
				|| aShirtPattern == 17 || aShirtPattern == 20) {
			final Color nrColorPri = calculateNrColor(aPriColor);
			final Color nrColorSec = calculateNrColor(aSecColor);
			return nrColorPri.equals(nrColorSec) ? nrColorPri : aSecColor;
		} else {
			return calculateNrColor(aPriColor, aSecColor);
		}
	}

	private static Color getCloserCornerColor(final Color aColor) {
		final int r = aColor.getRed();
		final int g = aColor.getGreen();
		final int b = aColor.getBlue();

		int i = 0;
		final Color color1 = CORNER_COLORS[i];
		final int r1 = color1.getRed();
		final int g1 = color1.getGreen();
		final int b1 = color1.getBlue();
		double distance = Math.sqrt((r - r1) * (r - r1) + (g - g1) * (g - g1)
				+ (b - b1) * (b - b1));
		double minDistance = distance;
		int iMin = i;

		for (i = 1; i < CORNER_COLORS.length; ++i) {
			final Color color2 = CORNER_COLORS[i];
			final int r2 = color2.getRed();
			final int g2 = color2.getGreen();
			final int b2 = color2.getBlue();
			distance = Math.sqrt((r - r2) * (r - r2) + (g - g2) * (g - g2)
					+ (b - b2) * (b - b2));
			if (minDistance > distance) {
				minDistance = distance;
				iMin = i;
			}
		}
		return CORNER_COLORS[iMin];
	}

	private static Color calculateNrColor(final Color aColor) {
		/*
		 * int rPri = aColor.getRed(); int gPri = aColor.getGreen(); int bPri =
		 * aColor.getBlue();
		 * 
		 * 
		 * double dWhite = Math.sqrt((rPri - 255) * (rPri - 255) + (gPri - 255)
		 * * (gPri - 255) + (bPri - 255) * (bPri - 255)); double dBlack =
		 * Math.sqrt(rPri * rPri + gPri * gPri + bPri * bPri); return dWhite >
		 * dBlack ? Color.WHITE : Color.BLACK;
		 */
		final Color closer = getCloserCornerColor(aColor);
		if (Color.BLACK.equals(closer) || Color.RED.equals(closer)
				|| Color.BLUE.equals(closer)) {
			return Color.WHITE;
		} else {
			return Color.BLACK;
		}

	}

	private static Color calculateNrColor(final Color aPriColor,
			final Color aSecColor) {
		final Color cPri = calculateNrColor(aPriColor);
		final Color cSec = calculateNrColor(aSecColor);
		if (cPri.equals(cSec)) {
			return cPri;
		}
		return calculateNrColorByDistance(aPriColor, aSecColor);
	}

	private static Color calculateNrColorByDistance(final Color aPriColor,
			final Color aSecColor) {
		final int rPri = aPriColor.getRed();
		final int gPri = aPriColor.getGreen();
		final int bPri = aPriColor.getBlue();

		final int rSec = aSecColor.getRed();
		final int gSec = aSecColor.getGreen();
		final int bSec = aSecColor.getBlue();

		final double d = Math.sqrt(
				(rPri - rSec) * (rPri - rSec) + (gPri - gSec) * (gPri - gSec)
						+ (bPri - bSec) * (bPri - bSec));

		int i = 0;
		double dPri = Math.sqrt((rPri - CORNER_COLORS[i].getRed())
				* (rPri - CORNER_COLORS[i].getRed())
				+ (gPri - CORNER_COLORS[i].getGreen())
						* (gPri - CORNER_COLORS[i].getGreen())
				+ (bPri - CORNER_COLORS[i].getBlue())
						* (bPri - CORNER_COLORS[i].getBlue()));
		double dSec = Math.sqrt((rSec - CORNER_COLORS[i].getRed())
				* (rSec - CORNER_COLORS[i].getRed())
				+ (gSec - CORNER_COLORS[i].getGreen())
						* (rSec - CORNER_COLORS[i].getGreen())
				+ (bSec - CORNER_COLORS[i].getBlue())
						* (rSec - CORNER_COLORS[i].getBlue()));
		double p = (d + dPri + dSec) / 2;

		double a = p * (p - d) * (p - dPri) * (p - dSec);

		double max = a;
		int iMax = i;

		for (i = 1; i < CORNER_COLORS.length; ++i) {
			dPri = Math.sqrt((rPri - CORNER_COLORS[i].getRed())
					* (rPri - CORNER_COLORS[i].getRed())
					+ (gPri - CORNER_COLORS[i].getGreen())
							* (gPri - CORNER_COLORS[i].getGreen())
					+ (bPri - CORNER_COLORS[i].getBlue())
							* (bPri - CORNER_COLORS[i].getBlue()));
			dSec = Math.sqrt((rSec - CORNER_COLORS[i].getRed())
					* (rSec - CORNER_COLORS[i].getRed())
					+ (gSec - CORNER_COLORS[i].getGreen())
							* (rSec - CORNER_COLORS[i].getGreen())
					+ (bSec - CORNER_COLORS[i].getBlue())
							* (rSec - CORNER_COLORS[i].getBlue()));
			p = (d + dPri + dSec) / 2;

			a = p * (p - d) * (p - dPri) * (p - dSec);
			if (max < a) {
				max = a;
				iMax = i;
			}
		}

		return CORNER_COLORS[iMax];
	}
}
