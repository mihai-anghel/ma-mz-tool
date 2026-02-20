package ro.anghel.mz.ui;

import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import com.jeta.forms.components.panel.FormPanel;

import ro.anghel.ctr.Controller;
import ro.anghel.view.ControllableView;

public class PnlPlayerComparator extends FormPanel implements ControllableView {

	private static final long serialVersionUID = 1L;

	private static final String MODEL = "gui/PnlPlayerComparator.xml";

	private static final String PNL_PLAYER_DETAILS1 = "PnlPlayerDetails1";

	private static final String PNL_PLAYER_DETAILS2 = "PnlPlayerDetails2";

	private static final String CMB_PLAYER1 = "CmbPlayer1";

	private static final String CMB_PLAYER2 = "CmbPlayer2";

	public PnlPlayerComparator() {
		super(MODEL);
	}

	public PnlPlayerDetails getPnlPlayerDetails1() {
		return (PnlPlayerDetails) getPanel(PNL_PLAYER_DETAILS1);
	}

	public PnlPlayerDetails getPnlPlayerDetails2() {
		return (PnlPlayerDetails) getPanel(PNL_PLAYER_DETAILS2);
	}

	public JComboBox<?> getCmbPlayer1() {
		return getComboBox(CMB_PLAYER1);
	}

	public JComboBox<?> getCmbPlayer2() {
		return getComboBox(CMB_PLAYER2);
	}

	@Override
	public void addListener(final Controller<?, ?> aController) {
		getCmbPlayer1().addActionListener((ActionListener) aController);
		getCmbPlayer2().addActionListener((ActionListener) aController);
	}

	@Override
	public void removeListener(final Controller<?, ?> aController) {
		getCmbPlayer1().removeActionListener((ActionListener) aController);
		getCmbPlayer2().removeActionListener((ActionListener) aController);
	}

}
