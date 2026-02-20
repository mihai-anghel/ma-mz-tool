package ro.anghel.mz.ui;

import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;

import com.jeta.forms.components.panel.FormPanel;

import ro.anghel.ctr.Controller;
import ro.anghel.util.NumberFormatUtil;
import ro.anghel.view.ControllableView;

public class PnlConfigurator extends FormPanel implements ControllableView {

	private static final long serialVersionUID = 1L;

	private static final String MODEL = "gui/PnlConfigurator.xml";

	private static final String PNL_PERCENTS = "PnlPercents";

	private static final String CHK_USE_ST_EX_FO = "ChkUseStExFo";

	private static final String TXT_STAMINA_FACTOR = "TxtStaminaFactor";

	private static final String TXT_EXPERIENCE_FACTOR = "TxtExperienceFactor";

	private static final String TXT_FORM_FACTOR = "TxtFormFactor";

	private static final String BTN_APPLY = "BtnApply";

	private static final String BTN_DEFAULT = "BtnDefault";

	private static final String BTN_REVERT = "BtnRevert";

	public PnlConfigurator() {
		super(MODEL);
		final PnlTableView pnlPercents = getPnlPercents();
		pnlPercents.setTitle("Skill Percents");
		pnlPercents.setAutoCreateRowSorter(false);
		pnlPercents.getTblData().setRowSorter(null);

		final DefaultFormatterFactory fmtFactory = NumberFormatUtil.FORMAT_3_3
				.formatterFactory();

		JFormattedTextField txt = getTxtStaminaFactor();
		txt.setFormatterFactory(fmtFactory);

		txt = getTxtExperienceFactor();
		txt.setFormatterFactory(fmtFactory);

		txt = getTxtFormFactor();
		txt.setFormatterFactory(fmtFactory);

	}

	public PnlTableView getPnlPercents() {
		return (PnlTableView) getPanel(PNL_PERCENTS);
	}

	public JCheckBox getChkUseStExFo() {
		return getCheckBox(CHK_USE_ST_EX_FO);
	}

	public JFormattedTextField getTxtStaminaFactor() {
		return (JFormattedTextField) getTextField(TXT_STAMINA_FACTOR);
	}

	public JFormattedTextField getTxtExperienceFactor() {
		return (JFormattedTextField) getTextField(TXT_EXPERIENCE_FACTOR);
	}

	public JFormattedTextField getTxtFormFactor() {
		return (JFormattedTextField) getTextField(TXT_FORM_FACTOR);
	}

	public AbstractButton getBtnApply() {
		return getButton(BTN_APPLY);
	}

	public AbstractButton getBtnDefault() {
		return getButton(BTN_DEFAULT);
	}

	public AbstractButton getBtnRevert() {
		return getButton(BTN_REVERT);
	}

	@Override
	public void addListener(final Controller<?, ?> aController) {
		getBtnApply().addActionListener((ActionListener) aController);
		getBtnRevert().addActionListener((ActionListener) aController);
		getBtnDefault().addActionListener((ActionListener) aController);
	}

	@Override
	public void removeListener(final Controller<?, ?> aController) {
		getBtnApply().removeActionListener((ActionListener) aController);
		getBtnRevert().removeActionListener((ActionListener) aController);
		getBtnDefault().removeActionListener((ActionListener) aController);
	}

}
