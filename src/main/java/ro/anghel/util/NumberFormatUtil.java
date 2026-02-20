package ro.anghel.util;

import java.text.NumberFormat;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public enum NumberFormatUtil {
	FORMAT_2_2(2, 2), //
	FORMAT_3_3(3, 3);

	private NumberFormat numberFormat = null;
	private DefaultFormatterFactory formatterFactory = null;
	private final int minFractDigits;
	private final int maxFractDigits;

	NumberFormatUtil(final int minFractDigits, final int maxFractDigits) {
		this.minFractDigits = minFractDigits;
		this.maxFractDigits = maxFractDigits;
	}

	public NumberFormat numberFormat() {
		if (numberFormat == null) {
			numberFormat = NumberFormat.getInstance();
			numberFormat.setMinimumFractionDigits(minFractDigits);
			numberFormat.setMaximumFractionDigits(maxFractDigits);
			numberFormat.setGroupingUsed(false);
		}
		return numberFormat;
	}

	public DefaultFormatterFactory formatterFactory() {
		if (formatterFactory == null) {
			final NumberFormatter formatter = new NumberFormatter(
					numberFormat());
			formatterFactory = new DefaultFormatterFactory(formatter, formatter,
					formatter);
		}
		return formatterFactory;
	}

}
