package ro.anghel.mz.ui;

import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.jeta.forms.components.panel.FormPanel;

import ro.anghel.ctr.Controller;
import ro.anghel.view.ControllableView;

public class PnlFormations extends FormPanel implements ControllableView {

	private static final long serialVersionUID = 1L;

	private static final String MODEL = "gui/PnlFormations.xml";

	private static final String PNL_SQUAD = "PnlSquad";

	private static final String PNL_SUBS = "PnlSubs";

	private static final String PNL_FIELD = "PnlField";

	private static final String LBL_AVERAGE = "LblAverage";

	private static final String CMB_FORMATION = "CmbFormation";

	private static final String CMB_SHORT_FORMATION = "CmbShortFormation";

	private static final String LBL_CAPTAIN = "LblCaptain";

	private static final String PNL_PENALTY = "PnlPenalty";

	private static final String PNL_FREEKICK = "PnlFreekick";

	private static final String CHK_USE_GA = "ChkUseGA";

	public PnlFormations() {
		super(MODEL);
		getPnlSquad().setTitle("Startup Formation");
		getPnlSubs().setTitle("Substitutes");
		getPnlPenalty().setTitle("Penalty Shooters");
		getPnlFreekick().setTitle("Freekick Shooters");
	}

	public PnlTableView getPnlSquad() {
		return (PnlTableView) getPanel(PNL_SQUAD);
	}

	public PnlTableView getPnlSubs() {
		return (PnlTableView) getPanel(PNL_SUBS);
	}

	public PnlField getPnlField() {
		return (PnlField) getPanel(PNL_FIELD);
	}

	public JLabel getLblAverage() {
		return getLabel(LBL_AVERAGE);
	}

	public JComboBox<?> getCmbFormation() {
		return getComboBox(CMB_FORMATION);
	}

	public JComboBox<?> getCmbShortFormation() {
		return getComboBox(CMB_SHORT_FORMATION);
	}

	public JLabel getLblCaptain() {
		return getLabel(LBL_CAPTAIN);
	}

	public PnlTableView getPnlPenalty() {
		return (PnlTableView) getPanel(PNL_PENALTY);
	}

	public PnlTableView getPnlFreekick() {
		return (PnlTableView) getPanel(PNL_FREEKICK);
	}

	public JCheckBox getChkUseGA() {
		return getCheckBox(CHK_USE_GA);
	}

	@Override
	public void addListener(final Controller<?, ?> aController) {
		getCmbFormation().addActionListener((ActionListener) aController);
		getCmbShortFormation().addActionListener((ActionListener) aController);
		getChkUseGA().addActionListener((ActionListener) aController);
	}

	@Override
	public void removeListener(final Controller<?, ?> aController) {
		getCmbFormation().removeActionListener((ActionListener) aController);
		getCmbShortFormation()
				.removeActionListener((ActionListener) aController);
		getChkUseGA().removeActionListener((ActionListener) aController);
	}

}
