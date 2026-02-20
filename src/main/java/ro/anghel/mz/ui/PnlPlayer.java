package ro.anghel.mz.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JLabel;

import com.jeta.forms.components.panel.FormPanel;

public class PnlPlayer extends FormPanel {

	private static final long serialVersionUID = 1L;

	private static final String MODEL = "gui/PnlPlayer.xml";

	private static final String LBL_NAME = "LblName";

	private static final String LBL_NUMBER = "LblNumber";

	public PnlPlayer(final int aShirtPattern, final short aNumber,
			final String aName, final String positionName,
			final Color aPriColor, final Color aSecColor, final Color aNrColor,
			final String aInjurySeverity, final boolean aAtTrainingCamp) {
		super(MODEL);
		final JLabel lblNr = getLblNumber();

		AbstractTShirtSmallIcon icon = null;
		icon = new TShirtSmallIcon(positionName, aShirtPattern, aPriColor,
				aSecColor, aNrColor, aInjurySeverity, aAtTrainingCamp);

		lblNr.setIcon(icon);
		lblNr.setText(String.valueOf(aNumber));
		final Color nrColor = icon.getNumberColor();
		lblNr.setForeground(nrColor);

		final JLabel lblName = getLblName();
		final Font font = lblName.getFont().deriveFont(Font.BOLD, 10);
		lblName.setFont(font);
		lblName.setText(aName);
		setToolTipText(positionName + " - " + aNumber + ". " + aName);
		setOpaque(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public JLabel getLblNumber() {
		return getLabel(LBL_NUMBER);
	}

	public JLabel getLblName() {
		return getLabel(LBL_NAME);
	}

}
