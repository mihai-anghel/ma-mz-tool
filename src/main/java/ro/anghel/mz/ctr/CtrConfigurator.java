package ro.anghel.mz.ctr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

import ro.anghel.ctr.AbstractController;
import ro.anghel.ctr.Controller;
import ro.anghel.mz.domain.Position;
import ro.anghel.mz.domain.PositionCalculator;
import ro.anghel.mz.domain.PositionCalculatorSettings;
import ro.anghel.mz.ui.PnlConfigurator;
import ro.anghel.mz.ui.PnlTableView;
import ro.anghel.mz.ui.model.SkillPercentsTableModel;
import ro.anghel.mz.ui.renderer.SkillPercentsCellRender;
import ro.anghel.view.ControllableView;

public class CtrConfigurator<T_PrevCtr extends Controller<?, ?>> extends
		AbstractController<CtrConfigurator<T_PrevCtr>, T_PrevCtr, CtrConfigurator.CtrKey, CtrConfigurator.BoKey, CtrConfigurator.ViewKey>
		implements ActionListener {

	public enum CtrKey {
	}

	public enum BoKey {
	}

	public enum ViewKey {
		PNL_CONFIGURATOR
	}

	public CtrConfigurator(final T_PrevCtr aPreviousController,
			final PnlConfigurator aPnlConfigurator) {
		super(aPreviousController, new ControllableView[] { aPnlConfigurator },
				null);
	}

	@Override
	protected void addSubControllers() {
	}

	@Override
	protected void addViews(final ControllableView[] aViews) {
		putView(ViewKey.PNL_CONFIGURATOR, aViews[0]);
	}

	@Override
	protected void addBusinessObjects(final Object[] aBusinessObjects) {
	}

	@Override
	protected void disposeController() {
	}

	@Override
	protected void initViews() {
		final PnlConfigurator pnlFormations = getPnlConfigurator();
		final JCheckBox chkUseStExFo = pnlFormations.getChkUseStExFo();
		chkUseStExFo.setSelected(
				PositionCalculatorSettings.INSTANCE.useStaminaExperienceForm());

		JFormattedTextField txt = pnlFormations.getTxtStaminaFactor();
		txt.setValue(Double.valueOf(
				PositionCalculatorSettings.INSTANCE.getStaminaFactor()));

		txt = pnlFormations.getTxtExperienceFactor();
		txt.setValue(Double.valueOf(
				PositionCalculatorSettings.INSTANCE.getExperienceFactor()));

		txt = pnlFormations.getTxtFormFactor();
		txt.setValue(Double
				.valueOf(PositionCalculatorSettings.INSTANCE.getFormFactor()));

		final PnlTableView pnlPercents = pnlFormations.getPnlPercents();
		final PositionCalculator gkCalc = new PositionCalculator(
				Position.GK.calculator());
		final PositionCalculator wbCalc = new PositionCalculator(
				Position.WB.calculator());
		final PositionCalculator cbCalc = new PositionCalculator(
				Position.CB.calculator());
		final PositionCalculator dmCalc = new PositionCalculator(
				Position.DM.calculator());
		final PositionCalculator cmCalc = new PositionCalculator(
				Position.CM.calculator());
		final PositionCalculator wmCalc = new PositionCalculator(
				Position.WM.calculator());
		final PositionCalculator amCalc = new PositionCalculator(
				Position.AM.calculator());
		final PositionCalculator fwCalc = new PositionCalculator(
				Position.FW.calculator());

		final SkillPercentsCellRender cellRender = new SkillPercentsCellRender();
		pnlPercents.setTblDataModel(new SkillPercentsTableModel(gkCalc, wbCalc,
				cbCalc, dmCalc, cmCalc, wmCalc, amCalc, fwCalc), cellRender);

	}

	private PnlConfigurator getPnlConfigurator() {
		return (PnlConfigurator) getView(ViewKey.PNL_CONFIGURATOR);
	}

	@Override
	protected void initBusinessObjects() {
	}

	@Override
	protected void initController() {
	}

	@Override
	protected void setVisible(final boolean aVisible) {
	}

	@Override
	protected void updateController() {
	}

	@Override
	public void actionPerformed(final ActionEvent aEvent) {
		final Object src = aEvent.getSource();
		final PnlConfigurator pnl = getPnlConfigurator();
		boolean bReinitAll = false;
		if (src == pnl.getBtnApply()) {
			final PnlTableView pnlPr = pnl.getPnlPercents();
			final SkillPercentsTableModel model = (SkillPercentsTableModel) pnlPr
					.getTblData().getModel();
			final PositionCalculator gkCalc = model
					.getRowData(Position.GK.ordinal());
			final PositionCalculator wbCalc = model
					.getRowData(Position.WB.ordinal());
			final PositionCalculator cbCalc = model
					.getRowData(Position.CB.ordinal());
			final PositionCalculator dmCalc = model
					.getRowData(Position.DM.ordinal());
			final PositionCalculator cmCalc = model
					.getRowData(Position.CM.ordinal());
			final PositionCalculator wmCalc = model
					.getRowData(Position.WM.ordinal());
			final PositionCalculator amCalc = model
					.getRowData(Position.AM.ordinal());
			final PositionCalculator fwCalc = model
					.getRowData(Position.FW.ordinal());

			Position.GK.calculator().copy(gkCalc);
			Position.WB.calculator().copy(wbCalc);
			Position.CB.calculator().copy(cbCalc);
			Position.DM.calculator().copy(dmCalc);
			Position.CM.calculator().copy(cmCalc);
			Position.WM.calculator().copy(wmCalc);
			Position.AM.calculator().copy(amCalc);
			Position.FW.calculator().copy(fwCalc);

			PositionCalculatorSettings.setUseStaminaExperienceForm(
					pnl.getChkUseStExFo().isSelected());

			Number v = (Number) pnl.getTxtStaminaFactor().getValue();
			PositionCalculatorSettings.setStaminaFactor(v.doubleValue());

			v = (Number) pnl.getTxtExperienceFactor().getValue();
			PositionCalculatorSettings.setExperienceFactor(v.doubleValue());

			v = (Number) pnl.getTxtFormFactor().getValue();
			PositionCalculatorSettings.setFormFactor(v.doubleValue());

			PositionCalculator.save();

			bReinitAll = true;
		} else if (src == pnl.getBtnDefault()) {
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(pnl,
					"Are you sure? \nAll your changes will be losed!",
					"Configurator", JOptionPane.YES_NO_OPTION)) {
				PositionCalculator.loadDefaults();
				PositionCalculator.save();
				bReinitAll = true;
			}
		} else if (src == pnl.getBtnRevert()) {
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(pnl,
					"Are you sure? \nAll your changes will be losed!",
					"Configurator", JOptionPane.YES_NO_OPTION)) {
				reinit();
			}
		}

		if (bReinitAll) {
			final T_PrevCtr prevCtr = getPreviousController();
			if (prevCtr instanceof CtrMain) {
				for (final Controller<?, ?> reinitCtr : prevCtr
						.getSubControllers()) {
					reinitCtr.reinit();
				}
			} else {
				reinit();
			}
			JOptionPane.showMessageDialog(getPnlConfigurator(),
					"Operation completed!", "Configurator",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
