package ro.anghel.mz.ctr;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import ro.anghel.ctr.AbstractController;
import ro.anghel.ctr.Controller;
import ro.anghel.mz.domain.Formation;
import ro.anghel.mz.domain.FormationType;
import ro.anghel.mz.domain.GroupedFormationType;
import ro.anghel.mz.domain.Player;
import ro.anghel.mz.domain.Team;
import ro.anghel.mz.genetic.FormationGeneticConfig;
import ro.anghel.mz.service.genetic.GeneticFormationOptimizer;
import ro.anghel.mz.ui.PnlFormations;
import ro.anghel.mz.ui.model.ShootersTableModel;
import ro.anghel.mz.ui.model.SquadTableModel;
import ro.anghel.mz.ui.renderer.SquadCellRender;
import ro.anghel.util.NumberFormatUtil;
import ro.anghel.view.ControllableView;

public class CtrFormations<T_PrevCtr extends Controller<?, ?>> extends
		AbstractController<CtrFormations<T_PrevCtr>, T_PrevCtr, CtrFormations.CtrKey, CtrFormations.BoKey, CtrFormations.ViewKey>
		implements ActionListener {

	public enum CtrKey {
	}

	public enum BoKey {
		TEAM
	}

	public enum ViewKey {
		PNL_FORMATIONS
	}

	private static final GeneticFormationOptimizer FORMATION_OPTIMIZER = FormationGeneticConfig.FORMATION_OPTIMIZER;

	private CtrFieldArrangement<CtrFormations<T_PrevCtr>> ctrFieldArrangement = null;

	public CtrFormations(final T_PrevCtr aPreviousController,
			final PnlFormations aPnlFormations, final Team aTeam) {
		super(aPreviousController, new ControllableView[] { aPnlFormations },
				new Object[] { aTeam });
	}

	@Override
	protected void addSubControllers() {
	}

	@Override
	protected void addViews(final ControllableView[] aViews) {
		putView(ViewKey.PNL_FORMATIONS, aViews[0]);
	}

	@Override
	protected void addBusinessObjects(final Object[] aBusinessObjects) {
		putBusinessObject(BoKey.TEAM, aBusinessObjects[0]);
	}

	@Override
	protected void disposeController() {
		if (ctrFieldArrangement != null) {
			ctrFieldArrangement.dispose();
		}
	}

	@Override
	protected void initViews() {
		final PnlFormations pnlFormations = getPnlFormations();
		initializeFormationComboBoxes(pnlFormations);
		updateFormation(pnlFormations,
				(FormationType) pnlFormations.getCmbFormation()
						.getSelectedItem(),
				pnlFormations.getChkUseGA().isSelected());
	}

	private static void initializeFormationComboBoxes(
			final PnlFormations pnlFormations) {
		@SuppressWarnings("unchecked")
		final JComboBox<GroupedFormationType> cmbGrp = (JComboBox<GroupedFormationType>) pnlFormations
				.getCmbShortFormation();
		GroupedFormationType grpType = (GroupedFormationType) cmbGrp
				.getSelectedItem();
		cmbGrp.setModel(new DefaultComboBoxModel<>(GroupedFormationType.all()));
		if (grpType != null) {
			cmbGrp.setSelectedItem(grpType);
		}

		grpType = (GroupedFormationType) cmbGrp.getSelectedItem();
		@SuppressWarnings("unchecked")
		final JComboBox<FormationType> cmbFormation = (JComboBox<FormationType>) pnlFormations
				.getCmbFormation();
		final FormationType formationType = (FormationType) cmbFormation
				.getSelectedItem();
		cmbFormation.setModel(new DefaultComboBoxModel<>(
				grpType != null ? grpType.getFormationTypes()
						: new FormationType[] {}));
		if (formationType != null) {
			cmbFormation.setSelectedItem(formationType);
		}
	}

	@Override
	public void actionPerformed(final ActionEvent aEvent) {
		final PnlFormations pnlFormations = getPnlFormations();
		@SuppressWarnings("unchecked")
		final JComboBox<GroupedFormationType> cmbGrp = (JComboBox<GroupedFormationType>) pnlFormations
				.getCmbShortFormation();
		@SuppressWarnings("unchecked")
		final JComboBox<FormationType> cmbFormation = (JComboBox<FormationType>) pnlFormations
				.getCmbFormation();
		final JCheckBox chk = pnlFormations.getChkUseGA();

		if (cmbGrp == aEvent.getSource() || cmbFormation == aEvent.getSource()
				|| chk == aEvent.getSource()) {
			if (cmbGrp == aEvent.getSource()) {
				final GroupedFormationType grpType = (GroupedFormationType) cmbGrp
						.getSelectedItem();
				cmbFormation.setModel(new DefaultComboBoxModel<>(
						grpType != null ? grpType.getFormationTypes()
								: new FormationType[] {}));
			}
			updateFormation(pnlFormations,
					(FormationType) cmbFormation.getSelectedItem(),
					chk.isSelected());
		}
	}

	private void updateFormation(final PnlFormations pnlFormations,
			final FormationType formationType, final boolean useGA) {
		Formation formation = new Formation(formationType, getTeam());
		if (useGA) {
			final Cursor oldCursor = pnlFormations.getCursor();
			pnlFormations
					.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			formation = FORMATION_OPTIMIZER.optimize(formation);
			pnlFormations.setCursor(oldCursor);
		}
		refreshFormationUI(pnlFormations, formation);
	}

	private void refreshFormationUI(final PnlFormations pnlFormations,
			final Formation formation) {
		final SquadCellRender cellRender = new SquadCellRender();
		pnlFormations.getPnlSquad().setTblDataModel(
				new SquadTableModel(formation.getStarting()), cellRender);
		pnlFormations.getPnlSubs().setTblDataModel(
				new SquadTableModel(formation.getSubstitutes()), cellRender);

		pnlFormations.getLblAverage().setText(NumberFormatUtil.FORMAT_2_2
				.numberFormat().format(formation.getAverageRating()));

		final Player captain = formation.getCaptain();
		pnlFormations.getLblCaptain()
				.setText(captain != null ? captain.toString() : "");

		pnlFormations.getPnlPenalty().setTblDataModel(
				new ShootersTableModel(formation.getPenaltyShooters()));
		pnlFormations.getPnlFreekick().setTblDataModel(
				new ShootersTableModel(formation.getFreekickShooters()));

		if (ctrFieldArrangement != null) {
			ctrFieldArrangement.dispose();
		}
		ctrFieldArrangement = new CtrFieldArrangement<>(this,
				pnlFormations.getPnlField(), formation);
		ctrFieldArrangement.init();
	}

	public Team getTeam() {
		return (Team) getBusinessObject(BoKey.TEAM);
	}

	private PnlFormations getPnlFormations() {
		return (PnlFormations) getView(ViewKey.PNL_FORMATIONS);
	}

	@Override
	protected void initBusinessObjects() {
	}

	@Override
	protected void initController() {
		resetDirty();
	}

	@Override
	protected void setVisible(final boolean aVisible) {
	}

	@Override
	protected void updateController() {
	}

	public boolean isDirty() {
		final Team team = getTeam();
		if (team != null) {
			for (final Player player : team.getPlayers()) {
				if (player.isDirty()) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean resetDirty() {
		final Team team = getTeam();
		if (team != null) {
			for (final Player player : team.getPlayers()) {
				player.resetDirty();
			}
		}
		return false;
	}
}
