package ro.anghel.mz.ui.renderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ro.anghel.mz.domain.Foot;
import ro.anghel.mz.domain.FormStatus;
import ro.anghel.mz.domain.Injury;
import ro.anghel.mz.domain.InjurySeverity;
import ro.anghel.mz.domain.Player;
import ro.anghel.mz.domain.PositionCalculator;
import ro.anghel.util.NumberFormatUtil;
import ro.anghel.util.StringFormatUtil;

public class SpecialRenderers {

	private SpecialRenderers() {
	}

	private static JLabel createBaseComponent(final Color aForeground,
			final String aText) {
		final JLabel lbl = new JLabel(aText);
		lbl.setOpaque(true);
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		final Font font = lbl.getFont();
		if (font.isBold()) {
			lbl.setFont(font.deriveFont(Font.PLAIN));
		}
		lbl.setForeground(aForeground);
		return lbl;
	}

	private static JLabel createBaseComponent(final Color aForeground) {
		return createBaseComponent(aForeground, null);
	}

	public static Component getATCComponent(final Color aForeground,
			final Player aPlayer) {
		String atc = null;

		final JLabel lbl = createBaseComponent(aForeground);
		if (aPlayer.isAlreadyAtTrainingCamp()) {
			final ImageIcon icon = new ImageIcon(SpecialRenderers.class
					.getClassLoader().getResource("gui/icon_trainingcamp.gif"));
			lbl.setIcon(icon);
			atc = "Player Already Participated In Training Camp This Season";
		}
		lbl.setToolTipText(atc);
		return lbl;
	}

	public static Component getCountryComponent(final Color aForeground,
			final Player aPlayer) {
		final JLabel lbl = createBaseComponent(aForeground);
		final String co = aPlayer.getCountryShortName();
		final Icon icon = CountryFlags.getFlag(co);
		if (icon != null) {
			lbl.setIcon(icon);
		} else {
			lbl.setText(co);
		}
		lbl.setToolTipText(aPlayer.getCountryLongName());
		return lbl;
	}

	public static Component getFootComponent(final Color aForeground,
			final Player aPlayer) {
		final Foot foot = aPlayer.getFoot();
		final JLabel lbl = createBaseComponent(aForeground,
				String.valueOf(foot.shortName()));
		lbl.setToolTipText(foot.longName());
		return lbl;
	}

	public static Component getInjuryComponent(final Color aForeground,
			final Player aPlayer) {
		final Injury injury = aPlayer.getInjury();
		final String inj = injury.getInjuryName();
		String injTooltip = null;
		final JLabel lbl = createBaseComponent(aForeground);
		if (!"".equals(inj)) {
			final InjurySeverity injurySeverity = injury.getInjurySeverity();
			final String iconName = InjurySeverity.RECOVER
					.equals(injurySeverity) ? "gui/injury_recover.gif"
							: "gui/injury_severe.gif";
			final ImageIcon icon = new ImageIcon(SpecialRenderers.class
					.getClassLoader().getResource(iconName));
			lbl.setIcon(icon);
			final int d = injury.getInjuryDays();
			injTooltip = injurySeverity.niceName() + ": " + inj + " (" + d
					+ " Day" + (d != 1 ? "s" : "") + " Left)";
		}
		lbl.setToolTipText(injTooltip);
		return lbl;
	}

	public static Component getStatusComponent(final Color aForeground,
			final Player aPlayer) {
		final FormStatus status = aPlayer.getStatus();
		final JLabel lbl = createBaseComponent(aForeground);
		lbl.setIcon(FormStatusIcons.getIcon(status));
		lbl.setToolTipText(status.longName());
		return lbl;
	}

	public static Component getTCComponent(final Color aForeground,
			final Player aPlayer) {
		String tc = aPlayer.getTrainingCamp();
		final JLabel lbl = createBaseComponent(aForeground);
		if (!"".equals(tc)) {
			final ImageIcon icon = new ImageIcon(SpecialRenderers.class
					.getClassLoader().getResource("gui/tc_"
							+ aPlayer.getTrainingCampDomain() + ".gif"));
			lbl.setHorizontalTextPosition(SwingConstants.CENTER);
			final Font font = lbl.getFont().deriveFont(Font.BOLD, 8);
			lbl.setFont(font);
			lbl.setIcon(icon);
			lbl.setForeground(Color.WHITE);
			lbl.setText(String.valueOf(aPlayer.getTrainingCampNr()));
			tc = "Training Camp - " + StringFormatUtil
					.capitalize(aPlayer.getTrainingCampDomain()) + ": " + tc;
		}
		lbl.setToolTipText("".equals(tc) ? null : tc);
		return lbl;
	}

	public static Component getPrSumComponent(final Color aForeground,
			final PositionCalculator aPositionCalculator) {
		final double sum = aPositionCalculator.getPrSum();
		final String strSum = NumberFormatUtil.FORMAT_2_2.numberFormat()
				.format(sum);
		final JLabel lbl = createBaseComponent(aForeground, strSum);
		final Font font = lbl.getFont();
		lbl.setFont(font.deriveFont(Font.BOLD));
		lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		if (sum != 100.0) {
			final ImageIcon icon = new ImageIcon(SpecialRenderers.class
					.getClassLoader().getResource("gui/exclamation.gif"));
			lbl.setIcon(icon);
			lbl.setToolTipText("Sum should be equal with 100!");
		}
		return lbl;
	}

}
